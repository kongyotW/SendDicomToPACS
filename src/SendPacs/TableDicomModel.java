/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TableDicomModel extends AbstractTableModel {

    private final ArrayList cacheResult = new ArrayList();
    private final String[] m_colNames = {
            "<html><Center><B> Index </B></Center></html>",
            "<html><Center><B> Path </B></Center></html>",
            "<html><Center><B> Status </B></Center></html>"            
    };

    public TableDicomModel() {}
    
    public void setDataTable() {
        try {
            cacheResult.clear();

            int counter = 1;
            ArrayList<String> dicomList = DicomListDataStruct.getInstance().getDicomList();
            for(int i =0; i < dicomList.size(); i++){
                String pathDicom = dicomList.get(i);                                
                String inxStr = Integer.toString(counter);
                
                HashMap<String,Integer> dicomMapper = DicomListDataStruct.getInstance().getModeCtInfoHash();
                Integer status = dicomMapper.get(pathDicom);
                
                Object[] row = new Object[m_colNames.length];
                row[0] = inxStr;
                row[1] = pathDicom;
                
                   
                if(status == DicomListDataStruct.Status_Complete){
                    row[2] = "OK";
                }else if(status == DicomListDataStruct.Status_Fail){
                    row[2] = "FAIL";
                }else if(status == DicomListDataStruct.Status_Prepare){
                    row[2] = "PREPARE";
                }
                 
                cacheResult.add(row);
            }            
        } catch (Exception e) {
            System.out.println("Error Id card is " + e.getMessage());
            e.printStackTrace();
        }
    }//END public void setDataInTable(ResultSet rs) 

    @Override
    public int getRowCount() {
        return cacheResult.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < cacheResult.size()) {
            return ((Object[]) cacheResult.get(rowIndex))[columnIndex];
        } else {
            return null;
        }
    }//END public Object getValueAt(int rowIndex, int columnIndex)

    @Override
    public int getColumnCount() {
        return m_colNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return m_colNames[col];
    }
    //ADD GET CLASS OF COLUMN FOR INSERT IMAGE ICON IN TABLE REVIEW 2016-11-29

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getRowCount() > 0) {
            Object value = getValueAt(0, columnIndex);
            if (value != null) {
                return getValueAt(0, columnIndex).getClass();
            }
        }
        return super.getColumnClass(columnIndex);
    }
   
}
