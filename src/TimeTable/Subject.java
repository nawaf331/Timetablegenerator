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
class Subject {
    public static int noOfSubjects;
    public static int noOfHoursPerSubject = 5;
    public String code,name,type,sem,fid;
    public int slotsRemaining;
    

    public Subject() {
        code=name=type=sem=fid=null;
    }
    
    
    
    public void setValue(String c,String n,String t,String s,String f){
        code = c;
        name = n;
        type = t;
        sem = s;
        fid = f;
        slotsRemaining = Subject.noOfHoursPerSubject;
    }
    
    
}
