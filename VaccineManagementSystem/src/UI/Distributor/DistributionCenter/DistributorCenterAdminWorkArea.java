/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Distributor.DistributionCenter;


import Business.DistributorCenter.DistributorCenter;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Events.Events;
import Business.Inventory.RequestedVaccineQty;
import Business.Network.Network;
import Business.Organization.DistributionCenterOrganization;
import Business.Organization.Organization;
import Business.Organization.PharmacyOrganization;
import Business.Organization.SupplierOrganization;
import Business.Supplier.Supplier;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.CDCEventInventoryWorkRequest;
import Business.WorkQueue.DistributorBillingWorkRequest;
import Business.WorkQueue.DistributorWorkRequest;
import Business.WorkQueue.PharmacyWorkRequest;
import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import Business.vaccine.Vaccine;
import UI.MainLoginJFrame;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class DistributorCenterAdminWorkArea extends javax.swing.JFrame {

    /**
     * Creates new form DistributorCenterAdminWorkArea
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private DistributionCenterOrganization distributionCenterOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private DistributorCenter dc;
    private Network network;
    
    private static final String logoFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String logoImagePath = logoFILENAME+"/Images/logout_blue.png";
    
    public DistributorCenterAdminWorkArea(UserAccount account, DistributionCenterOrganization distributionCenterOrganization, Enterprise enterprise, EcoSystem business, Network network) {
        initComponents();
        this.account = account;
        this.distributionCenterOrganization = distributionCenterOrganization;
        this.enterprise = enterprise;
        this.business = business;
        this.network = network;
        dc = distributionCenterOrganization.getDistCenter();

        System.out.println("busi" + business.getWorkQueue().getWorkRequestList().size());

        if (dc.getWorkQueue() == null) {
            WorkQueue w = new WorkQueue();
            dc.setWorkQueue(w);
        }
        populatetbl();
        populateUpdatedInventory();
        populateInventory();
        populateComboBox();
        populateSupCombo();
        populateDistRequestTbl();
        
        
        ImageIcon logoimgIcon = new ImageIcon(logoImagePath);
        Image lI = logoimgIcon.getImage();
        Image logoDimg = lI.getScaledInstance(30, 30,Image.SCALE_SMOOTH);
        ImageIcon logoImgThisImg = new ImageIcon(logoDimg);
        lblL.setIcon(logoImgThisImg);
    }
    
    
    void populatetbl() {
        DefaultTableModel dtm = (DefaultTableModel) distJTbl.getModel();
        dtm.setRowCount(0);
        for (WorkRequest wr : network.getWorkQueue().getWorkRequestList()) {
            if (wr instanceof PharmacyWorkRequest || wr instanceof CDCEventInventoryWorkRequest) {
                Object[] row = new Object[5];
                row[0] = wr.getVaccine().getVaccineName();
                if (wr instanceof PharmacyWorkRequest) {
                    row[1] = ((PharmacyWorkRequest) wr).getRequestedQty();
                } else if (wr instanceof CDCEventInventoryWorkRequest) {
                    row[1] = ((CDCEventInventoryWorkRequest) wr).getRequestedQty();
                }
                row[2] = wr;
                row[3] = wr.getReceiver();
                row[4] = wr.getSender();
                dtm.addRow(row);
            }
        }
    }

    public void populateSupCombo() {
        supCombo.removeAllItems();
        for (Organization organization1 : enterprise.getOrganizationDirectory().getOrganizationList()) {
            System.out.println(organization1);
            if (organization1 instanceof SupplierOrganization) {
                SupplierOrganization s = (SupplierOrganization) organization1;
                for (Supplier supplier : s.getSupplierList().getSupplierList()) {
                    supCombo.addItem(supplier);
                }
            }
        }
    }

    void populateDistRequestTbl() {

        DefaultTableModel model1 = (DefaultTableModel) distRequestTbl.getModel();
        model1.setRowCount(0);

        for (WorkRequest distReq : business.getWorkQueue().getWorkRequestList()) {
            if (distReq instanceof DistributorWorkRequest) {
                Object[] row = new Object[5];
                row[0] = distReq.getVaccine().getVaccineName();
                row[1] = ((DistributorWorkRequest) distReq).getRequestQuantity();
                row[2] = distReq;
                row[3] = ((DistributorWorkRequest) distReq).getSupplier().getSupplierName();
                row[4] = distReq.getSender();
                model1.addRow(row);
            }
        }

    }

    void populateInventory() {

        DefaultTableModel dtm2 = (DefaultTableModel) availVaccineJTbl.getModel();
        dtm2.setRowCount(0);

        try {
            for (Vaccine vac : dc.getDistVaccineDirectory().getVaccineList()) {
                Object[] rowVac = new Object[2];
                rowVac[0] = vac.getVaccineName();
                rowVac[1] = vac.getQuantity();
                dtm2.addRow(rowVac);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No stocks in the inventory");
        }

    }

    public void populateUpdatedInventory() {
        boolean foundUpdated = false;
        for (WorkRequest workRequest : dc.getWorkQueue().getWorkRequestList()) {
            System.out.println(workRequest.getStatus());
            DistributorWorkRequest p = (DistributorWorkRequest) workRequest;
            if (workRequest instanceof DistributorWorkRequest) {
                System.out.println(workRequest.getStatus());

                if (workRequest.getStatus().equals("Complete") && !workRequest.isIncludedFlag()) {
                    Vaccine v = workRequest.getVaccine();

                    for (Vaccine vaccine : dc.getDistVaccineDirectory().getVaccineList()) {
                        if (v.getVaccineName().equals(vaccine.getVaccineName())) {
                            vaccine.setQuantity(p.getRequestQuantity() + vaccine.getQuantity());
                            foundUpdated = true;
                        } else {
                            foundUpdated = false;
                        }
                    }
                    if (!foundUpdated) {
                        Vaccine vac = dc.getDistVaccineDirectory().addVaccine();
                        vac.setVaccineName(v.getVaccineName());
                        vac.setQuantity(p.getRequestQuantity());
                        vac.setDisease(v.getDisease());
                    }

                }
            }
            workRequest.setIncludedFlag(true);// count once
        }

    }

    void populateComboBox() {

        vaccineCombo.removeAllItems();
        for (Vaccine vacCombo : business.getVaccineDirectory().getVaccineList()) {
            vaccineCombo.addItem(vacCombo);
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
        distJTbl = new javax.swing.JTable();
        btnAssign = new javax.swing.JButton();
        btnAccept = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        availVaccineJTbl = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        distRequestTbl = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        vaccineCombo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        supCombo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        lblL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Distribution Center Work Area");

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        distJTbl.setBackground(new java.awt.Color(204, 204, 204));
        distJTbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        distJTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine Name", "Qty", "Status", "Receiver", "Sender"
            }
        ));
        jScrollPane1.setViewportView(distJTbl);

        btnAssign.setBackground(new java.awt.Color(204, 204, 204));
        btnAssign.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAssign.setText("Assign to me ");
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });

        btnAccept.setBackground(new java.awt.Color(204, 204, 204));
        btnAccept.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAccept.setText("Accept");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        availVaccineJTbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        availVaccineJTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine Name", "Qty"
            }
        ));
        jScrollPane2.setViewportView(availVaccineJTbl);

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));

        distRequestTbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        distRequestTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine Name", "Qty", "Status", "Supplier", "Sender"
            }
        ));
        jScrollPane3.setViewportView(distRequestTbl);

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setText("Request Supplier");

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setText("Vaccine Name:");

        vaccineCombo.setBackground(new java.awt.Color(204, 204, 204));
        vaccineCombo.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        vaccineCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setText("Qty :");

        txtQty.setBackground(new java.awt.Color(204, 204, 204));
        txtQty.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton1.setText("Request");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        supCombo.setBackground(new java.awt.Color(204, 204, 204));
        supCombo.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        supCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel5.setText("Supplier:");

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel6.setText("Request from Pharmacy");

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel7.setText("Inventory");

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel8.setText("Request to supplier");

        btnLoc.setBackground(new java.awt.Color(204, 204, 204));
        btnLoc.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnLoc.setText("Locate Pharmacy");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        lblL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(724, 724, 724))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnLoc)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAssign))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(vaccineCombo, 0, 203, Short.MAX_VALUE)
                                                    .addComponent(txtQty)
                                                    .addComponent(supCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(24, 24, 24)
                                                        .addComponent(jButton1)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAccept))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblL, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAccept)
                                .addComponent(btnAssign)
                                .addComponent(btnLoc))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(vaccineCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(supCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)))
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 3, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 3, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 707, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
        // TODO add your handling code here:
        int selectedRow = distJTbl.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select the row to assign the Pharmacy", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            if (distJTbl.getValueAt(selectedRow, 2) instanceof PharmacyWorkRequest) {
                PharmacyWorkRequest cwr = (PharmacyWorkRequest) distJTbl.getValueAt(selectedRow, 2);
                cwr.setReceiver(account);
                cwr.setStatus("Pending");
            } else if (distJTbl.getValueAt(selectedRow, 2) instanceof CDCEventInventoryWorkRequest) {
                CDCEventInventoryWorkRequest cwr = (CDCEventInventoryWorkRequest) distJTbl.getValueAt(selectedRow, 2);
                cwr.setReceiver(account);
                cwr.setStatus("Pending");
                for (Events e : network.getEventsDirectory().getEventList()) {
                    if (e.equals(cwr.getEvents())) {
                        System.out.println("e.equals(cwr.getEvent()) EVENT FOUND");
                        for (RequestedVaccineQty rvqty : e.getVaccineInventoryArrayList()) {
                            if (rvqty.getVaccine().equals(cwr.getVaccine())) {
                                System.out.println("e.equals(cwr.getEvent()) Vaccine FOUND");
                                rvqty.setStatus("Pending");
                            }
                        }
                    }
                }
            }
            populatetbl();

            JOptionPane.showMessageDialog(null, " Request assigned successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAssignActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        // TODO add your handling code here:
        int cdcSelectedRow = distJTbl.getSelectedRow();
        if (cdcSelectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select the row", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            if (distJTbl.getValueAt(cdcSelectedRow, 2) instanceof PharmacyWorkRequest) {
                PharmacyWorkRequest pharmacyWorkRequest = (PharmacyWorkRequest) distJTbl.getValueAt(cdcSelectedRow, 2);
                if (pharmacyWorkRequest.getStatus().equals("Pending")) {
                    if (dc.getDistVaccineDirectory().getVaccineList().size() <= 0) {
                        JOptionPane.showMessageDialog(null, "No Stock available. Request from Supplier");
                        return;
                    }
                    for (Vaccine vaclist : dc.getDistVaccineDirectory().getVaccineList()) {
                        if (pharmacyWorkRequest.getVaccine().getVaccineName().equals(vaclist.getVaccineName())) {
                            if (vaclist.getQuantity() - pharmacyWorkRequest.getRequestedQty() < 0) {
                                JOptionPane.showMessageDialog(null, "No enough Vaccines for supply. Wait for sometime");
                                return;
                            } else {

                                vaclist.setQuantity(vaclist.getQuantity() - pharmacyWorkRequest.getRequestedQty());
                                //PharmacyOrganization po = (PharmacyOrganization) pharmacyWorkRequest.getOrganization();
                                pharmacyWorkRequest.getVaccine().setQuantity(pharmacyWorkRequest.getRequestedQty());
                                for (Organization pharmacyOrganization : pharmacyWorkRequest.getEnterpeise().getOrganizationDirectory().getOrganizationList()) {
                                    if (pharmacyOrganization instanceof PharmacyOrganization) {
                                        ((PharmacyOrganization) pharmacyOrganization).getVaccineInventory().addVaccineInventory(pharmacyWorkRequest.getVaccine());
                                    }
                                }
                            }
                        }
                    }
                    pharmacyWorkRequest.setStatus("Complete");
                    JOptionPane.showMessageDialog(null, "You have successfully completed the request");
                    populateInventory();
                    populatetbl();

                    DistributorBillingWorkRequest dbwr = new DistributorBillingWorkRequest();

                    dbwr.setEnterpeise(pharmacyWorkRequest.getEnterpeise());
                    //dbwr.setOrganization(pharmacyWorkRequest.getOrganization());
                    dbwr.setVaccine(pharmacyWorkRequest.getVaccine());
                    dbwr.setPrice((pharmacyWorkRequest.getVaccine().getPrice())*(pharmacyWorkRequest.getRequestedQty()));
                    dbwr.setSender(account);
                    dbwr.setQty(pharmacyWorkRequest.getRequestedQty());
                    dbwr.setStatus("Bill Generated");

                    network.getWorkQueue().getWorkRequestList().add(dbwr);
                    //pharmacyWorkRequest.getOrganization().getWorkQueue().getWorkRequestList().add(dbwr);

                } else {
                    JOptionPane.showMessageDialog(null, "Please assign first");
                }

            } else if (distJTbl.getValueAt(cdcSelectedRow, 2) instanceof CDCEventInventoryWorkRequest) {
                CDCEventInventoryWorkRequest eventInventoryWorkRequest = (CDCEventInventoryWorkRequest) distJTbl.getValueAt(cdcSelectedRow, 2);
                if (eventInventoryWorkRequest.getStatus().equals("Pending")) {
                    if (dc.getDistVaccineDirectory().getVaccineList().size() <= 0) {
                        JOptionPane.showMessageDialog(null, "No Stock available. Request from Supplier");
                        return;
                    }
                    for (Vaccine vaclist : dc.getDistVaccineDirectory().getVaccineList()) {
                        if (eventInventoryWorkRequest.getVaccine().getVaccineName().equals(vaclist.getVaccineName())) {
                            if (vaclist.getQuantity() - eventInventoryWorkRequest.getRequestedQty() < 0) {
                                JOptionPane.showMessageDialog(null, "No enough Vaccines for supply. Wait for sometime");
                                return;
                            } else {

                                vaclist.setQuantity(vaclist.getQuantity() - eventInventoryWorkRequest.getRequestedQty());
                                //PharmacyOrganization po = (PharmacyOrganization) pharmacyWorkRequest.getOrganization();
                                eventInventoryWorkRequest.getVaccine().setQuantity(eventInventoryWorkRequest.getRequestedQty());
                                //                                for (Organization pharmacyOrganization : pharmacyWorkRequest.getEnterpeise().getOrganizationDirectory().getOrganizationList()) {
                                    //                                    if (pharmacyOrganization instanceof CDCEventInventoryWorkRequest) {
                                        //                                        ((CDCEventInventoryWorkRequest) pharmacyOrganization).getVaccineInventory().addVaccineInventory(pharmacyWorkRequest.getVaccine());
                                        //                                    }
                                    //                                }
                            }
                        }
                    }
                    eventInventoryWorkRequest.setStatus("Complete");

                    for (Events e : network.getEventsDirectory().getEventList()) {
                        if (e.equals(eventInventoryWorkRequest.getEvents())) {
                            for (RequestedVaccineQty rvqty : e.getVaccineInventoryArrayList()) {
                                if (rvqty.getVaccine().equals(eventInventoryWorkRequest.getVaccine())) {
                                    rvqty.setStatus("Complete");
                                    rvqty.getVaccine().setQuantity(rvqty.getRequestedQty());
                                    System.out.println("rvqty.getVaccine().setQuantity(rvqty.getRequestedQty())");
                                    System.out.println("Vaccine().getQuantity() " + rvqty.getVaccine().getQuantity());
                                }
                            }
                        }
                    }

                    JOptionPane.showMessageDialog(null, "You have successfully completed the request");
                    populateInventory();
                    populatetbl();
                } else {
                    JOptionPane.showMessageDialog(null, "Please assign first");
                }
            }

        }
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (txtQty.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter  Quantity.");
        } else {

            DistributorWorkRequest dwr = new DistributorWorkRequest();
            Supplier sup = (Supplier) supCombo.getSelectedItem();
            dwr.setVaccine((Vaccine) vaccineCombo.getSelectedItem());
            dwr.setRequestQuantity(Integer.parseInt(txtQty.getText()));
            dwr.setStatus("Sent to Supplier");
            dwr.setSender(account);
            dwr.setSupplier(sup);
            dwr.setIncludedFlag(false);
            sup.getWorkQueue().getWorkRequestList().add(dwr);
            distributionCenterOrganization.getWorkQueue().getWorkRequestList().add(dwr);
            dc.getWorkQueue().getWorkRequestList().add(dwr);
            business.getWorkQueue().getWorkRequestList().add(dwr);
            account.getWorkQueue().getWorkRequestList().add(dwr);
            populateDistRequestTbl();

            txtQty.setText("");

            JOptionPane.showMessageDialog(null, "Request generated successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        int SelectedRow = distJTbl.getSelectedRow();
        if (SelectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select the row", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                PharmacyWorkRequest p = (PharmacyWorkRequest) distJTbl.getValueAt(SelectedRow, 2);
                String postCode = String.valueOf(p.getPincode());
                String latLongs[] = getLatLongPositions(postCode);
                System.out.println("latitude" + latLongs[0] + "longitude" + latLongs[1]);
                System.out.println(postCode);
                JFrame test = new JFrame("Google Maps");
                try {
                    String latitude = latLongs[0];
                    String longitude = latLongs[1];

                    String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=18&size=612x612&markers=color:red%7Clabel:S%7C" + latitude + "," + longitude + "&scale=2&maptype=roadmap";
                    String destinationFile = "image.jpg";
                    // read the map image from Google
                    // then save it to a local file: image.jpg
                    //
                    URL url = new URL(imageUrl);
                    InputStream is = url.openStream();
                    OutputStream os = new FileOutputStream(destinationFile);
                    byte[] b = new byte[2048];
                    int length;
                    while ((length = is.read(b)) != -1) {
                        os.write(b, 0, length);
                    }
                    is.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                // create a GUI component that loads the image: image.jpg
                //
                ImageIcon imageIcon = new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600, java.awt.Image.SCALE_SMOOTH));
                test.add(new JLabel(imageIcon));
                // show the GUI window
                test.setVisible(true);
                test.pack();
            } catch (Exception ex) {
//                Logger.getLogger(DistributorCenterAdminWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void lblLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(business,network);
        ml.setVisible(true);
        
    }//GEN-LAST:event_lblLMouseClicked

    
                                           
    public static String[] getLatLongPositions(String address) throws Exception {
        int responseCode = 0;
        String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
        URL url = new URL(api);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();
        responseCode = httpConnection.getResponseCode();
        if (responseCode == 200) {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
            Document document = builder.parse(httpConnection.getInputStream());
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("/GeocodeResponse/status");
            String status = (String) expr.evaluate(document, XPathConstants.STRING);
            if (status.equals("OK")) {
                expr = xpath.compile("//geometry/location/lat");
                String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
                expr = xpath.compile("//geometry/location/lng");
                String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
                return new String[]{latitude, longitude};
            } else {
                throw new Exception("Error from the API - response status: " + status);
            }
        }
        return null;
    }
    
    
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
//            java.util.logging.Logger.getLogger(DistributorCenterAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DistributorCenterAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DistributorCenterAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DistributorCenterAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DistributorCenterAdminWorkArea().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable availVaccineJTbl;
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnAssign;
    private javax.swing.JButton btnLoc;
    private javax.swing.JTable distJTbl;
    private javax.swing.JTable distRequestTbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblL;
    private javax.swing.JComboBox supCombo;
    private javax.swing.JTextField txtQty;
    private javax.swing.JComboBox vaccineCombo;
    // End of variables declaration//GEN-END:variables
}
