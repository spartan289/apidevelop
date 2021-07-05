import java.net.URLEncoder;//imported urlencoder for prepraring parasms to be sent
import com.mashape.unirest.http.HttpResponse;//for handling a response
import com.mashape.unirest.http.JsonNode;//assigning JSON type to it
import com.mashape.unirest.http.Unirest;//making a get request
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class apiimdb {
    public static void main(String[] args) throws Exception {
        String host = "https://movie-database-imdb-alternative.p.rapidapi.com/";
        String charset = "UTF-8";
        //headers for request
        String x_rapidapi_host = "movie-database-imdb-alternative.p.rapidapi.com";
        String x_rapidapi_key = "1425bd98a3msh3fe9b75bb11f1dap1ce0c4jsn703ff4e7293c";
        String s = "pulp";
        //Format query for preventing encoding problems
        String query = String.format("s=%s",URLEncoder.encode(s,charset));
        HttpResponse <JsonNode> response = Unirest.get(host+"?"+query)
                .header("x-rapidapi-key",x_rapidapi_key)
                .header("x-rapidapi-host",x_rapidapi_host)
                .asJson();
            System.out.println(response.getStatus());
            System.out.println(response.getHeaders().get("Content-Type"));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);

    }
}
