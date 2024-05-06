package DaiHoc.BachHoa.controller.admin;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import DaiHoc.BachHoa.entity.Category;
import DaiHoc.BachHoa.entity.Product;
import DaiHoc.BachHoa.entity.SubCategory;
import DaiHoc.BachHoa.repository.CategoryRepository;
import DaiHoc.BachHoa.service.Imp.CategoryService;
import DaiHoc.BachHoa.service.Imp.SubCategoryService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/admin")
public class SubCategoryManagerController {
	@Autowired
	private SubCategoryService subcategoryService;
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/subcategory")
	public String SubCategoryMangerPage(Model model,@Param("keyword")String keyword
			,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo) {
		
		
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);
		
		Page<SubCategory> list = subcategoryService.getAll(pageNo);
		

		if(keyword != null) {
			list = subcategoryService.searchSubCategory(keyword,pageNo);
			model.addAttribute("keyword", keyword);
		}
		
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("curPage", pageNo);
		model.addAttribute("list", list);
		return "/admin/views/SubCategory/SubCategoryManager";
	}
	
//	
	@GetMapping("/create-subcategory")
	public String addSubCategory(Model model) {
		
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);
		
		SubCategory subCategory = new SubCategory();
		model.addAttribute("subCategory", subCategory);
		return "/admin/views/SubCategory/CreateSubCategory";
	}
//	
//	
	@PostMapping("/create-subcategory")
	public String createSubCategory(@ModelAttribute("subcategory") SubCategory subcategory ) {
		if(subcategoryService.save(subcategory)) {
			return "redirect:/admin/subcategory";
		}
		
		return "redirect:/admin/create-subcategory";
	}
//	
	@GetMapping("/update-subcategory/{id}")
	public String editSubCategory(Model model, @PathVariable("id") Long id) {
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);
		
		
		SubCategory subcategory = subcategoryService.findById(id);
		model.addAttribute("subcategory", subcategory);
		return "admin/views/SubCategory/UpdateSubCategory";		
	}
//	
	@PostMapping("/update-subcategory")
	public String updateCategory(@ModelAttribute("subcategory") SubCategory subcategory) {
		if(subcategoryService.update(subcategory)) {
			return "redirect:/admin/subcategory";
		}
		
		return "redirect:/admin/update-subcategory";
	}
	
	@GetMapping("/delete-subcategory/{id}")
	public String deleteSubCategory(@ModelAttribute("id") Long id) {
		if(subcategoryService.delete(id)) {
			return "redirect:/admin/subcategory";
		}
		
		return "redirect:/admin/admin";
	}
}