package server.controllers;

import server.models.User;

/**
 * Created by Filip on 10-10-2017.
 */
public class UserController {

    public UserController(){

    }
    public User validateUserCreation(String password, String firstName, String lastName, String email, char gender, String description, String major, int semester){

        User validatedUser = new User(password,firstName,lastName,email,gender,description,major,semester);

        try {

            String[] emailParts;
            emailParts = email.split("@");
            String emailName = emailParts[0];
            String emailDomain = emailParts[1];


            if (!emailDomain.endsWith("cbs.dk") && emailDomain.length()!=6 || !emailDomain.endsWith(".cbs.dk")) {
                throw new IllegalArgumentException("Email did not contain correct a domain");
            }
            else if (!String.valueOf(gender).equalsIgnoreCase("f")
                    || !String.valueOf(gender).equalsIgnoreCase("m")) {
                throw new IllegalArgumentException("Gender can only be \"m\" or \"f\"");
            } else if (semester <1 || semester >6) {
                throw new IllegalArgumentException("Semester must be a value of 1-6");
            }

        }
        catch (Exception exception){
            exception.printStackTrace();
        }


        return validatedUser;
    }
}