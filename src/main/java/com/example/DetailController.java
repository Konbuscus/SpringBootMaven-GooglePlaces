package com.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class DetailController {
    public static HashMap<Integer, String> weekDays;

    @RequestMapping(value="/restaurant/{id}", method=RequestMethod.GET)
    public String details(@PathVariable("id") String id) {
        initWeekDays();
        StringBuilder result = new StringBuilder();
        try {
            String requestUrl = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + id + "&key=" + RestoController.APIKEY;
            URL request = new URL(requestUrl);
            JSONTokener tokener = new JSONTokener(request.openStream());
            JSONObject json = new JSONObject(tokener);

            try {
                if (json.length() > 0) {
                    JSONObject resultJSON = json.getJSONObject("result");
                    result.append("<p>Nom du restaurant : <strong>" + resultJSON.getString("name") + "</strong><img src='" + resultJSON.getString("icon") + "' title='icon'></p>");
                    result.append("<p>URL : <a href='" + resultJSON.getString("url") + "' target='_blank'>Cliquez ici pour ouvrir dans un nouvel onglet (Google Maps)</a></p>");
                    result.append("<p>Adresse : " + resultJSON.getString("formatted_address") + "</p>");
                    result.append("<p>Numéro de téléphone : " + resultJSON.getString("formatted_phone_number") + " (" + resultJSON.getString("international_phone_number") + ")</p>");

                    JSONObject openingHours = resultJSON.getJSONObject("opening_hours");
                    JSONArray periods = openingHours.getJSONArray("periods");
                    ArrayList<String> list = new ArrayList<String>();
                    for (int index = 0; index < periods.length(); ++index) {
                        list.add("<h3>Jour : " + weekDays.get(periods.getJSONObject(index).getJSONObject("open").getInt("day")) + "</h3>");
                        list.add("<li>Ouverture à : " + periods.getJSONObject(index).getJSONObject("open").getString("time") + "</li>");
                        list.add("<li>Fermeture à : " + periods.getJSONObject(index).getJSONObject("close").getString("time") + "</li>");
                    }
                    String isOpen = (openingHours.getBoolean("open_now")) ? "Oui" : "Non";
                    StringBuilder daysAndHours = new StringBuilder();
                    for (String day : list) {
                        daysAndHours.append(day);
                    }
                    result.append("<p>Horaires d'ouverture :<ul>" +
                            "<li>Ouvert en ce moment : " + isOpen + "</li>" +
                            "<ul>" + daysAndHours.toString() + "</ul>" +
                            "</ul></p>"
                    );
                } else {
                    result.append("json is empty");
                }
            } catch (JSONException ex) {
                result.append(ex.getMessage());
            }
        } catch(IOException e) {
            result.append(e.getMessage());
        }
        return "<h1>RESULTATS</h1>" + result.toString();
    }

    private void initWeekDays() {
        weekDays = new HashMap<Integer, String>();
        weekDays.put(1, "Lundi");
        weekDays.put(2, "Mardi");
        weekDays.put(3, "Mercredi");
        weekDays.put(4, "Jeudi");
        weekDays.put(5, "Vendredi");
        weekDays.put(6, "Samedi");
        weekDays.put(7, "Dimanche");
    }
}
