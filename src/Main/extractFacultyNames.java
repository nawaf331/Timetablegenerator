package Main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ds.Faculty;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class extractFacultyNames {

    boolean allExtractSuccess = true;

    /**
     * @param args the command line arguments
     */
    public static Vector<String> names, ids;
    public Faculty f;

    public extractFacultyNames() {
        names = new Vector<String>();
        ids = new Vector<String>();
        f = new Faculty();
    }

    public Faculty getNameList(String inFile) {
        BufferedReader br;
        Pattern id = Pattern.compile("^[a-zA-Z0-9]+");

        try {
            br = new BufferedReader(new FileReader(inFile));
            String line;
            String idd = null;

            while ((line = br.readLine()) != null) {
                Matcher m = id.matcher(line);
                System.out.println("line :" + line + "\nMatcher : " + m.toString());
                while (m.find()) {

                    idd = m.group(0);
                }
                String n = line.substring(idd.length(), line.length());
                System.out.println("idd : " + idd + idd.length() + "\nname" + n + n.length());
                f.fid.add(idd);
                f.name.add(n);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            allExtractSuccess = false;
            //Logger.getLogger(EnterUsnForm.class.getName()).log(Level.SEVERE, null, e);
        }

        if (!allExtractSuccess) {
            JOptionPane.showMessageDialog(null, "Some names have not been added \n Please check the input file for the correct format");
        }
        return f;
    }
}
