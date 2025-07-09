package com.t1tanic.budget.view.category;

import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.service.CategoryService;
import com.t1tanic.budget.view.category.dialog.DeleteCategoryDialog;
import com.t1tanic.budget.view.category.dialog.EditCategoryDialog;
import com.t1tanic.budget.view.category.dialog.InsertCategoryDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Categories")
@Route(value = "categories", layout = com.t1tanic.budget.view.DashboardView.class)
public class CategoryView extends VerticalLayout {

    private final CategoryService categoryService;
    private final Grid<Category> categoryGrid = new Grid<>(Category.class);

    public CategoryView(CategoryService categoryService) {
        this.categoryService = categoryService;

        categoryGrid.setColumns("id", "name");

        categoryGrid.addComponentColumn(category -> {
            Icon editIcon = VaadinIcon.EDIT.create();
            editIcon.setColor("blue");
            editIcon.getStyle().set("cursor", "pointer");

            editIcon.addClickListener(e -> {
                EditCategoryDialog dialog = new EditCategoryDialog(category, updatedCategory -> {
                    categoryService.updateCategory(updatedCategory.getId(), updatedCategory);
                    refreshGrid();
                });
                dialog.open();
            });

            return editIcon;
        }).setHeader("Edit");

        categoryGrid.addComponentColumn(category -> {
            Icon trashIcon = VaadinIcon.TRASH.create();
            trashIcon.setColor("red");
            trashIcon.getStyle().set("cursor", "pointer");

            trashIcon.addClickListener(e -> {
                DeleteCategoryDialog dialog = new DeleteCategoryDialog(category, u -> {
                    categoryService.deleteCategory(u.getId());
                    refreshGrid();
                });
                dialog.open();
            });

            return trashIcon;
        }).setHeader("Delete");

        Button addButton = new Button("Add Category", e -> {
            InsertCategoryDialog dialog = new InsertCategoryDialog(newCategory -> {
                categoryService.addCategory(newCategory);
                refreshGrid();
            });
            dialog.open();
        });

        add(new H1("Category Management"), addButton, categoryGrid);
        refreshGrid();
    }

    private void refreshGrid() {
        categoryGrid.setItems(categoryService.getAllCategories());
    }
}
