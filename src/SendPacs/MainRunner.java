/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

import LogDisplay.TextAreaLogProgram;
import ProgramConfig.Configuration;
import QueryPacs.QueryDicomPanel;
import QueryPacs.QueryDicomPanelAction;
import Worklist.QueryWorklistPanel;
import Worklist.QueryWorklistPanelAction;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Windows10
 */
public class MainRunner {    

    public MainRunner() {
        Properties props = new Properties(); 
        try { 
            InputStream configStream = getClass().getResourceAsStream("/log4j.properties"); 
            props.load(configStream); 
            configStream.close(); 
        } catch (IOException e) { 
            System.out.println("Errornot laod configuration file "); 
        } 
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String filePathLog = "D://pacslog" + File.separator + format.format(
                    Calendar.getInstance().getTime()) + "-pacs.log";
        
        props.setProperty("log4j.appender.FILE.File", filePathLog); 
        LogManager.resetConfiguration(); 
        PropertyConfigurator.configure(props);
    }
    
    public static void main(String[] args) {  
        
        new MainRunner();
                
        Configuration setting = new Configuration();
        boolean successReadConfig = setting.readConfigFile();
        if (!successReadConfig) {
            JOptionPane.showMessageDialog(null, "READ CONFIG FILE ERROR, SYSTEM EXIT.");
            System.exit(0);
        }
                
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new TextAreaLogProgram().setVisible(true);
//            }
//        });        
        InfoPanel infoPanel = new InfoPanel();
        InfoPanelAction infoPanelAction = new InfoPanelAction(infoPanel);
        infoPanelAction.setupButtonAction();
        
        DicomListPanel dicomListPanel = new DicomListPanel();
        DicomListPanelAction dicomListPanelAction = new DicomListPanelAction(dicomListPanel);
        dicomListPanelAction.setupButtonAction();
        
        QueryDicomPanel queryDicomPanel = new QueryDicomPanel();
        QueryDicomPanelAction queryPanelAction = new QueryDicomPanelAction(queryDicomPanel, infoPanel);
        queryPanelAction.setupButtonAction();
        
        QueryWorklistPanel queryWorklistPanel = new QueryWorklistPanel();
        QueryWorklistPanelAction queryWorklistPanelAction = new QueryWorklistPanelAction(queryWorklistPanel, infoPanel);
        queryWorklistPanelAction.setupButtonAction();
                
        MainPanel mainPanel = new MainPanel();
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        
        SouthPanel southPanel = new SouthPanel();
        southPanel.getTab_pane().addTab("Send DICOM", dicomListPanel);
        southPanel.getTab_pane().addTab("Query Worklist", queryWorklistPanel);
        southPanel.getTab_pane().addTab("Query DICOM", queryDicomPanel);
        
        mainPanel.add(southPanel, BorderLayout.CENTER);   
        PacsManager.getInstance().setupInfoPanel(infoPanel);
        
        JDialog mainDialog = new JDialog();
        mainDialog.getContentPane().add(mainPanel);
        mainDialog.pack();
        mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        mainDialog.setModal(true);
        mainDialog.setTitle("Send DICOM to PACS [version 0.2]");
        mainDialog.setAlwaysOnTop(true);
        mainDialog.setVisible(true);                
//        PacsManager sendToPac = new PacsManager(infoPanel);
//        boolean isPACSAvaliable = sendToPac.isPACSisAvaliable();
//        System.out.println("isPACSAvaliable : " + isPACSAvaliable);
//                
//        System.exit(0);        
        
    }
}
