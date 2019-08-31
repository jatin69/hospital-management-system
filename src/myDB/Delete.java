/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jatin
 */
public class Delete {

    public static void deletePatient(int patientId) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        conn = DBconnect.connectToDatabase();
        String sql = "DELETE FROM `hospital`.`patient` WHERE patient_id = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, patientId);

        int affectedRows = stmt.executeUpdate();
        if (affectedRows != 0) {
            JOptionPane.showMessageDialog(null, "Patient discharged successfully !", "Success", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No patient with patient ID " + patientId + " found !!", "Message", JOptionPane.PLAIN_MESSAGE);
        }

        stmt.close();
        conn.close();

    }
}
