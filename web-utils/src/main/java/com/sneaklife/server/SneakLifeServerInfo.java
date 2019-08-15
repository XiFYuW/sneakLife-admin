package com.sneaklife.server;

import com.sneaklife.interfaces.ParameterTransformation;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.*;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/15 15:42
 */
public class SneakLifeServerInfo {

    private static final Sigar sigar = new Sigar();

    public static List<Map<String,Object>> getServerCpu() throws SigarException {
        List<Map<String,Object>> data = new ArrayList<>();
        CpuInfo[] cpuInfos = sigar.getCpuInfoList();
        CpuPerc[] cpuPercs = sigar.getCpuPercList();
        for (int i = 0; i < cpuInfos.length; i++) {
            CpuInfo cpuInfo = cpuInfos[i];
            CpuPerc cpuPerc = cpuPercs[i];
            Map<String,Object> map = new HashMap<>();
            map.put("CPU的总量MHz",cpuInfo.getMhz());
            map.put("CPU生产商",cpuInfo.getVendor());
            map.put("CPU类别",cpuInfo.getModel());
            map.put("CPU缓存数量",cpuInfo.getCacheSize());
            map.put("CPU用户使用率",CpuPerc.format(cpuPerc.getUser()));
            map.put("CPU系统使用率",CpuPerc.format(cpuPerc.getSys()));
            map.put("CPU当前等待率",CpuPerc.format(cpuPerc.getWait()));
            map.put("CPU当前错误率",CpuPerc.format(cpuPerc.getNice()));
            map.put("CPU当前空闲率",CpuPerc.format(cpuPerc.getIdle()));
            map.put("CPU总的使用率",CpuPerc.format(cpuPerc.getCombined()));
            data.add(map);
        }
        return data;
    }
}
