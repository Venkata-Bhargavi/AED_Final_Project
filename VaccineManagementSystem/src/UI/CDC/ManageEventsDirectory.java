/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI.CDC;

import Business.Enterprise.Enterprise;

/**
 *
 * @author bhargavi
 */
public class ManageEventsDirectory extends javax.swing.JPanel {

    /**
     * Creates new form ManageEventsDirectory
     */
    Enterprise enterprise;
    public ManageEventsDirectory(Enterprise enterprise) {
        initComponents();
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
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Events : Mobile Camps");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 29, -1, -1));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("Events");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 87, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 123, 550, 100));

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
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(519, 536, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 314, 160, -1));
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 355, 160, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 396, 160, -1));
        add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 437, 160, -1));

        jLabel9.setText("Vaccine :");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 575, -1, -1));

        jLabel10.setText("Quantity :");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 625, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 572, 108, -1));
        add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 622, 108, -1));

        jButton1.setText("Request");
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(587, 667, -1, -1));

        jButton2.setText("Create Event");
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 478, -1, -1));

        jButton3.setText("Display Event Inventory");
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 257, 180, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 314, 370, 302));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/kisspng-circle-structure-area-network-5abe0aa1daab97.4949489615224040018957.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        jLabel11.setPreferredSize(new java.awt.Dimension(800, 800));
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 800));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
