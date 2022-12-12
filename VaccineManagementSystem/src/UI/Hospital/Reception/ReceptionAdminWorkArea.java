/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Hospital.Reception;

import Business.Customers.Customer;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Events.Events;
import Business.Network.Network;
import Business.Organization.ReceptionOrganization;
import Business.Patient.Patient;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.ClinicWorkRequest;
import Business.WorkQueue.ReceptionWorkRequest;
import Business.WorkQueue.WorkRequest;
import static Business.sendEmail.sendConfirmationEmail;
import Business.vaccine.Vaccine;
import UI.MainLoginJFrame;
import java.awt.Image;
//import UI.MainLoginJFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Krishnakanth Naik Jarapala
 */
public class ReceptionAdminWorkArea extends javax.swing.JFrame {

    /**
     * Creates new form ReceptionAdminWorkArea
     */
    private UserAccount account;
    private ReceptionOrganization receptionOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private Network network;
     private static final String logoFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String logoImagePath = logoFILENAME+"/Images/logout_blue.png";
private static final String GFILENAME = Paths.get("src").toAbsolutePath().toString();// path to the data store
    private String bgimagePath = GFILENAME+"/Images/my-gradient.png";
    public ReceptionAdminWorkArea(UserAccount account, ReceptionOrganization receptionOrganization, Enterprise enterprise, EcoSystem business, Network network) {
        initComponents();
        this.account = account;
        this.receptionOrganization = receptionOrganization;
        this.enterprise = enterprise;
        this.business = business;
        this.network = network;
         ImageIcon logoimgIcon = new ImageIcon(logoImagePath);
        Image lI = logoimgIcon.getImage();
        Image logoDimg = lI.getScaledInstance(30, 30,Image.SCALE_SMOOTH);
        ImageIcon logoImgThisImg = new ImageIcon(logoDimg);
        lblL.setIcon(logoImgThisImg);
        
        ImageIcon bimgIcon = new ImageIcon(bgimagePath);
        Image bI = bimgIcon.getImage();
        Image cDimg = bI.getScaledInstance(1100, 800,Image.SCALE_SMOOTH);
        ImageIcon cImgThisImg = new ImageIcon(cDimg);
        lblB.setIcon(cImgThisImg);
        lblB2.setIcon(cImgThisImg);
        
        populateWorkQueueTable();
        populateEvents();
    }
    void populateWorkQueueTable() {

        DefaultTableModel dtm = (DefaultTableModel) ReftoDoctorTbl.getModel();
        dtm.setRowCount(0);
        for (WorkRequest work : enterprise.getWorkQueue().getWorkRequestList()) {
            if (work instanceof ReceptionWorkRequest) {
                Object[] row = new Object[6];
                row[0] = ((ReceptionWorkRequest) work).getPatient().getName();
                row[1] = ((ReceptionWorkRequest) work).getPatient().getAge();
                row[2] = work;
                row[3] = ((ReceptionWorkRequest) work).getPatient().getGender();
                row[4] = work.getReceiver();
                row[5] = ((ReceptionWorkRequest) work).getPatient().getDate();
                dtm.addRow(row);
            }
        }
    }

