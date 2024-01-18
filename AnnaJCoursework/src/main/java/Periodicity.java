/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anna
 */
public class Periodicity extends javax.swing.JFrame {
    static User user;

    /**
     * Creates new form Periodicity
     */
    public Periodicity(User user) {
        initComponents();
        this.user = user;
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
        lblGroup = new javax.swing.JLabel();
        cbPos = new javax.swing.JComboBox<>();
        lblIon = new javax.swing.JLabel();
        cbNeg = new javax.swing.JComboBox<>();
        lblFormulae = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnCalculate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblFormula = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taExplanation = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(113, 141, 206));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        lblGroup.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblGroup.setText("Positive Ion or Group:");

        cbPos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbPos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-None selected-", "Group 1 Metals", "Group 2 Metals", "Group 3 Metals", "Group 8 Gases", "H +", "NH4 +", "Pb 2+", "Cu 2+", "Zn 2+" }));
        cbPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPosActionPerformed(evt);
            }
        });

        lblIon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblIon.setText("Negative Ion or Group:");

        cbNeg.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbNeg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-None selected-", "Group 7", "OH -", "NO3 -", "HCO3 -", "O 2-", "CO3 2-", "SO4 2-" }));
        cbNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNegActionPerformed(evt);
            }
        });

        lblFormulae.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblFormulae.setText("How to work out formulae:");

        btnBack.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnCalculate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCalculate.setText("Calculate");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Formula:");

        lblFormula.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblFormula.setText("<formula>");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel3.setText("(X denotes a positive metal ion from the group chosen, and Y denotes a negative ion from the group chosen)");

        taExplanation.setEditable(false);
        taExplanation.setColumns(20);
        taExplanation.setLineWrap(true);
        taExplanation.setRows(5);
        taExplanation.setWrapStyleWord(true);
        taExplanation.setAutoscrolls(false);
        jScrollPane1.setViewportView(taExplanation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFormulae)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblGroup, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIon, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbPos, 0, 235, Short.MAX_VALUE)
                                    .addComponent(cbNeg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(197, 197, 197)
                        .addComponent(btnCalculate))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblFormula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGroup)
                    .addComponent(cbPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIon)
                    .addComponent(cbNeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalculate))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblFormula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lblFormulae)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnBack)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new CalcsOrPeriod(user).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void cbNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNegActionPerformed
        // TODO add your handling code here:
        String selectedPos = String.valueOf(cbPos.getSelectedItem());
        if (!(selectedPos.equals("-None selected-"))&& String.valueOf(cbPos.getSelectedItem()).equals("Group 8 Gases")){
            cbNeg.setSelectedItem("-None selected-");
            new DialogueBoxGrpAndIon().setVisible(true);
        }
    }//GEN-LAST:event_cbNegActionPerformed

    private void cbPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPosActionPerformed
        // TODO add your handling code here:
        String selectedNeg = String.valueOf(cbNeg.getSelectedItem());
        if (!(selectedNeg.equals("-None selected-"))&& String.valueOf(cbPos.getSelectedItem()).equals("Group 8 Gases")){
            cbNeg.setSelectedItem("-None selected-");
            new DialogueBoxGrpAndIon().setVisible(true);
        }
    }//GEN-LAST:event_cbPosActionPerformed

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        // TODO add your handling code here:
        String positiveName = String.valueOf(cbPos.getSelectedItem());
        String negativeName = String.valueOf(cbNeg.getSelectedItem());
        String[] result = new DatabaseAccess().periodicity(positiveName, negativeName);
        //String[] result = {"formula","pos","neg","pcharge","ncharge","pA", "nA"};
        lblFormula.setText(result[0]);
        taExplanation.setText("As "+ result[1] + " has a charge of "+ result[3] + " and "+ result[2] + " has a charge of "+ result[4] + " , there must be "+ result[5] + " of "+ result[1] + " and "+ result[6] + " of "+ result[2] + " to make the charges neutral.");
    }//GEN-LAST:event_btnCalculateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Periodicity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Periodicity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Periodicity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Periodicity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Periodicity(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCalculate;
    private javax.swing.JComboBox<String> cbNeg;
    private javax.swing.JComboBox<String> cbPos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFormula;
    private javax.swing.JLabel lblFormulae;
    private javax.swing.JLabel lblGroup;
    private javax.swing.JLabel lblIon;
    private javax.swing.JTextArea taExplanation;
    // End of variables declaration//GEN-END:variables
}
