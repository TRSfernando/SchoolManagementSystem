package app;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import db.DatabaseHandler;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ResultsDashboard {

	private JFrame frame;
	private JTextField resultIdTextField;
	private JTextField gradeTextField;
	private JTextField subjectTextField;
	private JTextField resultTextField;
	private JTextField resultIdTextFieldForEdits;
	private JTextField termTextField;
	private DefaultTableModel tableModel1;
	private JPanel panel_1;
	private JComboBox<String> studentIdComboBox;
	private JComboBox<String> examIdComboBox;
	private int cachedNextResultId;

	/**
	 * Create the application.
	 */
	public ResultsDashboard(String name) {
		initialize(name);
		createResultsTable();
		populateComboBoxes();
		updateResultsTable();
		getNextResultId();
	}
	
	public void show() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name) {		
		frame = new JFrame("School Management System: Result Dashboard");
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
		lblNewLabel.setIcon(new ImageIcon(ResultsDashboard.class.getResource("/resources/profile2.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 32, 200, 70);
		panel.add(lblNewLabel);
		
		JLabel greeting_results = new JLabel("Hello, <dynamic>");
		greeting_results.setHorizontalAlignment(SwingConstants.CENTER);
		greeting_results.setForeground(Color.WHITE);
		greeting_results.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		greeting_results.setBounds(10, 104, 200, 39);
		panel.add(greeting_results);
		
		JButton teachersBtn_results = new JButton("Teachers");
		teachersBtn_results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeachersDashboard teachersDashboard = new TeachersDashboard(name);
				System.out.println("ResultsDashboard created TeachersDashboard.");
				teachersDashboard.show();
				frame.setVisible(false);
			}
		});
		teachersBtn_results.setIcon(null);
		teachersBtn_results.setForeground(Color.WHITE);
		teachersBtn_results.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		teachersBtn_results.setBackground(Color.DARK_GRAY);
		teachersBtn_results.setBounds(10, 210, 200, 30);
		panel.add(teachersBtn_results);
		
		JButton studentsBtn_results = new JButton("Students");
		studentsBtn_results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentsDashboard studentsDashboard = new StudentsDashboard(name);
				System.out.println("ResultsDashboard created StudentsDashboard.");
				studentsDashboard.show();
				frame.setVisible(false);
			}
		});
		studentsBtn_results.setIcon(null);
		studentsBtn_results.setForeground(Color.WHITE);
		studentsBtn_results.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentsBtn_results.setBackground(Color.DARK_GRAY);
		studentsBtn_results.setBounds(10, 251, 200, 30);
		panel.add(studentsBtn_results);
		
		JButton subjectsBtn_results = new JButton("Subjects");
		subjectsBtn_results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectsDashboard subjectsDashboard = new SubjectsDashboard(name);
				System.out.println("ResultsDashboard created SubjectsDashboard.");
				subjectsDashboard.show();
				frame.setVisible(false);
			}
		});
		subjectsBtn_results.setIcon(null);
		subjectsBtn_results.setForeground(Color.WHITE);
		subjectsBtn_results.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectsBtn_results.setBackground(Color.DARK_GRAY);
		subjectsBtn_results.setBounds(10, 292, 200, 30);
		panel.add(subjectsBtn_results);
		
		JButton resultsBtn_results = new JButton("Results");
		resultsBtn_results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultsDashboard resultsDashboard = new ResultsDashboard(name);
				System.out.println("ResultsDashboard created ResultsDashboard.");
				resultsDashboard.show();
				frame.setVisible(false);
			}
		});
		resultsBtn_results.setIcon(null);
		resultsBtn_results.setForeground(Color.WHITE);
		resultsBtn_results.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		resultsBtn_results.setBackground(Color.DARK_GRAY);
		resultsBtn_results.setBounds(10, 415, 200, 30);
		panel.add(resultsBtn_results);
		
		JButton examsBtn_results = new JButton("Exams");
		examsBtn_results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExamsDashboard examsDashboard = new ExamsDashboard(name);
				System.out.println("ResultsDashboard created ExamsDashboard.");
				examsDashboard.show();
				frame.setVisible(false);
			}
		});
		examsBtn_results.setIcon(null);
		examsBtn_results.setForeground(Color.WHITE);
		examsBtn_results.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		examsBtn_results.setBackground(Color.DARK_GRAY);
		examsBtn_results.setBounds(10, 374, 200, 30);
		panel.add(examsBtn_results);
		
		JButton homeBtn_results = new JButton("Home");
		homeBtn_results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainDashboard mainDashboard = new MainDashboard(name);
				System.out.println("ResultsDashboard created MainDashboard.");
				mainDashboard.show();
				frame.setVisible(false);
			}
		});
		homeBtn_results.setIcon(new ImageIcon(ResultsDashboard.class.getResource("/resources/home2.png")));
		homeBtn_results.setForeground(Color.WHITE);
		homeBtn_results.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		homeBtn_results.setBackground(Color.DARK_GRAY);
		homeBtn_results.setBounds(44, 154, 132, 39);
		panel.add(homeBtn_results);
		
		JButton logOutBtn_results = new JButton("");
		logOutBtn_results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginWindow = new Login();
				System.out.println("ResultsDashboard created Login.");
				loginWindow.show();
				frame.setVisible(false);
			}
		});
		logOutBtn_results.setIcon(new ImageIcon(ResultsDashboard.class.getResource("/resources/logout.png")));
		logOutBtn_results.setBackground(Color.DARK_GRAY);
		logOutBtn_results.setBounds(170, 510, 40, 40);
		panel.add(logOutBtn_results);
		
		JButton classesBtn_results = new JButton("Classes");
		classesBtn_results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassesDashboard classesDashboard = new ClassesDashboard(name);
				System.out.println("ResultsDashboard created ClassesDashboard.");
				classesDashboard.show();
				frame.setVisible(false);
			}
		});
		classesBtn_results.setIcon(null);
		classesBtn_results.setForeground(Color.WHITE);
		classesBtn_results.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classesBtn_results.setBackground(Color.DARK_GRAY);
		classesBtn_results.setBounds(10, 333, 200, 30);
		panel.add(classesBtn_results);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(257, 70, 651, 448);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Add a result");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 300, 30);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Exam ID");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 93, 135, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		resultIdTextField = new JTextField("0");
		resultIdTextField.setToolTipText("Teacher ID - Automatically Assigned");
		resultIdTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		resultIdTextField.setEditable(false);
		resultIdTextField.setColumns(10);
		resultIdTextField.setBounds(52, 52, 80, 30);
		panel_1.add(resultIdTextField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Grade");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 134, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		gradeTextField = new JTextField();
		gradeTextField.setEditable(false);
		gradeTextField.setToolTipText("Enter your last name");
		gradeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gradeTextField.setColumns(10);
		gradeTextField.setBounds(141, 134, 157, 30);
		panel_1.add(gradeTextField);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Subject");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(10, 216, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		subjectTextField = new JTextField();
		subjectTextField.setEditable(false);
		subjectTextField.setToolTipText("Enter contact number - 7X-XXX-XXXX");
		subjectTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectTextField.setColumns(10);
		subjectTextField.setBounds(141, 216, 157, 30);
		panel_1.add(subjectTextField);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Result");
		lblNewLabel_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1.setBounds(520, 216, 56, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);
		
		resultTextField = new JTextField();
		resultTextField.setToolTipText("Enter email address");
		resultTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		resultTextField.setColumns(10);
		resultTextField.setBounds(576, 216, 65, 30);
		panel_1.add(resultTextField);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Term");
		lblNewLabel_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_2.setBounds(10, 175, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Result Details");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(10, 257, 631, 30);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Edit or remove a result\r\n");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(341, 11, 300, 30);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("ID");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(351, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1_2);
		
		resultIdTextFieldForEdits = new JTextField();
		resultIdTextFieldForEdits.setToolTipText("Enter the Teacher ID to edit or remove details");
		resultIdTextFieldForEdits.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		resultIdTextFieldForEdits.setColumns(10);
		resultIdTextFieldForEdits.setBounds(393, 52, 89, 30);
		panel_1.add(resultIdTextFieldForEdits);
		
		JButton searchDetailsButton = new JButton("Search Details");
		searchDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchResultDetails();
			}
		});
		searchDetailsButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		searchDetailsButton.setBackground(Color.LIGHT_GRAY);
		searchDetailsButton.setBounds(490, 52, 151, 30);
		panel_1.add(searchDetailsButton);
		
		JButton removeTeacherButton = new JButton("Remove");
		removeTeacherButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		removeTeacherButton.setBackground(Color.LIGHT_GRAY);
		removeTeacherButton.setBounds(341, 103, 141, 30);
		panel_1.add(removeTeacherButton);
		
		JButton saveChangesButton = new JButton("Save Changes");
		saveChangesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateResultDetails();
			}
		});
		saveChangesButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		saveChangesButton.setBackground(Color.LIGHT_GRAY);
		saveChangesButton.setBounds(490, 103, 151, 30);
		panel_1.add(saveChangesButton);
		
		JButton allTeachersButton = new JButton("Clear Fields");
		allTeachersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		allTeachersButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		allTeachersButton.setBackground(Color.LIGHT_GRAY);
		allTeachersButton.setBounds(341, 148, 300, 30);
		panel_1.add(allTeachersButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(320, 11, 5, 185);
		panel_1.add(panel_2);
		
		JButton btnAddResult = new JButton("Add Result");
		btnAddResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertResults(examIdComboBox, studentIdComboBox, gradeTextField, termTextField, subjectTextField);
			}
		});
		btnAddResult.setForeground(Color.WHITE);
		btnAddResult.setToolTipText("Save Teacher");
		btnAddResult.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAddResult.setBackground(Color.DARK_GRAY);
		btnAddResult.setBounds(141, 52, 157, 30);
		panel_1.add(btnAddResult);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.DARK_GRAY);
		panel_2_1.setBounds(320, 192, 320, 5);
		panel_1.add(panel_2_1);
		
		examIdComboBox = new JComboBox<String>();
		examIdComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedExamIdStr = (String) examIdComboBox.getSelectedItem();
				
				if (selectedExamIdStr == null) {
					return;
				}
		        
		        try {
		            int selectedExamId = Integer.parseInt(selectedExamIdStr);
		            getTextFieldValues(selectedExamId);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		examIdComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		examIdComboBox.setBounds(141, 93, 157, 29);
		panel_1.add(examIdComboBox);
		
		termTextField = new JTextField();
		termTextField.setToolTipText("Enter your last name");
		termTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		termTextField.setEditable(false);
		termTextField.setColumns(10);
		termTextField.setBounds(141, 175, 157, 30);
		panel_1.add(termTextField);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Student ID");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(320, 216, 105, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_1_1);
		
		studentIdComboBox = new JComboBox<String>();
		studentIdComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentIdComboBox.setBounds(424, 220, 86, 29);
		panel_1.add(studentIdComboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Manage Results");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(234, 0, 694, 70);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------- Other Methods ----------------------------------------------------------
	
	// Create the Results's table - table1
	private void createResultsTable() {
		// Init of the Table for displaying the Results information - table1
		// Create a tablemodel - tableModel1
		tableModel1 = new DefaultTableModel();
		tableModel1.addColumn("ID");
		tableModel1.addColumn("Exam ID");
		tableModel1.addColumn("Grade");
		tableModel1.addColumn("Term");
		tableModel1.addColumn("Subject");
		tableModel1.addColumn("Student ID");
		tableModel1.addColumn("Result");
		
		// Create a JTable with the model
        JTable table1 = new JTable(tableModel1);
        table1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table1.getColumnModel().getColumn(0).setPreferredWidth(40);
        table1.getColumnModel().getColumn(1).setPreferredWidth(40);
        table1.getColumnModel().getColumn(2).setPreferredWidth(30);
        table1.getColumnModel().getColumn(3).setPreferredWidth(50);
        table1.getColumnModel().getColumn(4).setPreferredWidth(100);
        table1.getColumnModel().getColumn(5).setPreferredWidth(40);
        table1.getColumnModel().getColumn(6).setPreferredWidth(40);
        
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        // Wrap the JTable inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table1);

        // Add the JScrollPane to the JFrame
        panel_1.add(scrollPane);
        scrollPane.setBounds(10, 298, 631, 139);
    }
	
	// Update the Result's table - table1
	private void updateResultsTable() {
		tableModel1.setRowCount(0);
			
		Connection connection = DatabaseHandler.getConnection();
		try {
			String query = "select r.result_id as result_id, r.exam_id as exam_id, r.class_name_number as grade, r.term_name as term_name, s.subject_name as subject_name, r.student_id as student_id, r.result as result from Results as r join Subjects as s on r.subject_id = s.subject_id order by result_id asc";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				int resultId_table = resultSet.getInt("result_id");
				int examId_table = resultSet.getInt("exam_id");
				int grade_table = resultSet.getInt("grade");
				String termName_table = resultSet.getString("term_name");
				String subjectName_table = resultSet.getString("subject_name");
				int studentId_table = resultSet.getInt("student_id");
				int result_table = resultSet.getInt("result");

				
				tableModel1.addRow(new Object[] {resultId_table, examId_table, grade_table, termName_table, subjectName_table, studentId_table, result_table});
			}
			resultSet.close();
			statement.close();
			connection.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
			}
		refreshNextResultId();
	}
	
	// Add a Result to the database 
	private void insertResults(JComboBox<String> examIdComboBox, JComboBox<String> studentIdComboBox, JTextField gradeTextField, JTextField termTextField, JTextField subjectTextField) {
			
		if (examIdComboBox.getSelectedIndex() == -1 || studentIdComboBox.getSelectedIndex() == -1 || gradeTextField.getText() == null || termTextField.getText() == null || subjectTextField.getText() == null) {
			JOptionPane.showMessageDialog(null, "One or more required fields are empty.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Formatting
		Integer resultId = getNextResultId();
		String studentIdStr = (String) studentIdComboBox.getSelectedItem();
		Integer studentId = Integer.parseInt(studentIdStr);
		String subjectName = subjectTextField.getText();
		String examIdStr = (String) examIdComboBox.getSelectedItem();
		Integer examId = Integer.parseInt(examIdStr);
		String gradeStr = gradeTextField.getText();
		Integer grade = Integer.parseInt(gradeStr);
		String termName = termTextField.getText();
		Integer subjectId = -1;
		Integer result = null;
		
		// Check if the entered result is a valid number
		try {
			String resultStr = resultTextField.getText();
			result = Integer.parseInt(resultStr);
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid result: " + e.getMessage(), "Number Format Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Get subject id
		try {
			String query = "select subject_id from Subjects where subject_name = ?";
			Connection connection = DatabaseHandler.getConnection();
			
			// Grade
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, subjectName);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				String subjectIdStr = resultSet.getString("subject_id");
				subjectId = Integer.parseInt(subjectIdStr);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		
		String insertQuery = "insert into Results (result_id, student_id, subject_id, exam_id, class_name_number, term_name, result) values (?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertQuery); 
				
			statement.setInt(1, resultId);
			statement.setInt(2, studentId);
			statement.setInt(3, subjectId);
			statement.setInt(4, examId);
			statement.setInt(5, grade);
			statement.setString(6, termName);
			statement.setInt(7, result);
				
			statement.executeUpdate();
			statement.close();
			connection.close();
				
			removeReusedResultId(resultId);
				
			JOptionPane.showMessageDialog(null, "Result Added Succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error adding data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
			
		updateResultsTable();
		refreshNextResultId();
		clearFields();
	}
	
	// Update existing result details
	private void updateResultDetails() {
		if (examIdComboBox.getSelectedIndex() == -1 || studentIdComboBox.getSelectedIndex() == -1 || resultTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "One or more required fields are empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// ID Validation
		if (resultIdTextFieldForEdits.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter an Exam ID to save changes.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		// Formatting
		Integer resultId = getNextResultId();
		String studentIdStr = (String) studentIdComboBox.getSelectedItem();
		Integer studentId = Integer.parseInt(studentIdStr);
		String subjectName = subjectTextField.getText();
		String examIdStr = (String) examIdComboBox.getSelectedItem();
		Integer examId = Integer.parseInt(examIdStr);
		String gradeStr = gradeTextField.getText();
		Integer grade = Integer.parseInt(gradeStr);
		String termName = termTextField.getText();
		Integer subjectId = -1;
		Integer result = -1;
		
		// Check if the entered result is a valid number
		try {
			String resultStr = resultTextField.getText();
			result = Integer.parseInt(resultStr);
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid number: " + e.getMessage(), "Number Format Error", JOptionPane.ERROR_MESSAGE);
		}
		
		// Get subject id
		try {
			String query = "select subject_id from Subjects where subject_name = ?";
			Connection connection = DatabaseHandler.getConnection();
			
			// Grade
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, subjectName);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				String subjectIdStr = resultSet.getString("subject_id");
				subjectId = Integer.parseInt(subjectIdStr);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		
		String checkQuery = "select count(*) from Results where result_id = ?";
		String updateQuery = "update Results set exam_id = ?, class_name_number = ?, term_name = ?, subject_id = ?, student_id = ?, result = ? where result_id = ?";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
				
			PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			checkStatement.setInt(1, resultId);
			ResultSet resultSet = checkStatement.executeQuery();
			resultSet.next();
				
			if (resultSet.getInt(1) == 0) {
				JOptionPane.showMessageDialog(null, "No Result found with ID:" + resultId, "Validation Error", JOptionPane.ERROR_MESSAGE);
				resultSet.close();
				checkStatement.close();
				connection.close();
				return;
			}
				
			resultSet.close();
			checkStatement.close();
				
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			
			statement.setInt(1, resultId);
			statement.setInt(2, studentId);
			statement.setInt(3, subjectId);
			statement.setInt(4, examId);
			statement.setInt(5, grade);
			statement.setString(6, termName);
			statement.setInt(7, result);
			
			statement.executeUpdate();
			statement.close();
			connection.close();
				
			JOptionPane.showMessageDialog(null, "Successfully updated details of Result with ID: " + resultId, "Success", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		updateResultsTable();
		refreshNextResultId();
		clearFields();
	}
	
	
	// Search Results details to edit or delete
	private void searchResultDetails() {
		
		Integer resultId;
		
		if (resultIdTextFieldForEdits.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter an Result ID.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		String query = "select r.result_id as result_id, r.exam_id as exam_id, r.class_name_number as grade, r.term_name as term_name, s.subject_name as subject_name, r.student_id as student_id, r.result as result from Results as r join Subjects as s on r.subject_id = s.subject_id where result_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			String resultIdStr = resultIdTextFieldForEdits.getText();
			resultId = Integer.parseInt(resultIdStr);
			statement.setInt(1, resultId);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				examIdComboBox.setSelectedItem(resultSet.getString("exam_id"));
				gradeTextField.setText(resultSet.getString("grade"));
				termTextField.setText(resultSet.getString("term_name"));
				subjectTextField.setText(resultSet.getString("subject_name"));
				studentIdComboBox.setSelectedItem(resultSet.getString("student_id"));
				resultTextField.setText(resultSet.getString("result"));
			} else {
				JOptionPane.showMessageDialog(null, "No result found with matching ID", "Not Found", JOptionPane.INFORMATION_MESSAGE);

			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid Result ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	// Populate the Combo Boxes
	private void populateComboBoxes() {
		
		// Exam ID
		try {
			String query = "select exam_id from Exams order by exam_id asc";
			
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			examIdComboBox.removeAllItems();
			
			while (resultSet.next()) {
				examIdComboBox.addItem(resultSet.getString("exam_id"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Find the next Result ID
	private int getNextResultId() {
		Connection connection = DatabaseHandler.getConnection();
		int nextResultId = -1;
		
		try {
			String query = "select result_id from RemovedResults order by result_id asc limit 1";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				nextResultId = resultSet.getInt("result_id");
			} else {
				// Find the highest ID if no Re-Usable ID's are available
				query = "select coalesce(max(result_id), 3000) + 1 as next_id from Results";
				statement = connection.prepareStatement(query);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					nextResultId = resultSet.getInt("next_id");
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextResultId;
	}
	
	// Populate the Text Field Values depending on the selected Exam ID
	private void getTextFieldValues(int examIdReceiver) {
		
		
		String gradeQuery = "select class_name_number from Exams where exam_id = ?";
		String termQuery = "select term_name from Exams where exam_id = ?";
		String subjectQuery = "select s.subject_name as subject_name from Subjects s join Exams e on e.subject_id = s.subject_id where exam_id = ? order by s.subject_name asc";
		String studentQuery = "select student_id from Students where class_name_number = ? order by student_id asc";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			
			// Grade
			PreparedStatement gradeStatement = connection.prepareStatement(gradeQuery);
			gradeStatement.setInt(1, examIdReceiver);
			ResultSet gradeResultSet = gradeStatement.executeQuery();
			
			if(gradeResultSet.next()) {
				String grade = gradeResultSet.getString("class_name_number");
				gradeTextField.setText(grade);
			} else {
				gradeTextField.setText("Not Found");
			}
			
			// Term
			PreparedStatement termStatement = connection.prepareStatement(termQuery);
			termStatement.setInt(1, examIdReceiver);
			ResultSet termResultSet = termStatement.executeQuery();
			
			if(termResultSet.next()) {
				String termName = termResultSet.getString("term_name");
				termTextField.setText(termName);
			} else {
				termTextField.setText("Not Found");
			}
			
			// Subject Name
			PreparedStatement subjectStatement = connection.prepareStatement(subjectQuery);
			subjectStatement.setInt(1, examIdReceiver);
			ResultSet subjectResultSet = subjectStatement.executeQuery();
			
			if(subjectResultSet.next()) {
				String subjectName = subjectResultSet.getString("subject_name");
				subjectTextField.setText(subjectName);
			} else {
				subjectTextField.setText("Not Found");
			}
			
			// Student ID
			
			PreparedStatement studentStatement = connection.prepareStatement(studentQuery);
			
			if (gradeTextField.getText().isEmpty()) {
				return;
			}
			
			String gradeStr = gradeTextField.getText();
			Integer grade = Integer.parseInt(gradeStr);
			studentStatement.setInt(1, grade);
			
			ResultSet studentResultSet = studentStatement.executeQuery();
			
			studentIdComboBox.removeAllItems();
			
			while (studentResultSet.next()) {
				studentIdComboBox.addItem(studentResultSet.getString("student_id"));
			}
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error faced: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Update the ID Field
	private void refreshNextResultId() {
		this.cachedNextResultId = getNextResultId();
		resultIdTextField.setText(String.valueOf(this.cachedNextResultId));
	}
	
	// Removing of reused Result Id's
	private void removeReusedResultId(int resultId) {
		String deleteQuery = "delete from RemovedResults where result_id = ?";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteQuery);
				
			statement.setInt(1, resultId);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error updating RemovedResults: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Clear all the fields
	private void clearFields() {
		examIdComboBox.setSelectedIndex(-1);
		gradeTextField.setText(null);
		termTextField.setText(null);
		subjectTextField.setText(null);
		studentIdComboBox.setSelectedIndex(-1);
	}
}