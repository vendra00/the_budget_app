package com.t1tanic.budget.view.incomeitem.form;

import com.t1tanic.budget.enums.IncomeType;
import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.model.IncomeItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;
import java.util.function.Consumer;

public class IncomeItemForm extends VerticalLayout {

    private final TextField descriptionField = new TextField("Description");
    private final NumberField amountField = new NumberField("Amount");
    private final DatePicker dateField = new DatePicker("Date");
    private final ComboBox<IncomeType> incomeTypeCombo = new ComboBox<>("Income Type");
    private final ComboBox<Category> categoryCombo = new ComboBox<>("Category");
    private final ComboBox<AppUser> userCombo = new ComboBox<>("User");

    private final Button saveBtn = new Button("Save income");

    private IncomeItem currentIncome = null;

    public IncomeItemForm(List<Category> categories, List<AppUser> users, Consumer<IncomeItem> onSave, Runnable onCancel) {

        incomeTypeCombo.setItems(IncomeType.values());
        categoryCombo.setItems(categories);
        categoryCombo.setItemLabelGenerator(Category::getName);
        userCombo.setItems(users);
        userCombo.setItemLabelGenerator(AppUser::getEmail);

        saveBtn.addClickListener(e -> {
            if (descriptionField.isEmpty() || amountField.isEmpty() || dateField.isEmpty()
                    || incomeTypeCombo.isEmpty() || categoryCombo.isEmpty() || userCombo.isEmpty()) {
                Notification.show("All fields are required.");
                return;
            }

            if (currentIncome == null) {
                currentIncome = new IncomeItem();
            }

            currentIncome.setDescription(descriptionField.getValue());
            currentIncome.setAmount(amountField.getValue());
            currentIncome.setDate(dateField.getValue());
            currentIncome.setIncomeType(incomeTypeCombo.getValue());
            currentIncome.setCategory(categoryCombo.getValue());
            currentIncome.setUser(userCombo.getValue());

            onSave.accept(currentIncome);
            Notification.show(currentIncome.getId() == null ? "Income created" : "Income updated");
            clear();
        });

        Button cancelBtn = new Button("Cancel", e -> {
            clear();
            onCancel.run();
        });

        add(descriptionField, amountField, dateField,
                incomeTypeCombo, categoryCombo, userCombo,
                new HorizontalLayout(saveBtn, cancelBtn));
    }

    public void editIncomeItem(IncomeItem incomeItem) {
        this.currentIncome = incomeItem;
        descriptionField.setValue(incomeItem.getDescription() != null ? incomeItem.getDescription() : "");
        amountField.setValue(incomeItem.getAmount());
        dateField.setValue(incomeItem.getDate());
        incomeTypeCombo.setValue(incomeItem.getIncomeType());
        categoryCombo.setValue(incomeItem.getCategory());
        userCombo.setValue(incomeItem.getUser());
        saveBtn.setText("Update income");
    }

    public void clear() {
        currentIncome = null;
        descriptionField.clear();
        amountField.clear();
        dateField.clear();
        incomeTypeCombo.clear();
        categoryCombo.clear();
        userCombo.clear();
        saveBtn.setText("Save income");
    }
}
