package com.t1tanic.budget.service;

import com.t1tanic.budget.model.IncomeItem;

import java.util.List;
import java.util.Optional;

public interface IncomeItemService {
    IncomeItem addIncomeItem(IncomeItem incomeItem);
    Optional<IncomeItem> getIncomeItemById(Long id);
    List<IncomeItem> getAllIncomeItems();
    Optional<IncomeItem> updateIncomeItem(Long id, IncomeItem updatedIncomeItem);
    void deleteIncomeItem(Long id);
}
