package com.sneaklife.service.system.monitoring.imp;

import com.sneaklife.common.CommonUtil;
import com.sneaklife.server.SneakLifeServerInfo;
import com.sneaklife.service.system.monitoring.MonitoringService;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.*;

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
        Map<String,Object> map = new HashMap<>();
        map.put("head","SystemMonitoring");
        map.put("data",data);
        return CommonUtil.respResultDataSUCCEED(map);
    }

    private List<Map<String,Object>> generateCpuData(List<Map<String,Object>> cpu){
        Map<String,Object> item = new HashMap<>();
        List<String> legendData = new ArrayList<>();
        List<String> xAxisData = getCpuX();
        List<List<Double>> seriesDataList = new ArrayList<>();
        for (int i = 0; i < cpu.size(); i++) {
            Map<String,Object> c = cpu.get(i);
            legendData.add("CPU-0" + (i + 1));
            List<Double> seriesData = new ArrayList<>();
            for (String dk: xAxisData) {
                seriesData.add(Double.valueOf(String.valueOf(c.get(dk))));
            }
            seriesDataList.add(seriesData);
        }
        item.put("id","cpuListen");
        item.put("text","CPU MONITORING");
        item.put("subtext","cpuListen");
        item.put("legendData", legendData);
        item.put("xAxisData", xAxisData);
        item.put("seriesDataList", seriesDataList);
        List<Map<String,Object>> data = new ArrayList<>();
        data.add(item);
        return data;
    }

    private List<String> getCpuX(){
        List<String> xAxisData = new ArrayList<>();
        xAxisData.add("CPU用户使用率");
        xAxisData.add("CPU系统使用率");
        xAxisData.add("CPU当前等待率");
        xAxisData.add("CPU当前错误率");
        xAxisData.add("CPU当前空闲率");
        xAxisData.add("CPU总的使用率");
        return xAxisData;
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
