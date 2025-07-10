package com.t1tanic.budget.view.util;

import com.t1tanic.budget.model.AppUser;
import com.t1tanic.budget.model.Category;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class ComboBoxUtils {

    private static final String ICON_SIZE = "16px";
    private static final String ICON_PADDING = "6px";


    public static <T> void configureEnumComboWithIcon(ComboBox<T> comboBox,
                                                      T[] values,
                                                      Function<T, String> getDisplayName,
                                                      Function<T, VaadinIcon> getIcon) {
        List<T> sortedItems = Arrays.stream(values)
                .sorted(Comparator.comparing(getDisplayName))
                .toList();

        comboBox.setItems(sortedItems);
        comboBox.setRenderer(new ComponentRenderer<>(item -> {
            Icon icon = getIcon.apply(item).create();
            icon.setSize(ICON_SIZE);
            icon.getStyle().set("paddingRight", ICON_PADDING);

            Span label = new Span(getDisplayName.apply(item));
            HorizontalLayout layout = new HorizontalLayout(icon, label);
            layout.setAlignItems(FlexComponent.Alignment.CENTER);
            return layout;
        }));
    }

    public static void configureCategoryCombo(ComboBox<Category> comboBox, List<Category> categories) {
        comboBox.setItems(categories);
        comboBox.setItemLabelGenerator(Category::getName);
    }

    public static void configureUserCombo(ComboBox<AppUser> comboBox, List<AppUser> users) {
        comboBox.setItems(users);
        comboBox.setItemLabelGenerator(AppUser::getName);
    }
}

