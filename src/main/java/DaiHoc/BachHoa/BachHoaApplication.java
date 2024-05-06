package DaiHoc.BachHoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import DaiHoc.BachHoa.entity.Category;
import DaiHoc.BachHoa.repository.CategoryRepository;


@SpringBootApplication
public class BachHoaApplication {
	@Autowired
	private static CategoryRepository repo;
	public static void main(String[] args) {
		SpringApplication.run(BachHoaApplication.class, args);
	}

//    public void run(String... args) throws Exception {
//        // Logic to save category
//        repo.save(new Category());
//    }
}
