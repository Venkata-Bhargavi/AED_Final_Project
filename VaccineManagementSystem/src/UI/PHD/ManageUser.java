/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI.PHD;

import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.awt.Image;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bhargavi
 */
public class ManageUser extends javax.swing.JPanel {

    /**
     * Creates new form ManageUser
     */
    
    JPanel workArea;
    Enterprise enterprise;
     private static final String AFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String aimagePath = AFILENAME+"/Images/manage_user.png";
    
            
    public ManageUser(Enterprise ent, JPanel workArea) {
        initComponents();
        this.workArea = workArea;
        this.enterprise = ent;
        popCBOrganization();
        populateDatatoTable();
        ImageIcon aimgIcon = new ImageIcon(aimagePath);
        Image aI = aimgIcon.getImage();
        Image aDimg = aI.getScaledInstance(90, 90,Image.SCALE_SMOOTH);
        ImageIcon aImgThisImg = new ImageIcon(aDimg);
        lblI.setIcon(aImgThisImg);
        
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        
    }
    
    
    public void popCBOrganization() {
        cbO.removeAllItems();

        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            cbO.addItem(organization);
        }
    }
    
    public void populateEmployeeComboBox(Organization organization){
        cbE.removeAllItems();
        
        for (Employee employee : organization.getEmployeeDirectory().getEmployeeList()){
            cbE.addItem(employee);
        }
    }
    
    private void populateRoleComboBox(Organization organization){
        cbR.removeAllItems();
        for (Role role : organization.getSupportedRole()){
            cbR.addItem(role);
        }
    }

    public void populateDatatoTable() {

        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();

        model.setRowCount(0);

        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            for (UserAccount ua : organization.getUserAccountDirectory().getUserAccountList()) {
                Object row[] = new Object[2];
                row[0] = ua;
                row[1] = ua.getRole();
                ((DefaultTableModel) tblUser.getModel()).addRow(row);
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

        lblPwd = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblUN = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        cbO = new javax.swing.JComboBox();
        cbE = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbR = new javax.swing.JComboBox();
        txtP = new javax.swing.JTextField();
        txtU = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblI = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(lblPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 419, 270, 23));

        jLabel4.setText("Employee :");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, -1, -1));
        add(lblUN, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 374, 270, 23));

        jLabel5.setText("UserName :");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, -1, -1));

        jLabel6.setText("Password :");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, -1, -1));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 156, 141));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create User Account");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 740, 51));

        btnCreate.setBackground(new java.awt.Color(255, 156, 141));
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        add(btnCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 100, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblUser.setBackground(new java.awt.Color(255, 156, 141));
        tblUser.setForeground(new java.awt.Color(255, 255, 255));
        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "User Name", "Role"
            }
        ));
        jScrollPane1.setViewportView(tblUser);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 520, 100));

        cbO.setBackground(new java.awt.Color(255, 156, 141));
        cbO.setForeground(new java.awt.Color(255, 255, 255));
        cbO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOActionPerformed(evt);
            }
        });
        add(cbO, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 200, -1));

        cbE.setBackground(new java.awt.Color(255, 156, 141));
        cbE.setForeground(new java.awt.Color(255, 255, 255));
        cbE.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEActionPerformed(evt);
            }
        });
        add(cbE, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 200, -1));

        jLabel2.setText("Organization :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, -1));

        cbR.setBackground(new java.awt.Color(255, 156, 141));
        cbR.setForeground(new java.awt.Color(255, 255, 255));
        cbR.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cbR, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, 200, -1));
        add(txtP, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 200, -1));
        add(txtU, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, 200, -1));

        jLabel3.setText("Role :");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, -1, -1));

        btnUpdate.setBackground(new java.awt.Color(255, 156, 141));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, 100, -1));

        btnDelete.setBackground(new java.awt.Color(255, 156, 141));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 490, 100, -1));
        add(lblI, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 510, 140, 120));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        
//        Checking the Password Regex
        String passwordToHash = txtP.getText();
        String password_ = null;
        boolean upCase = false;
        boolean loCase = false;
        boolean isDigit = false;
        boolean spChar = false;
        if (!passwordToHash.equals("")) {
            String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";

            password_ = passwordToHash.trim();
            char[] aC = password_.toCharArray();
            for (char c : aC) {
                if (Character.isUpperCase(c)) {
                    upCase = true;
                } else if (Character.isLowerCase(c)) {
                    loCase = true;
                } else if (Character.isDigit(c)) {
                    isDigit = true;
                } else if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                    spChar = true;
                }
            }
        }
        
