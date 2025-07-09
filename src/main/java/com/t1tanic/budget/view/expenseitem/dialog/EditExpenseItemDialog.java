package com.t1tanic.budget.view.expenseitem.dialog;

import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.model.ExpenseItem;
import com.t1tanic.budget.view.expenseitem.form.ExpenseItemForm;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;

import java.util.List;
import java.util.function.Consumer;

public class EditExpenseItemDialog extends Dialog {
    public EditExpenseItemDialog(ExpenseItem expenseItem,
                                 List<Category> categories,
                                 List<AppUser> users,
                                 Consumer<ExpenseItem> onSave)
    {
        ExpenseItemForm form = new ExpenseItemForm(categories, users, updatedExpenseItem -> {
            updatedExpenseItem.setId(expenseItem.getId());
            onSave.accept(updatedExpenseItem);
            close();
        }, this::close);

        form.editExpenseItem(expenseItem);

        add(new H3("Edit Expense Item"), form);

    }
}
