package USERS;

import java.util.Random;


public class UserValidator {

    public static UserMail validateUserMail(String name, UsersManager uM_G) {// Valida el mail
        boolean value = false;
        Random random = new Random();
        int randomPro;
        int i = 0;
        UserMail createMail = null;
        do {
            randomPro = random.nextInt(999);
            createMail = new UserMail(name + randomPro);
            i++;
            value = uM_G.validateUserExists(createMail);
        } while (value && i < 2000);
        return createMail;
    }


    public static boolean verifyDataName(String dataName) {// verifica que el nombre este en el formato admitido
        if (dataName.length() <= 1) {
            return false;
        }
        if (dataName.length() > 25) {
            return false;
        }
        if (!dataName.matches("^[a-zA-Z\\s']*$")) {
            return false;
        }
        return true;// retorna true si el nombre es menor a 25 caracteres, mayor o igual a 1 y no contiene caracteres especiales
    }

}
