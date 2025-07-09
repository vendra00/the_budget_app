package com.t1tanic.budget.view.category.dialog;

import com.t1tanic.budget.model.Category;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.function.Consumer;

public class DeleteCategoryDialog extends Dialog {

    public DeleteCategoryDialog(Category category, Consumer<Category> onDelete) {
        setHeaderTitle("Confirm Deletion");
        add("Are you sure you want to delete this category: " + category.getName() + "?");

        Button deleteBtn = new Button("Delete", e -> {
            onDelete.accept(category);
            close();
        });
        deleteBtn.getStyle().set("color", "red");

        Button cancelBtn = new Button("Cancel", e -> close());

        add(new HorizontalLayout(deleteBtn, cancelBtn));
    }
}
