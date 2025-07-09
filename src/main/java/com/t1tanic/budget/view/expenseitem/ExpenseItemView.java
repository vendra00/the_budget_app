package com.t1tanic.budget.view.expenseitem;

import com.t1tanic.budget.enums.ExpenseType;
import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.model.ExpenseItem;
import com.t1tanic.budget.service.AppUserService;
import com.t1tanic.budget.service.CategoryService;
import com.t1tanic.budget.service.ExpenseItemService;
import com.t1tanic.budget.view.DashboardView;
import com.t1tanic.budget.view.expenseitem.dialog.DeleteExpenseItemDialog;
import com.t1tanic.budget.view.expenseitem.dialog.EditExpenseItemDialog;
import com.t1tanic.budget.view.expenseitem.dialog.InsertExpenseItemDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Route(value = "expenses", layout = DashboardView.class)
@PageTitle("Expense Items")
public class ExpenseItemView extends VerticalLayout {

    private final ExpenseItemService expenseItemService;
    private final Grid<ExpenseItem> expenseGrid = new Grid<>(ExpenseItem.class);

    public ExpenseItemView(ExpenseItemService expenseItemService,
                           CategoryService categoryService,
                           AppUserService appUserService) {

        this.expenseItemService = expenseItemService;

        expenseGrid.setColumns("id", "description");

        expenseGrid.addColumn(item -> String.format("$%.2f", item.getAmount()))
                .setHeader("Amount")
                .setKey("amount")
                .setSortable(true);;

        expenseGrid.addComponentColumn(item -> {
                    ExpenseType type = item.getExpenseType();
                    if (type == null) return new Span();

                    Icon icon = type.getIcon().create();
                    icon.setSize("16px");
                    icon.getStyle().set("paddingRight", "6px");

                    Span label = new Span(type.getDisplayName());
                    HorizontalLayout layout = new HorizontalLayout(icon, label);
                    layout.setAlignItems(FlexComponent.Alignment.CENTER);

                    return layout;
                })
                .setHeader("Expense Type")
                .setComparator((a, b) -> {
                    String aType = a.getExpenseType() != null ? a.getExpenseType().getDisplayName() : "";
                    String bType = b.getExpenseType() != null ? b.getExpenseType().getDisplayName() : "";
                    return aType.compareToIgnoreCase(bType);
                })
                .setSortable(true);


        // Format date as dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        expenseGrid.addColumn(item -> item.getDate() != null ? item.getDate().format(formatter) : "")
                .setHeader("Date")
                .setKey("date")
                .setSortable(true);

        expenseGrid.addComponentColumn(item -> {
            Icon editIcon = VaadinIcon.EDIT.create();
            editIcon.setColor("blue");
            editIcon.getStyle().set("cursor", "pointer");

            editIcon.addClickListener(e -> {
                List<Category> categories = categoryService.getAllCategories();
                List<AppUser> users = appUserService.getAllUsers();

                EditExpenseItemDialog dialog = new EditExpenseItemDialog(item, categories, users, updatedItem -> {
                    expenseItemService.updateExpenseItem(updatedItem.getId(), updatedItem);
                    refreshGrid();
                });
                dialog.open();
            });

            return editIcon;
        }).setHeader("Edit");

        expenseGrid.addComponentColumn(item -> {
            Icon trashIcon = VaadinIcon.TRASH.create();
            trashIcon.setColor("red");
            trashIcon.getStyle().set("cursor", "pointer");

            trashIcon.addClickListener(e -> {
                DeleteExpenseItemDialog dialog = new DeleteExpenseItemDialog(item, deletedItem -> {
                    expenseItemService.deleteExpenseItem(deletedItem.getId());
                    refreshGrid();
                });
                dialog.open();
            });

            return trashIcon;
        }).setHeader("Delete");

        Button addButton = new Button("Add Expense", e -> {
            List<Category> categories = categoryService.getAllCategories();
            List<AppUser> users = appUserService.getAllUsers();

            InsertExpenseItemDialog dialog = new InsertExpenseItemDialog(categories, users, newItem -> {
                expenseItemService.addExpenseItem(newItem);
                refreshGrid();
            });
            dialog.open();
        });

        add(new H1("Expense Management"), addButton, expenseGrid);
        refreshGrid();
    }

    private void refreshGrid() {
        expenseGrid.setItems(expenseItemService.getAllExpenseItems());
    }
}
