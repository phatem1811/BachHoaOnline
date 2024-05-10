package DaiHoc.BachHoa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.Category;
import DaiHoc.BachHoa.entity.ForgotPassword;
import DaiHoc.BachHoa.entity.User;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer>{
	
	@Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
	ForgotPassword findByOtpAndUser(Integer otp, User user);
	
}
