package com.t1tanic.budget.repository;

import com.t1tanic.budget.model.ExpenseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseItemRepository extends JpaRepository<ExpenseItem, Long> {
    long countByCategoryId(Long categoryId);
}
