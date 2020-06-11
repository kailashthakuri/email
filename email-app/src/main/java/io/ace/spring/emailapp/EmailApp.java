package io.ace.spring.emailapp;

import io.ace.spring.emailapp.config.ApplicationConfiguration;
import io.ace.spring.emailapp.order.Order;
import io.ace.spring.emailapp.order.OrderManager;
import io.ace.spring.emailapp.order.OrderManagerImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class EmailApp {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        OrderManager manager = context.getBean(OrderManagerImpl.class);
        manager.sendMessageWithInlineImage(new Order("Food", 12));
        context.close();
    }
}
