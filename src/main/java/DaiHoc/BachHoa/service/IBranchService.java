package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.stereotype.Component;


import DaiHoc.BachHoa.entity.Branch;


@Component
public interface IBranchService {
	List<Branch> getAll();
	Boolean save(Branch branch);
	Boolean update(Branch branch);
	Boolean delete(Long Id);
	Branch findById(Long id);
}
