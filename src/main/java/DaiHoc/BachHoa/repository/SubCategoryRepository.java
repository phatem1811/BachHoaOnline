package DaiHoc.BachHoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.Category;
import DaiHoc.BachHoa.entity.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

	@Query("SELECT c FROM SubCategory c WHERE c.name LIKE %?1%")
	List<SubCategory> searchSubCategory(String keyword);
}
