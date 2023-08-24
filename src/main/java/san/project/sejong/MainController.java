package san.project.sejong;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import san.project.sejong.post.responseData.Item;
import san.project.sejong.user.UserEntity;
import san.project.sejong.user.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String root(Authentication authentication, Model model){
        String userEmail = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
            UserEntity user = this.userService.getUser(userEmail);
            // 로그인한 유저 정보를 user 객체로 전달
            model.addAttribute("user", user);
        }


        return "index.html";
    }

    @GetMapping("/create/review/{restaurantName}")
    @PreAuthorize("isAuthenticated()")
    public String review(@PathVariable("restaurantName") String restaurantName, Model model, Authentication authentication){

        String userEmail = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
        }

        UserEntity user = this.userService.getUser(userEmail);
        // 로그인한 유저 정보를 user 객체로 전달
        model.addAttribute("user", user);

        model.addAttribute("restaurantName", restaurantName);
        return "review_create";
    }

    @GetMapping("/test")
    public String test(){
        return "test.html";
    }
}
