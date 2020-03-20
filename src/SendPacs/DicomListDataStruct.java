/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author cti
 */
public class DicomListDataStruct {    
    private static final DicomListDataStruct instance = new DicomListDataStruct();
    public static DicomListDataStruct getInstance() {
        return instance;
    }
    private DicomListDataStruct(){} 
    
    private ArrayList<String> DicomList = new ArrayList<String>();
    private final HashMap<String, Integer> ModeCtInfoHash =  new HashMap();//{Path-->Status}
//    public static enum Status{Prepare,Complete,Fail};    
    public static int Status_Prepare = 0;
    public static int Status_Complete = 1;
    public static int Status_Fail = 2;
    
    public ArrayList<String> getDicomList() {
        return DicomList;
    }

    public HashMap<String, Integer> getModeCtInfoHash() {
        return ModeCtInfoHash;
    }
}
