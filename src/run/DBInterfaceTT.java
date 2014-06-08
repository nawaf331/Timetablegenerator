/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

/**
 *
 * @author RAAT
 */
public interface DBInterfaceTT {

    String TT_FACULTY_DETAILS = "faculty_details";
    String TT_SUBJECT_DETAILS = "subject_details";
    String TT_CLASSROOM_DETAILS = "CLASSROOM_details";
    String TT_TT = "tt";

    //FACULTY_DETAILS table attributes
    String TT_F_ID = "fid";
    String TT_F_NAME = "name";
    
    //SUBJECT_DETAILS table attributes
    String TT_SUB_CODE = "code";
    String TT_SUB_SUBNAME = "subname";
    String TT_SUB_SEM = "sem";
    String TT_SUB_FID = "fid";
    String TT_SUB_TYPE = "type";
    
    //CLSS ROOM DETAILS
    
    String TT_CR_NO = "roomno";
    String TT_CR_TYPE = "typ";
    String TT_CR_STRENGTH = "strn";
    
    //TT
    String TT_batch = "batchno";
    String TT_rowid = "rowid";
    String TT_colid = "colid";
    String TT_code = "code";

}
