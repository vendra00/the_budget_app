package com.t1tanic.budget.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        TextField name = new TextField("Your name");
        Button sayHello = new Button("Say hello",
                e -> add("Hello " + name.getValue()));
        add(name, sayHello);
    }
}
