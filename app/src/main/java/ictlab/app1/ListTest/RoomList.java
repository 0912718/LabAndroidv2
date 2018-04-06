package ictlab.app1.ListTest;

/**
 * Created by edgar on 5-4-2018.
 */

public class RoomList {
    private int begintime_id, classroom_id, date_id;

    public RoomList(int begintime_id, int classroom_id, int date_id){
        this.begintime_id = begintime_id;
        this.classroom_id = classroom_id;
        this.date_id = date_id;
    }

    public int getClassroom_id() {return classroom_id;}
    public void setClassroom_id(int classroom_id){this.classroom_id = classroom_id;}

    public int getBegintime_id(){return begintime_id;}
    public void setBegintime_id(int begintime_id){this.begintime_id = begintime_id;}

    public int getDate_id(){return date_id;}
    public void setDate_id(int date_id){this.date_id = date_id;}

//    public int getEndtime_id(){return endtime_id;}
//    public void setEndtime_id(int endtime_id){this.endtime_id = endtime_id;}
}
