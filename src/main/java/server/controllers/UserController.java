package server.controllers;

/**
 * Created by Filip on 10-10-2017.
 */
public class UserController {

    public UserController(){

    }
    public User validateUserCreation(String password, String firstName, String lastName, String email, char gender, String description, String major, int semester){

        try {
            User user = new User(password,firstName,lastName,email,gender,description,major,semester);

            String[] emailParts;
            emailParts = email.split("@");
            String emailName = emailParts[0];
            String emailDomain = emailParts[1];


            if (!emailDomain.endsWith("cbs.dk") ) {
                throw new IllegalArgumentException("Email did not contain correct domain");
            }

        }
        catch (Exception exception){

            exception.printStackTrace();
        }


        return null;
    }
}
