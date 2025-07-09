package com.t1tanic.budget.view.expenseitem.dialog;

import com.t1tanic.budget.model.ExpenseItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.function.Consumer;

public class DeleteExpenseItemDialog extends Dialog {
    public DeleteExpenseItemDialog(ExpenseItem item, Consumer<ExpenseItem> onDelete) {
        setHeaderTitle("Confirm Deletion");
        add("Are you sure you want to delete expense: \"" + item.getDescription() + "\"?");

        Button deleteBtn = new Button("Delete", e -> {
            onDelete.accept(item);
            close();
        });
        deleteBtn.getStyle().set("color", "red");

        Button cancelBtn = new Button("Cancel", e -> close());

        add(new HorizontalLayout(deleteBtn, cancelBtn));
    }
}
