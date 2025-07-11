package com.t1tanic.budget.view.category.dialog;

import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.util.I18nUtils;
import com.t1tanic.budget.view.category.form.CategoryForm;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;

import java.util.function.Consumer;

import static com.t1tanic.budget.enums.i18n.category.EditCategoryDialogI18N.DIALOG_TITLE;

public class EditCategoryDialog extends Dialog {

    public EditCategoryDialog(Category category, Consumer<Category> onSave, I18nUtils i18n) {
        CategoryForm form = new CategoryForm(updatedCategory -> {
            onSave.accept(updatedCategory);
            close();
        }, this::close, i18n);

        form.editCategory(category);

        add(new H3(DIALOG_TITLE.get(i18n)), form);
    }
}
