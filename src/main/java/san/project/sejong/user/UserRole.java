package san.project.sejong.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ADMIN("ROLE_ADMIN", "admin", "Admin"),
    USER("ROLE_USER", "user", "User"),
    GUEST("ROLE_GUEST", "guest", "Guest");

    private final String value;     // 스프링 시큐리티에서 권한 확인에 사용
    private final String key;       // 주로 권한을 표시할 때 사용
    private final String title;     // 주로 권한을 사용자에게 표시할 떄 사용
}
