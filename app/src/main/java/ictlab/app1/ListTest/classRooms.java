package ictlab.app1.ListTest;


/**
 - Our data object class
 - Represents a single room with name,description and image.
 - Takes in property values via Constructor.
 - Exposes them via getter methods.
 */

public class classRooms {

    private String name, description;

    public classRooms(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}



}
