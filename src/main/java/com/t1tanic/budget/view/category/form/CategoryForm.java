package com.t1tanic.budget.view.category.form;

import com.t1tanic.budget.model.Category;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class CategoryForm extends VerticalLayout {

    private final TextField nameField = new TextField("Name");
    private final Button saveButton = new Button("Save");
    private final Button cancelButton = new Button("Cancel");

    private Category currentCategory;

    public CategoryForm(Consumer<Category> onSave, Runnable onCancel) {
        saveButton.addClickListener(e -> {
            if ( nameField.isEmpty()) {
                Notification.show("Category name is required.");
                return;
            }

            if (currentCategory == null) {
                currentCategory = new Category();
            }

            currentCategory.setName(nameField.getValue());

            onSave.accept(currentCategory);
            Notification.show(currentCategory.getId() == null ? "Category created" : "Category updated");
            clear();
        });

        cancelButton.addClickListener(e -> {
            clear();
            onCancel.run();
        });

        add(nameField, new HorizontalLayout(saveButton, cancelButton));
    }

    public void editCategory(Category category) {
        this.currentCategory = category;
        nameField.setValue(category.getName() != null ? category.getName() : "");
        saveButton.setText("Update category");
    }

    public void clear() {
        currentCategory = null;
        nameField.clear();
        saveButton.setText("Save category");
    }

}
