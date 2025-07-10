package com.t1tanic.budget.enums;

import com.vaadin.flow.component.icon.VaadinIcon;
import lombok.Getter;

@Getter
public enum IncomeType {
    BUSINESS("Business", VaadinIcon.BRIEFCASE),
    DIVIDENDS("Dividends", VaadinIcon.MONEY),
    FREELANCE("Freelance", VaadinIcon.CLIPBOARD_USER),
    INTEREST("Interest", VaadinIcon.TRENDING_UP),
    OTHER("Other", VaadinIcon.QUESTION_CIRCLE),
    RENTAL("Rental", VaadinIcon.HOME),
    SALARY("Salary", VaadinIcon.COIN_PILES),
    INVESTMENT("Investment", VaadinIcon.LINE_CHART);

    private final String displayName;
    private final VaadinIcon icon;

    IncomeType(String displayName, VaadinIcon icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return displayName;
    }
}