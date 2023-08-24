package san.project.sejong.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import san.project.sejong.post.review.ReviewEntity;

import java.util.List;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username; // email을 아이디로 가져가 쓸 것이기 때문에 스프링 시큐리티 인증부분에서 편하게 쓸 수 있게 username으로 필드 선언
    
    @Column(unique = true)
    private String nickname;

    private String password;

    private String provider; // Local(로컬유져) kakao(카카오로그인) google(구글로그인) naver(네이버로그인) 4가지로 구분(중요한 구분 요소는 아님)

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.GUEST; // 비회원들이 이용할때 기본적으로 GUEST권한을 default로 놓음

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
    private List<ReviewEntity> reviewEntity;

    public UserEntity update(String provider){   // 동일한 email의 유저가 다른 플랫폼의 소셜로그인 시 유저 속성 update를 위한 메소드
        this.provider = provider;
        return this;
    }
    public String getRoleValue(){
        return this.role.getValue();    // UserRole 클래스에서 Value속성을 활용하기 위함
    }
    public UserEntity(){}
    @Builder
    public UserEntity(int id, String email, String nickname, UserRole role, String provider){
        this.id = id;
        this.username = email;
        this.nickname = nickname;
        this.role = role;
        this.provider = provider;
    }
}
