package DEV;

import ideas.Logueo;
import static ideas.Logueo.jPasswordField1;
import static ideas.Logueo.jTextField1;
import ideas.Principal;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

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

    public synchronized static boolean getIngresar() {

        boolean entro = false;
        try {
            nombre = jTextField1.getText();
            char[] arrayC = jPasswordField1.getPassword();
            String pass = new String(arrayC);
            
            new Principal().setVisible(true);

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
