package DaiHoc.BachHoa.controller.web;

import DaiHoc.BachHoa.entity.Bill;
import DaiHoc.BachHoa.entity.BillDetail;
import DaiHoc.BachHoa.service.IBillDetailService;
import DaiHoc.BachHoa.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BillDetailController {

    @Autowired
    private IBillService billService;

    @Autowired
    private IBillDetailService billDetailService;

    
    @GetMapping("/transaction-history/{billId}")
    public String viewBillDetails(@PathVariable Long billId, Model model) {
        Bill bill = billService.findById(billId);
        if (bill != null) {
            List<BillDetail> billDetails = billDetailService.findBillDetailByBillId(billId);
            model.addAttribute("bill", bill);
            model.addAttribute("billDetails", billDetails);
            return "web/views/bill-details";
        } else {
            return "redirect:/transaction-history";
        }
    }
}