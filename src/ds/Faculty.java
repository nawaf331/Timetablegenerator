/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ds;

import java.util.Vector;

/**
 *
 * @author raat
 */
public class Faculty {
    public Vector<String> fid ;
    public Vector<String> name;
    
    public Faculty(){
        fid = new Vector<String>() ;
        name = new Vector<String>();
    }
    public String getFID(int i){
        return fid.get(i);
    }
    
    public String getFName(int i){
        return name.get(i);
    }
    
}
