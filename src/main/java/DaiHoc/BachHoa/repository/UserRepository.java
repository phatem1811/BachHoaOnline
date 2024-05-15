package DaiHoc.BachHoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT c FROM User c WHERE c.phone LIKE %?1%")
	List<User> searchUser(String keyword);
	User findByPhone(String phone);
    User findByEmail(String email);
}
