package com.t1tanic.budget.enums.i18n.category;

import com.t1tanic.budget.util.I18nUtils;
import lombok.Getter;

@Getter
public enum DeleteCategoryDialogI18N {

    DELETE_BTN("delete.category.dialog.btn.delete"),
    CANCEL_BTN("delete.category.dialog.btn.cancel"),
    DIALOG_TITLE("delete.category.dialog.title.header"),
    DELETE_CONFIRMATION("delete.category.dialog.confirmation");

    private final String key;

    DeleteCategoryDialogI18N(String key) {
        this.key = key;
    }

    public String get(I18nUtils i18n) {
        return i18n.get(key);
    }
}
