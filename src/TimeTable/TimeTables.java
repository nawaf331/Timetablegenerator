/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TimeTable;
import Forms.LecturerForm;

import Forms.TimeTableMainForm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import run.DBConnectTT;
import run.DBInterfaceTT;
import static run.DBInterfaceTT.TT_TT;
/**
 *
 * @author ashik
 */
public class TimeTables {

    public static int SECOND_YEAR = 0;
    public static int THIRD_YEAR = 1;
    public static int FOURTH_YEAR = 2;
    public static int MONDAY = 0;
    public static int TUESDAY = 1;
    public static int WEDNESDAY = 2;
    public static int THURSDAY = 3;
    public static int FRIDAY = 4;
    public static int SATURDAY = 5;
    //public TTStatus ttstatus;
    public static int noOfSubjects = 0; 
    public Subject holiday = new Subject();
    public static int noOfBatches = 3;
    public TimeTable timetable[];
    public static boolean done = false;
    public static int totalPlaced = 0;
    public Subject[] listOfSubjects;
    private final int MAX_NO_ATTEMPS = 50;
    private int endCount;
    private final Subject seminar;
    private final Subject commonSub;
    private final int sbjctpw;
    //TimeTableMainForm ttmfObject;   
    JProgressBar pb;
    JLabel st;
    public TimeTables(boolean isSaturdayHalf,boolean wantCommonHour, int dayOfCommon,int hourOfCommon,int sbpw){
    //  ttmfObject = TimeTableMainForm.ttmfCopy;
        pb=Forms.TimeTableMainForm.prgrssBar;
        st=Forms.TimeTableMainForm.statusMessage;
        noOfSubjects = getTotalSubjects();
        timetable = new TimeTable[noOfBatches];
        listOfSubjects = new Subject[noOfSubjects];
         Subject.noOfHoursPerSubject = sbpw;
        for(int i =0;i<noOfSubjects;i++){
            listOfSubjects[i]= new Subject();
        }
        
        holiday.setValue("------", "XXXXXXXXX", "lab" , "0", "hdAdvcj");
        commonSub = new Subject();
        commonSub.setValue("BRANCH", "Common hour", "common", "5", "nil");
        sbjctpw = sbpw;
        for(int i = 0;i < 3;i++){
            timetable[i] = new TimeTable();
        }
        
        seminar = new Subject();
        seminar.setValue("SP","Sem/Pjct", "sem", "8", "ss");
        
        fetchData();
        checkTheSem();
        if(isSaturdayHalf){
            fillSaturday();
        }
        
        //friday
        for(int i =0;i<noOfBatches;i++){
            timetable[i].daySlots[4].slots[3].isItFilled = true;
            timetable[i].daySlots[4].slots[3].subject = holiday;
        }
        
        if(wantCommonHour){
            for(int i = 0;i<noOfBatches;i++){
                timetable[i].daySlots[dayOfCommon].slots[hourOfCommon].isItFilled = true;
                timetable[i].daySlots[dayOfCommon].slots[hourOfCommon].subject = commonSub;
            }
        }
       
        generate();
       // ttmf.setStatus("Completed...");
        display();
        updateDataBase();
        System.out.println("done...");
        //ttmfObject.done = true;
        //ttmfObject.enableeverything();
       // ttmf.setStatus("Done...!");
    }
    

    private void fetchData() {
        
       // ttmf.setStatus("Fething input Data...");
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        pb.setValue(0);
        st.setText("Fetchind input data..(1/3)");
        System.out.println("Fetching subject details from database..\n");
       pb.setValue(0);
       st.setText("Fetchind input data..(1/3)");
       
        DBConnectTT.getConnection();
        Connection con = DBConnectTT.connection;
        int rowCount = 0;
        Statement stmt = null;
        ResultSet rs = null;
        int loopcount = 0;
        
        String query = "select * from "+DBInterfaceTT.TT_SUBJECT_DETAILS;
        System.out.println("Query Timetables : "+ query);
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                
                pb.setValue(loopcount*4);
                String code = rs.getString(DBInterfaceTT.TT_SUB_CODE);
                String name = rs.getString(DBInterfaceTT.TT_SUB_SUBNAME);
                String type = rs.getString(DBInterfaceTT.TT_SUB_TYPE);
                String sem = rs.getString(DBInterfaceTT.TT_SUB_SEM);
                String fid = rs.getString(DBInterfaceTT.TT_SUB_FID);
              listOfSubjects[rowCount++].setValue(code, name, type, sem, fid);
                System.out.println(code+" is fetched successfully");
                
            }
            pb.setValue(100);
           System.out.println("Fetching complete....");
            
        } catch (SQLException ex) {
            System.out.println("err: "+ex.getMessage());
           // Logger.getLogger(TimeTables.class.getName()).log(Level.SEVERE, null, ex);
        }
