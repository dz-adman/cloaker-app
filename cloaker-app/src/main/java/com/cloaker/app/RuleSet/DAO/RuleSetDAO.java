package com.cloaker.app.RuleSet.DAO;

import com.cloaker.app.RuleSet.Entity.RuleSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RuleSetDAO extends CrudRepository<RuleSet, Integer> {

    Optional<RuleSet> findByRulesetId(final int rulesetId);
}
