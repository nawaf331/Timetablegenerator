/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Main.SaveTimeTable;
import TimeTable.TimeTables;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import run.DBConnectTT;
import run.DBInterfaceTT;
import static run.DBInterfaceTT.TT_TT;

/**
 *
 * @author ashik
 */
public class TimeTableShow extends javax.swing.JFrame {

    /**
     * Creates new form BIT_TimeTable
     */
    //TimeTables ourTT;
    DefaultTableModel tmBatch[];
    private String[] dayOf = {"Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};

    public TimeTableShow() {
        initComponents();
        // ourTT = new TimeTables();
        tmBatch = new DefaultTableModel[3];

        for (int i = 0; i < TimeTables.noOfBatches; i++) {
            tmBatch[i] = new DefaultTableModel();
            for (int j = 0; j < 8; j++) {
                tmBatch[i].addColumn("Hour-" + j);
            }

        }
        tTTb1.setModel(tmBatch[0]);
        tTTb2.setModel(tmBatch[1]);
        tTTb3.setModel(tmBatch[2]);

        writeToTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bPrint = new javax.swing.JButton();
        bSave = new javax.swing.JButton();
        PrintPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tTTb2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tTTb3 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTTb1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Generated timetable");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bPrint.setText("Print");
        bPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrintActionPerformed(evt);
            }
        });

        bSave.setText("Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        tTTb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Batch 2", "Hour 1", "Hour 2", "Hour 3", "Hour 4", "Hour 5", "Hour 6", "Hour 7", "Hour 8"
            }
        ));
        jScrollPane2.setViewportView(tTTb2);

        tTTb3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Batch 3", "Hour 1", "Hour 2", "Hour 3", "Hour 4", "Hour 5", "Hour 6", "Hour 7", "Hour 8"
            }
        ));
        jScrollPane3.setViewportView(tTTb3);

        tTTb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Batch 1", "Hour 1", "Hour 2", "Hour 3", "Hour 4", "Hour 5", "Hour 6", "Hour 7", "Hour 8"
            }
        ));
        jScrollPane1.setViewportView(tTTb1);

        jLabel1.setText("2nd year");

        jLabel5.setText("3rd year");

        jLabel6.setText("4th year");

        javax.swing.GroupLayout PrintPanelLayout = new javax.swing.GroupLayout(PrintPanel);
        PrintPanel.setLayout(PrintPanelLayout);
        PrintPanelLayout.setHorizontalGroup(
            PrintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrintPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1137, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2))
                .addGap(23, 23, 23))
        );
        PrintPanelLayout.setVerticalGroup(
            PrintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrintPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PrintPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bSave)
                .addGap(40, 40, 40)
                .addComponent(bPrint)
                .addGap(110, 110, 110))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PrintPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSave)
                    .addComponent(bPrint))
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:

    }//GEN-LAST:event_formWindowClosing

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        // TODO add your handling code here:
        new SaveTimeTable(tmBatch);
    }//GEN-LAST:event_bSaveActionPerformed

    private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrintActionPerformed
        // TODO add your handling code here:
        try {

            Toolkit tkp = PrintPanel.getToolkit();
            PrintJob pjp = tkp.getPrintJob(this, null, null);
            Graphics g = pjp.getGraphics();
            PrintPanel.print(g);
            g.dispose();
            pjp.end();
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }//GEN-LAST:event_bPrintActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PrintPanel;
    private javax.swing.JButton bPrint;
    private javax.swing.JButton bSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tTTb1;
    private javax.swing.JTable tTTb2;
    private javax.swing.JTable tTTb3;
    // End of variables declaration//GEN-END:variables

    private void writeToTable() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        DBConnectTT.getConnection();
        con = DBConnectTT.connection;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                String rowOfCodes[] = null;
                rowOfCodes = new String[8];
                rowOfCodes[0] = dayOf[j];

                for (int k = 0; k < 7; k++) {
                    String q = "select * from " + TT_TT + " where " + DBInterfaceTT.TT_batch + " = " + i + " and " + DBInterfaceTT.TT_rowid + " = " + j + " and " + DBInterfaceTT.TT_colid + " = " + k;
                    try {
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(q);
                        rs.next();
                        rowOfCodes[k + 1] = rs.getString(DBInterfaceTT.TT_code);
                    } catch (SQLException ex) {
                        Logger.getLogger(TimeTableShow.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                tmBatch[i].insertRow(j, rowOfCodes);
                System.out.println("addind row " + (i * j));
            }
        }

    }
}
