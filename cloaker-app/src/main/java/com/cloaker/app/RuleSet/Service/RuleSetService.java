package com.cloaker.app.RuleSet.Service;

import com.cloaker.app.RuleSet.DAO.RuleSetDAO;
import com.cloaker.app.RuleSet.DTO.RuleSetReqRespUpdtData;
import com.cloaker.app.RuleSet.Entity.RuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RuleSetService {

    @Autowired
    RuleSetDAO rulesetDao;

    public HttpStatus newRuleSet(RuleSetReqRespUpdtData ruleSetReqRespUpdtData) {
        if(ruleSetReqRespUpdtData.getRuleSet() != null) {
            rulesetDao.save(ruleSetReqRespUpdtData.getRuleSet());
            return HttpStatus.OK;
        }
        return HttpStatus.EXPECTATION_FAILED;
    }

    public List<Integer> loadRuleSetIDs() {
        List<Integer> ruleSetIDs = new ArrayList<Integer>();
        rulesetDao.findAll().forEach(ruleSet -> ruleSetIDs.add(ruleSet.getRulesetId()));
        return ruleSetIDs;
    }

    public RuleSetReqRespUpdtData loadRuleSet(int ruleSetId) {
        Optional<RuleSet> ruleSet = rulesetDao.findByRulesetId(ruleSetId);
        if(ruleSet.isPresent()) {
            RuleSetReqRespUpdtData ruleSetReqRespUpdtData = new RuleSetReqRespUpdtData();
            ruleSetReqRespUpdtData.setRuleSet(ruleSet.get());
            return ruleSetReqRespUpdtData;
        }
        return null;
    }

    public HttpStatus updateRuleSet(RuleSetReqRespUpdtData ruleSetReqRespUpdtData) {
        if(ruleSetReqRespUpdtData != null) {
            Optional<RuleSet> existingRuleSet = rulesetDao.findById(ruleSetReqRespUpdtData.getRuleSet().getRulesetId());
            if (existingRuleSet.isPresent()) {
                existingRuleSet.get().setRulesetName(ruleSetReqRespUpdtData.getRuleSet().getRulesetName());
                existingRuleSet.get().setRuleset(ruleSetReqRespUpdtData.getRuleSet().getRuleset());
                rulesetDao.save(existingRuleSet.get());
                return HttpStatus.OK;
            }
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.EXPECTATION_FAILED;
    }

    public HttpStatus deleteRuleSet(int ruleSetId) {
        rulesetDao.deleteById(ruleSetId);
        return HttpStatus.NO_CONTENT;
    }
}
