package DaiHoc.BachHoa.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.entity.User;
import DaiHoc.BachHoa.repository.UserRepository;
import DaiHoc.BachHoa.service.IUserService;
@Service
public class UserService implements IUserService
{
	@Autowired
	private UserRepository repo;
	@Override
	public List<User> getAll() {
		return repo.findAll();
	}
	@Override
	public Boolean save(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean update(User user) {
		try {
			Optional<User> opt = Optional.of(findById(user.getId()));
			if (opt.isPresent()) {
				repo.save(user);
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Boolean delete(Long Id) {
		try {
			repo.delete(findById(Id));
			return true;
		}
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 return false;
	}
	@Override
	public User findById(Long id) {	
		return repo.findById(id).get();
	}
	
	@Override
	public List<User> searchUser(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchUser(keyword);
	}
	@Override
	public Page<User> getAll(Integer pageNo) {
		Pageable pageable =PageRequest.of(pageNo-1, 10);
		return repo.findAll(pageable);
	}
	@Override
	public Page<User> searchUser(String keyword, Integer pageNo) {
		List list = this.searchUser(keyword);
		Pageable pageable =PageRequest.of(pageNo-1, 10);
		Integer start= (int) pageable.getOffset();
		Integer end = (int)((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() :  pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);
		
		return new PageImpl<User>(list, pageable,  this.searchUser(keyword).size());
	}
}
