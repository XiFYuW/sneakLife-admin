package com.sneaklife.server;

import org.hyperic.sigar.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/15 15:42
 */
public class SneakLifeServerInfo {

    public static List<Map<String,Object>> getServerCpu() throws SigarException {
        Sigar sigar = new Sigar();
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
            map.put("CPU用户使用率",percentOff(CpuPerc.format(cpuPerc.getUser())));
            map.put("CPU系统使用率",percentOff(CpuPerc.format(cpuPerc.getSys())));
            map.put("CPU当前等待率",percentOff(CpuPerc.format(cpuPerc.getWait())));
            map.put("CPU当前错误率",percentOff(CpuPerc.format(cpuPerc.getNice())));
            map.put("CPU当前空闲率",percentOff(CpuPerc.format(cpuPerc.getIdle())));
            map.put("CPU总的使用率",percentOff(CpuPerc.format(cpuPerc.getCombined())));
            data.add(map);
        }
        return data;
    }

    private static Double percentOff(String s){
        return Double.valueOf(s.substring(0, s.indexOf("%")));
    }
}
