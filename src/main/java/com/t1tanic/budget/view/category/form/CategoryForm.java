package com.t1tanic.budget.view.category.form;

import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.util.I18nUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

import java.util.function.Consumer;

import static com.t1tanic.budget.enums.i18n.category.CategoryFormI18N.*;

@Getter
public class CategoryForm extends VerticalLayout {

    private final TextField nameField = new TextField();
    private final Button saveButton = new Button();
    private final Button cancelButton = new Button();

    private Category currentCategory;

    private final I18nUtils i18n;

    public CategoryForm(Consumer<Category> onSave, Runnable onCancel, I18nUtils i18n) {
        this.i18n = i18n;

        nameField.setLabel(FIELD_NAME.get(i18n));
        saveButton.setText(BUTTON_SAVE.get(i18n));
        cancelButton.setText(BUTTON_CANCEL.get(i18n));

        saveButton.addClickListener(e -> {
            if ( nameField.isEmpty()) {
                Notification.show(VALIDATION_NAME_REQUIRED.get(i18n));
                return;
            }

            if (currentCategory == null) {
                currentCategory = new Category();
            }

            currentCategory.setName(nameField.getValue());

            onSave.accept(currentCategory);
            Notification.show(currentCategory.getId() == null ? NOTIFICATION_CREATED.get(i18n) : NOTIFICATION_UPDATED.get(i18n));
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
        saveButton.setText(BUTTON_UPDATE.get(i18n));
    }

    public void clear() {
        currentCategory = null;
        nameField.clear();
        saveButton.setText(BUTTON_SAVE_DEFAULT.get(i18n));
    }

}
