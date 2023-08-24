package san.project.sejong.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import san.project.sejong.email.MailService;
import san.project.sejong.post.review.ReviewEntity;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MailService mailService;
    @GetMapping("/user/signup")
    public String signup(UserCreateForm userCreateForm){
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup";
        } else {
            userService.create(userCreateForm.getNickname(), userCreateForm.getEmail(), userCreateForm.getPassword1());
        }
        return "redirect:/";
    }

    @PostMapping("/user/signup/mailConfirm")
    @ResponseBody
    public String mailConfirm(@RequestParam("email") String email) throws Exception{
        String code = mailService.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
    }

    @PostMapping("/user/signup/checknickname") // 닉네임 중복검사
    @ResponseBody
    public String checkNick(@RequestParam("id") String id){
        boolean isNickExist = userService.checkNicknameExists(id);
        return isNickExist ? "exist" : "available";
    }

    @PostMapping("/user/signup/checkemail")
    @ResponseBody
    public String checkEmail(@RequestParam("id") String id){
        boolean isEmailExist = userService.checkEmailExists(id.toLowerCase());  // email을 소문자로 변경해서 비교
        return isEmailExist ? "exist" : "available";
    }

    @GetMapping("/user/login")
    public String newLogin() {
        return "login";
    }

    @GetMapping("/user/{username}/mypage")
    public String mypage(@PathVariable String username, Authentication authentication, Model model){
        String userEmail = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
        }
        UserEntity user = this.userService.getUser(userEmail);
        List<ReviewEntity> reviewEntityList = user.getReviewEntity();

        if(user.getUsername().equals(username)){
            // 로그인한 유저 정보를 user 객체로 전달
            model.addAttribute("user", user);
            model.addAttribute("reviewList", reviewEntityList);
            return "mypage";
        } else {
            return "error";
        }
    }

    // 유저 닉네임 변경 get, post 매핑
    @GetMapping("/user/nickname_change")
    public String nickChange(UserNicknameModifyForm userNicknameModifyForm, Authentication authentication){
        String userEmail = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
        }
        UserEntity user = this.userService.getUser(userEmail);
        userNicknameModifyForm.setNickname(user.getNickname());

        return "nickname_change";
    }

    @PostMapping("/user/nickname_change")
    public String nickChangePost(@Valid UserNicknameModifyForm userNicknameModifyForm, Authentication authentication){
        String userEmail = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // UserService에서 로그인 유저 닉네임 반환하는 메소드 호출
            userEmail = userService.getAuthUsername(userEmail, authentication);
        }
        UserEntity user = this.userService.getUser(userEmail);
        userService.nickModify(user, userNicknameModifyForm.getNickname());

        return "redirect:/user/" + user.getUsername() + "/mypage";
    }
}
