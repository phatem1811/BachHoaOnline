package DaiHoc.BachHoa.service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.entity.MailBoby;

@Service
public class IEmailService {
	private final JavaMailSender javaMailsender;
	
	public IEmailService(JavaMailSender javamailsender) {
		this.javaMailsender = javamailsender;
	}
	
	public void sendSimpleMessage(MailBoby mailBody) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailBody.to());
		message.setFrom("namtrung.work@gmail.com");
		message.setSubject(mailBody.subject());
		message.setText(mailBody.text());
		
		javaMailsender.send(message);
	}
}
