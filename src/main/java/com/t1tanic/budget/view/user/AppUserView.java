package com.t1tanic.budget.view.user;

import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.service.AppUserService;
import com.t1tanic.budget.view.DashboardView;
import com.t1tanic.budget.view.user.dialog.DeleteAppUserDialog;
import com.t1tanic.budget.view.user.dialog.EditAppUserDialog;
import com.t1tanic.budget.view.user.dialog.InsertAppUserDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "app-users", layout = DashboardView.class)
@PageTitle("App Users")
public class AppUserView extends VerticalLayout {

    private final AppUserService userService;
    private final Grid<AppUser> userGrid = new Grid<>(AppUser.class);

    public AppUserView(AppUserService userService) {
        this.userService = userService;

        userGrid.setColumns("id", "name", "email");

        userGrid.addComponentColumn(user -> {
            Icon editIcon = VaadinIcon.EDIT.create();
            editIcon.setColor("blue");
            editIcon.getStyle().set("cursor", "pointer");

            editIcon.addClickListener(e -> {
                EditAppUserDialog dialog = new EditAppUserDialog(user, updatedUser -> {
                    userService.updateUser(updatedUser.getId(), updatedUser);
                    refreshGrid();
                });
                dialog.open();
            });

            return editIcon;
        }).setHeader("Edit");

        userGrid.addComponentColumn(user -> {
            Icon trashIcon = VaadinIcon.TRASH.create();
            trashIcon.setColor("red");
            trashIcon.getStyle().set("cursor", "pointer");

            trashIcon.addClickListener(e -> {
                DeleteAppUserDialog dialog = new DeleteAppUserDialog(user, u -> {
                    userService.deleteUser(u.getId());
                    refreshGrid();
                });
                dialog.open();
            });

            return trashIcon;
        }).setHeader("Delete");

        Button addButton = new Button("Add User", e -> {
            InsertAppUserDialog dialog = new InsertAppUserDialog(newUser -> {
                userService.createUser(newUser);
                refreshGrid();
            });
            dialog.open();
        });

        add(new H1("User Management"), addButton, userGrid);
        refreshGrid();
    }

    private void refreshGrid() {
        userGrid.setItems(userService.getAllUsers());
    }
}
