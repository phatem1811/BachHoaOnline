package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.stereotype.Component;

import DaiHoc.BachHoa.entity.PromotionalCode;


@Component
public interface IPromotionalCodeService {
	List<PromotionalCode> getAll();
	Boolean save(PromotionalCode promotionalCode);
	Boolean update(PromotionalCode promotionalCode);
	Boolean delete(Long Id);
	PromotionalCode findById(Long id);
	PromotionalCode findCodeByCode(String code);
}
