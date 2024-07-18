package org.eternity.reservation.persistence.memory;

import org.eternity.reservation.domain.DiscountCondition;
import org.eternity.reservation.domain.DiscountPolicy;
import org.eternity.reservation.persistence.DiscountConditionDAO;
import org.eternity.reservation.persistence.DiscountPolicyDAO;

import java.util.List;

public class DiscountPolicyMemoryDAO extends InMemoryDAO<DiscountPolicy> implements DiscountPolicyDAO {
    private DiscountConditionDAO discountConditionDAO;

    public DiscountPolicyMemoryDAO(DiscountConditionDAO discountConditionDAO) {
        this.discountConditionDAO = discountConditionDAO;
    }

    @Override
    public DiscountPolicy selectDiscountPolicy(Long movieId) {
        DiscountPolicy result = findOne(policy -> policy.getMovieId().equals(movieId));
        if (result == null) {
            return null;
        }

        List<DiscountCondition> conditions = discountConditionDAO.selectDiscountConditions(result.getId());
        result.setDiscountConditions(conditions);

        return result;
    }
}
