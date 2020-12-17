package aplicacionmensajes;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Dguerrero
 */
public class guardadoDB {

    Connection conexion = null;

    String correo;
    String destinatario;
    String asunto;

    public guardadoDB(String valor1, String valor2, String valor3) {
        this.correo = valor1;
        this.destinatario = valor2;
        this.asunto = valor3;
        guardar();
    }

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/reportes", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexion: " + e);
        }
        return conexion;
    }

    public void guardar() {

        Connection cn1 = conectar();
        String sql = "select id from correos where correo =" + "'" + correo + "'";
        String registros = "";
        int bandera = 0;
        try {
            Statement st = cn1.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros = rs.getString("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error1" + e);
        }

        if (registros.equals("")) {
            bandera = 1;
        }

        if (bandera == 1) {
            String sql2 = "insert into correos(correo) values(?)";
            try {
                PreparedStatement pst = cn1.prepareStatement(sql2);
                pst.setString(1, correo);
                int n = pst.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error2" + e);
            }

            String sql3 = "select id from correos where correo =" + "'" + correo + "'";
            registros = "";
            try {
                Statement st = cn1.createStatement();
                ResultSet rs = st.executeQuery(sql3);
                while (rs.next()) {
                    registros = rs.getString("id");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error3" + e);
            }
            int idCorreo = Integer.parseInt(registros);

            sql2 = "";
            sql2 = "insert into mensajes(correo,destinatario,asunto) values(?,?,?)";
            try {
                PreparedStatement pst = cn1.prepareStatement(sql2);
                pst.setInt(1, idCorreo);
                pst.setString(2, destinatario);
                pst.setString(3, asunto);
                int n = pst.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error4" + e);
            }

        } else {
            int idCorreo = Integer.parseInt(registros);
            String sql4 = "";
            sql4 = "insert into mensajes(correo,destinatario,asunto) values(?,?,?)";
            try {
                PreparedStatement pst = cn1.prepareStatement(sql4);
                pst.setInt(1, idCorreo);
                pst.setString(2, destinatario);
                pst.setString(3, asunto);
                int n = pst.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error4" + e);
            }
        }
    }
}

