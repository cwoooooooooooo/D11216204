import requests
API_KEY = "19af989e111a07a6ed10f4011f9e5d67"
city = "Taipei"
url = f"https://api.openweathermap.org/data/2.5/weather?q={city}&appid={API_KEY}&units=metric"
response = requests.get(url)
data = response.json()
print("城市:", data['name'])
print("溫度:", data['main']['temp'], "°C")
print("天氣:", data['weather'][0]['description'])