package DaiHoc.BachHoa.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import DaiHoc.BachHoa.entity.Bill;
import DaiHoc.BachHoa.entity.Category;
import DaiHoc.BachHoa.entity.PromotionalCode;

import DaiHoc.BachHoa.entity.User;

import DaiHoc.BachHoa.service.Imp.BillService;
import DaiHoc.BachHoa.service.Imp.PromotionalCodeService;
import DaiHoc.BachHoa.service.Imp.UserService;

@Controller
@RequestMapping("/admin")
public class UserManagerController {
	@Autowired
	private UserService userService;
	@Autowired
	private BillService billService;
//	@Autowired
//	private AccountService accountService;
//	@Autowired
//	private BillService billService;
//	@Autowired
//	private TransactionRepository transRepo;
	@GetMapping("/user")
	public String UserMangerPage(Model model,@Param("keyword")String keyword
			,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo) {
		Page<User> list = userService.getAll(pageNo);
		

		if(keyword != null) {
			list = userService.searchUser(keyword,pageNo);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("curPage", pageNo);
		model.addAttribute("list", list);

		return "/admin/views/user/UserManager";
		
		
		
		
	}
//	@GetMapping("/show-info-user/{id}")
//	public String showInfoUser(Model model, @PathVariable("id") Long id) {
//		User user = userService.findById(id);
//		model.addAttribute("user", user);
//		return "admin/views/user/UserInfo";		
//	}
//	
//	@GetMapping("/show-acc/{id}")
//	public String showAcc(Model model, @PathVariable("id") Long id) {
//		
//    	Account acc = accountService.findAccountByUserId(id);
//		model.addAttribute("acc", acc);
//		return "admin/views/user/AccountInfo";		
//	}
//	
	@GetMapping("/update-user/{id}")
	public String editUser(Model model, @PathVariable("id") Long id) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "admin/views/user/UpdateUser";		
	}
//	@GetMapping("/update-account/{id}")
//	public String editAcc(Model model, @PathVariable("id") Long id) {
//		Account account = accountService.findById(id);
//
//		model.addAttribute("account", account);
//		return "admin/views/user/UpdateAccount";		
//	}
	@PostMapping("/update-user")
	public String updateUser(@ModelAttribute("user")User user) {
		if(userService.update(user) ) {
			return "redirect:/admin/user";
		}	
		return "redirect:/admin/admin";
	}
//	@PostMapping("/update-acc")
//	public String updateAccount(@ModelAttribute("acc")Account acc) {
//	
//		if(accountService.update(acc) ) {
//			return "redirect:/admin/user";
//		}	
//		return "redirect:/admin/admin";
//	}
	@GetMapping("/purchase-history/{id}")
	public String PurchaseHistory(Model model, @PathVariable("id") Long id) {
		List<Bill> list = billService.findBillbyUserId(id);
		model.addAttribute("list", list);
		return "admin/views/user/PurchaseHistory";		
	}
//	@GetMapping("/bill-detail/{id}")
//	public String BillDetail(Model model, @PathVariable("id") Long id) {
//		Bill bill = billService.findById(id);
//		List<Transaction> listtrans = transRepo.findTransactionByBillId(id);
//		model.addAttribute("listtrans", listtrans);
//		model.addAttribute("bill", bill);
//		
//		return "admin/views/user/BillDetail";		
//	}
}
	