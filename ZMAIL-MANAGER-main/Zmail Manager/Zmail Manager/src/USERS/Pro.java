package USERS;

import JSON.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public final class Pro extends Business{// Hija de business
    private LinkedHashSet<UserMail> contactList;

    public Pro(UserMail mail, String password, String companyName, CompanyType companyType) {
        super(mail, password, companyName, companyType);
        this.contactList = new LinkedHashSet<UserMail>();
    }

    public Pro(){}

    public LinkedHashSet<UserMail> getContactList() {
        return contactList;
    }


    public boolean addMailToList(UserMail mail) {
            contactList.add(mail);
            return true;
    }

    @Override
    public HashMap<String,Object> toHashMap(){// Para transformar a hashmap
        HashMap<String, Object> hashMapGenericity = new HashMap<>(super.toHashMap());
        hashMapGenericity.put("contactList", getContactList());
        hashMapGenericity.put("user_type", "Pro");
        return hashMapGenericity;
    }
    public  HashSet<UserMail> getContactList(UserMail userMail){// Trae la ontact list de  pro desde el json
        HashSet<UserMail> userlist= new HashSet<>();
        try {
            String file = JSONUtiles.downloadFile("Users.json");
            JSONArray ja = new JSONArray(file);

            for(int i=0;i<ja.length();i++){
                JSONObject aux=ja.getJSONObject(i);
                if(aux.getString("user_type").matches("Pro") && aux.getString("mail").matches(userMail.toString().trim())){//Si encuentra el mail
                    JSONArray aa2=aux.getJSONArray("contactList");
                    for(int k=0;k<aa2.length();k++){
                        JSONObject aux3=aa2.getJSONObject(k);
                        userlist.add(new UserMail(aux3.getString("mail").toString()));
                    }
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return userlist;
    }
    public static String deleteUsersFromContactList(HashSet<UserMail> newContactList, UserMail userMail) {// Carga la nueva hashset de un pro
        try {
            String file = JSONUtiles.downloadFile("Users.json");
            JSONArray usersArray = new JSONArray(file);
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject existingUser = usersArray.getJSONObject(i);
                if (existingUser.getString("mail").equals(userMail.toString().trim())) {
                    existingUser.remove("contactList");
                    JSONArray jaux=new JSONArray();
                    for (UserMail userMail2 : newContactList){
                        JSONObject aux3=new JSONObject();
                        aux3.put("mail", userMail2);
                        jaux.put(aux3);
                    }
                    existingUser.put("contactList", jaux);
                    usersArray.put(i, existingUser);
                    return (JSONUtiles.uploadToFile(usersArray, "Users.json"))
                            ? "Lista de contactos actualizada con éxito."
                            : "No se pudo actualizar la lista de contactos.";
                }
            }
            return "Usuario no encontrado.";
        } catch (JSONException e) {
            throw new RuntimeException("Error al procesar el JSON: " + e.getMessage(), e);
        }
    }
    public static String addUsersToContactList(HashSet<UserMail> newContactList, UserMail userMail) {// Agrega usuarios a la lista de contactos del json
        try {
            String file = JSONUtiles.downloadFile("Users.json");
            JSONArray usersArray = new JSONArray(file);

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject existingUser = usersArray.getJSONObject(i);

                if (existingUser.getString("mail").equals(userMail.toString().trim())) {

                    JSONArray contactListArray = existingUser.optJSONArray("contactList");
                    if (contactListArray == null) {
                        contactListArray = new JSONArray();
                    }

                    for (UserMail userMail2 : newContactList) {
                        boolean alreadyExists = false;

                        for (int j = 0; j < contactListArray.length(); j++) {
                            if (contactListArray.getJSONObject(j).getString("mail").equals(userMail2.toString().trim())) {
                                alreadyExists = true;
                                break;
                            }
                        }

                        if (!alreadyExists) {
                            JSONObject newContact = new JSONObject();
                            newContact.put("mail", userMail2.toString());
                            contactListArray.put(newContact);
                        }
                    }

                    existingUser.put("contactList", contactListArray);

                    usersArray.put(i, existingUser);

                    return (JSONUtiles.uploadToFile(usersArray, "Users.json"))
                            ? "Lista de contactos actualizada con éxito."
                            : "No se pudo actualizar la lista de contactos.";
                }
            }

            return "Usuario no encontrado.";
        } catch (JSONException e) {
            throw new RuntimeException("Error al procesar el JSON: " + e.getMessage(), e);
        }
    }

}

