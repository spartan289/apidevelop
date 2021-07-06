import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class cobidvaccinetracker {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String date = "06-07-2021";
    String pincode = sc.nextLine();
    try {

        String host = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=" + pincode + "&date=" + date;
        System.out.println(host);
        HttpResponse<JsonNode> response = Unirest.get(host)
                .asJson();

        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        //below part is to show the json file in readable format
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);
        String readLine;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(response.getRawBody()));
        StringBuilder responses = new StringBuilder();
        while ((readLine = in.readLine()) != null) {
            responses.append(readLine);
        }//print result
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(responses.toString());
        JSONArray responseArr = (JSONArray) json.get("sessions");
        for (Object object : responseArr) {
            JSONObject jsonObj = (JSONObject) object;
            Long pincodel = (Long) jsonObj.get("pincode");
            String name = (String) jsonObj.get("name");
            String address = jsonObj.get("address") + ", " + jsonObj.get("district_name") + ", " +
                    jsonObj.get("state_name") + " " + pincodel;
            String fee_type = (String) jsonObj.get("fee_type");
            System.out.println(pincodel);
            System.out.println(name);
            System.out.println(address);
            System.out.println(fee_type);
        }

    } catch (UnirestException | JsonSyntaxException | IOException | ParseException e) {
        e.printStackTrace();
    }

    }



}