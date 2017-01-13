package com.example;
import org.json.JSONObject;
import se.walkercrou.places.*;
import se.walkercrou.places.exception.GooglePlacesException;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by HBxAa on 16/12/2016.
 */
public class RestoController {


    final String APIKEY = "AIzaSyAU3vOYPGy7eiTHA4aA53j43pbfqY_IxKQ";

    public  RestoController(Map<String, String> map) {
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

                /*for (Place place : places) {

                    System.out.println(place.getName());

                }*/

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




                /*System.out.println("Voulez vous entrer de nouvelles coordonées ? O/N");
                String input = sop.next();

                if (input.toLowerCase().equals("o"))
                {
                    keepGoing = true;
                }
                else
                {
                    keepGoing = false;
                }*/

        }
    }

}
