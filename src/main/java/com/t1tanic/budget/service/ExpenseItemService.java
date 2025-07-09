package com.t1tanic.budget.service;

import com.t1tanic.budget.model.ExpenseItem;

import java.util.List;

/**
 * Service interface for managing expense items.
 */
public interface ExpenseItemService {
    void addExpenseItem(ExpenseItem expenseItem);
    ExpenseItem getExpenseItemById(Long id);
    List<ExpenseItem> getAllExpenseItems();
    ExpenseItem updateExpenseItem(Long id, ExpenseItem updatedExpenseItem);
    void deleteExpenseItem(Long id);
}
