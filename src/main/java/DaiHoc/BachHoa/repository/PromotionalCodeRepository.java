package DaiHoc.BachHoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.PromotionalCode;
@Repository
public interface PromotionalCodeRepository extends JpaRepository<PromotionalCode, Long>{

	PromotionalCode findPromotionalCodeByCode(String code);
}
