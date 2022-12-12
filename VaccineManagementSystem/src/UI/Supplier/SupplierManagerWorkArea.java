/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Supplier;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.SupplierOrganization;
import Business.Supplier.Supplier;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.DistributorWorkRequest;
import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import Business.vaccine.Vaccine;
import UI.MainLoginJFrame;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class SupplierManagerWorkArea extends javax.swing.JFrame {

    /**
     * Creates new form SupplierManagerWorkArea
     */
    
    
//    private JPanel userProcessContainer;
    private UserAccount account;
    private SupplierOrganization supplierOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private Supplier s;
    private Network network;
    
    
    private static final String logoFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String logoImagePath = logoFILENAME+"/Images/logout_blue.png";
    
    public SupplierManagerWorkArea(UserAccount account, SupplierOrganization supplierOrganization, Enterprise enterprise, EcoSystem business) {
        initComponents();
        
        this.account = account;
        this.supplierOrganization = supplierOrganization;
        this.enterprise = enterprise;
        this.business = business;
        this.network = null;
        
        for (Supplier supplier : supplierOrganization.getSupplierList().getSupplierList()) {
            if (account.getEmployee().getName().equals(supplier.getSupplierName())) {
                s = supplier;
                System.out.println("supplier name" + s.getSupplierName());
            }
        }
        System.out.println("busi" + business.getWorkQueue().getWorkRequestList().size());
        if (s.getWorkQueue() == null) {
            WorkQueue w = new WorkQueue();
            s.setWorkQueue(w);
        }
        
        ImageIcon logoimgIcon = new ImageIcon(logoImagePath);
        Image lI = logoimgIcon.getImage();
        Image logoDimg = lI.getScaledInstance(30, 30,Image.SCALE_SMOOTH);
        ImageIcon logoImgThisImg = new ImageIcon(logoDimg);
        lbllogout.setIcon(logoImgThisImg);

        populateRequestTbl();
        populateCombo();
        populateAvailable();
        
        
        
        txtQty.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
         
    }

    void populateCombo() {
        comboBox.removeAllItems();
        for (Vaccine vaccine : business.getVaccineDirectory().getVaccineList()) {
            comboBox.addItem(vaccine);
        }
    }

    void populateRequestTbl() {
        ArrayList<WorkRequest> emptyObjects = new ArrayList();
        ArrayList<WorkRequest> reqObjects = new ArrayList();
        DefaultTableModel model = (DefaultTableModel) supplierReqJTbl.getModel();
        model.setRowCount(0);

        for (WorkRequest req : s.getWorkQueue().getWorkRequestList()) {
            if (req instanceof DistributorWorkRequest) {
                //      if((((DistributorWorkRequest) req).getSupplier().getSupplierName()).equals(account.getEmployee().getName()))
                {
                    // System.out.println(((DistributorWorkRequest) req).getSupplier().getSupplierName());
                    //System.out.println("---"+account.getEmployee().getName()+"---");
                    Object[] row = new Object[5];
                    row[0] = req.getVaccine().getVaccineName();
                    row[1] = ((DistributorWorkRequest) req).getRequestQuantity();
                    row[2] = req;
                    row[3] = ((DistributorWorkRequest) req).getSupplier();
                    row[4] = req.getSender();
                    model.addRow(row);
                }
            }

        }

    }

    public void populateAvailable() {
        DefaultTableModel model = (DefaultTableModel) inventoryJTbl.getModel();
        model.setRowCount(0);
        for (Vaccine vaccine : s.getSupplierVaccineList().getVaccineList()) {
            Object[] row = new Object[2];
            row[0] = vaccine.getVaccineName();
            row[1] = vaccine.getQuantity();
            model.addRow(row);
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplierReqJTbl = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        inventoryJTbl = new javax.swing.JTable();
        btnCom = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        comboBox = new javax.swing.JComboBox();
        btnAdd = new javax.swing.JButton();
        lbllogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Supplier Work Bench");

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        supplierReqJTbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        supplierReqJTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine Name", "Qty", "Status", "Receiver", "Sender"
            }
        ));
        jScrollPane1.setViewportView(supplierReqJTbl);

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        inventoryJTbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        inventoryJTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine Name", "Qty"
            }
        ));
        jScrollPane2.setViewportView(inventoryJTbl);

        btnCom.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnCom.setText("Complete the order");
        btnCom.setPreferredSize(new java.awt.Dimension(200, 40));
        btnCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Supplier Inventory");

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Zapf Dingbats", 1, 18)); // NOI18N
        jLabel3.setText("Vaccine:");

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Zapf Dingbats", 1, 18)); // NOI18N
        jLabel4.setText("Quantity:");

        txtQty.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        comboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.setPreferredSize(new java.awt.Dimension(200, 40));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lbllogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbllogoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(lbllogout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 14, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboBox, 0, 178, Short.MAX_VALUE)
                    .addComponent(txtQty))
                .addGap(395, 395, 395))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(366, 366, 366)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCom, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1))
                    .addComponent(lbllogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComActionPerformed
        // TODO add your handling code here:
        int selectedRow = supplierReqJTbl.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select the row to select the Vaccine", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            DistributorWorkRequest p = (DistributorWorkRequest) supplierReqJTbl.getValueAt(selectedRow, 2);
            if (p.getStatus().equals("Sent to Supplier")) {
                if (s.getSupplierVaccineList().getVaccineList().size() <= 0) {
                    JOptionPane.showMessageDialog(null, "No Stock available.");
                    return;
                }
                boolean vaccineFound = false, isStockSufficient = false;
                for (Vaccine v : s.getSupplierVaccineList().getVaccineList()) {
                    if (p.getVaccine().getVaccineName().equals(v.getVaccineName())) {
                        vaccineFound = true;
                        if (v.getQuantity() - p.getRequestQuantity() < 0) {
                            return;
                        } else {
                            isStockSufficient = true;
                            v.setQuantity(v.getQuantity() - p.getRequestQuantity());
                        }
                    } else {
                        ///JOptionPane.showMessageDialog(null, "No such vaccine found");
                    }
                }
                if (!vaccineFound) {
                    JOptionPane.showMessageDialog(null, "No such vaccine found");
                }

                if (!isStockSufficient) {
                    JOptionPane.showMessageDialog(null, "Not sufficient stock");
                }

                p.setStatus("Complete");
                //p.setIncludedFlag(fals);
                JOptionPane.showMessageDialog(null, "You have successfully completed the request");
                populateRequestTbl();
                populateAvailable();

            }
        }
    }//GEN-LAST:event_btnComActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

        if (txtQty.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Quantity.");
        }
        else {

            int quant = Integer.parseInt(txtQty.getText());
            Vaccine v = (Vaccine) comboBox.getSelectedItem();
            int temp = 0;
            for (Vaccine vaccine : s.getSupplierVaccineList().getVaccineList()) {
                if (v.getVaccineName().equals(vaccine.getVaccineName())) {
                    temp = 1;
                    vaccine.setQuantity(vaccine.getQuantity() + quant);
                }
            }

            if (temp == 0) {
                Vaccine vac = s.getSupplierVaccineList().addVaccine();
                vac.setQuantity(quant);
                vac.setDisease(v.getDisease());
                vac.setVaccineName(v.getVaccineName());
            }
            populateAvailable();
            txtQty.setText("");
            JOptionPane.showMessageDialog(null, "Vaccine Added successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void lbllogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbllogoutMouseClicked
        // TODO add your handling code here:
        
        this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(business,network);
        ml.setVisible(true);
    }//GEN-LAST:event_lbllogoutMouseClicked

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
//            java.util.logging.Logger.getLogger(SupplierManagerWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SupplierManagerWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SupplierManagerWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SupplierManagerWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SupplierManagerWorkArea().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCom;
    private javax.swing.JComboBox comboBox;
    private javax.swing.JTable inventoryJTbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbllogout;
    private javax.swing.JTable supplierReqJTbl;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
