package com.t1tanic.budget.view.incomeitem.dialog;

import com.t1tanic.budget.model.IncomeItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.function.Consumer;

public class DeleteIncomeItemDialog extends Dialog {

    public DeleteIncomeItemDialog(IncomeItem item, Consumer<IncomeItem> onDelete) {
        setHeaderTitle("Confirm Deletion");
        add("Are you sure you want to delete income: \"" + item.getDescription() + "\"?");

        Button deleteBtn = new Button("Delete", e -> {
            onDelete.accept(item);
            close();
        });
        deleteBtn.getStyle().set("color", "red");

        Button cancelBtn = new Button("Cancel", e -> close());

        add(new HorizontalLayout(deleteBtn, cancelBtn));
    }
}
