package com.cloaker.app.Service;

import com.cloaker.app.Campaign.DAO.CampaignDAO;
import com.cloaker.app.Campaign.Entity.Campaign;
import com.cloaker.app.POJO.FilterData.RuleSetData;
import com.cloaker.app.RuleSet.Entity.RuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InitRuleSetPOJOs {

    @Autowired
    CampaignDAO campaignDAO;

    public RuleSetData loadRuleSetToPOJOs(String url) {
        Optional<Campaign> campaign = campaignDAO.findByUrl(url);
        if(campaign.isPresent()) {
            RuleSet ruleSet = campaign.get().getRuleset();
            return ruleSet.getRuleset();
        }
        return null;
    }
}
