package DaiHoc.BachHoa.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.entity.Branch;

import DaiHoc.BachHoa.repository.BranchRepository;
import DaiHoc.BachHoa.service.IBranchService;

@Service
public class BranchService implements IBranchService{
	@Autowired
	private BranchRepository repo;
	@Override
	public List<Branch> getAll() {
		return repo.findAll();
	}
	@Override
	public Boolean save(Branch Branch) {
		// TODO Auto-generated method stub
		try {
			repo.save(Branch);
			return true;
		}
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 return false;
	}
	@Override
	public Boolean update(Branch Branch) {
		try {
			Optional<Branch> opt = Optional.of(findById(Branch.getId()));
			if (opt.isPresent()) {
				repo.save(Branch);
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
	public Branch findById(Long id) {
		// TODO Auto-generated method stub
		 return repo.findById(id).get();
	}


}
