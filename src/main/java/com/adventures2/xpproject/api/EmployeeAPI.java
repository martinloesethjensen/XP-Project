package com.adventures2.xpproject.api;

import com.adventures2.xpproject.DatabaseConnection;
import com.adventures2.xpproject.base.Employee;
import com.adventures2.xpproject.logic.EmployeeLogic;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class EmployeeAPI {

  @PostMapping("/api/changeScheme")
  public String changeScheme(@RequestParam("id") int id, @RequestParam("day") String day) throws SQLException {
    PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(
      "UPDATE employees SET "+day+" = NOT "+day+" WHERE id = ?");
    preparedStatement.setInt(1, id);

    DatabaseConnection.update(preparedStatement);

    String returnString = "";

    ArrayList<Employee> employeeArrayList = EmployeeLogic.getEmployeesForScheme();

    for(Employee employee: employeeArrayList) {
      returnString += "" +
        "<tr data-id='"+employee.getId()+"'>" +
          "<td>" + employee.getRealname() + "</td>" +
          "<td data-day='monday' style='background: "+ (employee.isMonday() ? "#0f0" : "#f00") +";'>&nbsp;</td>" +
          "<td data-day='tuesday' style='background: "+ (employee.isTuesday() ? "#0f0" : "#f00") +";'>&nbsp;</td>" +
          "<td data-day='wednesday' style='background: "+ (employee.isWednesday() ? "#0f0" : "#f00") +";'>&nbsp;</td>" +
          "<td data-day='thursday' style='background: "+ (employee.isThursday() ? "#0f0" : "#f00") +";'>&nbsp;</td>" +
          "<td data-day='friday' style='background: "+ (employee.isFriday() ? "#0f0" : "#f00") +";'>&nbsp;</td>" +
        "</tr>" +
        "";
    }

    return returnString;
  }

}
