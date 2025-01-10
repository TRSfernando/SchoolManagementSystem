package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.sql.Connection;
import db.DatabaseHandler;
import reporting.JasperReportsHandler;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class ReportWindow {

	private JFrame frmSchoolManagementSystem;

	/**
	 * Create the application.
	 */
	public ReportWindow() {
		initialize();
	}

	public void show() {
		frmSchoolManagementSystem.setVisible(true);
		frmSchoolManagementSystem.setLocationRelativeTo(null);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSchoolManagementSystem = new JFrame();
		frmSchoolManagementSystem.setResizable(false);
		frmSchoolManagementSystem.setTitle("School Management System: Report Window");
		frmSchoolManagementSystem.setBounds(100, 100, 450, 264);
		frmSchoolManagementSystem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSchoolManagementSystem.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 434, 225);
		frmSchoolManagementSystem.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("View Reports");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 11, 414, 42);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Class Evaluation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewClassEvaluationReport();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnNewButton.setBounds(10, 106, 195, 40);
		panel.add(btnNewButton);
		
		JButton btnStudentPerformanceReport = new JButton("Student Performance");
		btnStudentPerformanceReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(System.getProperty("java.class.path"));
				viewStudentPerformanceReport();
				

			}
		});
		btnStudentPerformanceReport.setForeground(Color.WHITE);
		btnStudentPerformanceReport.setBackground(Color.DARK_GRAY);
		btnStudentPerformanceReport.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnStudentPerformanceReport.setBounds(229, 106, 195, 40);
		panel.add(btnStudentPerformanceReport);
	}
	
	private void viewClassEvaluationReport() {
		InputStream jrxmlFilePath = getClass().getClassLoader().getResourceAsStream("reporting/ClassEvaluationReport.jrxml");
		//String jrxmlFilePath = "src/reporting/ClassEvaluationReport.jrxml";
		try {
			Connection connection = DatabaseHandler.getConnection();
			JasperReportsHandler.generateReport(jrxmlFilePath, connection);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "An Error occured: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void viewStudentPerformanceReport() {
		InputStream jrxmlFilePath = getClass().getClassLoader().getResourceAsStream("reporting/StudentPerformanceReport.jrxml");
		//String jrxmlFilePath = "src/reporting/StudentPerformanceReport.jrxml";
		try {
			Connection connection = DatabaseHandler.getConnection();
			JasperReportsHandler.generateReport(jrxmlFilePath, connection);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "An Error occured: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
