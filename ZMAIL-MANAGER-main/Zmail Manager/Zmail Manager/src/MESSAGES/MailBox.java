package MESSAGES;

import JSON.JSONUtiles;
import USERS.Person;
import USERS.User;
import USERS.UserMail;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class MailBox <T extends User>{
    private static ArrayList<Mail> messages;

    public MailBox() {
        messages = new ArrayList<>();
    }

    public void removeFromBox() {
        messages.clear();
    }

    public ArrayList<Mail> recibedMessages(UserMail u) {// Devuelve todos los mensajes recibidos por un usuario determinado


        messages.clear();


        try {
            JSONArray ja = new JSONArray();
            if(Files.exists(Paths.get("Mails.json"))){
                String file = JSONUtiles.downloadFile("Mails.json");
                ja = new JSONArray(file);
            }

            for(int i = 0; i<ja.length(); i++){
                JSONObject j = ja.getJSONObject(i);
                JSONArray receptorsList = j.getJSONArray("receptors_list");
                for(int k = 0; k < receptorsList.length(); k++){
                    JSONObject jAux = receptorsList.getJSONObject(k);
                    String mailJSON = jAux.getString("mail");

                    if(mailJSON.equals(u.getMail().toString())){

                        Mail m = Mail.transformJSONToObject(j);
                        messages.add(m);// Los agrega a un arraylist

                    }
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messages;

    }

    public ArrayList<Mail> sendedMessages(UserMail u) {// Devuelve los mensajes enviados por un usuario determinado


        messages.clear();

        try {
            JSONArray ja = new JSONArray();
            if(Files.exists(Paths.get("Mails.json"))){
                String file = JSONUtiles.downloadFile("Mails.json");
                ja = new JSONArray(file);
            }

            for(int i = 0; i<ja.length(); i++){
                JSONObject j = ja.getJSONObject(i);
                String sender = j.getString("sender");


                if(sender.equals(u.getMail().toString().trim())){

                    Mail m = Mail.transformJSONToObject(j);
                    messages.add(m);// Los agrega a un arraylist
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messages;
    }


}