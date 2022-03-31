package com.examly.springapp.event;

import javax.mail.internet.MimeMessage;

import com.examly.springapp.model.Event;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class BookingEventListener implements ApplicationListener<BookingEvent> {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    UserService userService;

    @Value("${spring.mail.username}")
    String fromAddress;

    @Override
    public void onApplicationEvent(BookingEvent event) {
        try {
            sendBookingMail(event);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void sendBookingMail(BookingEvent e) throws Exception {
        Event event = e.getEvent();
        User user = e.getUser();

        String toAddress = user.getEmail();
        String senderName = "Birthday EMS";
        String subject = "Booking Confirmation " + event.getEventName();
        String content = "Dear [[name]],<br>" + "Your event: [[event]] is booked for [[date]]<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">View Booking</a></h3>" + "Thank you,<br>" + "Birthday EMS.";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String redirectURL = e.getAppURL() + "/user/viewBookedEvents";
        content = content.replace("[[URL]]", redirectURL);
        content = content.replace("[[date]]", event.getEventDate().toString());
        content = content.replace("[[event]]", event.getEventName());

        helper.setText(content, true);
        javaMailSender.send(message);
    }
}
