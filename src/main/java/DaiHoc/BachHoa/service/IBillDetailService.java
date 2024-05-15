package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.stereotype.Component;

import DaiHoc.BachHoa.entity.Bill;
import DaiHoc.BachHoa.entity.BillDetail;



@Component
public interface IBillDetailService {
	List<BillDetail> getAll();
	List<BillDetail> findBillDetailByBillId(Long id);
	Boolean save(BillDetail billdetail);
}
