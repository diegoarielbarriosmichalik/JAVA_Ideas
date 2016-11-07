package DEV;

import FORMS.Ciudad;
import FORMS.Clientes;
import FORMS.Clientes_ABM;
import static FORMS.Clientes_ABM.jTextField_ruc;
import FORMS.Logueo;
import static FORMS.Logueo.jPasswordField1;
import static FORMS.Logueo.jTextField1;
import FORMS.Principal;
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
import javax.swing.JTextField;
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
    public static String titulo = null;
    public static String ubicacion_proyecto = null;
    public static int id_usuario = 0;
    public static int id = 0;
    public static int id_ciudad = 0;
    public static int id_cliente = 0;
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
                Clientes_ABM.jTextField_ciudad.setText(result.getString("ciudad").trim());
                Clientes_ABM.jTextField_rubro.setText(result.getString("rubro").trim());

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
            String sugerencia, String preferencia, String marca
    ) {
        try {

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
                    int paginas = 0;
                    if (isNumeric(paginas_str) == true) {
                        paginas = Integer.parseInt(paginas_str);
                    }

                    PreparedStatement ST_update = conexion.prepareStatement("INSERT INTO cliente VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)");
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
                    ST_update.executeUpdate();

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

    public synchronized static void Ciudad_seleccionar() {
        DefaultTableModel tm = (DefaultTableModel) Ciudad.jTable1.getModel();
        id_ciudad = Integer.parseInt(String.valueOf(tm.getValueAt(Ciudad.jTable1.getSelectedRow(), 0)));
        Clientes_ABM.jTextField_ciudad.setText(String.valueOf(tm.getValueAt(Ciudad.jTable1.getSelectedRow(), 1)));
    }

    public synchronized static void Clientes_seleccionar() {
        DefaultTableModel tm = (DefaultTableModel) Clientes.jTable1.getModel();
        id_cliente = Integer.parseInt(String.valueOf(tm.getValueAt(Clientes.jTable1.getSelectedRow(), 0)));

        Clientes_llevar_datos_a_editar();

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

    public synchronized static String getHoy_format1() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
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
