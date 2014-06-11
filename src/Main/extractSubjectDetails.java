package Main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ds.Subjects;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

public class extractSubjectDetails {

    boolean allExtractSuccess = true;

    /**
     */
    public Subjects s;

    public extractSubjectDetails() {

        s = new Subjects();
    }

    public Subjects getSubjectDetails(String inFile) {
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(inFile));
            String line;
            String idd = null;

            while ((line = br.readLine()) != null) {

                String[] n = line.split(",");
                if (n.length == 5) {
                    s.subCode.add(n[0]);
                }
                s.subName.add(n[1]);
                s.type.add(n[2]);
                s.sem.add(n[3]);
                s.fid.add(n[4]);

            }
            br.close();
            System.out.println("Extracted..");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            allExtractSuccess = false;
            //Logger.getLogger(EnterUsnForm.class.getName()).log(Level.SEVERE, null, e);
        }

        if (!allExtractSuccess) {
            JOptionPane.showMessageDialog(null, "Some names have not been added \n Please check the input file for the correct format");
        }
        return s;
    }
}
