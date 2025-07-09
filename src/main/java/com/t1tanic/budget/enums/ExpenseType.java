package com.t1tanic.budget.enums;


import com.vaadin.flow.component.icon.VaadinIcon;
import lombok.Getter;

@Getter
public enum ExpenseType {
    EDUCATION("Education", VaadinIcon.BOOK),
    ENTERTAINMENT("Entertainment", VaadinIcon.FILM),
    FOOD("Food", VaadinIcon.CUTLERY),
    HEALTHCARE("Healthcare", VaadinIcon.HEART),
    OTHER("Other", VaadinIcon.QUESTION_CIRCLE),
    SHOPPING("Shopping", VaadinIcon.CART),
    TRANSPORT("Transport", VaadinIcon.CAR),
    UTILITIES("Utilities", VaadinIcon.FLASH);

    private final String displayName;
    private final VaadinIcon icon;

    ExpenseType(String displayName, VaadinIcon icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
