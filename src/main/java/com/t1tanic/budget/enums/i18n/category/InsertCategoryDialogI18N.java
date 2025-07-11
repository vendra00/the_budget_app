package com.t1tanic.budget.enums.i18n.category;

import com.t1tanic.budget.util.I18nUtils;
import lombok.Getter;

@Getter
public enum InsertCategoryDialogI18N {

    ADD_NEW_CATEGORY("insert.category.dialog.add_new_category");

    private final String key;

    InsertCategoryDialogI18N(String key) {
        this.key = key;
    }

    public String get(I18nUtils i18n) {
        return i18n.get(key);
    }
}
