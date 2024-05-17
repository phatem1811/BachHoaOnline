package DaiHoc.BachHoa.service;

import java.util.List;

import org.springframework.stereotype.Component;

import DaiHoc.BachHoa.entity.Review;

@Component
public interface IReviewService {
	List<Review> getAll();
	void saveReview(Review review);
}
