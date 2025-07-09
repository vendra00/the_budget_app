package com.t1tanic.budget.view;

import com.t1tanic.budget.view.category.CategoryView;
import com.t1tanic.budget.view.expenseitem.ExpenseItemView;
import com.t1tanic.budget.view.incomeitem.IncomeItemView;
import com.t1tanic.budget.view.user.AppUserView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.html.Div;

@Route("dashboard")
@PageTitle("Dashboard")
public class DashboardView extends AppLayout {

    public DashboardView() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Budget Backoffice");
        logo.getStyle().set("font-size", "var(--lumo-font-size-xl)")
                .set("margin", "0");

        Div header = new Div(logo);
        header.getStyle().set("padding", "1rem")
                .set("background-color", "#f3f4f6");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink usersLink = new RouterLink("App Users", AppUserView.class);
        usersLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink categoriesLink = new RouterLink("Categories", CategoryView.class);
        categoriesLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink expenseItemsLink = new RouterLink("Expense Items", ExpenseItemView.class);
        expenseItemsLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink incomeItemsLink = new RouterLink("Income Items", IncomeItemView.class);
        incomeItemsLink.setHighlightCondition(HighlightConditions.sameLocation());

        Nav nav = new Nav(usersLink, categoriesLink, expenseItemsLink, incomeItemsLink);
        nav.getStyle().set("display", "flex")
                .set("flex-direction", "column")
                .set("padding", "1rem");

        addToDrawer(nav);
    }
}
