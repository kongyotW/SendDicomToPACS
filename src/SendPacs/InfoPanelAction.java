/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

import LogDisplay.TextAreaLogProgram;
import ProgramConfig.ConfigConstant;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Windows10
 */
public class InfoPanelAction implements ActionListener{

    private final InfoPanel infoPanel;
    private final JFrame logFrame = new TextAreaLogProgram();        
    
    public InfoPanelAction(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        this.setPanelInformation();
        
//        infoPanel.getB_echo_pacs().setVisible(false);
//        infoPanel.getB_ping_pacs().setVisible(false);
//        infoPanel.getTbutton_debug().setVisible(false);
    }
        
    private void setPanelInformation(){
        infoPanel.setTxt_ipPACS(ConfigConstant.PACS_CONECTION_INFO.PACS_IP);
        infoPanel.setTxt_portPACS(ConfigConstant.PACS_CONECTION_INFO.PACS_PORT);
        infoPanel.setTxt_aePACS(ConfigConstant.PACS_CONECTION_INFO.PACS_AE);
        
        infoPanel.setTxt_ipWorklist(ConfigConstant.PACS_CONECTION_INFO.WORKLIST_IP);
        infoPanel.setTxt_portWorklist(ConfigConstant.PACS_CONECTION_INFO.WORKLIST_PORT);
        infoPanel.setTxt_aeWorklist(ConfigConstant.PACS_CONECTION_INFO.WORKLIST_AE);
        
        infoPanel.setTxt_ipWorkstation(ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_HOST);
        infoPanel.setTxt_portWorkstation(ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_PORT);
        infoPanel.setTxt_aeWorkstation(ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_AE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
         if(ae.getActionCommand().equals("B_Ping_PACS")){
            boolean isPingOK = PacsManager.getInstance().isPingPACSOk();
            if(isPingOK) infoPanel.getL_status_pacs().setText("Ping OK");
            else infoPanel.getL_status_pacs().setText("Ping Fail!");
         }
         else if(ae.getActionCommand().equals("B_Echo_PACS")){
            boolean isConnectOK = PacsManager.getInstance().isPACSisAvaliable();            
            if(isConnectOK) infoPanel.getL_status_pacs().setText("Echo OK");
            else infoPanel.getL_status_pacs().setText("Echo Fail!");
         }
         else if(ae.getActionCommand().equals("B_Ping_Worklist")){
            boolean isPingOK = PacsManager.getInstance().isPingPACSOk();
            if(isPingOK) infoPanel.getL_status_worklist().setText("Ping OK");
            else infoPanel.getL_status_worklist().setText("Ping Fail!");
         }
         else if(ae.getActionCommand().equals("B_Echo_Worklist")){
            boolean isConnectOK = PacsManager.getInstance().isWorklistisAvaliable();            
            if(isConnectOK) infoPanel.getL_status_worklist().setText("Echo OK");
            else infoPanel.getL_status_worklist().setText("Echo Fail!");
         }else if(ae.getActionCommand().equals("ToggleB_Debug")){
             if(infoPanel.getTbutton_debug().isSelected()){
                 logFrame.setVisible(true);
             }else{
                 logFrame.setVisible(false);
             }
         }     
    }
    
    public void setupButtonAction(){
        infoPanel.getB_ping_pacs().setActionCommand("B_Ping_PACS");
        infoPanel.getB_ping_pacs().addActionListener(this);
        
        infoPanel.getB_echo_pacs().setActionCommand("B_Echo_PACS");
        infoPanel.getB_echo_pacs().addActionListener(this);
        
        infoPanel.getB_ping_worklist().setActionCommand("B_Ping_Worklist");
        infoPanel.getB_ping_worklist().addActionListener(this);
        
        infoPanel.getB_echo_worklist().setActionCommand("B_Echo_Worklist");
        infoPanel.getB_echo_worklist().addActionListener(this);
        
        infoPanel.getTbutton_debug().setActionCommand("ToggleB_Debug");
        infoPanel.getTbutton_debug().addActionListener(this);
    }
    
    
}
