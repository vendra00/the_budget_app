package com.t1tanic.budget.service;

import com.t1tanic.budget.model.IncomeItem;

import java.util.List;
import java.util.Optional;

public interface IncomeItemService {
    IncomeItem createIncomeItem(IncomeItem incomeItem);

    Optional<IncomeItem> getIncomeItemById(Long id);

    List<IncomeItem> getAllIncomeItems();

    IncomeItem updateIncomeItem(Long id, IncomeItem updatedIncomeItem);

    void deleteIncomeItem(Long id);
}
