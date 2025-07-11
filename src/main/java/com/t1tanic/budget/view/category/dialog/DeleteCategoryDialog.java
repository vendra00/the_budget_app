package com.t1tanic.budget.view.category.dialog;

import com.t1tanic.budget.model.Category;
import com.t1tanic.budget.util.I18nUtils;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.function.Consumer;

import static com.t1tanic.budget.enums.i18n.category.DeleteCategoryDialogI18N.*;

public class DeleteCategoryDialog extends Dialog {

    public DeleteCategoryDialog(Category category, Consumer<Category> onDelete, I18nUtils i18n) {
        setHeaderTitle(DIALOG_TITLE.get(i18n));

        // Body content
        Paragraph message = new Paragraph(DELETE_CONFIRMATION.get(i18n) + " " + category.getName() + "?");

        // Buttons
        Button deleteBtn = new Button(DELETE_BTN.get(i18n), e -> {
            try {
                onDelete.accept(category);
                close();
            } catch (IllegalStateException ex) {
                com.vaadin.flow.component.notification.Notification.show(
                        ex.getMessage(), 5000, com.vaadin.flow.component.notification.Notification.Position.MIDDLE
                );
            } catch (Exception ex) {
                com.vaadin.flow.component.notification.Notification.show(
                        i18n.get("unexpected.error"), 5000, com.vaadin.flow.component.notification.Notification.Position.MIDDLE
                );
            }
        });
        deleteBtn.getStyle().set("color", "red");

        Button cancelBtn = new Button(CANCEL_BTN.get(i18n), e -> close());
        cancelBtn.getStyle().set("color", "blue");

        HorizontalLayout buttonLayout = new HorizontalLayout(deleteBtn, cancelBtn);
        buttonLayout.getStyle().set("justify-content", "center");
        buttonLayout.setWidthFull();

        // Combine all in vertical layout
        VerticalLayout layout = new VerticalLayout(message, buttonLayout);
        layout.setSpacing(true);
        layout.setPadding(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(layout);
    }
}
