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

        populateBox();
        populateTbl();
        populateWorkQueueTable();
        populateVaccineInventory();
        orderAutomation();
        
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
        jButtonAssignToMe = new javax.swing.JButton();
        jButtonAccept = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVaccineInventory = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtPinCode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pharmacy ");

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        tblWorkQueue.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblWorkQueue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine Name", "Qty", "Status", "Receiver"
            }
        ));
        jScrollPane1.setViewportView(tblWorkQueue);

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Vaccine:");

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Qty:");

        txtVaccineQty.setBackground(new java.awt.Color(204, 204, 204));
        txtVaccineQty.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        vaccineComboBox.setBackground(new java.awt.Color(204, 204, 204));
        vaccineComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        vaccineComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonRequest.setBackground(new java.awt.Color(204, 204, 204));
        jButtonRequest.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButtonRequest.setText("Request");
        jButtonRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRequestActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        tblVaccineRequest.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblVaccineRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient Name", "Patient Age", "Status", "Vaccine", "Price", "Qty", "Sender", "Receiver"
            }
        ));
        tblVaccineRequest.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblVaccineRequest);

        jButtonAssignToMe.setBackground(new java.awt.Color(204, 204, 204));
        jButtonAssignToMe.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButtonAssignToMe.setText("Assign");
        jButtonAssignToMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAssignToMeActionPerformed(evt);
            }
        });

        jButtonAccept.setBackground(new java.awt.Color(204, 204, 204));
        jButtonAccept.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButtonAccept.setText("Accept");
        jButtonAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcceptActionPerformed(evt);
            }
        });

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));

        jTableVaccineInventory.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTableVaccineInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine ID", "Vaccine Name", "Vaccine Quantity", "Disease"
            }
        ));
        jTableVaccineInventory.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableVaccineInventory);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("PinCode:");

        txtPinCode.setBackground(new java.awt.Color(204, 204, 204));
        txtPinCode.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Vaccine Request");

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Vaccine Inventory");

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Patient Details");

        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vaccineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtVaccineQty, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPinCode, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, Short.MAX_VALUE)
                                .addComponent(jButtonAssignToMe, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAssignToMe)
                    .addComponent(jButtonAccept)
                    .addComponent(vaccineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtVaccineQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButtonRequest)
                    .addComponent(jLabel4)
                    .addComponent(txtPinCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1103, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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

    private void jButtonAssignToMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAssignToMeActionPerformed
        // TODO add your handling code here:

        int selectedRow = tblVaccineRequest.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select the row to assign Pharmacy.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            ReceptionWorkRequest cwr = (ReceptionWorkRequest) tblVaccineRequest.getValueAt(selectedRow, 2);
            cwr.setReceiver(ua);
            cwr.setStatus("Pending");
            populateTbl();
            JOptionPane.showMessageDialog(null, "Request Assigned successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAssignToMeActionPerformed

    private void jButtonAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptActionPerformed
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
                        if (v.getQuantity() > rwr.getRequestQuantity()) {
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
    }//GEN-LAST:event_jButtonAcceptActionPerformed

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(system,net);
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
    private javax.swing.JButton jButtonAccept;
    private javax.swing.JButton jButtonAssignToMe;
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
    private javax.swing.JLabel lblLogout;
    private javax.swing.JTable tblVaccineRequest;
    private javax.swing.JTable tblWorkQueue;
    private javax.swing.JTextField txtPinCode;
    private javax.swing.JTextField txtVaccineQty;
    private javax.swing.JComboBox vaccineComboBox;
    // End of variables declaration//GEN-END:variables
}
