package io.ace.spring.emailapp.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class OrderManagerImpl implements OrderManager {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMessage(Order order) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("sender@gmail.com");
        simpleMailMessage.setTo("receiver@gmail.com");
        simpleMailMessage.setSubject("Order Placement");
        simpleMailMessage.setText("Your order placed dude . Name : " + order.name + " , Quantity : " + order.quantity);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendMimeMessage(Order order) {
        MimeMessagePreparator messagePreparator = (mimeMessage) -> {
            mimeMessage.setFrom(new InternetAddress("sender@gmail.com"));
            StringBuilder stringBuilder = new StringBuilder()
                    .append("Hello kailash ,you order is ")
                    .append(order.getName())
                    .append(" is placed. ")
                    .append(" Thank you for your order. ");
            mimeMessage.setText(stringBuilder.toString());
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("kailashshahi833@gmail.com"));
        };
        javaMailSender.send(messagePreparator);
    }

    @Override
    public void sendMessageWithImage(Order order) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            Resource resource = new ClassPathResource("download.jpg");
            mimeMessageHelper.addAttachment("Image.jpg", resource);

            mimeMessageHelper.setFrom("sender@gmail.com");
            mimeMessageHelper.setTo("receiver@gmail.com");
            mimeMessageHelper.setSubject("Order Placement");
            mimeMessageHelper.setText("Your order " + order.getName() + " is placed.");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Something went wrong!");
        }
    }

    @Override
    public void sendMessageWithInlineImage(Order order) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            Resource resource = new ClassPathResource("download.jpg");
            mimeMessageHelper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);
            mimeMessageHelper.addInline("identifier1234", resource);

            mimeMessageHelper.setFrom("sender@gmail.com");
            mimeMessageHelper.setTo("receiver@gmail.com");
            mimeMessageHelper.setSubject("Order Placement");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Something went wrong!");
        }
    }
}
