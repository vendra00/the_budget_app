package com.t1tanic.budget.view.expenseitem;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Expense Items")
@Route(value = "expenses", layout = com.t1tanic.budget.view.DashboardView.class)
public class ExpenseItemView extends VerticalLayout {
}
