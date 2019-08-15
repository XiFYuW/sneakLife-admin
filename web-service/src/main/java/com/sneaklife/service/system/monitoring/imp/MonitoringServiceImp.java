package com.sneaklife.service.system.monitoring.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.server.SneakLifeServerInfo;
import com.sneaklife.service.system.monitoring.MonitoringService;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/14 16:23
 */
@Service
public class MonitoringServiceImp implements MonitoringService {

    @Override
    public ResponseEntity<String> cpuListen() throws Exception{
        List<Map<String,Object>> data = SneakLifeServerInfo.getServerCpu();
        data = generateCpuData(data);
        return CommonUtil.respResultDataSUCCEED(data);
    }
    private List<Map<String,Object>> generateCpuData(List<Map<String,Object>> cpu){
        Map<String,Object> item = new HashMap<>();
        item.put("id","cpuListen");
        Map<String,Object> style = new HashMap<>(2);
        style.put("width","100%");
        style.put("height","500px");
        item.put("style",style);
        Map<String,Object> option = new HashMap<>();
        Map<String,Object> title = new HashMap<>();
        title.put("text","CPU Listen");
        title.put("subtext","Listen");
        option.put("title",title);
        Map<String,Object> tooltip = new HashMap<>();
        tooltip.put("trigger","axis");
        option.put("tooltip",tooltip);
        Map<String,Object> legend = new HashMap<>();
        // cpu 数量
        List<String> legendData = new ArrayList<>();
        legend.put("data",legendData);
        option.put("legend",legend);
        Map<String,Object> toolbox = new HashMap<>();
        toolbox.put("show",true);
        Map<String,Object> feature = new HashMap<>();
        Map<String,Object> mark = new HashMap<>();
        mark.put("show",true);
        feature.put("mark",mark);
        Map<String,Object> magicType = new HashMap<>();
        magicType.put("show",true);
        magicType.put("type",new String[]{"stack","tiled"});
        feature.put("magicType",magicType);
        Map<String,Object> restore = new HashMap<>();
        restore.put("show",true);
        feature.put("restore",restore);
        Map<String,Object> saveAsImage = new HashMap<>();
        saveAsImage.put("show",true);
        feature.put("saveAsImage",saveAsImage);
        toolbox.put("feature",feature);
        option.put("toolbox",toolbox);
        option.put("calculable",true);
        List<Map<String,Object>> xAxis = new ArrayList<>();// !!!
        Map<String,Object> xAxisMap = new HashMap<>();
        xAxisMap.put("type","category");
        xAxisMap.put("boundaryGap",false);
        List<String> xAxisData = new ArrayList<>();// !!!
        xAxisMap.put("data",xAxisData);
        xAxis.add(xAxisMap);
        option.put("xAxis",xAxis);
        List<Map<String,Object>> yAxis = new ArrayList<>();// !!!
        Map<String,Object> yAxisMap = new HashMap<>();
        yAxisMap.put("type","value");
        yAxis.add(yAxisMap);
        option.put("yAxis",yAxis);
        List<Map<String,Object>> series = new ArrayList<>();// !!!
        Map<String,Object> seriesMapItem1 = new HashMap<>();
        seriesMapItem1.put("name","");
        seriesMapItem1.put("type","line");
        seriesMapItem1.put("smooth",true);
        seriesMapItem1.put("itemStyle","");
        seriesMapItem1.put("data","");
        series.add(seriesMapItem1);
        option.put("series",series);
        item.put("option",option);
        return null;
    }

    @Override
    public void tomcatListen() {

    }

    @Override
    public void jvmListen() {
        int kb = 1024;
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        // 剩余的物理内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        System.err.println("获得免费的物理内存大小"+freePhysicalMemorySize);
        long committedVirtualMemorySize = osmxb.getCommittedVirtualMemorySize();
        System.err.println("获取提交的虚拟内存大小:"+committedVirtualMemorySize);
        long freeSwapSpaceSize = osmxb.getFreeSwapSpaceSize();
        System.err.println("获得免费的交换空间大小:"+freeSwapSpaceSize);
        double processCpuLoad = osmxb.getProcessCpuLoad();
        System.err.println("获取进程Cpu负载:"+processCpuLoad);
        long processCpuTime = osmxb.getProcessCpuTime();
        System.err.println("获取进程Cpu时间:"+processCpuTime);
        double systemCpuLoad = osmxb.getSystemCpuLoad();
        System.err.println("获取系统Cpu负载:"+systemCpuLoad);
        long totalSwapSpaceSize = osmxb.getTotalSwapSpaceSize();
        System.err.println("获取总交换空间大小:"+totalSwapSpaceSize);
        long totalPhysicalMemorySize = osmxb.getTotalPhysicalMemorySize();
        System.err.println("获取总物理内存大小:"+totalPhysicalMemorySize);
    }
}
