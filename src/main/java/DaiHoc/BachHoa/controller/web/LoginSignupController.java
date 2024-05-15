package DaiHoc.BachHoa.controller.web;

import DaiHoc.BachHoa.entity.User;
import org.springframework.http.ResponseEntity;
import DaiHoc.BachHoa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/user")
public class LoginSignupController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private HttpSession httpSession;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Thực hiện đăng ký người dùng và kiểm tra kết quả
        boolean result = userService.registerUser(user);
        if (result) {
        	httpSession.setAttribute("authenticatedUser", user);
            // Nếu đăng ký thành công, trả về một đối tượng JSON với thông báo thành công
            return ResponseEntity.ok().body("{\"message\": \"Đăng ký thành công!\"}");
        } else {
            // Nếu có lỗi xảy ra, trả về một đối tượng JSON với thông báo lỗi
            return ResponseEntity.badRequest().body("{\"message\": \"Số điện thoại đã tồn tại.\"}");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        // Kiểm tra xác thực người dùng bằng số điện thoại và mật khẩu
        User authenticatedUser = userService.checkLogin(user.getPhone(), user.getPassword());
        
        if (authenticatedUser != null) {
            // Lưu thông tin người dùng vào Session Storage
            httpSession.setAttribute("authenticatedUser", authenticatedUser);
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Unauthorized access\"}");
        }
    }



}