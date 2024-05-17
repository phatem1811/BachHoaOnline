package DaiHoc.BachHoa.controller.web;

import DaiHoc.BachHoa.entity.Bill;
import DaiHoc.BachHoa.entity.User;
import DaiHoc.BachHoa.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TransactionHistoryController {

    @Autowired
    private IBillService billService;

    
    @GetMapping("/transaction-history")
    public String viewTransactionHistory(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Nếu người dùng không đăng nhập, chuyển hướng đến trang đăng nhập
            return "redirect:/login";
        } else {
            // Người dùng đã đăng nhập, lấy các hóa đơn của họ
            List<Bill> bills = billService.findBillbyUserId(user.getId());
            model.addAttribute("bills", bills);
            return "web/views/transaction-history";
        }
    }
//    @PostMapping("/transaction-history")
//    public String addTransactionHistory(@ModelAttribute("bill") Bill bill, HttpSession session, Model model) {
//        User user = (User) session.getAttribute("user");
//            // Người dùng đã đăng nhập, thêm hóa đơn mới
//            bill.setUser(user); // Thiết lập người dùng cho hóa đơn mới
//            billService.save(bill); // Lưu hóa đơn mới
//            // Lấy lại danh sách hóa đơn cập nhật
//            List<Bill> bills = billService.findBillbyUserId(user.getId());
//            model.addAttribute("bills", bills);
//            return "web/views/transaction-history";
//    }
}