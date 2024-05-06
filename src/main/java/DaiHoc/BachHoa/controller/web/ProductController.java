package DaiHoc.BachHoa.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DaiHoc.BachHoa.entity.Product;
import DaiHoc.BachHoa.service.IProductService;



@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private IProductService service;
	@GetMapping("")
	public String getProduct(ModelMap model) {
		List<Product> products = service.getAll();
		model.addAttribute("products", products);
		model.addAttribute("count", products.size());
		return "web/views/products";
	}
}
