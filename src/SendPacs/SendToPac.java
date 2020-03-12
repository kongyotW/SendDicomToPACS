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
public class SendToPac {    
    
    protected boolean isPACSisAvaliable(){    
        DcmEcho dcmEcho = new DcmEcho("UNDEFINE");      
        dcmEcho.setCalledAET(ConfigConstant.PACS_CONECTION_INFO.PACS_AE,true);                       
        dcmEcho.setRemoteHost(ConfigConstant.PACS_CONECTION_INFO.PACS_IP);
        dcmEcho.setRemotePort(Integer.parseInt(ConfigConstant.PACS_CONECTION_INFO.PACS_PORT));
        
        dcmEcho.setCalling(ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_AE);     
        dcmEcho.setLocalHost(ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_HOST);
        
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
            dcmsnd.setCalledAET(ConfigConstant.PACS_CONECTION_INFO.PACS_AE);
            dcmsnd.setRemoteHost(ConfigConstant.PACS_CONECTION_INFO.PACS_IP);
            dcmsnd.setRemotePort(Integer.parseInt(ConfigConstant.PACS_CONECTION_INFO.PACS_PORT));
            dcmsnd.setCalling(ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_AE);
            dcmsnd.setLocalPort(Integer.parseInt(ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_PORT));
            dcmsnd.setOfferDefaultTransferSyntaxInSeparatePresentationContext(false);
            dcmsnd.setSendFileRef(false);
            dcmsnd.setStorageCommitment(false);
            dcmsnd.setPackPDV(true);
            dcmsnd.setTcpNoDelay(true);

            dcmsnd.addFile(file);

            dcmsnd.configureTransferCapability();
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
