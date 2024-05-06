package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import DaiHoc.BachHoa.entity.Category;
@Component
public interface ICategoryService {
	List<Category> getAll();
	Boolean save(Category category);
	Boolean update(Category category);
	Boolean delete(Long Id);
	Category findById(Long id);
	List<Category> searchCategory(String keyword);
	Page<Category> getAll(Integer pageNo);
	Page<Category> searchCategory(String keyword, Integer pageNo);
}
