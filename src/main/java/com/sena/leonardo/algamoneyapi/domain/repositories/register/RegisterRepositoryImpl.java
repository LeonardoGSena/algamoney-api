package com.sena.leonardo.algamoneyapi.domain.repositories.register;

import com.sena.leonardo.algamoneyapi.domain.models.Register;
import com.sena.leonardo.algamoneyapi.domain.models.Register_;
import com.sena.leonardo.algamoneyapi.domain.repositories.filter.RegisterFilter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RegisterRepositoryImpl implements RegisterRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Register> filter(RegisterFilter registerFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Register> criteria = builder.createQuery(Register.class);
        Root<Register> root = criteria.from(Register.class);

        Predicate[] predicates = getLimits(registerFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Register> query = manager.createQuery(criteria);
        getAddLimitsPageable(pageable, query);

        return new PageImpl<>(query.getResultList(), pageable, getTotal(registerFilter));
    }

    private Predicate[] getLimits(RegisterFilter registerFilter, CriteriaBuilder builder, Root<Register> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(registerFilter.getDescription())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Register_.description)), "%" + registerFilter.getDescription().toLowerCase() + "%"
            ));
        }

        if (registerFilter.getDueDateFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(
                    root.get(Register_.dueDate), registerFilter.getDueDateFrom()
            ));
        }

        if (registerFilter.getDueDateTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(
                    root.get(Register_.dueDate), registerFilter.getDueDateTo()
            ));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void getAddLimitsPageable(Pageable pageable, TypedQuery<Register> query) {
        int actualPage = pageable.getPageNumber();
        int totalRegisterPerPage = pageable.getPageSize();
        int firstPageRegister = actualPage * totalRegisterPerPage;

        query.setFirstResult(firstPageRegister);
        query.setMaxResults(totalRegisterPerPage);
    }

    private Long getTotal(RegisterFilter registerFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Register> root = criteria.from(Register.class);

        Predicate[] predicates = getLimits(registerFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
