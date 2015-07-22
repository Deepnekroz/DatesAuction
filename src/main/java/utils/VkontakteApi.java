package utils;

import model.User;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by deepnekro on 22.07.15.
 */
public class VkontakteApi {

    public static final String[] APP_SCOPES = { "friends", "groups", "email" };
    private static Resource resource = new ClassPathResource("/application.properties");
    private static Properties props;
    static{
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        }catch (Exception e){e.printStackTrace();}
    }

    public static String getAuthUri() {
        return "http://oauth.vk.com/authorize?" +
                "client_id=" + props.getProperty("vk.app.id") + "&" +
                "scope=" + StringUtils.join(APP_SCOPES, ",") + "&" +
                "redirect_uri=" + props.getProperty("vk.app.response_uri") + "&" +
                "response_type=code";
    }

    public static User getUserByToken(String token){
        User user=null;
        try {
            String url = "https://api.vk.com/method/users.get?fields=sex,city,photo_200,bdate&access_token=" + token;
            String answer = HttpWorker.sendGetForResult(url);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject)((JSONArray)(((JSONObject) parser.parse(answer)).get("response"))).get(0);

            SimpleDateFormat sdf = new SimpleDateFormat("d.M.yyyy");
            Date bday = sdf.parse((String) json.get("bdate"));
            user = new User();
            user.setBday(bday);

            String city = ((String)((JSONObject)((JSONArray) ((JSONObject) parser.parse(HttpWorker.sendGetForResult("https://api.vk.com/method/database.getCitiesById?city_ids=" + Long.toString((Long) json.get("city"))))).get("response")).get(0)).get("name"));
            user.setCity(city);
            user.setVkId((Long) json.get("uid"));
            user.setName((String) json.get("first_name"));
            user.setLastname((String) json.get("last_name"));
            user.setGender(Byte.parseByte(Long.toString((Long)json.get("sex"))));

        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }



    public static String getToken(String code) {
        try {
            String urlForToken = "https://oauth.vk.com/access_token?client_id=" + props.getProperty("vk.app.id") +
                    "&client_secret=" + props.getProperty("vk.app.key") + "&code=" + code + "&redirect_uri=" + props.getProperty("vk.app.response_uri");
            JSONParser parser = new JSONParser();
            String answer = HttpWorker.sendGetForResult(urlForToken);
            JSONObject json = (JSONObject) parser.parse(answer);
            if (json.containsKey("error"))
                return json.toString();
            return (String) json.get("access_token");
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }

    }
}
