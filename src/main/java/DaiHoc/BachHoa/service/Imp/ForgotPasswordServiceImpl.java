package DaiHoc.BachHoa.service.Imp;

import DaiHoc.BachHoa.entity.ForgotPassword;
import DaiHoc.BachHoa.entity.User;
import DaiHoc.BachHoa.repository.ForgotPasswordRepository;
import DaiHoc.BachHoa.repository.UserRepository;
import DaiHoc.BachHoa.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public int generateOTP() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    @Override
    public void saveOTP(String email, int otp) {
        User user = findUserByEmail(email);
        if (user != null) {
            // Tìm và xóa bản ghi OTP cũ nếu tồn tại
            ForgotPassword existingForgotPassword = forgotPasswordRepository.findByUser(user).orElse(null);
            if (existingForgotPassword != null) {
                forgotPasswordRepository.delete(existingForgotPassword);
            }

            // Tạo bản ghi OTP mới
            ForgotPassword forgotPassword = ForgotPassword.builder()
                    .otp(otp)
                    .expirationTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10 minutes expiry
                    .user(user)
                    .build();
            forgotPasswordRepository.save(forgotPassword);
        }
    }



    @Override
    public boolean verifyOTP(String email, int otp) {
        User user = findUserByEmail(email);
        if (user != null) {
            ForgotPassword forgotPassword = forgotPasswordRepository.findByOtpAndUser(otp, user).orElse(null);
            if (forgotPassword != null) {
                // Check if OTP is expired
                if (forgotPassword.getExpirationTime().after(new Date())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean resetPassword(String email, String password) {
        User user = findUserByEmail(email);
        if (user != null) {
            user.setPassword(password); // Ensure to encrypt the password in real scenarios
            userRepository.save(user);
            return true;
        }
        return false;
    }
}