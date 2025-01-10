package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import db.DatabaseHandler;

public class SubjectsDashboard {

	private JFrame frame;
	private JTextField idForAddingSubjectField;
	private JTextField subjectNameTextField;
	private JTextField subjectIdText;
	private DefaultTableModel tableModel1;
	private JPanel panel_1;
	private int cachedNextSubjectId;

	/**
	 * Create the application.
	 */
	public SubjectsDashboard(String name) {
		initialize(name);
		createSubjectsTable();
		updateSubjectsTable();
	}
	
	public void show() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name) {
		frame = new JFrame("School Management System: Subject Dashboard");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 220, 561);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SubjectsDashboard.class.getResource("/resources/profile2.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 32, 200, 70);
		panel.add(lblNewLabel);
		
		JLabel greeting_subjects = new JLabel("Hello, " + name);
		greeting_subjects.setHorizontalAlignment(SwingConstants.CENTER);
		greeting_subjects.setForeground(Color.WHITE);
		greeting_subjects.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		greeting_subjects.setBounds(10, 104, 200, 39);
		panel.add(greeting_subjects);
		
		JButton teachersBtn_subjects = new JButton("Teachers");
		teachersBtn_subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeachersDashboard teachersDashboard = new TeachersDashboard(name);
				System.out.println("SubjectsDashboard created TeachersDashboard.");
				teachersDashboard.show();
				frame.setVisible(false);
			}
		});
		teachersBtn_subjects.setIcon(null);
		teachersBtn_subjects.setForeground(Color.WHITE);
		teachersBtn_subjects.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		teachersBtn_subjects.setBackground(Color.DARK_GRAY);
		teachersBtn_subjects.setBounds(10, 210, 200, 30);
		panel.add(teachersBtn_subjects);
		
		JButton studentsBtn_subjects = new JButton("Students");
		studentsBtn_subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentsDashboard studentsDashboard = new StudentsDashboard(name);
				System.out.println("SubjectsDashboard created StudentsDashboard.");
				studentsDashboard.show();
				frame.setVisible(false);
			}
		});
		studentsBtn_subjects.setIcon(null);
		studentsBtn_subjects.setForeground(Color.WHITE);
		studentsBtn_subjects.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentsBtn_subjects.setBackground(Color.DARK_GRAY);
		studentsBtn_subjects.setBounds(10, 251, 200, 30);
		panel.add(studentsBtn_subjects);
		
		JButton subjectsBtn_subjects = new JButton("Subjects");
		subjectsBtn_subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectsDashboard subjectsDashboard = new SubjectsDashboard(name);
				System.out.println("SubjectsDashboard created SubjectsDashboard.");
				subjectsDashboard.show();
				frame.setVisible(false);
			}
		});
		subjectsBtn_subjects.setIcon(null);
		subjectsBtn_subjects.setForeground(Color.WHITE);
		subjectsBtn_subjects.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectsBtn_subjects.setBackground(Color.DARK_GRAY);
		subjectsBtn_subjects.setBounds(10, 292, 200, 30);
		panel.add(subjectsBtn_subjects);
		
		JButton resultsBtn_subjects = new JButton("Results");
		resultsBtn_subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultsDashboard resultsDashboard = new ResultsDashboard(name);
				System.out.println("SubjectsDashboard created ResultsDashboard.");
				resultsDashboard.show();
				frame.setVisible(false);
			}
		});
		resultsBtn_subjects.setIcon(null);
		resultsBtn_subjects.setForeground(Color.WHITE);
		resultsBtn_subjects.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		resultsBtn_subjects.setBackground(Color.DARK_GRAY);
		resultsBtn_subjects.setBounds(10, 415, 200, 30);
		panel.add(resultsBtn_subjects);
		
		JButton examsBtn_subjects = new JButton("Exams");
		examsBtn_subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExamsDashboard examsDashboard = new ExamsDashboard(name);
				System.out.println("SubjectsDashboard created ExamsDashboard.");
				examsDashboard.show();
				frame.setVisible(false);
			}
		});
		examsBtn_subjects.setIcon(null);
		examsBtn_subjects.setForeground(Color.WHITE);
		examsBtn_subjects.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		examsBtn_subjects.setBackground(Color.DARK_GRAY);
		examsBtn_subjects.setBounds(10, 374, 200, 30);
		panel.add(examsBtn_subjects);
		
		JButton homeBtn_subjects = new JButton("Home");
		homeBtn_subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainDashboard mainDashboard = new MainDashboard(name);
				System.out.println("SubjectsDashboard created MainDashboard.");
				mainDashboard.show();
				frame.setVisible(false);
			}
		});
		homeBtn_subjects.setIcon(new ImageIcon(SubjectsDashboard.class.getResource("/resources/home2.png")));
		homeBtn_subjects.setForeground(Color.WHITE);
		homeBtn_subjects.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		homeBtn_subjects.setBackground(Color.DARK_GRAY);
		homeBtn_subjects.setBounds(44, 154, 132, 39);
		panel.add(homeBtn_subjects);
		
		JButton logOutBtn_subjects = new JButton("");
		logOutBtn_subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginWindow = new Login();
				System.out.println("SubjectsDashboard created Login.");
				loginWindow.show();
				frame.setVisible(false);
			}
		});
		logOutBtn_subjects.setIcon(new ImageIcon(SubjectsDashboard.class.getResource("/resources/logout.png")));
		logOutBtn_subjects.setBackground(Color.DARK_GRAY);
		logOutBtn_subjects.setBounds(170, 510, 40, 40);
		panel.add(logOutBtn_subjects);
		
		JButton classesBtn_subjects = new JButton("Classes");
		classesBtn_subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassesDashboard classesDashboard = new ClassesDashboard(name);
				System.out.println("SubjectsDashboard created ClassesDashboard.");
				classesDashboard.show();
				frame.setVisible(false);
			}
		});
		classesBtn_subjects.setIcon(null);
		classesBtn_subjects.setForeground(Color.WHITE);
		classesBtn_subjects.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classesBtn_subjects.setBackground(Color.DARK_GRAY);
		classesBtn_subjects.setBounds(10, 333, 200, 30);
		panel.add(classesBtn_subjects);
		
		JLabel lblNewLabel_2 = new JLabel("Manage Subjects");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(230, 11, 694, 70);
		frame.getContentPane().add(lblNewLabel_2);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(253, 81, 651, 448);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Add a subject");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 300, 30);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Subject Name");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 100, 135, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		idForAddingSubjectField = new JTextField("0");
		idForAddingSubjectField.setToolTipText("Subject ID - Automatically Assigned");
		idForAddingSubjectField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idForAddingSubjectField.setEditable(false);
		idForAddingSubjectField.setColumns(10);
		idForAddingSubjectField.setBounds(52, 52, 85, 30);
		panel_1.add(idForAddingSubjectField);
		
		subjectNameTextField = new JTextField();
		subjectNameTextField.setToolTipText("Enter subject name");
		subjectNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectNameTextField.setColumns(10);
		subjectNameTextField.setBounds(141, 100, 157, 30);
		panel_1.add(subjectNameTextField);
		
		JLabel lblNewLabel_1_2 = new JLabel("Subject Details");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(10, 192, 631, 30);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Edit or remove a subject");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(341, 11, 300, 30);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("ID");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(351, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1_2);
		
		subjectIdText = new JTextField();
		subjectIdText.setToolTipText("Enter the Subject ID to edit or remove details");
		subjectIdText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		subjectIdText.setColumns(10);
		subjectIdText.setBounds(393, 52, 89, 30);
		panel_1.add(subjectIdText);
		
		JButton searchSubjectsButton = new JButton("Search Details");
		searchSubjectsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchSubjectDetails();
			}
		});
		searchSubjectsButton.setToolTipText("Search for a subject");
		searchSubjectsButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		searchSubjectsButton.setBackground(Color.LIGHT_GRAY);
		searchSubjectsButton.setBounds(490, 52, 151, 30);
		panel_1.add(searchSubjectsButton);
		
		JButton removeSubjectButton = new JButton("Remove");
		removeSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the subject with ID: " + subjectIdText.getText(), "Confirm Removal?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        		
        		if (response == JOptionPane.YES_OPTION) {
        			removeSubject();
        			clearFields();
        			//System.out.println("Remove: " + getNextSubjectId());
        		} else {
        			return;
        		}
			}
		});
		removeSubjectButton.setToolTipText("Remove Subject");
		removeSubjectButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		removeSubjectButton.setBackground(Color.LIGHT_GRAY);
		removeSubjectButton.setBounds(341, 103, 141, 30);
		panel_1.add(removeSubjectButton);
		
		JButton clearSubjectButton = new JButton("Clear Fields");
		clearSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		clearSubjectButton.setToolTipText("Clear all text fields");
		clearSubjectButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		clearSubjectButton.setBackground(Color.LIGHT_GRAY);
		clearSubjectButton.setBounds(146, 52, 151, 30);
		panel_1.add(clearSubjectButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(320, 11, 5, 150);
		panel_1.add(panel_2);
		
		JButton addSubjectButton = new JButton("Add Subject");
		addSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertSubject(subjectNameTextField);
			}
		});
		addSubjectButton.setForeground(Color.WHITE);
		addSubjectButton.setToolTipText("Save Subject");
		addSubjectButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		addSubjectButton.setBackground(Color.DARK_GRAY);
		addSubjectButton.setBounds(10, 151, 288, 30);
		panel_1.add(addSubjectButton);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.DARK_GRAY);
		panel_2_1.setBounds(320, 157, 320, 5);
		panel_1.add(panel_2_1);
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSubjectDetails();
			}
		});
		btnSaveChanges.setToolTipText("Save subject name");
		btnSaveChanges.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnSaveChanges.setBackground(Color.LIGHT_GRAY);
		btnSaveChanges.setBounds(490, 103, 151, 30);
		panel_1.add(btnSaveChanges);
	}
	
	// Create the Subject's table - table1
	private void createSubjectsTable() {
		// Init of the Table for displaying the Subjects information - table1
		// Create a tablemodel - tableModel1
		tableModel1 = new DefaultTableModel();
		tableModel1.addColumn("ID");
		tableModel1.addColumn("Subject Name");

			
		// Create a JTable with the model
	    JTable table1 = new JTable(tableModel1);
	    table1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    table1.getColumnModel().getColumn(0).setPreferredWidth(40);
	    table1.getColumnModel().getColumn(1).setPreferredWidth(150);
	        
	    table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	        
	    // Wrap the JTable inside a JScrollPane
	    JScrollPane scrollPane = new JScrollPane(table1);

	    // Add the JScrollPane to the JFrame
	    panel_1.add(scrollPane);
	    scrollPane.setBounds(10, 233, 631, 204);
	}
	
	// Update the Subject's table - table1
	private void updateSubjectsTable() {
		tableModel1.setRowCount(0);
				
		Connection connection = DatabaseHandler.getConnection();
		try {
			String query = "select subject_id, subject_name from Subjects order by subject_id asc";			
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);
				
			while (resultSet.next()) {
				int subjectId_table = resultSet.getInt("subject_id");
				String subjectName_table = resultSet.getString("subject_name");

				tableModel1.addRow(new Object[] {subjectId_table, subjectName_table});
			}
			resultSet.close();
			statement.close();
			connection.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
			}
		refreshNextSubjectId();
	}
	
	// Update the ID Field
	private void refreshNextSubjectId() {
		this.cachedNextSubjectId = getNextSubjectId();
		idForAddingSubjectField.setText(String.valueOf(this.cachedNextSubjectId));
	}	
	
	// Find the next Student ID
	private int getNextSubjectId() {
		Connection connection = DatabaseHandler.getConnection();
		int nextSubjectId = -1;
		
		try {
			String query = "select subject_id from RemovedSubjects order by subject_id asc limit 1";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				nextSubjectId = resultSet.getInt("subject_id");
				//System.out.println(nextStudentId);
			} else {
				// Find the highest ID if no Re-Usable ID's are available
				query = "select coalesce(max(subject_id), 8000) + 1 as next_id from Subjects";
				statement = connection.prepareStatement(query);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					nextSubjectId = resultSet.getInt("next_id");
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextSubjectId;
	}
	
	// Add a Subject to the database
	private void insertSubject(JTextField subjectNameTextField) {
			
		if (subjectNameTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter the Subject Name", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		int subjectId_Field = getNextSubjectId();
		String subjectName_Field = subjectNameTextField.getText();
		String insertQuery = "insert into Subjects (subject_id, subject_name) values (?, ?)";

		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertQuery); 
				
			statement.setInt(1, subjectId_Field);
			statement.setString(2, subjectName_Field);
				
			statement.executeUpdate();
			statement.close();
			connection.close();
				
			removeReusedSubjectId(subjectId_Field);
				
			JOptionPane.showMessageDialog(null, "Subject Added Succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error adding data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
			
		updateSubjectsTable();
		refreshNextSubjectId();
		clearFields();
	}
	
	// Remove a Subject
	private void removeSubject() {
		if (subjectIdText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Subject ID to remove.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		int subjectId = 0;
			
		try {
			subjectId = Integer.parseInt(subjectIdText.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid Subject ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		String deleteQuery = "delete from Subjects where subject_id = ?";
		String insertQuery = "insert into RemovedSubjects (subject_id) values (?)";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
				
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, subjectId);
			int rowsAffected = deleteStatement.executeUpdate();
				
			if (rowsAffected > 0) {
				PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
				insertStatement.setInt(1, subjectId);
				insertStatement.executeUpdate();
				insertStatement.close();
					
				JOptionPane.showMessageDialog(null, "Subject removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No subject found with matching ID.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
			}
			
			deleteStatement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error while removing subject: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		
		updateSubjectsTable();
		refreshNextSubjectId();
		//insertToRemovedSubjects();
	}
	
	// Search Subjects details to edit or delete
	private void searchSubjectDetails() {
		if (subjectIdText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Subject ID.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		String query = "select * from Subjects where subject_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, Integer.parseInt(subjectIdText.getText()));
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				subjectNameTextField.setText(resultSet.getString("subject_name"));

			} else {
				JOptionPane.showMessageDialog(null, "No subject found with matching ID", "Not Found", JOptionPane.INFORMATION_MESSAGE);

			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid Subject ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Update existing subject details
	private void updateSubjectDetails() {
		if (subjectNameTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter a Subject name", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// ID Validation
		if (subjectIdText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter a Subject ID to save changes.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		int subjectId_Field = Integer.parseInt(subjectIdText.getText());
		String subjectName_Field = subjectNameTextField.getText();
			
		String checkQuery = "select count(*) from Subjects where subject_id = ?";
		String updateQuery = "update Subjects set subject_name = ? where subject_id = ?";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
				
			PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			checkStatement.setInt(1, subjectId_Field);
			ResultSet resultSet = checkStatement.executeQuery();
			resultSet.next();
				
			if (resultSet.getInt(1) == 0) {
				JOptionPane.showMessageDialog(null, "No Subject found with ID:" + subjectId_Field, "Validation Error", JOptionPane.ERROR_MESSAGE);
				resultSet.close();
				checkStatement.close();
				connection.close();
				return;
			}
				
			resultSet.close();
			checkStatement.close();
				
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			
			statement.setString(1, subjectName_Field);
			statement.setInt(2, subjectId_Field);
			
			statement.executeUpdate();
			statement.close();
			connection.close();
				
			JOptionPane.showMessageDialog(null, "Successfully updated the Subject name with ID: " + subjectId_Field, "Success", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		updateSubjectsTable();
		refreshNextSubjectId();
		clearFields();
	}
	
	// Removing of reused Subject Id's
	private void removeReusedSubjectId(int subjectId) {
		String deleteQuery = "delete from RemovedSubjects where subject_id = ?";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteQuery);
				
			statement.setInt(1, subjectId);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error updating RemovedSubjects: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Clear Teacher Details Fields
	private void clearFields() {
		subjectNameTextField.setText(null);
		subjectIdText.setText(null);
		refreshNextSubjectId();
	}
}