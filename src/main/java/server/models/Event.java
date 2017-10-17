package server.models;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Filip on 10-10-2017.
 */
public class Event {

    private int id;
    private String titel;
    private Timestamp created;
    private User owner;
    private Timestamp startDate;
    private Timestamp endDate;
    private String description;
    private ArrayList<User> participants;
    private ArrayList<Post> posts;

    public Event(int id, String titel, Timestamp created, User owner, Timestamp startDate, Timestamp endDate, String description) {
        this.id = id;
        this.titel = titel;
        this.created = created;
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;

        this.participants = new ArrayList<User>();
        this.posts = new ArrayList<Post>();
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public User getOwner() {
        return owner;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}


