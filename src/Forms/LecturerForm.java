/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Main.extractFacultyNames;

import ds.Faculty;
import java.awt.Cursor;
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
import javax.swing.table.DefaultTableModel;
import run.DBInterfaceTT;
import run.DBConnectTT;

/**
 *
 * @author Raat
 */
public class LecturerForm extends javax.swing.JFrame {

    int row;
    DefaultTableModel lectureTableModel;
    /*
     * Creates new form Lecturer
     */

    public LecturerForm() {

        initComponents();
        this.setTitle("Add Faculty Details");
        this.pack();
        this.setLocationRelativeTo(null);
        lectureTableModel = new DefaultTableModel();
        LectureDetailTable.setModel(lectureTableModel);
        lectureTableModel.addColumn("F id:");
        lectureTableModel.addColumn("Name:");
        lectureTableModel.setRowCount(0);
        retrievTableContents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        LectureDetailTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        bLoadFromFile = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfFID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfFName = new javax.swing.JTextField();
        bAdd = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bClearAll = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText(" Lecturer Details");

        LectureDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(LectureDetailTable);

        bLoadFromFile.setText("Load from file");
        bLoadFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadFromFileActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setText("Id :");

        jLabel2.setText("Name :");

        bAdd.setText("Add");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfFID)
                    .addComponent(tfFName, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bAdd)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfFID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAdd)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButton2.setText("Ok");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        bClearAll.setText("Clear All");
        bClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(bLoadFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(bClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bLoadFromFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bClearAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLoadFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadFromFileActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "File can be any text file\nEvery Record should start from new line.\nEach Record must be of following format\n <Faculty id(Should not spaces)><space><Faculty Name(Can contain spaces)>", "Help", JOptionPane.OK_OPTION);
        Connection con = null;
        Statement st = null;
        DBConnectTT.getConnection();
        con = DBConnectTT.connection;
        //Create a file chooser
        Faculty f;
        JFileChooser fc = null;
        String inFile;
        try {
            fc = new JFileChooser(previousDirectory());
        } catch (IOException ex) {
            //Logger.getLogger(EnterUsnForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        //In response to a button click:
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            setCursor(Cursor.WAIT_CURSOR);
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            inFile = file.getAbsolutePath();
            saveCurrentDirectory(file.getParentFile().getAbsolutePath());
            //e = new extractUSN(inFile);
            extractFacultyNames e = new extractFacultyNames();

            //System.out.println("FILE USN SIZE : " + FileUsn.size());
            f = e.getNameList(inFile);
            //System.out.println("FILE USN SIZE : " + FileUsn.size());
            for (int i = 0; i < f.fid.size(); i++) {
                System.out.println("id" + i + " : " + f.getFID(i));
                //   list.addElement(FileUsn.get(i));
                String query = "insert into " + DBInterfaceTT.TT_FACULTY_DETAILS + " values ( '" + f.getFID(i) + "','" + f.getFName(i) + "') ";
                try {
                    st = con.createStatement();
                    st.executeUpdate(query);
                    System.out.println(f.getFName(i) + " inserted");
                } catch (SQLException ex) {
                    System.out.println("Errr :" + ex.getMessage());
                    //   Logger.getLogger(Lecturer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            for (int i = 0; i < f.name.size(); i++) {
                System.out.println("NAME" + i + " : " + f.getFName(i));
                //   list.addElement(FileUsn.get(i));
            }

            //List_Usn.setModel(list);
            //modified = true;
        } else {
            System.out.println("Open command cancelled by user.");
        }
        retrievTableContents();
        setCursor(Cursor.getDefaultCursor());


    }//GEN-LAST:event_bLoadFromFileActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearAllActionPerformed

        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Are you sure? It will clear all the records", "Are you sure?", JOptionPane.OK_CANCEL_OPTION) != 0) {
            return;
        }
        Connection con = null;
        Statement stmt = null;
        DBConnectTT.getConnection();
        con = DBConnectTT.connection;

        try {
            stmt = con.createStatement();
            stmt.executeUpdate("delete from " + DBInterfaceTT.TT_FACULTY_DETAILS);
        } catch (SQLException ex) {
            Logger.getLogger(LecturerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        retrievTableContents();

    }//GEN-LAST:event_bClearAllActionPerformed

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        // TODO add your handling code here:
        String fid = tfFID.getText();
        String fname = tfFName.getText();

        Connection con = null;
        Statement stmt = null;

        if (!(fid.equals("") || fname.equals(""))) {

            try {
                DBConnectTT.getConnection();
                con = DBConnectTT.connection;

                stmt = con.createStatement();
                stmt.executeUpdate("insert into " + DBInterfaceTT.TT_FACULTY_DETAILS + " values ('" + fid + "' ,'" + fname + "')");

            } catch (SQLException ex) {
                Logger.getLogger(LecturerForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Fields Cannot be empty", "Error", JOptionPane.OK_OPTION);
        }

        retrievTableContents();
        ;

    }//GEN-LAST:event_bAddActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable LectureDetailTable;
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bClearAll;
    private javax.swing.JButton bLoadFromFile;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField tfFID;
    private javax.swing.JTextField tfFName;
    // End of variables declaration//GEN-END:variables

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

    private void retrievTableContents() {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection con = null;
        lectureTableModel.setRowCount(0);
        Statement stmt = null;
        ResultSet rs = null;
        int rowCount = 0;
        DBConnectTT.getConnection();
        con = DBConnectTT.connection;
        String q = "Select * from " + DBInterfaceTT.TT_FACULTY_DETAILS;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(q);

            while (rs.next()) {
                String id = rs.getString(DBInterfaceTT.TT_F_ID);
                String name = rs.getString(DBInterfaceTT.TT_F_NAME);
                lectureTableModel.insertRow(rowCount++, new Object[]{id, name});
                LectureDetailTable.setModel(lectureTableModel);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LecturerForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
