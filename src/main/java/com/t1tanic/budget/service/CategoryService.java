package com.t1tanic.budget.service;

import com.t1tanic.budget.model.Category;

import java.util.List;

/**
 * Service interface for managing categories.
 */
public interface CategoryService {
    /**
     * Adds a new category.
     *
     * @param category the category to add
     */
    void addCategory(Category category);

    /**
     * Retrieves a category by its ID.
     *
     * @param id the ID of the category
     * @return the category with the specified ID
     */
    Category getCategoryById(Long id);

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories
     */
    List<Category> getAllCategories();

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to update
     * @param updatedCategory the updated category data
     * @return the updated category
     */
    Category updateCategory(Long id, Category updatedCategory);

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete
     */
    void deleteCategory(Long id);
}
