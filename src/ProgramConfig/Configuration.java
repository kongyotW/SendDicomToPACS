/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Configuration {
    private final String configFilePath = "config.json";
    public boolean readConfigFile(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(configFilePath));            
            JSONObject objAll = (JSONObject) obj;            
            JSONObject pacsConfig = (JSONObject) objAll.get("PACS");
               
            ConfigConstant.PACS_CONECTION_INFO.PACS_AE = (String) pacsConfig.get("PACS_AE");
            ConfigConstant.PACS_CONECTION_INFO.PACS_IP = (String) pacsConfig.get("PACS_IP");
            ConfigConstant.PACS_CONECTION_INFO.PACS_PORT = (String) pacsConfig.get("PACS_PORT");
            ConfigConstant.PACS_CONECTION_INFO.MODALITY = (String) pacsConfig.get("DICOM_EXPORT_MODALITY");            
            ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_AE = (String) pacsConfig.get("WORKSTATION_AE"); 
            ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_PORT = (String) pacsConfig.get("WORKSTATION_PORT");
            ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_HOST = (String) pacsConfig.get("WORKSTATION_HOST");
            
            
            ConfigConstant.PACS_CONECTION_INFO.IS_SEND_WITH_COMMIT = (boolean) pacsConfig.get("IS_SEND_WITH_COMMIT");
                        
            System.out.println("\t-----------Pacs---------"+ "\n"            
            +"\t PACS_AE : " + ConfigConstant.PACS_CONECTION_INFO.PACS_AE+ "\n"
            +"\t PACS_IP : " + ConfigConstant.PACS_CONECTION_INFO.PACS_IP + "\n"
            +"\t PACS_PORT : " + ConfigConstant.PACS_CONECTION_INFO.PACS_PORT + "\n"
            +"\t DICOM_EXPORT_MODALITY : " + ConfigConstant.PACS_CONECTION_INFO.MODALITY + "\n"
                    
            +"\t WORKSTATION_AE : " + ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_AE + "\n"     
            +"\t WORKSTATION_PORT : " + ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_PORT + "\n"                     
            +"\t WORKSTATION_HOST : " + ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_HOST + "\n"   
            );        
           
            
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }    

    public boolean saveConfigFile(){       
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(configFilePath));            
            JSONObject objAll = (JSONObject) obj;                       
            JSONObject pacsConfig = (JSONObject) objAll.get("PACS");             
            pacsConfig.put("PACS_AE", ConfigConstant.PACS_CONECTION_INFO.PACS_AE);
            pacsConfig.put("PACS_IP", ConfigConstant.PACS_CONECTION_INFO.PACS_IP);
            pacsConfig.put("PACS_PORT", ConfigConstant.PACS_CONECTION_INFO.PACS_PORT);
            pacsConfig.put("DICOM_EXPORT_MODALITY", ConfigConstant.PACS_CONECTION_INFO.MODALITY);    
            pacsConfig.put("WORKSTATION_AE", ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_AE); 
            pacsConfig.put("WORKSTATION_PORT", ConfigConstant.PACS_CONECTION_INFO.WORKSTATION_PORT);           
                                                                
            //Write into the file
            try (FileWriter file = new FileWriter(configFilePath)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String prettyJson = gson.toJson(objAll);
                file.write(prettyJson);                
                
                System.out.println("\t prettyJson : " + prettyJson);                
                System.out.println("Successfully updated json object to file...!!");
            }
                        
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return false;
    }        
//    
//    public static void main(String[] args) {
//        Configuration c = new Configuration();
////        c.readConfigFile();
//        c.saveConfigFile();
//    }
}
