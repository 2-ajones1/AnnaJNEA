
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2-ajones1
 */
public class ClassHomePage extends javax.swing.JFrame {
    static User user;
    static String classCode;
    /**
     * Creates new form ClassHomePage
     */
    public ClassHomePage(User user, String classCode) {
        initComponents();
        this.user = user;
        this.classCode = classCode;
        String[] students = new String[29];
        students = new DatabaseAccess().teacherViewClass(classCode, user.getUserID());
        btn1.setText(students[0]);
        btn2.setText(students[1]);
        btn3.setText(students[2]);
        btn4.setText(students[3]);
        btn5.setText(students[4]);
        btn6.setText(students[5]);
        btn7.setText(students[6]);
        btn8.setText(students[7]);
        btn9.setText(students[8]);
        btn10.setText(students[9]);
        btn11.setText(students[10]);
        btn12.setText(students[11]);
        btn13.setText(students[12]);
        btn14.setText(students[13]);
        btn15.setText(students[14]);
        btn16.setText(students[15]);
        btn17.setText(students[16]);
        btn18.setText(students[17]);
        btn19.setText(students[18]);
        btn20.setText(students[19]);
        btn21.setText(students[20]);
        btn22.setText(students[21]);
        btn23.setText(students[22]);
        btn24.setText(students[23]);
        btn25.setText(students[24]);
        btn26.setText(students[25]);
        btn27.setText(students[26]);
        btn28.setText(students[27]);
        btn29.setText(students[28]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton27 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn28 = new javax.swing.JButton();
        btn29 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();

        jButton27.setText("jButton27");

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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Class Name");

        btn1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn1.setText("jButton1");

        btn20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn20.setText("jButton2");

        btn10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn10.setText("jButton3");

        btn2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn2.setText("jButton6");

        btn3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn3.setText("jButton7");

        btn4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn4.setText("jButton8");

        btn5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn5.setText("jButton9");

        btn6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn6.setText("jButton10");

        btn7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn7.setText("jButton11");

        btn8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn8.setText("jButton12");

        btn21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn21.setText("jButton4");

        btn22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn22.setText("jButton5");

        btn23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn23.setText("jButton15");

        btn24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn24.setText("jButton16");

        btn25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn25.setText("jButton17");

        btn9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn9.setText("jButton18");

        btnBack.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btn26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn26.setText("jButton14");

        btn27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn27.setText("jButton19");

        btn28.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn28.setText("jButton20");

        btn29.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn29.setText("jButton21");

        btn11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn11.setText("jButton22");

        btn12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn12.setText("jButton23");

        btn13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn13.setText("jButton24");

        btn14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn14.setText("jButton25");

        btn15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn15.setText("jButton26");

        btn16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn16.setText("jButton28");

        btn17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn17.setText("jButton29");

        btn18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn18.setText("jButton30");

        btn19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn19.setText("jButton31");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn1)
                            .addComponent(btn2)
                            .addComponent(btn3)
                            .addComponent(btn4)
                            .addComponent(btn5)
                            .addComponent(btn6)
                            .addComponent(btn7)
                            .addComponent(btn8)
                            .addComponent(btn9)
                            .addComponent(btnBack))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn22))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn21))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn24))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn23))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn26))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn25))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn28))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn27))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn29))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn10)
                                        .addGap(175, 175, 175)
                                        .addComponent(btn20)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn1, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn2, btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnBack});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn1)
                    .addComponent(btn20)
                    .addComponent(btn10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn2)
                    .addComponent(btn21)
                    .addComponent(btn11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn3)
                    .addComponent(btn22)
                    .addComponent(btn12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn4)
                    .addComponent(btn23)
                    .addComponent(btn13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn5)
                    .addComponent(btn24)
                    .addComponent(btn14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn25)
                        .addComponent(btn15))
                    .addComponent(btn6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn7)
                    .addComponent(btn26)
                    .addComponent(btn16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn17)
                        .addComponent(btn27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn18)
                        .addComponent(btn28)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn19)
                        .addComponent(btn29)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn1, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn2, btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnBack});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new ClassesHome(user).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(ClassHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClassHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClassHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClassHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClassHomePage(user, classCode).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn18;
    private javax.swing.JButton btn19;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn26;
    private javax.swing.JButton btn27;
    private javax.swing.JButton btn28;
    private javax.swing.JButton btn29;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton jButton27;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
