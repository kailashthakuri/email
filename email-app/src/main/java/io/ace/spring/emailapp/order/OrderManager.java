package io.ace.spring.emailapp.order;

public interface OrderManager {
    public void sendSimpleMessage(Order order);

    public void sendMimeMessage(Order order);

    public void sendMessageWithImage(Order order);

    public void sendMessageWithInlineImage(Order order);
}
