package DaiHoc.BachHoa.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.entity.SubCategory;
import DaiHoc.BachHoa.repository.SubCategoryRepository;
import DaiHoc.BachHoa.service.ISubCategoryService;
@Service
public class SubCategoryService implements ISubCategoryService
{
	@Autowired
	private SubCategoryRepository repo;
	@Override
	public List<SubCategory> getAll() {
		return repo.findAll();
	}
	@Override
	public Boolean save(SubCategory subCategory) {
		// TODO Auto-generated method stub
		try {
			repo.save(subCategory);
			return true;
		}
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 return false;
	}
	@Override
	public SubCategory findById(Long id) {	
		return repo.findById(id).get();
	}

	@Override
	public Boolean update(SubCategory subCategory) {
		try {
			Optional<SubCategory> opt = Optional.of(findById(subCategory.getId()));
			if (opt.isPresent()) {
				repo.save(subCategory);
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
	public List<SubCategory> searchSubCategory(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchSubCategory(keyword);
	}
	@Override
	public Page<SubCategory> getAll(Integer pageNo) {
		Pageable pageable =PageRequest.of(pageNo-1, 10);
		return repo.findAll(pageable);
	}
	@Override
	public Page<SubCategory> searchSubCategory(String keyword, Integer pageNo) {
		List list = this.searchSubCategory(keyword);
		Pageable pageable =PageRequest.of(pageNo-1, 10);
		Integer start= (int) pageable.getOffset();
		Integer end = (int)((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() :  pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);
		
		return new PageImpl<SubCategory>(list, pageable,  this.searchSubCategory(keyword).size());
	}

}
