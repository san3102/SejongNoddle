# Sejong Noddle

![개인프로젝트 메인](https://github.com/san3102/personalProj/assets/103571921/79a5ce3c-2f78-4a5a-b20d-0fe8feeb37be)


### 세종시 면요리 음식점 정보 사이트
> 개발일지 : [Velog][sejong] <br/>
> 공공데이터 출처 : [data.go.kr] <br/>
> 크롤링 데이터 : [naver blog search]

[sejong]: https://velog.io/@san3102/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EA%B0%9C%EC%9D%B8%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-3omplufn
[data.go.kr]: https://www.data.go.kr/data/15098456/openapi.do
[naver blog search]: https://section.blog.naver.com/BlogHome.naver?directoryNo=0&currentPage=1&groupId=0

#### 기간
2023.08.07 ~ 2023.08.15 <br/>

#### Skills
<div>
        <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
        <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
        <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
        <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
        <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
        <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
        <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
        <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
        <img src="https://img.shields.io/badge/selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white">  
</div>

#### 형상관리
<div>
        <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
</div>

#### 구현 목록

|구분|기능|구현|
|------|---|---|
|회원가입|이메일 인증|O|
||소셜 로그인|O|
|회원|닉네임 변경|O|
|음식점 목록|카테고리 분류|O|
||보기 방식|O|
||음식점 상세|O|
||음식점 블로그 리뷰|O|
|리뷰|리뷰 작성|O|
||리뷰 수정, 삭제|O|
|검색|다중 검색|O|

<br/><br/>

## Description

### 1. ERD Diagram

<img src="https://github.com/san3102/personalProj/assets/103571921/1e637532-b406-403d-a308-60d2e724ee89" width="550px" height="500px"></img><br/>

### 2. Sequence Diagram
#### 회원가입

<img src="https://github.com/san3102/personalProj/assets/103571921/2a97db4d-cc96-42d8-ad83-9923d4eaee82" width="800px" height="300px"></img><br/>

> **1. JavaScript 및 jQuery 사용** : 사용자가 닉네임, 비밀번호, 이메일 등의 정보를 입력받아 jQuery를 사용하여 이벤트 처리와 DOM 조작을 수행합니다. <br/><br/>
> **2. 유효성 검사 및 상태 관리** : 사용자가 입력한 닉네임, 비밀번호, 이메일 등의 데이터를 실시간으로 유효성을 검사합니다. 예를 들어, 이메일과 닉네임은 정규식을 사용하여 형식이나 중복 여부를 확인하고, 비밀번호는 여러 조건을 만족하는지 확인합니다. <br/><br/>
> **3. AJAX 요청** : 입력 데이터의 중복 여부를 확인하기 위해 AJAX를 사용하여 서버에 요청을 보냅니다. 중복된 닉네임, 이메일이 이미 존재하는지 확인합니다. <br/><br/>
> **4. Controller** <br/>
> * 회원가입 데이터를 Ajax POST 요청으로 받아 유효성을 검사하고, 에러가 없다면 회원 데이터를 생성합니다.
> * 이메일 인증 코드를 생성하여 반환합니다.
> * 아이디, 닉네임, 이메일 중복 여부를 확인하는 요청을 처리하고 결과를 반환합니다.
>
> **5. Service** <br/>
> * 회원가입 데이터를 사용하여 회원 정보를 생성하고 암호화된 비밀번호를 저장합니다.
> * JPA Repository를 활용해 데이터베이스와의 상호작용을 처리합니다. 닉네임, 이메일의 중복 여부를 확인하는 로직을 수행합니다.

<br/><br/>

#### 리뷰 등록

<img src="https://github.com/san3102/personalProj/assets/103571921/50987d5d-d7f7-46ce-b554-5c39be889256" width="850px" height="300px"></img><br/>

> **1. HTML** : 리뷰 작성 클릭 시 Thymeleaf 템플릿 엔진을 사용하여 리뷰 작성 페이지로 이동한 후, 리뷰작성 템플릿에서  Form 제출 시 작성한 제목, 내용 input Data를 서버로 전달합니다. <br/><br/>
> **2. Controller**
> * GET 요청을 처리하며, 사용자 인증 여부를 확인하고, 로그인된 사용자의 정보를 가져와 리뷰 작성 템플릿을 반환합니다.
> * Form 제출 시 POST 요청을 처리하며 title, content, restaurantName과 사용자 정보를 활용하여 리뷰를 생성하고 저장합니다.
> * 생성된 리뷰가 속한 식당 상세 페이지로 리다이렉트합니다.
>
> **3. Service**
> * JPA Repository를 활용해 데이터베이스와의 상호작용을 처리합니다.
> * Input Data로 넘어온 title, content, restaurantName과 사용자 정보를 활용하여 ReviewEntity를 생성하고, 해당 리뷰를 저장합니다.

<br/><br/>

### 3. 주요 코드 설명

##### 공공데이터 JSON 파싱
```java
StoreListService.java

@Service
@RequiredArgsConstructor
public class StoreListService {
    private final String baseUrl = " "; // 실제 API URL로 변경해야 합니다
    ...
        public List<Item> getRestaurantsWithKeyword(String keyword) throws IOException {

        String[] keywords = keyword.split("[,\\s]+"); // 띄어쓰기와 쉼표로 분리

        // 파라미터들을 정의
        String serviceKey = " ";		// 개인 API 인증키 입력부분
        String pageIndex = "1";			// 페이지 번호
        String pageUnit = "5000";		// 한 페이지 결과 수
        String dataTy = "json";			// 자료 형태
        String searchCondition = "induty";		// 검색 조건
        String searchKeyword = URLEncoder.encode("일반음식점", "UTF-8");		// 검색어

        // URL 생성
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("pageIndex", "UTF-8") + "=" + pageIndex);
        urlBuilder.append("&" + URLEncoder.encode("pageUnit", "UTF-8") + "=" + pageUnit);
        urlBuilder.append("&" + URLEncoder.encode("dataTy", "UTF-8") + "=" + dataTy);
        urlBuilder.append("&" + URLEncoder.encode("searchCondition", "UTF-8") + "=" + searchCondition);
        urlBuilder.append("&" + URLEncoder.encode("searchKeyword", "UTF-8") + "=" + searchKeyword);
```
> * 정의해놓은 파라미터들을 사용하여 urlBuilder라는 문자열 빌더를 사용하여 파라미터들을 하나씩 추가하면서 URL을 구성합니다.

```java
        // HTTP 요청 보내기
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
```
> * 생성된 URL을 기반으로 HttpURLConnection을 이용해 GET 요청을 보냅니다.

```java
        // 응답 데이터 읽기
        StringBuilder responseStringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                responseStringBuilder.append(line);
            }
        }
```
> * responseStringBuilder라는 새로운 StringBuilder 객체를 생성합니다. 이 객체는 문자열을 조합하기 위해 사용되며, 응답 데이터를 한 줄씩 읽어와서 추가할 것입니다.
> * BufferedReader와 InputStreamReader를 사용하여 연결된 스트림에서 데이터를 읽습니다. BufferedReader는 문자열을 읽어오는 버퍼링된 리더입니다.
> * reader.readLine()은 스트림에서 한 줄을 읽어오며, line에는 읽어온 문자열이 저장됩니다.
> * 각 줄마다 responseStringBuilder에 해당 줄을 추가하여 전체 응답 데이터를 문자열로 조합합니다.

```java
        // 응답 데이터 매핑
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseData responseData = objectMapper.readValue(responseStringBuilder.toString(), ResponseData.class);

        List<Item> restaurantsWithSearch = new ArrayList<>();

        // 응답 데이터 처리
        if (responseData != null && responseData.getBody() != null && responseData.getBody().getItems() != null) {
            List<Item> items = responseData.getBody().getItems();

            for (Item item : items) {
                for(String keywordInfo : keywords){
                    if(item.getMtlty() != null && item.getMtlty().contains(keywordInfo)){
                        restaurantsWithSearch.add(item);
                    }
                }
            }
        }
        return restaurantsWithSearch;
    }
    ...
```
> * ObjectMapper를 사용하여 응답 데이터를 ResponseData 클래스 형식으로 매핑합니다. (응답 데이터 구조화 시켜 매핑)
> * 응답 데이터를 처리하여 특정 키워드와 일치하는 식당 정보를 추출합니다. 추출된 정보는 restaurantsWithSearch 리스트에 저장되며, 이 리스트가 최종적으로 반환됩니다.

* 응답 데이터를 받기위한 클래스 구조화
```java
// API 응답의 전체 데이터를 나타냅니다. 이 클래스는 Header와 Body를 포함하고 있습니다. 
ResponseData.java

public class ResponseData {
    private Header header; // 응답 헤더 정보
    private Body body; // 응답 바디 정보
}

// 응답 헤더 정보를 나타냅니다. 이 정보에는 API 호출 결과에 대한 메타데이터가 포함되어 있습니다.
Header.java

public class Header {
    private String resultCode; // 결과 코드
    private String resultMsg; // 결과 메시지
    private int totalCount; // 전체 결과 수
    private int pageIndex; // 페이지 번호
    private int pageUnit; // 한 페이지 결과 수
    private String searchCondition; // 검색 조건
    private String searchKeyword; // 검색 키워드
}

// 응답 바디 정보를 나타냅니다. Body에는 Item 객체들의 리스트인 items가 포함됩니다. 이 리스트는 API 응답에서 제공하는 실제 데이터를 담고 있습니다.
Body.java

public class Body {
    private List<Item> items; // 실제 데이터 리스트
}

// 각각의 식당 정보를 나타냅니다. 이 클래스에는 식당의 여러 속성들이 포함되어 있습니다.
Item.java

public class Item {
    private String induty; // 업종
    private String psnPrmisnYmd; // 허가 일자
    private String mtlty; // 상호명
    private String roadNmAddr; // 도로명 주소
    private String addr; // 지번 주소
    private String telno; // 전화번호
}
```
> * 위의 클래스들은 API 서버로부터 받은 응답 데이터를 객체로 매핑하기 위해 사용됩니다.
> * ResponseData는 응답 전체를 담는 컨테이너 역할을 하며, Header는 응답 헤더 메타데이터를, Body는 실제 데이터를 담는 역할을 합니다. Item 클래스는 각 식당의 세부 정보를 담습니다.
> * 이 클래스들은 JSON 데이터를 자바 객체로 변환하거나, 자바 객체를 JSON으로 변환하는 데 사용되는 Jackson 라이브러리의 ObjectMapper와 함께 사용됩니다.
> * API 응답 데이터를 위 클래스 구조에 맞게 매핑함으로써 데이터를 쉽게 사용하고 처리할 수 있게 됩니다.

##### Selenium 활용 웹 크롤링
```java
public class CrawlingData {
    private String title;
    private String content;
    private String link;
    private String restaurantName;
}
```
> * 크롤링한 데이터를 담을 데이터 모델 클래스를 만들고 저장에 사용합니다.

```java
@Component
public class WebCrawlerUsingSelenium {
    private WebDriver driver;
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = " "; // Chromedriver.exe가 실제 위치하고 있는 경로, 크롬과 드라이버의 버전이 같은지 확인필수
    private String base_url;
    private List<CrawlingData> crawlingDataList = new ArrayList<>();
```
> * WebDriver driver: Selenium을 사용하여 웹 브라우저를 제어하기 위한 WebDriver 객체입니다.
> * crawlingDataList: 크롤링한 데이터를 저장할 리스트입니다.

```java
	public void scraping(String restaurantName) {
	    ChromeOptions chromeOptions = new ChromeOptions();
	    chromeOptions.setHeadless(true);	// 
	
	    System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	    driver = new ChromeDriver(chromeOptions);
	    base_url = "https://section.blog.naver.com/Search/Post.naver?pageNo=%d&rangeType=ALL&orderBy=recentdate&keyword=세종시 %s"; // 크롤링을 할 대상 url
	
	    try {
	        for (int pageNo = 1; pageNo < 2; pageNo++) {
	            String url = String.format(base_url, pageNo, restaurantName);
	            driver.get(url);
	            Thread.sleep(2000);
	
	            List<WebElement> elementsWithTitleClass = driver.findElements(By.cssSelector(".desc_inner")); // desc_inner 클래스내의 title클래스 선택.
	            List<WebElement> titleLinks = driver.findElements(By.cssSelector(".desc a")); // desc_inner 클래스내의 title 클래스 내부의 a 태그 선택.
	            List<WebElement> bodyText = driver.findElements(By.cssSelector(".desc .text")); // dsec 클래스내의 text 클래스 선택
	
	            for (int i = 0; i < elementsWithTitleClass.size(); i++) {
	                WebElement titleElement = elementsWithTitleClass.get(i);
	                WebElement titleLinkElement = titleLinks.get(i);
	                WebElement textElement = bodyText.get(i);
	
	                CrawlingData crawlingData = new CrawlingData();
	                crawlingData.setRestaurantName(restaurantName);
	                crawlingData.setTitle(titleElement.getText());
	                crawlingData.setLink(titleLinkElement.getAttribute("href"));
	                crawlingData.setContent(textElement.getText());
	
	                crawlingDataList.add(crawlingData);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        driver.quit();
	    }
	}
```
> * ChromeOptions를 사용하여 Headless모드(background 실행)로 Selenium을 실행합니다.
> * 여러 페이지를 크롤링하기 위해 for 루프를 통해 페이지별로 게시물을 크롤링합니다.
> * driver.findElements 메소드로 웹 요소(태그)들을 검색하고, 그에 따른 정보들을 CrawlingData 객체에 저장하여 리스트에 추가합니다.
> * 예외 처리 블록에서는 오류 발생 시 출력하고, finally 블록에서는 WebDriver를 종료합니다.

```java
public List<CrawlingData> getCrawlingDataList(String restaurantName) {
    List<CrawlingData> filteredList = new ArrayList<>();

    for (CrawlingData data : crawlingDataList) {
        if (data.getRestaurantName().equals(restaurantName)) {
            filteredList.add(data);
        }
    }

    return filteredList;
}
```
> * getCrawlingDataList 메소드는 특정 식당 이름과 관련된 데이터만 필터링하여 리스트로 반환합니다.
> * crawlingDataList를 반복하여 필터링 작업을 수행하고, 해당 식당 이름(data.getRestaurantName().equals(restaurantName))과 일치하는 데이터만 filteredList에 추가하여 반환합니다.

### 4. 결과물
<div display="inline">
        <img src="https://github.com/san3102/personalProj/assets/103571921/6889e7cb-3eb6-4404-8d72-73e8e08b27a3" width="400px" height="200px" align></img>
        <img src="https://github.com/san3102/personalProj/assets/103571921/89c8cb7b-05ae-45ed-b02b-ebdf3523cc20" width="400px" height="200px"></img>
        <img src="https://github.com/san3102/personalProj/assets/103571921/683f7397-f26b-4bbe-8068-51262d043734" width="400px" height="200px"></img>
        <img src="https://github.com/san3102/personalProj/assets/103571921/0c311f8d-9e2a-47d2-aa93-cad52cac6bfc" width="400px" height="200px"></img>
        <img src="https://github.com/san3102/personalProj/assets/103571921/a811b5ca-1521-4f56-8c7b-44f05fcdcc3a" width="400px" height="200px"></img>
</div>

<br/>

### 5. Trouble Shooting

#### 문제 현상

공공데이터 파싱 시에 JSON 형태로 파싱해 원하는 데이터를 얻으려고 했지만 계속 xml 형태로 데이터가 넘어오는 현상 발생.

#### 원인 분석

```java
        // JSON 응답을 기대하도록 Accept 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        
        // API 리스폰스 JSON 응답구조에 맞게
        ResponseEntity<ResponseData> response = restTemplate.getForEntity(urlBuilder.toString(), ResponseData.class);
```
API 요청 시 Accept 헤더 설정 부분도 서버에서 JSON형식으로 받아올 수 있게 설정해봤지만 계속되는 xml 응답.. 계속되는 xml 응답으로 인한 에러로 여러 검색 결과, 원인으로 2가지 가능성을 추측
1. API 응답 데이터 형식과 HttpMessageConverter가 일치하지 않는 경우
2. API 응답 데이터의 Content-Type과 Accept 헤더가 일치하지 않는 경우

#### 문제 해결을 위한 시도
```java
1번 추측을 위해
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }
}
해당 설정 클래스에 명시적으로 MappingJackson2HttpMessageConverter를 추가해봄

2번 추측을 위해
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
Content-type 헤더를 json으로 받아오는 코드도 추가해봄
```
RestTemplate를 사용하여 API 요청을 보낼 때 문제가 발생, getForEntity 메서드가 응답 데이터를 매핑하는 부분에서 문제 발생의 해결책을 찾지 못하고 결국 restTeamplate 사용안함.

```java
        // HTTP 요청 보내기
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // 응답 데이터 읽기
        StringBuilder responseStringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                responseStringBuilder.append(line);
            }
        }

        // 응답 데이터 매핑
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseData responseData = objectMapper.readValue(responseStringBuilder.toString(), ResponseData.class);
```
'HttpURLConnection'을 사용하여 응답 데이터를 직접 받아오고, 그 데이터를 ObjectMapper를 사용하여 매핑하는 방식으로 진행하여 해결.

#### 소감

팀 프로젝트를 마치고 개인 프로젝트에 돌입하면서, 개발의 자유로움을 느낄 수 있었습니다. 혼자 작업하면서 코드 구성과 개발 방향을 내 취향에 맞게 조율하는 장점을 경험했습니다. 그러나 팀 프로젝트에서는 다양한 의견을 통해 생각치 못한 접근법을 배울 수 있었는데, 이런 다양한 아이디어와 협업의 가치를 혼자 작업하는 동안에는 놓치게 되었습니다.

또한, 시간 제약 아래 혼자 개발을 진행하다 프로젝트 규모를 더 키우는 데 어려움을 겪었습니다.

이러한 경험을 통해 혼자서의 개발 역량 뿐만 아니라 팀워크와 협업 능력도 중요하다는 것을 다시 한 번 깨닫게 되었습니다. 
앞으로는 이러한 교훈을 살려 더 나은 프로젝트를 진행하고 발전시켜 나가고자 합니다.
