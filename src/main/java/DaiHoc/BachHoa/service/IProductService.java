package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import DaiHoc.BachHoa.entity.Product;
@Component
public interface IProductService {
	List<Product> getAll();
	Product getByID(Long id);
	Product getByCategory(Long cateID);

	
	boolean create(Product product);
	boolean update(Product product);
	boolean delete(Long id);
	List<Product> GetProductByCategory(Long id);
	
	List<Product> searchProduct(String keyword);
	Page<Product> getAll(Integer pageNo);
	Page<Product> searchProduct(String keyword, Integer pageNo);
}
