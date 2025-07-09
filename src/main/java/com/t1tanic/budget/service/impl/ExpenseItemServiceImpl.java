package com.t1tanic.budget.service.impl;

import com.t1tanic.budget.model.ExpenseItem;
import com.t1tanic.budget.repository.ExpenseItemRepository;
import com.t1tanic.budget.service.ExpenseItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ExpenseItemServiceImpl implements ExpenseItemService {

    private final ExpenseItemRepository expenseItemRepository;

    @Override
    public ExpenseItem addExpenseItem(ExpenseItem expenseItem) {
        log.info("Adding new expense item: {}", expenseItem);
        return expenseItemRepository.save(expenseItem);
    }

    @Override
    public Optional<ExpenseItem> getExpenseItemById(Long id) {
        log.info("Getting expense item with ID: {}", id);
        return expenseItemRepository.findById(id);
    }

    @Override
    public List<ExpenseItem> getAllExpenseItems() {
        log.info("Getting all expense items");
        return expenseItemRepository.findAll();
    }

    @Override
    public Optional<ExpenseItem> updateExpenseItem(Long id, ExpenseItem updatedExpenseItem) {
        log.info("Updating expense item with ID: {}", id);

        return expenseItemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setDescription(updatedExpenseItem.getDescription());
                    existingItem.setAmount(updatedExpenseItem.getAmount());
                    existingItem.setDate(updatedExpenseItem.getDate());
                    existingItem.setCategory(updatedExpenseItem.getCategory());
                    existingItem.setExpenseType(updatedExpenseItem.getExpenseType());
                    existingItem.setUser(updatedExpenseItem.getUser());
                    return expenseItemRepository.save(existingItem);
                });
    }

    @Override
    public void deleteExpenseItem(Long id) {
        log.info("Deleting expense item with ID: {}", id);
        if (!expenseItemRepository.existsById(id)) {
            throw new EntityNotFoundException("ExpenseItem not found with ID: " + id);
        }
        expenseItemRepository.deleteById(id);
        log.info("Deleted expense item with ID: {}", id);
    }
}
