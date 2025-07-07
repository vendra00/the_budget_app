package com.t1tanic.budget.view.user.dialog;

import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.view.user.form.AppUserForm;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;

import java.util.function.Consumer;

public class InsertAppUserDialog extends Dialog {
    public InsertAppUserDialog(Consumer<AppUser> onSave) {
        AppUserForm form = new AppUserForm(user -> {
            onSave.accept(user);
            close();
        }, this::close);

        add(new H3("Add New User"), form);
    }
}

