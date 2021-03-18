package com.cloaker.app.Service;

import com.cloaker.app.POJO.FilterData.IpBlackList;
import com.cloaker.app.POJO.FilterData.IspBlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BlacklistService {

    private final String IP_FILE_NAME = "src/main/resources/ipblacklist.json";
    private final String ISP_FILE_NAME = "src/main/resources/ispblacklist.json";

    @Autowired
    IpBlackList ipBlackList;

    @Autowired
    IspBlackList ispBlackList;

    public Map<String, Boolean> ipBlacklistFilter(String ipAddress) {
        Map<String, Boolean> ipBlacklistReport = new HashMap<>();
        try {
            Path path = Paths.get(IP_FILE_NAME);
            if (!Files.exists(path)) {
                ipBlacklistReport.put("IPBLACKLIST", true);
            } else {
                final Predicate<String> predicateIp = x -> x.equals(ipAddress);
                Stream<String> lines = Files.lines(path);
                List<String> listData = lines.collect(Collectors.toList());
                if (listData.stream().anyMatch(predicateIp))
                    ipBlacklistReport.put("IPBLACKLIST", false);
            }
            ipBlacklistReport.put("IPBLACKLIST", true);
            return ipBlacklistReport;
        } catch (Exception ex) {
            ex.printStackTrace();
            ipBlacklistReport.put("IPBLACKLIST", true);
            return ipBlacklistReport;
        }
    }

    public Map<String, Boolean> ispBlacklistFilter(String isp, String organization) {
        Map<String, Boolean> ispBlacklistReport = new HashMap<>();
        try {
            Path path = Paths.get(ISP_FILE_NAME);
            if (!Files.exists(path)) {
                ispBlacklistReport.put("ISPBLACKLIST", true);
            } else {
                Stream<String> lines = Files.lines(path);
                List<String> listData = lines.collect(Collectors.toList());
                Predicate<String> predicateIsp = x -> x.equalsIgnoreCase(isp);
                Predicate<String> predicateOrg = x -> x.equalsIgnoreCase(organization);
                if (listData.stream().anyMatch(predicateIsp.and(predicateOrg)))
                    ispBlacklistReport.put("ISPBLACKLIST", false);
            }
            ispBlacklistReport.put("ISPBLACKLIST", true);
            return ispBlacklistReport;
        } catch (Exception ex) {
            ex.printStackTrace();
            ispBlacklistReport.put("ISPBLACKLIST", true);
            return ispBlacklistReport;
        }
    }

    public HttpStatus addIpToBlacklist(IpBlackList ipBlackList) {
        try {
            ipBlackList.setIpList(ipBlackList.getIpList().stream().distinct().collect(Collectors.toList()));
            Path path = Paths.get(IP_FILE_NAME);
            if (!Files.exists(path)) {
                Files.createFile(path);
                Files.write(path, ipBlackList.getIpList());
            } else {
                Files.write(path,ipBlackList.getIpList() , StandardOpenOption.APPEND);
            }
            return HttpStatus.CREATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return HttpStatus.EXPECTATION_FAILED;
        }
    }

    public HttpStatus addIspToBlacklist(IspBlackList ispBlackList) {
        try {
            ispBlackList.setIspList(ispBlackList.getIspList().stream().distinct().collect(Collectors.toList()));
            Path path = Paths.get(ISP_FILE_NAME);
            if (!Files.exists(path)) {
                Files.createFile(path);
                Files.write(path, ispBlackList.getIspList());
            } else {
                Files.write(path,ispBlackList.getIspList() , StandardOpenOption.APPEND);
            }
            return HttpStatus.CREATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return HttpStatus.EXPECTATION_FAILED;
        }
    }

    public HttpStatus removeIpFromBlacklist(IpBlackList ipBlackList) {
        try {
            Path path = Paths.get(IP_FILE_NAME);
            if (!Files.exists(path)) {
                return HttpStatus.NOT_FOUND;
            } else {
                Stream<String> lines = Files.lines(path);
                List<String> listData = lines.collect(Collectors.toList());
                listData.removeAll(ipBlackList.getIpList());
                Files.write(path, listData, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            }
            return HttpStatus.OK;
        } catch (Exception ex) {
            ex.printStackTrace();
            return HttpStatus.EXPECTATION_FAILED;
        }
    }

    public HttpStatus removeIspFromBlacklist(IspBlackList ispBlackList) {
        try {
            Path path = Paths.get(ISP_FILE_NAME);
            if (!Files.exists(path)) {
                return HttpStatus.NOT_FOUND;
            } else {
                Stream<String> lines = Files.lines(path);
                List<String> listData = lines.collect(Collectors.toList());
                listData.removeAll(ispBlackList.getIspList());
                Files.write(path, listData, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            }
            return HttpStatus.OK;
        } catch (Exception ex) {
            ex.printStackTrace();
            return HttpStatus.EXPECTATION_FAILED;
        }
    }

    public IpBlackList fetchIpBlackList() {
        try {
            Path path = Paths.get(IP_FILE_NAME);
            if (!Files.exists(path)) {
                return null;
            } else {
                Stream<String> lines = Files.lines(path);
                ipBlackList.setIpList(lines.collect(Collectors.toList()));
            }
            return ipBlackList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public IspBlackList fetchIspBlackList() {
        try {
            Path path = Paths.get(ISP_FILE_NAME);
            if (!Files.exists(path)) {
                return null;
            } else {
                Stream<String> lines = Files.lines(path);
                ispBlackList.setIspList(lines.collect(Collectors.toList()));
            }
            return ispBlackList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public HttpStatus clearBlackLists(boolean clearIpList, boolean clearIspList) {
        if(clearIpList)
            clearBlackList(Paths.get(IP_FILE_NAME));
        if(clearIspList)
            clearBlackList(Paths.get(ISP_FILE_NAME));
        return HttpStatus.MULTI_STATUS;
    }

    private HttpStatus clearBlackList(final Path filePath) {
        try {
            if (!Files.exists(filePath)) {
                return HttpStatus.NOT_FOUND;
            } else {
                Files.write(filePath, new ArrayList<String>(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            }
            return HttpStatus.NO_CONTENT;
        } catch (Exception ex) {
            ex.printStackTrace();
            return HttpStatus.EXPECTATION_FAILED;
        }
    }

}
