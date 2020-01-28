package com.sneaklife.ut.servlet;

import cn.hutool.core.io.resource.ClassPathResource;
import com.sneaklife.config.SneakLifeSystemEnum;
import com.sneaklife.config.pkv.CommonPKV;
import com.sneaklife.ut.file.FileUtil;
import com.sneaklife.ut.spring.SpringContextUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/18 13:48
 */
public final class SneakLifeServlet {

    private static final Logger log = LoggerFactory.getLogger(SneakLifeServlet.class);

    private static final String CHARSET_NAME = "iso-8859-1";

    private static final String REGION = "内网IP|内网IP";

    private final MultiValueMap<String, String> mvm;

    private final HttpServletRequest httpServletRequest;

    private final HttpServletResponse httpServletResponse;

    SneakLifeServlet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.mvm = new HttpHeaders();
    }

    public MultiValueMap<String, String> getMvm() {
        return mvm;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setCrossDomain() {
        String origin =  httpServletRequest.getHeader(HttpHeaders.ORIGIN);
        mvm.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        mvm.set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
    }

    public void setExcelHeaders(String excelName) throws UnsupportedEncodingException {
        setCrossDomain();
        String v = "attachment; filename=" + new String(excelName.getBytes(), CHARSET_NAME);
        httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, v);
    }

    public void setResponseNoCache() {
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setDateHeader("Expires", -1);
    }

    public OutputStream getOutputStream() throws IOException {
        return httpServletResponse.getOutputStream();
    }

    public void closeOutputStream() throws IOException {
        OutputStream outputStream = getOutputStream();
        outputStream.flush();
        outputStream.close();
    }

    public String getServerPath() {
        return httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + getContextPath() + "/";
    }

    public String getContextPath() {
        return httpServletRequest.getContextPath();
    }

    public <T extends Object> T getSessionValue(String key , Class<T> c) {
        return (T) httpServletRequest.getSession().getAttribute(key);
    }

    public String getSessionId() {
        String id = httpServletRequest.getSession().getId();
        log.debug("Session id: {}", id);
        return id;
    }

    public String getCacheSessionId() {
        final CommonPKV commonPKV = SpringContextUtil.getBean(CommonPKV.class);
        StringBuilder sb = new StringBuilder();
        String id = httpServletRequest.getSession().getId();
        sb.append(commonPKV.getUserCacheDir()).append(SneakLifeSystemEnum.SNEAK_LIFE_CACHE_DIR_CUT.toName()).append(id);
        id = sb.toString();
        log.debug("Session id: {}", id);
        return id;
    }

    public void setSessionValue(String key, String value) {
        log.debug("key === {} , value === {}" ,key ,value);
        httpServletRequest.getSession().setAttribute(key, value);
    }

    public String getIp(){
        String ip = httpServletRequest.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        if  ("127.0.0.1".equals(ip))  {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return ip;
    }

    public String getCityInfo(String ip) {
        try {
            String path = "ip2region/ip2region.db";
            String name = "ip2region.db";
            DbConfig config = new DbConfig();
            File file = FileUtil.inputStreamToFile(new ClassPathResource(path).getStream(), name);
            DbSearcher searcher = new DbSearcher(config, file.getPath());
            Method method = searcher.getClass().getMethod("btreeSearch", String.class);
            DataBlock dataBlock = (DataBlock) method.invoke(searcher, ip);
            String address = dataBlock.getRegion().replace("0|","");
            if(address.charAt(address.length()-1) == '|'){
                address = address.substring(0,address.length() - 1);
            }
            return address.equals(REGION) ? "内网IP" : address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getBrowser(){
        UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }
}
