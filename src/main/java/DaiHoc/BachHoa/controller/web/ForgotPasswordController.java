package DaiHoc.BachHoa.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import DaiHoc.BachHoa.service.ForgotPasswordService;
import DaiHoc.BachHoa.service.EmailService;
import DaiHoc.BachHoa.service.Imp.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ForgotPasswordController {

    private final UserService userService;
    private final ForgotPasswordService forgotPasswordService;
    private final EmailService emailService;

    @Autowired
    public ForgotPasswordController(UserService userService, ForgotPasswordService forgotPasswordService, EmailService emailService) {
        this.userService = userService;
        this.forgotPasswordService = forgotPasswordService;
        this.emailService = emailService;
    }

    
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "/web/views/forgot-password"; // Trả về view với đường dẫn tuyệt đối
    }

//    @PostMapping("/forgot-password")
//    public String sendResetEmail(@RequestParam("email") String email, Model model) {
//        // Kiểm tra xem email có tồn tại trong cơ sở dữ liệu hay không
//        if (userService.isEmailExists(email)) {
//            // Tạo và gửi OTP
//            int otp = forgotPasswordService.generateOTP();
//            forgotPasswordService.saveOTP(email, otp);
//            emailService.sendOTP(email, otp);
//
//            // Chuyển hướng đến trang xác thực OTP
//            return "redirect:/verify-otp";
//        } else {
//            // Thông báo cho người dùng rằng email chưa được đăng ký
//            model.addAttribute("error", "Email chưa được đăng ký");
//            return "/web/views/forgot-password"; // Trả về trang forgot-password.html với thông báo lỗi
//        }
//    }
    @PostMapping("/forgot-password")
    public String sendResetEmail(@RequestParam("email") String email, Model model, HttpServletRequest request) {
        if (userService.isEmailExists(email)) {
            int otp = forgotPasswordService.generateOTP();
            forgotPasswordService.saveOTP(email, otp);
            emailService.sendOTP(email, otp);

            // Lưu email vào session
            HttpSession session = request.getSession();
            session.setAttribute("email", email);

            return "redirect:/verify-otp";
        } else {
            model.addAttribute("error", "Email chưa được đăng ký");
            return "/web/views/forgot-password";
        }
    }


    @GetMapping("/verify-otp")
    public String showVerifyOTPForm() {
        return "/web/views/verify-otp"; // Trả về view với đường dẫn tuyệt đối
    }//    @PostMapping("/verify-otp")
//  public String verifyOTP(@RequestParam("email") String email, @RequestParam("otp") int otp, Model model) {
//  if (forgotPasswordService.verifyOTP(email, otp)) {
//      model.addAttribute("email", email);
//      return "/web/views/reset-password"; // Chuyển hướng đến trang đặt lại mật khẩu
//  } else {
//      model.addAttribute("error", "OTP verification failed");
//      return "/web/views/verify-otp";
//  }
//}
@PostMapping("/verify-otp")
public String verifyOTP(@RequestParam("otp") int otp, Model model, HttpServletRequest request) {
  HttpSession session = request.getSession(false);
  String email = (session != null) ? (String) session.getAttribute("email") : null;

  if (email == null) {
      model.addAttribute("error", "Session expired or invalid. Please start the process again.");
      return "/web/views/forgot-password";
  }

  if (forgotPasswordService.verifyOTP(email, otp)) {
      model.addAttribute("email", email);
      return "/web/views/reset-password";
  } else {
      model.addAttribute("error", "OTP verification failed");
      return "/web/views/verify-otp";
  }
}


//@PostMapping("/reset-password")
//public String resetPassword(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
//  if (forgotPasswordService.resetPassword(email, password)) {
//      return "/web/views/login"; // Chuyển hướng đến trang đăng nhập
//  } else {
//      model.addAttribute("error", "Password reset failed");
//      return "/web/views/reset-password";
//  }
//}
@PostMapping("/reset-password")
public String resetPassword(@RequestParam("password") String password, Model model, HttpServletRequest request) {
  HttpSession session = request.getSession(false);
  String email = (session != null) ? (String) session.getAttribute("email") : null;

  if (email == null) {
      model.addAttribute("error", "Session expired or invalid. Please start the process again.");
      return "/web/views/forgot-password";
  }

  if (forgotPasswordService.resetPassword(email, password)) {
      // Xóa email khỏi session sau khi thành công
      session.removeAttribute("email");
      return "/web/views/login";
  } else {
      model.addAttribute("error", "Password reset failed");
      return "/web/views/reset-password";
  }
}

}