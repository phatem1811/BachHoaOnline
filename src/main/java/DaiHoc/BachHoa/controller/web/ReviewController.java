package DaiHoc.BachHoa.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import DaiHoc.BachHoa.entity.Review;
import DaiHoc.BachHoa.entity.Product;
import DaiHoc.BachHoa.service.IReviewService;
import DaiHoc.BachHoa.service.IProductService;

@Controller
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @Autowired
    private IProductService productService;

    @GetMapping("/review/{productId}")
    public String showReviewForm(@PathVariable Long productId, Model model) {
        Product product = productService.getByID(productId); 
        model.addAttribute("product", product);
        model.addAttribute("review", new Review());
        return "/web/views/review-form"; 
    }

    @PostMapping("/review/{productId}")
    public String submitReview(@PathVariable Long productId, 
                               @ModelAttribute("review") Review review,
                               RedirectAttributes redirectAttributes) {
        // Lưu đánh giá vào cơ sở dữ liệu
        Product product = productService.getByID(productId);
        review.setProduct(product);
        reviewService.saveReview(review);
        
        // Chuyển hướng tới trang chi tiết sản phẩm
        redirectAttributes.addAttribute("productId", productId);
        return "redirect:/detail/" + productId;
    }
}