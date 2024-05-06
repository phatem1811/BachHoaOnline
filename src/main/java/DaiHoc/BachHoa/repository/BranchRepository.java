package DaiHoc.BachHoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{

}
