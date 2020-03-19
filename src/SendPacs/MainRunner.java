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
        
        JDialog mainDialog = new JDialog();
        mainDialog.getContentPane().add(infoPanel);
        mainDialog.pack();
        mainDialog.setVisible(true);
        
        SendToPac sendToPac = new SendToPac(infoPanel);
        boolean isPACSAvaliable = sendToPac.isPACSisAvaliable();
        System.out.println("isPACSAvaliable : " + isPACSAvaliable);
        
        JFileChooser chooser= new JFileChooser();        
        int choice = chooser.showOpenDialog(null);
        if (choice != JFileChooser.APPROVE_OPTION) {
            System.exit(0);
        }
        File chosenFile = chooser.getSelectedFile();        
        
        boolean isSendSuccess = sendToPac.sendDICOMToPACS(chosenFile);
        System.out.println("isSendSuccess : " + isSendSuccess);
        System.exit(0);
    }
}
