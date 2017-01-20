package com.example;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.walkercrou.places.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

@RestController
public class RestoController {
    final static  String APIKEY = "AIzaSyAU3vOYPGy7eiTHA4aA53j43pbfqY_IxKQ";

    @RequestMapping(value= "/restaurant", method = RequestMethod.GET)
    public String restaurant(@RequestParam Map<String, String> requestParams) {
        String lat = requestParams.get("lat");
        String longi = requestParams.get("long");
        String html = "";

        if (lat != "" && longi != "") {
            float latitude = Float.parseFloat(lat);
            float longitude = Float.parseFloat(longi);
            GooglePlaces client = new GooglePlaces(APIKEY);
            List<Place> places = client.getNearbyPlaces(latitude, longitude, GooglePlacesInterface.MAXIMUM_RESULTS, Param.name("types").value(Types.TYPE_RESTAURANT), Param.name("radius").value(1000));

            for (int i = 0; i < places.size(); i++) {
                //System.out.println(i + " : " + places.get(i).getName());
                Place getDetails = places.get(i).getDetails();
                html += getDetails.getName() + "<br>";
                html += "<a href=restaurant/"+getDetails.getPlaceId()+ " </a>Détails ?<br>";
               /* html += getDetails.getAccuracy() + "<br>";
                html += getDetails.getAddress() + "<br>";
                html += getDetails.getClient() + "<br>";
                html += getDetails.getGoogleUrl() + "<br>";
                html += getDetails.getHours() + "<br>";
                html += getDetails.getInternationalPhoneNumber() + "<br>";
                html += getDetails.getPhoneNumber() + "<br>";
                html += getDetails.getWebsite() + "<br>";
                html += getDetails.getStatus() + "<br>";*/

                break;
            }
        }
        return html;
}

    @RequestMapping(value = "/error", method = RequestMethod.TRACE)
    public String error() {
        return "error";
    }
}
