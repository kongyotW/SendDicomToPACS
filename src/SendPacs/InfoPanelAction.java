/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

import ProgramConfig.ConfigConstant;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Windows10
 */
public class InfoPanelAction implements ActionListener{

    private final InfoPanel infoPanel;

    public InfoPanelAction(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        this.setPanelInformation();
    }
    
    private void setPanelInformation(){
//          = (String) pacsConfig.get("PACS_AE");
//            ConfigConstant.PACS_CONECTION_INFO.PACS_IP = (String) pacsConfig.get("PACS_IP");
//            ConfigConstant.PACS_CONECTION_INFO.PACS_PORT = (String) pacsConfig.get("PACS_PORT");
//            ConfigConstant.PACS_CONECTION_INFO.MODALITY = (String) pacsConfig.get("DICOM_EXPORT_MODALITY");            
//            ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_AE = (String) pacsConfig.get("WORKSTATION_AE"); 
//            ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_PORT = (String) pacsConfig.get("WORKSTATION_PORT");
//            ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_HOST = (String) pacsConfig.get("WORKSTATION_HOST");
                        
        infoPanel.setTxt_ipPACS(ConfigConstant.PACS_CONECTION_INFO.PACS_IP);
        infoPanel.setTxt_portPACS(ConfigConstant.PACS_CONECTION_INFO.PACS_PORT);
        infoPanel.setTxt_aePACS(ConfigConstant.PACS_CONECTION_INFO.PACS_AE);
        
        infoPanel.setTxt_ipWorkstation(ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_HOST);
        infoPanel.setTxt_portWorkstation(ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_PORT);
        infoPanel.setTxt_aeWorkstation(ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_AE);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {        
    }
    
}
