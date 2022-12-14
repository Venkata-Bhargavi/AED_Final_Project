/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Hospital.Clinic;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.ClinicOrganization;
import Business.Organization.Organization;
import Business.Patient.Patient;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.CDCReportingWorkRequest;
import Business.WorkQueue.ReceptionWorkRequest;
import Business.WorkQueue.WorkRequest;
import Business.vaccine.Vaccine;
import UI.MainLoginJFrame;
import java.awt.Image;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class ClinicWorkArea extends javax.swing.JFrame {

    /**
     * Creates new form ClinicWorkarean
     */
    
    private UserAccount account;
    private ClinicOrganization clinicOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private Network network;
        private static final String logoFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String logoImagePath = logoFILENAME+"/Images/logout_blue.png";
    private static final String GFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String bgimagePath = GFILENAME+"/Images/my-gradient.png";
    public ClinicWorkArea(UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business,Network network) {
        initComponents();
         this.account = account;
        this.clinicOrganization = clinicOrganization;
        this.enterprise = enterprise;
        this.business = business;
        this.network = network;
        ImageIcon logoimgIcon = new ImageIcon(logoImagePath);
        Image lI = logoimgIcon.getImage();
        Image logoDimg = lI.getScaledInstance(30, 30,Image.SCALE_SMOOTH);
        ImageIcon logoImgThisImg = new ImageIcon(logoDimg);
        lblLogout.setIcon(logoImgThisImg);
        
          ImageIcon bimgIcon = new ImageIcon(bgimagePath);
        Image bI = bimgIcon.getImage();
        Image cDimg = bI.getScaledInstance(1100, 800,Image.SCALE_SMOOTH);
        ImageIcon cImgThisImg = new ImageIcon(cDimg);
        lblB.setIcon(cImgThisImg);
        populateCBVaccine();
        populateSelectedPatient();
        txtName.setEnabled(false);
        txtAge.setEnabled(false);
        txtGender.setEnabled(false);

        
    }
    
    void populateCBVaccine() {
        cbVaccine.removeAllItems();
        for (Vaccine vaccine : business.getVaccineDirectory().getVaccineList()) {
            cbVaccine.addItem(vaccine);
        }
    }

    void populateSelectedPatient() {
        DefaultTableModel dtm_ = (DefaultTableModel) jTablePatientDetails.getModel();
        dtm_.setRowCount(0);

        for (WorkRequest work : enterprise.getWorkQueue().getWorkRequestList()) {

            if (work instanceof ReceptionWorkRequest) {
                //  for(WorkRequest work : receiptionOrganization.getWorkQueue().getWorkRequestList())
                //{
                Object[] row = new Object[8];
                row[0] = ((ReceptionWorkRequest) work).getPatient().getName();
                row[1] = ((ReceptionWorkRequest) work).getPatient().getAge();
                row[2] = work;
                row[3] = ((ReceptionWorkRequest) work).getPatient().getGender();
                row[4] = ((ReceptionWorkRequest) work).getVaccine();
                row[5] = ((ReceptionWorkRequest) work).getRequestQuantity();
                row[6] = work.getSender();
                row[7] = work.getReceiver();
                dtm_.addRow(row);
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePatientDetails = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        cbVaccine = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnAssign = new javax.swing.JButton();
        btnFetch = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        lblLogout = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 800));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTablePatientDetails.setBackground(new java.awt.Color(255, 156, 141));
        jTablePatientDetails.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTablePatientDetails.setForeground(new java.awt.Color(255, 255, 255));
        jTablePatientDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient Name", "Patient Age", "Status", "Gender", "Vaccine", "Qty", "Sender", "Receiver"
            }
        ));
        jScrollPane1.setViewportView(jTablePatientDetails);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 133, 1062, 145));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Age:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 438, -1, -1));

        txtAge.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeActionPerformed(evt);
            }
        });
        jPanel1.add(txtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 437, 153, -1));

        txtName.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 393, 153, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Vaccine Required : ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 515, -1, -1));

        txtGender.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(txtGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 476, 153, -1));

        cbVaccine.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(cbVaccine, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 514, 153, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Gender:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 477, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Qty:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 559, -1, -1));

        txtQty.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 558, 153, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Name:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(441, 394, -1, -1));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clinic WorkArea");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 690, 42));

        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1028, 12, -1, -1));

        btnAssign.setText("Assign");
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });
        jPanel1.add(btnAssign, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 306, 86, -1));

        btnFetch.setText("Fetch Patient Details");
        btnFetch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFetchActionPerformed(evt);
            }
        });
        jPanel1.add(btnFetch, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 306, 188, -1));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 622, 86, -1));

        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
        });
        jPanel1.add(lblLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 70, 50));
        jPanel1.add(lblB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 810));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeActionPerformed

    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
        // TODO add your handling code here:
        
        int selectedRow = jTablePatientDetails.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select the row to assign the vaccine", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            ReceptionWorkRequest cwr = (ReceptionWorkRequest) jTablePatientDetails.getValueAt(selectedRow, 2);
            cwr.setReceiver(account);
            cwr.setStatus("Assigned");
            populateSelectedPatient();
        }
    }//GEN-LAST:event_btnAssignActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        
        int selectedRow = jTablePatientDetails.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Select patient details to prescribe a vaccine.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (txtName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Patient Name");
        } else if (txtAge.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Age");
        } else if (txtQty.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the Vaccine Quantity");
        } else {

            ReceptionWorkRequest rw = (ReceptionWorkRequest) jTablePatientDetails.getValueAt(selectedRow, 2);
            rw.setStatus("Requested Vaccine to Pharmacy");
            rw.setVaccine((Vaccine) cbVaccine.getSelectedItem());
            rw.setRequestQuantity(Integer.parseInt(txtQty.getText()));
            rw.setSender(account);
            rw.setPrice(((Vaccine) cbVaccine.getSelectedItem()).getPrice());
            Patient p = rw.getPatient();
            CDCReportingWorkRequest crwr = new CDCReportingWorkRequest();
            crwr.setPatient(p);
            crwr.setVaccine((Vaccine) cbVaccine.getSelectedItem());
            crwr.setRequestedQty(Integer.parseInt(txtQty.getText()));
            enterprise.getWorkQueue().getWorkRequestList().add(crwr);
            network.getWorkQueue().getWorkRequestList().add(crwr);
            populateSelectedPatient();
            txtName.setText("");
            txtAge.setText("");
            txtQty.setText("");
            JOptionPane.showMessageDialog(null, "Vaccine Requested successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);


    }                      
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnFetchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFetchActionPerformed
        // TODO add your handling code here:
        
        int selectedRow = jTablePatientDetails.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Select a patient to prescribe a vaccine.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            ReceptionWorkRequest rw = (ReceptionWorkRequest) jTablePatientDetails.getValueAt(selectedRow, 2);
            txtName.setText(rw.getPatient().getName());
            txtAge.setText(String.valueOf(rw.getPatient().getAge()));
            txtGender.setText(rw.getPatient().getGender());
        }
    }//GEN-LAST:event_btnFetchActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        
        this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(business,network);
        ml.setVisible(true); 
    }//GEN-LAST:event_jLabel7MouseClicked

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(business,network);
        ml.setVisible(true);
    }//GEN-LAST:event_lblLogoutMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ClinicWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ClinicWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ClinicWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ClinicWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ClinicWorkArea().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssign;
    private javax.swing.JButton btnFetch;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cbVaccine;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePatientDetails;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
