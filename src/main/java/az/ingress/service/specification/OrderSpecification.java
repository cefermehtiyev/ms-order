package az.ingress.service.specification;

import az.ingress.dao.entity.OrderEntity;
import az.ingress.dao.entity.OrderItemEntity;
import az.ingress.model.constants.CriteriaConstants;
import az.ingress.model.criteria.OrderCriteria;
import az.ingress.util.PredicateUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static az.ingress.model.constants.CriteriaConstants.AMOUNT;



@AllArgsConstructor
public class OrderSpecification implements Specification<OrderEntity> {
    private OrderCriteria orderCriteria;

    public Predicate toPredicate(Root<OrderEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb){

        var predicates = PredicateUtil.builder()
                .addNullSafety(orderCriteria.getAmountFrom(),
                        amountFrom -> cb.greaterThanOrEqualTo(root.get(AMOUNT),amountFrom ))
                .addNullSafety(orderCriteria.getAmountTo(),
                        amountTo -> cb.lessThanOrEqualTo(root.get(AMOUNT),amountTo ))
                .build();

        return cb.and(predicates);
    }


}
