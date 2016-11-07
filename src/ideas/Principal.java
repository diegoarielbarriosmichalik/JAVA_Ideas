package ideas;

import DEV.Metodos;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {
    
    public Principal() {
        
        initComponents();
        setLocationRelativeTo(null);
        setTitle(Metodos.titulo);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icon.png")).getImage());
        
    //    Metodos.Update_imagen();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                
                close();
            }
        });
        
    }
    
    private void close() {
        if (JOptionPane.showConfirmDialog(rootPane, "¿Salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Metodos.Cerrar_Conexion();
            System.exit(0);
        }
    }
    
    public static int id_usuario;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu_ventas = new javax.swing.JMenu();
        jMenuItem_ventas = new javax.swing.JMenuItem();
        jSeparator_pedidos = new javax.swing.JPopupMenu.Separator();
        jMenuItem_pedidos = new javax.swing.JMenuItem();
        jMenuItem_pedidos_pendientes = new javax.swing.JMenuItem();
        jMenuItem_pedidos_terminados = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu_informes = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(669, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu11.setText("Clientes");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setMnemonic('c');
        jMenuItem8.setText("Clientes");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem8);

        jMenuItem29.setText("Estado de cuenta");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem29);

        jMenuItem5.setText("Historial de ventas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem5);

        jMenuItem20.setText("Cuentas a cobrar");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem20);

        jMenuBar1.add(jMenu11);

        jMenu10.setText("Productos");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setMnemonic('p');
        jMenuItem6.setText("Productos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem6);
        jMenu10.add(jSeparator4);

        jMenuItem15.setText("Rubros");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem15);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem7.setMnemonic('e');
        jMenuItem7.setText("Proveedores");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem7);

        jMenuItem28.setText("Ubicaciones");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem28);
        jMenu10.add(jSeparator3);

        jMenuItem16.setText("Productos con stock bajo");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem16);

        jMenuItem19.setText("Listado de productos");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem19);

        jMenuItem17.setText("Listado de productos por rubro");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem17);

        jMenuBar1.add(jMenu10);

        jMenu5.setText("Compras");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        jMenu5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenu5KeyPressed(evt);
            }
        });

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem10.setMnemonic('r');
        jMenuItem10.setText("Compras");
        jMenuItem10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem10MouseClicked(evt);
            }
        });
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenuItem10.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenuItem10MenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jMenu5.add(jMenuItem10);
        jMenu5.add(jSeparator1);

        jMenuItem14.setText("Listado de Compras");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuBar1.add(jMenu5);

        jMenu_ventas.setText("Ventas");
        jMenu_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu_ventasMouseClicked(evt);
            }
        });
        jMenu_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_ventasActionPerformed(evt);
            }
        });
        jMenu_ventas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMenu_ventasFocusGained(evt);
            }
        });

        jMenuItem_ventas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem_ventas.setMnemonic('v');
        jMenuItem_ventas.setText("Ventas");
        jMenuItem_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ventasActionPerformed(evt);
            }
        });
        jMenu_ventas.add(jMenuItem_ventas);

        jSeparator_pedidos.setForeground(new java.awt.Color(153, 153, 255));
        jMenu_ventas.add(jSeparator_pedidos);

        jMenuItem_pedidos.setText("Pedidos");
        jMenuItem_pedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_pedidosActionPerformed(evt);
            }
        });
        jMenu_ventas.add(jMenuItem_pedidos);

        jMenuItem_pedidos_pendientes.setText("Listado de Pedidos Pendientes");
        jMenuItem_pedidos_pendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_pedidos_pendientesActionPerformed(evt);
            }
        });
        jMenu_ventas.add(jMenuItem_pedidos_pendientes);

        jMenuItem_pedidos_terminados.setText("Listado de Pedidos Terminados");
        jMenuItem_pedidos_terminados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_pedidos_terminadosActionPerformed(evt);
            }
        });
        jMenu_ventas.add(jMenuItem_pedidos_terminados);
        jMenu_ventas.add(jSeparator2);

        jMenuItem2.setText("Listado de ventas por productos");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu_ventas.add(jMenuItem2);
        jMenu_ventas.add(jSeparator5);

        jMenuItem23.setText("Recibo de dinero");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu_ventas.add(jMenuItem23);

        jMenuItem30.setText("Pagarés");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu_ventas.add(jMenuItem30);
        jMenu_ventas.add(jSeparator6);

        jMenuBar1.add(jMenu_ventas);

        jMenu1.setText("Caja");

        jMenuItem4.setText("Apertura de Caja");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setText("Arqueo de Caja");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Contabilidad");

        jMenuItem26.setText("Depositos bancarios");
        jMenu4.add(jMenuItem26);

        jMenuItem24.setText("Cheques");
        jMenu4.add(jMenuItem24);

        jMenuItem25.setText("Pagares");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem25);

        jMenuItem27.setText("Libro banco");
        jMenu4.add(jMenuItem27);

        jMenuBar1.add(jMenu4);

        jMenu_informes.setText("Informes");
        jMenu_informes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu_informesMouseClicked(evt);
            }
        });
        jMenu_informes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenu_informesKeyPressed(evt);
            }
        });
        jMenu_informes.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenu_informesMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
        });

        jMenuItem18.setText("Estado de resultado");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu_informes.add(jMenuItem18);

        jMenuItem13.setText("Balance de compras y ventas");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu_informes.add(jMenuItem13);

        jMenuBar1.add(jMenu_informes);

        jMenu8.setText("Configuracion");
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });
        jMenu8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenu8KeyPressed(evt);
            }
        });

        jMenuItem21.setText("Configuraciones Generales");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem21);

        jMenuItem22.setText("Configuraciones de Impresion");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem22);

        jMenuItem11.setText("Usuarios del sistema");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem11);

        jMenuItem9.setText("Control de Usuarios");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem9);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

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

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Metodos.Verificar_conexion();
    }//GEN-LAST:event_formWindowActivated

    private void jMenu_ventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu_ventasMouseClicked
