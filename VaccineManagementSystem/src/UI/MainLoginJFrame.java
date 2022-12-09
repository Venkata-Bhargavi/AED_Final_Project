/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.EventOrganisation;
import Business.Organization.Organization;
import Business.SubOrganization.SubOrganization;
import Business.UserAccount.UserAccount;
import UI.SystemAdminWorkArea.SystemAdminWorkArea;
import java.awt.CardLayout;
import static java.time.Clock.system;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class MainLoginJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainLoginJFrame
     */
    private EcoSystem system;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();
    private Network network;
    
    public MainLoginJFrame(){
        initComponents();
        system = dB4OUtil.retrieveSystem();
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }
    
    public MainLoginJFrame(EcoSystem system,Network network) {
        initComponents();
//        Storing the data to database
        dB4OUtil.storeSystem(system);
        this.system = system;
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
        
        
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
        titlebox = new javax.swing.JLabel();
        lbl_role = new javax.swing.JLabel();
        lbl_Password = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        comboBox_roleMenu = new javax.swing.JComboBox<>();
        pass_password = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        titlebox.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        titlebox.setForeground(new java.awt.Color(51, 51, 51));
        titlebox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlebox.setText("Vaccine Management System");

        lbl_role.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lbl_role.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbl_role.setText("User:");

        lbl_Password.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lbl_Password.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbl_Password.setText("Password:");

        lbl_username.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lbl_username.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbl_username.setText("Username:");

        txt_username.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        comboBox_roleMenu.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        comboBox_roleMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "System Admin" }));
        comboBox_roleMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_roleMenuActionPerformed(evt);
            }
        });

        pass_password.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        pass_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_passwordActionPerformed(evt);
            }
        });

        btn_login.setText("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(titlebox, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pass_password, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbl_role, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(comboBox_roleMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbl_username, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(421, 421, 421)
                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(titlebox, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_role, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBox_roleMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_username, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pass_password, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBox_roleMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_roleMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox_roleMenuActionPerformed

    private void pass_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pass_passwordActionPerformed

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // TODO add your handling code here:
        
//        String role = comboBox_roleMenu.getSelectedItem().toString();
        
//        for(Patient pat: patientlist.getPatientlist()){
//            this.patientCred.put(pat.getUsername(), pat.getPassword());
//        }
//        
//        for(Doctor doc: doctorlist.getDoctorlist()){
//            this.doctorCred.put(doc.getUsername(), doc.getPassword());
//        }
        //doctorCred = doctorlist.getDoctorCredentails();
        //patientCred = patientlist.getPatientCredentails();
        
        
//        if((role.equals("System Admin")) && (txt_username.getText().equals("admin"))  && (pass_password.getText().equals("admin"))){
//            this.setVisible(false);
//            SystemAdminWorkArea sa = new SystemAdminWorkArea(system);
////            JOptionPane.showMessageDialog(this, "USER Credentials are correct!");
//            sa.setVisible(true);
//        }

        if (txt_username.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Login Name.");
        } else if (pass_password.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Login Password.");
        } 
        else {

            // Get user name
            String userName = txt_username.getText();
            String password = pass_password.getText();

            //Checking whether is user is an instance of enterprise.
            UserAccount userAccount = system.getUserAccountDirectory().authenticateUser(userName, password);
            // Initilizing the enterprise, org, suborg and user as null as we don't yet know where the user is working.
            UserAccount userAccounttoFind = null;
            Enterprise userEnterprise = null;
            Organization userOrganization = null;
            SubOrganization userSubOrganization = null;

            //            Loop to find whether the user is in Enterprise / Org / Suborg
            if(userAccount == null){
                //                if the user is not found, then checking in each enterprise by each network
                for(Network network: system.getNetworkList()){
                    for(Enterprise ent: network.getEnterpriseDirectory().getEnterpriseList()){
                        userAccount = ent.getUserAccountDirectory().authenticateUser(userName, password);
                        if (userAccount == null) {
                            //if user is not found in the enterprse, we drill down into each each organization for each enterprise
                            for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
                                userAccount = org.getUserAccountDirectory().authenticateUser(userName, password);
                                if (userAccount == null) {
                                    if (org instanceof EventOrganisation && ((EventOrganisation) org).getEventSubOrganizationDirectory().getOrganizationList() != null) {
                                        // Similarly, checking in the suborg of each org.
                                        for (SubOrganization subOrganization : ((EventOrganisation) org).getEventSubOrganizationDirectory().getOrganizationList()) {
                                            userAccount = subOrganization.getUserAccountDirectory().authenticateUser(userName, password);
                                            if (userAccount != null) {
                                                userAccounttoFind = userAccount;
                                                userEnterprise = ent;
                                                userOrganization = org;
                                                this.network = network;
                                                userSubOrganization = subOrganization;
                                                System.out.println("organization ia an instance of EventOrganisation" + userAccount.getRole());
                                                System.out.println("organization is an instance of EventOrganisation" + userEnterprise);
                                                System.out.println("organization is an instance of EventOrganisation" + userSubOrganization);
                                                //System.out.println("organization instanceof EventOrganisation");
                                                break;
                                            }
                                        }
                                    }
                                    //                                    User found in a organization, so must be working in thespecific organization
                                    else if (userAccount != null) {
                                        userEnterprise = ent;
                                        userOrganization = org;
                                        this.network = network;
                                        System.out.println("organization is an instance of EventOrganisation" + userAccount.getRole());
                                        break;
                                        //}
                                }
                            } else if (userAccount != null) {
                                userEnterprise = ent;
                                userOrganization = org;
                                this.network = network;
                                break;
                            }
                        }
                    }
                    //                        Found the user in enterprise
                    else {
                        userEnterprise = ent;
                        this.network = network;
                        break;
                    }
                    if (userOrganization != null) {
                        break;
                    }
                }
                if (userEnterprise != null) {
                    break;
                }
                }
            }

        if(userAccount == null && userAccounttoFind == null){
            JOptionPane.showMessageDialog(null, "User not Found!, Please check Credentails!");
            return;
        }
        else if(userAccount == null && userAccounttoFind != null){
            JFrame workArea = userAccounttoFind.getRole().createWorkArea( userAccounttoFind, userOrganization, userEnterprise, system, network);
            workArea.setVisible(true);
            setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(null, "valid Credentails!");
            JFrame workArea = userAccount.getRole().createWorkArea( userAccounttoFind, userOrganization, userEnterprise, system, network);
            workArea.setVisible(true);
            setVisible(false);

        }
    }//GEN-LAST:event_btn_loginActionPerformed
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainLoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainLoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainLoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainLoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainLoginJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JComboBox<String> comboBox_roleMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_Password;
    private javax.swing.JLabel lbl_role;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JPasswordField pass_password;
    private javax.swing.JLabel titlebox;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}