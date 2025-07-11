package com.t1tanic.budget.enums.i18n;

import com.t1tanic.budget.util.I18nUtils;
import lombok.Getter;

@Getter
public enum DashboardI18N {
    VIEW_TITLE("dashboard.view.title"),
    ROUTER_LINK_APP_USER("dashboard.view.router_link.app_user"),
    ROUTER_LINK_CATEGORY("dashboard.view.router_link.category"),
    ROUTER_LINK_EXPENSE_ITEMS("dashboard.view.router_link.expense_items"),
    ROUTER_LINK_INCOME_ITEMS("dashboard.view.router_link.income_items");

    private final String key;

    DashboardI18N(String key) {
        this.key = key;
    }

    public String get(I18nUtils i18n) {
        return i18n.get(key);
    }
}
