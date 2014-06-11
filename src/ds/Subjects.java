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
public class Subjects {

    public Vector<String> subCode;
    public Vector<String> subName, sem, type, fid;

    public Subjects() {
        fid = new Vector<String>();
        subName = new Vector<String>();
        type = new Vector<String>();
        sem = new Vector<String>();
        subCode = new Vector<String>();
    }

}
