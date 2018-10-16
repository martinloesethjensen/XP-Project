package com.adventures2.xpproject.logic;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ActivityLogic {
    public static Activity[] getActivities() {
        Activity[] activities = new Activity[getTotalActivities()];
        int counter = 0;

        ResultSet resultSet = DatabaseConnection.query("SELECT * FROM activities ORDER BY name ASC");

        try {
            while (resultSet.next()) {
                activities[counter] = new Activity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                  resultSet.getString("time"),
                        resultSet.getInt("discount"),
                        resultSet.getString("image"));
                counter++;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return activities;
    }

    public static int getTotalActivities() {
        int total = 0;

        ResultSet resultSet = DatabaseConnection.query("SELECT * FROM activities");

        try {
            while (resultSet.next()) {
                total++;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    public static HashMap<Integer, Activity> getActivitiesFromDatabaseToHashMap() {
        HashMap<Integer, Activity> activityHashMap = new HashMap<>();
        ResultSet resultSet = DatabaseConnection.query("SELECT * FROM activities");
        try {
            while (resultSet.next()) {
                activityHashMap.put(resultSet.getInt("id"), new Activity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("time"),
                        resultSet.getInt("discount"),
                        resultSet.getString("image")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activityHashMap;
    }
}