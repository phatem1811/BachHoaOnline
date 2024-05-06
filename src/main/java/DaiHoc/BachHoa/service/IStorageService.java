package DaiHoc.BachHoa.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	void init();
	void store(MultipartFile file); 
	
	
	void save(MultipartFile file);
	Resource load(String filename);
	Stream<Path> loadAll();
}
