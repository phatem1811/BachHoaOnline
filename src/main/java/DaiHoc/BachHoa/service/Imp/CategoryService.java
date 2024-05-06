package DaiHoc.BachHoa.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.entity.Category;
import DaiHoc.BachHoa.repository.CategoryRepository;
import DaiHoc.BachHoa.service.ICategoryService;
@Service
public class CategoryService implements ICategoryService
{
	@Autowired
	private CategoryRepository repo;
	@Override
	public List<Category> getAll() {
		return repo.findAll();
	}
	@Override
	public Boolean save(Category category) {
		// TODO Auto-generated method stub
		try {
			repo.save(category);
			return true;
		}
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 return false;
	}
	@Override
	public Category findById(Long id) {	
		return repo.findById(id).get();
	}

	@Override
	public Boolean update(Category category) {
		try {
			Optional<Category> opt = Optional.of(findById(category.getId()));
			if (opt.isPresent()) {
				repo.save(category);
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
	public List<Category> searchCategory(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchCategory(keyword);
	}
	@Override
	public Page<Category> getAll(Integer pageNo) {
		Pageable pageable =PageRequest.of(pageNo-1, 10);
		return repo.findAll(pageable);
	}
	@Override
	public Page<Category> searchCategory(String keyword, Integer pageNo) {
		List list = this.searchCategory(keyword);
		Pageable pageable =PageRequest.of(pageNo-1, 10);
		Integer start= (int) pageable.getOffset();
		Integer end = (int)((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() :  pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);
		
		return new PageImpl<Category>(list, pageable,  this.searchCategory(keyword).size());
	}

}
