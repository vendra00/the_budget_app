package com.t1tanic.budget.service.impl;

import com.t1tanic.budget.model.IncomeItem;
import com.t1tanic.budget.repository.IncomeItemRepository;
import com.t1tanic.budget.service.IncomeItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class IncomeItemServiceImpl implements IncomeItemService {

    private final IncomeItemRepository incomeItemRepository;

    @Override
    public IncomeItem addIncomeItem(IncomeItem incomeItem) {
        log.info("Creating income item: {}", incomeItem);
        return incomeItemRepository.save(incomeItem);
    }

    @Override
    public Optional<IncomeItem> getIncomeItemById(Long id) {
        log.info("Fetching income item with ID: {}", id);
        return incomeItemRepository.findById(id);
    }

    @Override
    public List<IncomeItem> getAllIncomeItems() {
        log.info("Fetching all income items");
        return incomeItemRepository.findAll();
    }

    @Override
    public Optional<IncomeItem> updateIncomeItem(Long id, IncomeItem updatedIncomeItem) {
        log.info("Updating income item with ID: {}", id);
        return incomeItemRepository.findById(id)
                .map(existing -> {
                    existing.setDescription(updatedIncomeItem.getDescription());
                    existing.setAmount(updatedIncomeItem.getAmount());
                    existing.setDate(updatedIncomeItem.getDate());
                    existing.setCategory(updatedIncomeItem.getCategory());
                    existing.setUser(updatedIncomeItem.getUser());
                    return incomeItemRepository.save(existing);
                });
    }

    @Override
    public void deleteIncomeItem(Long id) {
        log.info("Deleting income item with ID: {}", id);
        if (!incomeItemRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete. Income item not found with ID: " + id);
        }
        incomeItemRepository.deleteById(id);
    }
}