    void populateEvents() {

        DefaultTableModel dtm = (DefaultTableModel) tblEvents.getModel();
        dtm.setRowCount(0);
        Date startDate = new Date();
        for (Events e : network.getEventsDirectory().getEventList()) {
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

            Object row[] = new Object[4];
            row[0] = e;
            if (elapsedDays < 1) {
                row[1] = elapsedHours + "hrs and " + elapsedMinutes + "mins to go";
            } else {
                row[1] = elapsedDays + "days to go";
            }
//            row[2] = e.getHospitalEnterprise();
            row[2] = e.getCapacity();
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ReftoDoctorTbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        radioFemale = new javax.swing.JRadioButton();
        radioMale = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        lblL = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rbYes = new javax.swing.JRadioButton();
        rbNo = new javax.swing.JRadioButton();
        lblB = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEvents = new javax.swing.JTable();
        lblB2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 10, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ReftoDoctorTbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        ReftoDoctorTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Age", "Status", "Gender", "Receiver", "Date"
            }
        ));
        ReftoDoctorTbl.setSelectionBackground(new java.awt.Color(255, 156, 141));
        ReftoDoctorTbl.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(ReftoDoctorTbl);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 106, 1043, 180));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel1.setText("Name:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 348, -1, -1));

        txtName.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 344, 140, -1));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel2.setText("Age:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 391, 59, -1));

        txtAge.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jPanel1.add(txtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 387, 140, -1));

        jButton1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 572, 140, -1));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Reception WorkArea");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 24, 445, 52));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel4.setText("Date:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 475, 59, -1));

        txtDate.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jPanel1.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 471, 140, -1));

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel5.setText("date : dd/MM/yyyy");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 475, -1, -1));

        radioFemale.setBackground(new java.awt.Color(255, 255, 255));
        radioFemale.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        radioFemale.setText("Female");
        radioFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFemaleActionPerformed(evt);
            }
        });
        jPanel1.add(radioFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 430, -1, -1));

        radioMale.setBackground(new java.awt.Color(255, 255, 255));
        radioMale.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        radioMale.setText("Male");
        radioMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMaleActionPerformed(evt);
            }
        });
        jPanel1.add(radioMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 430, -1, -1));

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel6.setText("Gender:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 432, -1, -1));

        lblL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLMouseClicked(evt);
            }
        });
        jPanel1.add(lblL, new org.netbeans.lib.awtextra.AbsoluteConstraints(943, 24, 55, 52));

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel7.setText("Insured");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 516, 59, -1));

        rbYes.setBackground(new java.awt.Color(255, 255, 255));
        rbYes.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        rbYes.setText("Yes");
        rbYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbYesActionPerformed(evt);
            }
        });
        jPanel1.add(rbYes, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 514, -1, -1));

        rbNo.setBackground(new java.awt.Color(255, 255, 255));
        rbNo.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        rbNo.setText("No");
        rbNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoActionPerformed(evt);
            }
        });
        jPanel1.add(rbNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 514, -1, -1));
        jPanel1.add(lblB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 760));

        jTabbedPane1.addTab("Add Patients", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EventName", "Start Date", "Event Capacity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblEvents.setSelectionBackground(new java.awt.Color(255, 156, 141));
        tblEvents.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tblEvents);
        if (tblEvents.getColumnModel().getColumnCount() > 0) {
            tblEvents.getColumnModel().getColumn(0).setResizable(false);
            tblEvents.getColumnModel().getColumn(1).setResizable(false);
            tblEvents.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 102, 670, 146));
        jPanel2.add(lblB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 760));

        jTabbedPane1.addTab("View Events", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Date date = null;
        if ((!txtDate.getText().equals("")) || (txtDate.getText().length() == (10) )) {
            String format = "dd/MM/yyyy";
            String createFlightTimeValidate = txtDate.getText();

            SimpleDateFormat sdf = new SimpleDateFormat(format);
            //Date date = null;
            try {
                date = sdf.parse(createFlightTimeValidate);
            } catch (ParseException ex) {
                // Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (txtName.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please enter Patient Name.");
        } else if (txtAge.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "Please enter Age.");
        } else if (Integer.parseInt(txtAge.getText()) <=0 ) {
            JOptionPane.showMessageDialog(null, "Please enter Valid Age.");
        }
        else if ((txtDate.getText().equals("")) || (txtDate.getText().length() != 10 )) {
            JOptionPane.showMessageDialog(null, "Please enter Date.");
        } else if ((date == null) || (txtDate.getText().length() != 10 )) {
            JOptionPane.showMessageDialog(null, "Please enter valid date");
        }

        else {

            try {
                // TODO add your handling code here:
                ReceptionWorkRequest rwr = new ReceptionWorkRequest();
                Patient p = new Patient();
                p.setName(txtName.getText());
                p.setAge((txtAge.getText()));
                if(radioMale.isSelected())
                {
                    p.setGender("Male");
                }
                if(radioFemale.isSelected())
                {
                    p.setGender("Female");
                }

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date startDate = df.parse(txtDate.getText());

                p.setDate(startDate);

                rwr.setPatient(p);
                rwr.setStatus("Reffered to Doctor");
                rwr.setSender(account);
                rwr.setIncludedFlag(false);

                enterprise.getWorkQueue().getWorkRequestList().add(rwr);
                populateWorkQueueTable();
                
//                Adding Cust to Cust Dir
                Customer cust = business.getCustomerDirectory().addCustomer();
                cust.setName(txtName.getText());
                cust.setInsuarance(rootPaneCheckingEnabled);
                if(rbYes.isSelected())
                {
                    cust.setInsuarance(true);
                }
                if(rbNo.isSelected())
                {
                    cust.setInsuarance(false);
                }
                
                txtName.setText("");
                txtDate.setText("");
                txtAge.setText("");
                JOptionPane.showMessageDialog(null, "Patient record saved.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                sendConfirmationEmail( "jkkn.iitkgp@gmail.com", "Patient Appointment Booked Successfully!" , "New Appointment");
            } catch (ParseException ex) {
                Logger.getLogger(ReceptionAdminWorkArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void radioFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFemaleActionPerformed
        // TODO add your handling code here:
        if(radioFemale.isSelected())
        {
            radioMale.setSelected(false);
        }
    }//GEN-LAST:event_radioFemaleActionPerformed

    private void radioMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMaleActionPerformed
        // TODO add your handling code here:
        if(radioMale.isSelected())
        {
            radioFemale.setSelected(false);
        }
    }//GEN-LAST:event_radioMaleActionPerformed

    private void lblLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        MainLoginJFrame ml = new MainLoginJFrame(business,network);
        ml.setVisible(true);
    }//GEN-LAST:event_lblLMouseClicked

    private void rbYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbYesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbYesActionPerformed

    private void rbNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbNoActionPerformed

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
//            java.util.logging.Logger.getLogger(ReceptionAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ReceptionAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ReceptionAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ReceptionAdminWorkArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ReceptionAdminWorkArea().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ReftoDoctorTbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblB2;
    private javax.swing.JLabel lblL;
    private javax.swing.JRadioButton radioFemale;
    private javax.swing.JRadioButton radioMale;
    private javax.swing.JRadioButton rbNo;
    private javax.swing.JRadioButton rbYes;
    private javax.swing.JTable tblEvents;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
