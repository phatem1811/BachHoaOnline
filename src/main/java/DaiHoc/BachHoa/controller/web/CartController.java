package DaiHoc.BachHoa.controller.web;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DaiHoc.BachHoa.entity.Bill;
import DaiHoc.BachHoa.entity.BillDetail;
import DaiHoc.BachHoa.entity.Category;
import DaiHoc.BachHoa.entity.LineItem;
import DaiHoc.BachHoa.entity.Product;
import DaiHoc.BachHoa.entity.PromotionalCode;
import DaiHoc.BachHoa.entity.User;
import DaiHoc.BachHoa.repository.PromotionalCodeRepository;
import DaiHoc.BachHoa.service.Imp.BillDetailService;
import DaiHoc.BachHoa.service.Imp.BillService;
import DaiHoc.BachHoa.service.Imp.ProductService;
import DaiHoc.BachHoa.service.Imp.PromotionalCodeService;
import DaiHoc.BachHoa.service.Imp.ShoppingCartItem;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("shopping-cart")
public class CartController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ShoppingCartItem cartService;
	@Autowired
	private BillService billservice;
	@Autowired
	private BillDetailService billdetailservice;

	@Autowired
	private PromotionalCodeService promotionalCodeService;

	@GetMapping("view")
	public String viewCart(HttpSession session,Model model, @Param("code") String code) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);
		}
		else {
			model.addAttribute("user", null);
		}
		Collection<LineItem> lineitem = cartService.getAllLineItems();
		double total = cartService.getTotal();
		int count = cartService.getCount();
		model.addAttribute("lineitem", lineitem);
		if (code != null) {
			PromotionalCode procode = promotionalCodeService.findCodeByCode(code);
			if (procode != null) {
				double discount = procode.getDiscount();
				double newtotal = total - total * discount / 100;
				model.addAttribute("procode", procode);
				model.addAttribute("newtotal", newtotal);
			}

		}
		model.addAttribute("count", count);
		model.addAttribute("total", total);
		return "/web/views/cart";
	}

	@GetMapping("/add/{id}")
	public String addItem(Model model, @ModelAttribute("id") Long id) {
		Product product = productService.getByID(id);
		if (product != null) {
			LineItem item = new LineItem();
			item.setProductId(product.getId());
			item.setName(product.getName());
			item.setPrice(product.getPrice());
			item.setQuantity(1);
			item.setImage(product.getImage());
			cartService.add(item);
			return "redirect:/home";
		}

		return null;

	}

	@GetMapping("/remove/{id}")
	public String removeLineitem(Model model, @ModelAttribute("id") Long id) {
		cartService.remove(id);
		return "redirect:/shopping-cart/view";
	}

	@GetMapping("/increase/{id}")
	public String increaseQuantity(Model model, @ModelAttribute("id") Long id) {
		LineItem item = cartService.findLineItem(id);
		item.setQuantity(item.getQuantity() + 1);
		return "redirect:/shopping-cart/view";
	}

	@GetMapping("/decrease/{id}")
	public String decreaseQuantity(Model model, @ModelAttribute("id") Long id) {
		LineItem item = cartService.findLineItem(id);
		if (item.getQuantity() == 1) {
			cartService.remove(id);
		} else {
			item.setQuantity(item.getQuantity() - 1);
		}
		return "redirect:/shopping-cart/view";
	}
	@GetMapping("/delete-cart")
	public String deletecart(Model model){
		cartService.clear();
		return "redirect:/shopping-cart/view"; 
	}

	@GetMapping("/checkout")
	public String Checkout(HttpSession session, Model model, @Param("code") String code) {

		if (session.getAttribute("user") == null) {
			// Nếu đã đăng nhập, lấy thông tin cá nhân từ session
			return "redirect:/login";
		}
		Bill bill = new Bill();
		User user = (User) session.getAttribute("user");
		Collection<LineItem> lineitem = cartService.getAllLineItems();

		model.addAttribute("user", user);
		double total = cartService.getTotal();

		if (code != null) {
			PromotionalCode procode = promotionalCodeService.findCodeByCode(code);
			if (procode != null) {
				double discount = procode.getDiscount();
				double newtotal = total - total * discount / 100;
				model.addAttribute("procode", procode);
				model.addAttribute("newtotal", newtotal);
			}

		}
		model.addAttribute("bill", bill);
		model.addAttribute("lineitem", lineitem);
		model.addAttribute("total", total);
		return "/web/views/checkout";
	}

	@PostMapping("/payment")
	public String payment(HttpSession session, Model model, @Param("payment") String payment,@Param("address") String address,
			@Param("idcode") Long idcode)  {
		User user = (User) session.getAttribute("user");
		Bill bill = new Bill();
		
		LocalDate d = java.time.LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = d.format(formatter);
        bill.setDate(date);
		bill.setAddress(address);
		bill.setMethod_payment(payment);
		bill.setUser(user);
		double total = cartService.getTotal();
		bill.setTotal_price(total);

		if (idcode != null) {
			PromotionalCode procode = promotionalCodeService.findById(idcode);
			double discount = procode.getDiscount();
			double newtotal = total - total * discount / 100;
			bill.setTotal_price(newtotal);
			bill.setPromotionalCode(procode);
		}
		else {
			long id = 4;
			PromotionalCode procode = promotionalCodeService.findById(id);	
			bill.setPromotionalCode(procode);
		}
		if(billservice.save(bill)) {
			Collection<LineItem> list = cartService.getAllLineItems();
			for (LineItem item :list) {
				BillDetail billdetail = new BillDetail();
				billdetail.setBill(bill);
				billdetail.setProduct(productService.getByID(item.getProductId()));
				billdetail.setQuantity(item.getQuantity());
				billdetail.setTotalLineitem(item.getQuantity()*item.getPrice());
				billdetailservice.save(billdetail);
			}
			cartService.clear();
			return "redirect:/home";
		}
		
		return "redirect:/login";
	}

}