//        new principal().setVisible(true);
    }//GEN-LAST:event_jMenu_ventasMouseClicked

    private void jMenu5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenu5KeyPressed

    }//GEN-LAST:event_jMenu5KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        

    }//GEN-LAST:event_formKeyPressed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked

//        Productos.jL_Comercio_id.setText(jL_Comercio_Id.getText());
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu_informesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenu_informesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu_informesKeyPressed

    private void jMenu_informesMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenu_informesMenuKeyPressed
        

    }//GEN-LAST:event_jMenu_informesMenuKeyPressed

    private void jMenu_informesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu_informesMouseClicked
//        new caja().setVisible(true);
//        Compras.jT_Buscar_Productos.setEnabled(false);
    }//GEN-LAST:event_jMenu_informesMouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
//        new Caja_arqueo().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked

    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
  //      new Listado_de_ventas_por_productos().setVisible(true);
    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    //    new Listado_de_ventas_por_productos().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_ventasActionPerformed
//        new principal().setVisible(true);
    }//GEN-LAST:event_jMenu_ventasActionPerformed

    private void jMenu_ventasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMenu_ventasFocusGained
//        new principal().setVisible(true);
    }//GEN-LAST:event_jMenu_ventasFocusGained

    private void jMenu8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenu8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu8KeyPressed

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked

    }//GEN-LAST:event_jMenu8MouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
 //       new Productos().setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
     //   new Clientes().setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
      //  new FORMS.Proveedores().setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
      //  new Compras().setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed

    }//GEN-LAST:event_jMenu5ActionPerformed

    private void jMenuItem10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem10MouseClicked

    }//GEN-LAST:event_jMenuItem10MouseClicked

    private void jMenuItem10MenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenuItem10MenuKeyPressed
     //   new Compras().setVisible(true);
    //    Compras.jT_Buscar_Productos.setEnabled(false);
    }//GEN-LAST:event_jMenuItem10MenuKeyPressed

    private void jMenuItem_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ventasActionPerformed
//        new Ventas().setVisible(true);
     //   new Facturacion().setVisible(true);
    }//GEN-LAST:event_jMenuItem_ventasActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
       // new Productos_rubro().setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
      //  new Listado_de_compras().setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       // new Cliente_historial().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
       // new Productos_con_stock_bajo().setVisible(true);
// Metodos.Stock_bajo();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
      //  Metodos.Listado_de_Productos();
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
      //  new Estado_de_Resultado().setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
//        if (Metodos.privilegio == 1) {
//            new Configuraciones().setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "No puedes acceder a este menu. Consulta con el administrador");
//        }
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
//        if (Metodos.privilegio == 1) {
//            new Configuracion_impresion().setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "No puedes acceder a este menu. Consulta con el administrador");
//        }
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
//        if (Metodos.privilegio == 1) {
//            new Usuarios().setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "No puedes acceder a este menu. Consulta con el administrador");
//        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
//        new Caja_apertura().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem_pedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_pedidosActionPerformed
//        new Pedidos().setVisible(true);
    }//GEN-LAST:event_jMenuItem_pedidosActionPerformed

    private void jMenuItem_pedidos_pendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_pedidos_pendientesActionPerformed
//        new Listado_de_pedidos_pendientes().setVisible(true);
    }//GEN-LAST:event_jMenuItem_pedidos_pendientesActionPerformed

    private void jMenuItem_pedidos_terminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_pedidos_terminadosActionPerformed
//        new Listado_de_pedidos_terminados().setVisible(true);
    }//GEN-LAST:event_jMenuItem_pedidos_terminadosActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        
//        if (Metodos.privilegio == 1) {
//            new Usuarios_control().setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "No puedes acceder a este menu. Consulta con el administrador");
//        }

    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
//        new Imagen_de_fondo().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
//        new Listado_compras_ventas_por_factura().setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
//        new Facturas_a_credito().setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
//        new Productos_listado_de_productos_por_rubro().setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
//        new Pagares().setVisible(true);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
//        new Productos_ubicacion().setVisible(true);
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
//        new Clientes_estado_de_cuenta().setVisible(true);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
//        new Pagares().setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
//        new Cuentas_por_cobrar().setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    public static javax.swing.JMenuItem jMenuItem_pedidos;
    public static javax.swing.JMenuItem jMenuItem_pedidos_pendientes;
    public static javax.swing.JMenuItem jMenuItem_pedidos_terminados;
    private javax.swing.JMenuItem jMenuItem_ventas;
    private javax.swing.JMenu jMenu_informes;
    private javax.swing.JMenu jMenu_ventas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    public static javax.swing.JPopupMenu.Separator jSeparator_pedidos;
    // End of variables declaration//GEN-END:variables
}
