package com.t1tanic.budget.view.incomeitem;

import com.t1tanic.budget.enums.IncomeType;
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

@Route(value = "incomes", layout = DashboardView.class)
@PageTitle("Income Items")
public class IncomeItemView extends VerticalLayout {

    private final IncomeItemService incomeItemService;
    private final Grid<IncomeItem> incomeGrid = new Grid<>(IncomeItem.class);

    public IncomeItemView(IncomeItemService incomeItemService,
                          CategoryService categoryService,
                          AppUserService appUserService) {
        this.incomeItemService = incomeItemService;

        incomeGrid.setColumns("id", "description");

        incomeGrid.addColumn(item -> String.format("$%.2f", item.getAmount()))
                .setHeader("Amount")
                .setKey("amount")
                .setSortable(true);

        incomeGrid.addComponentColumn(item -> {
                    IncomeType type = item.getIncomeType();
                    if (type == null) return new Span();

                    Icon icon = type.getIcon().create();
                    icon.setSize("16px");
                    icon.getStyle().set("paddingRight", "6px");

                    Span label = new Span(type.getDisplayName());
                    HorizontalLayout layout = new HorizontalLayout(icon, label);
                    layout.setAlignItems(FlexComponent.Alignment.CENTER);

                    return layout;
                })
                .setHeader("Income Type")
                .setComparator((a, b) -> {
                    String aType = a.getIncomeType() != null ? a.getIncomeType().getDisplayName() : "";
                    String bType = b.getIncomeType() != null ? b.getIncomeType().getDisplayName() : "";
                    return aType.compareToIgnoreCase(bType);
                })
                .setSortable(true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        incomeGrid.addColumn(item -> item.getDate() != null ? item.getDate().format(formatter) : "")
                .setHeader("Date")
                .setKey("date")
                .setSortable(true);

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
                incomeItemService.addIncomeItem(newItem);
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
