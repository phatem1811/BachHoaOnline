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
import org.springframework.web.multipart.MultipartFile;

import DaiHoc.BachHoa.entity.Branch;
import DaiHoc.BachHoa.entity.Category;

import DaiHoc.BachHoa.entity.Product;
import DaiHoc.BachHoa.entity.ProductDetail;
import DaiHoc.BachHoa.entity.SubCategory;
import DaiHoc.BachHoa.service.Imp.BranchService;
import DaiHoc.BachHoa.service.Imp.CategoryService;
import DaiHoc.BachHoa.service.Imp.ProductDetailService;
import DaiHoc.BachHoa.service.Imp.ProductService;
import DaiHoc.BachHoa.service.Imp.SubCategoryService;
import DaiHoc.BachHoa.service.IStorageService;

@Controller
@RequestMapping("/admin")
public class ProductManagerController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	@Autowired
	private SubCategoryService subCategoryService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private IStorageService iStorageService;
	@GetMapping("/product")
	public String ProductMangerPage(Model model, @Param("keyword") String keyword, @Param("cateid") Long cateid,
			@Param("manuid") Long manuid,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		
		List<Category> listCate = this.categoryService.getAll();	
		model.addAttribute("listCate", listCate);
		
		List<SubCategory> listSubcate = this.subCategoryService.getAll();
		model.addAttribute("listSubcate", listSubcate);
		
		Page<Product> list = productService.getAll(pageNo);
		

	

		if (keyword != null) {
			list = productService.searchProduct(keyword, pageNo);
			model.addAttribute("keyword", keyword);
		}

		model.addAttribute("list", list);
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("curPage", pageNo);
		

		return "/admin/views/product/ProductManager";
	}
	@GetMapping("/product-detail/{id}")
	public String ProductDetail(Model model, @PathVariable("id") Long id) {
		Product product = productService.getByID(id);
		model.addAttribute("product", product);
		return "admin/views/product/ProductDetail";		
	}
	@GetMapping("/create-product")
	public String createProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		ProductDetail productdetail = new ProductDetail();
		model.addAttribute("productdetail", productdetail);
		List<Branch> branch = this.branchService.getAll();
		model.addAttribute("branch", branch);
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);
		List<SubCategory> subcategory = subCategoryService.getAll();
		model.addAttribute("subcategory", subcategory);
	
		return "/admin/views/product/CreateProduct";
	}

	@PostMapping("/create-product")
	public String createProduct(Model model,@ModelAttribute("product") Product product,@ModelAttribute("productdetail") ProductDetail productdetail,
			@RequestParam("FilePicture") MultipartFile file) {
		iStorageService.store(file);

		String filename = file.getOriginalFilename();
		product.setImage(filename);
		product.setProductDetail(productdetail);
		
		Long subid = (long) 1;
		SubCategory sub = subCategoryService.findById(subid);
		product.setSubcategory(sub);
			if (productService.create(product)) {
				
				return "redirect:/admin/product";
			}
		
		

		return "redirect:/admin/create-product";
	}

	
	@GetMapping("/update-product/{id}")
	public String editProduct(Model model, @PathVariable("id") Long id) {
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);
		List<SubCategory> subcategory = this.subCategoryService.getAll();
		model.addAttribute("subcategory", subcategory);
		List<Branch> branch = this.branchService.getAll();
		model.addAttribute("branch", branch);
		Product product = productService.getByID(id);
		model.addAttribute("product", product);
		ProductDetail productdetail = product.getProductDetail();
		model.addAttribute("productdetail", productdetail);
		return "admin/views/product/UpdateProduct";
	}

//	
	@PostMapping("/update-product")
	public String updateProduct(@ModelAttribute("product") Product product,@ModelAttribute("productdetail") ProductDetail productdetail,
			@RequestParam("FilePicture") MultipartFile file) {
		if (!file.isEmpty()) {
			iStorageService.store(file);
			String filename = file.getOriginalFilename();
			product.setImage(filename);
		}
		Long subid = (long) 1;
		SubCategory sub = subCategoryService.findById(subid);
		product.setSubcategory(sub);
		product.setProductDetail(productdetail);

		if (productService.update(product)) {
			return "redirect:/admin/product";
		}

		return "redirect:/admin/update-product";
	}

	@GetMapping("/delete-product/{id}")
	public String deleteProduct(@ModelAttribute("id") Long id) {
		if (productService.delete(id)) {
			return "redirect:/admin/product";
		}

		return "redirect:/admin/admin";
	}

	
	

}
