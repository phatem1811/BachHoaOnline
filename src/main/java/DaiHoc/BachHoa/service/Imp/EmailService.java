package DaiHoc.BachHoa.service.Imp;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.dto.MailBody;

@Service
public class EmailService {
	private final JavaMailSender javaMailsender;
	
	public EmailService(JavaMailSender javamailsender) {
		this.javaMailsender = javamailsender;
	}
	
	public void sendSimpleMessage(MailBody mailBody) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailBody.to());
		message.setFrom("namtrung.work@gmail.com");
		message.setSubject(mailBody.subject());
		message.setText(mailBody.text());
		
		javaMailsender.send(message);
	}
}
