package DaiHoc.BachHoa.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller

public class AdminPageController {
	@RequestMapping("/admin")
	public String Adminpage() {
		return "/admin/admin";
	}
	
	

}
