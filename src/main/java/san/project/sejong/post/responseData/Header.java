package san.project.sejong.post.responseData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Header {
    private String resultCode;
    private String resultMsg;
    private int totalCount;
    private int pageIndex;
    private int pageUnit;
    private String searchCondition;
    private String searchKeyword;
}
