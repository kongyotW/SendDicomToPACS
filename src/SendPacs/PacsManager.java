/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

import ProgramConfig.ConfigConstant;
import java.io.File;
import java.io.IOException;
import org.dcm4che2.net.ConfigurationException;
import org.dcm4che2.tool.dcmecho.DcmEcho;
import org.dcm4che2.tool.dcmsnd.DcmSnd;

/**
 *
 * @author user
 */
public class PacsManager {    
    private InfoPanel infoPanel;
    
    private static final PacsManager instance = new PacsManager();
    public static PacsManager getInstance() {
        return instance;
    }     
    private PacsManager(){}
    
    public void setupInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }
    
    protected boolean isPACSisAvaliable(){    
        DcmEcho dcmEcho = new DcmEcho("UNDEFINE");      
        
        dcmEcho.setCalledAET(infoPanel.getTxt_aePACS().getText(),true);                       
        dcmEcho.setRemoteHost(infoPanel.getTxt_ipPACS().getText());
        dcmEcho.setRemotePort(Integer.parseInt(infoPanel.getTxt_portPACS().getText()));
        
        dcmEcho.setCalling(infoPanel.getTxt_aeWorkstation().getText());     
        dcmEcho.setLocalHost(infoPanel.getTxt_ipWorkstation().getText());
        
        boolean dcmPacsStatus= false;
        try {
            dcmEcho.open();
            dcmPacsStatus=true;            
            dcmEcho.close();
            return dcmPacsStatus;
        } catch (IOException ex) {
//            System.out.println("ERR Connection #1= " + ex.getMessage());
        } catch (ConfigurationException ex) {
            System.out.println("ERR Connection #2= " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println("ERR Connection #3= " + ex.getMessage());
        }
        return dcmPacsStatus;        
    }
    
    protected boolean sendDICOMToPACS(File file) {
        DcmSnd dcmsnd = new DcmSnd();
        try {
            dcmsnd.setCalledAET(infoPanel.getTxt_aePACS().getText());
            dcmsnd.setRemoteHost(infoPanel.getTxt_ipPACS().getText());
            dcmsnd.setRemotePort(Integer.parseInt(infoPanel.getTxt_portPACS().getText()));
            
            dcmsnd.setCalling(infoPanel.getTxt_aeWorkstation().getText());
            dcmsnd.setLocalHost(infoPanel.getTxt_ipWorkstation().getText());
//            dcmsnd.setOfferDefaultTransferSyntaxInSeparatePresentationContext(false);
//            dcmsnd.setSendFileRef(false);
//            dcmsnd.setStorageCommitment(false);
//            dcmsnd.setPackPDV(true);
//            dcmsnd.setTcpNoDelay(true);

            dcmsnd.addFile(file);

//            dcmsnd.configureTransferCapability();
            dcmsnd.start();
            dcmsnd.open();
            dcmsnd.send();
            dcmsnd.close();
            return true;
        } catch (IOException | ConfigurationException | InterruptedException e) {            
            System.err.println("ERROR: Failed to start server for receiving "
                    + "Storage Commitment results:" + e.getMessage());
            return false;
        }
    }    
}
