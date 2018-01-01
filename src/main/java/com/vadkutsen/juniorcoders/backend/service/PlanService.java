package com.vadkutsen.juniorcoders.backend.service;

import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.Plan;
import com.vadkutsen.juniorcoders.backend.persistence.repositories.PlanRepository;
import com.vadkutsen.juniorcoders.enums.PlansEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    /**
     * Returns the Plan for the fgiven id or null if couldn't find one.
     * @param planId
     * @return
     */
    public Plan findPlanById(int planId) {
        return planRepository.findOne(planId);
    }

    /**
     * It creates a Basic or Pro plan
     * @param planId
     * @return
     */
    @Transactional
    public Plan createPlan(int planId) {

        Plan plan = null;
        if (planId == 1) {
            plan = planRepository.save(new Plan(PlansEnum.BASIC));
        } else if (planId == 2){
            plan = planRepository.save(new Plan(PlansEnum.PRO));
        } else {
            throw new IllegalArgumentException("PLan id " + planId + " not recognized.");
        }
        return plan;
    }


}
