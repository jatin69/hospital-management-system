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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jatin
 */
public class Select {

    public static ArrayList<String> searchPatientById(int patientId) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<String> foundPatient = new ArrayList<>();

        conn = DBconnect.connectToDatabase();
        String sql = "SELECT * FROM `hospital`.`patient`  WHERE patient_id = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, patientId);
        rs = stmt.executeQuery();

        /*
         rs.next() will give me the found data, which in this case is only 1 tuple
         so, while loop with rs.next() is not necessary here.
         */
        if (rs.next()) {
            do {
                int numberOfColumns = rs.getMetaData().getColumnCount();
                int i = 1;
                while (i <= numberOfColumns) {
                    foundPatient.add(rs.getString(i++));
                }
            } while (rs.next());
        } else {
            JOptionPane.showMessageDialog(null, "No patient with patient ID " + patientId + " found !!", "Message", JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        rs.close();
        stmt.close();
        conn.close();

        return foundPatient;
    }

    public static ArrayList<ArrayList<String>> searchPatientByName(String patientName) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // make 2D list for found patients
        ArrayList<ArrayList<String>> foundPatients = new ArrayList<>();
        conn = DBconnect.connectToDatabase();
        String sql = "SELECT * FROM `hospital`.`patient` WHERE name LIKE ?";
        stmt = conn.prepareStatement(sql);

        String postMatch = patientName + "%";
        stmt.setString(1, postMatch);
        rs = stmt.executeQuery();

        /*
         rs.next() will give me the found data, which in this case is only 1 tuple
         so, while loop with rs.next() is not necessary here.
         */
        if (rs.next()) {
            do {
                int numberOfColumns = rs.getMetaData().getColumnCount();
                // Make 1D arrayList, store current ROW in it  
                ArrayList<String> currentRow = new ArrayList<>(numberOfColumns);
                int i = 1;
                while (i <= numberOfColumns) {
                    currentRow.add(rs.getString(i++));
                }
                // add currentRow to final result
                foundPatients.add(currentRow);
            } while (rs.next());
        } else {
            JOptionPane.showMessageDialog(null, "No matching Results found !!", "Message", JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        rs.close();
        stmt.close();
        conn.close();

        return foundPatients;
    }



    public static ArrayList<ArrayList<String>> displayAll() throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // make 2D list for found patients
        ArrayList<ArrayList<String>> foundPatients = new ArrayList<>();
        conn = DBconnect.connectToDatabase();
        String sql = "SELECT * FROM `hospital`.`patient`";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        /*
         rs.next() will give me the found data, which in this case is only 1 tuple
         so, while loop with rs.next() is not necessary here.
         */
        if (rs.next()) {
            do {
                int numberOfColumns = rs.getMetaData().getColumnCount();
                // Make 1D arrayList, store current ROW in it  
                ArrayList<String> currentRow = new ArrayList<>(numberOfColumns);
                int i = 1;
                while (i <= numberOfColumns) {
                    currentRow.add(rs.getString(i++));
                }
                // add currentRow to final result
                foundPatients.add(currentRow);
            } while (rs.next());
        } else {
            JOptionPane.showMessageDialog(null, "No Data found !!", "Message", JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        rs.close();
        stmt.close();
        conn.close();

        return foundPatients;
    }
}
