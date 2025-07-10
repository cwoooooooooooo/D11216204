String apiKey = "19af989e111a07a6ed10f4011f9e5d67";
String location = "Taipei";
String urlStr = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey;
URL url = new URL(urlStr);
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
if (conn.getResponseCode() == 200) {
BufferedReader in = new BufferedReader(new
InputStreamReader(conn.getInputStream()));
String inputLine, content = "";
while ((inputLine = in.readLine()) != null) { content += inputLine; }
in.close();
JSONObject obj = new JSONObject(content);
System.out.println("城市:" + obj.getString("name"));
System.out.println("溫度:" + obj.getJSONObject("main").getDouble("temp"));
} else {
System.out.println("請求失敗,狀態碼:" + conn.getResponseCode());
}