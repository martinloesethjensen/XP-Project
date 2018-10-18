package com.adventures2.xpproject.logic;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Activity;
import com.adventures2.xpproject.base.DefectTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DefectLogic {


    public static void writeDefectToDatabase(DefectTools defect) {
        try {
            PreparedStatement con = DatabaseConnection.getConnection().prepareStatement("insert into defects(name,description) values (?,?)");
            con.setString(1, defect.getToolname());
            con.setString(2, defect.getDecription());

DatabaseConnection.insert(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<DefectTools> ReadDefects() {
        ResultSet rs = DatabaseConnection.query("select * from defects");
        ArrayList<DefectTools> defectToolsArrayList = new ArrayList<>();
        DefectTools defect = new DefectTools();
        try {
            while (rs.next()) {
                defect.setID(rs.getInt("id"));
                defect.setToolname(
                        rs.getString("name"));
                defect.setDecription(rs.getString("description"));
                defectToolsArrayList.add(defect);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }


        return defectToolsArrayList;
    }


    public static void deletedefects(int id) {
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("DELETE  FROM defects WHERE id = ? ");
            preparedStatement.setInt(1, id);
            DatabaseConnection.delete(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
