package DaiHoc.BachHoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT c FROM Product c WHERE c.name LIKE %?1%")
	List<Product> searchProduct(String keyword);
	
	List<Product> findByCategoryId(Long id);

}
