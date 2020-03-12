/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

import ProgramConfig.Configuration;
import java.io.File;
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
        SendToPac sendToPac = new SendToPac();
        boolean isPACSAvaliable = sendToPac.isPACSisAvaliable();
        System.out.println("isPACSAvaliable : " + isPACSAvaliable);
        
        JFileChooser chooser= new JFileChooser();        
        int choice = chooser.showOpenDialog(null);
        if (choice != JFileChooser.APPROVE_OPTION) return;
        File chosenFile = chooser.getSelectedFile();        
        
        boolean isSendSuccess = sendToPac.sendDICOMToPACS(chosenFile);
        System.out.println("isSendSuccess : " + isSendSuccess);
        System.exit(0);
    }
}
