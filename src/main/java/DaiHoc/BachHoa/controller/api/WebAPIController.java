package DaiHoc.BachHoa.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DaiHoc.BachHoa.repository.CategoryRepository;


@RestController
@RequestMapping("/api")
public class WebAPIController {
	@Autowired
	private CategoryRepository repo;


	}

