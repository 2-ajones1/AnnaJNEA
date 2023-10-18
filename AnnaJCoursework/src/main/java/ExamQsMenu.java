/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2-ajones1
 */
public class ExamQsMenu extends javax.swing.JFrame {

    /**
     * Creates new form ExamQsMenu
     */
    public ExamQsMenu() {
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

        jPanel1 = new javax.swing.JPanel();
        lblExamQs = new javax.swing.JLabel();
        btnTopic = new javax.swing.JButton();
        btnAccuracy = new javax.swing.JButton();
        btnRandom = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

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

        lblExamQs.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblExamQs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExamQs.setText("Exam Questions");

        btnTopic.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnTopic.setText("By Topic");
        btnTopic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTopicActionPerformed(evt);
            }
        });

        btnAccuracy.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAccuracy.setText("By Lowest Accuracy");
        btnAccuracy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccuracyActionPerformed(evt);
            }
        });

        btnRandom.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRandom.setText("Shuffled");
        btnRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRandomActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblExamQs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(btnAccuracy, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(btnRandom, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblExamQs)
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAccuracy)
                    .addComponent(btnRandom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAccuracy, btnRandom, btnTopic});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new HomePage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnTopicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTopicActionPerformed
        // TODO add your handling code here:
        new ExamQuestion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTopicActionPerformed

    private void btnAccuracyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccuracyActionPerformed
        // TODO add your handling code here:
        new ExamQuestion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAccuracyActionPerformed

    private void btnRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandomActionPerformed
        // TODO add your handling code here:
        new ExamQuestion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRandomActionPerformed

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
            java.util.logging.Logger.getLogger(ExamQsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExamQsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExamQsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExamQsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExamQsMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccuracy;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRandom;
    private javax.swing.JButton btnTopic;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblExamQs;
    // End of variables declaration//GEN-END:variables
}
