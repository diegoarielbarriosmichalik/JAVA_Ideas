package DEV;

import FORMS.Avisos;
import FORMS.Balance;
import FORMS.Clientes_Ciudad;
import FORMS.Clientes;
import FORMS.Clientes_ABM;
import static FORMS.Clientes_ABM.jTextField_ruc;
import FORMS.Clientes_estado_de_cuenta;
import FORMS.Configuracion;
import FORMS.Facebook;
import FORMS.Facebook_publicaciones;
import FORMS.Facebook_publicaciones_clientes;
import FORMS.Logueo;
import static FORMS.Logueo.jPasswordField1;
import static FORMS.Logueo.jTextField1;
import FORMS.Obligaciones;
import FORMS.Obligaciones_ABM;
import FORMS.Obligaciones_proveedor;
import FORMS.Pagos;
import FORMS.Pagos_ABM;
import FORMS.Pagos_clientes;
import FORMS.Principal;
import FORMS.Proveedor;
import FORMS.Recibo_de_dinero;
import FORMS.Recibo_de_dinero_clientes;
import FORMS.Cliente_Rubro;
import FORMS.Recibo;
import FORMS.Usuarios_ABM;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sun.security.util.Password;

public class Metodos {

    public static Connection conexion = null;

    public synchronized static String getHoy_format2() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String hoy = ft.format(dNow);
        return hoy;
    }

    public static String nombre = null;
    public static String empresa = null;
    public static String titulo = "";
    public static String ubicacion_proyecto = null;
    public static int id_usuario = 0;
    public static int id = 0;
    public static int id_ciudad = 0;
    public static int id_cliente = 0;
    public static int id_proveedor = 0;
    public static int id_rubro = 0;
    public static int periodo = 0;
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static ResultSetMetaData rsm;
    public static DefaultTableModel dtm;
    public static long total_pagos = 0;
    public static long total_obligaciones = 0;

    public static int id_recibo = 0;
    public static int max = 0;

    public synchronized static void Ciudad_guardar(String ciudad) {
        try {

            if (ciudad.isEmpty() || ciudad.length() < 1) {
                JOptionPane.showMessageDialog(null, "Complete los campos");
            } else if (id_ciudad == 0) {

                Statement st1 = conexion.createStatement();
                ResultSet result = st1.executeQuery("SELECT MAX(id_ciudad) FROM ciudad");
                if (result.next()) {
                    id_ciudad = result.getInt(1) + 1;
                }

                PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO ciudad VALUES(?,?)");
                ST_update.setInt(1, id_ciudad);
                ST_update.setString(2, ciudad);
                ST_update.executeUpdate();

                JOptionPane.showMessageDialog(null, "Guardado correctamente");
            } else {
                PreparedStatement st2 = conexion.prepareStatement(""
                        + "UPDATE ciudad "
                        + "SET ciudad ='" + ciudad + "' "
                        + "WHERE id_ciudad = '" + id_ciudad + "'");
                st2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Guardado correctamente");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Usuario_guardar(String nombre_usuario, String usuario, String pass) {

        try {
            id = 0;
            int id_user = 0;
            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery("SELECT MAX(id_usuario) FROM usuario");
            if (result.next()) {
                id_user = result.getInt(1) + 1;
            }
            int privilegio = 0;
            if (Usuarios_ABM.jCheckBox_admin.isSelected() == true) {
                privilegio = 1;
            }

            PreparedStatement stUpdateProducto = conexion.prepareStatement("INSERT INTO usuario VALUES(?,?,?,?,?)");
            stUpdateProducto.setInt(1, id_user);
            stUpdateProducto.setString(2, usuario);
            stUpdateProducto.setString(3, pass);
            stUpdateProducto.setInt(4, privilegio);
            stUpdateProducto.setString(5, nombre_usuario);
            stUpdateProducto.executeUpdate();

            ResultSet rs_2 = st.executeQuery("SELECT MAX(id_control) FROM control_de_acceso");
            if (rs_2.next()) {
                id = rs_2.getInt(1) + 1;
            }

            PreparedStatement st2 = conexion.prepareStatement("INSERT INTO control_de_acceso VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            st2.setInt(1, id);
            st2.setInt(2, id_user);
            st2.setInt(3, 0);
            st2.setInt(4, 0);
            st2.setInt(5, 0);
            st2.setInt(6, 0);
            st2.setInt(7, 0);
            st2.setInt(8, 0);
            st2.setInt(9, 0);
            st2.setString(10, " ");
            st2.setString(11, " ");
            st2.setInt(12, 1);
            st2.setInt(13, 0);
            st2.setInt(14, 0);
            st2.executeUpdate();

            JOptionPane.showMessageDialog(null, "Agregado correctamente");
        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }

    public static void Recibo_de_dinero_borrar() {
        try {
            PreparedStatement stUpdateAuxiliar2 = conexion.prepareStatement(""
                    + "delete from recibos WHERE id_recibo ='" + id_recibo + "'");
            stUpdateAuxiliar2.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public static void Recibo_imprimir() {
        try {
            String path = ubicacion_proyecto + "\\reportes\\recibo.jasper";
            JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conexion);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Recibo_de_dinero_seleccionar_para_editar() {
        DefaultTableModel tm = (DefaultTableModel) Clientes_estado_de_cuenta.jTable_recibos.getModel();
        id_recibo = Integer.parseInt(String.valueOf(tm.getValueAt(Clientes_estado_de_cuenta.jTable_recibos.getSelectedRow(), 0)));
        Buscar_recibo();
    }

    public static void Buscar_recibo() {
        try {
            String sql = "select  * "
                    + "from recibos "
                    + "inner join cliente on cliente.id_cliente = recibos.id_cliente "
                    + "where id_recibo ='" + id_recibo + "' ";
            Statement ST_Productos = conexion.createStatement();
            ResultSet RS_Productos = ST_Productos.executeQuery(sql);
            while (RS_Productos.next()) {
                id_cliente = RS_Productos.getInt("id_cliente");
                Recibo_de_dinero.jTextField_Cliente.setText(RS_Productos.getString("nombre").trim());
                Recibo_de_dinero.jTextField_concepto.setText(RS_Productos.getString("concepto").trim());
                Recibo_de_dinero.jTextField_dinero.setText(RS_Productos.getString("dinero_entregado"));
                Recibo_de_dinero.jDateChooser2.setDate(RS_Productos.getDate("fecha"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public static void Generar_recibos_poner_cero() {
        try {
            PreparedStatement st2 = conexion.prepareStatement(""
                    + "UPDATE cuenta "
                    + "SET generar_recibo ='0', "
                    + "generar_recibo_str = ' ' "
                    + "WHERE generar_recibo = '1'");
            st2.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Clientes_estado_de_cuenta_buscar_cliente_cargar_lista() {
        try {
            dtm = (DefaultTableModel) Clientes_estado_de_cuenta.jTable_recibos.getModel();
            for (int j = 0; j < Clientes_estado_de_cuenta.jTable_recibos.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            dtm = (DefaultTableModel) Clientes_estado_de_cuenta.jTable_facturas.getModel();
            for (int j = 0; j < Clientes_estado_de_cuenta.jTable_facturas.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ps = conexion.prepareStatement(""
                    + "select id_recibo, id_recibo, fecha, cliente.nombre, concepto, dinero_entregado "
                    + "from cliente "
                    + "inner join recibos on recibos.id_cliente = cliente.id_cliente "
                    + "where cliente.nombre ilike '%" + Clientes_estado_de_cuenta.jTextField_buscar.getText() + "%' "
                    + "and cliente.borrado_int != '1' "
                    + "order by id_recibo DESC ");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    if (rs.getObject(i + 1) != null) {
                        if (rs.getObject(i + 1).toString().length() > 1) {
                            rows[i] = rs.getObject(i + 1).toString().trim();
                        } else {
                            rows[i] = rs.getObject(i + 1);
                        }
                    }
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Clientes_estado_de_cuenta.jTable_recibos.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }
            ps = conexion.prepareStatement(""
                    + "select cuenta.id_cuenta, factura,  cuenta.fecha_date, cliente.nombre, productos.nombre, cuenta_detalle.total "
                    + "from cliente "
                    + "inner join cuenta on cuenta.id_cliente = cliente.id_cliente "
                    + "inner join cuenta_detalle on cuenta_detalle.id_cuenta = cuenta.id_cuenta "
                    + "inner join productos on productos.id_producto = cuenta_detalle.id_producto "
                    + "where cliente.nombre ilike '%" + Clientes_estado_de_cuenta.jTextField_buscar.getText() + "%' "
                    + "and tipo = '2' "
                    + "and id_estado != '11' "
                    + "and cuenta.id_estado != '9' "
                    + "and cliente.borrado_int != '1' "
                    + "order by id_cuenta DESC ");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    if (rs.getObject(i + 1) != null) {
                        if (rs.getObject(i + 1).toString().length() > 1) {
                            rows[i] = rs.getObject(i + 1).toString().trim();
                        } else {
                            rows[i] = rs.getObject(i + 1);
                        }
                    }
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Clientes_estado_de_cuenta.jTable_facturas.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

            long recibos = 0;
            Clientes_estado_de_cuenta.jTextField_total.setText("0");

            Statement ST_Productos8 = conexion.createStatement();
            ResultSet RS_Productos8 = ST_Productos8.executeQuery(""
                    + "select SUM(dinero_entregado) "
                    + "from cliente "
                    + "inner join recibos on recibos.id_cliente = cliente.id_cliente "
                    + "where cliente.nombre ilike '%" + Clientes_estado_de_cuenta.jTextField_buscar.getText() + "%' "
                    + "and cliente.borrado_int != '1' ");
            if (RS_Productos8.next()) {
                recibos = RS_Productos8.getLong(1);
            }

            Statement ST_Productos7 = conexion.createStatement();
            ResultSet RS_Productos7 = ST_Productos7.executeQuery(""
                    + "select SUM(cuenta_detalle.total) "
                    + "from cliente "
                    + "inner join cuenta on cuenta.id_cliente = cliente.id_cliente "
                    + "inner join cuenta_detalle on cuenta_detalle.id_cuenta = cuenta.id_cuenta "
                    + "inner join productos on productos.id_producto = cuenta_detalle.id_producto "
                    + "where cliente.nombre ilike '%" + Clientes_estado_de_cuenta.jTextField_buscar.getText() + "%' "
                    + "and tipo = '2' "
                    + "and id_estado != '11' "
                    + "and cuenta.id_estado != '9' "
                    + "and cliente.borrado_int != '1' ");
            if (RS_Productos7.next()) {
                if (RS_Productos7.getLong(1) > 0) {
                    long total = RS_Productos7.getLong(1) - recibos;
                    String str_puntitos = getSepararMiles(String.valueOf(total));
                    Clientes_estado_de_cuenta.jTextField_total.setText(str_puntitos);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        }
    }

    public synchronized static void Recibo_cargar_lista() {
        try {
            dtm = (DefaultTableModel) Recibo.jTable_recibos.getModel();
            for (int j = 0; j < Recibo.jTable_recibos.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }

            ps = conexion.prepareStatement(""
                    + "select id_recibo, id_recibo, fecha, cliente.nombre, concepto, dinero_entregado "
                    + "from cliente "
                    + "inner join recibos on recibos.id_cliente = cliente.id_cliente "
                    + "where cliente.nombre ilike '%" + Recibo.jTextField_buscar.getText() + "%' "
                    + "and cliente.borrado != '1' "
                    + "order by id_recibo DESC ");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    if (rs.getObject(i + 1) != null) {
                        if (rs.getObject(i + 1).toString().length() > 1) {
                            rows[0] = rs.getObject(1).toString().trim();
                            rows[1] = rs.getObject(2).toString().trim();
                            rows[2] = rs.getObject(3).toString().trim();
                            rows[3] = rs.getObject(4).toString().trim();
                            rows[4] = rs.getObject(5).toString().trim();
                            rows[5] = getSepararMiles(rs.getObject(6).toString());
                        }
                    }
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Recibo.jTable_recibos.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

            long recibos = 0;
            Recibo.jTextField_total.setText("0");

            Statement ST_Productos8 = conexion.createStatement();
            ResultSet RS_Productos8 = ST_Productos8.executeQuery(""
                    + "select SUM(dinero_entregado) "
                    + "from cliente "
                    + "inner join recibos on recibos.id_cliente = cliente.id_cliente "
                    + "where cliente.nombre ilike '%" + Recibo.jTextField_buscar.getText() + "%' "
                    + "and cliente.borrado != '1' ");
            if (RS_Productos8.next()) {
                recibos = RS_Productos8.getLong(1);
            }

            Recibo.jTextField_total.setText(getSepararMiles(String.valueOf(recibos)));

        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        }
    }

    public synchronized static void Recibo_de_dinero_buscar_cliente_agregar_desde_lista() {
        DefaultTableModel tm = (DefaultTableModel) Recibo_de_dinero_clientes.jTable_clientes.getModel();
        id_cliente = Integer.parseInt(String.valueOf(tm.getValueAt(Recibo_de_dinero_clientes.jTable_clientes.getSelectedRow(), 0)));
        Recibo_de_dinero.jTextField_Cliente.setText(String.valueOf(tm.getValueAt(Recibo_de_dinero_clientes.jTable_clientes.getSelectedRow(), 1)));
    }

    public synchronized static String Clientes_buscar_por_id(int id_cliente) {

        String nombre = null;
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente where id_cliente = '" + id_cliente + "'");
            while (rs.next()) {
                nombre = rs.getString("nombre").trim();
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return nombre;
    }

    public synchronized static String Dinero_a_letras(long numeroINT) throws ClassNotFoundException, SQLException {

        Numero_a_Letra NumLetra = new Numero_a_Letra();
        String cantidad_string = Long.toString(numeroINT);
        String aRemplazar = NumLetra.Convertir(cantidad_string, true);
        String remplazado = aRemplazar.replace("0", "");
        return remplazado;

    }

    public static class Numero_a_Letra {

        private final String[] UNIDADES = {"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
        private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
            "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
            "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
        private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
            "setecientos ", "ochocientos ", "novecientos "};

        public Numero_a_Letra() {
        }

        public String Convertir(String numero, boolean mayusculas) {
            String literal = "";
            String parte_decimal;
            //si el numero utiliza (.) en lugar de (,) -> se reemplaza
            numero = numero.replace(".", ",");
            //si el numero no tiene parte decimal, se le agrega ,00
            if (numero.indexOf(",") == -1) {
                numero = numero + ",0";
            }
            //se valida formato de entrada -> 0,00 y 999 999 999,00
            if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
                //se divide el numero 0000000,00 -> entero y decimal
                String Num[] = numero.split(",");
                //de da formato al numero decimal
                parte_decimal = Num[1] + "";
                //se convierte el numero a literal
                if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                    literal = "cero ";
                } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                    literal = getMillones(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                    literal = getMiles(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                    literal = getCentenas(Num[0]);
                } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                    literal = getDecenas(Num[0]);
                } else {//sino unidades -> 9
                    literal = getUnidades(Num[0]);
                }
                //devuelve el resultado en mayusculas o minusculas
                if (mayusculas) {
                    return (literal + parte_decimal).toUpperCase();
                } else {
                    return (literal + parte_decimal);
                }
            } else {//error, no se puede convertir
                return literal = null;
            }
        }

        /* funciones para convertir los numeros a literales */
        private String getUnidades(String numero) {// 1 - 9
            //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
            String num = numero.substring(numero.length() - 1);
            return UNIDADES[Integer.parseInt(num)];
        }

        private String getDecenas(String num) {// 99                        
            int n = Integer.parseInt(num);
            if (n < 10) {//para casos como -> 01 - 09
                return getUnidades(num);
            } else if (n > 19) {//para 20...99
                String u = getUnidades(num);
                if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                    return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
                } else {
                    return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
                }
            } else {//numeros entre 11 y 19
                return DECENAS[n - 10];
            }
        }

        private String getCentenas(String num) {// 999 o 099
            if (Integer.parseInt(num) > 99) {//es centena
                if (Integer.parseInt(num) == 100) {//caso especial
                    return " cien ";
                } else {
                    return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
                }
            } else {//por Ej. 099 
                //se quita el 0 antes de convertir a decenas
                return getDecenas(Integer.parseInt(num) + "");
            }
        }

        private String getMiles(String numero) {// 999 999
            //obtiene las centenas
            String c = numero.substring(numero.length() - 3);
            //obtiene los miles
            String m = numero.substring(0, numero.length() - 3);
            String n = "";
            //se comprueba que miles tenga valor entero
            if (Integer.parseInt(m) > 0) {
                n = getCentenas(m);
                return n + "mil " + getCentenas(c);
            } else {
                return "" + getCentenas(c);
            }

        }

        private String getMillones(String numero) { //000 000 000        
            //se obtiene los miles
            String miles = numero.substring(numero.length() - 6);
            //se obtiene los millones
            String millon = numero.substring(0, numero.length() - 6);
            String n = "";

            int mill = Integer.parseInt(millon);

            if (millon.length() > 1) {
                n = getCentenas(millon) + "millones ";
            } else {
                if (mill == 1) {
                    n = getCentenas(millon) + "millon ";
                }
                if (mill > 1) {
                    n = getCentenas(millon) + "millones ";
                }
            }

            return n + getMiles(miles);
        }
    }

    public static void Recibos_Max() {

        try {
            Statement st4 = conexion.createStatement();
            ResultSet rs2 = st4.executeQuery("SELECT MAX(id_recibo) FROM recibos");
            if (rs2.next()) {
                max = rs2.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public static void Recibos_guardar() {
        try {
            String nombre = Clientes_buscar_por_id(id_cliente);

            Date fecha_date = Recibo_de_dinero.jDateChooser2.getDate();

            String dinero = Recibo_de_dinero.jTextField_dinero.getText().replace(".", "");
            String dinero_en_letras = Dinero_a_letras(Integer.parseInt(dinero));

            PreparedStatement stClienteBorrar = conexion.prepareStatement("UPDATE recibos SET activa='0' WHERE activa  ='1'");
            stClienteBorrar.executeUpdate();

            if (id_recibo == 0) {
                Recibos_Max();

                PreparedStatement st2 = conexion.prepareStatement("INSERT INTO recibos VALUES(?,?,?,?,?,?,?,?)");
                st2.setInt(1, max);
                st2.setInt(2, id_cliente);
                st2.setString(3, Recibo_de_dinero.jTextField_concepto.getText());
                st2.setInt(4, Integer.parseInt(dinero));
                st2.setDate(5, util_Date_to_sql_date(fecha_date));
                st2.setString(6, dinero_en_letras);
                st2.setInt(7, 0);
                st2.setInt(8, 1);
                st2.executeUpdate();

            } else {
                PreparedStatement ST2 = conexion.prepareStatement(""
                        + "UPDATE recibos "
                        + "SET id_cliente='" + id_cliente + "', "
                        + "concepto='" + Recibo_de_dinero.jTextField_concepto.getText() + "', "
                        + "dinero_entregado='" + dinero + "', "
                        + "dinero_letras='" + dinero_en_letras + "', "
                        + "fecha ='" + util_Date_to_sql_date(fecha_date) + "', "
                        + "activa ='1' "
                        + "WHERE id_recibo  ='" + id_recibo + "'");
                ST2.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
        }

    }

    public static void Recibo_de_dinero_clientes_cargar_jtable() {
        try {
            dtm = (DefaultTableModel) Recibo_de_dinero_clientes.jTable_clientes.getModel();
            for (int j = 0; j < Recibo_de_dinero_clientes.jTable_clientes.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            PreparedStatement ps2 = conexion.prepareStatement(""
                    + "select id_cliente, nombre "
                    + "from cliente "
                    + "where nombre ilike '%" + Recibo_de_dinero_clientes.jTextField_buscar.getText() + "%'");
            ResultSet rs2 = ps2.executeQuery();
            rsm = rs2.getMetaData();
            ArrayList<Object[]> data2 = new ArrayList<>();
            while (rs2.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] = rs2.getObject(i + 1).toString().trim();
                }
                data2.add(rows);
            }
            dtm = (DefaultTableModel) Recibo_de_dinero_clientes.jTable_clientes.getModel();
            for (int i = 0; i < data2.size(); i++) {
                dtm.addRow(data2.get(i));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Configuracion_traer_datos() {
        try {
            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT * FROM configuracion");
            if (result.next()) {
                Configuracion.jTextField_nombre.setText(result.getString("empresa").trim());
                Configuracion.jTextField_periodo.setText(result.getString("periodo"));
                Configuracion.jTextField_direccion.setText(result.getString("direccion"));
                Configuracion.jTextField_telefono.setText(result.getString("telefono"));
                Configuracion.jTextField_mensaje.setText(result.getString("mensaje"));
                Configuracion.jTextField_ruc.setText(result.getString("ruc"));
                Configuracion.jTextField_factura.setText(result.getString("factura"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static void Configuracion_guardar(String nombre, String direccion, String ruc, String telefono, String mensaje,
            String factura, String periodo_str) {
        try {
            if (nombre.length() > 0) {
                if (isNumeric(periodo_str)) {
                    PreparedStatement stClienteBorrar3 = conexion.prepareStatement(""
                            + "UPDATE configuracion "
                            + "SET empresa='" + nombre + "', "
                            + "direccion = '" + direccion + "', "
                            + "telefono = '" + telefono + "', "
                            + "factura = '" + factura + "', "
                            + "mensaje = '" + mensaje + "', "
                            + "ruc = '" + ruc + "', "
                            + "periodo = '" + periodo_str + "' "
                    );
                    periodo = Integer.parseInt(periodo_str);
                    titulo = nombre + " - Periodo: " + periodo_str;
                    stClienteBorrar3.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Guardado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Periodo");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Nombre");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public synchronized static void Proveedores_guardar(String proveedor, String ruc, String telefono) {
        try {
            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT MAX(id_proveedor) FROM proveedor");
            if (result.next()) {
                id = result.getInt(1) + 1;
            }

            if ((proveedor.length() > 0) && (ruc.length() > 0) && (telefono.length() > 0)) {
                PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO proveedor VALUES(?,?,?,?,?, ?,?,?,? )");
                ST_update.setInt(1, id);
                ST_update.setString(2, proveedor);
                ST_update.setString(3, telefono);
                ST_update.setString(4, ruc);
                ST_update.setString(5, "");
                ST_update.setString(6, "");
                ST_update.setString(7, "");
                ST_update.setString(8, proveedor);
                ST_update.setInt(9, 0);
                ST_update.executeUpdate();
                JOptionPane.showMessageDialog(null, "Guardado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Complete todos los campos");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Obligaciones_Guardar(String monto, String mes) {
        try {
            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT MAX(id_obligacion) FROM obligacion");
            if (result.next()) {
                id = result.getInt(1) + 1;
            }

            PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO obligacion VALUES(?,?,?,?,?,?)");
            ST_update.setInt(1, id);
            ST_update.setInt(2, id_proveedor);
            ST_update.setString(3, mes);
            ST_update.setLong(4, Long.parseLong(monto.replace(".", "")));
            ST_update.setString(5, "PENDIENTE");
            ST_update.setInt(6, periodo);
            ST_update.executeUpdate();

            JOptionPane.showMessageDialog(null, "Agregado correctamente");

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Pagos_Guardar(String monto, String mes, Date fecha) {
        try {
            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT MAX(id_pago) FROM pago");
            if (result.next()) {
                id = result.getInt(1) + 1;
            }

            PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO pago VALUES(?,?,?,?,?,?,?,?)");
            ST_update.setInt(1, id);
            ST_update.setInt(2, id_cliente);
            ST_update.setLong(3, Long.valueOf(monto.replace(".", "")));
            ST_update.setString(4, mes);
            ST_update.setString(5, "PENDIENTE");
            ST_update.setDate(6, util_Date_to_sql_date(fecha));
            ST_update.setInt(7, periodo);
            ST_update.setInt(8, 0);
            ST_update.executeUpdate();

            JOptionPane.showMessageDialog(null, "Agregado correctamente");

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static java.sql.Date util_Date_to_sql_date(Date fecha) {
        java.sql.Date fecha_sql_date = null;
        if (fecha != null) {
            java.util.Date utilDate = fecha;
            fecha_sql_date = new java.sql.Date(utilDate.getTime());
        }
        return fecha_sql_date;
    }

    public synchronized static void Facebook_publicaciones_Guardar(String publicacion, Date fecha, String monto) {
        try {
            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT MAX(id_facebook) FROM facebook");
            if (result.next()) {
                id = result.getInt(1) + 1;
            }
            monto = monto.replace(".", "");
            if ((publicacion.length() > 0) && (fecha != null) && (isNumeric(monto))) {
                PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO facebook VALUES(?,?,?,?,?,?,?)");
                ST_update.setInt(1, id);
                ST_update.setInt(2, id_cliente);
                ST_update.setString(3, publicacion);
                ST_update.setDate(4, util_Date_to_sql_date(fecha));
                ST_update.setLong(5, Long.parseLong(monto));
                ST_update.setString(6, "PENDIENTE");
                ST_update.setInt(7, 0);
                ST_update.executeUpdate();
                JOptionPane.showMessageDialog(null, "Guardado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Complete todos los campos");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Cliente_producto_guardar(String producto) {
        try {

            if (id_cliente > 0) {

                Statement st1 = conexion.createStatement();
                ResultSet result = st1.executeQuery("SELECT MAX(id_cliente_producto) FROM cliente_producto");
                if (result.next()) {
                    id = result.getInt(1) + 1;
                }

                PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO cliente_producto VALUES(?,?,?)");
                ST_update.setInt(1, id);
                ST_update.setString(2, producto);
                ST_update.setInt(3, id_cliente);
                ST_update.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un cliente para continuar");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Cliente_colores_guardar(String color) {
        try {

            if (id_cliente > 0) {

                Statement st1 = conexion.createStatement();
                ResultSet result = st1.executeQuery("SELECT MAX(id_cliente_colores) FROM cliente_colores");
                if (result.next()) {
                    id = result.getInt(1) + 1;
                }

                PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO cliente_colores VALUES(?,?,?)");
                ST_update.setInt(1, id);
                ST_update.setString(2, color);
                ST_update.setInt(3, id_cliente);
                ST_update.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un cliente para continuar");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Cliente_trabajos_guardar(String trabajo, String monto) {
        try {

            if (id_cliente > 0) {

                Statement st1 = conexion.createStatement();
                ResultSet result = st1.executeQuery("SELECT MAX(id_cliente_trabajo_extra) FROM cliente_trabajo_extra");
                if (result.next()) {
                    id = result.getInt(1) + 1;
                }

                PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO cliente_trabajo_extra VALUES(?,?,?,?,?,?)");
                ST_update.setInt(1, id);
                ST_update.setString(2, trabajo);
                ST_update.setLong(3, Long.valueOf(monto.replace(".", "")));
                ST_update.setString(4, "");
                ST_update.setInt(5, 0);
                ST_update.setInt(6, id_cliente);
                ST_update.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un cliente para continuar");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Rubro_guardar(String rubro) {
        try {
            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT MAX(id_rubro) FROM rubro");
            if (result.next()) {
                id = result.getInt(1) + 1;
            }

            PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO rubro VALUES(?,?)");
            ST_update.setInt(1, id);
            ST_update.setString(2, rubro);
            ST_update.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Clientes_llevar_datos_a_editar() {
        try {
            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT * FROM cliente "
                    + "inner join rubro on rubro.id_rubro = cliente.id_rubro "
                    + "inner join ciudad on ciudad.id_ciudad = cliente.id_ciudad "
                    + "where id_cliente = '" + id_cliente + "'");
            if (result.next()) {

                Clientes_ABM.jTextField_nombre.setText(result.getString("nombre").trim());
                Clientes_ABM.jTextField_ruc.setText(result.getString("ruc").trim());
                Clientes_ABM.jTextField_direccion.setText(result.getString("direccion").trim());
                Clientes_ABM.jTextField_telefono.setText(result.getString("telefono").trim());
                Clientes_ABM.jTextField_email.setText(result.getString("email").trim());
                Clientes_ABM.jTextField_encargado.setText(result.getString("encargado").trim());
                Clientes_ABM.jTextField_lunes_a_viernes.setText(result.getString("horario_atencion_lunes_a_viernes").trim());
                Clientes_ABM.jTextField_sabado.setText(result.getString("horario_de_atencion_sabado").trim());
                Clientes_ABM.jTextField_marcas.setText(result.getString("marcas").trim());
                Clientes_ABM.jTextField_paginas.setText(result.getString("paginas").trim());
                Clientes_ABM.jTextField_preferencia.setText(result.getString("preferencias").trim());
                Clientes_ABM.jTextField_sugerencia.setText(result.getString("sugerencias").trim());
                Clientes_ABM.jTextField_ciudad.setText(result.getString("ciudad").trim());
                Clientes_ABM.jTextField_mensual.setText(getSepararMiles(result.getString("mensual")));
                Clientes_ABM.jTextField_ideas.setText(getSepararMiles(result.getString("ideas")));
                Clientes_ABM.jTextField_marandu.setText(getSepararMiles(result.getString("marandu")));
                Clientes_ABM.jDateChooser_vencimiento.setDate((result.getDate("vencimiento")));
                id_ciudad = result.getInt("id_ciudad");
                Clientes_ABM.jTextField_rubro.setText(result.getString("rubro").trim());
                id_rubro = result.getInt("id_rubro");
                Clientes_ABM.jTextField_nombre.requestFocus();

            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public synchronized static void Cliente_Guardar(
            String cliente_nombre, String direccion, String telefono, String ruc, String email,
            int id_ciudad,
            String encargado, String horario_lunavier, String horario_sabado,
            String paginas_str, int id_rubro,
            String sugerencia, String preferencia, String marca, String mensual, String marandu, String ideas, Date vencimiento
    ) {
        try {

            int paginas = 0;
            if (isNumeric(paginas_str) == true) {
                paginas = Integer.parseInt(paginas_str);
            }

            if (id_cliente == 0) {
                boolean ruc_existe = Metodos.Clientes_buscar_por_ruc(jTextField_ruc.getText());
                if (ruc_existe == false) {
                    if ((cliente_nombre.length() < 1) || (ruc.length() < 1)) {
                        JOptionPane.showMessageDialog(null, "Nombre y RUC no puede ser vacio");
                    } else {
                        Statement st1 = conexion.createStatement();
                        ResultSet result = st1.executeQuery("SELECT MAX(id_cliente) FROM cliente");
                        if (result.next()) {
                            id = result.getInt(1) + 1;
                        }

                        if (vencimiento == null) {

                            PreparedStatement ST_update = conexion.prepareStatement(""
                                    + "INSERT INTO cliente VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,? )");
                            ST_update.setInt(1, id);
                            ST_update.setString(2, cliente_nombre);
                            ST_update.setString(3, direccion);
                            ST_update.setString(4, telefono);
                            ST_update.setString(5, ruc);
                            ST_update.setString(6, email);
                            ST_update.setDate(7, null);
                            ST_update.setInt(8, 0);
                            ST_update.setInt(9, 0); //borrado
                            ST_update.setString(10, "");
                            ST_update.setString(11, "");
                            ST_update.setInt(12, id_ciudad);
                            ST_update.setString(13, encargado);
                            ST_update.setString(14, horario_lunavier);
                            ST_update.setString(15, horario_sabado);
                            ST_update.setInt(16, paginas);
                            ST_update.setInt(17, id_rubro);
                            ST_update.setString(18, sugerencia);
                            ST_update.setString(19, preferencia);
                            ST_update.setString(20, marca);
                            ST_update.setLong(21, Long.parseLong(mensual.replace(".", "")));
                            ST_update.setLong(22, Long.parseLong(marandu.replace(".", "")));
                            ST_update.setLong(23, Long.parseLong(ideas.replace(".", "")));
                            ST_update.executeUpdate();
                        } else {

                            PreparedStatement ST_update = conexion.prepareStatement(""
                                    + "INSERT INTO cliente VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,? )");
                            ST_update.setInt(1, id);
                            ST_update.setString(2, cliente_nombre);
                            ST_update.setString(3, direccion);
                            ST_update.setString(4, telefono);
                            ST_update.setString(5, ruc);
                            ST_update.setString(6, email);
                            ST_update.setDate(7, null);
                            ST_update.setInt(8, 0);
                            ST_update.setInt(9, 0); //borrado
                            ST_update.setString(10, "");
                            ST_update.setString(11, "");
                            ST_update.setInt(12, id_ciudad);
                            ST_update.setString(13, encargado);
                            ST_update.setString(14, horario_lunavier);
                            ST_update.setString(15, horario_sabado);
                            ST_update.setInt(16, paginas);
                            ST_update.setInt(17, id_rubro);
                            ST_update.setString(18, sugerencia);
                            ST_update.setString(19, preferencia);
                            ST_update.setString(20, marca);
                            ST_update.setLong(21, Long.parseLong(mensual.replace(".", "")));
                            ST_update.setLong(22, Long.parseLong(marandu.replace(".", "")));
                            ST_update.setLong(23, Long.parseLong(ideas.replace(".", "")));
                            ST_update.setDate(24, util_Date_to_sql_date(vencimiento));
                            ST_update.executeUpdate();
                        }

                        JOptionPane.showMessageDialog(null, "Guardado correctamente");

                    }
                }
            } else {
                boolean ruc_existe = Metodos.Clientes_buscar_por_ruc_y_id(jTextField_ruc.getText());

                if (ruc_existe == false) {

                    if (vencimiento == null) {

                        PreparedStatement stClienteBorrar3 = conexion.prepareStatement(""
                                + "UPDATE cliente SET nombre='" + cliente_nombre + "', "
                                + "direccion = '" + direccion + "', "
                                + "telefono = '" + telefono + "', "
                                + "ruc = '" + ruc + "', "
                                + "email = '" + email + "', "
                                + "id_ciudad = '" + id_ciudad + "', "
                                + "encargado = '" + encargado + "', "
                                + "horario_atencion_lunes_a_viernes = '" + horario_lunavier + "', "
                                + "horario_de_atencion_sabado = '" + horario_sabado + "', "
                                + "paginas = '" + paginas + "', "
                                + "id_rubro = '" + id_rubro + "', "
                                + "sugerencias = '" + sugerencia + "', "
                                + "preferencias = '" + preferencia + "', "
                                + "mensual = '" + Long.parseLong(mensual.replace(".", "")) + "', "
                                + "ideas = '" + Long.parseLong(ideas.replace(".", "")) + "', "
                                + "marandu = '" + Long.parseLong(marandu.replace(".", "")) + "', "
                                + "marcas = '" + marca + "' "
                                + "WHERE id_cliente  ='" + id_cliente + "' ");
                        stClienteBorrar3.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Guardado correctamente");
                    } else {

                        PreparedStatement stClienteBorrar3 = conexion.prepareStatement(""
                                + "UPDATE cliente SET nombre='" + cliente_nombre + "', "
                                + "direccion = '" + direccion + "', "
                                + "telefono = '" + telefono + "', "
                                + "ruc = '" + ruc + "', "
                                + "email = '" + email + "', "
                                + "id_ciudad = '" + id_ciudad + "', "
                                + "encargado = '" + encargado + "', "
                                + "horario_atencion_lunes_a_viernes = '" + horario_lunavier + "', "
                                + "horario_de_atencion_sabado = '" + horario_sabado + "', "
                                + "paginas = '" + paginas + "', "
                                + "id_rubro = '" + id_rubro + "', "
                                + "sugerencias = '" + sugerencia + "', "
                                + "preferencias = '" + preferencia + "', "
                                + "mensual = '" + Long.parseLong(mensual.replace(".", "")) + "', "
                                + "ideas = '" + Long.parseLong(ideas.replace(".", "")) + "', "
                                + "marandu = '" + Long.parseLong(marandu.replace(".", "")) + "', "
                                + "vencimiento = '" + util_Date_to_sql_date(vencimiento) + "', "
                                + "marcas = '" + marca + "' "
                                + "WHERE id_cliente  ='" + id_cliente + "' ");
                        stClienteBorrar3.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Guardado correctamente");

                    }
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static boolean Clientes_buscar_por_ruc(String ruc) {

        boolean dato = false;
        try {

            if (ruc.length() > 0) {
                ruc = ruc.trim();
                Statement st1 = conexion.createStatement();
                ResultSet result = st1.executeQuery("SELECT * FROM cliente where ruc = '" + ruc + "'");
                if (result.next()) {
                    dato = true;
                    JOptionPane.showMessageDialog(null, "RUC registrado a nombre de: " + result.getString("nombre").trim());
                }
            } else {
                System.err.println("RUC vacio");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return dato;
    }

    public synchronized static boolean Clientes_buscar_por_ruc_y_id(String ruc) {

        boolean dato = false;
        try {

            if (ruc.length() > 0) {
                ruc = ruc.trim();
                Statement st1 = conexion.createStatement();
                ResultSet result = st1.executeQuery("SELECT * FROM cliente where ruc = '" + ruc + "' and id_cliente != '" + id_cliente + "'");
                if (result.next()) {
                    dato = true;
                    JOptionPane.showMessageDialog(null, "RUC registrado a nombre de: " + result.getString("nombre").trim());
                }
            } else {
                System.err.println("RUC vacio");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return dato;
    }

    public synchronized static String getSepararMiles(String txtprec) {
        String valor = txtprec;

        int largo = valor.length();
        if (largo > 8) {
            valor = valor.substring(largo - 9, largo - 6) + "." + valor.substring(largo - 6, largo - 3) + "." + valor.substring(largo - 3, largo);
        } else if (largo > 7) {
            valor = valor.substring(largo - 8, largo - 6) + "." + valor.substring(largo - 6, largo - 3) + "." + valor.substring(largo - 3, largo);
        } else if (largo > 6) {
            valor = valor.substring(largo - 7, largo - 6) + "." + valor.substring(largo - 6, largo - 3) + "." + valor.substring(largo - 3, largo);
        } else if (largo > 5) {
            valor = valor.substring(largo - 6, largo - 3) + "." + valor.substring(largo - 3, largo);
        } else if (largo > 4) {
            valor = valor.substring(largo - 5, largo - 3) + "." + valor.substring(largo - 3, largo);
        } else if (largo > 3) {
            valor = valor.substring(largo - 4, largo - 3) + "." + valor.substring(largo - 3, largo);
        }
        txtprec = valor;
        return valor;
    }

    public synchronized static void Ciudad_seleccionar() {
        DefaultTableModel tm = (DefaultTableModel) Clientes_Ciudad.jTable1.getModel();
        id_ciudad = Integer.parseInt(String.valueOf(tm.getValueAt(Clientes_Ciudad.jTable1.getSelectedRow(), 0)));
        Clientes_ABM.jTextField_ciudad.setText(String.valueOf(tm.getValueAt(Clientes_Ciudad.jTable1.getSelectedRow(), 1)));
    }

    public synchronized static void Clientes_seleccionar() {
        DefaultTableModel tm = (DefaultTableModel) Clientes.jTable1.getModel();
        id_cliente = Integer.parseInt(String.valueOf(tm.getValueAt(Clientes.jTable1.getSelectedRow(), 0)));
        Clientes_llevar_datos_a_editar();
        Cliente_producto_cargar_jtable();
        Cliente_colores_cargar_jtable();
        Cliente_trabajos_cargar_jtable();

    }

    public synchronized static void Pagos_Clientes_seleccionar() {
        DefaultTableModel tm = (DefaultTableModel) Pagos_clientes.jTable1.getModel();
        id_cliente = Integer.parseInt(String.valueOf(tm.getValueAt(Pagos_clientes.jTable1.getSelectedRow(), 0)));
        Pagos_ABM.jTextField_cliente.setText(String.valueOf(tm.getValueAt(Pagos_clientes.jTable1.getSelectedRow(), 1)));
        Pagos_ABM.jTextField_monto.setText(getSepararMiles(String.valueOf(tm.getValueAt(Pagos_clientes.jTable1.getSelectedRow(), 4))));
    }

    public synchronized static void Proveedor_seleccionar() {
        DefaultTableModel tm = (DefaultTableModel) Obligaciones_proveedor.jTable1.getModel();
        id_proveedor = Integer.parseInt(String.valueOf(tm.getValueAt(Obligaciones_proveedor.jTable1.getSelectedRow(), 0)));
        Obligaciones_ABM.jTextField_proveedor.setText(String.valueOf(tm.getValueAt(Obligaciones_proveedor.jTable1.getSelectedRow(), 1)));
    }

    public synchronized static void Facebook_clientes_seleccionar() {
        DefaultTableModel tm = (DefaultTableModel) Facebook_publicaciones_clientes.jTable1.getModel();
        id_cliente = Integer.parseInt(String.valueOf(tm.getValueAt(Facebook_publicaciones_clientes.jTable1.getSelectedRow(), 0)));
        Facebook_publicaciones.jTextField_cliente.setText(String.valueOf(tm.getValueAt(Facebook_publicaciones_clientes.jTable1.getSelectedRow(), 1)));
    }

    public synchronized static void Rubro_seleccionar() {
        DefaultTableModel tm = (DefaultTableModel) Cliente_Rubro.jTable1.getModel();
        id_rubro = Integer.parseInt(String.valueOf(tm.getValueAt(Cliente_Rubro.jTable1.getSelectedRow(), 0)));
        Clientes_ABM.jTextField_rubro.setText(String.valueOf(tm.getValueAt(Cliente_Rubro.jTable1.getSelectedRow(), 1)));
    }

    public synchronized static void Ciudad_cargar_jtable(String buscar) {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT id_ciudad, ciudad "
                    + "from ciudad "
                    + "where id_ciudad > '0' "
                    + "and ciudad ilike '%" + buscar + "%' "
                    + "order by ciudad");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Clientes_Ciudad.jTable1.getModel();
            for (int j = 0; j < Clientes_Ciudad.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] = rs.getObject(i + 1).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Clientes_Ciudad.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Pagos_cargar_jtable() {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT cliente.nombre, fecha, mes, monto "
                    + "from pago "
                    + "inner join cliente on cliente.id_cliente = pago.id_cliente "
                    + "where estado ilike  '%PENDIENTE%' "
                    + " order by mes ");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Avisos.jTable1.getModel();
            for (int j = 0; j < Avisos.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[0] = rs.getObject(1).toString().trim();
                    rows[1] = rs.getObject(2).toString();
                    rows[2] = rs.getObject(3).toString().trim();
                    rows[3] = getSepararMiles(rs.getObject(4).toString());
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Avisos.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Vencimiento_cargar_jtable(String fecha) {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT nombre, vencimiento "
                    + "from cliente "
                    + "where vencimiento <= '" + fecha + "'");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Avisos.jTable_vencimiento.getModel();
            for (int j = 0; j < Avisos.jTable_vencimiento.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[0] = rs.getObject(1).toString().trim();
                    rows[1] = rs.getObject(2).toString();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Avisos.jTable_vencimiento.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Obligaciones_cargar_jtable(String buscar) {
        try {

            String total = "0";
            Obligaciones.jTextField_total.setText(total);

            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery(""
                    + "SELECT SUM(monto) "
                    + "from obligacion "
                    + "inner join proveedor on proveedor.id_proveedor = obligacion.id_proveedor "
                    + "where mes ilike '%" + buscar + "%' and periodo = '" + periodo + "' "
                    + " ");
            if (result.next()) {
                if (result.getString(1) == null) {
                } else if ((result.getString(1).length() > 0) && (isNumeric(result.getString(1)))) {
                    total = getSepararMiles(result.getString(1));
                    Obligaciones.jTextField_total.setText(total);
                }
            }

            ps = conexion.prepareStatement(""
                    + "SELECT id_obligacion, nombre, mes, monto, estado "
                    + "from obligacion "
                    + "inner join proveedor on proveedor.id_proveedor = obligacion.id_proveedor "
                    + "where mes ilike '%" + buscar + "%' and periodo = '" + periodo + "' "
                    + "order by id_obligacion ");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Obligaciones.jTable1.getModel();
            for (int j = 0; j < Obligaciones.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[0] = rs.getObject(1);
                    rows[1] = rs.getObject(2).toString().trim();
                    rows[2] = rs.getObject(3).toString().trim();
                    rows[3] = getSepararMiles(rs.getObject(4).toString().trim());
                    rows[4] = rs.getObject(5).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Obligaciones.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Balance_Obligaciones_cargar_jtable(String buscar) {
        try {

            String total = "0";
            Balance.jTextField_total_obligaciones.setText(total);

            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery(""
                    + "SELECT SUM(monto) "
                    + "from obligacion "
                    + "inner join proveedor on proveedor.id_proveedor = obligacion.id_proveedor "
                    + "where mes ilike '%" + buscar + "%' and periodo = '" + periodo + "' "
                    + " ");
            if (result.next()) {
                if (result.getString(1) == null) {
                } else if ((result.getString(1).length() > 0) && (isNumeric(result.getString(1)))) {
                    total_obligaciones = result.getLong(1);
                    total = getSepararMiles(result.getString(1));
                    Balance.jTextField_total_obligaciones.setText(total);
                }
            }

            ps = conexion.prepareStatement(""
                    + "SELECT nombre, monto "
                    + "from obligacion "
                    + "inner join proveedor on proveedor.id_proveedor = obligacion.id_proveedor "
                    + "where mes ilike '%" + buscar + "%' and periodo = '" + periodo + "' "
                    + "order by id_obligacion ");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Balance.jTable_obligaciones.getModel();
            for (int j = 0; j < Balance.jTable_obligaciones.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[0] = rs.getObject(1).toString().trim();
                    rows[1] = getSepararMiles(rs.getObject(2).toString().trim());
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Balance.jTable_obligaciones.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Obligaciones_cargar_jtable_pendientes() {
        try {

            String total = "0";
            Obligaciones.jTextField_total.setText(total);

            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery(""
                    + "SELECT SUM(monto) "
                    + "from obligacion "
                    + "inner join proveedor on proveedor.id_proveedor = obligacion.id_proveedor "
                    + "where estado ilike '%PENDIENTE%' and periodo = '" + periodo + "' "
                    + " ");
            if (result.next()) {
                if (result.getString(1) == null) {
                } else if ((result.getString(1).length() > 0) && (isNumeric(result.getString(1)))) {
                    total = getSepararMiles(result.getString(1));
                    Obligaciones.jTextField_total.setText(total);
                }
            }

            ps = conexion.prepareStatement(""
                    + "SELECT id_obligacion, nombre, mes, monto, estado "
                    + "from obligacion "
                    + "inner join proveedor on proveedor.id_proveedor = obligacion.id_proveedor "
                    + "where estado ilike '%PENDIENTE%' and periodo = '" + periodo + "' "
                    + "order by id_obligacion ");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Obligaciones.jTable1.getModel();
            for (int j = 0; j < Obligaciones.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[0] = rs.getObject(1);
                    rows[1] = rs.getObject(2).toString().trim();
                    rows[2] = rs.getObject(3).toString().trim();
                    rows[3] = getSepararMiles(rs.getObject(4).toString().trim());
                    rows[4] = rs.getObject(5).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Obligaciones.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Proveedor_cargar_jtable(String buscar) {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT id_proveedor, proveedor "
                    + "from proveedor "
                    + "where id_proveedor > '0' "
                    + "and proveedor ilike '%" + buscar + "%' "
                    + "order by proveedor ");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Proveedor.jTable1.getModel();
            for (int j = 0; j < Proveedor.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] = rs.getObject(i + 1).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Proveedor.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Facebook_publicaciones_cargar_jtable(String buscar) {
        try {

            dtm = (DefaultTableModel) Facebook.jTable1.getModel();
            for (int j = 0; j < Facebook.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }

            String total = "0";
            Facebook.jTextField_total.setText(total);

            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT SUM(monto)"
                    + "from cliente "
                    + "inner join facebook on facebook.id_cliente = cliente.id_cliente "
                    + "where nombre ilike '%" + buscar + "%' "
                    + "and cliente.borrado != '1' and facebook.borrado != '1'");
            if (result.next()) {

                if (result.getString(1) == null) {

                } else if ((result.getString(1).length() > 0) && (isNumeric(result.getString(1)))) {
                    total = getSepararMiles(result.getString(1));
                    Facebook.jTextField_total.setText(total);
                }
            }

            ps = conexion.prepareStatement(""
                    + "SELECT id_facebook, nombre, publicacion, fecha, monto, pagado "
                    + "from cliente "
                    + "inner join facebook on facebook.id_cliente = cliente.id_cliente "
                    + "where nombre ilike '%" + buscar + "%' "
                    + "and cliente.borrado != '1' and facebook.borrado != '1' "
                    + "order by nombre");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();

            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[0] = rs.getObject(1);
                    rows[1] = rs.getObject(2).toString().trim();
                    rows[2] = rs.getObject(3).toString().trim();
                    rows[4] = rs.getObject(4).toString().trim();
                    rows[3] = getSepararMiles(rs.getObject(5).toString());
                    rows[5] = rs.getObject(6).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Facebook.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Pagos_cargar_jtable(String buscar) {
        try {

            dtm = (DefaultTableModel) Pagos.jTable1.getModel();
            for (int j = 0; j < Pagos.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }

            String total = "0";
            Pagos.jTextField_total.setText(total);

            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT SUM(monto)"
                    + "from cliente "
                    + "inner join pago on pago.id_cliente = cliente.id_cliente "
                    + "where nombre ilike '%" + buscar + "%' "
                    + "and cliente.borrado != '1' "
                    + "and pago.borrado != '1' "
                    + "and periodo = '" + periodo + "'");
            if (result.next()) {

                if (result.getString(1) == null) {

                } else if ((result.getString(1).length() > 0) && (isNumeric(result.getString(1)))) {
                    total = getSepararMiles(result.getString(1));
                    Pagos.jTextField_total.setText(total);
                }
            }

            ps = conexion.prepareStatement(""
                    + "SELECT id_pago, nombre, mes, monto, estado "
                    + "from cliente "
                    + "inner join pago on pago.id_cliente = cliente.id_cliente "
                    + "where nombre ilike '%" + buscar + "%' "
                    + "and cliente.borrado != '1' and pago.borrado != '1' and periodo = '" + periodo + "' "
                    + "order by nombre");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();

            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[0] = rs.getObject(1);
                    rows[1] = rs.getObject(2).toString().trim();
                    rows[2] = rs.getObject(3).toString().trim();
                    rows[3] = getSepararMiles(rs.getObject(4).toString());
                    rows[4] = rs.getObject(5).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Pagos.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Balance_Pagos_cargar_jtable(String buscar) {
        try {
            dtm = (DefaultTableModel) Balance.jTable_pagos.getModel();
            for (int j = 0; j < Balance.jTable_pagos.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }

            String total = "0";
            Balance.jTextField_pagos.setText(total);

            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT SUM(monto)"
                    + "from cliente "
                    + "inner join pago on pago.id_cliente = cliente.id_cliente "
                    + "where mes ilike '%" + buscar + "%' "
                    + "and estado ilike '%PENDIENTE%'"
                    + "and cliente.borrado != '1' and pago.borrado != '1' and periodo = '" + periodo + "'");
            if (result.next()) {
                if (result.getString(1) == null) {
                } else if ((result.getString(1).length() > 0) && (isNumeric(result.getString(1)))) {
                    total_pagos = result.getLong(1);
                    total = getSepararMiles(result.getString(1));
                    Balance.jTextField_pagos.setText(total);
                }
            }

            ps = conexion.prepareStatement(""
                    + "SELECT nombre, monto "
                    + "from cliente "
                    + "inner join pago on pago.id_cliente = cliente.id_cliente "
                    + "where mes ilike '%" + buscar + "%' "
                    + "and estado ilike '%PENDIENTE%'"
                    + "and cliente.borrado != '1' and pago.borrado != '1' and periodo = '" + periodo + "' "
                    + "order by nombre");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[0] = rs.getObject(1).toString().trim();
                    rows[1] = getSepararMiles(rs.getObject(2).toString());
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Balance.jTable_pagos.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

            Balance.jTextField_total.setText(getSepararMiles(String.valueOf(total_pagos - total_obligaciones)));
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Cliente_producto_cargar_jtable() {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT id_cliente_producto, cliente_producto "
                    + "from cliente_producto "
                    + "where id_cliente_producto > '0' "
                    + "and id_cliente = '" + id_cliente + "' "
                    + "order by cliente_producto");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Clientes_ABM.jTable_productos.getModel();
            for (int j = 0; j < Clientes_ABM.jTable_productos.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] = rs.getObject(i + 1).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Clientes_ABM.jTable_productos.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Cliente_colores_cargar_jtable() {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT id_cliente_colores, cliente_colores "
                    + "from cliente_colores "
                    + "where id_cliente_colores > '0' "
                    + "and id_cliente = '" + id_cliente + "' "
                    + "order by cliente_colores");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Clientes_ABM.jTable_color.getModel();
            for (int j = 0; j < Clientes_ABM.jTable_color.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] = rs.getObject(i + 1).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Clientes_ABM.jTable_color.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Cliente_trabajos_cargar_jtable() {
        try {

            dtm = (DefaultTableModel) Clientes_ABM.jTable_trabajo_extra.getModel();
            for (int j = 0; j < Clientes_ABM.jTable_trabajo_extra.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ps = conexion.prepareStatement(""
                    + "SELECT id_cliente_trabajo_extra, trabajo, monto "
                    + "from cliente_trabajo_extra "
                    + "where id_cliente = '" + id_cliente + "' "
                    + "order by id_cliente_trabajo_extra DESC");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[0] = rs.getObject(1);
                    rows[1] = rs.getObject(2).toString().trim();
                    rows[2] = getSepararMiles(rs.getObject(3).toString());
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Clientes_ABM.jTable_trabajo_extra.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Clientes_cargar_jtable(String buscar) {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT id_cliente, nombre, ruc, telefono "
                    + "from cliente "
                    + "where id_cliente > '0' "
                    + "and nombre ilike '%" + buscar + "%' "
                    + "order by nombre");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Clientes.jTable1.getModel();
            for (int j = 0; j < Clientes.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] = rs.getObject(i + 1).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Clientes.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Pagos_Clientes_cargar_jtable(String buscar) {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT id_cliente, nombre, ruc, telefono, mensual "
                    + "from cliente "
                    + "where id_cliente > '0' "
                    + "and nombre ilike '%" + buscar + "%' "
                    + "order by nombre");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Pagos_clientes.jTable1.getModel();
            for (int j = 0; j < Pagos_clientes.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] = rs.getObject(i + 1).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Pagos_clientes.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Obligaciones_Proveedor_cargar_jtable(String buscar) {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT id_proveedor, nombre, ruc, telefono "
                    + "from proveedor "
                    + "where id_proveedor > '0' "
                    + "and nombre ilike '%" + buscar + "%' "
                    + "order by nombre");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Obligaciones_proveedor.jTable1.getModel();
            for (int j = 0; j < Obligaciones_proveedor.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] = rs.getObject(i + 1).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Obligaciones_proveedor.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Facebook_clientes_cargar_jtable(String buscar) {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT id_cliente, nombre, ruc, telefono "
                    + "from cliente "
                    + "where id_cliente > '0' "
                    + "and nombre ilike '%" + buscar + "%' "
                    + "order by nombre");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Facebook_publicaciones_clientes.jTable1.getModel();
            for (int j = 0; j < Facebook_publicaciones_clientes.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] = rs.getObject(i + 1).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Facebook_publicaciones_clientes.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static void Rubro_cargar_jtable(String buscar) {
        try {

            ps = conexion.prepareStatement(""
                    + "SELECT id_rubro, rubro "
                    + "from rubro "
                    + "where id_rubro > '0' "
                    + "and rubro ilike '%" + buscar + "%' "
                    + "order by rubro");
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            dtm = (DefaultTableModel) Cliente_Rubro.jTable1.getModel();
            for (int j = 0; j < Cliente_Rubro.jTable1.getRowCount(); j++) {
                dtm.removeRow(j);
                j -= 1;
            }
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] rows = new Object[rsm.getColumnCount()];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] = rs.getObject(i + 1).toString().trim();
                }
                data.add(rows);
            }
            dtm = (DefaultTableModel) Cliente_Rubro.jTable1.getModel();
            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(data.get(i));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public synchronized static boolean getIngresar() {

        boolean entro = false;
        try {
            nombre = jTextField1.getText();
            char[] arrayC = jPasswordField1.getPassword();
            String pass = new String(arrayC);
//            PreparedStatement ps = conexion.prepareStatement("select * from usuario where nombre ='" + nombre + "' and contrasenha = '" + pass + "'");
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {

            PreparedStatement ps2 = conexion.prepareStatement("select * from configuracion");
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                empresa = rs2.getString("empresa").trim();
                periodo = rs2.getInt("periodo");
            }
            ubicacion_proyecto = new File("").getAbsolutePath();
//                nombre = rs.getString("nombre_real").trim();
//                id_usuario = rs.getInt("id_usuario");
            titulo = empresa + " - " + nombre + " - Periodo activo: " + String.valueOf(periodo);
            entro = true;

            new Principal().setVisible(true);

//            }
            if (entro == false) {
                //new Logueo().setVisible(true);
                JOptionPane.showMessageDialog(null, "Error de usuario y/o contraseña.");
                Logueo.jTextField1.requestFocus();
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return entro;
    }

    public synchronized static String getHoy_format3() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String hoy = ft.format(dNow);
        return hoy;
    }

    public synchronized static String getHoy_format_yyyy_mm_dd(Date dNow) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String hoy = ft.format(dNow);
        return hoy;
    }

    public synchronized static String getHoy_format_dd_mm_yyyy() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        String hoy = ft.format(dNow);
        return hoy;
    }

    public static void Iniciar_Conexion() {
        try {
            InetAddress ip;
            ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }

            String db = null;
            String host = null;
            String user = null;
            String pass = null;
            String mac_adress = null;
            String mac_adress2 = null;

            db = "ideas";
            host = "localhost";
            user = "postgres";
            pass = "postgres";
            mac_adress = "00-E0-4C-80-14-91";
            mac_adress2 = "";

            if ((sb.toString().equals(mac_adress)) || (sb.toString().equals(mac_adress2))) {
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection("jdbc:postgresql://" + host + ":5432/" + db, user, pass);
                //       Logueo.jLabel2.setVisible(false);
                //  Logueo.jTextField1.setEditable(true);
                //    Logueo.jPasswordField1.setEditable(true);
            } else {
                JOptionPane.showMessageDialog(null, "PC no registrada (" + sb.toString() + ")");
                System.exit(-1);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la conexion con la base de datos." + ex);
            System.exit(-1);
        } catch (ClassNotFoundException | UnknownHostException | SocketException ex) {
            System.err.println(ex);
        }
    }

    public static void Cerrar_Conexion() {
        try {
            conexion.close();
            System.err.println("Conexion finalizada");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public static void Verificar_conexion() {
        try {
            if (conexion.isClosed() == false) {
                System.err.println("conexion OK");
            } else {
                System.err.println("conexion FAIL");
                Iniciar_Conexion();
                if (conexion.isClosed() == false) {
                    System.err.println("conexion reiniciada; Conexion OK");
                } else {
                    JOptionPane.showMessageDialog(null, "Error de conexion con la base de datos.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

}
