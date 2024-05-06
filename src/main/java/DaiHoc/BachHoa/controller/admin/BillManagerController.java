package DaiHoc.BachHoa.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.BachHoa.entity.Bill;
import DaiHoc.BachHoa.entity.BillDetail;
import DaiHoc.BachHoa.entity.Category;
import DaiHoc.BachHoa.entity.User;
import DaiHoc.BachHoa.service.Imp.BillDetailService;
import DaiHoc.BachHoa.service.Imp.BillService;
@Controller
@RequestMapping("/admin")
public class BillManagerController {
	@Autowired
	private BillService BillService;
	@Autowired
	private BillDetailService billdetailService;
	@GetMapping("/bill")
	public String BillMangerPage(Model model,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo) {
		Page<Bill> list = BillService.getAll(pageNo);	

		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("curPage", pageNo);
		model.addAttribute("list", list);

		return "/admin/views/bills/BillManager";
	
	}
	@GetMapping("/billdetail/{id}")
	public String BillDeteil(Model model, @PathVariable("id") Long id) {
		List<BillDetail> billdetail = billdetailService.findBillDetailByBillId(id);
		model.addAttribute("billdetail", billdetail);
		return "admin/views/bills/BillDetail";		
	}
	
}
