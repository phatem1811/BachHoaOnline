package DaiHoc.BachHoa.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DaiHoc.BachHoa.entity.PromotionalCode;
import DaiHoc.BachHoa.service.Imp.PromotionalCodeService;


@Controller
@RequestMapping("/admin")
public class PromotionalCodeController {
	@Autowired
	private PromotionalCodeService promotionalCodeService;
	@GetMapping("/promotional-code")
	public String PromotionalCodeManager(Model model) {
		List<PromotionalCode> code = promotionalCodeService.getAll();
		model.addAttribute("code", code);
		return  "admin/views/DiscountEvent/PromotionalCodeManager";
		
	}
	@GetMapping("/create-promotional-code")
	public String addPromotionalCode(Model model) {
		PromotionalCode procode = new PromotionalCode();
		model.addAttribute("procode", procode);
		return "/admin/views/DiscountEvent/CreateCode";
	}
	@PostMapping("/create-promotional-code")
	public String createPromotionCode(@ModelAttribute("procode") PromotionalCode procode ) {
		if(promotionalCodeService.save(procode)) {
			return "redirect:/admin/promotional-code";
		}
		
		return "redirect:/admin";
	}
	@GetMapping("/delete-promotional-code/{id}")
	public String deletePromotionalCode(@ModelAttribute("id") Long id) {
		if(promotionalCodeService.delete(id)) {
			return "redirect:/admin/promotional-code";
		}
		
		return "redirect:/admin/admin";
	}
	@GetMapping("/update-promotional-code/{id}")
	public String editPromotionalCode(Model model, @PathVariable("id") Long id) {
		PromotionalCode pro_code = promotionalCodeService.findById(id);
		model.addAttribute("pro_code", pro_code);
		return "admin/views/DiscountEvent/UpdateCode";		
	}
	@PostMapping("/update-promotional-code")
	public String updatePromotionalCode(@ModelAttribute("pro_code") PromotionalCode pro_code ) {
		if(promotionalCodeService.update(pro_code)) {
			return "redirect:/admin/promotional-code";
		}
		
		return "redirect:/admin/create-category";
	}
}
