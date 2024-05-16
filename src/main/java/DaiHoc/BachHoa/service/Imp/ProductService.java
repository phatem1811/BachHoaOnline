package DaiHoc.BachHoa.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.entity.Product;
import DaiHoc.BachHoa.repository.ProductRepository;
import DaiHoc.BachHoa.service.IProductService;
@Service
public class ProductService implements IProductService{
	@Autowired
	ProductRepository repo;
	
	@Override
	public List<Product> getAll() {
		return repo.findAll();
	}

	@Override
	public Product getByID(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public Product getByCategory(Long cateID) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean create(Product product) {
		// TODO Auto-generated method stub
		try {
			repo.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Product product) {
		try {
			Optional<Product> opt = Optional.of(getByID(product.getId()));
			if (opt.isPresent()) {
				repo.save(product);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		try {
			repo.delete(getByID(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Page<Product> getAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 4);
		return repo.findAll(pageable);
	}

	@Override
	public Page<Product> searchProduct(String keyword, Integer pageNo) {
		List<Product> list = this.searchProduct(keyword);
		Pageable pageable = PageRequest.of(pageNo - 1, 4);
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size()
				: pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);

		return new PageImpl<Product>(list, pageable, this.searchProduct(keyword).size());
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchProduct(keyword);
	}

	@Override
	public List<Product> GetProductByCategory(Long id) {
		// TODO Auto-generated method stub
		return repo.findByCategoryId(id);
	}



}
