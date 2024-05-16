package DaiHoc.BachHoa.service;

import DaiHoc.BachHoa.entity.User;

public interface ForgotPasswordService {
    User findUserByEmail(String email);
    int generateOTP();
    void saveOTP(String email, int otp);
    boolean verifyOTP(String email, int otp);
    boolean resetPassword(String email, String password);
}