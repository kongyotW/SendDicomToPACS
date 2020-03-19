/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

/**
 *
 * @author Windows10
 */
public class InfoPanel extends javax.swing.JPanel {
   
    /**
     * Creates new form InfoPanel
     */
    public InfoPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p_pacsInfo = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        l_PleaseSpe = new javax.swing.JLabel();
        txt_ipPACS = new javax.swing.JTextField();
        l_PleaseSpe1 = new javax.swing.JLabel();
        txt_portPACS = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        l_teethNo = new javax.swing.JLabel();
        txt_aePACS = new javax.swing.JTextField();
        p_workstationInfo = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        l_PleaseSpe4 = new javax.swing.JLabel();
        txt_ipWorkstation = new javax.swing.JTextField();
        l_PleaseSpe5 = new javax.swing.JLabel();
        txt_portWorkstation = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        l_teethNo2 = new javax.swing.JLabel();
        txt_aeWorkstation = new javax.swing.JTextField();
        p_button = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        b_echo = new javax.swing.JButton();
        b_select_dicom = new javax.swing.JButton();
        b_send = new javax.swing.JButton();
        p_ExitProgram = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        text_Status = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(500, 300));
        setLayout(new java.awt.GridLayout(3, 0));

        p_pacsInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PACS Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        p_pacsInfo.setMaximumSize(new java.awt.Dimension(32767, 150));
        p_pacsInfo.setMinimumSize(new java.awt.Dimension(500, 150));
        p_pacsInfo.setPreferredSize(new java.awt.Dimension(272, 150));
        p_pacsInfo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        l_PleaseSpe.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_PleaseSpe.setText("IP Address :");
        l_PleaseSpe.setEnabled(false);
        l_PleaseSpe.setMaximumSize(new java.awt.Dimension(80, 25));
        l_PleaseSpe.setMinimumSize(new java.awt.Dimension(80, 25));
        l_PleaseSpe.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel8.add(l_PleaseSpe);

        txt_ipPACS.setMaximumSize(new java.awt.Dimension(160, 36));
        txt_ipPACS.setMinimumSize(new java.awt.Dimension(160, 36));
        txt_ipPACS.setPreferredSize(new java.awt.Dimension(160, 36));
        txt_ipPACS.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel8.add(txt_ipPACS);

        l_PleaseSpe1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_PleaseSpe1.setText("Port :");
        l_PleaseSpe1.setEnabled(false);
        l_PleaseSpe1.setMaximumSize(new java.awt.Dimension(50, 25));
        l_PleaseSpe1.setMinimumSize(new java.awt.Dimension(50, 25));
        l_PleaseSpe1.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel8.add(l_PleaseSpe1);

        txt_portPACS.setMaximumSize(new java.awt.Dimension(80, 36));
        txt_portPACS.setMinimumSize(new java.awt.Dimension(80, 36));
        txt_portPACS.setPreferredSize(new java.awt.Dimension(80, 36));
        txt_portPACS.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel8.add(txt_portPACS);

        p_pacsInfo.add(jPanel8);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        l_teethNo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_teethNo.setText("AE Title :");
        l_teethNo.setEnabled(false);
        l_teethNo.setMaximumSize(new java.awt.Dimension(80, 25));
        l_teethNo.setMinimumSize(new java.awt.Dimension(80, 25));
        l_teethNo.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel6.add(l_teethNo);

        txt_aePACS.setMaximumSize(new java.awt.Dimension(120, 36));
        txt_aePACS.setMinimumSize(new java.awt.Dimension(120, 36));
        txt_aePACS.setPreferredSize(new java.awt.Dimension(120, 36));
        txt_aePACS.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel6.add(txt_aePACS);

        p_pacsInfo.add(jPanel6);

        add(p_pacsInfo);

        p_workstationInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Workstation Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        p_workstationInfo.setMaximumSize(new java.awt.Dimension(32767, 150));
        p_workstationInfo.setMinimumSize(new java.awt.Dimension(500, 150));
        p_workstationInfo.setPreferredSize(new java.awt.Dimension(272, 150));
        p_workstationInfo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        l_PleaseSpe4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_PleaseSpe4.setText("IP Address :");
        l_PleaseSpe4.setEnabled(false);
        l_PleaseSpe4.setMaximumSize(new java.awt.Dimension(80, 25));
        l_PleaseSpe4.setMinimumSize(new java.awt.Dimension(80, 25));
        l_PleaseSpe4.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel10.add(l_PleaseSpe4);

        txt_ipWorkstation.setMaximumSize(new java.awt.Dimension(160, 36));
        txt_ipWorkstation.setMinimumSize(new java.awt.Dimension(160, 36));
        txt_ipWorkstation.setPreferredSize(new java.awt.Dimension(160, 36));
        txt_ipWorkstation.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel10.add(txt_ipWorkstation);

        l_PleaseSpe5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_PleaseSpe5.setText("Port :");
        l_PleaseSpe5.setEnabled(false);
        l_PleaseSpe5.setMaximumSize(new java.awt.Dimension(50, 25));
        l_PleaseSpe5.setMinimumSize(new java.awt.Dimension(50, 25));
        l_PleaseSpe5.setPreferredSize(new java.awt.Dimension(50, 25));
        jPanel10.add(l_PleaseSpe5);

        txt_portWorkstation.setMaximumSize(new java.awt.Dimension(80, 36));
        txt_portWorkstation.setMinimumSize(new java.awt.Dimension(80, 36));
        txt_portWorkstation.setPreferredSize(new java.awt.Dimension(80, 36));
        txt_portWorkstation.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel10.add(txt_portWorkstation);

        p_workstationInfo.add(jPanel10);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        l_teethNo2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_teethNo2.setText("AE Title :");
        l_teethNo2.setEnabled(false);
        l_teethNo2.setMaximumSize(new java.awt.Dimension(80, 25));
        l_teethNo2.setMinimumSize(new java.awt.Dimension(80, 25));
        l_teethNo2.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel11.add(l_teethNo2);

        txt_aeWorkstation.setMaximumSize(new java.awt.Dimension(120, 36));
        txt_aeWorkstation.setMinimumSize(new java.awt.Dimension(120, 36));
        txt_aeWorkstation.setPreferredSize(new java.awt.Dimension(120, 36));
        txt_aeWorkstation.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel11.add(txt_aeWorkstation);

        p_workstationInfo.add(jPanel11);

        add(p_workstationInfo);

        p_button.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        p_button.setMaximumSize(new java.awt.Dimension(32767, 150));
        p_button.setMinimumSize(new java.awt.Dimension(500, 150));
        p_button.setPreferredSize(new java.awt.Dimension(272, 150));
        p_button.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        b_echo.setText("Echo");
        jPanel9.add(b_echo);

        b_select_dicom.setText("Select");
        jPanel9.add(b_select_dicom);

        b_send.setText("Send");
        jPanel9.add(b_send);

        p_button.add(jPanel9, java.awt.BorderLayout.CENTER);

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

        text_Status.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        text_Status.setMaximumSize(new java.awt.Dimension(400, 30));
        text_Status.setMinimumSize(new java.awt.Dimension(400, 30));
        text_Status.setPreferredSize(new java.awt.Dimension(400, 30));
        p_ExitProgram.add(text_Status);

        p_button.add(p_ExitProgram, java.awt.BorderLayout.SOUTH);

        add(p_button);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_echo;
    private javax.swing.JButton b_select_dicom;
    private javax.swing.JButton b_send;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel l_PleaseSpe;
    private javax.swing.JLabel l_PleaseSpe1;
    private javax.swing.JLabel l_PleaseSpe4;
    private javax.swing.JLabel l_PleaseSpe5;
    private javax.swing.JLabel l_teethNo;
    private javax.swing.JLabel l_teethNo2;
    private javax.swing.JPanel p_ExitProgram;
    private javax.swing.JPanel p_button;
    private javax.swing.JPanel p_pacsInfo;
    private javax.swing.JPanel p_workstationInfo;
    private javax.swing.JTextField text_Status;
    private javax.swing.JTextField txt_aePACS;
    private javax.swing.JTextField txt_aeWorkstation;
    private javax.swing.JTextField txt_ipPACS;
    private javax.swing.JTextField txt_ipWorkstation;
    private javax.swing.JTextField txt_portPACS;
    private javax.swing.JTextField txt_portWorkstation;
    // End of variables declaration//GEN-END:variables


    public javax.swing.JTextField getTxt_ipPACS() {
        return txt_ipPACS;
    }

    public void setTxt_ipPACS(String txt_ip1) {
        this.txt_ipPACS.setText(txt_ip1);
    }

    public javax.swing.JTextField getTxt_portPACS() {
        return txt_portPACS;
    }

    public void setTxt_portPACS(String txt_portPACS) {
        this.txt_portPACS.setText(txt_portPACS);
    }


    public javax.swing.JTextField getTxt_aePACS() {
        return txt_aePACS;
    }


    public void setTxt_aePACS(String txt_aePACS) {
        this.txt_aePACS.setText(txt_aePACS);
    }

    public javax.swing.JTextField getTxt_ipWorkstation() {
        return txt_ipWorkstation;
    }

    public void setTxt_ipWorkstation(String txt_ipWorkstation) {
        this.txt_ipWorkstation.setText(txt_ipWorkstation);
    }

    public javax.swing.JTextField getTxt_portWorkstation() {
        return txt_portWorkstation;
    }

    public void setTxt_portWorkstation(String txt_portWorkstation) {
        this.txt_portWorkstation.setText(txt_portWorkstation);
    }

    public javax.swing.JTextField getTxt_aeWorkstation() {
        return txt_aeWorkstation;
    }

    public void setTxt_aeWorkstation(String txt_aeWorkstation) {
        this.txt_aeWorkstation.setText(txt_aeWorkstation);
    }

    public javax.swing.JButton getB_echo() {
        return b_echo;
    }

    public javax.swing.JButton getB_select_dicom() {
        return b_select_dicom;
    }

    public javax.swing.JButton getB_send() {
        return b_send;
    }
    
    public void setTextStatus(String statusVal){
        this.text_Status.setText(statusVal);
    }

}
