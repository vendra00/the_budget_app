package com.t1tanic.budget.service;

import com.t1tanic.budget.model.ExpenseItem;
import com.t1tanic.budget.model.IncomeItem;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing expense items.
 */
public interface ExpenseItemService {
    ExpenseItem addExpenseItem(ExpenseItem expenseItem);
    Optional<ExpenseItem> getExpenseItemById(Long id);
    List<ExpenseItem> getAllExpenseItems();
    Optional<ExpenseItem> updateExpenseItem(Long id, ExpenseItem updatedExpenseItem);
    void deleteExpenseItem(Long id);
}
