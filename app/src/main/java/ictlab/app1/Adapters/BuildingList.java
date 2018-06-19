package ictlab.app1.Adapters;

/**
 * This whole code underneath is coded by Edgar Buyten - 0912718
 */

public class BuildingList {
    private String building_name;
    private String classroom;
    private String options;
    private String building_id, classroom_id, date, title, description, from_block, to_block;

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }


    public String getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(String building_id) {
        this.building_id = building_id;
    }

    public String getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(String classroom_id) {
        this.classroom_id = classroom_id;
    }
}

