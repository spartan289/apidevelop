import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Scanner;
public class cobidvaccinetracker {
    public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(System.in);
    String date = "02-07-2021";
    String pincode = sc.nextLine();

    String host = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+pincode+"&date="+date;
    System.out.println(host);
    HttpResponse <JsonNode> response = Unirest.get(host)
            .asJson();

    System.out.println(response.getStatus());
    System.out.println(response.getBody());
    //below part is to show the json file in readable format
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jp = new JsonParser();
    JsonElement je = jp.parse(response.getBody().toString());
    String prettyJsonString = gson.toJson(je);
    System.out.println(prettyJsonString);
    Object obj = new JSONParser().parse(String.valueOf(response.getBody()));
    JSONObject jo = (JSONObject) obj;

    }

}
