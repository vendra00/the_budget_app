package com.t1tanic.budget.model;

import com.t1tanic.budget.enums.IncomeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("INCOME")
@Getter
@Setter
public class IncomeItem extends BudgetItem {

    @Enumerated(EnumType.STRING)
    private IncomeType incomeType;
}
