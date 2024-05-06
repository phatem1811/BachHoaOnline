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

import DaiHoc.BachHoa.entity.Branch;
import DaiHoc.BachHoa.service.Imp.BranchService;
@Controller
@RequestMapping("/admin")
public class BranchManagerCotroller {
	@Autowired
	private BranchService branchService;
	@GetMapping("branch")
	public String BranchManager(Model model) {
		List<Branch> branch = branchService.getAll();
		model.addAttribute("branch", branch);
		return  "admin/views/branch/BranchManager";
		
	}
	@GetMapping("/create-branch")
	public String addBranch(Model model) {
		Branch branch = new Branch();
		model.addAttribute("branch", branch);
		return "/admin/views/branch/CreateBranch";
	}
	@PostMapping("/create-branch")
	public String createBranch(@ModelAttribute("branch") Branch branch ) {
		if(branchService.save(branch)) {
			return "redirect:/admin/branch";
		}
		
		return "redirect:/admin";
	}
	@GetMapping("/delete-branch/{id}")
	public String deleteBranch(@ModelAttribute("id") Long id) {
		if(branchService.delete(id)) {
			return "redirect:/admin/branch";
		}
		
		return "redirect:/admin/admin";
	}
	@GetMapping("/update-branch/{id}")
	public String editBranch(Model model, @PathVariable("id") Long id) {
		Branch branch = branchService.findById(id);
		model.addAttribute("branch", branch);
		return "admin/views/branch/UpdateBranch";		
	}
	@PostMapping("/update-branch")
	public String updateBranch(@ModelAttribute("branch") Branch branch ) {
		if(branchService.update(branch)) {
			return "redirect:/admin/branch";
		}
		
		return "redirect:/admin";
	}
}
