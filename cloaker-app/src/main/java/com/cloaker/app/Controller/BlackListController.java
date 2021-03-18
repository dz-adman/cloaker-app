package com.cloaker.app.Controller;

import com.cloaker.app.POJO.FilterData.IpBlackList;
import com.cloaker.app.POJO.FilterData.IspBlackList;
import com.cloaker.app.Service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlackListController {

    @Autowired
    BlacklistService blacklistService;

    @GetMapping(value = "/fetchIpBlackList", produces = "application/json")
    public IpBlackList fetchIpBlackList() {
        return blacklistService.fetchIpBlackList();
    }

    @GetMapping(value = "/fetchIspBlackList", produces = "application/json")
    public IspBlackList fetchIspBlackList() {
        return blacklistService.fetchIspBlackList();
    }

    @PostMapping(value = "/addIpToBlacklist", consumes = "application/json")
    public HttpStatus addIpToBlacklist(@RequestBody IpBlackList ipBlackList) {
        return blacklistService.addIpToBlacklist(ipBlackList);
    }

    @PostMapping(value = "/addIspToBlacklist", consumes = "application/json")
    public HttpStatus addIspToBlacklist(@RequestBody IspBlackList ispBlackList) {
        return blacklistService.addIspToBlacklist(ispBlackList);
    }

    @PostMapping(value = "/removeIpFromBlackList", consumes = "application/json")
    public HttpStatus removeIpFromBlacklist(@RequestBody IpBlackList ipBlackList) {
        return blacklistService.removeIpFromBlacklist(ipBlackList);
    }

    @PostMapping(value = "/removeIspFromBlackList", consumes = "application/json")
    public HttpStatus removeIspFromBlacklist(@RequestBody IspBlackList ispBlackList) {
        return blacklistService.removeIspFromBlacklist(ispBlackList);
    }

    @DeleteMapping(value = "/clearBlackLists")
    public HttpStatus clearIpBlackLists(@RequestParam boolean clearIpList, @RequestParam boolean clearIspList) {
        return blacklistService.clearBlackLists(clearIpList, clearIspList);
    }
}
