/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import run.DBConnectTT;
import run.DBInterfaceTT;
import static run.DBInterfaceTT.TT_TT;

/**
 *
 * @author DELL
 */
public class SaveTimeTable {
    private int batch = 0;

    public SaveTimeTable(DefaultTableModel dataModel[]) {
        JFileChooser chooser = null;
        BufferedWriter bfw = null;
        try {
            chooser = new JFileChooser(previousDirectory());
        } catch (IOException ex) {
            Logger.getLogger(SaveTimeTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Sheet", ".CSV", "CSV");
        chooser.setFileFilter(filter);
        int actionDialog = chooser.showSaveDialog(null);
        if (actionDialog == JFileChooser.APPROVE_OPTION) {

            try {
                File fileName = new File(chooser.getSelectedFile().toString());
                saveCurrentDirectory(fileName.getParentFile().getAbsolutePath());
                if (fileName == null) {
                    return;
                }
                if (fileName.exists()) {
                    actionDialog = JOptionPane.showConfirmDialog(null, "Replace existing file?");
                    // may need to check for cancel option as well
                    if (actionDialog == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
                bfw = new BufferedWriter(new FileWriter(fileName + ".csv"));
                
                while(batch < 3){
            //    Forms.MainForm.log("Writing to File" + fileName);
                //bfw.write("hello world");
                for (int i = 0; i < dataModel[batch].getColumnCount(); i++) {
                    try {
                        bfw.write(dataModel[batch].getColumnName(i));
                        bfw.write(", ");
                    } catch (IOException ex) {
                       Logger.getLogger(SaveTimeTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                for (int i = 0; i < dataModel[batch].getRowCount(); i++) {
                    try {
                        bfw.newLine();
                        for (int j = 0; j < dataModel[batch].getColumnCount(); j++) {
                            bfw.write(dataModel[batch].getValueAt(i, j).toString());
                            bfw.write(", ");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(SaveTimeTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                bfw.newLine();
                bfw.newLine();
                appendLeactureDetails(bfw,batch);
                bfw.newLine();
                bfw.newLine();
                batch++;
                }
                bfw.close();
                JOptionPane.showMessageDialog(null, "Saved", "saved", JOptionPane.OK_OPTION);
                //Forms.MainForm.log("Save Complete");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(SaveTimeTable.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(null, "Error in Saving", "Error", JOptionPane.OK_OPTION);

                //Forms.MainForm.logError("Error Saving File");
                
            }
        }
    }

    public String previousDirectory() throws IOException {
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(".previouslySelectedDirectory.txt"));
            line = br.readLine();
        } catch (Exception e) {

            System.out.println("Error: " + e);
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return line;
    }

    private void saveCurrentDirectory(String absolutePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(".previouslySelectedDirectory.txt"));
            writer.write(absolutePath);
            writer.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Error:" + e);
        }
    }


    private void appendLeactureDetails(BufferedWriter bfw,int b) throws IOException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        DBConnectTT.getConnection();
        con = DBConnectTT.connection;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from "+DBInterfaceTT.TT_SUBJECT_DETAILS);
            while(rs.next()){
                String subName = rs.getString(DBInterfaceTT.TT_SUB_SUBNAME);
                String subCode = rs.getString(DBInterfaceTT.TT_SUB_CODE);
                String sem = rs.getString(DBInterfaceTT.TT_SUB_SEM);
                String fid = rs.getString(DBInterfaceTT.TT_SUB_FID);
                int batch = ((Integer.parseInt(sem)) + 1) / 2 - 2;
                if(batch == b){
                    bfw.write(subName );
                    bfw.write(", - ,");
                
                Connection con2 = null;
                con2 = DBConnectTT.connection;

                Statement stmt2 = null;
                stmt2 = con2.createStatement();
                ResultSet rs2 = stmt2.executeQuery("select * from "+DBInterfaceTT.TT_FACULTY_DETAILS+" where "+DBInterfaceTT.TT_F_ID+" = '"+fid+"'");
                rs2.next();
                bfw.write(rs2.getString(DBInterfaceTT.TT_F_NAME));
                bfw.write(",");
                bfw.newLine();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveTimeTable.class.getName()).log(Level.SEVERE, null, ex);
        }}
}
