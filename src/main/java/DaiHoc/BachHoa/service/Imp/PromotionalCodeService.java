package DaiHoc.BachHoa.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.entity.PromotionalCode;
import DaiHoc.BachHoa.entity.PromotionalCode;
import DaiHoc.BachHoa.repository.PromotionalCodeRepository;
import DaiHoc.BachHoa.service.IPromotionalCodeService;
@Service
public class PromotionalCodeService implements IPromotionalCodeService
{
	@Autowired
	private PromotionalCodeRepository repo;
	@Override
	public List<PromotionalCode> getAll() {
		return repo.findAll();
	}
	@Override
	public Boolean save(PromotionalCode promotionalCode) {
		// TODO Auto-generated method stub
		try {
			repo.save(promotionalCode);
			return true;
		}
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 return false;
	}
	@Override
	public Boolean update(PromotionalCode promotionalCode) {
		try {
			Optional<PromotionalCode> opt = Optional.of(findById(promotionalCode.getId()));
			if (opt.isPresent()) {
				repo.save(promotionalCode);
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
	public PromotionalCode findById(Long id) {
		// TODO Auto-generated method stub
		 return repo.findById(id).get();
	}
	@Override
	public PromotionalCode findCodeByCode(String code) {
		// TODO Auto-generated method stub
		return repo.findPromotionalCodeByCode(code);
	}

}
