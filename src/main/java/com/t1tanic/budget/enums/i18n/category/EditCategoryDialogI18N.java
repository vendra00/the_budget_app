package com.t1tanic.budget.enums.i18n.category;

import com.t1tanic.budget.util.I18nUtils;
import lombok.Getter;

@Getter
public enum EditCategoryDialogI18N {

    DIALOG_TITLE("edit.category.dialog.title.header");

    private final String key;

    EditCategoryDialogI18N(String key) {
        this.key = key;
    }

    public String get(I18nUtils i18n) {
        return i18n.get(key);
    }
}
