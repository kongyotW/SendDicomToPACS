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
     * @return the tbutton_debug
     */
    public javax.swing.JToggleButton getTbutton_debug() {
        return tbutton_debug;
    }

    /**
     * @return the l_status_pacs
     */
    public javax.swing.JLabel getL_status_pacs() {
        return l_status_pacs;
    }

    /**
     * @return the l_status_worklist
     */
    public javax.swing.JLabel getL_status_worklist() {
        return l_status_worklist;
    }

    /**
     * @return the b_echo_pacs
     */
    public javax.swing.JButton getB_echo_pacs() {
        return b_echo_pacs;
    }

    /**
     * @return the b_echo_worklist
     */
    public javax.swing.JButton getB_echo_worklist() {
        return b_echo_worklist;
    }

    
    public javax.swing.JButton getB_ping_pacs() {
        return b_ping_pacs;
    }

    public javax.swing.JButton getB_ping_worklist() {
        return b_ping_worklist;
    }
   
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
        l_teethNo = new javax.swing.JLabel();
        txt_aePACS = new javax.swing.JTextField();
        b_ping_pacs = new javax.swing.JButton();
        b_echo_pacs = new javax.swing.JButton();
        l_status_pacs = new javax.swing.JLabel();
        p_worklistInfo = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        l_PleaseSpe2 = new javax.swing.JLabel();
        txt_ipWorklist = new javax.swing.JTextField();
        l_PleaseSpe8 = new javax.swing.JLabel();
        txt_portWorklist = new javax.swing.JTextField();
        l_teethNo1 = new javax.swing.JLabel();
        txt_aeWorklist = new javax.swing.JTextField();
        b_ping_worklist = new javax.swing.JButton();
        b_echo_worklist = new javax.swing.JButton();
        l_status_worklist = new javax.swing.JLabel();
        p_workstationInfo = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        l_PleaseSpe6 = new javax.swing.JLabel();
        txt_ipWorkstation = new javax.swing.JTextField();
        l_PleaseSpe7 = new javax.swing.JLabel();
        txt_portWorkstation = new javax.swing.JTextField();
        l_teethNo3 = new javax.swing.JLabel();
        txt_aeWorkstation = new javax.swing.JTextField();
        tbutton_debug = new javax.swing.JToggleButton();

        setMinimumSize(new java.awt.Dimension(750, 210));
        setPreferredSize(new java.awt.Dimension(750, 210));
        setLayout(new java.awt.GridLayout(3, 0));

        p_pacsInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PACS Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        p_pacsInfo.setMaximumSize(new java.awt.Dimension(32767, 150));
        p_pacsInfo.setMinimumSize(new java.awt.Dimension(500, 150));
        p_pacsInfo.setPreferredSize(new java.awt.Dimension(272, 150));
        p_pacsInfo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel8.setMinimumSize(new java.awt.Dimension(730, 35));
        jPanel8.setPreferredSize(new java.awt.Dimension(730, 35));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        l_PleaseSpe.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_PleaseSpe.setText("IP Address :");
        l_PleaseSpe.setEnabled(false);
        l_PleaseSpe.setMaximumSize(new java.awt.Dimension(70, 25));
        l_PleaseSpe.setMinimumSize(new java.awt.Dimension(70, 25));
        l_PleaseSpe.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel8.add(l_PleaseSpe);

        txt_ipPACS.setMaximumSize(new java.awt.Dimension(90, 28));
        txt_ipPACS.setMinimumSize(new java.awt.Dimension(90, 28));
        txt_ipPACS.setPreferredSize(new java.awt.Dimension(90, 28));
        txt_ipPACS.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel8.add(txt_ipPACS);

        l_PleaseSpe1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_PleaseSpe1.setText("Port :");
        l_PleaseSpe1.setEnabled(false);
        l_PleaseSpe1.setMaximumSize(new java.awt.Dimension(30, 25));
        l_PleaseSpe1.setMinimumSize(new java.awt.Dimension(30, 25));
        l_PleaseSpe1.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel8.add(l_PleaseSpe1);

        txt_portPACS.setMaximumSize(new java.awt.Dimension(70, 28));
        txt_portPACS.setMinimumSize(new java.awt.Dimension(70, 28));
        txt_portPACS.setPreferredSize(new java.awt.Dimension(70, 28));
        txt_portPACS.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel8.add(txt_portPACS);

        l_teethNo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_teethNo.setText("AE Title :");
        l_teethNo.setEnabled(false);
        l_teethNo.setMaximumSize(new java.awt.Dimension(60, 25));
        l_teethNo.setMinimumSize(new java.awt.Dimension(60, 25));
        l_teethNo.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel8.add(l_teethNo);

        txt_aePACS.setMaximumSize(new java.awt.Dimension(120, 28));
        txt_aePACS.setMinimumSize(new java.awt.Dimension(120, 28));
        txt_aePACS.setPreferredSize(new java.awt.Dimension(120, 28));
        txt_aePACS.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel8.add(txt_aePACS);

        b_ping_pacs.setText("Ping");
        b_ping_pacs.setMaximumSize(new java.awt.Dimension(120, 28));
        b_ping_pacs.setMinimumSize(new java.awt.Dimension(80, 28));
        b_ping_pacs.setPreferredSize(new java.awt.Dimension(80, 28));
        jPanel8.add(b_ping_pacs);

        b_echo_pacs.setText("Echo");
        b_echo_pacs.setMaximumSize(new java.awt.Dimension(120, 28));
        b_echo_pacs.setMinimumSize(new java.awt.Dimension(80, 28));
        b_echo_pacs.setPreferredSize(new java.awt.Dimension(80, 28));
        jPanel8.add(b_echo_pacs);

        l_status_pacs.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_status_pacs.setEnabled(false);
        l_status_pacs.setMaximumSize(new java.awt.Dimension(80, 25));
        l_status_pacs.setMinimumSize(new java.awt.Dimension(80, 25));
        l_status_pacs.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel8.add(l_status_pacs);

        p_pacsInfo.add(jPanel8);

        add(p_pacsInfo);

        p_worklistInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Worklist Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        p_worklistInfo.setMaximumSize(new java.awt.Dimension(32767, 150));
        p_worklistInfo.setMinimumSize(new java.awt.Dimension(500, 150));
        p_worklistInfo.setPreferredSize(new java.awt.Dimension(272, 150));
        p_worklistInfo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel11.setMinimumSize(new java.awt.Dimension(730, 35));
        jPanel11.setPreferredSize(new java.awt.Dimension(730, 35));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        l_PleaseSpe2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_PleaseSpe2.setText("IP Address :");
        l_PleaseSpe2.setEnabled(false);
        l_PleaseSpe2.setMaximumSize(new java.awt.Dimension(70, 25));
        l_PleaseSpe2.setMinimumSize(new java.awt.Dimension(70, 25));
        l_PleaseSpe2.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel11.add(l_PleaseSpe2);

        txt_ipWorklist.setMaximumSize(new java.awt.Dimension(90, 28));
        txt_ipWorklist.setMinimumSize(new java.awt.Dimension(90, 28));
        txt_ipWorklist.setPreferredSize(new java.awt.Dimension(90, 28));
        txt_ipWorklist.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel11.add(txt_ipWorklist);

        l_PleaseSpe8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_PleaseSpe8.setText("Port :");
        l_PleaseSpe8.setEnabled(false);
        l_PleaseSpe8.setMaximumSize(new java.awt.Dimension(30, 25));
        l_PleaseSpe8.setMinimumSize(new java.awt.Dimension(30, 25));
        l_PleaseSpe8.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel11.add(l_PleaseSpe8);

        txt_portWorklist.setMaximumSize(new java.awt.Dimension(70, 28));
        txt_portWorklist.setMinimumSize(new java.awt.Dimension(70, 28));
        txt_portWorklist.setPreferredSize(new java.awt.Dimension(70, 28));
        txt_portWorklist.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel11.add(txt_portWorklist);

        l_teethNo1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_teethNo1.setText("AE Title :");
        l_teethNo1.setEnabled(false);
        l_teethNo1.setMaximumSize(new java.awt.Dimension(60, 25));
        l_teethNo1.setMinimumSize(new java.awt.Dimension(60, 25));
        l_teethNo1.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel11.add(l_teethNo1);

        txt_aeWorklist.setMaximumSize(new java.awt.Dimension(120, 28));
        txt_aeWorklist.setMinimumSize(new java.awt.Dimension(120, 28));
        txt_aeWorklist.setPreferredSize(new java.awt.Dimension(120, 28));
        txt_aeWorklist.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel11.add(txt_aeWorklist);

        b_ping_worklist.setText("Ping");
        b_ping_worklist.setMaximumSize(new java.awt.Dimension(120, 28));
        b_ping_worklist.setMinimumSize(new java.awt.Dimension(80, 28));
        b_ping_worklist.setPreferredSize(new java.awt.Dimension(80, 28));
        jPanel11.add(b_ping_worklist);

        b_echo_worklist.setText("Echo");
        b_echo_worklist.setMaximumSize(new java.awt.Dimension(120, 28));
        b_echo_worklist.setMinimumSize(new java.awt.Dimension(80, 28));
        b_echo_worklist.setPreferredSize(new java.awt.Dimension(80, 28));
        jPanel11.add(b_echo_worklist);

        l_status_worklist.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_status_worklist.setEnabled(false);
        l_status_worklist.setMaximumSize(new java.awt.Dimension(80, 25));
        l_status_worklist.setMinimumSize(new java.awt.Dimension(80, 25));
        l_status_worklist.setPreferredSize(new java.awt.Dimension(80, 25));
        jPanel11.add(l_status_worklist);

        p_worklistInfo.add(jPanel11);

        add(p_worklistInfo);

        p_workstationInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Workstation Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        p_workstationInfo.setMaximumSize(new java.awt.Dimension(32767, 150));
        p_workstationInfo.setMinimumSize(new java.awt.Dimension(500, 150));
        p_workstationInfo.setPreferredSize(new java.awt.Dimension(272, 150));
        p_workstationInfo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel10.setMinimumSize(new java.awt.Dimension(730, 35));
        jPanel10.setPreferredSize(new java.awt.Dimension(730, 35));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        l_PleaseSpe6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_PleaseSpe6.setText("IP Address :");
        l_PleaseSpe6.setEnabled(false);
        l_PleaseSpe6.setMaximumSize(new java.awt.Dimension(70, 25));
        l_PleaseSpe6.setMinimumSize(new java.awt.Dimension(70, 25));
        l_PleaseSpe6.setPreferredSize(new java.awt.Dimension(70, 25));
        jPanel10.add(l_PleaseSpe6);

        txt_ipWorkstation.setMaximumSize(new java.awt.Dimension(90, 28));
        txt_ipWorkstation.setMinimumSize(new java.awt.Dimension(90, 28));
        txt_ipWorkstation.setPreferredSize(new java.awt.Dimension(90, 28));
        txt_ipWorkstation.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel10.add(txt_ipWorkstation);

        l_PleaseSpe7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_PleaseSpe7.setText("Port :");
        l_PleaseSpe7.setEnabled(false);
        l_PleaseSpe7.setMaximumSize(new java.awt.Dimension(30, 25));
        l_PleaseSpe7.setMinimumSize(new java.awt.Dimension(30, 25));
        l_PleaseSpe7.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel10.add(l_PleaseSpe7);

        txt_portWorkstation.setMaximumSize(new java.awt.Dimension(70, 28));
        txt_portWorkstation.setMinimumSize(new java.awt.Dimension(70, 28));
        txt_portWorkstation.setPreferredSize(new java.awt.Dimension(70, 28));
        txt_portWorkstation.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel10.add(txt_portWorkstation);

        l_teethNo3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        l_teethNo3.setText("AE Title :");
        l_teethNo3.setEnabled(false);
        l_teethNo3.setMaximumSize(new java.awt.Dimension(60, 25));
        l_teethNo3.setMinimumSize(new java.awt.Dimension(60, 25));
        l_teethNo3.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel10.add(l_teethNo3);

        txt_aeWorkstation.setMaximumSize(new java.awt.Dimension(120, 28));
        txt_aeWorkstation.setMinimumSize(new java.awt.Dimension(120, 28));
        txt_aeWorkstation.setPreferredSize(new java.awt.Dimension(120, 28));
        txt_aeWorkstation.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel10.add(txt_aeWorkstation);

        tbutton_debug.setText("show debug ");
        jPanel10.add(tbutton_debug);

        p_workstationInfo.add(jPanel10);

        add(p_workstationInfo);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_echo_pacs;
    private javax.swing.JButton b_echo_worklist;
    private javax.swing.JButton b_ping_pacs;
    private javax.swing.JButton b_ping_worklist;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel l_PleaseSpe;
    private javax.swing.JLabel l_PleaseSpe1;
    private javax.swing.JLabel l_PleaseSpe2;
    private javax.swing.JLabel l_PleaseSpe6;
    private javax.swing.JLabel l_PleaseSpe7;
    private javax.swing.JLabel l_PleaseSpe8;
    private javax.swing.JLabel l_status_pacs;
    private javax.swing.JLabel l_status_worklist;
    private javax.swing.JLabel l_teethNo;
    private javax.swing.JLabel l_teethNo1;
    private javax.swing.JLabel l_teethNo3;
    private javax.swing.JPanel p_pacsInfo;
    private javax.swing.JPanel p_worklistInfo;
    private javax.swing.JPanel p_workstationInfo;
    private javax.swing.JToggleButton tbutton_debug;
    private javax.swing.JTextField txt_aePACS;
    private javax.swing.JTextField txt_aeWorklist;
    private javax.swing.JTextField txt_aeWorkstation;
    private javax.swing.JTextField txt_ipPACS;
    private javax.swing.JTextField txt_ipWorklist;
    private javax.swing.JTextField txt_ipWorkstation;
    private javax.swing.JTextField txt_portPACS;
    private javax.swing.JTextField txt_portWorklist;
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
    
     public javax.swing.JTextField getTxt_ipWorklist() {
        return txt_ipWorklist;
    }

    public void setTxt_ipWorklist(String txt_ip1) {
        this.txt_ipWorklist.setText(txt_ip1);
    }

    public javax.swing.JTextField getTxt_portWorklist() {
        return txt_portWorklist;
    }

    public void setTxt_portWorklist(String txt_portPACS) {
        this.txt_portWorklist.setText(txt_portPACS);
    }


    public javax.swing.JTextField getTxt_aeWorklist() {
        return txt_aeWorklist;
    }

    public void setTxt_aeWorklist(String txt_aePACS) {
        this.txt_aeWorklist.setText(txt_aePACS);
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
    

}
