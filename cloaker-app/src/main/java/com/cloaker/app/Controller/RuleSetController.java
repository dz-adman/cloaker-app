package com.cloaker.app.Controller;

import com.cloaker.app.POJO.FilterData.RuleSetData;
import com.cloaker.app.RuleSet.DTO.RuleSetReqRespUpdtData;
import com.cloaker.app.RuleSet.Service.RuleSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RuleSetController {

    @Autowired
    RuleSetService ruleSetService;

    @Autowired
    RuleSetData ruleSetData;

    @PostMapping(value = "/newRuleSet", consumes = "application/json")
    public HttpStatus newRuleSet(@RequestBody RuleSetReqRespUpdtData ruleSetReqRespUpdtData) {
        return ruleSetService.newRuleSet(ruleSetReqRespUpdtData);
    }

    @GetMapping(value = "/loadRuleSetIDs", produces = "application/json")
    public List<Integer> loadRuleSetIDs() {
        return ruleSetService.loadRuleSetIDs();
    }

    @GetMapping(value = "/loadRuleSet/{ruleSetId}")
    public RuleSetReqRespUpdtData loadRuleSet(@PathVariable int ruleSetId) {
        return ruleSetService.loadRuleSet(ruleSetId);
    }

    @PutMapping(value = "/updateRuleSet", consumes = "application/json")
    public HttpStatus updateRuleSet(@RequestBody RuleSetReqRespUpdtData ruleSetReqRespUpdtData) {
        return ruleSetService.updateRuleSet(ruleSetReqRespUpdtData);
    }

    @DeleteMapping(value = "/deleteRuleSet/{ruleSetId}")
    public HttpStatus deleteRuleSet(@PathVariable int ruleSetId) { return ruleSetService.deleteRuleSet(ruleSetId); }
}
