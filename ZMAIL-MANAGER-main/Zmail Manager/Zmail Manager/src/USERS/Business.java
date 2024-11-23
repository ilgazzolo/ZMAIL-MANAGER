package USERS;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Business extends User { //Hija de user
    private String companyName;
    private CompanyType companyType;


    public Business(UserMail mail, String password, String companyName, CompanyType companyType) {
        super(mail, password);
        this.companyName = companyName;
        this.companyType = companyType;
    }

    public Business(){} // GETTERS Y SETTERS----------------------------

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    @Override
    public HashMap<String,Object> toHashMap(){ // Transforma los atributos  a hashmap
        HashMap<String,Object> hashMapGenericity = new HashMap<>(super.toHashMap());
        hashMapGenericity.put("companyName", getCompanyName());
        hashMapGenericity.put("companyType", getCompanyType());
        hashMapGenericity.put("user_type", "Business");
        return hashMapGenericity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyName);
    }

}
