package com.example;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.walkercrou.places.*;
import se.walkercrou.places.exception.GooglePlacesException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@RestController
public class RestoController {

    final String APIKEY = "AIzaSyAU3vOYPGy7eiTHA4aA53j43pbfqY_IxKQ";

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
                html += getDetails.getPlaceId() + "<br>";
                html += getDetails.getAccuracy() + "<br>";
                html += getDetails.getAddress() + "<br>";
                html += getDetails.getClient() + "<br>";
                html += getDetails.getGoogleUrl() + "<br>";
                html += getDetails.getHours() + "<br>";
                html += getDetails.getInternationalPhoneNumber() + "<br>";
                html += getDetails.getPhoneNumber() + "<br>";
                html += getDetails.getWebsite() + "<br>";
                html += getDetails.getStatus() + "<br>";
                break;
                /*System.out.println("ID: " + getDetails.getPlaceId());
                System.out.println("Nom: " + getDetails.getName());
                System.out.println("Téléphone: " + getDetails.getPhoneNumber());
                System.out.println("N° de téléphone International: " + getDetails.getInternationalPhoneNumber());
                System.out.println("Site web: " + getDetails.getWebsite());
                System.out.println("Toujours ouvert: " + getDetails.isAlwaysOpened());
                System.out.println("Status: " + getDetails.getStatus());
                System.out.println("Google Place URL: " + getDetails.getGoogleUrl());
                System.out.println("Prix: " + getDetails.getPrice());
                System.out.println("Adresse: " + getDetails.getAddress());
                System.out.println("Vicinity: " + getDetails.getVicinity());
                System.out.println("Reviews: " + getDetails.getReviews().size());
                System.out.println("Horaire:\n " + getDetails.getHours());*/
            }


        }

        return html;


}

    @RequestMapping(value = "/error", method = RequestMethod.TRACE)
    public String error() {
        return "error";
    }

    /*public RestoController(Map<String, String> map) {
        GooglePlaces client = new GooglePlaces(APIKEY);
        Scanner sop = new Scanner(System.in);

        boolean keepGoing = true;
        boolean keepGetDetails = true;
        while (keepGoing) {

            float lat = Float.parseFloat(map.get("lat"));
            float longi = Float.parseFloat(map.get("long"));

            try
            {
                List<Place> places = client.getNearbyPlaces(lat, longi, GooglePlacesInterface.MAXIMUM_RESULTS, Param.name("types").value(Types.TYPE_RESTAURANT), Param.name("radius").value(1000));

                *//*for (Place place : places) {

                    System.out.println(place.getName());

                }*//*

                for (int i = 0; i < places.size();i++)
                {
                    System.out.println(i + " : " +  places.get(i).getName());
                }

                Scanner sc = new Scanner(System.in);

                System.out.println("Choisissez un des lieux ci-dessus pour en obtenir les détails (0-1-2-3 etc...) : ");
                //On récupère le choix du resto
                int choice = sc.nextInt();
                //ON lance une requête GET sur ses détails et on les affiche
                Place getDetails = places.get(choice).getDetails();
                System.out.println("ID: " + getDetails.getPlaceId());
                System.out.println("Nom: " + getDetails.getName());
                System.out.println("Téléphone: " + getDetails.getPhoneNumber());
                System.out.println("N° de téléphone International: " + getDetails.getInternationalPhoneNumber());
                System.out.println("Site web: " + getDetails.getWebsite());
                System.out.println("Toujours ouvert: " + getDetails.isAlwaysOpened());
                System.out.println("Status: " + getDetails.getStatus());
                System.out.println("Google Place URL: " + getDetails.getGoogleUrl());
                System.out.println("Prix: " + getDetails.getPrice());
                System.out.println("Adresse: " + getDetails.getAddress());
                System.out.println("Vicinity: " + getDetails.getVicinity());
                System.out.println("Reviews: " + getDetails.getReviews().size());
                System.out.println("Horaire:\n " + getDetails.getHours());

            }
            catch (GooglePlacesException e)
            {
                System.out.println("Pas de résultat");
            }




                *//*System.out.println("Voulez vous entrer de nouvelles coordonées ? O/N");
                String input = sop.next();

                if (input.toLowerCase().equals("o"))
                {
                    keepGoing = true;
                }
                else
                {
                    keepGoing = false;
                }

        }
    }*/

}
