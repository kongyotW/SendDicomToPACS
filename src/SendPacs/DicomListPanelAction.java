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
import java.util.Collection;
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
        
        dicomListPanel.getB_send().setEnabled(false);
    }
    
    public void setupButtonAction(){       
        dicomListPanel.getB_select_dicom().setActionCommand("SelectDCM");
        dicomListPanel.getB_select_dicom().addActionListener(this);
        
        dicomListPanel.getB_send().setActionCommand("SendDICOM");
        dicomListPanel.getB_send().addActionListener(this);
    }
                
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("SelectDCM")){    
            String userDir = System.getProperty("user.home");
            JFileChooser chooser= new JFileChooser(userDir +"/Desktop");    
            chooser.setMultiSelectionEnabled(false);
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            
            int choice = chooser.showOpenDialog(dicomListPanel);
            if (choice != JFileChooser.APPROVE_OPTION) {
                return;
            }                       
            Collection<File> dicomFiles = null;
            File selectedFiles = chooser.getSelectedFile();
            if(selectedFiles.isDirectory()){
                dicomFiles = org.apache.commons.io.FileUtils
                    .listFiles(selectedFiles.getAbsoluteFile(), new String[]{"dcm"}, true);
            }else{
                if(selectedFiles.getName().endsWith(".dcm")){
                    dicomFiles = new ArrayList();
                    dicomFiles.add(selectedFiles);
                }
            }              
            //Setup DataStructor...
            setupDataStructor(dicomFiles);
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
            table.getColumnModel().getColumn(2).setPreferredWidth(80);
            table.getColumnModel().getColumn(2).setMinWidth(80);
            table.getColumnModel().getColumn(2).setMaxWidth(80); 
            table.revalidate();
            table.repaint();     
            
            if(DicomListDataStruct.getInstance().getDicomList().size() > 0){
                dicomListPanel.getB_send().setEnabled(true);
            }
        }else if(ae.getActionCommand().equals("SendDICOM")){
            Thread t1 =new Thread(new Runnable() {
                @Override
                public void run() {
                    dicomListPanel.getB_select_dicom().setEnabled(false);
                    dicomListPanel.getB_send().setEnabled(false);
                    
                    ArrayList<String> dicomPathList = DicomListDataStruct.getInstance().getDicomList();
                    int successCouter = 0;
                    int numOfAllFile = dicomPathList.size();
                    for(int i=0; i<numOfAllFile;i++){
                        String dicomPath = dicomPathList.get(i);
                        File fileToSend = new File(dicomPath);
                        if(fileToSend.exists()){
                            HashMap dicomMapper = DicomListDataStruct.getInstance().getModeCtInfoHash();
//                            System.out.println("fPath To Send : " + dicomPathList.get(i));

                            boolean isSendComplete = false;                            
                            if(dicomMapper.get(dicomPath).equals(DicomListDataStruct.Status_Complete)){
                                //Skip Previous sending
                                isSendComplete = true;
                            }else{
                                if(ConfigConstant.PACS_CONECTION_INFO.IS_SEND_WITH_COMMIT){
                                    isSendComplete = PacsManager.getInstance().sendDICOMToPACSWithCommit(fileToSend);
                                }else{
                                    isSendComplete = PacsManager.getInstance().sendDICOMToPACS(fileToSend);
                                }
                            }
                            
                            if(isSendComplete){
                                dicomMapper.put(dicomPath, DicomListDataStruct.Status_Complete);
                                successCouter++;
                            }else{
                                dicomMapper.put(dicomPath, DicomListDataStruct.Status_Fail);
                            }
                            
                            dicomListPanel.setTextStatus("Complete ["+ successCouter + "|" +numOfAllFile +"]");
                            
                            System.out.println("isSendComplete : " + isSendComplete);
                            tableDicomModel.setDataTable();
                            dicomListPanel.getDataTable().revalidate();
                            dicomListPanel.getDataTable().repaint();  
                        }                
                    }
                     
                    dicomListPanel.getB_select_dicom().setEnabled(true);
                    dicomListPanel.getB_send().setEnabled(true);
                }
            });
            t1.start();            
        }
    }
    
    private void setupDataStructor(Collection<File> selectedDicomFiles){
        for(File f : selectedDicomFiles){
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
