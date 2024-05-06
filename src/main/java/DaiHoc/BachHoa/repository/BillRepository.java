package DaiHoc.BachHoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.Bill;
import DaiHoc.BachHoa.entity.Category;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	@Query("SELECT c FROM Bill c WHERE c.method_payment LIKE %?1%")
	List<Bill> searchBill(String keyword);
	
	
	List<Bill> findBillByUserId(Long id);
}
