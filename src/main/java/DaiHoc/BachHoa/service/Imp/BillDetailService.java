package DaiHoc.BachHoa.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.entity.BillDetail;
import DaiHoc.BachHoa.repository.BillDetailRepository;
import DaiHoc.BachHoa.service.IBillDetailService;

@Service
public class BillDetailService implements IBillDetailService {

	@Autowired
	private BillDetailRepository repo;
	@Override
	public List<BillDetail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillDetail> findBillDetailByBillId(Long id) {
		// TODO Auto-generated method stub
		return repo.findBillDetailByBillId(id);
	}

}
