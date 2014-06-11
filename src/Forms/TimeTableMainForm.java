/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Main.TimeTableTask;
import java.awt.Color;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.UnsupportedLookAndFeelException;
import run.DBConnectTT;
import run.DBInterfaceTT;
import static run.DBInterfaceTT.TT_TT;

/**
 *
 * @author DELL
 */
public class TimeTableMainForm extends javax.swing.JFrame {

    private int count;
    private int lectureCount;
    private int subjectCount;
    private int sbpw;

    public static JProgressBar prgrssBar;
    public boolean done = false;
    public static JLabel statusMessage;

    /**
     * Creates new form NewJFrame
     */
    public TimeTableMainForm() {
        initComponents();

        this.setLocationRelativeTo(null);
        progressBar.setValue(progressBar.getMinimum());
        progressBar.setMinimum(0);

        progressBar.setMaximum(100);
        prgrssBar = progressBar;
        statusMessage = status;
        cbDay.setSelectedIndex(2);
        cbHour.setSelectedIndex(3);
        getInputStatus();
        setLabels();
        setLabels1();
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
        bInputLecture = new javax.swing.JButton();
        bInputSubject = new javax.swing.JButton();
        bGenerate = new javax.swing.JButton();
        bSeePrev = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbSaturday = new javax.swing.JComboBox();
        chkbCommon = new javax.swing.JCheckBox();
        cbDay = new javax.swing.JComboBox();
        cbHour = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbspw = new javax.swing.JComboBox();
        lLeactureStatus = new javax.swing.JLabel();
        lSubjectStatus = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        progressBar = new javax.swing.JProgressBar();
        status = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        bNew = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trajan Pro", 1, 22)); // NOI18N
        jLabel1.setText(" Automatic Timetable Generator");

        bInputLecture.setBackground(new java.awt.Color(255, 255, 255));
        bInputLecture.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bInputLecture.setText("Input Lecturer Details");
        bInputLecture.setBorder(new javax.swing.border.MatteBorder(null));
        bInputLecture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bInputLectureMouseClicked(evt);
            }
        });
        bInputLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInputLectureActionPerformed(evt);
            }
        });

        bInputSubject.setBackground(new java.awt.Color(255, 255, 255));
        bInputSubject.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bInputSubject.setText("Input  Subject Details");
        bInputSubject.setBorder(new javax.swing.border.MatteBorder(null));
        bInputSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInputSubjectActionPerformed(evt);
            }
        });

        bGenerate.setBackground(new java.awt.Color(255, 255, 255));
        bGenerate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        bGenerate.setText("GENERATE");
        bGenerate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGenerateActionPerformed(evt);
            }
        });

        bSeePrev.setText("See Prev");
        bSeePrev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bSeePrevMouseEntered(evt);
            }
        });
        bSeePrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSeePrevActionPerformed(evt);
            }
        });

        jLabel2.setText("Sat :");

        cbSaturday.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Half Day", "Full Day" }));

        chkbCommon.setText("Want common hour for all batch");
        chkbCommon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkbCommonItemStateChanged(evt);
            }
        });
        chkbCommon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbCommonActionPerformed(evt);
            }
        });

        cbDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));
        cbDay.setEnabled(false);
        cbDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDayActionPerformed(evt);
            }
        });

        cbHour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "First Hour", "Second Hour", "Third Hour", "Fourth Hour", "Fifth Hour", "Sixth Hour", "Seventh" }));
        cbHour.setSelectedItem(3);
        cbHour.setEnabled(false);

        jLabel3.setText("Subject/Week:");

        cbspw.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "4", "6" }));

        lLeactureStatus.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        lLeactureStatus.setForeground(new java.awt.Color(0, 75, 2));
        lLeactureStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lLeactureStatus.setText("Ok");

        lSubjectStatus.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        lSubjectStatus.setForeground(new java.awt.Color(0, 79, 4));
        lSubjectStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lSubjectStatus.setText("Ok");

        status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status.setText("Welcome");

        jMenu1.setText("File");

        bNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        bNew.setText("New");
        bNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNewActionPerformed(evt);
            }
        });
        jMenu1.add(bNew);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("About Us");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bInputLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(bInputSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(109, 109, 109))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(cbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bSeePrev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(cbHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSaturday, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(chkbCommon)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(lLeactureStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbspw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(lSubjectStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                            .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bInputLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bInputSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSeePrev, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lLeactureStatus)
                    .addComponent(lSubjectStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbSaturday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbspw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkbCommon)
                    .addComponent(cbHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(bGenerate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bInputLectureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInputLectureActionPerformed
        // TODO add your handling code here:

        new LecturerForm().setVisible(true);
        getInputStatus();
        setLabels();
    }//GEN-LAST:event_bInputLectureActionPerformed

    private void bInputLectureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bInputLectureMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bInputLectureMouseClicked

    private void bInputSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInputSubjectActionPerformed
        // TODO add your handling code here:

        new SubjectsForm().setVisible(true);

        getInputStatus();
        setLabels1();
    }//GEN-LAST:event_bInputSubjectActionPerformed

    private void bGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGenerateActionPerformed
        // TODO add your handling code here:

        getInputStatus();
        setLabels();
        setLabels1();
        getValues();

        if (lectureCount < 10 || subjectCount < 20) {

            JOptionPane.showMessageDialog(null, "InSufficient Inputs..", "Try", JOptionPane.OK_OPTION);
            return;
        }

        DBConnectTT.getConnection();
        Connection con = DBConnectTT.connection;

        int x = JOptionPane.showConfirmDialog(null, "This will delete all your previous records\nAre you sure you want to continue ?", "New TimeTable", JOptionPane.OK_CANCEL_OPTION);
        if (x == 0) {
            //  log("Deleting previous records !");

            Statement stmtSt = null;
            String querySt = " Delete  from " + TT_TT;

            try {
                stmtSt = con.createStatement();
                stmtSt.executeUpdate(querySt);

            } catch (SQLException ex) {
                Logger.getLogger(TimeTableMainForm.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error : " + ex.getMessage());
            }

            //
            setCursor(Cursor.WAIT_CURSOR);
            //  new TimeTable.TimeTables(isSaturdayHalf,wantCommonHour,dayOfCommon,hourOfCommon,sbpw,progressBar);
            TimeTableTask task = new Main.TimeTableTask(isSaturdayHalf, wantCommonHour, dayOfCommon, hourOfCommon, sbpw);
            task.addPropertyChangeListener(
                    new PropertyChangeListener() {
                        public void propertyChange(PropertyChangeEvent evt) {
                            if ("progress".equals(evt.getPropertyName())) {
                                progressBar.setValue((Integer) evt.getNewValue());
                                if (done) {
                                    setCursor(Cursor.getDefaultCursor());
                                    enableeverything();

                                }

                            }

                        }
                    });
            task.execute();

            disableeverything();

        } else {
            return;
        }


    }//GEN-LAST:event_bGenerateActionPerformed

    private void bNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewActionPerformed
        // TODO add your handling code here:

        DBConnectTT.getConnection();
        Connection con = DBConnectTT.connection;

        int x = JOptionPane.showConfirmDialog(null, "This will delete all your previous records\nAre you sure you want to continue ?", "New TimeTable", JOptionPane.OK_CANCEL_OPTION);
        if (x == 0) {
            //  log("Deleting previous records !");

            Statement stmtSt = null;
            String querySt = " Delete  from " + TT_TT;

            try {
                stmtSt = con.createStatement();
                stmtSt.executeUpdate(querySt);
                System.out.println("querySt : " + querySt);
            } catch (SQLException ex) {
//                log(ex.getMessage());
                Logger.getLogger(TimeTableMainForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_bNewActionPerformed

    private void bSeePrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSeePrevActionPerformed
        // TODO add your handling code here:

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimeTableShow().setVisible(true);
            }
        });
    }//GEN-LAST:event_bSeePrevActionPerformed

    private void cbDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDayActionPerformed

    private void chkbCommonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbCommonActionPerformed
                // TODO add your handling code here:

    }//GEN-LAST:event_chkbCommonActionPerformed

    private void chkbCommonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkbCommonItemStateChanged
        Object source = evt.getItemSelectable();
        if (source == chkbCommon) {
            cbDay.setEnabled(true);
            cbHour.setEnabled(true);
        } else {
            cbDay.setEnabled(false);
            cbHour.setEnabled(false);
        }

    }//GEN-LAST:event_chkbCommonItemStateChanged

    private void bSeePrevMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bSeePrevMouseEntered
        // TODO add your handling code here:
        //  progressBar.setValue(100);
    }//GEN-LAST:event_bSeePrevMouseEntered

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:

       // task.cancel(true);

    }//GEN-LAST:event_formWindowClosing

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        getInputStatus();
        setLabels();
        setLabels1();
    }//GEN-LAST:event_formWindowGainedFocus

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TimeTableMainForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(TimeTableMainForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(TimeTableMainForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(TimeTableMainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimeTableMainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bGenerate;
    private javax.swing.JButton bInputLecture;
    private javax.swing.JButton bInputSubject;
    private javax.swing.JMenuItem bNew;
    private javax.swing.JButton bSeePrev;
    private javax.swing.JComboBox cbDay;
    private javax.swing.JComboBox cbHour;
    private javax.swing.JComboBox cbSaturday;
    private javax.swing.JComboBox cbspw;
    private javax.swing.JCheckBox chkbCommon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lLeactureStatus;
    private javax.swing.JLabel lSubjectStatus;
    public javax.swing.JProgressBar progressBar;
    public javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
    private boolean isSaturdayHalf;
    private boolean wantCommonHour;
    private int dayOfCommon;
    private int hourOfCommon;

    private void getValues() {
        //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (cbSaturday.getSelectedItem().equals("Half Day")) {
            isSaturdayHalf = true;
        } else {
            isSaturdayHalf = false;
        }

        sbpw = Integer.parseInt(cbspw.getSelectedItem().toString());

        wantCommonHour = chkbCommon.isSelected();

        dayOfCommon = cbDay.getSelectedIndex();
        hourOfCommon = cbHour.getSelectedIndex();
    }

    public void getInputStatus() {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        DBConnectTT.getConnection();
        con = DBConnectTT.connection;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from " + DBInterfaceTT.TT_FACULTY_DETAILS);
            while (rs.next()) {
                lectureCount++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeTableMainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from " + DBInterfaceTT.TT_SUBJECT_DETAILS);
            while (rs.next()) {
                subjectCount++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeTableMainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setLabels() {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (lectureCount < 1) {
            lLeactureStatus.setText("No Data");
            lLeactureStatus.setForeground(Color.RED);
            return;

        }
        if (lectureCount < 5) {
            lLeactureStatus.setText("Low Data");
            lLeactureStatus.setForeground(Color.RED);
            return;
        }
        if (lectureCount < 10) {
            lLeactureStatus.setText("Not Enough, will try");
            lLeactureStatus.setForeground(Color.ORANGE);
            return;
        }
        if (lectureCount <= 12) {
            lLeactureStatus.setText("Ok");
            lLeactureStatus.setForeground(Color.green);
            return;
        }

        lLeactureStatus.setText("Good");
        lLeactureStatus.setForeground(Color.GREEN);
        return;

    }

    public void setLabels1() {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (subjectCount < 1) {
            lSubjectStatus.setText("No Data");
            lSubjectStatus.setForeground(Color.RED);
            return;

        }
        if (subjectCount < 5) {
            lSubjectStatus.setText("Low Data");
            lSubjectStatus.setForeground(Color.RED);
            return;
        }
        if (subjectCount < 10) {
            lSubjectStatus.setText("Less than 10 Subject");
            lSubjectStatus.setForeground(Color.ORANGE);
            return;
        }
        if (subjectCount <= 20) {
            lSubjectStatus.setText("Ok");
            lSubjectStatus.setForeground(Color.green);
            return;
        }

        lSubjectStatus.setText("Good");
        lSubjectStatus.setForeground(Color.GREEN);
        return;

    }

    private void disableeverything() {

        bGenerate.setEnabled(false);
        bInputLecture.setEnabled(false);
        bInputSubject.setEnabled(false);
        bSeePrev.setEnabled(false);
    }

    public void enableeverything() {

        bGenerate.setEnabled(true);
        bInputLecture.setEnabled(true);
        bInputSubject.setEnabled(true);
        bSeePrev.setEnabled(true);
    }

}
