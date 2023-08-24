package san.project.sejong.post.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import san.project.sejong.user.UserEntity;
import san.project.sejong.user.UserRepository;
import san.project.sejong.user.UserService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ReviewRepository reviewRepository;
    @PostMapping("/save-content/{restaurantName}")
    public String savecontent(String content, String title, @PathVariable("restaurantName") String restaurantName, Authentication authentication){

        String userEmail = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
        }

        UserEntity user = this.userService.getUser(userEmail);

        reviewService.createReview(title, content, restaurantName, user);

        return "redirect:/user/" + user.getUsername() + "/mypage";
    }

    @GetMapping("/test/load/{number}")
    public String testLoad(Model model, @PathVariable Long number, Authentication authentication){
        String userEmail = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
        }
        UserEntity user = this.userService.getUser(userEmail);
        model.addAttribute("user", user);

        Optional<ReviewEntity> content = reviewRepository.findById(number);
        ReviewEntity review = content.get();
        model.addAttribute("review", review);
        System.out.println(review.getContent());
        return "review_load";
    }

    // 작성한 리뷰 삭제
    @DeleteMapping("/review/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable long reviewId){
        try{
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok("게시물이 삭제되었습니다.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시물 삭제에 실패했습니다.");
        }
    }

    // 작성한 기존 리뷰 수정
    @GetMapping("/review/modify/{reviewId}")
    public String modifyReview(@PathVariable long reviewId, Model model, Authentication authentication){
        String userEmail = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
        }
        UserEntity user = this.userService.getUser(userEmail);
        model.addAttribute("user", user);

        ReviewEntity reviewContent = reviewService.getReviewContent(reviewId);

        model.addAttribute("review", reviewContent);
        return "review_modify";
    }

    @PostMapping("/review-modify/{restaurantName}/{reviewId}")
    public String modifyReviewUpdate(@PathVariable long reviewId, String title, String content, Authentication authentication){
        String userEmail = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
        }
        UserEntity user = this.userService.getUser(userEmail);

        try {
            reviewService.updateReview(reviewId, title, content);
            return "redirect:/user/" + user.getUsername() + "/mypage";
        } catch (Exception e){
            return "error";
        }
    }
}
