package com.t1tanic.budget.view.user.form;

import com.t1tanic.budget.model.AppUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.function.Consumer;

public class AppUserForm extends VerticalLayout {

    private final TextField nameField = new TextField("Name");
    private final EmailField emailField = new EmailField("Email");
    private final PasswordField passwordField = new PasswordField("Password");
    private final Button saveBtn = new Button("Save user");

    private AppUser currentUser = null;

    public AppUserForm(Consumer<AppUser> onSave, Runnable onCancel) {

        saveBtn.addClickListener(e -> {
            if (emailField.isEmpty() || nameField.isEmpty()) {
                Notification.show("Name and email are required.");
                return;
            }

            if (currentUser == null) {
                currentUser = new AppUser();
            }

            currentUser.setName(nameField.getValue());
            currentUser.setEmail(emailField.getValue());
            currentUser.setPassword(passwordField.getValue());

            onSave.accept(currentUser);
            Notification.show(currentUser.getId() == null ? "User created" : "User updated");
            clear();
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.addClickListener(e -> {
            clear();
            onCancel.run();
        });

        add(nameField, emailField, passwordField, new HorizontalLayout(saveBtn, cancelBtn));
    }

    public void editUser(AppUser user) {
        this.currentUser = user;
        nameField.setValue(user.getName() != null ? user.getName() : "");
        emailField.setValue(user.getEmail() != null ? user.getEmail() : "");
        passwordField.setValue(user.getPassword() != null ? user.getPassword() : "");
        saveBtn.setText("Update user");
    }

    public void clear() {
        currentUser = null;
        nameField.clear();
        emailField.clear();
        passwordField.clear();
        saveBtn.setText("Save user");
    }
}
