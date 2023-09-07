package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is used to get the weather of the place where the player is located
 * It uses the API of open-meteo.com
 */
public class Weather {

    // metodo per restituire a terminal l'altezza in metri e se e' giorno o notte

    /**
     * This method returns the height in meters and if it is day or night
     */
    public static String getWeather() {
        String weather = "";
        try {
            URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=27.2276&longitude=88.586&hourly=is_day&current_weather=true&timezone=Asia%2FTokyo&forecast_days=1");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) content.append(inputLine);
            in.close();
            con.disconnect();
            String elevation = content.toString().replaceAll(".*\"elevation\":(\\d+\\.?\\d*).*", "$1");
            weather += "Ti trovi ad un altezza di : " + elevation + " metri\n";
            // se e' giorno o notte
            String is_day = content.toString().replaceAll(".*\"is_day\":(\\d+).*", "$1");
            if (is_day.equals("1")) {
                weather += "Ed e' giorno\n";
            } else {
                weather += "Ed e' notte\n";
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return weather;
    }
}