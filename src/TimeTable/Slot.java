/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimeTable;

/**
 *
 * @author nawaf
 */
class Slot {

    public Subject subject;
    public boolean isItFilled;
    public static int noOfSlotsInADay = 7;

    public Slot() {
        isItFilled = false;
        subject = null;

    }

}
