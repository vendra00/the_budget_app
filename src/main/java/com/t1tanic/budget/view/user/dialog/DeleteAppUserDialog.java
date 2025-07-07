package com.t1tanic.budget.view.user.dialog;

import com.t1tanic.budget.model.AppUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.function.Consumer;

public class DeleteAppUserDialog extends Dialog {

    public DeleteAppUserDialog(AppUser user, Consumer<AppUser> onDelete) {
        setHeaderTitle("Confirm Deletion");
        add("Are you sure you want to delete user: " + user.getName() + "?");

        Button deleteBtn = new Button("Delete", e -> {
            onDelete.accept(user);
            close();
        });
        deleteBtn.getStyle().set("color", "red");

        Button cancelBtn = new Button("Cancel", e -> close());

        add(new HorizontalLayout(deleteBtn, cancelBtn));
    }
}
