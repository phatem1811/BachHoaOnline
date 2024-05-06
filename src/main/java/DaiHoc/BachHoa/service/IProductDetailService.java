package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.stereotype.Component;

import DaiHoc.BachHoa.entity.Product;
import DaiHoc.BachHoa.entity.ProductDetail;

@Component
public interface IProductDetailService {
	List<ProductDetail> getAll();
	boolean create(ProductDetail productdetail);
	boolean update(ProductDetail productdetail);
	boolean delete(Long id);

}
