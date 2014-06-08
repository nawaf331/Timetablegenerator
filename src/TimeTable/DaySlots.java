/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TimeTable;

/**
 *
 * @author ashik
 */
class DaySlots {
    public static int noOfSlots = 7;
    public static int noOfDaysinAWeek = 6;
    public int daySlotId;
    public Slot slots[];
    
    public DaySlots(){
        slots = new Slot[noOfSlots];
        for(int i = 0;i<noOfSlots;i++){
            slots[i] = new Slot();
        }
    }
}
