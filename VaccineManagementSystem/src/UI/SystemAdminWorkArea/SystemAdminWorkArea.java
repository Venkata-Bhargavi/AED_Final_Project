/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.SystemAdminWorkArea;

import Business.EcoSystem;
import Business.Network.Network;
import UI.MainLoginJFrame;
import java.awt.CardLayout;
import java.awt.Image;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author bhargavi
 */
public class SystemAdminWorkArea extends javax.swing.JFrame{

    /**
     * Creates new form SystemAdminWorkArea
     */
    
    EcoSystem system;
    Network network;
    
    private static final String logoFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String logoImagePath = logoFILENAME+"/Images/logout_blue.png";
    
    private static final String GFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String bgimagePath = GFILENAME+"/Images/my-gradient.png";
    
    private static final String AFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String aimagePath = AFILENAME+"/Images/sysadmin.png";
    public SystemAdminWorkArea(EcoSystem system) {
        initComponents();
        this.system = system;
         ImageIcon logoimgIcon = new ImageIcon(logoImagePath);
        Image lI = logoimgIcon.getImage();
        Image logoDimg = lI.getScaledInstance(30, 30,Image.SCALE_SMOOTH);
        ImageIcon logoImgThisImg = new ImageIcon(logoDimg);
        lblL.setIcon(logoImgThisImg);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // for control panel
        ImageIcon bimgIcon = new ImageIcon(bgimagePath);
        Image bI = bimgIcon.getImage();
        Image cDimg = bI.getScaledInstance(200, 800,Image.SCALE_SMOOTH);
        ImageIcon cImgThisImg = new ImageIcon(cDimg);
        lblCB.setIcon(cImgThisImg);
        
        //for admin image
        
          ImageIcon aimgIcon = new ImageIcon(aimagePath);
        Image aI = aimgIcon.getImage();
        Image aDimg = aI.getScaledInstance(60, 60,Image.SCALE_SMOOTH);
        ImageIcon aImgThisImg = new ImageIcon(aDimg);
        lblA.setIcon(aImgThisImg);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        controlPanel = new javax.swing.JPanel();
        btnManageNetwork = new javax.swing.JButton();
        btnManageDisease = new javax.swing.JButton();
        btnManageEnterprise = new javax.swing.JButton();
        btnManageEnterpriseAdmin = new javax.swing.JButton();
        btnManageVaccine = new javax.swing.JButton();
        lblL = new javax.swing.JLabel();
        lblA = new javax.swing.JLabel();
        lblCB = new javax.swing.JLabel();
        workArea = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        controlPanel.setPreferredSize(new java.awt.Dimension(200, 800));
        controlPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnManageNetwork.setText("Manage Network");
        btnManageNetwork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageNetworkActionPerformed(evt);
            }
        });
        controlPanel.add(btnManageNetwork, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 218, 180, -1));

        btnManageDisease.setText("Manage Disease");
        btnManageDisease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageDiseaseActionPerformed(evt);
            }
        });
        controlPanel.add(btnManageDisease, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 259, 180, -1));

        btnManageEnterprise.setText("Manage Enterprise");
        btnManageEnterprise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageEnterpriseActionPerformed(evt);
            }
        });
        controlPanel.add(btnManageEnterprise, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 341, 180, -1));

        btnManageEnterpriseAdmin.setText("Manage Enterprise Admin");
        btnManageEnterpriseAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageEnterpriseAdminActionPerformed(evt);
            }
        });
        controlPanel.add(btnManageEnterpriseAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 382, -1, -1));

        btnManageVaccine.setText("Manage Vaccine");
        btnManageVaccine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageVaccineActionPerformed(evt);
            }
        });
        controlPanel.add(btnManageVaccine, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 300, 180, -1));

        lblL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout.png"))); // NOI18N
        lblL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLMouseClicked(evt);
            }
        });
        lblL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lblLKeyReleased(evt);
            }
        });
        controlPanel.add(lblL, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 610, 50, 30));
        controlPanel.add(lblA, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 90, 70));
        controlPanel.add(lblCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 800));

        splitPane.setLeftComponent(controlPanel);

        workArea.setPreferredSize(new java.awt.Dimension(1100, 800));
        workArea.setLayout(new java.awt.CardLayout());
        splitPane.setRightComponent(workArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnManageNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageNetworkActionPerformed
        // TODO add your handling code here:
//        ManageNetworkJPanel manageNetworkJPanel = new ManageNetworkJPanel(system);
////        userProcessContainer.add("manageNetworkJPanel", manageNetworkJPanel);
//        CardLayout layout = (CardLayout) workArea.getLayout();
//        layout.next(workArea);
        ManageNetworkJPanel mnp = new ManageNetworkJPanel(system, workArea);
        workArea.add("manageNetworkJPanel", mnp);
//        splitPane.setRightComponent(mnp);

        CardLayout cardlayout = (CardLayout) workArea.getLayout();
        cardlayout.next(workArea);        
        
    }//GEN-LAST:event_btnManageNetworkActionPerformed

    private void btnManageDiseaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageDiseaseActionPerformed
        // TODO add your handling code here:
        ManageDiseaseJPanel mdp = new ManageDiseaseJPanel(system, workArea);
