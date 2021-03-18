package com.cloaker.app.Controller;

import com.cloaker.app.Campaign.DTO.CampaignReqRespData;
import com.cloaker.app.Campaign.DTO.UpdateCampaignData;
import com.cloaker.app.Campaign.Service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @PostMapping(value = "/newCampaign", consumes = "application/json")
    public HttpStatus newCampaign(@RequestBody CampaignReqRespData campaignReqRespData) {
        return campaignService.newCampaign(campaignReqRespData);
    }

    @GetMapping(value = "/loadCampaignIDs", produces = "application/json")
    public List<Integer> loadCampaignIDs() {
        return campaignService.loadCampaignIDs();
    }

    @GetMapping(value = "/loadCampaign/{campaignId}")
    public CampaignReqRespData loadCampaign(@PathVariable int campaignId) {
        return campaignService.loadCampaign(campaignId);
    }

    @PutMapping(value = "/updateCampaign", consumes = "application/json")
    public HttpStatus updateCampaign(@RequestBody UpdateCampaignData updateCampaignData) {
        return campaignService.updateCampaign(updateCampaignData);
    }

    @DeleteMapping(value = "/deleteCampaign/{campaignId}")
    public HttpStatus deleteRuleSet(@PathVariable int campaignId) { return campaignService.deleteCampaign(campaignId); }
}
