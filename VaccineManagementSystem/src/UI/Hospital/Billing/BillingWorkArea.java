/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Hospital.Billing;

import javax.swing.JOptionPane;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.HospitalBillingOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.CDCBillingWorkRequest;
import Business.WorkQueue.DistributorBillingWorkRequest;
import Business.WorkQueue.InsuaranceBillingWorkRequest;
import Business.WorkQueue.PharmacyWorkRequest;
import Business.WorkQueue.WorkRequest;
import UI.MainLoginJFrame;
import java.awt.Image;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class BillingWorkArea extends javax.swing.JFrame {

    /**
     * Creates new form BillingWorkArea
     */
//    private JPanel userProcessContainer;
    private UserAccount account;
    private HospitalBillingOrganization hospitalBillingOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private Network network;
    private static final String logoFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String logoImagePath = logoFILENAME+"/Images/logout_blue.png";

    private static final String GFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String bgimagePath = GFILENAME+"/Images/my-gradient.png";
    public BillingWorkArea(UserAccount account, HospitalBillingOrganization hospitalBillingOrganization, Enterprise enterprise, EcoSystem business, Network network) {
        initComponents();
//        this.userProcessContainer = userProcessContainer;
        this.account= account;
        this.hospitalBillingOrganization = hospitalBillingOrganization;
        this.enterprise = enterprise;
        this.business = business;
        this.network = network;
        ImageIcon logoimgIcon = new ImageIcon(logoImagePath);
        Image lI = logoimgIcon.getImage();
        Image logoDimg = lI.getScaledInstance(30, 30,Image.SCALE_SMOOTH);
        ImageIcon logoImgThisImg = new ImageIcon(logoDimg);
        lblL.setIcon(logoImgThisImg);
        
          ImageIcon bimgIcon = new ImageIcon(bgimagePath);
        Image bI = bimgIcon.getImage();
        Image cDimg = bI.getScaledInstance(1100, 800,Image.SCALE_SMOOTH);
        ImageIcon cImgThisImg = new ImageIcon(cDimg);
        lblB.setIcon(cImgThisImg);
        populateTbl();
        populateDistBillTbl();
    }
    
    void populateTbl()
    {
        DefaultTableModel dtm = (DefaultTableModel)tblPharmacyBills.getModel();
       dtm.setRowCount(0);
       
       for(WorkRequest wr : network.getWorkQueue().getWorkRequestList())  
       {
           if(wr instanceof CDCBillingWorkRequest){
               Object[] row = new Object[7];
               row[0] = ((CDCBillingWorkRequest) wr).getName();
               row[1] = wr.getVaccine();
               row[2] = wr;
               row[3] = ((CDCBillingWorkRequest) wr).getPrice();
               row[4] = wr.getSender();
               row[5] = wr.getReceiver();
               row[6] = wr.getMessage();
               dtm.addRow(row);
           }
           if(wr instanceof InsuaranceBillingWorkRequest){
               Object[] row = new Object[7];
               row[0] = ((InsuaranceBillingWorkRequest) wr).getName();
               row[1] = wr.getVaccine();
               row[2] = wr;
               row[3] = ((InsuaranceBillingWorkRequest) wr).getPrice();
               row[4] = wr.getSender();
               row[5] = wr.getReceiver();
               row[6] = wr.getMessage();
               dtm.addRow(row);
           }
       }
    }
    
    
    void populateDistBillTbl()
    {
        DefaultTableModel model = (DefaultTableModel)distBillsJTbl.getModel();
        model.setRowCount(0);
        
        for(WorkRequest dwr : network.getWorkQueue().getWorkRequestList())
        {
            if(dwr instanceof DistributorBillingWorkRequest)
            {
                if(dwr.getEnterpeise().getName().equals(enterprise.getName()))
                {
                    Object row[]= new Object[6];
                    row[0]= dwr.getVaccine();
                    row[1]= ((DistributorBillingWorkRequest) dwr).getQty();
                    row[2]= ((DistributorBillingWorkRequest) dwr).getPrice();
                    row[3]= dwr;
                    row[4]= dwr.getReceiver();
                    row[5]= dwr.getSender();
                    model.addRow(row);
                }
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
        tblPharmacyBills = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        distBillsJTbl = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblL = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPharmacyBills.setBackground(new java.awt.Color(255, 156, 141));
        tblPharmacyBills.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblPharmacyBills.setForeground(new java.awt.Color(255, 255, 255));
        tblPharmacyBills.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient Name", "Vaccine", "Status", "Price", "Sender", "Receiver", "Message"
            }
        ));
        jScrollPane1.setViewportView(tblPharmacyBills);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 93, 904, 147));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BILLING WORK AREA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 6, 494, 43));

        distBillsJTbl.setBackground(new java.awt.Color(255, 156, 141));
        distBillsJTbl.setForeground(new java.awt.Color(255, 255, 255));
        distBillsJTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine", "Quantity", "Price", "Status", "Receiver", "Sender"
            }
        ));
        jScrollPane3.setViewportView(distBillsJTbl);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 296, 904, 112));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Distributor Bills ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 258, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("All Billing summary");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 61, -1, -1));

        jButton1.setText("Assign");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 420, -1, -1));

        jButton2.setText("Accept");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 420, -1, -1));

        jButton3.setText("Reject");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 420, -1, -1));

        lblL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLMouseClicked(evt);
            }
        });
        jPanel1.add(lblL, new org.netbeans.lib.awtextra.AbsoluteConstraints(791, 6, 49, 38));
        jPanel1.add(lblB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 670));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 922, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 672, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = distBillsJTbl.getSelectedRow();
        if(selectedRow<0)
        {
            JOptionPane.showMessageDialog(null, "Please select the row to assign", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else{
            DistributorBillingWorkRequest cwr = (DistributorBillingWorkRequest)distBillsJTbl.getValueAt(selectedRow,3);
            cwr.setReceiver(account);
            cwr.setStatus("Pending");
//            populateDistBillTbl();
            JOptionPane.showMessageDialog(null, "Assigned successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int cdcSelectedRow = distBillsJTbl.getSelectedRow();
        if(cdcSelectedRow<0)
        {
            JOptionPane.showMessageDialog(null, "Please select the row to accept", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            DistributorBillingWorkRequest cr = (DistributorBillingWorkRequest)distBillsJTbl.getValueAt(cdcSelectedRow, 3);
            cr.setStatus("Bill Paid");
//            populateDistBillTbl();
            JOptionPane.showMessageDialog(null, "Payment done!", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int cdcSelectedRow = distBillsJTbl.getSelectedRow();
        if(cdcSelectedRow<0)
        {
            JOptionPane.showMessageDialog(null, "Please select the row to accept", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            DistributorBillingWorkRequest cr = (DistributorBillingWorkRequest)distBillsJTbl.getValueAt(cdcSelectedRow, 3);
            cr.setStatus("Payment Rejected");
//            populateDistBillTbl();
            JOptionPane.showMessageDialog(null, "Payment has been rejected!", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void lblLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLMouseClicked
        // TODO add your handling code here:
         this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(business,network);
        ml.setVisible(true);
    }//GEN-LAST:event_lblLMouseClicked

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
//            java.util.logging.Logger.getLogger(BillingWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BillingWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BillingWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BillingWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BillingWorkArea().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable distBillsJTbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblL;
    private javax.swing.JTable tblPharmacyBills;
    // End of variables declaration//GEN-END:variables
}
