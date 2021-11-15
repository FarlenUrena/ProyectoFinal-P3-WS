/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.util;

import cr.ac.una.wsrestuna.dto.ReporteDto;
import cr.ac.una.wsrestuna.wsrestuna;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Farlen
 */
public class Reporte {
    public ReporteDto generarReportes(Map<String,Object> datos) throws FileNotFoundException, /*JRException,*/ SQLException,   JRException,   NamingException{
    
    String url = (String) datos.get("url");
    
    InputStream x = wsrestuna.class.getClassLoader().getResourceAsStream("cr/ac/una/wsrestuna/Reports/"+url+".jrxml");
    
    InitialContext context = new InitialContext();   
    DataSource datasource = (DataSource) context.lookup("jdbc/RestUNA");
    Connection c =   datasource.getConnection();
    System.out.println();
    JasperReport compileReport = JasperCompileManager.compileReport(x);
    
    
    
    
//    InputStream x = relojunaws.class.getClassLoader().getResourceAsStream("url");
//    /* TODO
//    Conexion a base de datos
//      ...
//            */
//    JasperReport compileReport = JasperCompileManager.compileReport(x);
    
    
    
    
    
    
    
    
//    HashMap<String,Object> map = new HashMap<>();    
    JasperPrint report = JasperFillManager.fillReport(compileReport, datos,/*new JREmptyDataSource()*/c);
//    JasperExportManager.exportReportToPdfFile(report,"prueba.pdf");
//        byte[] xd = JasperExportManager.exportReportToPdf(report);
    
//        JasperExportManager.
//     StringBuffer sbuffer = new StringBuffer();
//  JRXmlExporter exporter = new JRXmlExporter();
//  
//  exporter..setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//  exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
//  
//  exporter.exportReport();
  
//  return xd;
    
//    byte [] data = JasperExportManager.exportReportToPdf(report);

//      em.getTransaction().commit();
        byte[] xd = JasperExportManager.exportReportToPdf(report);
ReporteDto rep = new ReporteDto();
     rep.setPrintReport(xd);
     return rep;
//    report;
    }
}