//        Checking the Credentials and throwing respective errors.
        if (txtU.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter User Name.");
        } else if (txtP.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Password.");
        } 
         else if ((password_.length() > 7) || (password_.length() < 3)) {
            JOptionPane.showMessageDialog(null, "Password must have minimum lenght 3 and maximum length 7");
            lblPwd.setText("Password must have minimum lenght 3 and maximum length 7");
        } else if (upCase == false) {
            JOptionPane.showMessageDialog(null, "Password must have one Upper case");
            lblPwd.setText("Password must have one Upper case");
        } else if (loCase == false) {
            JOptionPane.showMessageDialog(null, "Password must have one Lower case");
            lblPwd.setText("Password must have one Lower case");
        } else if (isDigit == false) {
            JOptionPane.showMessageDialog(null, "Password must have one Digit");
            lblPwd.setText("Password must have one Digit");
        } else if (spChar == false) {
            JOptionPane.showMessageDialog(null, "Password must have one Special Character");
            lblPwd.setText("Password must have one Special Character");
        }
        else {
            String userName = txtU.getText();
            String password = txtP.getText();
            Organization organization = (Organization) cbO.getSelectedItem();
            Employee employee = (Employee) cbE.getSelectedItem();
            Role role = (Role) cbR.getSelectedItem();

            organization.getUserAccountDirectory().createUserAccount(userName, password, employee, role);
            JOptionPane.showMessageDialog(null, "New User created successfully!", "Warning", JOptionPane.INFORMATION_MESSAGE);
            populateDatatoTable();
            txtU.setText("");
            txtP.setText("");
        }
        
    }//GEN-LAST:event_btnCreateActionPerformed

    private void cbOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOActionPerformed
        // TODO add your handling code here:
        Organization organization = (Organization) cbO.getSelectedItem();
        if (organization != null){
            populateEmployeeComboBox(organization);
            populateRoleComboBox(organization);
        }
      
    }//GEN-LAST:event_cbOActionPerformed

    private void cbEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEActionPerformed
        // TODO add your handling code here:
         Organization organization = (Organization) cbO.getSelectedItem();
        Employee employee = (Employee) cbE.getSelectedItem();
        
        boolean empExist = organization.getUserAccountDirectory().findEmployee(employee);
        
        if(empExist){
//            JOptionPane.showMessageDialog(null, "Employee already exists!", "Dialogue", JOptionPane.INFORMATION_MESSAGE);
            btnCreate.setEnabled(false);
            btnUpdate.setEnabled(true);
            btnDelete.setEnabled(true);
            
            UserAccount ua = organization.getUserAccountDirectory().fetchEmployeeCredentials(employee);
            txtU.setText(ua.getUsername());
            txtP.setText(ua.getPassword());
            
//            txtUN.setText("");
        }
        else{
            btnCreate.setEnabled(true);
        }
    }//GEN-LAST:event_cbEActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:

        String passwordToHash = String.valueOf(txtP.getText());
        String password_ = null;
        boolean upCase = false;
        boolean loCase = false;
        boolean isDigit = false;
        boolean spChar = false;
        if (!passwordToHash.equals("")) {
            String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";

            password_ = passwordToHash.trim();
            char[] aC = password_.toCharArray();
            for (char c : aC) {
                if (Character.isUpperCase(c)) {
                    upCase = true;
                } else if (Character.isLowerCase(c)) {
                    loCase = true;
                } else if (Character.isDigit(c)) {
                    isDigit = true;
                } else if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                    spChar = true;
                }
            }
        }

        if (txtU.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter User Name.");
        }
        else if (txtP.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Password.");
        }
        else if ((password_.length() > 7) || (password_.length() < 3)) {
            JOptionPane.showMessageDialog(null, "Password must have minimum lenght 3 and maximum length 7");
            //            lblEP.setText("Password must have minimum lenght 3 and maximum length 7");
        } else if (upCase == false) {
            JOptionPane.showMessageDialog(null, "Password must have one Upper case");
            //            lblEP.setText("Password must have one Upper case");
        } else if (loCase == false) {
            JOptionPane.showMessageDialog(null, "Password must have one Lower case");
            //            lblEP.setText("Password must have one Lower case");
        } else if (isDigit == false) {
            JOptionPane.showMessageDialog(null, "Password must have one Digit");
            //            lblEP.setText("Password must have one Digit");
        } else if (spChar == false) {
            JOptionPane.showMessageDialog(null, "Password must have one Special Character");
            //            lblEP.setText("Password must have one Special Character");
        }

        else {

            String userName = txtU.getText();
            String password = txtP.getText();
            Organization organization = (Organization) cbO.getSelectedItem();
            Employee employee = (Employee) cbE.getSelectedItem();
            Role role = (Role) cbR.getSelectedItem();

            UserAccount ua = organization.getUserAccountDirectory().fetchEmployeeCredentials(employee);
            //            txtUN.setText(ua.getUsername());
            //            txtP.setText(ua.getPassword());
            ua.setUsername(userName);
            ua.setPassword(password);

            //            organization.getUserAccountDirectory().createUserAccount(userName, password, employee, role);
            txtU.setText("");
            txtP.setText("");
            populateDatatoTable();
            btnCreate.setEnabled(true);
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);

            JOptionPane.showMessageDialog(null, "Account Updated successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        Organization organization = (Organization) cbO.getSelectedItem();
        Employee employee = (Employee) cbE.getSelectedItem();
        Role role = (Role) cbR.getSelectedItem();

        boolean x  = organization.getEmployeeDirectory().deleteEmployee(employee);

        if(x){
            boolean y = organization.getUserAccountDirectory().deleteEmployeeCredentials(employee);
            txtU.setText("");
            txtP.setText("");
            btnCreate.setEnabled(true);
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Account Deleted successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
            //            popOrganizationComboBox();
            //            cbO.setSelectedIndex(-1);

            populateEmployeeComboBox(organization);
            populateRoleComboBox(organization);
            populateDatatoTable();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cbE;
    private javax.swing.JComboBox cbO;
    private javax.swing.JComboBox cbR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblI;
    private javax.swing.JLabel lblPwd;
    private javax.swing.JLabel lblUN;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtP;
    private javax.swing.JTextField txtU;
    // End of variables declaration//GEN-END:variables
}
