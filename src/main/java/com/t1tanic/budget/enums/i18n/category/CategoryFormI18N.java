package com.t1tanic.budget.enums.i18n.category;

import com.t1tanic.budget.util.I18nUtils;
import lombok.Getter;

@Getter
public enum CategoryFormI18N {

    FIELD_NAME("category.form.field.name"),
    BUTTON_SAVE("category.form.button.save"),
    BUTTON_CANCEL("category.form.button.cancel"),
    BUTTON_UPDATE("category.form.button.update"),
    BUTTON_SAVE_DEFAULT("category.form.button.save.default"),
    VALIDATION_NAME_REQUIRED("category.form.validation.name_required"),
    NOTIFICATION_CREATED("category.form.notification.created"),
    NOTIFICATION_UPDATED("category.form.notification.updated");

    private final String key;

    CategoryFormI18N(String key) {
        this.key = key;
    }

    public String get(I18nUtils i18n) {
        return i18n.get(key);
    }
}
