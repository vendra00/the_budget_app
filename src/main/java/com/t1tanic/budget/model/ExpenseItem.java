package com.t1tanic.budget.model;

import com.t1tanic.budget.enums.ExpenseType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("EXPENSE")
@Getter
@Setter
public class ExpenseItem extends BudgetItem {

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
}
