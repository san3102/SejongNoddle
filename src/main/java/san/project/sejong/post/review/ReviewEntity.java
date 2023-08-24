package san.project.sejong.post.review;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import san.project.sejong.user.UserEntity;

@Entity
@Getter
@Setter
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String restaurantName; // 어떤 음식점에 대한 리뷰인지 필터 용도

    @ManyToOne
    private UserEntity userEntity;
}
