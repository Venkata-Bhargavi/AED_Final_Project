/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Hospital.Event;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Events.Events;
import Business.Inventory.RequestedVaccineQty;
import Business.Network.Network;
import Business.Organization.EventOrganisation;
import Business.Patient.Patient;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.EventClinicWorkRequest;
import Business.WorkQueue.WorkRequest;
import Business.vaccine.Vaccine;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class EventVolunteerWorkArea extends javax.swing.JFrame {

    /**
     * Creates new form EventVolunteerWorkArea
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private EventOrganisation eventOrganisation;
    private Enterprise enterprise;
    private EcoSystem business;
    private Network network;
    private String eventName = "";
    private int eventCapacity = 0;
    
    public EventVolunteerWorkArea(UserAccount account, EventOrganisation eventOrganisation, Enterprise enterprise, EcoSystem business, Network network) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.eventOrganisation = eventOrganisation;
        this.enterprise = enterprise;
        this.business = business;
        this.network = network;
        jLabelEventName.setText("Event");

        populateComboBox();
        populatePatientDetails();
        populateEvents();
        //populateVaccineInventoryTable();

        jLabelEventName.setText("Event : " + eventName + " capacity : " + eventCapacity);
        
    }
    public void populateComboBox() {
        vaccineComboBox.removeAllItems();
        for (Events e : network.getEventsDirectory().getEventList()) {
            if (e.getHospitalEnterprise() != null) {
                if (e.getHospitalEnterprise().equals(enterprise)) {
                    for (RequestedVaccineQty qty : e.getVaccineInventoryArrayList()) {
                        if (qty.getVaccine().getQuantity() > 0) {
                            vaccineComboBox.addItem(qty.getVaccine());
                        }
                        eventName = e.getName();
                        eventCapacity = e.getCapacity();
                    }
                }
            }
        }
    }

    public void populatePatientDetails() {
        DefaultTableModel dtm_ = (DefaultTableModel) jTablePatientDetails.getModel();
        dtm_.setRowCount(0);

        for (WorkRequest work : eventOrganisation.getWorkQueue().getWorkRequestList()) {
            if (work instanceof EventClinicWorkRequest) {
                Object[] row = new Object[5];
                row[0] = ((EventClinicWorkRequest) work).getPatient().getName();
                row[1] = ((EventClinicWorkRequest) work).getPatient().getAge();
                row[2] = ((EventClinicWorkRequest) work).getVaccine();
                row[3] = ((EventClinicWorkRequest) work).getRequestedQty();
                row[4] = work;
                dtm_.addRow(row);
            }
        }
    }
    
    public void populateEvents() {
        DefaultTableModel dtm = (DefaultTableModel) tblEvents.getModel();
        dtm.setRowCount(0);
        
        for (Events e : network.getEventsDirectory().getEventList()) {
            Date startDate = new Date();
            long different = e.getStartDate().getTime() - startDate.getTime();

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            System.out.println(elapsedDays);
            System.out.println(elapsedHours);
            System.out.println(elapsedMinutes);

            Object row[] = new Object[5];
            row[0] = e;
            if (elapsedDays < 1) {
                row[1] = elapsedHours + "hrs and " + elapsedMinutes + "mins to go";
            } else {
                row[1] = elapsedDays + "days to go";
            }
            row[2] = e.getHospitalEnterprise();
            row[3] = e.getCapacity();
            row[4] = e.getStatus();
            dtm.addRow(row);

        }
    }

    public void populateVaccineInventoryTable(Events e) {
        DefaultTableModel dtm = (DefaultTableModel) jTableVaccineInventory.getModel();
        dtm.setRowCount(0);
        //for (Events e : network.getEventsDirectory().getEventList()) {
            if (e.getHospitalEnterprise() != null) {
                if (e.getHospitalEnterprise().equals(enterprise)) {
                    for (RequestedVaccineQty qty : e.getVaccineInventoryArrayList()) {
                        Object row[] = new Object[5];
                        row[0] = qty.getVaccine();
                        row[1] = qty.getVaccine().getDisease();
                        row[2] = qty.getVaccine().getQuantity();                        
                        row[3] = qty;                        
                        dtm.addRow(row);
                    }
                }
            }
        //}
    }
    
    public void updateInventory(Vaccine v) {
        boolean isVaccineEmpty = false;
        Events eve = null;
        for (Events e : network.getEventsDirectory().getEventList()) {
            if (e.getHospitalEnterprise() != null) {
                if (e.getHospitalEnterprise().equals(enterprise)) {
                    for (RequestedVaccineQty qty : e.getVaccineInventoryArrayList()) {
                        if (qty.getVaccine().equals(v)) {
                            eve = e;
                            if (qty.getVaccine().getQuantity() > 0) {
                                qty.getVaccine().setQuantity(qty.getVaccine().getQuantity() - 1);
                            } else {
                                isVaccineEmpty = true;
                            }
                        }
                    }
                }
            }
        }
        if (isVaccineEmpty) {
            JOptionPane.showMessageDialog(null, "No stock available for selected vaccine");
        }
        populateVaccineInventoryTable(eve);
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
        jLabelEventName = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVaccineInventory = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePatientDetails = new javax.swing.JTable();
        jTextFieldName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldAge = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        vaccineComboBox = new javax.swing.JComboBox();
        txtQty = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEvents = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabelEventName.setBackground(new java.awt.Color(204, 204, 204));
        jLabelEventName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelEventName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEventName.setText("Event : X is goin on");

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));

        jTableVaccineInventory.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTableVaccineInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vaccine", "Disease", "Vaccine Quantity", "Object"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVaccineInventory.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableVaccineInventory);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Patient Details");

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        jTablePatientDetails.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTablePatientDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient Name", "Patient Age", "Vaccine", "Qty", "Object"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePatientDetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTablePatientDetails);

        jTextFieldName.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldName.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel5.setText("Name:");

        jTextFieldAge.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldAge.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jTextFieldAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAgeActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel6.setText("Age:");

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel7.setText("Vaccine: ");

        vaccineComboBox.setBackground(new java.awt.Color(204, 204, 204));
        vaccineComboBox.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        vaccineComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtQty.setBackground(new java.awt.Color(204, 204, 204));
        txtQty.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtQty.setText("1");
        txtQty.setEnabled(false);

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel8.setText("Qty:");

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton2.setText("Request");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Vaccine Inventory");

        jScrollPane4.setBackground(new java.awt.Color(204, 204, 204));

        tblEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EventName", "Start Date", "Hospital", "Capacity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblEvents);

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton4.setText("Inventory");
        jButton4.setMaximumSize(new java.awt.Dimension(150, 40));
        jButton4.setMinimumSize(new java.awt.Dimension(150, 40));
        jButton4.setPreferredSize(new java.awt.Dimension(150, 40));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEventName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldAge, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vaccineComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtQty)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelEventName, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(vaccineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 941, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 688, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAgeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String name = null, age = null;
        //int age = 0;
        boolean checkName = false, checkAge = false;
        if (jTextFieldName.getText().trim().length() > 0) {
            name = jTextFieldName.getText().trim();
            checkName = true;
        } else {
            JOptionPane.showMessageDialog(null, "Name cannot be empty");
            checkName = false;
        }

        if (jTextFieldAge.getText().trim().length() > 0) {
            age = jTextFieldAge.getText().trim();
            checkAge = true;
        } else {
            JOptionPane.showMessageDialog(null, "Age cannot be empty and zero");
            checkAge = false;
        }

        if(txtQty.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter Quantity.");
            return;
        }

        if (checkAge && checkName) {
            EventClinicWorkRequest eventClinicWorkRequest = new EventClinicWorkRequest();
            eventClinicWorkRequest.setRequestedQty(1);
            eventClinicWorkRequest.setVaccine(((Vaccine) vaccineComboBox.getSelectedItem()));
            eventClinicWorkRequest.setSender(account);
            eventClinicWorkRequest.setStatus("Complete");
            Patient patient = new Patient();
            patient.setAge(age);
            patient.setDate(new Date());
            patient.setName(name);
            eventClinicWorkRequest.setPatient(patient);
            eventOrganisation.getWorkQueue().getWorkRequestList().add(eventClinicWorkRequest);
            updateInventory(((Vaccine) vaccineComboBox.getSelectedItem()));
            populatePatientDetails();

            jTextFieldName.setText("");
            jTextFieldAge.setText("");
            txtQty.setText("");
            JOptionPane.showMessageDialog(null, "Request created successfully.", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (tblEvents.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select a row from events table");
        } else {
            Events e = (Events) tblEvents.getValueAt(tblEvents.getSelectedRow(), 0);
            populateVaccineInventoryTable(e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
//            java.util.logging.Logger.getLogger(EventVolunteerWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EventVolunteerWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EventVolunteerWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EventVolunteerWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EventVolunteerWorkArea().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEventName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTablePatientDetails;
    private javax.swing.JTable jTableVaccineInventory;
    private javax.swing.JTextField jTextFieldAge;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTable tblEvents;
    private javax.swing.JTextField txtQty;
    private javax.swing.JComboBox vaccineComboBox;
    // End of variables declaration//GEN-END:variables
}
