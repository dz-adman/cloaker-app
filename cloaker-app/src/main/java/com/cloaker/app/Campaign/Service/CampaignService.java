package com.cloaker.app.Campaign.Service;

import com.cloaker.app.Campaign.DAO.CampaignDAO;
import com.cloaker.app.Campaign.DTO.CampaignReqRespData;
import com.cloaker.app.Campaign.DTO.UpdateCampaignData;
import com.cloaker.app.Campaign.Entity.Campaign;
import com.cloaker.app.RuleSet.DAO.RuleSetDAO;
import com.cloaker.app.RuleSet.Entity.RuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    @Autowired
    CampaignDAO campaignDAO;

    @Autowired
    RuleSetDAO ruleSetDAO;

    public HttpStatus newCampaign(CampaignReqRespData campaignReqRespData) {
        if(campaignReqRespData != null) {
            Optional<RuleSet> ruleset = ruleSetDAO.findById(campaignReqRespData.getRulesetId());
            Campaign campaign = new Campaign();
            campaign.setUrl(campaignReqRespData.getUrl());
            campaign.setCampaignName(campaignReqRespData.getCampaignName());
            campaign.setOriginalUrl(campaignReqRespData.getOriginalUrl());
            campaign.setFallbackUrl(campaignReqRespData.getFallbackUrl());
            campaign.setCreatedOn(new Date(System.currentTimeMillis()));
            campaign.setRuleset(ruleset.get());
            campaignDAO.save(campaign);
            return HttpStatus.OK;
        }
        return HttpStatus.EXPECTATION_FAILED;
    }

    public List<Integer> loadCampaignIDs() {
        List <Integer> campaignIDs = new ArrayList<Integer>();
        campaignDAO.findAll().forEach(campaign -> {
            campaignIDs.add(campaign.getCampaignId());
        });
        return campaignIDs;
    }

    public CampaignReqRespData loadCampaign(int campaignId) {
        Optional<Campaign> campaign = campaignDAO.findById(campaignId);
        if(campaign.isPresent()) {
            CampaignReqRespData campaignReqRespData = new CampaignReqRespData();
            campaignReqRespData.setCampaignId(campaign.get().getCampaignId());
            campaignReqRespData.setCampaignName(campaign.get().getCampaignName());
            campaignReqRespData.setUrl(campaign.get().getUrl());
            campaignReqRespData.setOriginalUrl(campaign.get().getOriginalUrl());
            campaignReqRespData.setFallbackUrl(campaign.get().getFallbackUrl());
            campaignReqRespData.setRulesetId(campaign.get().getRuleset().getRulesetId());
            return campaignReqRespData;
        }
        else return null;
    }

    public HttpStatus updateCampaign(UpdateCampaignData updateCampaignData) {
        if(updateCampaignData != null) {
            Optional<Campaign> existingCampaign = campaignDAO.findById(updateCampaignData.getCampaignId());
            if (existingCampaign.isPresent()) {
                existingCampaign.get().setCampaignName(updateCampaignData.getCampaignName());
                existingCampaign.get().setOriginalUrl(updateCampaignData.getOriginalUrl());
                existingCampaign.get().setFallbackUrl(updateCampaignData.getFallbackUrl());
                Optional<RuleSet> ruleset = ruleSetDAO.findById(updateCampaignData.getRuleSetId());
                existingCampaign.get().setRuleset(ruleset.get());
                campaignDAO.save(existingCampaign.get());
                return HttpStatus.OK;
            }
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.EXPECTATION_FAILED;
    }

    public HttpStatus deleteCampaign(int campaignId) {
        campaignDAO.deleteById(campaignId);
        return HttpStatus.NO_CONTENT;
    }
}
