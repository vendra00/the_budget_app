package com.t1tanic.budget.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class I18nUtils {

    private final MessageSource messageSource;

    @Autowired
    public I18nUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String key) {
        try {
            return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return "??" + key + "??";
        }
    }

    public String get(String key, Object... args) {
        return messageSource.getMessage(key, args, getLocale());
    }

    private Locale getLocale() {
        return Locale.getDefault();
    }
}

