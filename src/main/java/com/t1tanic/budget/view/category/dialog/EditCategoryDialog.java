package com.t1tanic.budget.view.category.dialog;

import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.view.category.form.CategoryForm;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;

import java.util.function.Consumer;

public class EditCategoryDialog extends Dialog {

    public EditCategoryDialog(Category category, Consumer<Category> onSave) {
        CategoryForm form = new CategoryForm(updatedCategory -> {
            onSave.accept(updatedCategory);
            close();
        }, this::close);

        form.editCategory(category);

        add(new H3("Edit Category"), form);
    }
}
