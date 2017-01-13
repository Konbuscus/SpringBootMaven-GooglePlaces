package com.example;
import jdk.internal.util.xml.impl.Input;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.tomcat.util.modeler.Util;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.*;
import se.walkercrou.places.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

@RestController
/**
 * Created by HBxAa on 13/01/2017.
 */
public class DetailController {


    @RequestMapping(value="/restaurant/{id}", method=RequestMethod.GET)
    public String details(@PathVariable("id") String id)
    {
        StringBuilder result = new StringBuilder();
        InputStream stream = null;
        try{
            String requestUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?placeId="+id+"&radius=500&types=food&name=cruise&key="+RestoController.APIKEY+"";
            URL TheRequest = new URL(requestUrl);
            URLConnection connection = TheRequest.openConnection();

            stream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

        }
        catch(IOException e){

        }

        return result.toString();

    }

}
