package com.t1tanic.budget.view.incomeitem;

import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.model.IncomeItem;
import com.t1tanic.budget.service.AppUserService;
import com.t1tanic.budget.service.CategoryService;
import com.t1tanic.budget.service.IncomeItemService;
import com.t1tanic.budget.view.DashboardView;
import com.t1tanic.budget.view.incomeitem.dialog.DeleteIncomeItemDialog;
import com.t1tanic.budget.view.incomeitem.dialog.EditIncomeItemDialog;
import com.t1tanic.budget.view.incomeitem.dialog.InsertIncomeItemDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "incomes", layout = DashboardView.class)
@PageTitle("Income Items")
public class IncomeItemView extends VerticalLayout {

    private final IncomeItemService incomeItemService;
    private final Grid<IncomeItem> incomeGrid = new Grid<>(IncomeItem.class);

    public IncomeItemView(IncomeItemService incomeItemService, CategoryService categoryService, AppUserService appUserService) {
        this.incomeItemService = incomeItemService;

        incomeGrid.setColumns("id", "description", "amount", "date", "incomeType");

        incomeGrid.addComponentColumn(item -> {
            Icon editIcon = VaadinIcon.EDIT.create();
            editIcon.setColor("blue");
            editIcon.getStyle().set("cursor", "pointer");

            editIcon.addClickListener(e -> {
                List<Category> categories = categoryService.getAllCategories();
                List<AppUser> users = appUserService.getAllUsers();

                EditIncomeItemDialog dialog = new EditIncomeItemDialog(item, categories, users, updatedItem -> {
                    incomeItemService.updateIncomeItem(updatedItem.getId(), updatedItem);
                    refreshGrid();
                });
                dialog.open();
            });

            return editIcon;
        }).setHeader("Edit");

        incomeGrid.addComponentColumn(item -> {
            Icon trashIcon = VaadinIcon.TRASH.create();
            trashIcon.setColor("red");
            trashIcon.getStyle().set("cursor", "pointer");

            trashIcon.addClickListener(e -> {
                DeleteIncomeItemDialog dialog = new DeleteIncomeItemDialog(item, deletedItem -> {
                    incomeItemService.deleteIncomeItem(deletedItem.getId());
                    refreshGrid();
                });
                dialog.open();
            });

            return trashIcon;
        }).setHeader("Delete");

        Button addButton = new Button("Add Income", e -> {
            List<Category> categories = categoryService.getAllCategories();
            List<AppUser> users = appUserService.getAllUsers();

            InsertIncomeItemDialog dialog = new InsertIncomeItemDialog(categories, users, newItem -> {
                incomeItemService.createIncomeItem(newItem);
                refreshGrid();
            });
            dialog.open();
        });

        add(new H1("Income Management"), addButton, incomeGrid);
        refreshGrid();
    }

    private void refreshGrid() {
        incomeGrid.setItems(incomeItemService.getAllIncomeItems());
    }
}
