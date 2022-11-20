import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Rest {
    public static void main(String[] args) {

        RestTemplate rest= new RestTemplate();
        String url1 = "http://localhost:8080/sensors/registration";
        String url2 = "http://localhost:8080/measurements/add";
        String url3 = "http://localhost:8080/measurements";
        String url4 = "http://localhost:8080/measurements/rainyDaysCount";

        Map<String,String> sensor = new HashMap<>();
        sensor.put("name","alaska");

        HttpEntity<Map<String,String>> request = new HttpEntity<>(sensor);

        System.out.println("First request. Replay: " + rest.postForObject(url1,request,String.class));
        Map<String,String> measurements = new HashMap<>();
        measurements.put("sensor","alaska");
        measurements.put("raining","true");
        for(int i = 0;i<1000;i++ ){
            String value = String.valueOf(random());
            measurements.put("value",value);
            HttpEntity<Map<String,String>> request2 = new HttpEntity<>(measurements);
            System.out.println("Second request. Replay: " + rest.postForObject(url2,request2,String.class));
        }

        System.out.println("Measurements: " + rest.getForObject(url3,String.class));
        System.out.println("Rainy days: " + rest.getForObject(url4,String.class));


    }
    public static int random() {
        int max = 100;
        int min = -100;
        max -= min;
        return (int) (Math.random() * ++max) + min;

    }
}
