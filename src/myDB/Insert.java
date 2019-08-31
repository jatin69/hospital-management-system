/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Jatin
 */
public class Insert {

    public static void InsertPatient(String name, String sex, String dob, String address, String contactNo) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        /*
         ResultSet 'rs' is necessary to be initiliazed due to complex if-else logic.
         The same can also happen with Connection and PreparedStatement, so being safe, initializing them here.
         */

        conn = DBconnect.connectToDatabase();
        String sql = "INSERT INTO `hospital`.`patient` (`name`, `sex`, `DOB`, `address`, `contact_no`) VALUES (?,?,?,?,?);";
        stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, name);
        stmt.setString(2, sex);
        stmt.setString(3, dob);
        stmt.setString(4, address);
        stmt.setString(5, contactNo);

        Long patientId = null;
        if (stmt.executeUpdate() > 0) {
            rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                patientId = rs.getLong(1);
            }
            JOptionPane.showMessageDialog(null, "Patient inserted successfully with patient ID :  " + patientId, "Success", JOptionPane.PLAIN_MESSAGE);
        }

        /*
         Here, 'rs' also gets its value from function and can be handled by null pointer exception handler,
         but its initialization is based on a if condition, thus, there is a chance that 'rs' never gets initialized
         In that case the rs.close() will cause serious problem, to prevent that,
         its prior initialization with null and a null check before closing is necessary.
         */
        if (rs != null) {
            rs.close();
        }
        /*
         Stmt and Conn - Both get their values from inbuilt library functions,
         so, IN CASE null is returned, the calling function itself handles the null pointer exception,
         so no need to check for stmt and conn being 'null' 
         */
        stmt.close();
        conn.close();

    }

    public static void InsertTreatment(int doctorId, int patientId, String prescription) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        conn = DBconnect.connectToDatabase();

        String sql = "INSERT INTO `hospital`.`treatment` (`doctor_id`, `patient_id`, `prescription`) VALUES (?,?,?);";
        stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.setInt(1, doctorId);
        stmt.setInt(2, patientId);
        stmt.setString(3, prescription);

        if (stmt.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null, "Prescription successfully added !! ", "Success", JOptionPane.PLAIN_MESSAGE);
        }

        stmt.close();
        conn.close();

    }

    public static void InsertDoctor(String name, String specialization, String contactNo, int consultingFee) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        conn = DBconnect.connectToDatabase();

        String sql = "INSERT INTO `hospital`.`doctor` (`name`, `specialization`, `contact_no`,`consulting_fee`) VALUES (?,?,?,?);";
        stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, name);
        stmt.setString(2, specialization);
        stmt.setString(3, contactNo);
        stmt.setInt(4, consultingFee);

        Long doctorId = null;

        if (stmt.executeUpdate() > 0) {
            rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                doctorId = rs.getLong(1);
            }
            JOptionPane.showMessageDialog(null, "Doctor inserted successfully with doctor ID :  " + doctorId, "Success", JOptionPane.PLAIN_MESSAGE);
        }
        if (rs != null) {
            rs.close();
        }
        stmt.close();
        conn.close();
    }

}
