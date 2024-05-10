package DaiHoc.BachHoa.controller.web;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DaiHoc.BachHoa.entity.ForgotPassword;
import DaiHoc.BachHoa.entity.MailBoby;
import DaiHoc.BachHoa.entity.User;
import DaiHoc.BachHoa.repository.ForgotPasswordRepository;
import DaiHoc.BachHoa.repository.UserRepository;
import DaiHoc.BachHoa.service.IEmailService;
import DaiHoc.BachHoa.utils.ChangePassword;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {
	
	private final UserRepository userRepository;
	private final IEmailService emailService;
	private final ForgotPasswordRepository fpRepository;
	
	public ForgotPasswordController(UserRepository userRepository, IEmailService emailService, ForgotPasswordRepository fpRepository) {
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.fpRepository = fpRepository;
	}
	
	//send mail for verification
	@PostMapping("/verifyMail/{email}")
	public ResponseEntity<String> verifyEmail(@PathVariable String email){
		User user = userRepository.findByEmail(email).orElseThrow( () -> new RuntimeException("Please provide a valid email"));
		int otp = OtpGenerator();
		MailBoby mailBody = MailBoby.builder().to(email).text("This is the otp for verification: " + otp).subject("OTP for forgot password request").build();
		
		ForgotPassword fp = ForgotPassword.builder().otp(otp).expirationTime(new Date(System.currentTimeMillis()+70*1000)).user(user).build();
		emailService.sendSimpleMessage(mailBody);
		fpRepository.save(fp);
		
		return ResponseEntity.ok("Email sent for verification");
	}
	@PostMapping("/verifyOtp/{otp}/{email}")
	public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email){
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Please provide a valid email"));
		
		ForgotPassword fp = fpRepository.findByOtpAndUser(otp, user).orElseThrow(() -> new RuntimeException("Invalid OTP for email: " + email));
		
		if(fp.getExpirationTime().before(Date.from(Instant.now())))
		{
			fpRepository.deleteById(fp.getFpID());
			return new ResponseEntity<>("OTP has expired",HttpStatus.EXPECTATION_FAILED);
		}
		
		return ResponseEntity.ok("OTP verified");
	}
	
	@PostMapping("/changePassword/{email}")
	public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword, @PathVariable String email){
		if(!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
			return new ResponseEntity<>("Please enter password again", HttpStatus.EXPECTATION_FAILED);
		}
		userRepository.updatePassword(email, changePassword.password());
		return ResponseEntity.ok("Password has been changed!");
	}
	
	private Integer OtpGenerator() {
		Random r = new Random();
		return r.nextInt(100_000,999_999);
	}
}
