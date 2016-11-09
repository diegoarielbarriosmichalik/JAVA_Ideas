package DEV;

import FORMS.Ciudad;
import FORMS.Clientes;
import FORMS.Clientes_ABM;
import static FORMS.Clientes_ABM.jTextField_ruc;
import FORMS.Facebook;
import FORMS.Facebook_publicaciones;
import FORMS.Facebook_publicaciones_clientes;
import FORMS.Logueo;
import static FORMS.Logueo.jPasswordField1;
import static FORMS.Logueo.jTextField1;
import FORMS.Obligaciones;
import FORMS.Obligaciones_ABM;
import FORMS.Obligaciones_proveedor;
import FORMS.Principal;
import FORMS.Proveedor;
import FORMS.Rubro;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Metodos {

    public static Connection conexion = null;

    public synchronized static String getHoy_format2() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String hoy = ft.format(dNow);
        return hoy;
    }

    public static String nombre = null;
    public static String titulo = "IDEAS Mkt ";
    public static String ubicacion_proyecto = null;
    public static int id_usuario = 0;
    public static int id = 0;
    public static int id_ciudad = 0;
    public static int id_cliente = 0;
    public static int id_proveedor = 0;
    public static int id_rubro = 0;
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static ResultSetMetaData rsm;
    public static DefaultTableModel dtm;

    public synchronized static void Ciudad_guardar(String ciudad) {
        try {
            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery("SELECT MAX(id_ciudad) FROM ciudad");
            if (result.next()) {
                id = result.getInt(1) + 1;
            }

            PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO ciudad VALUES(?,?)");
            ST_update.setInt(1, id);
            ST_update.setString(2, ciudad);
            ST_update.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex);
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

            PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO obligacion VALUES(?,?,?,?,?)");
            ST_update.setInt(1, id);
            ST_update.setInt(2, id_proveedor);
            ST_update.setString(3, mes);
            ST_update.setLong(4, Long.parseLong(monto.replace(".", "")));
            ST_update.setString(5, "PENDIENTE");
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
            String sugerencia, String preferencia, String marca, String mensual, String marandu, String ideas
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

                        PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO cliente VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,? )");
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

                        JOptionPane.showMessageDialog(null, "Guardado correctamente");

                    }
                }
            } else {
                boolean ruc_existe = Metodos.Clientes_buscar_por_ruc_y_id(jTextField_ruc.getText());

                if (ruc_existe == false) {
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
        DefaultTableModel tm = (DefaultTableModel) Ciudad.jTable1.getModel();
        id_ciudad = Integer.parseInt(String.valueOf(tm.getValueAt(Ciudad.jTable1.getSelectedRow(), 0)));
        Clientes_ABM.jTextField_ciudad.setText(String.valueOf(tm.getValueAt(Ciudad.jTable1.getSelectedRow(), 1)));
    }

    public synchronized static void Clientes_seleccionar() {
        DefaultTableModel tm = (DefaultTableModel) Clientes.jTable1.getModel();
        id_cliente = Integer.parseInt(String.valueOf(tm.getValueAt(Clientes.jTable1.getSelectedRow(), 0)));
        Clientes_llevar_datos_a_editar();
        Cliente_producto_cargar_jtable();
        Cliente_colores_cargar_jtable();
        Cliente_trabajos_cargar_jtable();

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
        DefaultTableModel tm = (DefaultTableModel) Rubro.jTable1.getModel();
        id_rubro = Integer.parseInt(String.valueOf(tm.getValueAt(Rubro.jTable1.getSelectedRow(), 0)));
        Clientes_ABM.jTextField_rubro.setText(String.valueOf(tm.getValueAt(Rubro.jTable1.getSelectedRow(), 1)));
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
            dtm = (DefaultTableModel) Ciudad.jTable1.getModel();
            for (int j = 0; j < Ciudad.jTable1.getRowCount(); j++) {
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
            dtm = (DefaultTableModel) Ciudad.jTable1.getModel();
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
                    + "where mes ilike '%" + buscar + "%' "
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
                    + "where mes ilike '%" + buscar + "%' "
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

    public synchronized static void Obligaciones_cargar_jtable_pendientes() {
        try {

            String total = "0";
            Obligaciones.jTextField_total.setText(total);

            Statement st1 = conexion.createStatement();
            ResultSet result = st1.executeQuery(""
                    + "SELECT SUM(monto) "
                    + "from obligacion "
                    + "inner join proveedor on proveedor.id_proveedor = obligacion.id_proveedor "
                    + "where estado ilike '%PENDIENTE%' "
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
                    + "where estado ilike '%PENDIENTE%' "
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
            dtm = (DefaultTableModel) Rubro.jTable1.getModel();
            for (int j = 0; j < Rubro.jTable1.getRowCount(); j++) {
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
            dtm = (DefaultTableModel) Rubro.jTable1.getModel();
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

            new Principal().setVisible(true);
            entro = true;

            PreparedStatement ps = conexion.prepareStatement("select * from usuario where nombre ='" + nombre + "' and contrasenha = '" + pass + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre_real").trim();
                id_usuario = rs.getInt("id_usuario");

                ubicacion_proyecto = new File("").getAbsolutePath();
                //                new Principal().setVisible(true);

                entro = true;
                String hoy = DEV.Metodos.getHoy_format3();

            }
            if (entro == false) {
                //new Logueo().setVisible(true);
                JOptionPane.showMessageDialog(null, "Error de usuario y/o contraseña.");
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

    public synchronized static String getHoy_format_yyyy_mm_dd() {
        Date dNow = new Date();
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
                Logueo.jLabel2.setVisible(false);
                Logueo.jTextField1.setEditable(true);
                Logueo.jPasswordField1.setEditable(true);
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
