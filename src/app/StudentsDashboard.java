package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import db.DatabaseHandler;

public class StudentsDashboard {

	private JFrame frame;
	private JTextField idForAddingStudentField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField idForEditOrRemoveStudents;
	private DefaultTableModel tableModel1;
	private JPanel panel_1;
	private int cachedNextStudentId;
	private JTextField addressField;
	private JComboBox<String> classNameFromClassesTable;
	private JDateChooser birthdateChooser;

	/**
	 * Create the application.
	 */
	public StudentsDashboard(String name) {
		initialize(name);
		createStudentsTable();
		updateStudentsTable();
		populateClassNameComboBox();
	}

	public void show() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name) {
		frame = new JFrame("School Management System: Student Dashboard");
		frame.setResizable(false);
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
		lblNewLabel.setIcon(new ImageIcon(StudentsDashboard.class.getResource("/resources/profile2.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 32, 200, 70);
		panel.add(lblNewLabel);
		
		JLabel greeting_students = new JLabel("Hello, " + name);
		greeting_students.setHorizontalAlignment(SwingConstants.CENTER);
		greeting_students.setForeground(Color.WHITE);
		greeting_students.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		greeting_students.setBounds(10, 104, 200, 39);
		panel.add(greeting_students);
		
		JButton teachersBtn_students = new JButton("Teachers");
		teachersBtn_students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeachersDashboard teachersDashboard = new TeachersDashboard(name);
				System.out.println("StudentsDashboard created TeachersDashboard.");
				teachersDashboard.show();
				frame.setVisible(false);
			}
		});
		teachersBtn_students.setIcon(null);
		teachersBtn_students.setForeground(Color.WHITE);
		teachersBtn_students.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		teachersBtn_students.setBackground(Color.DARK_GRAY);
		teachersBtn_students.setBounds(10, 210, 200, 30);
		panel.add(teachersBtn_students);
		
		JButton studentsBtn_students = new JButton("Students");
		studentsBtn_students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentsDashboard studentsDashboard = new StudentsDashboard(name);
				System.out.println("StudentsDashboard created StudentsDashboard.");
				studentsDashboard.show();
				frame.setVisible(false);
			}
		});
		studentsBtn_students.setIcon(null);
		studentsBtn_students.setForeground(Color.WHITE);
		studentsBtn_students.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentsBtn_students.setBackground(Color.DARK_GRAY);
		studentsBtn_students.setBounds(10, 251, 200, 30);
		panel.add(studentsBtn_students);
		
		JButton subjectsBtn_students = new JButton("Subjects");
		subjectsBtn_students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectsDashboard subjectsDashboard = new SubjectsDashboard(name);
				System.out.println("StudentsDashboard created SubjectsDashboard.");
				subjectsDashboard.show();
				frame.setVisible(false);
			}
		});
		subjectsBtn_students.setIcon(null);
		subjectsBtn_students.setForeground(Color.WHITE);
		subjectsBtn_students.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectsBtn_students.setBackground(Color.DARK_GRAY);
		subjectsBtn_students.setBounds(10, 292, 200, 30);
		panel.add(subjectsBtn_students);
		
		JButton resultsBtn_students = new JButton("Results");
		resultsBtn_students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultsDashboard resultsDashboard = new ResultsDashboard(name);
				resultsDashboard.show();
				System.out.println("StudentsDashboard created ResultsDashboard.");
				frame.setVisible(false);
			}
		});
		resultsBtn_students.setIcon(null);
		resultsBtn_students.setForeground(Color.WHITE);
		resultsBtn_students.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		resultsBtn_students.setBackground(Color.DARK_GRAY);
		resultsBtn_students.setBounds(10, 415, 200, 30);
		panel.add(resultsBtn_students);
		
		JButton examsBtn_students = new JButton("Exams");
		examsBtn_students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExamsDashboard examsDashboard = new ExamsDashboard(name);
				examsDashboard.show();
				System.out.println("StudentsDashboard created ExamsDashboard.");
				frame.setVisible(false);
			}
		});
		examsBtn_students.setIcon(null);
		examsBtn_students.setForeground(Color.WHITE);
		examsBtn_students.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		examsBtn_students.setBackground(Color.DARK_GRAY);
		examsBtn_students.setBounds(10, 374, 200, 30);
		panel.add(examsBtn_students);
		
		JButton homeBtn_students = new JButton("Home");
		homeBtn_students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainDashboard mainDashboard = new MainDashboard(name);
				System.out.println("StudentsDashboard created MainDashboard.");
				mainDashboard.show();
				frame.setVisible(false);
			}
		});
		homeBtn_students.setIcon(new ImageIcon(StudentsDashboard.class.getResource("/resources/home2.png")));
		homeBtn_students.setForeground(Color.WHITE);
		homeBtn_students.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		homeBtn_students.setBackground(Color.DARK_GRAY);
		homeBtn_students.setBounds(44, 154, 132, 39);
		panel.add(homeBtn_students);
		
		JButton logOutBtn_students = new JButton("");
		logOutBtn_students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginWindow = new Login();
				System.out.println("StudentsDashboard created Login.");
				loginWindow.show();
				frame.setVisible(false);
			}
		});
		logOutBtn_students.setIcon(new ImageIcon(StudentsDashboard.class.getResource("/resources/logout.png")));
		logOutBtn_students.setBackground(Color.DARK_GRAY);
		logOutBtn_students.setBounds(170, 510, 40, 40);
		panel.add(logOutBtn_students);
		
		JButton classesBtn_students = new JButton("Classes");
		classesBtn_students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassesDashboard classesDashboard = new ClassesDashboard(name);
				classesDashboard.show();
				System.out.println("StudentsDashboard created ClassesDashboard.");
				frame.setVisible(false);
			}
		});
		classesBtn_students.setIcon(null);
		classesBtn_students.setForeground(Color.WHITE);
		classesBtn_students.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classesBtn_students.setBackground(Color.DARK_GRAY);
		classesBtn_students.setBounds(10, 333, 200, 30);
		panel.add(classesBtn_students);
		
		JLabel lblNewLabel_2 = new JLabel("Manage Students");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(230, 11, 694, 70);
		frame.getContentPane().add(lblNewLabel_2);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(253, 81, 651, 448);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Add a student");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 300, 30);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("First Name");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 93, 135, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		idForAddingStudentField = new JTextField("0");
		idForAddingStudentField.setToolTipText("Student ID - Automatically Assigned");
		idForAddingStudentField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idForAddingStudentField.setEditable(false);
		idForAddingStudentField.setColumns(10);
		idForAddingStudentField.setBounds(52, 52, 85, 30);
		panel_1.add(idForAddingStudentField);
		
		firstNameField = new JTextField();
		firstNameField.setToolTipText("Enter student's first name");
		firstNameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		firstNameField.setColumns(10);
		firstNameField.setBounds(151, 93, 147, 30);
		panel_1.add(firstNameField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 134, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		lastNameField = new JTextField();
		lastNameField.setToolTipText("Enter student's last name");
		lastNameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lastNameField.setColumns(10);
		lastNameField.setBounds(151, 134, 147, 30);
		panel_1.add(lastNameField);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Class Name");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(10, 216, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Address");
		lblNewLabel_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1.setBounds(330, 216, 65, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Birthday");
		lblNewLabel_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_2.setBounds(10, 175, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_2);
		
		birthdateChooser = new JDateChooser();
		birthdateChooser.setToolTipText("Enter student's birthday");
		birthdateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		birthdateChooser.setBounds(151, 175, 147, 30);
		panel_1.add(birthdateChooser);
		
		JLabel lblNewLabel_1_2 = new JLabel("Student Details");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(10, 257, 631, 30);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Edit or remove a student");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(341, 11, 300, 30);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("ID");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(351, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1_2);
		
		idForEditOrRemoveStudents = new JTextField();
		idForEditOrRemoveStudents.setToolTipText("Enter the Students ID to edit or remove details");
		idForEditOrRemoveStudents.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idForEditOrRemoveStudents.setColumns(10);
		idForEditOrRemoveStudents.setBounds(393, 52, 89, 30);
		panel_1.add(idForEditOrRemoveStudents);
		
		JButton searchDetailsButton = new JButton("Search Details");
		searchDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchStudentDetails();
			}
		});
		searchDetailsButton.setToolTipText("Search for student details");
		searchDetailsButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		searchDetailsButton.setBackground(Color.LIGHT_GRAY);
		searchDetailsButton.setBounds(490, 52, 151, 30);
		panel_1.add(searchDetailsButton);
		
		JButton removeStudentButton = new JButton("Remove");
		removeStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the student with ID: " + idForEditOrRemoveStudents.getText(), "Confirm Removal?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        		
        		if (response == JOptionPane.YES_OPTION) {
        			removeStudent();
        			clearFields();
        			//System.out.println("Remove: " + getNextStudentId());
        		} else {
        			return;
        		}
			}
		});
		removeStudentButton.setToolTipText("Remove Student");
		removeStudentButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		removeStudentButton.setBackground(Color.LIGHT_GRAY);
		removeStudentButton.setBounds(341, 103, 141, 30);
		panel_1.add(removeStudentButton);
		
		JButton saveChangesButton = new JButton("Save Changes");
		saveChangesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStudentDetails();
			}
		});
		saveChangesButton.setToolTipText("Save student details");
		saveChangesButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		saveChangesButton.setBackground(Color.LIGHT_GRAY);
		saveChangesButton.setBounds(490, 103, 151, 30);
		panel_1.add(saveChangesButton);
		
		JButton clearAllFields = new JButton("Clear Fields");
		clearAllFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		clearAllFields.setToolTipText("Clear text fields");
		clearAllFields.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		clearAllFields.setBackground(Color.LIGHT_GRAY);
		clearAllFields.setBounds(341, 151, 300, 30);
		panel_1.add(clearAllFields);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(320, 11, 5, 185);
		panel_1.add(panel_2);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertStudent(firstNameField, lastNameField, classNameFromClassesTable, birthdateChooser, addressField);
			}
		});
		btnAddStudent.setForeground(Color.WHITE);
		btnAddStudent.setToolTipText("Save Student");
		btnAddStudent.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnAddStudent.setBackground(Color.DARK_GRAY);
		btnAddStudent.setBounds(151, 52, 147, 30);
		panel_1.add(btnAddStudent);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.DARK_GRAY);
		panel_2_1.setBounds(320, 192, 320, 5);
		panel_1.add(panel_2_1);
		
        classNameFromClassesTable = new JComboBox<>();
        classNameFromClassesTable.setSelectedIndex(-1);
        classNameFromClassesTable.setMaximumRowCount(13);
        classNameFromClassesTable.setToolTipText("Select the class");
        classNameFromClassesTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        classNameFromClassesTable.setBounds(151, 217, 147, 30);
        panel_1.add(classNameFromClassesTable);
        
        addressField = new JTextField();
        addressField.setToolTipText("Enter Student's address");
        addressField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        addressField.setColumns(10);
        addressField.setBounds(405, 216, 236, 30);
        panel_1.add(addressField);
	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------- Other Methods ----------------------------------------------------------
	
	// Create the Student's table - table1
	private void createStudentsTable() {
		// Init of the Table for displaying the Students information - table1
		// Create a tablemodel - tableModel1
		tableModel1 = new DefaultTableModel();
		tableModel1.addColumn("ID");
		tableModel1.addColumn("Full Name");
		tableModel1.addColumn("Birthday");
		tableModel1.addColumn("Class Name");
		tableModel1.addColumn("Address");
		
		// Create a JTable with the model
        JTable table1 = new JTable(tableModel1);
        table1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table1.getColumnModel().getColumn(0).setPreferredWidth(40);
        table1.getColumnModel().getColumn(1).setPreferredWidth(150);
        table1.getColumnModel().getColumn(2).setPreferredWidth(80);
        table1.getColumnModel().getColumn(3).setPreferredWidth(80);
        table1.getColumnModel().getColumn(4).setPreferredWidth(220);
        
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        // Wrap the JTable inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table1);

        // Add the JScrollPane to the JFrame
        panel_1.add(scrollPane);
        scrollPane.setBounds(10, 298, 631, 139);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.DARK_GRAY);
        panel_2.setBounds(320, 192, 320, 5);
        panel_1.add(panel_2);
    }
	
	// Update the Student's table - table1
	private void updateStudentsTable() {
		tableModel1.setRowCount(0);
			
		Connection connection = DatabaseHandler.getConnection();
		try {
			String query = "select s.student_id, concat(s.fname, ' ', s.lname) as student_name, s.dob as student_dob, s.class_name_number, s.class_name_letter, s.address as student_address from Students as s order by student_id asc";			
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				int studentId_table = resultSet.getInt("student_id");
				String studentFullName_table = resultSet.getString("student_name");
				String studentDob_table = resultSet.getString("student_dob");
				String classNameNumber_table = resultSet.getString("class_name_number");
				String classNameLetter_table = resultSet.getString("class_name_letter");
				String className_table = classNameNumber_table + classNameLetter_table;
				String studentAddress_table = resultSet.getString("student_address");
				
				tableModel1.addRow(new Object[] {studentId_table, studentFullName_table, studentDob_table, className_table, studentAddress_table});
			}
			resultSet.close();
			statement.close();
			connection.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
			}
		refreshNextStudentId();
	}
		
	// Update the ID Field
	private void refreshNextStudentId() {
		this.cachedNextStudentId = getNextStudentId();
		idForAddingStudentField.setText(String.valueOf(this.cachedNextStudentId));
	}
	
	// Find the next Student ID
	private int getNextStudentId() {
		Connection connection = DatabaseHandler.getConnection();
		int nextStudentId = -1;
		
		try {
			String query = "select student_id from RemovedStudents order by student_id asc limit 1";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				nextStudentId = resultSet.getInt("student_id");
				//System.out.println(nextStudentId);
			} else {
				// Find the highest ID if no Re-Usable ID's are available
				query = "select coalesce(max(student_id), 1000) + 1 as next_id from Students";
				statement = connection.prepareStatement(query);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					nextStudentId = resultSet.getInt("next_id");
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextStudentId;
	}
	
	// Populate the comboBox
	private void populateClassNameComboBox() {
		try {
			String query = "select class_name_number, class_name_letter from Classes order by class_name_number asc";
			
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			classNameFromClassesTable.removeAllItems();
			
			while (resultSet.next()) {
				classNameFromClassesTable.addItem(resultSet.getString("class_name_number") + resultSet.getString("class_name_letter"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Add a Student to the database
	private void insertStudent(JTextField firstNameField, JTextField lastNameField, JComboBox<String> classNameFromClassesTable, JDateChooser birthdateChooser, JTextField addressField) {
			
		if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || classNameFromClassesTable.getSelectedIndex() == -1 || addressField.getText().isEmpty() || birthdateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "One or more required fields are empty.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Seperating comboBox Values
		String selectedValue = (String) classNameFromClassesTable.getSelectedItem();
			
		int studentId_Field = getNextStudentId();
		String firstName_Field = firstNameField.getText();
		String lastName_CmbBox = lastNameField.getText();
		int classNameNumber_Field = Integer.parseInt(selectedValue.substring(0, selectedValue.length() - 1));
		String classNameLetter_Field = selectedValue.substring(selectedValue.length() - 1);
		String address_Field = addressField.getText();
			
		// Date conversion
		java.util.Date birthdate_Chooser = birthdateChooser.getDate();
		Date birthdate_Chooser_SQL = new Date(birthdate_Chooser.getTime());
			
		String insertQuery = "insert into Students (student_id, fname, lname, class_name_number, class_name_letter, dob, address) values (?, ?, ?, ?, ?, ?, ?)";

			
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertQuery); 
				
			statement.setInt(1, studentId_Field);
			statement.setString(2, firstName_Field);
			statement.setString(3, lastName_CmbBox);
			statement.setInt(4, classNameNumber_Field);
			statement.setString(5, classNameLetter_Field);
			statement.setDate(6, birthdate_Chooser_SQL);
			statement.setString(7, address_Field);
				
			statement.executeUpdate();
			statement.close();
			connection.close();
				
			removeReusedStudentId(studentId_Field);
				
			JOptionPane.showMessageDialog(null, "Student Added Succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error adding data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
			
		updateStudentsTable();
		refreshNextStudentId();
		clearFields();
	}
		
	// Remove a Student
	private void removeStudent() {
		if (idForEditOrRemoveStudents.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Student ID to remove.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		int studentId;
			
		try {
			studentId = Integer.parseInt(idForEditOrRemoveStudents.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid Student ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		String deleteQuery = "delete from Students where student_id = ?";
		String insertQuery = "insert into RemovedStudents (student_id) values (?)";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
				
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, studentId);
			int rowsAffected = deleteStatement.executeUpdate();
				
			if (rowsAffected > 0) {
				PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
				insertStatement.setInt(1, studentId);
				insertStatement.executeUpdate();
				insertStatement.close();
					
				JOptionPane.showMessageDialog(null, "Student removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No student found with matching ID.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
			}
			
			deleteStatement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error while removing student: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		
		updateStudentsTable();
		refreshNextStudentId();
		//insertToRemovedStudents();
	}
		
	// Search Students details to edit or delete
	private void searchStudentDetails() {
		if (idForEditOrRemoveStudents.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Student ID.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		String query = "select * from Students where student_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, Integer.parseInt(idForEditOrRemoveStudents.getText()));
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				firstNameField.setText(resultSet.getString("fname"));
				lastNameField.setText(resultSet.getString("lname"));
				birthdateChooser.setDate(resultSet.getDate("dob"));
				classNameFromClassesTable.setSelectedItem(resultSet.getInt("class_name_number") + resultSet.getString("class_name_letter"));
				addressField.setText(resultSet.getString("address"));
			} else {
				JOptionPane.showMessageDialog(null, "No teacher found with matching ID", "Not Found", JOptionPane.INFORMATION_MESSAGE);

			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid Student ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	// Update existing student details
	private void updateStudentDetails() {
		if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || classNameFromClassesTable.getSelectedIndex() == -1 || birthdateChooser.getDate() == null || addressField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "One or more required fields are empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// ID Validation
		if (idForEditOrRemoveStudents.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter a Student ID to save changes.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		// Formatting comboBox Value
		String selectedValue = (String) classNameFromClassesTable.getSelectedItem();
		int classNameNumber_Field = Integer.parseInt(selectedValue.substring(0, selectedValue.length() - 1));
		String classNameLetter_Field = selectedValue.substring(selectedValue.length() - 1);
			
		int studentId_Field = Integer.parseInt(idForEditOrRemoveStudents.getText());
		String firstname_Field = firstNameField.getText();
		String lastname_Field = lastNameField.getText();
		String address_Field = addressField.getText();
			
		// Date conversion
		java.util.Date birthdate_Chooser = birthdateChooser.getDate();
		Date birthdate_Chooser_SQL = new Date(birthdate_Chooser.getTime());
		
		String checkQuery = "select count(*) from Students where student_id = ?";
		String updateQuery = "update Students set fname = ?, lname = ?, dob = ?, class_name_number = ?, class_name_letter = ?, address = ? where student_id = ?";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
				
			PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			checkStatement.setInt(1, studentId_Field);
			ResultSet resultSet = checkStatement.executeQuery();
			resultSet.next();
				
			if (resultSet.getInt(1) == 0) {
				JOptionPane.showMessageDialog(null, "No Student found with ID:" + studentId_Field, "Validation Error", JOptionPane.ERROR_MESSAGE);
				resultSet.close();
				checkStatement.close();
				connection.close();
				return;
			}
				
			resultSet.close();
			checkStatement.close();
				
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			
			statement.setString(1, firstname_Field);
			statement.setString(2, lastname_Field);
			statement.setDate(3, birthdate_Chooser_SQL);
			statement.setInt(4, classNameNumber_Field);
			statement.setString(5, classNameLetter_Field);
			statement.setString(6, address_Field);
			statement.setInt(7, studentId_Field);
			
			statement.executeUpdate();
			statement.close();
			connection.close();
				
			JOptionPane.showMessageDialog(null, "Successfully updated details of Student with ID: " + studentId_Field, "Success", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		updateStudentsTable();
		refreshNextStudentId();
		clearFields();
	}
		
	// Removing of reused Student Id's
	private void removeReusedStudentId(int studentId) {
		String deleteQuery = "delete from RemovedStudents where student_id = ?";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteQuery);
				
			statement.setInt(1, studentId);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error updating RemovedStudents: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	// Clear Teacher Details Fields
	private void clearFields() {
		firstNameField.setText(null);
		lastNameField.setText(null);
		birthdateChooser.setDate(null);
		classNameFromClassesTable.setSelectedIndex(-1);
		addressField.setText(null);
		idForEditOrRemoveStudents.setText(null);
		refreshNextStudentId();
	}
};