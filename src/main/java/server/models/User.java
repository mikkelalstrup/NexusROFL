package server.models;

import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */
public class User {

    private int id;
    private String password;
    private String salt;
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private char gender;
    private String major;
    private int semester;
    private ArrayList<Event> events;
    private ArrayList<Post> posts;

    public User (){
        this.events = new ArrayList<Event>();
        this.posts = new ArrayList<Post>();
    }

    public User(String password, String salt, String firstName) {
        this.id = id;
        this.password = password;
        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.description = description;
        this.gender = gender;
        this.major = major;
        this.semester = semester;

        this.events = new ArrayList<Event>();
        this.posts = new ArrayList<Post>();
    }

    // Use this constructor when assembling data for new user creation
       public User(String password, String firstName, String lastName, String email, char gender, String description, String major, int semester){
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.description = description;
        this.gender = gender;
        this.major = major;
        this.semester = semester;

        this.events = new ArrayList<Event>();
        this.posts = new ArrayList<Post>();
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public char getGender() {
        return gender;
    }

    public String getMajor() {
        return major;
    }

    public int getSemester() {
        return semester;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
