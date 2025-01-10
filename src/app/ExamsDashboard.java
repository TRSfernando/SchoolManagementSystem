package app;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import db.DatabaseHandler;

public class ExamsDashboard {

	private JFrame frame;
	private JTextField examIdTextField;
	private JTextField subjectNameText;
	private JTextField examIdText;
	private JPanel panel_1;
	private DefaultTableModel tableModel1;
	private JComboBox<String> gradeComboBox;
	private JComboBox<String> termComboBox;
	private JComboBox<String> subjectIdComboBox;
	private JDateChooser examDateChooser;
	private int cachedNextExamId;

	/**
	 * Create the application.
	 */
	public ExamsDashboard(String name) {
		initialize(name);
		createExamsTable();
		updateComboBoxes();
		getNextExamId();
		updateExamsTable();
	}

	public void show() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name) {
		frame = new JFrame("School Management System: Exam Dashboard");
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
		lblNewLabel.setIcon(new ImageIcon(ExamsDashboard.class.getResource("/resources/profile2.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 32, 200, 70);
		panel.add(lblNewLabel);
		
		JLabel greeting_exams = new JLabel("Hello, " + name);
		greeting_exams.setHorizontalAlignment(SwingConstants.CENTER);
		greeting_exams.setForeground(Color.WHITE);
		greeting_exams.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		greeting_exams.setBounds(10, 104, 200, 39);
		panel.add(greeting_exams);
		
		JButton teachersBtn_exams = new JButton("Teachers");
		teachersBtn_exams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeachersDashboard teachersDashboard = new TeachersDashboard(name);
				System.out.println("ExamsDashboard created TeachersDashboard.");
				teachersDashboard.show();
				frame.setVisible(false);
			}
		});
		teachersBtn_exams.setIcon(null);
		teachersBtn_exams.setForeground(Color.WHITE);
		teachersBtn_exams.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		teachersBtn_exams.setBackground(Color.DARK_GRAY);
		teachersBtn_exams.setBounds(10, 210, 200, 30);
		panel.add(teachersBtn_exams);
		
		JButton studentsBtn_exams = new JButton("Students");
		studentsBtn_exams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentsDashboard studentsDashboard = new StudentsDashboard(name);
				System.out.println("ExamsDashboard created StudentsDashboard.");
				studentsDashboard.show();
				frame.setVisible(false);
			}
		});
		studentsBtn_exams.setIcon(null);
		studentsBtn_exams.setForeground(Color.WHITE);
		studentsBtn_exams.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentsBtn_exams.setBackground(Color.DARK_GRAY);
		studentsBtn_exams.setBounds(10, 251, 200, 30);
		panel.add(studentsBtn_exams);
		
		JButton subjectsBtn_exams = new JButton("Subjects");
		subjectsBtn_exams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectsDashboard subjectsDashboard = new SubjectsDashboard(name);
				System.out.println("ExamsDashboard created SubjectsDashboard.");
				subjectsDashboard.show();
				frame.setVisible(false);
			}
		});
		subjectsBtn_exams.setIcon(null);
		subjectsBtn_exams.setForeground(Color.WHITE);
		subjectsBtn_exams.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectsBtn_exams.setBackground(Color.DARK_GRAY);
		subjectsBtn_exams.setBounds(10, 292, 200, 30);
		panel.add(subjectsBtn_exams);
		
		JButton resultsBtn_exams = new JButton("Results");
		resultsBtn_exams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultsDashboard resultsDashboard = new ResultsDashboard(name);
				System.out.println("ExamsDashboard created ResultsDashboard.");
				resultsDashboard.show();
				frame.setVisible(false);
			}
		});
		resultsBtn_exams.setIcon(null);
		resultsBtn_exams.setForeground(Color.WHITE);
		resultsBtn_exams.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		resultsBtn_exams.setBackground(Color.DARK_GRAY);
		resultsBtn_exams.setBounds(10, 415, 200, 30);
		panel.add(resultsBtn_exams);
		
		JButton examsBtn_exams = new JButton("Exams");
		examsBtn_exams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExamsDashboard examsDashboard = new ExamsDashboard(name);
				System.out.println("ExamsDashboard created ExamsDashboard.");
				examsDashboard.show();
				frame.setVisible(false);
			}
		});
		examsBtn_exams.setIcon(null);
		examsBtn_exams.setForeground(Color.WHITE);
		examsBtn_exams.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		examsBtn_exams.setBackground(Color.DARK_GRAY);
		examsBtn_exams.setBounds(10, 374, 200, 30);
		panel.add(examsBtn_exams);
		
		JButton homeBtn_exams = new JButton("Home");
		homeBtn_exams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainDashboard mainDashboard = new MainDashboard(name);
				System.out.println("ExamsDashboard created MainDashboard.");
				mainDashboard.show();
				frame.setVisible(false);
			}
		});
		homeBtn_exams.setIcon(new ImageIcon(ExamsDashboard.class.getResource("/resources/home2.png")));
		homeBtn_exams.setForeground(Color.WHITE);
		homeBtn_exams.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		homeBtn_exams.setBackground(Color.DARK_GRAY);
		homeBtn_exams.setBounds(44, 154, 132, 39);
		panel.add(homeBtn_exams);
		
		JButton logOutBtn_exams = new JButton("");
		logOutBtn_exams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginWindow = new Login();
				System.out.println("ExamsDashboard created Login.");
				loginWindow.show();
				frame.setVisible(false);
			}
		});
		logOutBtn_exams.setIcon(new ImageIcon(ExamsDashboard.class.getResource("/resources/logout.png")));
		logOutBtn_exams.setBackground(Color.DARK_GRAY);
		logOutBtn_exams.setBounds(170, 510, 40, 40);
		panel.add(logOutBtn_exams);
		
		JButton classesBtn_exams = new JButton("Classes");
		classesBtn_exams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassesDashboard classesDashboard = new ClassesDashboard(name);
				System.out.println("ExamsDashboard created ClassesDashboard.");
				classesDashboard.show();
				frame.setVisible(false);
			}
		});
		classesBtn_exams.setIcon(null);
		classesBtn_exams.setForeground(Color.WHITE);
		classesBtn_exams.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classesBtn_exams.setBackground(Color.DARK_GRAY);
		classesBtn_exams.setBounds(10, 333, 200, 30);
		panel.add(classesBtn_exams);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(242, 70, 651, 448);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Add an exam");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 300, 30);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Grade");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 93, 135, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		examIdTextField = new JTextField("0");
		examIdTextField.setToolTipText("Teacher ID - Automatically Assigned");
		examIdTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		examIdTextField.setEditable(false);
		examIdTextField.setColumns(10);
		examIdTextField.setBounds(52, 52, 85, 30);
		panel_1.add(examIdTextField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Term");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 134, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Subject ID");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(10, 216, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Subject Name");
		lblNewLabel_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1.setBounds(320, 216, 115, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);
		
		subjectNameText = new JTextField();
		subjectNameText.setEditable(false);
		subjectNameText.setToolTipText("Enter email address");
		subjectNameText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectNameText.setColumns(10);
		subjectNameText.setBounds(445, 216, 196, 30);
		panel_1.add(subjectNameText);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Exam Date");
		lblNewLabel_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_2.setBounds(10, 175, 135, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_2);
		
		examDateChooser = new JDateChooser();
		examDateChooser.setToolTipText("Choose the Exam Date");
		examDateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		examDateChooser.setBounds(141, 175, 157, 30);
		panel_1.add(examDateChooser);
		
		JLabel lblNewLabel_1_2 = new JLabel("Exam Details");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(10, 257, 631, 30);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Edit or remove an exam");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(341, 11, 300, 30);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("ID");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(351, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1_2);
		
		examIdText = new JTextField();
		examIdText.setToolTipText("Enter the Exam \r\nID to edit or remove details");
		examIdText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		examIdText.setColumns(10);
		examIdText.setBounds(393, 52, 89, 30);
		panel_1.add(examIdText);
		
		JButton searchExamButton = new JButton("Search Details");
		searchExamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchExamDetails();
			}
		});
		searchExamButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		searchExamButton.setBackground(Color.LIGHT_GRAY);
		searchExamButton.setBounds(490, 52, 151, 30);
		panel_1.add(searchExamButton);
		
		JButton removeExamButton = new JButton("Remove");
		removeExamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the exam with ID: " + examIdText.getText(), "Confirm Removal?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        		
        		if (response == JOptionPane.YES_OPTION) {
        			removeExam();
        			clearFields();
        			//System.out.println("Remove: " + getNextStudentId());
        		} else {
        			return;
        		}
			}
		});
		removeExamButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		removeExamButton.setBackground(Color.LIGHT_GRAY);
		removeExamButton.setBounds(341, 98, 141, 30);
		panel_1.add(removeExamButton);
		
		JButton saveExamButton = new JButton("Save Changes");
		saveExamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateExamDetails();
			}
		});
		saveExamButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		saveExamButton.setBackground(Color.LIGHT_GRAY);
		saveExamButton.setBounds(490, 98, 151, 30);
		panel_1.add(saveExamButton);
		
		JButton clearExamButton = new JButton("Clear Fields");
		clearExamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		clearExamButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		clearExamButton.setBackground(Color.LIGHT_GRAY);
		clearExamButton.setBounds(341, 145, 300, 30);
		panel_1.add(clearExamButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(320, 11, 5, 185);
		panel_1.add(panel_2);
		
		JButton btnAddExam = new JButton("Add Exam");
		btnAddExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertExams(gradeComboBox, subjectIdComboBox, termComboBox, examDateChooser);
			}
		});
		btnAddExam.setForeground(Color.WHITE);
		btnAddExam.setToolTipText("Save Teacher");
		btnAddExam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAddExam.setBackground(Color.DARK_GRAY);
		btnAddExam.setBounds(141, 52, 157, 30);
		panel_1.add(btnAddExam);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.DARK_GRAY);
		panel_2_1.setBounds(320, 192, 320, 5);
		panel_1.add(panel_2_1);
		
		gradeComboBox = new JComboBox<String>();
		gradeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gradeComboBox.setToolTipText("Choose the grade");
		gradeComboBox.setBounds(141, 93, 157, 29);
		panel_1.add(gradeComboBox);
		
		termComboBox = new JComboBox<String>();
		termComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		termComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Term 1", "Term 2", "Term 3"}));
		termComboBox.setToolTipText("Choose the term");
		termComboBox.setBounds(141, 134, 157, 29);
		panel_1.add(termComboBox);
		
		subjectIdComboBox = new JComboBox<String>();
		subjectIdComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedSubjectIdStr = (String) subjectIdComboBox.getSelectedItem();
				
				if (selectedSubjectIdStr == null) {
					return;
				}
		        
		        try {
		            int selectedSubjectId = Integer.parseInt(selectedSubjectIdStr);
		            updateSubjectNameField(selectedSubjectId);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		subjectIdComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectIdComboBox.setToolTipText("Choose the Subject Id");
		subjectIdComboBox.setBounds(141, 216, 157, 29);
		panel_1.add(subjectIdComboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Manage Exams");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(219, 0, 694, 70);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------- Other Methods ----------------------------------------------------------
	
	// Create the Student's table - table1
	private void createExamsTable() {
		// Init of the Table for displaying the Students information - table1
		// Create a tablemodel - tableModel1
		tableModel1 = new DefaultTableModel();
		tableModel1.addColumn("ID");
		tableModel1.addColumn("Grade");
		tableModel1.addColumn("Term");
		tableModel1.addColumn("Subject ID");
		tableModel1.addColumn("Subject Name");
		tableModel1.addColumn("Exam Date");
		
		// Create a JTable with the model
        JTable table1 = new JTable(tableModel1);
        table1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table1.getColumnModel().getColumn(0).setPreferredWidth(30);
        table1.getColumnModel().getColumn(1).setPreferredWidth(30);
        table1.getColumnModel().getColumn(2).setPreferredWidth(40);
        table1.getColumnModel().getColumn(3).setPreferredWidth(40);
        table1.getColumnModel().getColumn(4).setPreferredWidth(100);
        table1.getColumnModel().getColumn(5).setPreferredWidth(80);
        
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        // Wrap the JTable inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table1);

        // Add the JScrollPane to the JFrame
        panel_1.add(scrollPane);
        scrollPane.setBounds(10, 298, 631, 139);
    }
	
	private void updateComboBoxes() {
		
		// Grade
		try {
			String gradeQuery = "select class_name_number from Classes order by class_name_number asc";
			
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(gradeQuery);
			ResultSet resultSet = statement.executeQuery();
			
			gradeComboBox.removeAllItems();
			
			while (resultSet.next()) {
				gradeComboBox.addItem(resultSet.getString("class_name_number"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		
		// Subject ID
		try {
			String subjectIdQuery = "select subject_id from Subjects order by subject_id asc";
			
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(subjectIdQuery);
			ResultSet resultSet = statement.executeQuery();
			
			subjectIdComboBox.removeAllItems();
			
			while (resultSet.next()) {
				subjectIdComboBox.addItem(resultSet.getString("subject_id"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	// Update the Subject Name Field
	private void updateSubjectNameField(int subjectIdReceiver) {
		String query = "select subject_name from Subjects where subject_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, subjectIdReceiver);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				String subjectName = resultSet.getString("subject_name");
				subjectNameText.setText(subjectName);
			} else {
				subjectNameText.setText("Not Found");
			}
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error faced: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			subjectNameText.setText("Error");
		}
	}
	
	// Find the next Exam ID
	private int getNextExamId() {
		Connection connection = DatabaseHandler.getConnection();
		int nextExamId = -1;
		
		try {
			String query = "select exam_id from RemovedExams order by exam_id asc limit 1";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				nextExamId = resultSet.getInt("exam_id");
				//System.out.println(nextExamId);
			} else {
				// Find the highest ID if no Re-Usable ID's are available
				query = "select coalesce(max(exam_id), 7000) + 1 as next_id from Exams";
				statement = connection.prepareStatement(query);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					nextExamId = resultSet.getInt("next_id");
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextExamId;
	}
	
	// Update the Exams table - table1
	private void updateExamsTable() {
		tableModel1.setRowCount(0);

		String query = "select e.exam_id as exam_id, e.subject_id as subject_id, s.subject_name as subject_name, e.term_name as term_name, e.class_name_number as grade, e.exam_date as exam_date from Exams e join Subjects s on e.subject_id = s.subject_id order by e.exam_id asc";			
			
		Connection connection = DatabaseHandler.getConnection();
		try {			
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				int examId_table = resultSet.getInt("exam_id");
				int subjectId_table = resultSet.getInt("subject_id");
				String termName_table = resultSet.getString("term_name");
				int grade_table = resultSet.getInt("grade");
				String subjectName_table = resultSet.getString("subject_name");
				String examDate_table = resultSet.getString("exam_date");
				
				tableModel1.addRow(new Object[] {examId_table, grade_table, termName_table, subjectId_table, subjectName_table, examDate_table});
			}
			resultSet.close();
			statement.close();
			connection.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
			}
		refreshNextExamId();
	}
	
	// Add an Exam to the database 
	private void insertExams(JComboBox<String> gradeComboBox, JComboBox<String> subjectIdComboBox, JComboBox<String> termComboBox, JDateChooser examDateChooser) {
			
		if (gradeComboBox.getSelectedIndex() == -1 || subjectIdComboBox.getSelectedIndex() == -1 || termComboBox.getSelectedIndex() == -1 || examDateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "One or more required fields are empty.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Formatting
		Integer examId = getNextExamId();
		String subjectIdStr = (String) subjectIdComboBox.getSelectedItem();
		Integer subjectId = Integer.parseInt(subjectIdStr);
		String termName = (String) termComboBox.getSelectedItem();
		String gradeStr = (String) gradeComboBox.getSelectedItem();
		Integer grade = Integer.parseInt(gradeStr);
		
		// Date conversion and assignment
		java.util.Date examDate_Chooser = examDateChooser.getDate();
		Date examDate = new Date(examDate_Chooser.getTime());
			
		String insertQuery = "insert into Exams (exam_id, subject_id, term_name, class_name_number, exam_date) values (?, ?, ?, ?, ?)";

			
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertQuery); 
				
			statement.setInt(1, examId);
			statement.setInt(2, subjectId);
			statement.setString(3, termName);
			statement.setInt(4, grade);
			statement.setDate(5, examDate);
				
			statement.executeUpdate();
			statement.close();
			connection.close();
				
			removeReusedExamId(examId);
				
			JOptionPane.showMessageDialog(null, "Student Added Succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error adding data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
			
		updateExamsTable();
		refreshNextExamId();
		clearFields();
	}
	
	// Remove a Student
	private void removeExam() {
		if (examIdText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter an Exam ID to remove.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		int examId;
			
		try {
			examId = Integer.parseInt(examIdText.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid Exam ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		String deleteQuery = "delete from Exams where exam_id = ?";
		String insertQuery = "insert into RemovedExams (exam_id) values (?)";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
				
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, examId);
			int rowsAffected = deleteStatement.executeUpdate();
				
			if (rowsAffected > 0) {
				PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
				insertStatement.setInt(1, examId);
				insertStatement.executeUpdate();
				insertStatement.close();
					
				JOptionPane.showMessageDialog(null, "Exam removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No exam found with matching ID.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
			}
			
			deleteStatement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error while removing exam: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		
		updateExamsTable();
		refreshNextExamId();
		//insertToRemovedExams();
	}
	
	// Search Exams details to edit or delete
	private void searchExamDetails() {
		if (examIdText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter an Exam ID.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		String query = "select class_name_number as grade, term_name, exam_date, subject_id from Exams where exam_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, Integer.parseInt(examIdText.getText()));
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				gradeComboBox.setSelectedItem(resultSet.getString("grade"));
				termComboBox.setSelectedItem(resultSet.getString("term_name"));
				examDateChooser.setDate(resultSet.getDate("exam_date"));
				subjectIdComboBox.setSelectedItem(resultSet.getString("subject_id"));
			} else {
				JOptionPane.showMessageDialog(null, "No exam found with matching ID", "Not Found", JOptionPane.INFORMATION_MESSAGE);

			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid Exam ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Update existing exam details
	private void updateExamDetails() {
		if (gradeComboBox.getSelectedIndex() == -1 || termComboBox.getSelectedIndex() == -1 || subjectIdComboBox.getSelectedIndex() == -1 || examDateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "One or more required fields are empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// ID Validation
		if (examIdText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter an Exam ID to save changes.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		// Formatting
		Integer examId = getNextExamId();
		String subjectIdStr = (String) subjectIdComboBox.getSelectedItem();
		Integer subjectId = Integer.parseInt(subjectIdStr);
		String termName = (String) termComboBox.getSelectedItem();
		String gradeStr = (String) gradeComboBox.getSelectedItem();
		Integer grade = Integer.parseInt(gradeStr);
		
		// Date conversion and assignment
		java.util.Date examDate_Chooser = examDateChooser.getDate();
		Date examDate = new Date(examDate_Chooser.getTime());
		
		String checkQuery = "select count(*) from Exams where exam_id = ?";
		String updateQuery = "update Exams set subject_id = ?, term_name = ?, exam_date = ?, class_name_number = ? where exam_id = ?";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
				
			PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			checkStatement.setInt(1, examId);
			ResultSet resultSet = checkStatement.executeQuery();
			resultSet.next();
				
			if (resultSet.getInt(1) == 0) {
				JOptionPane.showMessageDialog(null, "No Exam found with ID:" + examId, "Validation Error", JOptionPane.ERROR_MESSAGE);
				resultSet.close();
				checkStatement.close();
				connection.close();
				return;
			}
				
			resultSet.close();
			checkStatement.close();
				
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			
			statement.setInt(1, subjectId);
			statement.setString(2, termName);
			statement.setDate(3, examDate);
			statement.setInt(4, grade);
			statement.setInt(5, examId);
			
			statement.executeUpdate();
			statement.close();
			connection.close();
				
			JOptionPane.showMessageDialog(null, "Successfully updated details of Exam with ID: " + examId, "Success", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		updateExamsTable();
		refreshNextExamId();
		clearFields();
	}
	
	// Update the ID Field
	private void refreshNextExamId() {
		this.cachedNextExamId = getNextExamId();
		examIdTextField.setText(String.valueOf(this.cachedNextExamId));
	}
	
	// Removing of reused Exam Id's
	private void removeReusedExamId(int examId) {
		String deleteQuery = "delete from RemovedExams where exam_id = ?";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteQuery);
				
			statement.setInt(1, examId);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error updating RemovedExams: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Clear all the fields
	private void clearFields() {
		gradeComboBox.setSelectedIndex(-1);
		termComboBox.setSelectedIndex(-1);
		subjectIdComboBox.setSelectedIndex(-1);
		examDateChooser.setDate(null);
		subjectNameText.setText(null);
	}
}