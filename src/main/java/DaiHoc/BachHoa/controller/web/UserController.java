package DaiHoc.BachHoa.controller.web;

import DaiHoc.BachHoa.entity.User;
import DaiHoc.BachHoa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "/web/views/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
    	// Kiểm tra tính duy nhất của email hoặc số điện thoại
        if (userService.isEmailExists(user.getEmail())) {
            model.addAttribute("error", "Email đã tồn tại!");
            return "/web/views/register";
        }

        if (userService.existsByPhone(user.getPhone())) {
            model.addAttribute("error", "Số điện thoại đã tồn tại!");
            return "/web/views/register";
        }

        // Lưu người dùng mới vào cơ sở dữ liệu
        userService.save(user);

        // Chuyển hướng người dùng hoặc gửi thông báo xác nhận đăng ký thành công
        return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
    }


    @GetMapping("/login")
    public String login() {
        return "/web/views/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone,
                        @RequestParam("password") String password,
                        Model model,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        User user = userService.checkLogin(phone, password);
        if (user != null) {
            // Kiểm tra vai trò của người dùng và chuyển hướng đến trang tương ứng
            if (user.getRole() == 1) {
                // Vai trò là admin, chuyển hướng đến trang admin
                session.setAttribute("user", user);
                return "/admin/admin";
            } else {
                // Vai trò là người dùng, chuyển hướng đến trang home
                session.setAttribute("user", user);
                model.addAttribute("user", user);
                //return "redirect:/home";
                //return "redirect:/profile";
             // Chuyển hướng người dùng tới trang lịch sử giao dịch sau khi đăng nhập thành công
                return "redirect:/home";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid phone number or password.");
            
            return "redirect:/login";
        }
    }
    
//    @GetMapping("/admin")
//    public String adminPage(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        if (user != null && user.getRole() == 1) {
//            return "admin"; 
//        } else {
//           
//            return "redirect:/login";
//        }
//    }

//    @GetMapping("/home")
//    public String homePage(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        if (user != null) {
//            return "home"; 
//        } else {
//            // Nếu không có người dùng đăng nhập, chuyển hướng đến trang login
//            return "redirect:/login";
//        }
//    }
//   
    
//    @GetMapping("/profile")
//    public String profilePage(HttpSession session, Model model) {
//        // Kiểm tra xem người dùng đã đăng nhập chưa
//        if (session.getAttribute("user") != null) {
//            // Nếu đã đăng nhập, lấy thông tin cá nhân từ session
//            User user = (User) session.getAttribute("user");
//            model.addAttribute("user", user);
//            return "profile";
//        } else {
//            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
//            return "redirect:/login";
//        } 
//    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        model.addAttribute("user", null);
        return "redirect:/home";
    }
}