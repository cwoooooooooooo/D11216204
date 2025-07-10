import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherAPI {
    public static void main(String[] args) {
        try {
            String apiKey = "19af989e111a07a6ed10f4011f9e5d67"; // 換成你自己的 key
            String city = "Taipei";

            String urlStr = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // 解析 JSON
            JSONObject obj = new JSONObject(content.toString());

            // 城市名稱
            String cityName = obj.optString("name", "未知城市");
            System.out.println("城市: " + cityName);

            JSONObject main = obj.optJSONObject("main");
            if (main != null) {
                double tempK = main.optDouble("temp", Double.NaN);
                if (!Double.isNaN(tempK)) {
                    double tempC = tempK - 273.15;
                    System.out.printf("溫度: %.2f °C\n", tempC);
                }
                System.out.println("濕度: " + main.optInt("humidity", -1) + "%");
                System.out.println("氣壓: " + main.optInt("pressure", -1) + " hPa");
            }

            JSONArray weatherArray = obj.optJSONArray("weather");
            if (weatherArray != null && weatherArray.length() > 0) {
                JSONObject weather = weatherArray.getJSONObject(0);
                System.out.println("天氣狀況: " + weather.optString("main", "無資料"));
                System.out.println("詳細描述: " + weather.optString("description", "無資料"));
            }

            JSONObject wind = obj.optJSONObject("wind");
            if (wind != null) {
                System.out.println("風速: " + wind.optDouble("speed", 0) + " m/s");
                System.out.println("風向: " + wind.optInt("deg", 0) + "°");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
