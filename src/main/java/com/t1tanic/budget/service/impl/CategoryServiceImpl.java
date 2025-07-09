package com.t1tanic.budget.service.impl;

import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.repository.CategoryRepository;
import com.t1tanic.budget.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        log.info("Added new category: {}", category);
        categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        log.info("Get category by ID: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + id));
    }

    @Override
    public List<Category> getAllCategories() {
        log.info("Retrieving all categories");
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        log.info("Updating category with ID: {}", id);
        Category existing = getCategoryById(id);
        existing.setName(updatedCategory.getName());
        return categoryRepository.save(existing);
    }


    @Override
    public void deleteCategory(Long id) {
        log.info("Deleting category with ID: {}", id);
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
        log.info("Deleted category with ID: {}", id);
    }
}
