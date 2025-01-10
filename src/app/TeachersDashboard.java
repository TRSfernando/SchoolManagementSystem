package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import db.DatabaseHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeachersDashboard {

	private JFrame frame;
	private MainDashboard mainDashboard;
	private TeachersDashboard teachersDashboard;
	private Login loginWindow;
	private JTextField idForAddingTeacherField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField contactNumberField;
	private JTextField emailAddressField;
	private JTextField idForEditOrRemoveTeachers;
	//private int nextTeacherId;
	private DefaultTableModel tableModel1;
	private JPanel panel_1;
	private JDateChooser birthdateChooser;
	private int cachedNextTeacherId;

	public void show() {
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public TeachersDashboard(String name) {
		this.cachedNextTeacherId = getNextTeacherId();
		initialize(name);		
		createTeachersTable();
		updateTeachersTable();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name) {
		frame = new JFrame("School Management System: Teacher Dashboard");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 220, 561);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TeachersDashboard.class.getResource("/resources/profile2.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 32, 200, 70);
		panel.add(lblNewLabel);
		
		JLabel greeting_teachers = new JLabel("Hello, " + name);
		greeting_teachers.setHorizontalAlignment(SwingConstants.CENTER);
		greeting_teachers.setForeground(Color.WHITE);
		greeting_teachers.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		greeting_teachers.setBounds(10, 104, 200, 39);
		panel.add(greeting_teachers);
		
		JButton teachersBtn_teachers = new JButton("Teachers");
		teachersBtn_teachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teachersDashboard = new TeachersDashboard(name);
				teachersDashboard.show();
				System.out.println("TeachersDashboard created TeachersDashboard.");
				frame.setVisible(false);
			}
		});
		teachersBtn_teachers.setIcon(null);
		teachersBtn_teachers.setForeground(Color.WHITE);
		teachersBtn_teachers.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		teachersBtn_teachers.setBackground(Color.DARK_GRAY);
		teachersBtn_teachers.setBounds(10, 210, 200, 30);
		panel.add(teachersBtn_teachers);
		
		JButton studentsBtn_teachers = new JButton("Students");
		studentsBtn_teachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentsDashboard studentsDashboard = new StudentsDashboard(name);
				System.out.println("TeachersDashboard created StudentsDashboard.");
				studentsDashboard.show();
				frame.setVisible(false);
			}
		});
		studentsBtn_teachers.setIcon(null);
		studentsBtn_teachers.setForeground(Color.WHITE);
		studentsBtn_teachers.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentsBtn_teachers.setBackground(Color.DARK_GRAY);
		studentsBtn_teachers.setBounds(10, 251, 200, 30);
		panel.add(studentsBtn_teachers);
		
		JButton subjectsBtn_teachers = new JButton("Subjects");
		subjectsBtn_teachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectsDashboard subjectsDashboard = new SubjectsDashboard(name);
				System.out.println("TeachersDashboard created SubjectsDashboard.");
				subjectsDashboard.show();
				frame.setVisible(false);
			}
		});
		subjectsBtn_teachers.setIcon(null);
		subjectsBtn_teachers.setForeground(Color.WHITE);
		subjectsBtn_teachers.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectsBtn_teachers.setBackground(Color.DARK_GRAY);
		subjectsBtn_teachers.setBounds(10, 292, 200, 30);
		panel.add(subjectsBtn_teachers);
		
		JButton resultsBtn_teachers = new JButton("Results");
		resultsBtn_teachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultsDashboard resultsDashboard = new ResultsDashboard(name);
				resultsDashboard.show();
				System.out.println("TeachersDashboard created ResultsDashboard.");
				frame.setVisible(false);
			}
		});
		resultsBtn_teachers.setIcon(null);
		resultsBtn_teachers.setForeground(Color.WHITE);
		resultsBtn_teachers.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		resultsBtn_teachers.setBackground(Color.DARK_GRAY);
		resultsBtn_teachers.setBounds(10, 415, 200, 30);
		panel.add(resultsBtn_teachers);
		
		JButton examsBtn_teachers = new JButton("Exams");
		examsBtn_teachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExamsDashboard examsDashboard = new ExamsDashboard(name);
				examsDashboard.show();
				System.out.println("TeachersDashboard created ExamsDashboard.");
				frame.setVisible(false);
			}
		});
		examsBtn_teachers.setIcon(null);
		examsBtn_teachers.setForeground(Color.WHITE);
		examsBtn_teachers.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		examsBtn_teachers.setBackground(Color.DARK_GRAY);
		examsBtn_teachers.setBounds(10, 374, 200, 30);
		panel.add(examsBtn_teachers);
		
		JButton homeBtn_teachers = new JButton("Home");
		homeBtn_teachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainDashboard = new MainDashboard(name);
				mainDashboard.show();
				System.out.println("TeachersDashboard created MainDashboard.");
				frame.setVisible(false);
			}
		});
		homeBtn_teachers.setIcon(new ImageIcon(TeachersDashboard.class.getResource("/resources/home2.png")));
		homeBtn_teachers.setForeground(Color.WHITE);
		homeBtn_teachers.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		homeBtn_teachers.setBackground(Color.DARK_GRAY);
		homeBtn_teachers.setBounds(44, 154, 132, 39);
		panel.add(homeBtn_teachers);
		
		JButton logOutBtn_teachers = new JButton("");
		logOutBtn_teachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginWindow = new Login();
				loginWindow.show();
				System.out.println("TeachersDashboard created Login.");
				frame.setVisible(false);
			}
		});
		logOutBtn_teachers.setIcon(new ImageIcon(TeachersDashboard.class.getResource("/resources/logout.png")));
		logOutBtn_teachers.setBackground(Color.DARK_GRAY);
		logOutBtn_teachers.setBounds(170, 510, 40, 40);
		panel.add(logOutBtn_teachers);
		
		JButton classesBtn_teachers = new JButton("Classes");
		classesBtn_teachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassesDashboard classesDashboard = new ClassesDashboard(name);
				System.out.println("TeachersDashboard created ClassesDashboard.");
				classesDashboard.show();
				frame.setVisible(false);
			}
		});
		classesBtn_teachers.setIcon(null);
		classesBtn_teachers.setForeground(Color.WHITE);
		classesBtn_teachers.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classesBtn_teachers.setBackground(Color.DARK_GRAY);
		classesBtn_teachers.setBounds(10, 333, 200, 30);
		panel.add(classesBtn_teachers);
		
		JLabel lblNewLabel_2 = new JLabel("Manage Teachers");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(230, 11, 694, 70);
		frame.getContentPane().add(lblNewLabel_2);
		
		panel_1 = new JPanel();
		panel_1.setBounds(253, 81, 651, 448);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Add a teacher");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 300, 30);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		
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
		
		idForAddingTeacherField = new JTextField("" + getNextTeacherId());
		idForAddingTeacherField.setToolTipText("Teacher ID - Automatically Assigned");
		idForAddingTeacherField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idForAddingTeacherField.setEditable(false);
		idForAddingTeacherField.setBounds(52, 52, 85, 30);
		panel_1.add(idForAddingTeacherField);
		idForAddingTeacherField.setColumns(10);
		
		firstNameField = new JTextField();
		firstNameField.setToolTipText("Enter your first name");
		firstNameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		firstNameField.setColumns(10);
		firstNameField.setBounds(141, 93, 157, 30);
		panel_1.add(firstNameField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 134, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		lastNameField = new JTextField();
		lastNameField.setToolTipText("Enter your last name");
		lastNameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lastNameField.setColumns(10);
		lastNameField.setBounds(141, 134, 157, 30);
		panel_1.add(lastNameField);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Contact Number");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(10, 216, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		contactNumberField = new JTextField();
		contactNumberField.setToolTipText("Enter contact number - 7X-XXX-XXXX");
		contactNumberField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		contactNumberField.setColumns(10);
		contactNumberField.setBounds(141, 216, 157, 30);
		panel_1.add(contactNumberField);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("E-mail Address");
		lblNewLabel_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1.setBounds(320, 216, 115, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);
		
		emailAddressField = new JTextField();
		emailAddressField.setToolTipText("Enter email address");
		emailAddressField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		emailAddressField.setColumns(10);
		emailAddressField.setBounds(445, 216, 196, 30);
		panel_1.add(emailAddressField);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Birthday");
		lblNewLabel_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_2.setBounds(10, 175, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_2);
		
		birthdateChooser = new JDateChooser();
		birthdateChooser.setToolTipText("Enter your birthday");
		birthdateChooser.setBounds(141, 175, 157, 30);
		birthdateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(birthdateChooser);
		
		JLabel lblNewLabel_1_2 = new JLabel("Teacher Details");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(10, 257, 631, 30);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Edit or remove a teacher");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(341, 11, 300, 30);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("ID");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(351, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1_2);
		
		idForEditOrRemoveTeachers = new JTextField();
		idForEditOrRemoveTeachers.setToolTipText("Enter the Teacher ID to edit or remove details");
		idForEditOrRemoveTeachers.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idForEditOrRemoveTeachers.setColumns(10);
		idForEditOrRemoveTeachers.setBounds(393, 52, 89, 30);
		panel_1.add(idForEditOrRemoveTeachers);
		
		JButton searchDetailsButton = new JButton("Search Details");
		searchDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTeacherDetails();
			}
		});
		searchDetailsButton.setBackground(Color.LIGHT_GRAY);
		searchDetailsButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		searchDetailsButton.setBounds(490, 52, 151, 30);
		panel_1.add(searchDetailsButton);
        
        JButton removeTeacherButton = new JButton("Remove");
        removeTeacherButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the teacher with ID: " + idForEditOrRemoveTeachers.getText(), "Confirm Removal?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        		
        		if (response == JOptionPane.YES_OPTION) {
        			removeTeacher();
        			//System.out.println("Remove: " + getNextTeacherId());
        			refreshNextTeacherId();
        			clearFields();
        		} else {
        			return;
        		}
        	}
        });
        removeTeacherButton.setBackground(Color.LIGHT_GRAY);
        removeTeacherButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        removeTeacherButton.setBounds(341, 105, 141, 30);
        panel_1.add(removeTeacherButton);
        
        JButton saveChangesButton = new JButton("Save Changes");
        saveChangesButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTeacherDetails();
        	}
        });
        saveChangesButton.setBackground(Color.LIGHT_GRAY);
        saveChangesButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        saveChangesButton.setBounds(490, 105, 151, 30);
        panel_1.add(saveChangesButton);
        
        JButton allTeachersButton = new JButton("Clear Fields");
        allTeachersButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clearFields();
        	}
        });
        allTeachersButton.setBackground(Color.LIGHT_GRAY);
        allTeachersButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        allTeachersButton.setBounds(341, 151, 300, 30);
        panel_1.add(allTeachersButton);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.DARK_GRAY);
        panel_2.setBounds(320, 11, 5, 185);
        panel_1.add(panel_2);
        
        JButton addDetailsButton = new JButton("Add Teacher");
        addDetailsButton.setForeground(Color.WHITE);
        addDetailsButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		insertTeacher(firstNameField, lastNameField, birthdateChooser, contactNumberField, emailAddressField);
        		//System.out.println("Add: " + getNextTeacherId());
        	}
        });
        addDetailsButton.setBackground(Color.DARK_GRAY);
        addDetailsButton.setToolTipText("Save Teacher");
        addDetailsButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        addDetailsButton.setBounds(141, 52, 157, 30);
        panel_1.add(addDetailsButton);
	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------- Other Methods ----------------------------------------------------------
	
	// Find the next Teacher ID
	private int getNextTeacherId() {
		Connection connection = DatabaseHandler.getConnection();
		int nextTeacherId = -1;
		
		try {
			String query = "select teacher_id from RemovedTeachers order by teacher_id asc limit 1";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				nextTeacherId = resultSet.getInt("teacher_id");
				//System.out.println(nextTeacherId);
			} else {
				// Find the highest ID if no Re-Usable ID's are available
				query = "select coalesce(max(teacher_id), 6000) + 1 as next_id from Teachers";
				statement = connection.prepareStatement(query);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					nextTeacherId = resultSet.getInt("next_id");
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextTeacherId;
	}
	
	// Create the Teachers table - table1
	private void createTeachersTable() {
		// Init of the Table for displaying the teachers information - table1
		// Create a table model - tableModel1
		tableModel1 = new DefaultTableModel();
        tableModel1.addColumn("ID");
        tableModel1.addColumn("Full Name");
        tableModel1.addColumn("Birthday");
        tableModel1.addColumn("Contact Number");
        tableModel1.addColumn("Email Address");

        // Create a JTable with the model
        JTable table1 = new JTable(tableModel1);
        table1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table1.getColumnModel().getColumn(0).setPreferredWidth(40);
        table1.getColumnModel().getColumn(1).setPreferredWidth(150);
        table1.getColumnModel().getColumn(2).setPreferredWidth(80);
        table1.getColumnModel().getColumn(3).setPreferredWidth(100);
        table1.getColumnModel().getColumn(4).setPreferredWidth(200);
        
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
	
	// Update the Teachers table - table1
	private void updateTeachersTable() {
		tableModel1.setRowCount(0);
		
		Connection connection = DatabaseHandler.getConnection();
		try {
			String query = "select t.teacher_id as teacher_id, concat(t.fname, ' ', lname) as teacher_name, t.dob as teacher_dob, t.phone_number as teacher_phone, t.email_address as teacher_email from Teachers as t order by teacher_id asc";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);
		
			while (resultSet.next()) {
				int teacherId_table = resultSet.getInt("teacher_id");
				String teacherFullName_table = resultSet.getString("teacher_name");
				String teacherDob_table = resultSet.getString("teacher_dob");
				String teacherPhone_table = resultSet.getString("teacher_phone");
				String teacherEmail_table = resultSet.getString("teacher_email");
			
				tableModel1.addRow(new Object[] {teacherId_table, teacherFullName_table, teacherDob_table, teacherPhone_table, teacherEmail_table});
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		refreshNextTeacherId();
	}
	
	// Add a Teacher to the database
	private void insertTeacher(JTextField firstNameField, JTextField lastNameField, JDateChooser birthdateChooser, JTextField contactNumberField, JTextField emailAddressField) {
		
		if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || contactNumberField.getText().isEmpty() || emailAddressField.getText().isEmpty() || birthdateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "One or more required fields are empty.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Validations
		// 1 - Phone Number Validation
		if (!contactNumberField.getText().matches("0\\d{9}")) {
			JOptionPane.showMessageDialog(null, "Phone number should contain 10 digits and should start from 0.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// 2 - Email Address Validation
		if (!emailAddressField.getText().matches("^[^@\\s]+@[^@\\s]+\\.com$")) {
			JOptionPane.showMessageDialog(null, "Enter a valid email address.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int teacherId_Field = getNextTeacherId();
		String firstName_Field = firstNameField.getText();
		String lastName_Field = lastNameField.getText();
		String contactNumber_Field = contactNumberField.getText();
		String emailAddress_Field = emailAddressField.getText();
		
		// Date conversion
		java.util.Date birthdate_Chooser = birthdateChooser.getDate();
		Date birthdate_Chooser_SQL = new Date(birthdate_Chooser.getTime());
		
		String insertQuery = "insert into Teachers (teacher_id, fname, lname, dob, phone_number, email_address) values (?, ?, ?, ?, ?, ?)";

		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertQuery); 
			
			statement.setInt(1, teacherId_Field);
			statement.setString(2, firstName_Field);
			statement.setString(3, lastName_Field);
			statement.setDate(4, birthdate_Chooser_SQL);
			statement.setString(5, contactNumber_Field);
			statement.setString(6, emailAddress_Field);
			
			statement.executeUpdate();
			statement.close();
			connection.close();
			
			removeReusedTeacherId(teacherId_Field);
			
			JOptionPane.showMessageDialog(null, "Teacher Added Succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error adding data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		
		updateTeachersTable();
		refreshNextTeacherId();
		clearFields();
	}

	// Removing of reused Teacher Id's
	private void removeReusedTeacherId(int teacherId) {
		String deleteQuery = "delete from RemovedTeachers where teacher_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteQuery);
			
			statement.setInt(1, teacherId);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error updating RemovedTeachers: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Search Teacher details to edit or delete
	private void searchTeacherDetails() {
		if (idForEditOrRemoveTeachers.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Teacher ID.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		String query = "select * from Teachers where teacher_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, Integer.parseInt(idForEditOrRemoveTeachers.getText()));
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				firstNameField.setText(resultSet.getString("fname"));
				lastNameField.setText(resultSet.getString("lname"));
				birthdateChooser.setDate(resultSet.getDate("dob"));
				contactNumberField.setText(resultSet.getString("phone_number"));
				emailAddressField.setText(resultSet.getString("email_address"));
			} else {
				JOptionPane.showMessageDialog(null, "No teacher found with matching ID", "Not Found", JOptionPane.INFORMATION_MESSAGE);

			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid Teacher ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Remove a Teacher
	private void removeTeacher() {
		if (idForEditOrRemoveTeachers.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Teacher ID to remove.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int teacherId;
		
		try {
			teacherId = Integer.parseInt(idForEditOrRemoveTeachers.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid Teacher ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String deleteQuery = "delete from Teachers where teacher_id = ?";
		String insertQuery = "insert into RemovedTeachers (teacher_id) values (?)";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, teacherId);
			int rowsAffected = deleteStatement.executeUpdate();
			
			if (rowsAffected > 0) {
				PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
				insertStatement.setInt(1, teacherId);
				insertStatement.executeUpdate();
				insertStatement.close();
				
				JOptionPane.showMessageDialog(null, "Teacher removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No teacher found with matching ID.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
			}
			
			deleteStatement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error while removing teacher: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		
		updateTeachersTable();
		refreshNextTeacherId();
		//insertToRemovedTeachers();
	}
	
	private void updateTeacherDetails() {
		if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || contactNumberField.getText().isEmpty() || emailAddressField.getText().isEmpty() || birthdateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "One or more required fields are empty.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Validations
		// 1 - Phone Number Validation
		if (!contactNumberField.getText().matches("0\\d{9}")) {
			JOptionPane.showMessageDialog(null, "Phone number should contain 10 digits and should start from 0.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// 2 - Email Address Validation
		if (!emailAddressField.getText().matches("^[^@\\s]+@[^@\\s]+\\.com$")) {
			JOptionPane.showMessageDialog(null, "Enter a valid email address.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// 3 - ID Validation
		if (idForEditOrRemoveTeachers.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter a Teacher ID to save changes.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int teacherId_Field = Integer.parseInt(idForEditOrRemoveTeachers.getText());
		String firstname_Field = firstNameField.getText();
		String lastname_Field = lastNameField.getText();
		String contactNumber_Field = contactNumberField.getText();
		String emailAddress_Field = emailAddressField.getText();
		
		// Date conversion
		java.util.Date birthdate_Chooser = birthdateChooser.getDate();
		Date birthdate_Chooser_SQL = new Date(birthdate_Chooser.getTime());
		
		String checkQuery = "select count(*) from Teachers where teacher_id = ?";
		String updateQuery = "update Teachers set fname = ?, lname = ?, dob = ?, phone_number = ?, email_address = ? where teacher_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			
			PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			checkStatement.setInt(1, teacherId_Field);
			ResultSet resultSet = checkStatement.executeQuery();
			resultSet.next();
			
			if (resultSet.getInt(1) == 0) {
				JOptionPane.showMessageDialog(null, "No Teacher found with ID:" + teacherId_Field, "Validation Error", JOptionPane.ERROR_MESSAGE);
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
			statement.setString(4, contactNumber_Field);
			statement.setString(5, emailAddress_Field);
			statement.setInt(6, teacherId_Field);
			
			statement.executeUpdate();
			statement.close();
			connection.close();
			
			JOptionPane.showMessageDialog(null, "Successfully updated details of Teacher with ID: " + teacherId_Field, "Success", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		updateTeachersTable();
		refreshNextTeacherId();
		clearFields();
	}
	
	/* Replaced-----------------------------------------------------------------------------------------------------------------------------------
	// Insert to RemovedTeachers
	private void insertToRemovedTeachers() {
		String insertQuery = "insert into RemovedTeachers (teacher_id) value (?)";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertQuery);		
			
			int idForEditOrRemovalOfTeachers = Integer.parseInt(idForEditOrRemoveTeachers.getText());
			
			statement.setInt(1, idForEditOrRemovalOfTeachers);
			statement.executeUpdate();
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error while removing teacher: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	--------------------------------------------------------------------------------------------------------------------------------------------*/ 
	
	// Clear Teacher Details Fields
	private void clearFields() {
		firstNameField.setText(null);
		lastNameField.setText(null);
		birthdateChooser.setDate(null);
		contactNumberField.setText(null);
		emailAddressField.setText(null);
		idForEditOrRemoveTeachers.setText(null);
		refreshNextTeacherId();
	}
	
	// Update the ID Field
	private void refreshNextTeacherId() {
		this.cachedNextTeacherId = getNextTeacherId();
		idForAddingTeacherField.setText(String.valueOf(this.cachedNextTeacherId));
	}
}
