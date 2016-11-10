package FORMS;

import DEV.Metodos;
import static DEV.Metodos.Generar_recibos_poner_cero;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Clientes_estado_de_cuenta extends javax.swing.JFrame {

    public Clientes_estado_de_cuenta() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Facturas a crédito");
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
        Generar_recibos_poner_cero();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField_buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_facturas = new javax.swing.JTable();
        jTextField_total = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_recibos = new javax.swing.JTable();

        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField_buscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        jTextField_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_buscarActionPerformed(evt);
            }
        });
        jTextField_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_buscarKeyReleased(evt);
            }
        });

        jTable_facturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Factura", "Fecha", "Cliente", "Descripción producto", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_facturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_facturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_facturas);
        if (jTable_facturas.getColumnModel().getColumnCount() > 0) {
            jTable_facturas.getColumnModel().getColumn(0).setResizable(false);
            jTable_facturas.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable_facturas.getColumnModel().getColumn(1).setResizable(false);
            jTable_facturas.getColumnModel().getColumn(1).setPreferredWidth(10);
            jTable_facturas.getColumnModel().getColumn(2).setResizable(false);
            jTable_facturas.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTable_facturas.getColumnModel().getColumn(3).setResizable(false);
            jTable_facturas.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable_facturas.getColumnModel().getColumn(4).setResizable(false);
            jTable_facturas.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable_facturas.getColumnModel().getColumn(5).setResizable(false);
            jTable_facturas.getColumnModel().getColumn(5).setPreferredWidth(5);
        }

        jTextField_total.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField_total.setForeground(new java.awt.Color(0, 102, 0));
        jTextField_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_total.setText("0");

        jButton1.setMnemonic('N');
        jButton1.setText("Nuevo recibo de dinero");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable_recibos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Recibos", "Fecha", "Cliente", "Concepto", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_recibos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_recibosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_recibos);
        if (jTable_recibos.getColumnModel().getColumnCount() > 0) {
            jTable_recibos.getColumnModel().getColumn(0).setResizable(false);
            jTable_recibos.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable_recibos.getColumnModel().getColumn(1).setResizable(false);
            jTable_recibos.getColumnModel().getColumn(1).setPreferredWidth(10);
            jTable_recibos.getColumnModel().getColumn(2).setResizable(false);
            jTable_recibos.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTable_recibos.getColumnModel().getColumn(3).setResizable(false);
            jTable_recibos.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable_recibos.getColumnModel().getColumn(4).setResizable(false);
            jTable_recibos.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable_recibos.getColumnModel().getColumn(5).setResizable(false);
            jTable_recibos.getColumnModel().getColumn(5).setPreferredWidth(5);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_total, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_buscarKeyReleased
        Metodos.Clientes_estado_de_cuenta_buscar_cliente_cargar_lista();
        if ((evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            this.setVisible(false);
        }

    }//GEN-LAST:event_jTextField_buscarKeyReleased

    private void jTable_facturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_facturasMouseClicked
       // new Facturacion().setVisible(true);
       // Metodos.Cliente_estado_de_cuenta_ver_factura();
    }//GEN-LAST:event_jTable_facturasMouseClicked

    private void jTextField_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_buscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Metodos.id_recibo = 0;
        new Recibo_de_dinero().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable_recibosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_recibosMouseClicked
        new Recibo_de_dinero().setVisible(true);
        Recibo_de_dinero.jButton_borrado.setVisible(true);
        Metodos.Recibo_de_dinero_seleccionar_para_editar();
    }//GEN-LAST:event_jTable_recibosMouseClicked

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes_estado_de_cuenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable_facturas;
    public static javax.swing.JTable jTable_recibos;
    public static javax.swing.JTextField jTextField_buscar;
    public static javax.swing.JTextField jTextField_total;
    // End of variables declaration//GEN-END:variables
}
