/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Insurance;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import UI.MainLoginJFrame;
import java.awt.CardLayout;
import java.awt.Image;
import java.nio.file.Paths;
import javax.swing.ImageIcon;

/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class InsuranceAdminWorkArea extends javax.swing.JFrame {

    /**
     * Creates new form InsuranceAdminWorkArea
     */
    
    private Enterprise enterprise;
    private EcoSystem system;
    private Network network;
      private static final String AFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String aimagePath = AFILENAME+"/Images/sysadmin.png";
    
     private static final String logoFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String logoImagePath = logoFILENAME+"/Images/logout_blue.png";
    
     private static final String GFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String bgimagePath = GFILENAME+"/Images/my-gradient.png";
    public InsuranceAdminWorkArea(Enterprise enterprise, EcoSystem sys, Network network) {
        initComponents();
        ImageIcon aimgIcon = new ImageIcon(aimagePath);
        Image aI = aimgIcon.getImage();
        Image aDimg = aI.getScaledInstance(60, 60,Image.SCALE_SMOOTH);
        ImageIcon aImgThisImg = new ImageIcon(aDimg);
        lblI.setIcon(aImgThisImg);
        
         ImageIcon logoimgIcon = new ImageIcon(logoImagePath);
        Image lI = logoimgIcon.getImage();
        Image logoDimg = lI.getScaledInstance(30, 30,Image.SCALE_SMOOTH);
        ImageIcon logoImgThisImg = new ImageIcon(logoDimg);
        lblL.setIcon(logoImgThisImg);
        
          ImageIcon bimgIcon = new ImageIcon(bgimagePath);
        Image bI = bimgIcon.getImage();
        Image cDimg = bI.getScaledInstance(200, 800,Image.SCALE_SMOOTH);
        ImageIcon cImgThisImg = new ImageIcon(cDimg);
        lblB.setIcon(cImgThisImg);
        this.enterprise = enterprise;
        this.system= sys;
        this.network = network;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        controlPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblL = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblI = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();
        workArea = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        controlPanel.setPreferredSize(new java.awt.Dimension(200, 800));
        controlPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Manage Organisation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        controlPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 309, 178, -1));

        jButton2.setText("Manage Employee");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        controlPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 350, 178, -1));

        jButton4.setText("Manage Users");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        controlPanel.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 391, 178, -1));

        lblL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLMouseClicked(evt);
            }
        });
        controlPanel.add(lblL, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 520, 60, 40));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel1.setText("Insurance Admin");
        controlPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));
        controlPanel.add(lblI, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 90, 90));
        controlPanel.add(lblB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 730));

        jSplitPane1.setLeftComponent(controlPanel);

        workArea.setPreferredSize(new java.awt.Dimension(800, 800));
        workArea.setLayout(new java.awt.CardLayout());
        jSplitPane1.setRightComponent(workArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1054, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        ManageInsuranceOrganisation mcojp = new ManageInsuranceOrganisation(enterprise.getOrganizationDirectory(), workArea);
        workArea.add("ManageInsuaranceOrganizationJPanel", mcojp);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.next(workArea);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        ManageInsuranceEmployee mcojp = new ManageInsuranceEmployee(enterprise.getOrganizationDirectory(), workArea);
        workArea.add("ManageInsuaranceOrganizationJPanel", mcojp);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.next(workArea);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ManageInsuranceUser mcojp = new ManageInsuranceUser(enterprise, workArea);
        workArea.add("ManageInsuaranceOrganizationJPanel", mcojp);
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.next(workArea);
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void lblLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(system,network);
        ml.setVisible(true); 
    }//GEN-LAST:event_lblLMouseClicked

    /**
     * @param args the command line arguments
//     */
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
//            java.util.logging.Logger.getLogger(InsuranceAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(InsuranceAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(InsuranceAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(InsuranceAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new InsuranceAdminWorkArea().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblI;
    private javax.swing.JLabel lblL;
    private javax.swing.JPanel workArea;
    // End of variables declaration//GEN-END:variables
}
