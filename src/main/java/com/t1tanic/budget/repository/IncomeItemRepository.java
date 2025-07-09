package com.t1tanic.budget.repository;

import com.t1tanic.budget.model.IncomeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeItemRepository extends JpaRepository<IncomeItem, Long> {}
