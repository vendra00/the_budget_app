package com.t1tanic.budget.view.expenseitem.form;

import com.t1tanic.budget.enums.ExpenseType;
import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.model.ExpenseItem;
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

public class ExpenseItemForm extends VerticalLayout {

    private final TextField descriptionField = new TextField("Description");
    private final NumberField amountField = new NumberField("Amount");
    private final DatePicker dateField = new DatePicker("Date");
    private final ComboBox<ExpenseType> expenseTypeCombo = new ComboBox<>("Expense Type");
    private final ComboBox<Category> categoryCombo = new ComboBox<>("Category");
    private final ComboBox<AppUser> userCombo = new ComboBox<>("User");

    private final Button saveBtn = new Button("Save expense");

    private ExpenseItem currentExpense = null;

    public ExpenseItemForm(List<Category> categories, List<AppUser> users, Consumer<ExpenseItem> onSave, Runnable onCancel) {

        expenseTypeCombo.setItems(ExpenseType.values());
        categoryCombo.setItems(categories);
        categoryCombo.setItemLabelGenerator(Category::getName);
        userCombo.setItems(users);
        userCombo.setItemLabelGenerator(AppUser::getEmail);

        saveBtn.addClickListener(e -> {
            if (descriptionField.isEmpty() || amountField.isEmpty() || dateField.isEmpty()
                    || expenseTypeCombo.isEmpty() || categoryCombo.isEmpty() || userCombo.isEmpty()) {
                Notification.show("All fields are required.");
                return;
            }

            if (currentExpense == null) {
                currentExpense = new ExpenseItem();
            }

            currentExpense.setDescription(descriptionField.getValue());
            currentExpense.setAmount(amountField.getValue());
            currentExpense.setDate(dateField.getValue());
            currentExpense.setExpenseType(expenseTypeCombo.getValue());
            currentExpense.setCategory(categoryCombo.getValue());
            currentExpense.setUser(userCombo.getValue());

            onSave.accept(currentExpense);
            Notification.show(currentExpense.getId() == null ? "Expense created" : "Expense updated");
            clear();
        });

        Button cancelBtn = new Button("Cancel", e -> {
            clear();
            onCancel.run();
        });

        add(descriptionField, amountField, dateField,
                expenseTypeCombo, categoryCombo, userCombo,
                new HorizontalLayout(saveBtn, cancelBtn));
    }

    public void editExpenseItem(ExpenseItem expenseItem) {
        this.currentExpense = expenseItem;
        descriptionField.setValue(expenseItem.getDescription() != null ? expenseItem.getDescription() : "");
        amountField.setValue(expenseItem.getAmount());
        dateField.setValue(expenseItem.getDate());
        expenseTypeCombo.setValue(expenseItem.getExpenseType());
        categoryCombo.setValue(expenseItem.getCategory());
        userCombo.setValue(expenseItem.getUser());
        saveBtn.setText("Update expense");
    }

    public void clear() {
        currentExpense = null;
        descriptionField.clear();
        amountField.clear();
        dateField.clear();
        expenseTypeCombo.clear();
        categoryCombo.clear();
        userCombo.clear();
        saveBtn.setText("Save expense");
    }
}
