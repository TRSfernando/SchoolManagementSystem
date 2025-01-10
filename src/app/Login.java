package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import db.DatabaseHandler;

import java.sql.*;

public class Login {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private MainDashboard mainDashboard;

	public void show() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("School Management System: Login");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("School Management System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 36));
		lblNewLabel.setBounds(10, 29, 914, 84);
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(249, 100, 436, 10);
		frame.getContentPane().add(separator);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(228, 174, 478, 288);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 11, 458, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(80, 89, 98, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password:");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(80, 149, 98, 31);
		panel.add(lblNewLabel_2_1);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		usernameField.setBounds(177, 89, 220, 31);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(177, 149, 157, 31);
		panel.add(passwordField);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameField.setText(null);
				passwordField.setText(null);
			}
		});
		clearButton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		clearButton.setBounds(80, 224, 108, 30);
		panel.add(clearButton);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				String name = isValidUser(username, password);
				if (name != null) {
					mainDashboard = new MainDashboard(name);
					mainDashboard.show();
					System.out.println("Login created MainDashboard.");
					frame.setVisible(false);
				}
			}
		});
		loginButton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		loginButton.setBounds(289, 224, 108, 30);
		panel.add(loginButton);
		
		JCheckBox showPasswordChkBox = new JCheckBox("Show");
		showPasswordChkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showPasswordChkBox.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar('●');
				}
			}
		});
		showPasswordChkBox.setForeground(Color.LIGHT_GRAY);
		showPasswordChkBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		showPasswordChkBox.setBackground(Color.DARK_GRAY);
		showPasswordChkBox.setBounds(340, 151, 57, 31);
		panel.add(showPasswordChkBox);
		
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private String isValidUser(String username, String password) {
		Connection connection = DatabaseHandler.getConnection();
		
		if (connection == null) {
			JOptionPane.showMessageDialog(frame, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		try {
			String query = "select name from Users where username = ? and password = ?";
			
			// Prepared statements are used to prevent SQL Injection attacks
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			// ResultSet is used to store an SQL Query Output
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				return name;
			} else {
				JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Failed", JOptionPane.WARNING_MESSAGE);
			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(frame, "An error occurred during login: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return null;
	}
}
