package USERS;

import EXCEPTIONS.PasswordException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

public final class Person extends User {
    private String name;
    private String lastName;

    public Person(UserMail mail, String password, String name, String lastName) {
        super(mail, password);
        this.name = name;
        this.lastName = lastName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    @Override
    public String toString() {
        return "Person{" + super.toString() +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public HashMap<String,Object> toHashMap(){// Transforma los atributos  a hashmap
        HashMap<String,Object> hashMapGenericity = new HashMap<>(super.toHashMap());
        hashMapGenericity.put("name", getName());
        hashMapGenericity.put("lastName", getLastName());
        hashMapGenericity.put("user_type", "Estandar");
        return hashMapGenericity;
    }
}
