package com.t1tanic.budget.view.util;

import com.vaadin.flow.component.HasValue;

import java.util.Arrays;

public class ValidationExpenseUtils {

    public static boolean hasEmptyFields(HasValue<?, ?>... fields) {
        return Arrays.stream(fields)
                .anyMatch(HasValue::isEmpty);
    }

    public static void markRequired(HasValue<?, ?>... fields) {
        Arrays.stream(fields)
                .forEach(field -> field.setRequiredIndicatorVisible(true));
    }
}
