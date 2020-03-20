/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

import ProgramConfig.Configuration;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Windows10
 */
public class MainRunner {
    public static void main(String[] args) {
        Configuration setting = new Configuration();
        boolean successReadConfig = setting.readConfigFile();
        if (!successReadConfig) {
            JOptionPane.showMessageDialog(null, "READ CONFIG FILE ERROR, SYSTEM EXIT.");
            System.exit(0);
        }
        
        InfoPanel infoPanel = new InfoPanel();
        InfoPanelAction infoPanelAction = new InfoPanelAction(infoPanel);
        
        DicomListPanel dicomListPanel = new DicomListPanel();
        DicomListPanelAction dicomListPanelAction = new DicomListPanelAction(dicomListPanel);
        dicomListPanelAction.setupButtonAction();
        
        MainPanel mainPanel = new MainPanel();
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(dicomListPanel, BorderLayout.CENTER);
        
        PacsManager.getInstance().setupInfoPanel(infoPanel);
        
        JDialog mainDialog = new JDialog();
        mainDialog.getContentPane().add(mainPanel);
        mainDialog.pack();
        mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainDialog.setModal(true);
        mainDialog.setVisible(true);
        
//        PacsManager sendToPac = new PacsManager(infoPanel);
//        boolean isPACSAvaliable = sendToPac.isPACSisAvaliable();
//        System.out.println("isPACSAvaliable : " + isPACSAvaliable);
//        
        
        System.exit(0);
    }
}