//        for(int i =0;i<noOfSubjects;i++){
//            System.out.println(listOfSubjects[i].type);
//        }
         
    }

    private void generate() {
 
        //hrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("inside generate..");
        pb.setValue(0);
       st.setText("Generating... (2/3)");
       pb.setMaximum((listOfSubjects.length) * Subject.noOfHoursPerSubject - 20 );
        for(int i=0;i<listOfSubjects.length;i++){
                if(listOfSubjects[i].type.equals("lab")){
                    lookFor(listOfSubjects[i]);
                }
                else{
                   
                }
        }
          
        
        while(!finish()){
            
            
            
            Subject s;
            s = getRandomSubject();
            System.out.println(s.code+" has "+s.slotsRemaining+" slots remining");
            if(s.type.equals("theory")){
                lookFor(s);                
            }
            else{
                System.out.println(s.code+" has "+s.slotsRemaining+" slots remining, got over");
                s = getRandomSubject();
            }

            
        }
      pb.setValue(pb.getMaximum());

   
        
    }

    private Subject getRandomSubject() {
            System.out.println("Inside getRandomSubject");
        int n;
        while(true){
            
                n = (int) (Math.random() * noOfSubjects);
                
           
           
            
         //System.out.println("Generated random value is ");
          
         
            if(listOfSubjects[n].slotsRemaining != 0){
                            
                            System.out.println("Generated random subject is "+listOfSubjects[n].code);
                           return listOfSubjects[n];
            }
            else{
                
                
            }
            
        }
        
        
    }

    private boolean finish() {
            System.out.println("Checking for finish");
        boolean returnvalue = true;
        for(int i=0;i<noOfSubjects;i++){
            if(listOfSubjects[i].slotsRemaining != 0){
                returnvalue = false;
                break;
            }
        }
        
        return returnvalue;
    }

    private void lookFor(Subject subject) {
       
       // ttmf.setStatus("Subject placed : "+totalPlaced+"/"+noOfSubjects);
            System.out.println("Looking for "+subject.code);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int year = (Integer.parseInt(subject.sem) +1)/2; 
        int batch = year - 2;
        DaySlots daySlots;
        boolean loop = true;
        String type = subject.type;
        daySlots = getRandomDaySlot(batch);
        //JOptionPane.showMessageDialog(null, totalPlaced, "Total "+totalPlaced, JOptionPane.OK_OPTION);
        if(type.equals("lab")){
            System.out.println("subject is lab");
            while(loop){
            //if afternoon is free
                System.out.println(("dayslot:"+daySlots.daySlotId+",slot:4 isitFilled :"+daySlots.slots[4].isItFilled));
                System.out.println(("dayslot:"+daySlots.daySlotId+",slot:5 isitFilled :"+daySlots.slots[5].isItFilled));
                System.out.println(("dayslot:"+daySlots.daySlotId+",slot:6 isitFilled :"+daySlots.slots[6].isItFilled));
            if(!((daySlots.slots[4].isItFilled)||(daySlots.slots[5].isItFilled)||(daySlots.slots[6].isItFilled))){
                
                if(isEngagedForOthers(batch,daySlots.daySlotId,4)){
                    if(isAfternoonGotOver()){
                        if(daySlots.slots[1].isItFilled|| daySlots.slots[2].isItFilled || daySlots.slots[3].isItFilled){
                            daySlots = getRandomDaySlot(batch);
                            continue;
                        }
                        else{
                            if((!daySlots.slots[4].isItFilled&&daySlots.slots[5].isItFilled&&daySlots.slots[6].isItFilled)){
                                place(subject, batch, daySlots.daySlotId, 1);
                                loop = false;
                            }
                            else {
                                
                        daySlots = getRandomDaySlot(batch);
                        continue;
                            }
                            
                        }
                        
                    }
                    else{
                        daySlots = getRandomDaySlot(batch);
                        continue;
                    }
                }
                else{
                    place(subject,batch,daySlots.daySlotId,4);
                    loop = false;
                }
            }
            else{// afternoon is not free
                if(isAfternoonGotOver()){
                    if(daySlots.slots[1].isItFilled|| daySlots.slots[2].isItFilled || daySlots.slots[3].isItFilled){
                        daySlots = getRandomDaySlot(batch);
                        continue;
                    }
                    else{//If it is free
                        place(subject,batch,daySlots.daySlotId,1);
                        loop = false;
                    }
                }else{
                    daySlots = getRandomDaySlot(batch);
                    continue;
                }
            }
        }
        
        }
        else{//type is theory
            System.out.println("subject is theory");
            int noOfAttempts = 0;
            int whileLoopCount = 0;
            loop = true;
             int sl =(int) (Math.random() * 6);
             System.out.println("value of sl is "+sl);
            while(loop){
                whileLoopCount++;
                if(whileLoopCount > 500){
                    boolean success = false;
                    for(int i=0;i<6;i++){
                        System.out.println("***********************************************************");
                    }
                    display();
                    System.out.println("Cannot insert : "+subject.code);
                    batch = ((Integer.parseInt(subject.sem) +1) /2) -2 ;
                    for(int i =0;i<DaySlots.noOfDaysinAWeek && !success;i++){//for all daySlots where that subject is not there
                        if(!isSubIn(subject,batch,i)){
                            for(int j=0;j<Slot.noOfSlotsInADay && !success;j++){
                                if(canWePlace(subject, batch, i, j)){
                                    if(!timetable[batch].daySlots[i].slots[j].isItFilled){
                                        place(subject, batch, i, j);
                                        whileLoopCount = 0;
                                        loop = false;
                                        success = true;
                                    }
                                    else{
                                        Subject orgSub = timetable[batch].daySlots[i].slots[j].subject;
                                        if((orgSub.type.equals("theory"))&&(placeInFreeSlot(orgSub,batch))){
                                            //If Success 
                                            place(subject, batch, i, j);
                                            loop = false;
                                            success = true;
                                            whileLoopCount = 0;
                                        }
                                        
                                          
                                        
                                    }
                                    
                                }
                            }
                        }
                    }
                    
                    if(!success){
                        for(int i =0;i<DaySlots.noOfDaysinAWeek && !success;i++){//for all daySlots where that subject is not there
                            if(!isSubIn(subject,batch,i)){
                                for(int j=0;j<Slot.noOfSlotsInADay;j++){
                                    if(canWePlace(subject, batch, i, j)){
                                        timetable[batch].daySlots[i].slots[j].subject.slotsRemaining++;
                                        place(subject, batch, i, j);
                                        whileLoopCount = 0;
                                        loop = false;
                                        success = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    
                    if(!success){
                        for(int i =0;i<DaySlots.noOfDaysinAWeek && !success;i++){//for all daySlots where that subject is not there
                            if(isSubIn(subject,batch,i)){
                                for(int j=0;j<Slot.noOfSlotsInADay &&!success;j++){
                                    if((!timetable[batch].daySlots[i].slots[j].isItFilled && (fillCount(batch,i) < 6)  )){
                                        for(int k =0;k<3;k++){
                                            if((batch!=k)&&(timetable[k].daySlots[i].slots[k].isItFilled )&&(!(timetable[k].daySlots[i].slots[k].subject.fid.equals(subject.fid)))){
                                                place(subject, batch, i, j);
                                                whileLoopCount = 0;
                                                loop = false;
                                                success = true;
                                                break;                                                
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                        
                    }
                    
                    
                    if(!success){
                        for(int i =0;i<DaySlots.noOfDaysinAWeek && !success;i++){//for all daySlots where that subject is there
                            if(isSubIn(subject,batch,i)){
                                for(int j=0;j<Slot.noOfSlotsInADay &&!success;j++){
                                    if((!timetable[batch].daySlots[i].slots[j].isItFilled )){
                                        for(int k =0;k<3;k++){
                                            if((batch!=k)&&(timetable[k].daySlots[i].slots[j].isItFilled )&&(!(timetable[k].daySlots[i].slots[j].subject.fid.equals(subject.fid)))){
                                               
                                                
                                                if(((j > 0) && (!timetable[batch].daySlots[i].slots[j-1].subject.fid.equals(subject.fid))&&(j < 5) && (!timetable[batch].daySlots[i].slots[j+1].subject.fid.equals(subject.fid)))){
                                                    place(subject, batch, i, j);
                                                    whileLoopCount = 0;
                                                    loop = false;
                                                    success = true;
                                                    break;
                                               }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                        
                    }
                                        if(!success){
                        for(int i =0;i<DaySlots.noOfDaysinAWeek && !success;i++){//for all daySlots where that subject is there
                            if(isSubIn(subject,batch,i)){
                                for(int j=0;j<Slot.noOfSlotsInADay &&!success;j++){
                                    if((!timetable[batch].daySlots[i].slots[j].isItFilled )){
                                        for(int k =0;k<3;k++){
                                            if((batch!=k)&&(timetable[k].daySlots[i].slots[j].isItFilled )&&(!(timetable[k].daySlots[i].slots[j].subject.fid.equals(subject.fid)))){
                                               
                                                
                                       //         if(((j > 0) && (!timetable[batch].daySlots[i].slots[j-1].subject.fid.equals(subject.fid))&&(j < 5) && (!timetable[batch].daySlots[i].slots[j+1].subject.fid.equals(subject.fid)))){
                                                    place(subject, batch, i, j);
                                                    whileLoopCount = 0;
                                                    loop = false;
                                                    success = true;
                                                    break;
                                         //      }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                        
                    }
                    
                    
                    if(!success){
                        
                        if(endCount++>5){
                            JOptionPane.showMessageDialog(null, "Unable to Genetate... \nPossible Causes:\n1. Unable to generate at the moment.. - Try again\n2. Too much Subjects or Lack of faculty", "Error", JOptionPane.OK_OPTION);
                            display();
                            System.exit(0);
                        }
                    }
                    break;

                }
                if((noOfAttempts++)>MAX_NO_ATTEMPS){
                    
                    subject = getRandomSubject();
                     batch = ((Integer.parseInt(subject.sem) +1) /2) -2 ;
                }
               System.out.println("value   of sl is "+sl);
           
                sl =(int) (Math.random() * 6);
                if(isSubIn(subject,batch,daySlots.daySlotId)){
                    daySlots = getRandomDaySlot(batch);
                   // sl =(int) Math.random() * 6;
//                    sl = ((daySlots.daySlotId * 4) + (++sl)) % Slot.noOfSlotsInADay;
//                    System.out.println("value of sl is "+sl);
                    continue;
                }
                else{
                    if(canWePlace(subject,batch,daySlots.daySlotId,sl)){
                        place(subject, batch, daySlots.daySlotId, sl);
                        loop = false;
                    }
                    else{
                     //   sl = (int) Math.random() * DaySlots.noOfSlots;
                        daySlots = getRandomDaySlot(batch);
                        continue;
                    }
                    
                    
                }
                

                System.out.println("Loop count = "+whileLoopCount);
                  
            }
        
            
        }
        
        
    
    }

    private DaySlots getRandomDaySlot(int batch) {
        
        int randomNo = (int)(Math.random()* DaySlots.noOfDaysinAWeek);
        System.out.println("Inside getRandomDaySlots ,generated random slot is "+randomNo);
        
        return timetable[batch].daySlots[randomNo];
                
    }

    private boolean isEngagedForOthers(int batch,int ds, int s) {
            System.out.println("Checking for isEngagedforOthers,batch: "+batch+" ,ds : "+ds+" , s : "+s);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    boolean returnvalue = false;
        for(int i = 0;i<noOfBatches;i++){
            if((i == 2 )&&(timetable[2].daySlots[ds].slots[s].isItFilled)&&(timetable[2].daySlots[ds].slots[s].subject.type.equals("sem"))){
                return false;
            }
            if(i!=batch ){
                if(timetable[i].daySlots[ds].slots[s].isItFilled  )
                    returnvalue = true;
            }
        }
            System.out.println("Return value from isEngagedtoothers is "+returnvalue);
        return returnvalue;
    }

    private void place(Subject subject,int b,int ds, int s) {
        
        
            System.out.println("Placing subject "+subject.code);
        if(subject.type.equals("lab")){
            timetable[b].daySlots[ds].slots[s].subject = subject;
            subject.slotsRemaining = 0;
            timetable[b].daySlots[ds].slots[s].isItFilled = true;
            timetable[b].daySlots[ds].slots[s+1].isItFilled = true;
            timetable[b].daySlots[ds].slots[s+2].isItFilled = true;
            timetable[b].daySlots[ds].slots[s+1].subject = subject; 
            timetable[b].daySlots[ds].slots[s+2].subject = subject;
        }
        else{
             timetable[b].daySlots[ds].slots[s].subject = subject;
            subject.slotsRemaining--;
            timetable[b].daySlots[ds].slots[s].isItFilled = true;
        }

            System.out.println("subject "+subject.code+" is placed in batch : "+b+", ds : "+ds+", s : "+s);
            System.out.println(subject.code+" has "+subject.slotsRemaining+" slots remining");
            System.out.println("Total subjects  placed : "+(++totalPlaced));
pb.setValue(totalPlaced);         
        
        
    }

    private boolean isAfternoonGotOver() {
            System.out.println("Checking for afternoon got ovrr");
        boolean returnvalue = true;
        if(((Integer.parseInt(listOfSubjects[0].sem)) % 2) == 0 ){
            return false;
            
        }
        for(int i = 0; i < 6; i++){
            if(!((timetable[0].daySlots[i].slots[4].isItFilled)||(timetable[1].daySlots[i].slots[4].isItFilled)||(timetable[2].daySlots[i].slots[4].isItFilled))){
                returnvalue = false;
                break;
            }
        }
            System.out.println("return value from isAfternoon got over is "+returnvalue);
        return returnvalue;
    
    }

   
    private boolean isSubIn(Subject subject, int batch, int daySlotId) {
            System.out.println("Checking weather "+subject.code+" is present in dayslot "+daySlotId);
        boolean returnvalue = false;
        for(int i=0;i<DaySlots.noOfSlots;i++){
            if(timetable[batch].daySlots[daySlotId].slots[i].subject == subject ){
                returnvalue = true;
                break;                
            }

        }
        if(returnvalue)     System.out.println("Present"); else     System.out.println("Not present");
        
        return returnvalue;
    }

    private boolean canWePlace(Subject subject, int batch, int daySlotId, int s) {
        System.out.println("Checking for can we place "+subject.code+" batch: "+batch+" , dayslot : "+daySlotId+" ,slot: "+s);
        boolean returnvalue = true;
        String fid = subject.fid;
        System.out.println("fid of "+subject.code+" is "+fid);
        for(int i =0;i< noOfBatches;i++){
            if(i !=batch ){
                //check for same time period
                if(timetable[i].daySlots[daySlotId].slots[s].isItFilled){
                    System.out.println("Checking with batch: "+i+",  dayslot: "+daySlotId+", slot:"+s);
                if((timetable[i].daySlots[daySlotId].slots[s].subject.fid.equals(fid))){
                    returnvalue = false;
                    break;
                }
                }
                //check for previous peroid
                if(s > 0){
                if((timetable[i].daySlots[daySlotId].slots[s-1].isItFilled) && timetable[i].daySlots[daySlotId].slots[s-1].subject.type.equals("theory")) {
                    System.out.println("Checking with batch: "+i+",  dayslot: "+daySlotId+", slot:"+(s-1));
                
                    if(timetable[i].daySlots[daySlotId].slots[s-1].subject.fid.equals(fid)){
                        returnvalue = false;
                        break;
                    }
                }
                }
                //check for next time period
                if(s < 6){
                if(timetable[i].daySlots[daySlotId].slots[s+1].isItFilled && timetable[i].daySlots[daySlotId].slots[s+1].subject.type.equals("theory") ){
                    System.out.println("Checking with batch: "+i+",  dayslot: "+daySlotId+", slot:"+(s+1));
                
                    if(timetable[i].daySlots[daySlotId].slots[s+1].subject.fid.equals(fid)){
                        returnvalue = false;
                        break;
                    }
                }
                }
//                if(daySlotId > 0  ){
//                if(timetable[i].daySlots[daySlotId-1].slots[s].isItFilled){
//                    System.out.println("Checking with batch: "+i+",  dayslot: "+(daySlotId-1)+", slot:"+s);
//                
//                    if(timetable[i].daySlots[daySlotId-1].slots[s].subject.fid.equals(fid)){
//                        returnvalue = false;
//                        break;
//                    }
//                }
//                }
//                if(daySlotId < 5){
//                if(timetable[i].daySlots[daySlotId+1].slots[s].isItFilled){
//                    System.out.println("Checking with batch: "+i+",  dayslot: "+(daySlotId+1)+", slot:"+s);
//                             
//                
//                    if(timetable[i].daySlots[daySlotId+1].slots[s].subject.fid.equals(fid)){
//                        returnvalue = false;
//                        break;
//                    }
//                }
//                }
            }
            else{// i is a batch
                if(timetable[i].daySlots[daySlotId].slots[s].isItFilled){
                    returnvalue = false;
                    break;
                }
                if(totalPlaced < 60){
                if(subjectInColumn(subject,batch,s)){
                    returnvalue = false;
                }
                }
            }
            
        }
        if(returnvalue)     System.out.println("Can place"); else     System.out.println("Cannot place");
        
        return returnvalue;
    }

    private boolean subjectInColumn(Subject subject, int batch, int s) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean returnvalue = false;
            for(int i =0;i < DaySlots.noOfDaysinAWeek;i++){
                if(timetable[batch].daySlots[i].slots[s].isItFilled  ){
                    if(timetable[batch].daySlots[i].slots[s].subject.code.equals(subject.code)){
                        returnvalue = true;
                    }
                    
            
                }
            }
    
    return returnvalue;
    }

    public void display() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("\t\t Timetable");
        for(int i = 0;i<3;i++){
            System.out.println("\n\t\t TimeTable of Batch "+(i+1));
            for(int j = 0; j < 6;j++){
                System.out.print("\n"+(j+1)+"");
                for(int k = 0;k<7;k++){
                    Subject s;
                    s = timetable[i].daySlots[j].slots[k].subject;
                    if(s == null){
                        System.out.print("\tXXXXXX");
                    }
                    else{
                        System.out.print("\t"+s.code);                        
                    }
                }
            }
            
        }
                            System.out.println("\n\n\nTotal Subject placed : "+totalPlaced);
                  
    }

    private void fillSaturday() {
   //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    for(int i = 0;i < 3; i++){
        timetable[i].daySlots[5].slots[4].isItFilled = true;
        timetable[i].daySlots[5].slots[5].isItFilled = true;
        timetable[i].daySlots[5].slots[6].isItFilled = true;
        timetable[i].daySlots[5].slots[6].subject = holiday;
        timetable[i].daySlots[5].slots[5].subject = holiday;
        timetable[i].daySlots[5].slots[4].subject = holiday;
        
        
          
    }
    }

   
  

    private boolean swapIfCan(Subject subject, int batch, Subject placedSub, int daySlotid, int sl) {
        boolean returnvalue = false;
        int freeDaySlot = 0,slot = 0;
        boolean notTaken = true;
           System.out.println("Inside Swap if can");
           System.out.println("batch "+batch);
           System.exit(0);
            
           System.out.println("Trying to swap "+subject.code+" and "+placedSub.code);
           //System.exit(0);
            for(int i = 0;i < 6;i++){
                for(int j = 0;j< 7;j++){
                    
                    if(timetable[batch].daySlots[i].slots[j].isItFilled == false && notTaken ){
                        freeDaySlot = i;
                        slot = j;
                         if((canWePlace(placedSub, batch, freeDaySlot, slot)) && (canWePlace(subject, batch, daySlotid, sl) )) 
                        {
                            System.out.println("Found free slot at batch: "+batch+" dayslot : "+daySlotid +"slot "+sl);
            
                             timetable[batch].daySlots[freeDaySlot].slots[slot].subject = placedSub;
                             timetable[batch].daySlots[freeDaySlot].slots[slot].isItFilled = true;
                             timetable[batch].daySlots[daySlotid].slots[sl].subject = subject;
                             subject.slotsRemaining--;
                             totalPlaced++;
                             returnvalue = true;
                             notTaken = false;
                        }
                    }
                }
            }
            
        
    
        return returnvalue;   }

    private void updateDataBase() {
st.setText("Saving into DataBase (3/3)");
  pb.setValue(0);
   pb.setMaximum(noOfBatches * DaySlots.noOfDaysinAWeek * Slot.noOfSlotsInADay);
        Connection con = null;
        int loopcount = 0;
        Statement stmt = null;
        String cod = "------";
        DBConnectTT.getConnection();
        con = DBConnectTT.connection;
        for(int i = 0;i < noOfBatches;i++){
            for(int j=0;j<DaySlots.noOfDaysinAWeek;j++){
                for(int k=0;k< Slot.noOfSlotsInADay;k++){
                    if(timetable[i].daySlots[j].slots[k].isItFilled){
                        cod = timetable[i].daySlots[j].slots[k].subject.code;
                    }
                    else{
                        cod = "------";
                    }
                    String q = "insert into "+TT_TT+" values ("+i+","+j+","+k+",'"+cod+"')";
                    try {
                        stmt = con.createStatement();
                        stmt.executeUpdate(q);
                    } catch (SQLException ex) {
                        Logger.getLogger(TimeTables.class.getName()).log(Level.SEVERE, null, ex);
                    }
pb.setValue(loopcount++);
                    
                    System.out.println("Inserting "+cod+" to database");
                }
            }
        }
   pb.setValue(pb.getMaximum());
     st.setText("Done...!");
    }

    private boolean placeInFreeSlot(Subject orgSub, int batch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int fillCount(int batch, int i) {
    int count = 0;
    for(int x = 0;x<7;x++){
        if(timetable[batch].daySlots[i].slots[x].isItFilled && ((timetable[batch].daySlots[i].slots[x].subject.type.equals("theory") ))){
            count++;
        }
    }
    return count;
    }

    private void checkTheSem() {
       // ttmf.setStatus("Checking for the semister...");
        if((Integer.parseInt(listOfSubjects[0].sem) % 2 == 0)){
            for(int i=0;i<6;i++){
                timetable[2].daySlots[i].slots[4].isItFilled = true;
                timetable[2].daySlots[i].slots[5].isItFilled = true;            
                timetable[2].daySlots[i].slots[6].isItFilled = true;
                
                timetable[2].daySlots[i].slots[4].subject = seminar;
                timetable[2].daySlots[i].slots[5].subject = seminar;
                timetable[2].daySlots[i].slots[6].subject = seminar;
                
                 
            }
//            if(Subject.noOfHoursPerSubject == 5){
//                for(int i=0;i<noOfSubjects;i++){
//                    if(Integer.parseInt(listOfSubjects[i].sem) == 8 ){
//                        listOfSubjects[i].slotsRemaining--;
//                    }
//                }
//            }
        }
        
        
            
       }

    private int getTotalSubjects() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    int c =0;
    Connection con = DBConnectTT.connection;
    Statement st= null;
    ResultSet rs = null;
    
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from "+DBInterfaceTT.TT_SUBJECT_DETAILS);
            while(rs.next()){
                c++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeTables.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    return c;
    }
    
    
}
