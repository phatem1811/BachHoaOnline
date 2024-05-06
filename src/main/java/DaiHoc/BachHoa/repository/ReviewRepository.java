package DaiHoc.BachHoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import DaiHoc.BachHoa.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
}
