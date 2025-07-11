package com.t1tanic.budget.view.category;

import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.service.CategoryService;
import com.t1tanic.budget.util.I18nUtils;
import com.t1tanic.budget.view.category.dialog.DeleteCategoryDialog;
import com.t1tanic.budget.view.category.dialog.EditCategoryDialog;
import com.t1tanic.budget.view.category.dialog.InsertCategoryDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import static com.t1tanic.budget.enums.i18n.category.CategoryViewI18N.*;

@Route(value = "categories", layout = com.t1tanic.budget.view.DashboardView.class)
public class CategoryView extends VerticalLayout implements BeforeEnterObserver {

    private final I18nUtils i18n;
    private final CategoryService categoryService;
    private final Grid<Category> categoryGrid = new Grid<>(Category.class);

    public CategoryView(I18nUtils i18n, CategoryService categoryService) {
        this.i18n = i18n;
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
                }, i18n);
                dialog.open();
            });

            return editIcon;
        }).setHeader(EDIT.get(i18n));

        categoryGrid.addComponentColumn(category -> {
            Icon trashIcon = VaadinIcon.TRASH.create();
            trashIcon.setColor("red");
            trashIcon.getStyle().set("cursor", "pointer");

            trashIcon.addClickListener(e -> {
                DeleteCategoryDialog dialog = new DeleteCategoryDialog(category, u -> {
                    categoryService.deleteCategory(u.getId());
                    refreshGrid();
                }, i18n);
                dialog.open();
            });

            return trashIcon;
        }).setHeader(DELETE.get(i18n));

        Button addButton = new Button(ADD_CATEGORY.get(i18n), e -> {
            InsertCategoryDialog dialog = new InsertCategoryDialog(newCategory -> {
                categoryService.addCategory(newCategory);
                refreshGrid();
            }, i18n);
            dialog.open();
        });

        add(new H1(VIEW_TITLE.get(i18n)), addButton, categoryGrid);
        refreshGrid();
    }

    private void refreshGrid() {
        categoryGrid.setItems(categoryService.getAllCategories());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        getElement().executeJs("document.title = $0", VIEW_TITLE.get(i18n));
    }
}
