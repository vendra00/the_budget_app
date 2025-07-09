package com.t1tanic.budget.view.incomeitem.dialog;

import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.model.IncomeItem;
import com.t1tanic.budget.view.incomeitem.form.IncomeItemForm;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;

import java.util.List;
import java.util.function.Consumer;

public class InsertIncomeItemDialog extends Dialog {

    public InsertIncomeItemDialog(List<Category> categories, List<AppUser> users, Consumer<IncomeItem> onSave) {
        IncomeItemForm form = new IncomeItemForm(categories, users, incomeItem -> {
            onSave.accept(incomeItem);
            close();
        }, this::close);

        add(new H3("Add New Income"), form);
    }
}

