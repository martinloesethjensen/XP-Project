package com.adventures2.xpproject.auth;

import com.adventures2.xpproject.DatabaseConnection;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticate {
    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("ID") != null;
    }

    public static boolean login(String username, String password, HttpSession session) throws SQLException {
        ResultSet resultSet = DatabaseConnection.query("SELECT * FROM users WHERE username = '"+username+"'");
        resultSet.last();
        if(resultSet.getRow() == 1 && checkPassword(password, resultSet.getString("password"))) {
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

    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

    public static boolean isChef(HttpSession session) {
        return (int)session.getAttribute("NIVEAU") == 1;
    }
}
