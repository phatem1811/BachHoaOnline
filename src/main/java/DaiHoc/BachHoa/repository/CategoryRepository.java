package DaiHoc.BachHoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("SELECT c FROM Category c WHERE c.name LIKE %?1%")
	List<Category> searchCategory(String keyword);
}
