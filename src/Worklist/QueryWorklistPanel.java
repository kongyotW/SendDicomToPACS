/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Worklist;

import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author cti
 */
public class QueryWorklistPanel extends javax.swing.JPanel {
    
    /**
     * @return the text_Status
     */
    public javax.swing.JTextField getText_Status() {
        return text_Status;
    }

    public javax.swing.JButton getB_query() {
        return b_query;
    }   
    
    public JTextField gettxt_queryDate(){
        return txt_Date;
    }
        
    public JTextField gettxt_queryModality(){
        return txt_Modality;
    }
    /**
     * Creates new form QueryPanel
     */
    public QueryWorklistPanel() {
        initComponents();
        
        String today = LocalDate.now().toString();
        String tomorrow =  LocalDate.now().plusDays(1).toString();
        today = today.replaceAll("-", "");
        tomorrow = tomorrow.replaceAll("-", "");
        txt_Date.setText(today+ "-" +tomorrow);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Date = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_Modality = new javax.swing.JTextField();
        b_query = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        p_ExitProgram = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        text_Status = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Query Worklist Section", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        setMinimumSize(new java.awt.Dimension(600, 150));
        setPreferredSize(new java.awt.Dimension(600, 150));
        setLayout(new java.awt.BorderLayout());

        jPanel9.setMaximumSize(new java.awt.Dimension(193, 33));
        jPanel9.setLayout(new java.awt.GridLayout(2, 0));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Date : ");
        jPanel1.add(jLabel1);

        txt_Date.setMinimumSize(new java.awt.Dimension(250, 20));
        txt_Date.setPreferredSize(new java.awt.Dimension(250, 20));
        jPanel1.add(txt_Date);

        jPanel9.add(jPanel1);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setText("Modality :");
        jPanel3.add(jLabel2);

        txt_Modality.setMinimumSize(new java.awt.Dimension(250, 20));
        txt_Modality.setPreferredSize(new java.awt.Dimension(250, 20));
        jPanel3.add(txt_Modality);

        b_query.setText("Query Worklist");
        jPanel3.add(b_query);

        jPanel9.add(jPanel3);

        add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel2.setMaximumSize(new java.awt.Dimension(500, 40));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 40));

        p_ExitProgram.setMaximumSize(new java.awt.Dimension(147, 40));
        p_ExitProgram.setMinimumSize(new java.awt.Dimension(147, 40));
        p_ExitProgram.setPreferredSize(new java.awt.Dimension(147, 40));
        p_ExitProgram.setLayout(new javax.swing.BoxLayout(p_ExitProgram, javax.swing.BoxLayout.LINE_AXIS));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText(" Status : ");
        jLabel19.setMaximumSize(new java.awt.Dimension(60, 18));
        jLabel19.setMinimumSize(new java.awt.Dimension(60, 18));
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 18));
        p_ExitProgram.add(jLabel19);

        text_Status.setEditable(false);
        text_Status.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        text_Status.setMaximumSize(new java.awt.Dimension(400, 30));
        text_Status.setMinimumSize(new java.awt.Dimension(400, 30));
        text_Status.setPreferredSize(new java.awt.Dimension(400, 30));
        p_ExitProgram.add(text_Status);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p_ExitProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p_ExitProgram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        add(jPanel2, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_query;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel p_ExitProgram;
    private javax.swing.JTextField text_Status;
    private javax.swing.JTextField txt_Date;
    private javax.swing.JTextField txt_Modality;
    // End of variables declaration//GEN-END:variables
}
