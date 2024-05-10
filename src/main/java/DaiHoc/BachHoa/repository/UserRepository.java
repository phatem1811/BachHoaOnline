package DaiHoc.BachHoa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.User;
import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT c FROM User c WHERE c.phone LIKE %?1%")
	List<User> searchUser(String keyword);

	@Query("SELECT c FROM User c WHERE c.email = ?1")
	Optional<User> findByEmail(String username);
	
	@Transactional
	@Modifying
	@Query("update User u set u.password = ?2 where u.email = ?1")
	void updatePassword(String email, String password);
}
