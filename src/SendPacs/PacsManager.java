/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

import ProgramConfig.ConfigConstant;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
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

    private PacsManager() {
    }

    public void setupInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    protected boolean isPingWorklistOk() {
        try {
            InetAddress ip = InetAddress.getByName(infoPanel.getTxt_ipWorklist().getText());
            if (ip.isReachable(5000)) {
                System.out.println("Host is reachable");
                return true;
            } else {
                System.out.println("Sorry ! We can't reach to this host");
                return false;
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(PacsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PacsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    protected boolean isPingPACSOk() {
        try {
            InetAddress ip = InetAddress.getByName(infoPanel.getTxt_ipPACS().getText());
            if (ip.isReachable(5000)) {
                System.out.println("Host is reachable");
                return true;
            } else {
                System.out.println("Sorry ! We can't reach to this host");
                return false;
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(PacsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PacsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    protected boolean isPACSisAvaliable() {
        DcmEcho dcmEcho = new DcmEcho("MIS");

        dcmEcho.setCalledAET(infoPanel.getTxt_aePACS().getText(), true);
        dcmEcho.setRemoteHost(infoPanel.getTxt_ipPACS().getText());
        dcmEcho.setRemotePort(Integer.parseInt(infoPanel.getTxt_portPACS().getText()));

        dcmEcho.setCalling(infoPanel.getTxt_aeWorkstation().getText());
//        dcmEcho.setLocalHost(infoPanel.getTxt_ipWorkstation().getText()); //O_o"

        boolean dcmPacsStatus = false;
        try {
            dcmEcho.open();
            dcmPacsStatus = true;
            dcmEcho.close();
            return dcmPacsStatus;
        } catch (IOException ex) {
            System.out.println("ERR Connection #1= " + ex.getMessage());
        } catch (ConfigurationException ex) {
            System.out.println("ERR Connection #2= " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println("ERR Connection #3= " + ex.getMessage());
        }
        return dcmPacsStatus;
    }

    protected boolean isWorklistisAvaliable() {
        DcmEcho dcmEcho = new DcmEcho("MIS");

        dcmEcho.setCalledAET(infoPanel.getTxt_aeWorklist().getText(), true);
        dcmEcho.setRemoteHost(infoPanel.getTxt_ipWorklist().getText());
        dcmEcho.setRemotePort(Integer.parseInt(infoPanel.getTxt_portWorklist().getText()));

        dcmEcho.setCalling(infoPanel.getTxt_aeWorkstation().getText());
//        dcmEcho.setLocalHost(infoPanel.getTxt_ipWorkstation().getText()); //O_o"

        boolean dcmPacsStatus = false;
        try {
            dcmEcho.open();
            dcmPacsStatus = true;
            dcmEcho.close();
            return dcmPacsStatus;
        } catch (IOException ex) {
            System.out.println("ERR Connection #1= " + ex.getMessage());
        } catch (ConfigurationException ex) {
            System.out.println("ERR Connection #2= " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println("ERR Connection #3= " + ex.getMessage());
        }
        return dcmPacsStatus;
    }

    //Send And Wait for PACS return message.
    protected boolean sendDICOMToPACSWithCommit(File file) {
        My_Dcmsnd dcmsnd = new My_Dcmsnd();
        dcmsnd.setCalledAET(infoPanel.getTxt_aePACS().getText());
        dcmsnd.setRemoteHost(infoPanel.getTxt_ipPACS().getText());
        dcmsnd.setRemotePort(Integer.parseInt(infoPanel.getTxt_portPACS().getText()));
        dcmsnd.setCalling(infoPanel.getTxt_aeWorkstation().getText());
        dcmsnd.setLocalPort(104);

//        dcmsnd.setOfferDefaultTransferSyntaxInSeparatePresentationContext(false);
//        dcmsnd.setSendFileRef(false);
        dcmsnd.setStorageCommitment(true);
//        dcmsnd.setPackPDV(true);
//        dcmsnd.setTcpNoDelay(true);

        dcmsnd.addFile(file);
        dcmsnd.configureTransferCapability();

        try {
            dcmsnd.start();
        } catch (Exception e) {
            System.err.println("ERROR: Failed to start server for receiving " + "Storage Commitment results:" + e.getMessage());
            return false;
        }
        try {
            dcmsnd.open();
        } catch (Exception e) {
            System.err.println("ERROR: Failed to establish association:" + e.getMessage());
            return false;
        }
        try {
            dcmsnd.send();
            boolean sendSuccess = false;
            if (dcmsnd.isStorageCommitment()) {
                boolean isCommitOk = dcmsnd.commit();
                System.out.println("isCommitOk : " + isCommitOk);
                if (isCommitOk) {
                    System.out.println("DCM Sending Success!");
                    int sendStatus = PacsManager.getInstance().getProgramStatus(); //dcmsnd.getStatus();
                    String userSeeStatus = PacsManager.getInstance().macthingSendStatus(sendStatus);
                    System.out.println("userSeeStatus : " + userSeeStatus);
                    sendSuccess = true;
                } else {
                    System.out.println("DCM Sending Fail!");
                    int sendStatus = PacsManager.getInstance().getProgramStatus(); //dcmsnd.getStatus();
                    String userSeeStatus = PacsManager.getInstance().macthingSendStatus(sendStatus);
                    System.out.println("userSeeStatus : " + userSeeStatus);
                }
            }
            dcmsnd.close();
            System.out.println("Released connection to " + "remoteAE");
            return sendSuccess;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dcmsnd.stop();
        }
        return false;
    }

    protected boolean sendDICOMToPACS(File file) {
        DcmSnd dcmsnd = new DcmSnd();
        try {
            dcmsnd.setCalledAET(infoPanel.getTxt_aePACS().getText());
            dcmsnd.setRemoteHost(infoPanel.getTxt_ipPACS().getText());
            dcmsnd.setRemotePort(Integer.parseInt(infoPanel.getTxt_portPACS().getText()));
            dcmsnd.setCalling(infoPanel.getTxt_aeWorkstation().getText());

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

    //<editor-fold defaultstate="collapsed" desc="Status...Send">
    private String userStatus = "unknown";
    private int programStatus = 0;

    public int getProgramStatus() {
        return programStatus;
    }

    public void setProgramStatus(int programStatus) {
        this.programStatus = programStatus;
    }

    public String macthingSendStatus(int pgStaus) {
        String sendStatusHex = String.format("%x", pgStaus);
        System.out.println("sendStatusHex ::: " + sendStatusHex);
        switch (pgStaus) {
            case 0:
                userStatus = "Success";
                break;
            case 0xA801:
                userStatus = "This file Already exist in PACS";
                break;
        }
        return this.userStatus;
    }
    //</editor-fold>

}
