package com.t1tanic.budget.view.incomeitem.dialog;

import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.model.IncomeItem;
import com.t1tanic.budget.view.incomeitem.form.IncomeItemForm;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;

import java.util.List;
import java.util.function.Consumer;

public class EditIncomeItemDialog extends Dialog {

    public EditIncomeItemDialog(
            IncomeItem incomeItem,
            List<Category> categories,
            List<AppUser> users,
            Consumer<IncomeItem> onSave
    ) {
        IncomeItemForm form = new IncomeItemForm(categories, users, updatedIncomeItem -> {
            updatedIncomeItem.setId(incomeItem.getId());
            onSave.accept(updatedIncomeItem);
            close();
        }, this::close);

        form.editIncomeItem(incomeItem);

        add(new H3("Edit Income Item"), form);
    }
}
