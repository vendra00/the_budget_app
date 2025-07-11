package com.t1tanic.budget.enums.i18n.category;

import com.t1tanic.budget.util.I18nUtils;
import lombok.Getter;

@Getter
public enum CategoryViewI18N {

    EDIT("category.view.grid.header.edit"),
    DELETE("category.view.grid.header.delete"),
    ADD_CATEGORY("category.view.button.add_category"),
    VIEW_TITLE("category.view.title");

    private final String key;

    CategoryViewI18N(String key) {
        this.key = key;
    }

    public String get(I18nUtils i18n) {
        return i18n.get(key);
    }
}
