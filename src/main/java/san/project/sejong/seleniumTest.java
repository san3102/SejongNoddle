package san.project.sejong;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.List;

// SpringBoot Test용도
@Service
public class seleniumTest {
    //다운받은 드라이버 (프로그램) 명과 경로(경로 끝에 프로그램명.exe 작성)
    private WebDriver driver;
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "C:\\IT\\personalProjDB\\chromedriver.exe"; // 32-bit로 다운받은 .exe 파일경로 크롬과 드라이버의 버전확인 필수.
    private String base_url;

    //setProperty 메소드를 통해 프로그램명과 경로 받기
    //base_url에 스크래핑 할 브라우저 url 작성
    public void scraping2() {
        // Headless 모드로 Selenium을 실행 (백그라운드에서 웹페이지 로드)
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver(chromeOptions);
        base_url = "https://section.blog.naver.com/Search/Post.naver?pageNo=%d&rangeType=ALL&orderBy=recentdate&keyword=우성관"; // 크롤링을 할 대상 url

        try {

            int checkNum1 = 0;

            for (int pageNo = 1; pageNo <= 5; pageNo++) {
                String url = String.format(base_url, pageNo);
                driver.get(url);

                Thread.sleep(2000);

                List<WebElement> elementsWithTitleClass = driver.findElements(By.cssSelector(".desc_inner .title")); // desc_inner 클래스내의 title클래스 선택.
                List<WebElement> bodyText = driver.findElements(By.cssSelector(".desc .text"));

                for (int i = 0; i < elementsWithTitleClass.size(); i++) {
                    WebElement titleElement = elementsWithTitleClass.get(i);
                    WebElement textElement = bodyText.get(i);

                    System.out.println(checkNum1);
                    System.out.println("Title: " + titleElement.getText());
                    System.out.println("Text: " + textElement.getText());
                    checkNum1++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
