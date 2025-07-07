package com.t1tanic.budget.view;

import com.t1tanic.budget.model.AppUser;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;

import java.util.function.Consumer;

public class EditAppUserDialog extends Dialog {
    public EditAppUserDialog(AppUser user, Consumer<AppUser> onSave) {
        AppUserForm form = new AppUserForm(updatedUser -> {
            onSave.accept(updatedUser);
            close();
        }, this::close);

        form.editUser(user);

        add(new H3("Edit User"), form);
    }
}

