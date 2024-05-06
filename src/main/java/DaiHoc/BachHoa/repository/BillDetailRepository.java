package DaiHoc.BachHoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.BillDetail;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long>{
	List<BillDetail> findBillDetailByBillId(Long id);
}
