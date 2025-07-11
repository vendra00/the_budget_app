package com.t1tanic.budget.view.category.dialog;

import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.util.I18nUtils;
import com.t1tanic.budget.view.category.form.CategoryForm;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;

import java.util.function.Consumer;

import static com.t1tanic.budget.enums.i18n.category.InsertCategoryDialogI18N.ADD_NEW_CATEGORY;

public class InsertCategoryDialog extends Dialog {

    public InsertCategoryDialog(Consumer<Category> onSave, I18nUtils i18n) {
        CategoryForm form = new CategoryForm(category -> {
            onSave.accept(category);
            close();
        }, this::close, i18n);

        add(new H3(ADD_NEW_CATEGORY.get(i18n)), form);
    }
}
