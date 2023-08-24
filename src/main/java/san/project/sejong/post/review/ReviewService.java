package san.project.sejong.post.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import san.project.sejong.user.UserEntity;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void createReview(String title, String content, String restaurantName, UserEntity user){
        ReviewEntity review = new ReviewEntity();
        review.setTitle(title);
        review.setContent(content);
        review.setRestaurantName(restaurantName);
        review.setUserEntity(user);
        reviewRepository.save(review);
    }

    // 특정 음식점에 대해 작성한 전체 리뷰들 리스트화
    public List<ReviewEntity> getReviewList(String restaurantName){
        return this.reviewRepository.findByRestaurantName(restaurantName);
    }

    public void deleteReview(long reviewId){
        Optional<ReviewEntity> reviewEntity = this.reviewRepository.findById(reviewId);
        ReviewEntity review = reviewEntity.get();

        this.reviewRepository.delete(review);
    }

    public ReviewEntity getReviewContent(long reviewId){
        Optional<ReviewEntity> reviewEntity = this.reviewRepository.findById(reviewId);

        return reviewEntity.get();
    }

    // 수정한 리뷰 내용 저장
    public void updateReview(long reviewId, String title, String content){
        Optional<ReviewEntity> reviewEntity = this.reviewRepository.findById(reviewId);
        ReviewEntity review = reviewEntity.get();

        review.setTitle(title);
        review.setContent(content);

        this.reviewRepository.save(review);
    }
}
