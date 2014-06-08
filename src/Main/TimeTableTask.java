/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import Forms.BIT_TimeTable;
import javax.swing.SwingWorker;

/**
 *
 * @author ashik
 */
public class TimeTableTask extends SwingWorker<Integer, Integer> {

    boolean isSaturdayHalf;
    boolean wantCommonHour;
    int dayOfCommon;
    int hourOfCommon;
    int sbpw;

    public TimeTableTask(boolean saturdayHalf, boolean wantCommonHour, int dayOfCommon, int hourOfCommon, int sbpw) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    isSaturdayHalf = saturdayHalf;
    this.wantCommonHour = wantCommonHour;
    this.dayOfCommon = dayOfCommon;
    this.hourOfCommon = hourOfCommon;
    this.sbpw = sbpw;
    
    }
    @Override
    protected Integer doInBackground() throws Exception {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    new TimeTable.TimeTables(isSaturdayHalf,wantCommonHour,dayOfCommon,hourOfCommon,sbpw);
       // setProgress(10);
      new BIT_TimeTable().setVisible(true);
    return 1;
    }
    
}
