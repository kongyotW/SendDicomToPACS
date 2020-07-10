/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryPacs;

import SendPacs.InfoPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.tool.dcmqr.DcmQR;

/**
 *
 * @author cti
 */
public class QueryPanelAction implements ActionListener {

    private final QueryPanel queryPanel;
    private final InfoPanel infoPanel;

    public QueryPanelAction(QueryPanel queryPanel, InfoPanel infoPanel) {
        this.queryPanel = queryPanel;
        this.infoPanel = infoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Query")) {
            this.queryBySeries();
        }
    }

    public void setupButtonAction() {
        queryPanel.getB_query().setActionCommand("Query");
        queryPanel.getB_query().addActionListener(this);

    }

    private boolean queryBySeries() {       
        queryPanel.gettxtA_searchResult().setText("");
        if(queryPanel.gettxt_queryModality().getText().equals("") ||
            queryPanel.gettxt_querySerieUID().getText().equals("")) {
            queryPanel.gettxtA_searchResult().append("Please Check Your Inputs");
            return false;
        }
        
        boolean isFound = false;
        DcmQR dcmqr = new DcmQR("DcmQR");
        dcmqr.setCalledAET(infoPanel.getTxt_aePACS().getText(), true);
        dcmqr.setRemoteHost(infoPanel.getTxt_ipPACS().getText());
        dcmqr.setRemotePort(Integer.parseInt(infoPanel.getTxt_portPACS().getText()));
        dcmqr.setCalling(infoPanel.getTxt_aeWorkstation().getText());
//        dcmqr.setLocalHost("192.168.39.219");
//        dcmqr.setLocalPort(104);
        
        dcmqr.getKeys();
        dcmqr.setDateTimeMatching(true);
        dcmqr.setCFind(true);
                
        dcmqr.setQueryLevel(DcmQR.QueryRetrieveLevel.STUDY);
        dcmqr.addReturnKey(new int[]{Tag.PatientID});
        dcmqr.addReturnKey(new int[]{Tag.PatientName});
        dcmqr.addReturnKey(new int[]{Tag.SeriesInstanceUID});
        
        dcmqr.addMatchingKey(new int[]{Tag.Modality},queryPanel.gettxt_queryModality().getText()); 
        dcmqr.addMatchingKey(new int[]{Tag.SeriesInstanceUID},queryPanel.gettxt_querySerieUID().getText()); 
        
        dcmqr.configureTransferCapability(true);

        List<DicomObject> result = null;
        try {
            dcmqr.start();
            dcmqr.open();
            result = dcmqr.query();   
//            System.out.println(result);//result.get(i) 
            queryPanel.gettxtA_searchResult().append(result.toString());
            if(result.size() > 0){
                isFound = true;                
            }
            queryPanel.gettxtA_searchResult().append("Result Size : " + result.size());
//            for(int i = 0;i<result.size();i++){       
//                DicomObject data_SPS = result.get(i).getNestedDicomObject(Tag.ScheduledProcedureStepSequence);
//                DicomObject data_RPS = result.get(i).getNestedDicomObject(Tag.RequestedProcedureCodeSequence);
//                DicomElement data_SPS_e = result.get(i).get(Tag.ScheduledProcedureStepSequence);
//                DicomObject data_SPS_0 = data_SPS_e.getDicomObject(1);
//                System.out.println("PATIENT ID : " + result.get(i).getString(Tag.PatientID) + " : NAME : " + result.get(i).getString(Tag.PatientName));                
//            }
//            System.out.println("Result Size : " + result.size());            
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        try {
            if (dcmqr != null) {
                dcmqr.stop();
                dcmqr.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isFound;
    }
}
