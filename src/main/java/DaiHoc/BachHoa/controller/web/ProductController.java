package DaiHoc.BachHoa.controller.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import DaiHoc.BachHoa.entity.Category;
import DaiHoc.BachHoa.entity.Product;
import DaiHoc.BachHoa.entity.ProductDetail;
import DaiHoc.BachHoa.entity.SubCategory;
import DaiHoc.BachHoa.entity.User;
import DaiHoc.BachHoa.service.IProductService;
import DaiHoc.BachHoa.service.Imp.CategoryService;
import DaiHoc.BachHoa.service.Imp.ProductService;
import DaiHoc.BachHoa.service.Imp.ShoppingCartItem;
import DaiHoc.BachHoa.service.Imp.SubCategoryService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class ProductController {
	@Autowired
	private ProductService productservice;

	@Autowired
	private ShoppingCartItem cartService;
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubCategoryService subcategoryService;

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("")
	public String getProduct( HttpSession session, Model model) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);
		}
		else {
			model.addAttribute("user", null);
		}
		int count = (int) cartService.getCount();
		model.addAttribute("count", count);
		List<Product> products = productservice.getAll();
		List<Category> category = categoryService.getAll();
		List<SubCategory> subcategory = subcategoryService.getAll();
		model.addAttribute("category", category);
		model.addAttribute("subcategory", subcategory);

		model.addAttribute("products", products);
		model.addAttribute("count", products.size());
		model.addAttribute("products", productService.getAll());
		return "web/views/shop";
	}

	@GetMapping("detail/{id}")
	public String ProductDetail(HttpSession session,Model model, @PathVariable("id") Long id) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);
		}
		else {
			model.addAttribute("user", null);
		}
		Product product = productService.getByID(id);
		model.addAttribute("product", product);
		return "web/views/detail";		
	}
	 @GetMapping("filter/{id}")
	 public String filter(HttpSession session,Model model, @PathVariable("id") Long id) {
		 
		 if (session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				model.addAttribute("user", user);
			}
			else {
				model.addAttribute("user", null);
			}
		  Category c = categoryService.findById(id);
		  List<Product> list = productService.GetProductByCategory(id);
		  List<Category> category = categoryService.getAll();
		  model.addAttribute("c", c);
		  model.addAttribute("category", category);
		  model.addAttribute("list", list);
		  return "web/views/filterByCategory";
	 }
	}

