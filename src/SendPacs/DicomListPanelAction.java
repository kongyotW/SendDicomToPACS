/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

import ProgramConfig.ConfigConstant;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JTable;

/**
 *
 * @author cti
 */
public class DicomListPanelAction implements ActionListener{
    private final DicomListPanel dicomListPanel;
    private final TableDicomModel tableDicomModel;
//    private boolean isCommitToPACS = false;
    
    public DicomListPanelAction(DicomListPanel dicomListPanel) {
        this.dicomListPanel = dicomListPanel;
        this.tableDicomModel = new TableDicomModel();
    }
    
    public void setupButtonAction(){
        dicomListPanel.getB_echo().setActionCommand("Echo");
        dicomListPanel.getB_echo().addActionListener(this);
        
        dicomListPanel.getB_select_dicom().setActionCommand("SelectDCM");
        dicomListPanel.getB_select_dicom().addActionListener(this);
        
        dicomListPanel.getB_send().setActionCommand("SendDICOM");
        dicomListPanel.getB_send().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("Echo")){
            
            boolean isConnectOK = PacsManager.getInstance().isPACSisAvaliable();
            
            if(isConnectOK) dicomListPanel.setTextStatus("Echo OK");
            else dicomListPanel.setTextStatus("Echo Fail!");
        }else if(ae.getActionCommand().equals("SelectDCM")){            
            JFileChooser chooser= new JFileChooser();    
            chooser.setMultiSelectionEnabled(true);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int choice = chooser.showOpenDialog(null);
            if (choice != JFileChooser.APPROVE_OPTION) {
                return;
            }            
//            File chosenFile = chooser.getSelectedFile();        
            File[] selectedFiles = chooser.getSelectedFiles();
            
            //Setup DataStructor...
            setupDataStructor(selectedFiles);
            tableDicomModel.setDataTable();
            
            JTable table = this.dicomListPanel.getDataTable(); 
            table.getTableHeader().setReorderingAllowed(false);           
            table.setModel(this.tableDicomModel);        
            if(this.tableDicomModel.getRowCount() <= 0){                               
                table.getSelectionModel().clearSelection();    
            }        
            table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(0).setMinWidth(50);
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(220);
            table.getColumnModel().getColumn(1).setMinWidth(220);
            table.getColumnModel().getColumn(2).setPreferredWidth(50);
            table.getColumnModel().getColumn(2).setMinWidth(50);
            table.getColumnModel().getColumn(2).setMaxWidth(50); 
            table.revalidate();
            table.repaint();
            
        }else if(ae.getActionCommand().equals("SendDICOM")){
            ArrayList<String> dicomPathList = DicomListDataStruct.getInstance().getDicomList();
            for(int i=0; i<dicomPathList.size();i++){
                String dicomPath = dicomPathList.get(i);
                File fileToSend = new File(dicomPath);
                if(fileToSend.exists()){
                    HashMap dicomMapper = DicomListDataStruct.getInstance().getModeCtInfoHash();
                    System.out.println("fPath To Send : " + dicomPathList.get(i));
                             
                    boolean isSendComplete = false;
                    if(ConfigConstant.PACS_CONECTION_INFO.IS_SEND_WITH_COMMIT){
                        isSendComplete = PacsManager.getInstance().sendDICOMToPACSWithCommit(fileToSend);
                    }else{
                        isSendComplete = PacsManager.getInstance().sendDICOMToPACS(fileToSend);
                    }                    
                    
                    if(isSendComplete){
                        dicomMapper.put(dicomPath, DicomListDataStruct.Status_Complete);
                    }else{
                        dicomMapper.put(dicomPath, DicomListDataStruct.Status_Fail);
                    }
                    System.out.println("isSendComplete : " + isSendComplete);
                }                
            }
            tableDicomModel.setDataTable();
            this.dicomListPanel.getDataTable().revalidate();
            this.dicomListPanel.getDataTable().repaint();   
        }
    }
    
    private void setupDataStructor(File[] selectedFiles){
        for(File f : selectedFiles){
            String fPath = f.getPath();                
            System.out.println("fPath : " + fPath);
            HashMap dicomMapper = DicomListDataStruct.getInstance().getModeCtInfoHash();
            
            if(!dicomMapper.containsKey(fPath)){
                DicomListDataStruct.getInstance().getDicomList().add(fPath);
                dicomMapper.put(fPath, DicomListDataStruct.Status_Prepare);
            }                        
        }        
    }
   
    
}
