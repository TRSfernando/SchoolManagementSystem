package reporting;

import java.io.InputStream;
import java.sql.Connection;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportsHandler {
	public static void generateReport(InputStream filePath, Connection connection) {
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
			JasperViewer.viewReport(jasperPrint, false);
			
		} catch(JRException e) {
			JOptionPane.showMessageDialog(null, "An Error occured while generating a report: " + e.getMessage(), "JasperReports Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An Error occured has occred: " + e.getMessage(), "JasperReports Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
