package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import DaiHoc.BachHoa.entity.BillDetail;
import DaiHoc.BachHoa.entity.Bill;



@Component
public interface IBillService {
	List<Bill> getAll();
	Boolean save(Bill bill);
	Boolean update(Bill bill);
	Boolean delete(Long Id);
	Bill findById(Long id);
	List<Bill> searchBill(String keyword);
	Page<Bill> getAll(Integer pageNo);
	Page<Bill> searchBill(String keyword, Integer pageNo);
	List<Bill> findBillbyUserId(Long id);
}
