package DaiHoc.BachHoa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DaiHoc.BachHoa.entity.ForgotPassword;
import DaiHoc.BachHoa.entity.User;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer>{
	
	@Query("select fp form ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
	Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
}
