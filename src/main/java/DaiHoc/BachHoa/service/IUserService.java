package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import DaiHoc.BachHoa.entity.User;

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
}
