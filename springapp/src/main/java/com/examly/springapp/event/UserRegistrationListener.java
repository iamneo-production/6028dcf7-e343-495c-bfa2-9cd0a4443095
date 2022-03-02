package com.examly.springapp.event;

import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;

@Component
public class UserRegistrationListener implements ApplicationListener<UserRegistrationEvent>{

	@Autowired
	UserService userService;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	String fromAddress;
	
	@Override
	public void onApplicationEvent(UserRegistrationEvent event) {
		try {
			this.confrmRegistration(event);
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void confrmRegistration(UserRegistrationEvent event) 
			throws Exception {
				User user = event.getUser();
				String token = UUID.randomUUID().toString();
				userService.createVerificationToken(user, token);
				
				String toAddress = user.getEmail();
				String senderName = "Birthday EMS";
				String subject = "Please verify your registration for Birthday EMS";
				String content = "Dear [[name]],<br>"
			            + "Please click the link below to verify your registration:<br>"
			            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
			            + "Thank you,<br>"
			            + "Birthday EMS.";
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message);
				
				helper.setFrom(fromAddress,senderName);
				helper.setTo(toAddress);
				helper.setSubject(subject);
				
				content = content.replace("[[name]]", user.getUsername());
				String verifyURL = event.getAppURL()+"/user/verify-registration?token="+token;
				System.out.println(verifyURL);
				content = content.replace("[[URL]]", verifyURL);
				
				helper.setText(content, true);
				javaMailSender.send(message);
			}
}
