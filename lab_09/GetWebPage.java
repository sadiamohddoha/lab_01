import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetWebPage {
    public static void main(String[] args) {
        String urlString = "https://www.java.com/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int code = conn.getResponseCode();
            System.out.println("HTTP Response Code: " + code);
            System.out.println("---- Page Content ----");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Error fetching page: " + e.getMessage());
        }
    }
}
