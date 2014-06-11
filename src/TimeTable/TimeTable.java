/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimeTable;

/**
 *
 * @author
 */
public class TimeTable {

    public DaySlots daySlots[];

    TimeTable() {
        daySlots = new DaySlots[6];
        for (int i = 0; i < 6; i++) {
            daySlots[i] = new DaySlots();
            daySlots[i].daySlotId = i;
        }

    }

}
