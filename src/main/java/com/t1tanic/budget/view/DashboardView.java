package com.t1tanic.budget.view;

import com.t1tanic.budget.util.I18nUtils;
import com.t1tanic.budget.view.category.CategoryView;
import com.t1tanic.budget.view.expenseitem.ExpenseItemView;
import com.t1tanic.budget.view.incomeitem.IncomeItemView;
import com.t1tanic.budget.view.user.AppUserView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.router.*;
import com.vaadin.flow.component.html.Div;

import static com.t1tanic.budget.enums.i18n.DashboardI18N.*;

@Route("dashboard")
public class DashboardView extends AppLayout implements BeforeEnterObserver {

    private final I18nUtils i18n;

    public DashboardView(I18nUtils i18n) {
        this.i18n = i18n;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1(VIEW_TITLE.get(i18n));
        logo.getStyle().set("font-size", "var(--lumo-font-size-xl)").set("margin", "0");

        Div header = new Div(logo);
        header.getStyle().set("padding", "1rem").set("background-color", "#f3f4f6");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink usersLink = new RouterLink(ROUTER_LINK_APP_USER.get(i18n), AppUserView.class);
        usersLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink categoriesLink = new RouterLink(ROUTER_LINK_CATEGORY.get(i18n), CategoryView.class);
        categoriesLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink expenseItemsLink = new RouterLink(ROUTER_LINK_EXPENSE_ITEMS.get(i18n), ExpenseItemView.class);
        expenseItemsLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink incomeItemsLink = new RouterLink(ROUTER_LINK_INCOME_ITEMS.get(i18n), IncomeItemView.class);
        incomeItemsLink.setHighlightCondition(HighlightConditions.sameLocation());

        Nav nav = new Nav(usersLink, categoriesLink, expenseItemsLink, incomeItemsLink);
        nav.getStyle().set("display", "flex").set("flex-direction", "column").set("padding", "1rem");

        addToDrawer(nav);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        getElement().executeJs("document.title = $0", VIEW_TITLE.get(i18n));
    }
}
