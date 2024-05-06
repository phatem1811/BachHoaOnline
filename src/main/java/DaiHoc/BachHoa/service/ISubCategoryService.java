package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import DaiHoc.BachHoa.entity.Category;
import DaiHoc.BachHoa.entity.SubCategory;

@Component
public interface ISubCategoryService {
	List<SubCategory> getAll();
	Boolean save(SubCategory subCategory);
	Boolean update(SubCategory subCategory);
	Boolean delete(Long Id);
	SubCategory findById(Long id);
	List<SubCategory> searchSubCategory(String keyword);
	Page<SubCategory> getAll(Integer pageNo);
	Page<SubCategory> searchSubCategory(String keyword, Integer pageNo);
}
