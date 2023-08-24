package san.project.sejong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;


@SpringBootTest
class AppTest {

	@Autowired
	seleniumTest seleniumTest;

	@Test
	void scraping(){
		seleniumTest.scraping2();
	}

//	@Test
//	// 데이터 응답이 잘 오는지 확인
//	public static void main(String[] args) throws IOException {
//		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/5690000/sjRegularRestaurant/sj_00000760"); /*URL*/
//		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=C4%2FCSlu3ZqCtZXgPUWjooZMwNYvflRyQiFQrlxjE%2BzmNHAr3mdKOOZW6y5j6mUpo2%2F4IywOX1Mrx8KPBDZYJUw%3D%3D"); /*Service Key*/
//		urlBuilder.append("&" + URLEncoder.encode("pageIndex", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
//		urlBuilder.append("&" + URLEncoder.encode("pageUnit", "UTF-8") + "=" + URLEncoder.encode("5000", "UTF-8")); /*한 페이지 결과 수 기본20건*/
//		urlBuilder.append("&" + URLEncoder.encode("dataTy", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*자료형태 : xml,json,excel/ 기본값 json*/
//		urlBuilder.append("&" + URLEncoder.encode("searchCondition", "UTF-8") + "=" + URLEncoder.encode("induty", "UTF-8")); /*값이 없는 경우 모든조건*/
//		urlBuilder.append("&" + URLEncoder.encode("searchKeyword", "UTF-8") + "=" + URLEncoder.encode("일반음식점", "UTF-8")); /*값이 없는 경우 검색안함*/
//		URL url = new URL(urlBuilder.toString());
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());
//
//		BufferedReader rd;
//		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		} else {
//			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		}
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = rd.readLine()) != null) {
//			sb.append(line);
//		}
//		rd.close();
//		conn.disconnect();
//		System.out.println("JSON : " + sb.toString());
//	}
}
