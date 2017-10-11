package server.models;

import java.sql.Timestamp;

/**
 * Created by Filip on 10-10-2017.
 */
public class Post {

    private int id;
    private Timestamp created;
    private User owner;
    private String content;
    private Event event;
    private Post parent;

    public Post(int id, Timestamp created, User owner, String content, Event event, Post parent) {
        this.id = id;
        this.created = created;
        this.owner = owner;
        this.content = content;
        this.event = event;
        this.parent = parent;
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

    public String getContent() {
        return content;
    }

    public Event getEvent() {
        return event;
    }

    public Post getParent() {
        return parent;
    }
}
