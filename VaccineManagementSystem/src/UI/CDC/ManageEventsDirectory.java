/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI.CDC;

import Business.EcoSystem;
import Business.Enterprise.DistributorEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.HospitalEnterprise;
import Business.Events.EventManagement;
import Business.Events.Events;
import Business.Inventory.RequestedVaccineQty;
import Business.Network.Network;
import Business.WorkQueue.CDCEventInventoryWorkRequest;
import Business.vaccine.Vaccine;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bhargavi
 */
public class ManageEventsDirectory extends javax.swing.JPanel {

    /**
     * Creates new form ManageEventsDirectory
     */
    JPanel workArea;
    private Enterprise enterprise;
    private Network network;
    private Vaccine selectedVaccine;
    private EcoSystem system;
    private HospitalEnterprise selectedHospitalEnterprise;
    private ArrayList<RequestedVaccineQty> vaccineRequestedArrayList;
    private boolean checkVaccineRequested = false;
    int totalVaccineRemains = 0, capacityEvent = 0;
    public ManageEventsDirectory(Enterprise enterprise,Network network,EcoSystem system, JPanel workArea) {
        initComponents();
        this.workArea = workArea;
        this.enterprise = enterprise;
        this.network = network;
        this.system = system;
        populatetbl();
        populateVaccineCombobox();
        selectedVaccine = (Vaccine) cbVaccine.getSelectedItem();
        vaccineRequestedArrayList = new ArrayList<>();
        
        populateEventLocation();
        
        
        
        txtQuantity.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        
        txtEventCapacity.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        
    }
    
    
    
