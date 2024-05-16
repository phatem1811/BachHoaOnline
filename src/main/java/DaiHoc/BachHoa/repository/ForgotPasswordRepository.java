package DaiHoc.BachHoa.repository;

import DaiHoc.BachHoa.entity.ForgotPassword;
import DaiHoc.BachHoa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
    Optional<ForgotPassword> findByUser(User user); // Thêm dòng này
}