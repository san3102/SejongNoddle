package san.project.sejong.post;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import san.project.sejong.post.responseData.Item;
import san.project.sejong.post.responseData.ResponseData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreListService {
    private final String baseUrl = "http://apis.data.go.kr/5690000/sjRegularRestaurant/sj_00000760"; // 실제 API URL로 변경해야 합니다

    // RestTemplate를 주입받아 사용할 수 있도록 설정
    private final RestTemplate restTemplate;

    public List<Item> getRestaurantsWithNoodle(String branch) throws IOException {
        // 파라미터들을 정의
        String serviceKey = "C4%2FCSlu3ZqCtZXgPUWjooZMwNYvflRyQiFQrlxjE%2BzmNHAr3mdKOOZW6y5j6mUpo2%2F4IywOX1Mrx8KPBDZYJUw%3D%3D";
        String pageIndex = "1";
        String pageUnit = "5000";
        String dataTy = "json";
        String searchCondition = "induty";
        String searchKeyword = URLEncoder.encode("일반음식점", "UTF-8");

        // URL 생성
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("pageIndex", "UTF-8") + "=" + pageIndex);
        urlBuilder.append("&" + URLEncoder.encode("pageUnit", "UTF-8") + "=" + pageUnit);
        urlBuilder.append("&" + URLEncoder.encode("dataTy", "UTF-8") + "=" + dataTy);
        urlBuilder.append("&" + URLEncoder.encode("searchCondition", "UTF-8") + "=" + searchCondition);
        urlBuilder.append("&" + URLEncoder.encode("searchKeyword", "UTF-8") + "=" + searchKeyword);

        // JSON 응답을 기대하도록 Accept 헤더 설정
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

//        System.out.println("리스폰스 테스트 : " + urlBuilder.toString());

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

//        System.out.println("JSON 데이터 테스트 : " + responseStringBuilder.toString());

        // 응답 데이터 매핑
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseData responseData = objectMapper.readValue(responseStringBuilder.toString(), ResponseData.class);

        // API 리스폰스 JSON 응답구조에 맞게
//        ResponseEntity<ResponseData> response = restTemplate.getForEntity(urlBuilder.toString(), ResponseData.class);

        List<Item> restaurantsWithNoodle = new ArrayList<>();

        // 응답 데이터 처리
        if (responseData != null && responseData.getBody() != null && responseData.getBody().getItems() != null) {
            List<Item> items = responseData.getBody().getItems();

            for (Item item : items) {
                if (branch.equals("korean")) {
                    if (item.getMtlty() != null && item.getMtlty().contains("면") || item.getMtlty().contains("국수")) {
                        restaurantsWithNoodle.add(item);
                    }
                } else if (branch.equals("china")) {
                    if (item.getMtlty() != null && item.getMtlty().contains("짜장") || item.getMtlty().contains("짬뽕")) {
                        restaurantsWithNoodle.add(item);
                    }
                } else {
                    if (item.getMtlty() != null && item.getMtlty().contains("라면") || item.getMtlty().contains("라멘") || item.getMtlty().contains("파스타")
                            || item.getMtlty().contains("테이블레이") || item.getMtlty().contains("브리즈윅") || item.getMtlty().contains("스페이스")) {
                        restaurantsWithNoodle.add(item);
                    }
                }
            }
        }
        return restaurantsWithNoodle;
    }

    // 특정 상호명에 대한 음식점 정보만 반환
    public Item getRestaurantByName(String name, String branch) throws IOException {
        List<Item> restaurants = getRestaurantsWithNoodle(branch);

        for (Item restaurant : restaurants) {
            if (restaurant.getMtlty() != null && restaurant.getMtlty().equals(name)) {
                return restaurant;
            }
        }

        return null;  // 찾는 상호명에 해당하는 음식점이 없는 경우
    }

    public List<Item> getRestaurantsWithKeyword(String keyword) throws IOException {

        String[] keywords = keyword.split("[,\\s]+"); // 띄어쓰기와 쉼표로 분리

        // 파라미터들을 정의
        String serviceKey = "C4%2FCSlu3ZqCtZXgPUWjooZMwNYvflRyQiFQrlxjE%2BzmNHAr3mdKOOZW6y5j6mUpo2%2F4IywOX1Mrx8KPBDZYJUw%3D%3D";
        String pageIndex = "1";
        String pageUnit = "5000";
        String dataTy = "json";
        String searchCondition = "induty";
        String searchKeyword = URLEncoder.encode("일반음식점", "UTF-8");

        // URL 생성
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("pageIndex", "UTF-8") + "=" + pageIndex);
        urlBuilder.append("&" + URLEncoder.encode("pageUnit", "UTF-8") + "=" + pageUnit);
        urlBuilder.append("&" + URLEncoder.encode("dataTy", "UTF-8") + "=" + dataTy);
        urlBuilder.append("&" + URLEncoder.encode("searchCondition", "UTF-8") + "=" + searchCondition);
        urlBuilder.append("&" + URLEncoder.encode("searchKeyword", "UTF-8") + "=" + searchKeyword);

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

    // 특정 상호명에 대한 음식점 정보만 반환
    public Item getRestaurantBySearchName(String name) throws IOException {
        List<Item> restaurants = getRestaurantsWithKeyword(name);

        for (Item restaurant : restaurants) {
            if (restaurant.getMtlty() != null && restaurant.getMtlty().equals(name)) {
                return restaurant;
            }
        }

        return null;  // 찾는 상호명에 해당하는 음식점이 없는 경우
    }
}
