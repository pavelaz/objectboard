
package aplicacionmensajes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Dguerrero
 */
public class proceReport implements Runnable {

    @Override
    public void run() {
        
        try {
         Map objeto = new HashMap();
             
            Class.forName("com.mysql.jdbc.Driver");
             Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/reportes", "root", "");

             JasperReport report = JasperCompileManager.compileReport("D:/Almacenamiento D/SENA 4to trimestre/JAVA/proyectos/AplicacionMensajes/src/aplicacionmensajes/reporte.jrxml");
           
            
             JasperPrint print = JasperFillManager.fillReport(report, objeto, conexion);

             JasperViewer jviewer= new JasperViewer(print, false);
            jviewer.setTitle("Reporte de Usuarios que envian mensajes");
            jviewer.setVisible(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
   
     public void start(){
        new Thread(this).start(); 
    }   
}
