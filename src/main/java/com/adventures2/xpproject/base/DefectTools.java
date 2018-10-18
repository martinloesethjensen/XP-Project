package com.adventures2.xpproject.base;

public class DefectTools {
private int ID;
private String toolname;
private String decription;

public DefectTools(){

}
    public DefectTools(int ID, String toolname, String decription) {
        this.ID = ID;
        this.toolname = toolname;
        this.decription = decription;
    }
    public DefectTools(String toolname, String decription) {
        this.toolname = toolname;
        this.decription = decription;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getToolname() {
        return toolname;
    }

    public void setToolname(String toolname) {
        this.toolname = toolname;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    public String toString() {
        return "DefectTools{" +
                "ID=" + ID +
                ", toolname='" + toolname + '\'' +
                ", decription='" + decription + '\'' +
                '}';
    }
}
