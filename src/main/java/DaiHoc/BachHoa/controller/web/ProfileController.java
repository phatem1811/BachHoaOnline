package DaiHoc.BachHoa.controller.web;

import DaiHoc.BachHoa.entity.User;
import DaiHoc.BachHoa.service.Imp.UserService;

import org.apache.log4j.spi.LoggerFactory;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;

//	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@GetMapping("/profile")
	    public String showProfile(HttpSession session, Model model) {
	        // Kiểm tra xem người dùng đã đăng nhập chưa
	        if (session.getAttribute("user") != null) {
	            // Nếu đã đăng nhập, lấy thông tin cá nhân từ session
	            User user = (User) session.getAttribute("user");
	            
	            model.addAttribute("user", user);
	            return "/web/views/profile";
	        } else {
	            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
	            return "redirect:/login";
	        }
	        
	    }
    
//	@GetMapping("/update-profile")
//    public String showUpdateProfileForm() {
//        return "update-profile"; 
//    }
//	
//	@PostMapping("/update-profile")
//	public String updateUserProfile(HttpServletRequest request,
//	        HttpSession session,
//	        RedirectAttributes redirectAttributes) {
//	    String fullname = request.getParameter("fullname");
//	    String email = request.getParameter("email");
//	    String phone = request.getParameter("phone");
//
//	    // Kiểm tra email có được nhập vào không
//	    if (email == null || email.isEmpty()) {
//	        // Xử lý trường hợp email không hợp lệ
//	        redirectAttributes.addFlashAttribute("errorMessage", "Email is required");
//	        return "redirect:/profile"; // Hoặc có thể trả về trang profile với thông báo lỗi
//	    }
//
//	    // Lấy thông tin người dùng từ session
//	    User user = (User) session.getAttribute("user");
//
//	    // Kiểm tra xem người dùng có tồn tại không
//	    if (user == null) {
//	        // Xử lý trường hợp người dùng không tồn tại
//	        return "redirect:/error"; // Hoặc có thể trả về thông báo lỗi khác
//	    }
//
//	    // Cập nhật thông tin người dùng
//	    user.setFullname(fullname);
//	    user.setEmail(email);
//	    user.setPhone(phone);
//
//	    // Lưu thông tin người dùng đã cập nhật vào cơ sở dữ liệu
//	    userService.saveOrUpdate(user);
//
//	    return "redirect:/profile";
//	}

	@GetMapping("/update-profile")
	public String showUpdateProfileForm(Model model, HttpSession session) {
	    // Kiểm tra xem người dùng đã đăng nhập chưa
	    User user = (User) session.getAttribute("user");
	    if (user == null) {
	        // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
	        return "redirect:/login";
	    }
//	    // Log thông tin người dùng
//	    logger.info("User info: " + user.toString());

	    // In ra thông tin người dùng để kiểm tra
	    System.out.println("Session Attribute: " + session.getAttribute("user"));
	    // Nếu đã đăng nhập, truyền thông tin người dùng vào model để hiển thị trên form
	    model.addAttribute("user", user);
	    return "/web/views/update-profile";
	}

	@PostMapping("/update-profile")
	public String updateUserProfile(@ModelAttribute("user") User updatedUser, HttpSession session) {
	    // Kiểm tra xem người dùng đã đăng nhập chưa
	    User user = (User) session.getAttribute("user");
	    if (user == null) {
	        // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
	        return "redirect:/login";
	    }

	    // Kiểm tra tính hợp lệ của dữ liệu nhập vào (có thể thêm các điều kiện kiểm tra ở đây)
	    if (updatedUser.getFullname() == null || updatedUser.getEmail() == null || updatedUser.getPhone() == null) {
	        // Nếu dữ liệu không hợp lệ, có thể trả về trang cập nhật với thông báo lỗi
	        return "/web/views/update-profile";
	    }

	    // Kiểm tra xem email hoặc số điện thoại đã tồn tại trong cơ sở dữ liệu chưa
	    User existingUserByEmail = userService.findByEmail(updatedUser.getEmail());
	    User existingUserByPhone = userService.findByPhone(updatedUser.getPhone());

	    if (existingUserByEmail != null && !existingUserByEmail.getId().equals(user.getId())) {
	        // Nếu email đã tồn tại và không phải của người dùng hiện tại
	        // Thông báo lỗi và yêu cầu người dùng nhập lại
	        // Lưu ý: Cần xử lý hiển thị thông báo lỗi trong giao diện người dùng
	        return "/web/views/update-profile";
	    }

	    if (existingUserByPhone != null && !existingUserByPhone.getId().equals(user.getId())) {
	        // Nếu số điện thoại đã tồn tại và không phải của người dùng hiện tại
	        // Thông báo lỗi và yêu cầu người dùng nhập lại
	        return "/web/views/update-profile";
	    }
// Cập nhật thông tin người dùng từ updatedUser vào user
	    user.setFullname(updatedUser.getFullname());
	    user.setEmail(updatedUser.getEmail());
	    user.setPhone(updatedUser.getPhone());

	    // Tiếp tục xử lý cập nhật hồ sơ
	    userService.saveOrUpdate(user);

	    // Cập nhật thông tin người dùng trong session
	    session.setAttribute("user", user);

	    // Sau khi cập nhật thành công, chuyển hướng lại trang profile
	    return "redirect:/profile";
	}


    
    @GetMapping("/change-password")
    public String showChangePasswordForm() {
        return "/web/views/change-password"; // Trả về tên của view template để hiển thị form
    }
    
    @PostMapping("/change-password")
	public String changePassword(HttpServletRequest request,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			// Xử lý trường hợp người dùng không tồn tại
			return "redirect:/error"; 
		}
		
		if (!user.getPassword().equals(oldPassword)) {
	        redirectAttributes.addAttribute("error", "oldPasswordMismatch");
	        return "redirect:/change-password"; 
	    }

	    if (!newPassword.equals(confirmPassword)) {
	        redirectAttributes.addAttribute("error", "passwordMismatch");
	        return "redirect:/change-password"; // Chuyển hướng đến trang đổi mật khẩu với thông báo lỗi
	    }

	    user.setPassword(newPassword); // Cập nhật mật khẩu mới cho người dùng
	    userService.saveOrUpdate(user); // Lưu thay đổi vào cơ sở dữ liệu
	    redirectAttributes.addAttribute("success", "passwordChanged");
	    return "redirect:/profile";
    }
    
}