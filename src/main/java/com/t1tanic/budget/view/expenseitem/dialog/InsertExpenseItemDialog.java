package com.t1tanic.budget.view.expenseitem.dialog;

import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.model.ExpenseItem;
import com.t1tanic.budget.view.expenseitem.form.ExpenseItemForm;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;

import java.util.List;
import java.util.function.Consumer;

public class InsertExpenseItemDialog extends Dialog {

    public InsertExpenseItemDialog(List<Category> categories, List<AppUser> users, Consumer<ExpenseItem> onSave) {
        ExpenseItemForm form = new ExpenseItemForm(categories, users, expenseItem -> {
            onSave.accept(expenseItem);
            close();
        }, this::close);

        add(new H3("Add New Expense"), form);
    }
}
