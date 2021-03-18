package com.cloaker.app.Campaign.DAO;

import com.cloaker.app.Campaign.Entity.Campaign;
import com.cloaker.app.RuleSet.Entity.RuleSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignDAO extends CrudRepository<Campaign, Integer> {

    Optional<Campaign> findByRuleset(RuleSet ruleset);
    Optional<Campaign> findByUrl(String url);
}
