package DaiHoc.BachHoa.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DaiHoc.BachHoa.entity.Review;
import DaiHoc.BachHoa.repository.ReviewRepository;
import DaiHoc.BachHoa.service.IReviewService;
@Service
public class ReviewService implements IReviewService
{
	@Autowired
	private ReviewRepository repo;
	@Override
	public List<Review> getAll() {
		return repo.findAll();
	}
	@Override
    public void saveReview(Review review) {
        repo.save(review);
    }
}
