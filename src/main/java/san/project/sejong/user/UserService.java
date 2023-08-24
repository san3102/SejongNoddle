package san.project.sejong.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity create(String nickname, String email, String password){
        UserEntity user = new UserEntity();
        user.setUsername(email.toLowerCase()); // email은 항상 소문자로 저장
        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

    // 닉네임 중복 체크 메소드
    public boolean checkNicknameExists(String nickname) {
        // UserEntity 테이블에서 닉네임을 기준으로 중복 여부를 확인하는 쿼리를 실행합니다.
        // 존재하는 경우 true, 존재하지 않는 경우 false를 반환합니다.
        Optional<UserEntity> existingUser = userRepository.findByNickname(nickname);
        return existingUser.isPresent();
    }

    // 이메일 중복 체크 메소드
    public boolean checkEmailExists(String email) {
        Optional<UserEntity> existingUser = userRepository.findByUsername(email);
        return existingUser.isPresent();
    }

    public String getAuthUsername(String userEmail, Authentication authentication){
        if (authentication instanceof OAuth2AuthenticationToken) {
            // OAuth2 인증 사용자
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            // nickname 가져오는 로직 추가

            // naver, google은 else 부분 구문들로 email이 추출되었는데, kakao의 email추출 방법이 달라서 if-else로 조건문 하나 더 달아줌 (kakao else naver,google)
            if (oAuth2AuthenticationToken.getAuthorizedClientRegistrationId().equals("kakao")) {
                Map<String, Object> attributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
                Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

                // OAuth2 인증을 통한 사용자는 email값으로 DB 데이터와 비교 후, nickname에 해당 email과 동일한 유저의 nickname을 저장
                userEmail = (String) kakaoAccount.get("email");
            } else {
                // OAuth2 인증을 통한 사용자는 email값으로 DB 데이터와 비교 후, nickname에 해당 email과 동일한 유저의 nickname을 저장
                userEmail = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email").toString();
            }
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            // 로컬 인증 사용자
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
            // nickname 가져오는 로직 추가
            userEmail = usernamePasswordAuthenticationToken.getName();
        }

        return userEmail;
    }

    public UserEntity getUser(String username){
        Optional<UserEntity> user = this.userRepository.findByUsername(username);
        return user.get();
    }

    // 유저 닉네임 변경
    public void nickModify(UserEntity userEntity, String nickname){
        userEntity.setNickname(nickname);
        this.userRepository.save(userEntity);
    }
}
