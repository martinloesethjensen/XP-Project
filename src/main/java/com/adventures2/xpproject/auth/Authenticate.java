package com.adventures2.xpproject.auth;

import com.adventures2.xpproject.DatabaseConnection;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticate {
    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("ID") != null;
    }

    public static boolean login(String username, String password, HttpSession session) throws SQLException {
        ResultSet resultSet = DatabaseConnection.query("SELECT * FROM users WHERE username = '"+username+"' AND password = '"+password+"'");
        resultSet.last();
        if(resultSet.getRow() == 1) {
            session.setAttribute("ID", resultSet.getInt("id"));
            session.setAttribute("REALNAME", resultSet.getString("realname"));
            session.setAttribute("NIVEAU", resultSet.getInt("niveau"));
            return true;
        }
        return false;
    }

    public static void logout(HttpSession session) {
        session.removeAttribute("ID");
        session.removeAttribute("REALNAME");
        session.removeAttribute("NIVEAU");
    }
}
