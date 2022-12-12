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
    private static final String GFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String bgimagePath = GFILENAME+"/Images/my-gradient.png";
    public SupplierManagerWorkArea(UserAccount account, SupplierOrganization supplierOrganization, Enterprise enterprise, EcoSystem business) {
        initComponents();
         ImageIcon bimgIcon = new ImageIcon(bgimagePath);
        Image bI = bimgIcon.getImage();
        Image cDimg = bI.getScaledInstance(1100, 800,Image.SCALE_SMOOTH);
        ImageIcon cImgThisImg = new ImageIcon(cDimg);
        lblB.setIcon(cImgThisImg);
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
        lblB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Supplier Work Bench");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 706, -1));

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        supplierReqJTbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        supplierReqJTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine Name", "Qty", "Status", "Receiver", "Sender"
            }
        ));
        supplierReqJTbl.setSelectionBackground(new java.awt.Color(255, 156, 141));
        supplierReqJTbl.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(supplierReqJTbl);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 98, 1005, 218));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        inventoryJTbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        inventoryJTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine Name", "Qty"
            }
        ));
        inventoryJTbl.setSelectionBackground(new java.awt.Color(255, 156, 141));
        inventoryJTbl.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(inventoryJTbl);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 407, 1005, 140));

        btnCom.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnCom.setForeground(new java.awt.Color(255, 156, 141));
        btnCom.setText("Complete the order");
        btnCom.setPreferredSize(new java.awt.Dimension(200, 40));
        btnCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComActionPerformed(evt);
            }
        });
        jPanel1.add(btnCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 328, 209, -1));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Supplier Inventory");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 372, 1005, -1));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Zapf Dingbats", 1, 18)); // NOI18N
        jLabel3.setText("Vaccine:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 580, 81, -1));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Zapf Dingbats", 1, 18)); // NOI18N
        jLabel4.setText("Quantity:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 618, -1, -1));

        txtQty.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 614, 178, -1));

        comboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 576, 178, -1));

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 156, 141));
        btnAdd.setText("ADD");
        btnAdd.setPreferredSize(new java.awt.Dimension(200, 40));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 658, -1, -1));

        lbllogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbllogoutMouseClicked(evt);
            }
        });
        jPanel1.add(lbllogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 30, 50, 40));
        jPanel1.add(lblB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 740));

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
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lbllogout;
    private javax.swing.JTable supplierReqJTbl;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
