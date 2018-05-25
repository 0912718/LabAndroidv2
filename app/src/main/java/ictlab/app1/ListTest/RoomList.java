package ictlab.app1.ListTest;

/**
 * Created by edgar on 5-4-2018.
 */

public class RoomList {
    private String title, description;
    private int classroom_id, from ,to;

    public RoomList(String title, String description, int from, int to, int classroom_id){
        this.title = title;
        this.classroom_id = classroom_id;
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }

//    public int getClassroom_id() {return classroom_id;}
//    public void setClassroom_id(int classroom_id){this.classroom_id = classroom_id;}
//
//    public int getBegintime_id(){return begintime_id;}
//    public void setBegintime_id(int begintime_id){this.begintime_id = begintime_id;}
//
//    public int getDate_id(){return date_id;}
//    public void setDate_id(int date_id){this.date_id = date_id;}

//    public int getEndtime_id(){return endtime_id;}
//    public void setEndtime_id(int endtime_id){this.endtime_id = endtime_id;}
}
