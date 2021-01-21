/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Worklist;

import SendPacs.InfoPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.data.UID;
import org.dcm4che2.net.ConfigurationException;
import org.dcm4che2.tool.dcmmwl.DcmMWL;

/**
 *
 * @author Windows10
 */
public class QueryWorklistPanelAction implements ActionListener{
    private final QueryWorklistPanel queryWorklistPanel;
    private final InfoPanel infoPanel;

    public QueryWorklistPanelAction(QueryWorklistPanel queryWorklistPanel, InfoPanel infoPanel) {
        this.queryWorklistPanel = queryWorklistPanel;
        this.infoPanel = infoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("QueryWorklist")) {
            quertWorklist();
        }
    }
    
    public void setupButtonAction() {
        queryWorklistPanel.getB_query().setActionCommand("QueryWorklist");
        queryWorklistPanel.getB_query().addActionListener(this);
    }
    
    private void quertWorklist(){
        DcmMWL dcmmwl = new DcmMWL("MIS");                 
        dcmmwl.setCalledAET(infoPanel.getTxt_aePACS().getText());
        dcmmwl.setCalling(infoPanel.getTxt_aeWorkstation().getText());
        dcmmwl.setRemoteHost(infoPanel.getTxt_ipPACS().getText());
        dcmmwl.setRemotePort(Integer.parseInt(infoPanel.getTxt_portPACS().getText()));
        
        String[] returnKey = new String[17];
        returnKey [0] = "OtherPatientNames";  
        returnKey [1] = "InstitutionName"; 
        returnKey [2] = "InstitutionAddress"; 
        returnKey [3] = "PatientAddress";
        returnKey [4] = "PatientComments";
        returnKey [5] = "PatientTelephoneNumbers";
        returnKey [6] = "ReferringPhysicianTelephoneNumbers";
        returnKey [7] = "StudyID";
        returnKey [8] = "StudyDate";
        returnKey [9] = "SeriesDate";
        returnKey [10] = "SeriesTime";
        returnKey [11] = "StudyTime";
        returnKey [12] = "StudyDescription";
        returnKey [13] = "SeriesInstanceUID";
        returnKey [14] = "SOPClassUID";
        returnKey [15] = "SOPInstanceUID";
        returnKey [16] = "PatientAge";       
        if (returnKey != null){
            for (int k = 0;k < returnKey.length; k++){
                dcmmwl.addReturnKey(Tag.toTagPath(returnKey[k]));
            }
            returnKey = null;
        }        
        dcmmwl.addSpsMatchingKey(Tag.Modality, queryWorklistPanel.gettxt_queryModality().getText());
     
//        String dateRange = this.getSearchStartDate() + "-" + this.getSearchEndDate();
        String dateRange = queryWorklistPanel.gettxt_queryDate().getText();
        System.out.println("Range date : " + dateRange);        
//        dcmmwl.addSpsMatchingKey(Tag.ScheduledProcedureStepStartDate, "*");
        dcmmwl.addSpsMatchingKey(Tag.ScheduledProcedureStepStartDate, dateRange);
//        dcmmwl.addSpsMatchingKey(Tag.ScheduledProcedureStepEndDate, this.getSearchEndDate());  
//        if (this.getSearchStartDate() != null) {
//            dcmmwl.addSpsMatchingKey(Tag.ScheduledProcedureStepStartDate, this.getSearchStartDate());  
//        }                        
//        String dateRange = this.getSearchStartDate()+"-"+this.getSearchEndDate();
//        System.out.println("range : " + dateRange);        
//        dcmmwl.addSpsMatchingKey(Tag.ScheduledProcedureStepStartDate, "2560");
//        dcmmwl.addSpsMatchingKey(Tag.ScheduledProcedureStepStartDate, dateRange);
//        dcmmwl.addSpsMatchingKey(Tag.ScheduledStationAETitle, "NECTEC01");
//        dcmmwl.addSpsMatchingKey(Tag.ScheduledProcedureStepEndDate, "20160701");          
//        String[] matchingKeysOptionQ = null;
//        if (matchingKeysOptionQ != null) {
//            for (int i = 1; i < matchingKeysOptionQ.length; i++, i++){
//                if (matchingKeysOptionQ [i] != null ){
//                    byte ptext[] = matchingKeysOptionQ[i]
//                            .getBytes(java.nio.charset.StandardCharsets.ISO_8859_1);
//                    String value = new String(ptext);
//                    dcmmwl.addMatchingKey(Tag.toTagPath(matchingKeysOptionQ[i - 1]), value);
//                }
//            }
//        }
        dcmmwl.setPackPDV(false);
        dcmmwl.setTcpNoDelay(false);
        String[] LE_TS = {UID.ExplicitVRLittleEndian, UID.ImplicitVRLittleEndian };
        dcmmwl.setTransferSyntax(LE_TS);

        try {
            dcmmwl.open();
        } catch (IOException | InterruptedException | ConfigurationException e) {
            System.err.println("ERROR: Failed to establish association:");
            e.printStackTrace();
        }
        List<DicomObject> result = null;
        try {
            result = dcmmwl.query();
            System.out.println("Sense number of modality : " + result.size());           
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            dcmmwl.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   
    }
}