    public void populateEventLocation() {
        cbEL.removeAllItems();
        for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
            if (e instanceof HospitalEnterprise) {
                cbEL.addItem(((HospitalEnterprise) e));
            }
        }
    }

    public void populatetbl() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblEvent.getModel();
            dtm.setRowCount(0);
            for (Events e : network.getEventsDirectory().getEventList()) {
                Object row[] = new Object[4];
                row[0] = e;
                row[1] = e.getStartDate();
                row[2] = e.getHospitalEnterprise();
                row[3] = e.getCapacity();
                dtm.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There are no events yet");
        }
    }

    public void populateVaccineRequestTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblEI.getModel();
        dtm.setRowCount(0);
        for (RequestedVaccineQty qty : vaccineRequestedArrayList) {
            Object row[] = new Object[5];
            row[0] = qty.getVaccine();
            row[1] = qty.getVaccine().getDisease();
            row[2] = qty.getRequestedQty();
            row[3] = qty;
            row[4] = qty.getStatus();
            dtm.addRow(row);
        }
    }

    public void populateVaccineCombobox() {
        cbVaccine.removeAllItems();
        for (Vaccine v : system.getVaccineDirectory().getVaccineList()) {
            cbVaccine.addItem(v);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEvent = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEN = new javax.swing.JTextField();
        txtSD = new javax.swing.JTextField();
        cbEL = new javax.swing.JComboBox();
        txtEventCapacity = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbVaccine = new javax.swing.JComboBox();
        txtQuantity = new javax.swing.JTextField();
        btnRequest = new javax.swing.JButton();
        btnCreateEvent = new javax.swing.JButton();
        btnDEI = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEI = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Events : Mobile Camps");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 29, -1, -1));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("Events");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 87, -1, -1));

        tblEvent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Event  Name", "Start Date", "Event Location", "Capacity"
            }
        ));
        jScrollPane1.setViewportView(tblEvent);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 123, 660, 100));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel3.setText("Create New Event");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 259, -1, -1));

        jLabel4.setText("Event Name :");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 317, -1, -1));

        jLabel5.setText("Start Date (dd/mm/yyyy) :");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 358, -1, -1));

        jLabel6.setText("Event Location :");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 399, -1, -1));

        jLabel7.setText("Event Capacity :");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 440, -1, -1));

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel8.setText("Request Vaccines for the event");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 480, -1, -1));

        txtEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtENActionPerformed(evt);
            }
        });
        add(txtEN, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 314, 160, -1));
        add(txtSD, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 355, 160, -1));

        cbEL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cbEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 396, 160, -1));
        add(txtEventCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 437, 160, -1));

        jLabel9.setText("Vaccine :");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 520, -1, -1));

        jLabel10.setText("Quantity :");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 570, -1, -1));

        cbVaccine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cbVaccine, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 520, 108, -1));
        add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 570, 108, -1));

        btnRequest.setText("Request");
        btnRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestActionPerformed(evt);
            }
        });
        add(btnRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 630, -1, -1));

        btnCreateEvent.setText("Create Event");
        btnCreateEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateEventActionPerformed(evt);
            }
        });
        add(btnCreateEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 630, -1, -1));

        btnDEI.setText("Display Event Inventory");
        btnDEI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDEIActionPerformed(evt);
            }
        });
        add(btnDEI, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 257, 220, -1));

        tblEI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Vaccine ", "Disease", "Quantity", "Status"
            }
        ));
        jScrollPane2.setViewportView(tblEI);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 314, 370, 302));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/kisspng-circle-structure-area-network-5abe0aa1daab97.4949489615224040018957.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        jLabel11.setPreferredSize(new java.awt.Dimension(800, 800));
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 800));
    }// </editor-fold>//GEN-END:initComponents

    private void txtENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtENActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtENActionPerformed

    private void btnCreateEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateEventActionPerformed
        // TODO add your handling code here:
        DistributorEnterprise distributorEnterprise;
        for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
            if (e instanceof DistributorEnterprise) {
                distributorEnterprise = (DistributorEnterprise) e;
            }
        }
        
        
              Date date = null;
        if (!txtSD.getText().equals("")) {
            String format = "dd/MM/yyyy";
            String createFlightTimeValidate = txtSD.getText();

            SimpleDateFormat sdf = new SimpleDateFormat(format);
            //Date date = null;
            try {
                date = sdf.parse(createFlightTimeValidate);
            } catch (ParseException ex) {
               // Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if (checkVaccineRequested) {
            try {
                selectedHospitalEnterprise = (HospitalEnterprise) cbEL.getSelectedItem();
                if (selectedHospitalEnterprise.isHospitalApproved()) {
                    String eventName = null, sd = null;
                    int eventCapacity = 0;
                    boolean checkName = false, checkStartDate = false, checkEventCapacity = false;

                    if (txtEN.getText().length() > 0) {
                        eventName = txtEN.getText();
                        checkName = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Event name cannot be empty");
                        checkName = false;
                    }

                    if (txtSD.getText().length() > 0) {
                        sd = txtSD.getText();
                        checkStartDate = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Event date cannot be empty");
                        checkStartDate = false;
                    }
                    
                    if (date == null) {
                        JOptionPane.showMessageDialog(null, "Enter valid date");
                        return;
                    }

                    if (Integer.parseInt(txtEventCapacity.getText()) > 0) {
                        eventCapacity = Integer.parseInt(txtEventCapacity.getText());
                        checkEventCapacity = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Event capacity cannot be empty");
                        checkEventCapacity = false;
                    }

                    if (checkName && checkStartDate && checkEventCapacity) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Date startDate = df.parse(sd);
                        System.out.println(network.getName());
                        Events eve = network.getEventsDirectory().addEvents();
                        eve.setName(eventName);
                        eve.setStartDate(startDate);
                        eve.setCapacity(eventCapacity);
                        eve.setEventManagement(new EventManagement());
                        eve.setHospitalEnterprise(selectedHospitalEnterprise);
                        eve.setVaccineInventoryArrayList(vaccineRequestedArrayList);
                        populatetbl();

                        txtEN.setText("");
                        txtSD.setText("");
                        txtEventCapacity.setText("");

                        for (RequestedVaccineQty qty : vaccineRequestedArrayList) {
                            CDCEventInventoryWorkRequest workRequest = new CDCEventInventoryWorkRequest();
                            workRequest.setEnterpeise(enterprise);
                            //workRequest.setOrganization(enterprise);
                            workRequest.setRequestDate(new Date());
                            workRequest.setStatus("Requested to Distributor");
                            workRequest.setRequestedQty(qty.getRequestedQty());
                            workRequest.setVaccine(qty.getVaccine());
                            workRequest.setEvents(eve);
                            network.getWorkQueue().getWorkRequestList().add(workRequest);
                        }
                        
                          JOptionPane.showMessageDialog(null, "Event added!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Event cannot be created at this hospital, as it is not yet approved by PHD");
                    vaccineRequestedArrayList.clear();
                    populateVaccineRequestTable();
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Input cannot be parsed, kindly enter in dd/MM/yyyy format");
                txtSD.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Request some vaccines");
        }
    }//GEN-LAST:event_btnCreateEventActionPerformed

    private void btnRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequestActionPerformed
        // TODO add your handling code here:
        int vaccineQty = 0, capacityEvent = 0;
        boolean checkVaccineQty = false;
        if (Integer.parseInt(txtEventCapacity.getText()) > 0) {
            capacityEvent = Integer.parseInt(txtEventCapacity.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Event capacity cannot be empty");
        }

        if (Integer.parseInt(txtQuantity.getText()) > 0) {
            vaccineQty = Integer.parseInt(txtQuantity .getText());
            checkVaccineQty = true;
        } else {
            JOptionPane.showMessageDialog(null, "Vaccine Quantity should be greater than 0");
            checkVaccineQty = false;
        }

        if (checkVaccineQty) {
            for (RequestedVaccineQty qty : vaccineRequestedArrayList) {
                totalVaccineRemains += qty.getRequestedQty();
            }
            boolean vaccineFound = false;
            for (RequestedVaccineQty qty : vaccineRequestedArrayList) {
                if (qty.getVaccine().equals(selectedVaccine)) {
                    qty.setRequestedQty(qty.getRequestedQty() + vaccineQty);
                    vaccineFound = true;
                }
            }
            if (!vaccineFound) {
                System.out.println("totalVaccineRemains " + totalVaccineRemains);
                //System.out.println("totalVaccineRemains " +totalVaccineRemains);
                System.out.println("(capacityEvent - totalVaccineRemains) " + (capacityEvent - totalVaccineRemains));
                if ((capacityEvent - totalVaccineRemains) >= 0) {
                    RequestedVaccineQty requestedVaccineQty = new RequestedVaccineQty();
                    requestedVaccineQty.setVaccine(selectedVaccine);
                    requestedVaccineQty.setRequestedQty(vaccineQty);
                    requestedVaccineQty.setStatus("Requested to Distributor");
                    vaccineRequestedArrayList.add(requestedVaccineQty);
                    populateVaccineRequestTable();
                    checkVaccineRequested = true;

                } else {
                    JOptionPane.showMessageDialog(null, "Vaccine Quantity cannot exceed Event capacity");
                }
            }
            txtQuantity.setText("");
        } else {
            checkVaccineRequested = false;
        }
    }//GEN-LAST:event_btnRequestActionPerformed

    private void btnDEIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDEIActionPerformed
        // TODO add your handling code here:
        
        if (tblEvent.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an event");
        } else {
            Events events = (Events) tblEvent.getValueAt(tblEvent.getSelectedRow(), 0);
            DefaultTableModel dtm = (DefaultTableModel) tblEI.getModel();
            dtm.setRowCount(0);
            for (RequestedVaccineQty qty : events.getVaccineInventoryArrayList()) {
                Object row[] = new Object[5];
                row[0] = qty.getVaccine();
                row[1] = qty.getVaccine().getDisease();
                row[2] = qty.getRequestedQty();
                row[3] = qty;
                row[4] = qty.getStatus();
                dtm.addRow(row);
            }
        }
    }//GEN-LAST:event_btnDEIActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateEvent;
    private javax.swing.JButton btnDEI;
    private javax.swing.JButton btnRequest;
    private javax.swing.JComboBox cbEL;
    private javax.swing.JComboBox cbVaccine;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblEI;
    private javax.swing.JTable tblEvent;
    private javax.swing.JTextField txtEN;
    private javax.swing.JTextField txtEventCapacity;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSD;
    // End of variables declaration//GEN-END:variables
}
