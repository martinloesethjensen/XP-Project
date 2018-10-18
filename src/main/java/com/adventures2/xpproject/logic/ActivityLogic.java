package com.adventures2.xpproject.logic;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Activity;

import org.springframework.web.bind.annotation.PostMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.xml.crypto.Data;

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
          resultSet.getString("image"),
          resultSet.getInt("amountOfEquipment"));
        counter++;
      }
    } catch (SQLException e) {
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
    } catch (SQLException e) {
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
          resultSet.getString("image"),
          resultSet.getInt("amountOfEquipment")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return activityHashMap;
  }

  public static void createActivity(Activity activity) {
    try {
      PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO activities ( name, price, time, discount, image, amountOfEquipment) VALUES (?,?,?,?,?,?)");
      preparedStatement.setString(1, activity.getName());
      preparedStatement.setDouble(2, activity.getPrice());
      preparedStatement.setString(3, activity.getTime());
      preparedStatement.setInt(4, activity.getDiscount());
      preparedStatement.setString(5, activity.getImage());
      preparedStatement.setInt(6, activity.getAmountOfEquipment());
      DatabaseConnection.insert(preparedStatement);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void updateActivity(Activity activity) {
    try {
      PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("UPDATE activities SET name = ?, price = ?, time = ? , discount = ?, image = ?, amountOfEquipment = ? WHERE id = ? ");
      preparedStatement.setString(1, activity.getName());
      preparedStatement.setDouble(2, activity.getPrice());
      preparedStatement.setString(3, activity.getTime());
      preparedStatement.setInt(4, activity.getDiscount());
      preparedStatement.setString(5, activity.getImage());
      preparedStatement.setInt(6, activity.getAmountOfEquipment());
      preparedStatement.setInt(7, activity.getId());
      DatabaseConnection.update(preparedStatement);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void deleteActivity(Activity activity) {
    try {
      PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("DELETE  FROM activities WHERE id = ? ");
      preparedStatement.setInt(1, activity.getId());
      DatabaseConnection.delete(preparedStatement);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public static Activity getActivityById(int id) {
    try {
      System.out.println(id);
      PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM activities WHERE id = ? ");
      preparedStatement.setInt(1, id);
      ResultSet resultSet = DatabaseConnection.queryWithParameters(preparedStatement);
      if (resultSet.next()) {
        // int id, String name, double price, String time, int discount, String image
        return new Activity(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getDouble(3),
                resultSet.getString(4),
                resultSet.getInt(5),
                resultSet.getString(6),
                resultSet.getInt(7)
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new Activity();
  }
}