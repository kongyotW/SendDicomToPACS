/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendPacs;

/**
 *
 * @author cti
 */
public class DicomListPanel extends javax.swing.JPanel {
    
    /**
     * Creates new form DicomListPanel
     */
    public DicomListPanel() {
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

        jPanel9 = new javax.swing.JPanel();
        b_echo = new javax.swing.JButton();
        b_select_dicom = new javax.swing.JButton();
        b_send = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        DicomListScrollPane = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        p_ExitProgram = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        text_Status = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(600, 233));
        setPreferredSize(new java.awt.Dimension(600, 300));
        setLayout(new java.awt.BorderLayout());

        jPanel9.setMaximumSize(new java.awt.Dimension(193, 33));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        b_echo.setText("Echo");
        jPanel9.add(b_echo);

        b_select_dicom.setText("Select");
        jPanel9.add(b_select_dicom);

        b_send.setText("Send");
        jPanel9.add(b_send);

        add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel2.setMaximumSize(new java.awt.Dimension(500, 200));
        jPanel2.setMinimumSize(new java.awt.Dimension(500, 200));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 200));
        jPanel2.setLayout(new java.awt.BorderLayout());

        DicomListScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        DicomListScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dicom Prepare List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        DicomListScrollPane.setMaximumSize(new java.awt.Dimension(500, 400));
        DicomListScrollPane.setPreferredSize(new java.awt.Dimension(500, 400));

        dataTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        dataTable.setFocusable(false);
        dataTable.setGridColor(new java.awt.Color(204, 255, 255));
        dataTable.setPreferredSize(null);
        dataTable.setRowHeight(20);
        dataTable.setSelectionBackground(new java.awt.Color(0, 102, 153));
        dataTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        DicomListScrollPane.setViewportView(dataTable);

        jPanel2.add(DicomListScrollPane, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.setMaximumSize(new java.awt.Dimension(500, 40));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 40));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p_ExitProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p_ExitProgram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        add(jPanel1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane DicomListScrollPane;
    private javax.swing.JButton b_echo;
    private javax.swing.JButton b_select_dicom;
    private javax.swing.JButton b_send;
    private javax.swing.JTable dataTable;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel p_ExitProgram;
    private javax.swing.JTextField text_Status;
    // End of variables declaration//GEN-END:variables
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

    public javax.swing.JTable getDataTable() {
        return dataTable;
    }
}