/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI.Distributor;

import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
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
public class ManageOrganization extends javax.swing.JPanel {

    /**
     * Creates new form ManageOrganization
     */
    private OrganizationDirectory directory;
    JPanel workArea;
     private static final String AFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String aimagePath = AFILENAME+"/Images/manage_org.png";
    public ManageOrganization(OrganizationDirectory directory,JPanel workArea) {
        initComponents();
        this.workArea = workArea;
        this.directory = directory;
        ImageIcon aimgIcon = new ImageIcon(aimagePath);
        Image aI = aimgIcon.getImage();
        Image aDimg = aI.getScaledInstance(90, 90,Image.SCALE_SMOOTH);
        ImageIcon aImgThisImg = new ImageIcon(aDimg);
        lblI.setIcon(aImgThisImg);
        populateTable();
        populateCombo();
    }
    private void populateTable(){
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();        
        model.setRowCount(0);        
        for (Organization organization : directory.getOrganizationList())  
        {
            Object[] row = new Object[1];
            row[0] = organization;
            model.addRow(row);
        }
    }
     private void populateCombo(){
         cbO.removeAllItems();
         cbO.addItem(Organization.Type.DistributionCenter);
         cbO.addItem(Organization.Type.SupplierOrganization);
         cbO.addItem(Organization.Type.DistributorBillingOrganization);
         
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbO = new javax.swing.JComboBox();
        btnAO = new javax.swing.JButton();
        lblI = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 156, 141));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manage Organization");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 813, -1));

        tbl.setBackground(new java.awt.Color(255, 156, 141));
        tbl.setForeground(new java.awt.Color(255, 255, 255));
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Name"
            }
        ));
        jScrollPane1.setViewportView(tbl);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 524, 100));

        jLabel2.setText("Organization type :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, -1, 20));

        cbO.setBackground(new java.awt.Color(255, 156, 141));
        cbO.setForeground(new java.awt.Color(255, 255, 255));
        cbO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOActionPerformed(evt);
            }
        });
        add(cbO, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 256, -1));

        btnAO.setBackground(new java.awt.Color(255, 156, 141));
        btnAO.setForeground(new java.awt.Color(255, 255, 255));
        btnAO.setText("Add Organization");
        btnAO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAOActionPerformed(evt);
            }
        });
        add(btnAO, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, -1, 30));
        add(lblI, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 550, 150, 130));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAOActionPerformed
        // TODO add your handling code here:
             Organization.Type type = (Organization.Type) cbO.getSelectedItem();
        directory.createOrganization(type);
        populateTable();
          JOptionPane.showMessageDialog(null, "Organization added successfully.", "Message", JOptionPane.INFORMATION_MESSAGE);
    
    }//GEN-LAST:event_btnAOActionPerformed

    private void cbOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAO;
    private javax.swing.JComboBox cbO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblI;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
