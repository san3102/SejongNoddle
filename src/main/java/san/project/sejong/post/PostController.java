package san.project.sejong.post;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import san.project.sejong.WebCrawlerUsingSelenium;
import san.project.sejong.post.crawlingData.CrawlingData;
import san.project.sejong.post.responseData.Item;
import san.project.sejong.post.review.ReviewEntity;
import san.project.sejong.post.review.ReviewService;
import san.project.sejong.user.UserEntity;
import san.project.sejong.user.UserService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final StoreListService storeListService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final WebCrawlerUsingSelenium webCrawlerUsingSelenium;

    @GetMapping("/list/{branch}")
    public String list(Model model, @PathVariable("branch") String branch, Authentication authentication) {
        try {

            String userEmail = null;

            if (authentication != null && authentication.isAuthenticated()) {
                // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
                userEmail = userService.getAuthUsername(userEmail, authentication);
                UserEntity user = this.userService.getUser(userEmail);
                // 로그인한 유저 정보를 user 객체로 전달
                model.addAttribute("user", user);
            }

            List<Item> restaurants = storeListService.getRestaurantsWithNoodle(branch);
            model.addAttribute("branch", branch);
            model.addAttribute("restaurants", restaurants); // 모델 객체에 데이터 추가
            return "storeList.html"; // 템플릿 이름 반환
        } catch (IOException e) {
            // 예외 처리 로직
            System.out.println("error");
            return "error"; // 예시로 에러 템플릿 반환
        }
    }

    @GetMapping("/detail/{branch}/{restaurantName}")
    public String detail(@PathVariable("restaurantName") String restaurantName, @PathVariable("branch") String branch, Model model, Authentication authentication)
            throws IOException {
        List<CrawlingData> crawlingDataList = webCrawlerUsingSelenium.getCrawlingDataList(restaurantName);

        if (crawlingDataList.isEmpty()) { // 이미 크롤링된 데이터가 없는 경우에만 크롤링 실행
            webCrawlerUsingSelenium.scraping(restaurantName);
            crawlingDataList = webCrawlerUsingSelenium.getCrawlingDataList(restaurantName);
        }

        String userEmail = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
            UserEntity user = this.userService.getUser(userEmail);
            // 로그인한 유저 정보를 user 객체로 전달
            model.addAttribute("user", user);
        }

        Item restaurant = storeListService.getRestaurantByName(restaurantName, branch);
        if (restaurant != null) {
            List<ReviewEntity> reviewEntityList = this.reviewService.getReviewList(restaurantName);
            model.addAttribute("reviewList", reviewEntityList);

            model.addAttribute("restaurant", restaurant);
            model.addAttribute("crawlingDataList", crawlingDataList); // 크롤링 데이터를 모델에 추가
            return "detailView";  // 뷰 이름 반환
        } else {
            return "notFound";    // 해당 이름의 음식점이 없는 경우 처리하는 뷰 이름 반환
        }
    }

    @GetMapping("/search")
    public String search(Authentication authentication, Model model, @RequestParam("keyword") String keyword){
        try{
            String userEmail = null;

            if (authentication != null && authentication.isAuthenticated()) {
                // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
                userEmail = userService.getAuthUsername(userEmail, authentication);
                UserEntity user = this.userService.getUser(userEmail);
                // 로그인한 유저 정보를 user 객체로 전달
                model.addAttribute("user", user);
            }

            List<Item> restaurants = storeListService.getRestaurantsWithKeyword(keyword);
            model.addAttribute("restaurants", restaurants); // 모델 객체에 데이터 추가

            return "searchList";
        } catch (IOException e){
            return "error";
        }
    }

    // 검색결과에 따른 목록에서의 해당 음식점 상세정보
    @GetMapping("/detail/{restaurantName}")
    public String searchDetail(@PathVariable("restaurantName") String restaurantName, Model model, Authentication authentication)
            throws IOException {
        List<CrawlingData> crawlingDataList = webCrawlerUsingSelenium.getCrawlingDataList(restaurantName);

        if (crawlingDataList.isEmpty()) { // 이미 크롤링된 데이터가 없는 경우에만 크롤링 실행
            webCrawlerUsingSelenium.scraping(restaurantName);
            crawlingDataList = webCrawlerUsingSelenium.getCrawlingDataList(restaurantName);
        }

        String userEmail = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
            UserEntity user = this.userService.getUser(userEmail);
            // 로그인한 유저 정보를 user 객체로 전달
            model.addAttribute("user", user);
        }

        Item restaurant = storeListService.getRestaurantBySearchName(restaurantName);
        if (restaurant != null) {
            List<ReviewEntity> reviewEntityList = this.reviewService.getReviewList(restaurantName);
            model.addAttribute("reviewList", reviewEntityList);

            model.addAttribute("restaurant", restaurant);
            model.addAttribute("crawlingDataList", crawlingDataList); // 크롤링 데이터를 모델에 추가
            return "detailView";  // 뷰 이름 반환
        } else {
            return "notFound";    // 해당 이름의 음식점이 없는 경우 처리하는 뷰 이름 반환
        }
    }

}
