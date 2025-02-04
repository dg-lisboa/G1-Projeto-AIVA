package main.java.com.Menu;


public class VendasMenu extends javax.swing.JFrame {

    // Declaração da condicional para login
    private boolean loginAcesso;
    private String usuarioEmail;
    private String usuarioSenha;

    public VendasMenu() {
        initComponents();
        this.loginAcesso = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        botaoLogin = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campoSenha = new javax.swing.JPasswordField();
        campoEmail = new javax.swing.JTextField();
        campoEmail1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campoEmail2 = new javax.swing.JTextField();
        botaoLogin1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setText("Nova venda");

        botaoLogin.setBackground(new java.awt.Color(108, 46, 242));
        botaoLogin.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        botaoLogin.setForeground(new java.awt.Color(255, 255, 255));
        botaoLogin.setText("Entrar -->");
        botaoLogin.setAlignmentY(0.0F);
        botaoLogin.setBorder(null);
        botaoLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botaoLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoLoginMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 17)); // NOI18N
        jLabel2.setText("Valor");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 17)); // NOI18N
        jLabel3.setText("Sua senha");

        campoSenha.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        campoSenha.setText("jPasswordField1");
        campoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSenhaActionPerformed(evt);
            }
        });

        campoEmail.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        campoEmail.setText("Digite seu email");
        campoEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmailActionPerformed(evt);
            }
        });

        campoEmail1.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        campoEmail1.setText("Digite seu email");
        campoEmail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmail1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 17)); // NOI18N
        jLabel4.setText("Nome cliente");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 17)); // NOI18N
        jLabel5.setText("Referência do Produto");

        campoEmail2.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        campoEmail2.setText("Digite seu email");
        campoEmail2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmail2ActionPerformed(evt);
            }
        });

        botaoLogin1.setBackground(new java.awt.Color(108, 46, 242));
        botaoLogin1.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        botaoLogin1.setForeground(new java.awt.Color(255, 255, 255));
        botaoLogin1.setText("Entrar -->");
        botaoLogin1.setAlignmentY(0.0F);
        botaoLogin1.setBorder(null);
        botaoLogin1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botaoLogin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoLogin1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(53, 53, 53))
                                .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(campoEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(campoEmail2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(botaoLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel5)))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(botaoLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(botaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmailActionPerformed
        String usuarioEmail = campoEmail.getText();
    }//GEN-LAST:event_campoEmailActionPerformed

    private void campoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSenhaActionPerformed
        char[] usuarioSenha = campoSenha.getPassword();
    }//GEN-LAST:event_campoSenhaActionPerformed

    private void botaoLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoLoginMouseClicked
        new ClienteMenu().setVisible(true);
    }//GEN-LAST:event_botaoLoginMouseClicked

    private void campoEmail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmail1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEmail1ActionPerformed

    private void campoEmail2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmail2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEmail2ActionPerformed

    private void botaoLogin1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoLogin1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoLogin1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoLogin;
    private javax.swing.JButton botaoLogin1;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEmail1;
    private javax.swing.JTextField campoEmail2;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    // End of variables declaration//GEN-END:variables
}
