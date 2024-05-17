package DaiHoc.BachHoa.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.entity.BillDetail;
import DaiHoc.BachHoa.entity.Bill;
import DaiHoc.BachHoa.repository.BillRepository;
import DaiHoc.BachHoa.service.IBillDetailService;
import DaiHoc.BachHoa.service.IBillService;

@Service
public class BillService implements IBillService
{

	@Autowired
	private BillRepository repo;
	@Override
	public List<Bill> getAll() {
		return repo.findAll();
	}
	@Override
	public Boolean save(Bill Bill) {
		// TODO Auto-generated method stub
		try {
			repo.save(Bill);
			return true;
		}
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 return false;
	}
	@Override
	public Bill findById(Long id) {	
		return repo.findById(id).get();
	}

	@Override
	public Boolean update(Bill Bill) {
		try {
			Optional<Bill> opt = Optional.of(findById(Bill.getId()));
			if (opt.isPresent()) {
				repo.save(Bill);
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Boolean delete(Long Id) {
		try {
			repo.delete(findById(Id));
			return true;
		}
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 return false;
	}
	@Override
	public List<Bill> searchBill(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchBill(keyword);
	}
	@Override
	public Page<Bill> getAll(Integer pageNo) {
		Pageable pageable =PageRequest.of(pageNo-1, 10);
		return repo.findAll(pageable);
	}
	@Override
	public Page<Bill> searchBill(String keyword, Integer pageNo) {
		List list = this.searchBill(keyword);
		Pageable pageable =PageRequest.of(pageNo-1, 10);
		Integer start= (int) pageable.getOffset();
		Integer end = (int)((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() :  pageable.getOffset() + pageable.getPageSize());
		list = list.subList(start, end);
		
		return new PageImpl<Bill>(list, pageable,  this.searchBill(keyword).size());
	}
	@Override
	public List<Bill> findBillbyUserId(Long id) {
		// TODO Auto-generated method stub
		return repo.findBillByUserId(id);
	}
}
