package server.controllers;

import server.models.Post;

/**
 * Created by Filip on 10-10-2017.
 */
public class ContentController {

    public Post validatePostInformation(Post jsonPost) {


        if (jsonPost.getEvent().getId() < 0 || jsonPost.getParent().getId() < 0){
            throw new IllegalArgumentException();
        }
        if (jsonPost.getEvent() == null) {

        }
        return jsonPost;
    }
}