//        workArea.add("managePatientJPanel", mdp);
//        splitPane.setRightComponent(mdp);
        workArea.add("manageDiseaseJPanel", mdp);
//        splitPane.setRightComponent(mnp);

        CardLayout cardlayout = (CardLayout) workArea.getLayout();
        cardlayout.next(workArea);        
    }//GEN-LAST:event_btnManageDiseaseActionPerformed

    private void btnManageVaccineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageVaccineActionPerformed
        // TODO add your handling code here:
        ManageVaccineJPanel mvp = new ManageVaccineJPanel(system, workArea);
        workArea.add("managePatientJPanel", mvp);
//        splitPane.setRightComponent(mvp);
        CardLayout cardlayout = (CardLayout) workArea.getLayout();
        cardlayout.next(workArea);      
    }//GEN-LAST:event_btnManageVaccineActionPerformed

    private void btnManageEnterpriseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageEnterpriseActionPerformed
        // TODO add your handling code here:
        ManageEnterpriseJPanel mep = new ManageEnterpriseJPanel(system, workArea);
        workArea.add("managePatientJPanel", mep);
//        splitPane.setRightComponent(mep);
        CardLayout cardlayout = (CardLayout) workArea.getLayout();
        cardlayout.next(workArea);  
    }//GEN-LAST:event_btnManageEnterpriseActionPerformed

    private void btnManageEnterpriseAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageEnterpriseAdminActionPerformed
        // TODO add your handling code here:
        
        ManageEnterpriseAdminJPanel meap = new ManageEnterpriseAdminJPanel(system, workArea);
        workArea.add("managePatientJPanel1", meap);
//        splitPane.setRightComponent(meap);
        CardLayout cardlayout = (CardLayout) workArea.getLayout();
        cardlayout.next(workArea);  
    }//GEN-LAST:event_btnManageEnterpriseAdminActionPerformed

    private void lblLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblLKeyReleased
        // TODO add your handling code here:
         this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(system,network);
        ml.setVisible(true);
    }//GEN-LAST:event_lblLKeyReleased

    private void lblLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(system,network);
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
//            java.util.logging.Logger.getLogger(SystemAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SystemAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SystemAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SystemAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SystemAdminWorkArea().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnManageDisease;
    private javax.swing.JButton btnManageEnterprise;
    private javax.swing.JButton btnManageEnterpriseAdmin;
    private javax.swing.JButton btnManageNetwork;
    private javax.swing.JButton btnManageVaccine;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblCB;
    private javax.swing.JLabel lblL;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JPanel workArea;
    // End of variables declaration//GEN-END:variables
}
