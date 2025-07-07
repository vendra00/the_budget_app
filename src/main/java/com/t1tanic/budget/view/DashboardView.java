package com.t1tanic.budget.view;

import com.t1tanic.budget.view.user.AppUserView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.html.Div;

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

        Nav nav = new Nav(usersLink);
        nav.getStyle().set("display", "flex")
                .set("flex-direction", "column")
                .set("padding", "1rem");

        addToDrawer(nav);
    }
}
