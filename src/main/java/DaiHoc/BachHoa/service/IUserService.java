package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import DaiHoc.BachHoa.entity.User;
import java.util.List;
@Component
public interface IUserService {
	List<User> getAll();

	Boolean save(User user);
	Boolean update(User user);
	Boolean delete(Long Id);
	User findById(Long id);
	
	List<User> searchUser(String keyword);
	Page<User> getAll(Integer pageNo);
	Page<User> searchUser(String keyword, Integer pageNo);
	
	User checkLogin(String phone, String password);

	User findByPhone(String phone);

	User findByEmail(String email);

	boolean existsByPhone(String phone);

	boolean isEmailExists(String email);
	
	void saveOrUpdate(User user);
	
	
    boolean registerUser(User user);
    User loginUser(String phone, String password);
    void logoutUser();
    User getUserByPhone(String phone);

}
