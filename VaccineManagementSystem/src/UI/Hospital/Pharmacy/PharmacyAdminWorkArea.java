/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Hospital.Pharmacy;


import Business.Customers.Customer;
import Business.Customers.CustomerDirectory;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.HospitalEnterprise;
import Business.Network.Network;
import Business.Organization.PharmacyOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.CDCBillingWorkRequest;
import Business.WorkQueue.InsuaranceBillingWorkRequest;
import Business.WorkQueue.PharmacyWorkRequest;
import Business.WorkQueue.ReceptionWorkRequest;
import Business.WorkQueue.WorkRequest;
import Business.vaccine.Vaccine;
import UI.MainLoginJFrame;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class PharmacyAdminWorkArea extends javax.swing.JFrame {

    /**
     * Creates new form PharmacyAdminWorkArea
     */
    private UserAccount ua;
    private PharmacyOrganization pharOrganization;
    private HospitalEnterprise hospEnt;
    private EcoSystem system;
    private Network net;
    private final int VACCINE_THRESHOLD = 5;
    private final int VACCINE_AUTOORDER_QTY = 10;
    //private Pharmacy pharmacy;
    //private VaccineInventory vaccineInventory;
    private static final String logoFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String logoImagePath = logoFILENAME+"/Images/logout_blue.png";
    
private static final String GFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String bgimagePath = GFILENAME+"/Images/my-gradient.png";
    public PharmacyAdminWorkArea(UserAccount account, PharmacyOrganization pharmacyOrganization, Enterprise enterprise, EcoSystem business, Network network) {
        initComponents();
        this.ua = account;
        this.pharOrganization = pharmacyOrganization;
        this.hospEnt = (HospitalEnterprise) enterprise;
        this.system = business;
        this.net = network;
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
        populateBox();
        populateTbl();
        populateWorkQueueTable();
        populateVaccineInventory();
        orderAutomation();
        
        btnAssign.setEnabled(false);
        btnAccept.setEnabled(false);
        
    }
    
    
    public void orderAutomation() {
        boolean orderAutomtion = false, addInPWR = false;
        Vaccine v = null;
        WorkRequest wrTest = null;
        if (!pharOrganization.getVaccineInventory().getVaccineInventoryArrayList().isEmpty()) {
            if (hospEnt.isHospitalApproved()) {
                for (Vaccine vaccine : pharOrganization.getVaccineInventory().getVaccineInventoryArrayList()) {
                    if (vaccine.getQuantity() < VACCINE_THRESHOLD) {
                        for (WorkRequest wr : pharOrganization.getWorkQueue().getWorkRequestList()) {
                            if (vaccine.equals(wr.getVaccine())) {
//                                if (!((wr.getStatus().equalsIgnoreCase("Sent to PHD"))
//                                        || (wr.getStatus().equalsIgnoreCase("Pending"))
//                                        || (wr.getStatus().equalsIgnoreCase("Forwarded to CDC"))
//                                        || (wr.getStatus().equalsIgnoreCase("Forwarded to Distributor")))) {
                                wrTest = wr;
                                addInPWR = true;
                                //System.out.println("Status "+wr.getStatus());
                                v = wr.getVaccine();
                                //break;
                                //}
                            }
                        }

                        if (addInPWR) {
                            System.out.println("Status " + wrTest.getStatus());
                            if (!((wrTest.getStatus().equalsIgnoreCase("Sent to PHD"))
                                    || (wrTest.getStatus().equalsIgnoreCase("Pending"))
                                    || (wrTest.getStatus().equalsIgnoreCase("Forwarded to CDC"))
                                    || (wrTest.getStatus().equalsIgnoreCase("Forwarded to Distributor")))) {

                                PharmacyWorkRequest pwr = new PharmacyWorkRequest();
                                pwr.setVaccine(v);
                                pwr.setRequestedQty(VACCINE_AUTOORDER_QTY);
                                pwr.setStatus("Sent to PHD");
                                pwr.setSender(ua);
                                pwr.setEnterpeise(hospEnt);
                                pwr.setOrganization(pharOrganization);
                                pwr.setIncludedFlag(false);

                                pharOrganization.getWorkQueue().getWorkRequestList().add(pwr);
                                hospEnt.getWorkQueue().getWorkRequestList().add(pwr);
                                net.getWorkQueue().getWorkRequestList().add(pwr);
                                ua.getWorkQueue().getWorkRequestList().add(pwr);
                                //business.getWorkQueue().getWorkRequestList().add(pwr);
                                populateWorkQueueTable();
                            }
                        }

                    }
                }
            } else {
                ///hospital is not approved
                JOptionPane.showMessageDialog(null, "Auto Order cannot be processed as Hospital is not approved by PHD");
            }
        } else {

        }

    }

    public void populateBox() {
        vaccineComboBox.removeAllItems();
        for (Vaccine vaccine : system.getVaccineDirectory().getVaccineList()) {
            vaccineComboBox.addItem(vaccine);
        }
    }

    void populateTbl() {

        DefaultTableModel dtm_ = (DefaultTableModel) tblVaccineRequest.getModel();
        dtm_.setRowCount(0);
        for (WorkRequest work : hospEnt.getWorkQueue().getWorkRequestList()) {
            if (work instanceof ReceptionWorkRequest) {
                Object[] row = new Object[8];
                row[0] = ((ReceptionWorkRequest) work).getPatient().getName();
                row[1] = ((ReceptionWorkRequest) work).getPatient().getAge();
                row[2] = work;
                row[3] = work.getVaccine();
                row[4] = ((ReceptionWorkRequest) work).getPrice();
                row[5] = ((ReceptionWorkRequest) work).getRequestQuantity();
                row[6] = work.getSender();
                row[7] = work.getReceiver();
                dtm_.addRow(row);
            }
        }
    }

    public void populateVaccineInventory() {

        if (!pharOrganization.getVaccineInventory().getVaccineInventoryArrayList().isEmpty()) {

//            System.out.println("isEmpty " + !pharmacyOrganization.getVaccineInventory().getVaccineInventoryArrayList().isEmpty());
//            System.out.println("size " + pharmacyOrganization.getVaccineInventory().getVaccineInventoryArrayList().size());
//            Vaccine vx = pharmacyOrganization.getVaccineInventory().getVaccineInventoryArrayList().get(0);
//            System.out.println("Vaccine "+vx);
            DefaultTableModel dtm = (DefaultTableModel) jTableVaccineInventory.getModel();
            dtm.setRowCount(0);
            for (Vaccine v : pharOrganization.getVaccineInventory().getVaccineInventoryArrayList()) {
                System.out.println("Vaccine " + v);
                Object[] row = new Object[4];
                row[0] = v.getVaccineId();
                row[1] = v;
                row[2] = v.getQuantity();
                row[3] = v.getDisease();
                dtm.addRow(row);
            }
        }
    }

    public void populateWorkQueueTable() {

        DefaultTableModel dtm = (DefaultTableModel) tblWorkQueue.getModel();
        dtm.setRowCount(0);

        for (WorkRequest wr : pharOrganization.getWorkQueue().getWorkRequestList()) {
            Object[] row = new Object[4];
            row[0] = wr.getVaccine().getVaccineName();
            row[1] = ((PharmacyWorkRequest) wr).getRequestedQty();
            row[2] = wr;
            row[3] = wr.getReceiver();
            dtm.addRow(row);
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
        tblWorkQueue = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtVaccineQty = new javax.swing.JTextField();
        vaccineComboBox = new javax.swing.JComboBox();
        jButtonRequest = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVaccineRequest = new javax.swing.JTable();
        btnAssign = new javax.swing.JButton();
        btnAccept = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVaccineInventory = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtPinCode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pharmacy WorkRequest Dashboard");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 12, 717, -1));

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        tblWorkQueue.setBackground(new java.awt.Color(255, 156, 141));
        tblWorkQueue.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblWorkQueue.setForeground(new java.awt.Color(255, 255, 255));
        tblWorkQueue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine Name", "Qty", "Status", "Receiver"
            }
        ));
        tblWorkQueue.setSelectionBackground(new java.awt.Color(255, 156, 141));
        tblWorkQueue.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblWorkQueue);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 339, 607, 323));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Vaccine:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 271, -1, -1));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Qty:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 271, -1, -1));

        txtVaccineQty.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(txtVaccineQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 270, 80, -1));

        vaccineComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        vaccineComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(vaccineComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 270, 118, -1));

        jButtonRequest.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButtonRequest.setText("Request");
        jButtonRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRequestActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(619, 271, 167, -1));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        tblVaccineRequest.setBackground(new java.awt.Color(255, 156, 141));
        tblVaccineRequest.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblVaccineRequest.setForeground(new java.awt.Color(255, 255, 255));
        tblVaccineRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient Name", "Patient Age", "Status", "Vaccine", "Price", "Qty", "Sender", "Receiver"
            }
        ));
        tblVaccineRequest.setSelectionBackground(new java.awt.Color(255, 156, 141));
        tblVaccineRequest.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblVaccineRequest.getTableHeader().setReorderingAllowed(false);
        tblVaccineRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVaccineRequestMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblVaccineRequest);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 78, 1091, 186));

        btnAssign.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAssign.setText("Assign");
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });
        jPanel1.add(btnAssign, new org.netbeans.lib.awtextra.AbsoluteConstraints(793, 271, 152, -1));

        btnAccept.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAccept.setText("Accept");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });
        jPanel1.add(btnAccept, new org.netbeans.lib.awtextra.AbsoluteConstraints(951, 271, 146, -1));

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));

        jTableVaccineInventory.setBackground(new java.awt.Color(255, 156, 141));
        jTableVaccineInventory.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTableVaccineInventory.setForeground(new java.awt.Color(255, 255, 255));
        jTableVaccineInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine ID", "Vaccine Name", "Vaccine Quantity", "Disease"
            }
        ));
        jTableVaccineInventory.setSelectionBackground(new java.awt.Color(255, 156, 141));
        jTableVaccineInventory.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTableVaccineInventory.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableVaccineInventory);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(619, 339, 478, 323));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("PinCode:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 271, -1, -1));

        txtPinCode.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(txtPinCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 270, 166, -1));

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Vaccine Request");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 316, -1, -1));

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Vaccine Inventory");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(619, 316, -1, -1));

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Patient Details");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 55, -1, -1));

        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
        });
        jPanel1.add(lblLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(964, 12, 46, 37));
        jPanel1.add(lblB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 760));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1109, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 6, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRequestActionPerformed
        // TODO add your handling code here:

        if (txtVaccineQty.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Quantity.");
        } else {

            if (hospEnt.isHospitalApproved()) {
                PharmacyWorkRequest pwr = new PharmacyWorkRequest();
                pwr.setVaccine((Vaccine) vaccineComboBox.getSelectedItem());
                pwr.setRequestedQty(Integer.parseInt(txtVaccineQty.getText()));
                pwr.setStatus("Sent to PHD");
                pwr.setSender(ua);
                pwr.setEnterpeise(hospEnt);
                pwr.setOrganization(pharOrganization);
                pwr.setIncludedFlag(false);
                pwr.setEnterpriseName(hospEnt.getName());
                pwr.setPincode(txtPinCode.getText());

                pharOrganization.getWorkQueue().getWorkRequestList().add(pwr);
                hospEnt.getWorkQueue().getWorkRequestList().add(pwr);
                net.getWorkQueue().getWorkRequestList().add(pwr);
                ua.getWorkQueue().getWorkRequestList().add(pwr);
                //business.getWorkQueue().getWorkRequestList().add(pwr);
                populateWorkQueueTable();
                txtVaccineQty.setText("");
                JOptionPane.showMessageDialog(null, "Request created successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Order cannot be processed as Hospital is not approved by PHD");
            }
        }
    }//GEN-LAST:event_jButtonRequestActionPerformed

    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
        // TODO add your handling code here:
        
        
        
        int selectedRow = tblVaccineRequest.getSelectedRow();
        
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select the row to assign Pharmacy.", "Warning", JOptionPane.WARNING_MESSAGE);
        } 
        else {
            ReceptionWorkRequest cwr = (ReceptionWorkRequest) tblVaccineRequest.getValueAt(selectedRow, 2);
//            if(cwr.getStatus() != 'Complete')
            WorkRequest wreq  = (WorkRequest) tblVaccineRequest.getValueAt(selectedRow, 2);
            System.out.println("Select record status: "+wreq.getStatus().toUpperCase());
            
            cwr.setReceiver(ua);
            cwr.setStatus("Pending");
            populateTbl();
            JOptionPane.showMessageDialog(null, "Request Assigned successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_btnAssignActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblVaccineRequest.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a Patient to give prescribed vaccine.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            ReceptionWorkRequest rwr = (ReceptionWorkRequest) tblVaccineRequest.getValueAt(selectedRow, 2);
            Vaccine vac = rwr.getVaccine();
            boolean requestedVacQtyAvailable = false;
//          Enter loop if vaccine inventory is not empty
            if (!pharOrganization.getVaccineInventory().getVaccineInventoryArrayList().isEmpty()) {
                for (Vaccine v : pharOrganization.getVaccineInventory().getVaccineInventoryArrayList()) {
                    if (vac.equals(v)) {
                        if ((v.getQuantity() > rwr.getRequestQuantity())) {
                            requestedVacQtyAvailable = true;
                                
                            v.setQuantity(v.getQuantity() - rwr.getRequestQuantity());
                            rwr.setStatus("Complete");
                            if (rwr.getStatus().equals("Complete")) {
                                System.out.println("is Complete");
                                CustomerDirectory cusDir = system.getCustomerDirectory();
                                for (Customer cus : cusDir.getCustomerList()) {
                                    System.out.println(cus.getName());
                                    if ((cus.getName()).equals(rwr.getPatient().getName())) {
                                        if (cus.isInsuarance()) {
                                            System.out.println("He is insured!");
                                            InsuaranceBillingWorkRequest ibwr = new InsuaranceBillingWorkRequest();
                                            ibwr.setVaccine(rwr.getVaccine());
                                            ibwr.setName(rwr.getPatient().getName());
                                            ibwr.setSender(ua);
                                            ibwr.setStatus("Sent to Insuarance");
                                            ibwr.setMessage("Patient is insured");
                                            ibwr.setPrice(rwr.getRequestQuantity()*(rwr.getVaccine().getPrice()));
                                            hospEnt.getWorkQueue().getWorkRequestList().add(ibwr);
                                            net.getWorkQueue().getWorkRequestList().add(ibwr);
                                        } else {
                                            System.out.println("Not insured");
                                            CDCBillingWorkRequest cbwr = new CDCBillingWorkRequest();
                                            cbwr.setVaccine(rwr.getVaccine());
                                            cbwr.setName(rwr.getPatient().getName());
                                            cbwr.setSender(ua);
                                            cbwr.setStatus("Sent to CDC");
                                            cbwr.setMessage("Patient is not insured");
                                            cbwr.setPrice(rwr.getRequestQuantity()*(rwr.getVaccine().getPrice()));
                                            hospEnt.getWorkQueue().getWorkRequestList().add(cbwr);
                                            net.getWorkQueue().getWorkRequestList().add(cbwr);
                                            //cdc work queue
                                        }
                                    }
                                }
                            }
                            
                            else{
                                JOptionPane.showMessageDialog(null, "Request is Already Completed");
                            }
                            populateVaccineInventory();// update Inventory
                            populateTbl();//

                            JOptionPane.showMessageDialog(null, "Request Accepted  successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            break;
                        }
                    }
                }
                if (!requestedVacQtyAvailable) {
                    JOptionPane.showMessageDialog(null, "Requested Vaccine Quantity is greater than available Quantity in Inventory");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Inventory is Empty, request your order to PHD");
            }
        }
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(system,net);
        ml.setVisible(true);
    }//GEN-LAST:event_lblLogoutMouseClicked

    private void tblVaccineRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVaccineRequestMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblVaccineRequest.getSelectedRow();
        ReceptionWorkRequest cwr = (ReceptionWorkRequest) tblVaccineRequest.getValueAt(selectedRow, 2);
        WorkRequest wreq  = (WorkRequest) tblVaccineRequest.getValueAt(selectedRow, 2);
        System.out.println("Select record status: "+wreq.getStatus().toUpperCase());
            
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a Patient to give prescribed vaccine.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if(wreq.getStatus().toUpperCase().equals("REQUESTED VACCINE TO PHARMACY")){
            btnAssign.setEnabled(true);
//            JOptionPane.showMessageDialog(null, "Selected a Patient to give prescribed vaccine.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if(wreq.getStatus().equals("Pending")){
            btnAccept.setEnabled(true);
        }
        else if((wreq.getStatus().toUpperCase() != "REQUESTED VACCINE TO PHARMACY") || (wreq.getStatus() != "Pending") || (cwr.getStatus() == "Complete")){
            btnAssign.setEnabled(false);
            btnAccept.setEnabled(false);
        }
    }//GEN-LAST:event_tblVaccineRequestMouseClicked

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
//            java.util.logging.Logger.getLogger(PharmacyAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PharmacyAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PharmacyAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PharmacyAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PharmacyAdminWorkArea().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnAssign;
    private javax.swing.JButton jButtonRequest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableVaccineInventory;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JTable tblVaccineRequest;
    private javax.swing.JTable tblWorkQueue;
    private javax.swing.JTextField txtPinCode;
    private javax.swing.JTextField txtVaccineQty;
    private javax.swing.JComboBox vaccineComboBox;
    // End of variables declaration//GEN-END:variables
}
