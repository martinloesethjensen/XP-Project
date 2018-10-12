//package com.adventures2.xpproject.logic;
//
//import com.adventures2.xpproject.base.Activity;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ActivityLogic {
//
//
//public void createActivity(Activity acitivity){
//    PreparedStatement preparedStatement;
//
//    try { //count returnerer et antal rækker, der matcher et kriterie,
//        ps = con.prepareStatement("SELECT COUNT(*) AS count FROM zipcodes WHERE zipcode = ?");
//        ps.setInt(1, member.getZipcode());
//
//        ResultSet rs = ps.executeQuery();
//        rs.next();
//        if(rs.getInt("count") == 0){ // hvis der ikke er nogen rækker med det man lige har indtastet så indsætter vi postNr og by.
//            ps = con.prepareStatement("INSERT INTO zipcodes(zipcode, zipcode_city) VALUES(?,?)");
//            ps.setInt(1,  member.getZipcode());
//            ps.setString(2, member.getCity());
//
//            ps.executeUpdate();
//        }
//        ps.close();
//        ps2 = con.prepareStatement("INSERT INTO (member_firstName, member_lastName, member_dateOfBirth, member_CPR, member_address, zipcodes_zipcode) VALUES(?,?,?,?,?,?)");
//        ps2.setString(1,member.getFirstName());
//        ps2.setString(2,member.getLastName());
//        ps2.setDate(3, new java.sql.Date(member.getDateOfBirth().getTime()));
//        ps2.setString(4, member.getCpr());
//        ps2.setString(5, member.getAddress());
//        ps2.setInt(6, member.getZipcode());
//        ps2.executeUpdate();
//    }catch(SQLException e){
//        e.printStackTrace();
//    }
//}
//
//
//
//
//
//
//
//}
