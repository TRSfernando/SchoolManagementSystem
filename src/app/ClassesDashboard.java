package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import db.DatabaseHandler;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class ClassesDashboard {

	private JFrame frame;
	private JTextField idForAddingClassField;
	private JTextField idForEditOrRemoveClass;
	private JTextField classTeacherText;
	private JComboBox<String> classTeacherIdComboBox;
	private DefaultTableModel tableModel1;
	private JPanel panel_1;
	private JTextField studentCountText;
	private JComboBox<String> classNameLetterComboBox;
	private JComboBox<String> classNameNumberComboBox; 
	private int cachedNextClassId;

	/**
	 * Create the application.
	 */
	public ClassesDashboard(String name) {
		initialize(name);
		createClassesTable();
		updateClassesTable();
		populateClassTeacherIdComboBox();
		getNextClassId();
	}

	public void show() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name) {
		frame = new JFrame("School Management System: Class Dashboard");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 220, 561);
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ClassesDashboard.class.getResource("/resources/profile2.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 32, 200, 70);
		panel.add(lblNewLabel);
		
		JLabel greeting_classes = new JLabel("Hello, " + name);
		greeting_classes.setHorizontalAlignment(SwingConstants.CENTER);
		greeting_classes.setForeground(Color.WHITE);
		greeting_classes.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		greeting_classes.setBounds(10, 104, 200, 39);
		panel.add(greeting_classes);
		
		JButton teachersBtn_classes = new JButton("Teachers");
		teachersBtn_classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeachersDashboard teachersDashboard = new TeachersDashboard(name);
				System.out.println("ClassesDashboard created TeachersDashboard.");
				teachersDashboard.show();
				frame.setVisible(false);
			}
		});
		teachersBtn_classes.setIcon(null);
		teachersBtn_classes.setForeground(Color.WHITE);
		teachersBtn_classes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		teachersBtn_classes.setBackground(Color.DARK_GRAY);
		teachersBtn_classes.setBounds(10, 210, 200, 30);
		panel.add(teachersBtn_classes);
		
		JButton studentsBtn_classes = new JButton("Students");
		studentsBtn_classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentsDashboard studentsDashboard = new StudentsDashboard(name);
				System.out.println("ClassesDashboard created StudentsDashboard.");
				studentsDashboard.show();
				frame.setVisible(false);
			}
		});
		studentsBtn_classes.setIcon(null);
		studentsBtn_classes.setForeground(Color.WHITE);
		studentsBtn_classes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentsBtn_classes.setBackground(Color.DARK_GRAY);
		studentsBtn_classes.setBounds(10, 251, 200, 30);
		panel.add(studentsBtn_classes);
		
		JButton subjectsBtn_classes = new JButton("Subjects");
		subjectsBtn_classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectsDashboard subjectsDashboard = new SubjectsDashboard(name);
				System.out.println("ClassesDashboard created SubjectsDashboard.");
				subjectsDashboard.show();
				frame.setVisible(false);
			}
		});
		subjectsBtn_classes.setIcon(null);
		subjectsBtn_classes.setForeground(Color.WHITE);
		subjectsBtn_classes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectsBtn_classes.setBackground(Color.DARK_GRAY);
		subjectsBtn_classes.setBounds(10, 292, 200, 30);
		panel.add(subjectsBtn_classes);
		
		JButton resultsBtn_classes = new JButton("Results");
		resultsBtn_classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultsDashboard resultsDashboard = new ResultsDashboard(name);
				System.out.println("ClassesDashboard created ResultsDashboard.");
				resultsDashboard.show();
				frame.setVisible(false);
			}
		});
		resultsBtn_classes.setIcon(null);
		resultsBtn_classes.setForeground(Color.WHITE);
		resultsBtn_classes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		resultsBtn_classes.setBackground(Color.DARK_GRAY);
		resultsBtn_classes.setBounds(10, 415, 200, 30);
		panel.add(resultsBtn_classes);
		
		JButton examsBtn_classes = new JButton("Exams");
		examsBtn_classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExamsDashboard examsDashboard = new ExamsDashboard(name);
				System.out.println("ClassesDashboard created ExamsDashboard.");
				examsDashboard.show();
				frame.setVisible(false);
			}
		});
		examsBtn_classes.setIcon(null);
		examsBtn_classes.setForeground(Color.WHITE);
		examsBtn_classes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		examsBtn_classes.setBackground(Color.DARK_GRAY);
		examsBtn_classes.setBounds(10, 374, 200, 30);
		panel.add(examsBtn_classes);
		
		JButton homeBtn_classes = new JButton("Home");
		homeBtn_classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainDashboard mainDashboard = new MainDashboard(name);
				System.out.println("ClassesDashboard created MainDashboard.");
				mainDashboard.show();
				frame.setVisible(false);
			}
		});
		homeBtn_classes.setIcon(new ImageIcon(ClassesDashboard.class.getResource("/resources/home2.png")));
		homeBtn_classes.setForeground(Color.WHITE);
		homeBtn_classes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		homeBtn_classes.setBackground(Color.DARK_GRAY);
		homeBtn_classes.setBounds(44, 154, 132, 39);
		panel.add(homeBtn_classes);
		
		JButton logOutBtn_classes = new JButton("");
		logOutBtn_classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginWindow = new Login();
				System.out.println("ClassesDashboard created Login.");
				loginWindow.show();
				frame.setVisible(false);
			}
		});
		logOutBtn_classes.setIcon(new ImageIcon(ClassesDashboard.class.getResource("/resources/logout.png")));
		logOutBtn_classes.setBackground(Color.DARK_GRAY);
		logOutBtn_classes.setBounds(170, 510, 40, 40);
		panel.add(logOutBtn_classes);
		
		JButton classesBtn_classes = new JButton("Classes");
		classesBtn_classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassesDashboard classesDashboard = new ClassesDashboard(name);
				System.out.println("ClassesDashboard created ClassesDashboard.");
				classesDashboard.show();
				frame.setVisible(false);
			}
		});
		classesBtn_classes.setIcon(null);
		classesBtn_classes.setForeground(Color.WHITE);
		classesBtn_classes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classesBtn_classes.setBackground(Color.DARK_GRAY);
		classesBtn_classes.setBounds(10, 333, 200, 30);
		panel.add(classesBtn_classes);
		
		JLabel lblNewLabel_2 = new JLabel("Manage Classes");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(230, 11, 694, 70);
		frame.getContentPane().add(lblNewLabel_2);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(253, 81, 651, 448);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Add a class");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 300, 30);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Class Name");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 93, 135, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		idForAddingClassField = new JTextField("0");
		idForAddingClassField.setToolTipText("Teacher ID - Automatically Assigned");
		idForAddingClassField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idForAddingClassField.setEditable(false);
		idForAddingClassField.setColumns(10);
		idForAddingClassField.setBounds(52, 52, 79, 30);
		panel_1.add(idForAddingClassField);
		
		JLabel lblNewLabel_1_2 = new JLabel("Class Details");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(10, 218, 631, 30);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Edit or remove a class");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(341, 11, 300, 30);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("ID");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(351, 52, 38, 30);
		panel_1.add(lblNewLabel_1_1_2);
		
		idForEditOrRemoveClass = new JTextField();
		idForEditOrRemoveClass.setToolTipText("Enter the Teacher ID to edit or remove details");
		idForEditOrRemoveClass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idForEditOrRemoveClass.setColumns(10);
		idForEditOrRemoveClass.setBounds(393, 52, 89, 30);
		panel_1.add(idForEditOrRemoveClass);
		
		JButton searchClassButton = new JButton("Search Details");
		searchClassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchClassDetails();
			}
		});
		searchClassButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		searchClassButton.setBackground(Color.LIGHT_GRAY);
		searchClassButton.setBounds(490, 52, 151, 30);
		panel_1.add(searchClassButton);
		
		JButton removeClassButton = new JButton("Remove");
		removeClassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the class with ID: " + idForEditOrRemoveClass.getText(), "Confirm Removal?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        		
        		if (response == JOptionPane.YES_OPTION) {
        			removeClass();
        			populateClassTeacherIdComboBox();
        			//System.out.println("Remove: " + getNextClassId());
        		} else {
        			return;
        		}
			}
		});
		removeClassButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		removeClassButton.setBackground(Color.LIGHT_GRAY);
		removeClassButton.setBounds(341, 88, 141, 30);
		panel_1.add(removeClassButton);
		
		JButton clearClassButton = new JButton("Clear Fields");
		clearClassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		clearClassButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		clearClassButton.setBackground(Color.LIGHT_GRAY);
		clearClassButton.setBounds(341, 124, 300, 30);
		panel_1.add(clearClassButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(320, 14, 5, 150);
		panel_1.add(panel_2);
		
		JButton btnAddClass = new JButton("Add Class");
		btnAddClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertClass(classNameNumberComboBox, classNameLetterComboBox, classTeacherIdComboBox);
				populateClassTeacherIdComboBox();
			}
		});
		btnAddClass.setForeground(Color.WHITE);
		btnAddClass.setToolTipText("Save Teacher");
		btnAddClass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAddClass.setBackground(Color.DARK_GRAY);
		btnAddClass.setBounds(141, 52, 157, 30);
		panel_1.add(btnAddClass);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.DARK_GRAY);
		panel_2_1.setBounds(320, 160, 320, 5);
		panel_1.add(panel_2_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Class Teacher ID");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 136, 122, 30);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Class Teacher");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(10, 177, 122, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		classTeacherText = new JTextField();
		classTeacherText.setEditable(false);
		classTeacherText.setToolTipText("Enter your first name");
		classTeacherText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classTeacherText.setColumns(10);
		classTeacherText.setBounds(141, 177, 266, 30);
		panel_1.add(classTeacherText);
		
		classTeacherIdComboBox = new JComboBox<>();
		classTeacherIdComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedTeacherIdStr = (String) classTeacherIdComboBox.getSelectedItem();
				
				if (selectedTeacherIdStr == null) {
					return;
				}
		        
		        try {
		            int selectedTeacherId = Integer.parseInt(selectedTeacherIdStr);
		            updateClassTeacherNameField(selectedTeacherId);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		classTeacherIdComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classTeacherIdComboBox.setBounds(141, 134, 157, 30);
		panel_1.add(classTeacherIdComboBox);
		frame.setBounds(100, 100, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    JLabel studentCountLabel = new JLabel("Student Count");
	    studentCountLabel.setHorizontalAlignment(SwingConstants.LEFT);
	    studentCountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    studentCountLabel.setBounds(431, 177, 122, 30);
	    panel_1.add(studentCountLabel);
	    
	    studentCountText = new JTextField();
	    studentCountText.setToolTipText("Student Count - Automatically Assigned");
	    studentCountText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    studentCountText.setEditable(false);
	    studentCountText.setColumns(10);
	    studentCountText.setBounds(562, 177, 79, 30);
	    panel_1.add(studentCountText);
	    
	    classNameNumberComboBox = new JComboBox<String>();
	    classNameNumberComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"}));
	    classNameNumberComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    classNameNumberComboBox.setBounds(141, 93, 74, 30);
	    panel_1.add(classNameNumberComboBox);
	    
	    classNameLetterComboBox = new JComboBox<String>();
	    classNameLetterComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}));
	    classNameLetterComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    classNameLetterComboBox.setBounds(224, 93, 74, 30);
	    panel_1.add(classNameLetterComboBox);
	}
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------- Other Methods ----------------------------------------------------------
	
	// Find the next Student ID
	private int getNextClassId() {
		Connection connection = DatabaseHandler.getConnection();
		int nextClassId = -1;
		
		try {
			String query = "select class_id from RemovedClasses order by class_id asc limit 1";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				nextClassId = resultSet.getInt("class_id");
				//System.out.println(nextClassId);
			} else {
				// Find the highest ID if no Re-Usable ID's are available
				query = "select coalesce(max(class_id), 7000) + 1 as next_id from Classes";
				statement = connection.prepareStatement(query);
				resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					nextClassId = resultSet.getInt("next_id");
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextClassId;
	}
	
	// Create the Classes table - table1
	private void createClassesTable() {
		// Init of the Table for displaying the Classes information - table1
		// Create a tablemodel - tableModel1
		tableModel1 = new DefaultTableModel();
		tableModel1.addColumn("ID");
		tableModel1.addColumn("Class Name");
		tableModel1.addColumn("Class Teacher ID");
		tableModel1.addColumn("Class Teacher");
			
		// Create a JTable with the model
	    JTable table1 = new JTable(tableModel1);
	    table1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    table1.getColumnModel().getColumn(0).setPreferredWidth(20);
	    table1.getColumnModel().getColumn(1).setPreferredWidth(40);
	    table1.getColumnModel().getColumn(2).setPreferredWidth(40);
	    table1.getColumnModel().getColumn(3).setPreferredWidth(200);
	        
	    table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	        
	    // Wrap the JTable inside a JScrollPane
	    JScrollPane scrollPane = new JScrollPane(table1);

	    // Add the JScrollPane to the JFrame
	    panel_1.add(scrollPane);
	    scrollPane.setBounds(10, 259, 631, 178);
	    
	    JButton btnSaveChanges = new JButton("Save Changes");
	    btnSaveChanges.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		updateClassDetails();
	    	}
	    });
	    btnSaveChanges.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    btnSaveChanges.setBackground(Color.LIGHT_GRAY);
	    btnSaveChanges.setBounds(490, 88, 151, 30);
	    panel_1.add(btnSaveChanges);
	}
	
	// Update the Classes table - table1
	private void updateClassesTable() {
		tableModel1.setRowCount(0);

		String query = "select c.class_id, concat(c.class_name_number, c.class_name_letter) as class_name, c.classteacher_id, concat(t.fname, ' ', t.lname) as teacher_name from Classes as c join Teachers as t on c.classteacher_id = t.teacher_id order by class_id asc";			
			
		Connection connection = DatabaseHandler.getConnection();
		try {			
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				int classId_table = resultSet.getInt("class_id");
				String className_table = resultSet.getString("class_name");
				String classTeacherId_table = resultSet.getString("classteacher_id");
				String teacherName_table = resultSet.getString("teacher_name");
				
				tableModel1.addRow(new Object[] {classId_table, className_table, classTeacherId_table, teacherName_table});
			}
			resultSet.close();
			statement.close();
			connection.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
			}
		refreshNextClassId();
	}
	
	// Update the ID Field
	private void refreshNextClassId() {
		this.cachedNextClassId = getNextClassId();
		idForAddingClassField.setText(String.valueOf(this.cachedNextClassId));
	}
	
	// Populate the comboBox
	private void populateClassTeacherIdComboBox() {
		try {
			String query = "select t.teacher_id from Teachers as t where not exists (select 1 from  Classes as c where c.classteacher_id = teacher_id)";
			
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			classTeacherIdComboBox.removeAllItems();
			
			while (resultSet.next()) {
				classTeacherIdComboBox.addItem(resultSet.getString("teacher_id"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Update the student count field
	private void updateStudentCount(JComboBox<String> classNameNumberComboBox, JComboBox<String> classNameLetterComboBox) {
	    String query = "select count(student_id) as student_count from Students as s join Classes as c on s.class_name_letter = c.class_name_letter and s.class_name_number = c.class_name_number where c.class_id = ?";

	    if (classNameNumberComboBox.getSelectedIndex() == -1 || classNameLetterComboBox.getSelectedIndex() == -1) {
	        studentCountText.setText(""); 
	        return;
	    }

	    String classIdFieldSC = idForEditOrRemoveClass.getText();
	    //String classNumber = (String) classNameNumberComboBox.getSelectedItem();
	    //System.out.println(classNumber);
	    //String classLetter = (String) classNameLetterComboBox.getSelectedItem();
	    //System.out.println(classLetter);

	    try (Connection connection = DatabaseHandler.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setInt(1, Integer.parseInt(classIdFieldSC)); 
	        //statement.setString(2, classLetter);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                int count = resultSet.getInt("student_count");
	                studentCountText.setText(String.valueOf(count)); 
	            } else {
	                studentCountText.setText("0"); 
	            }
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	// Add a Class to the database
	private void insertClass(JComboBox<String> classNameNumberComboBox, JComboBox<String> classNameLetterComboBox, JComboBox<String> classTeacherIdComboBox) {
	    if (classNameNumberComboBox.getSelectedItem() == null || classNameLetterComboBox.getSelectedItem() == null || classTeacherIdComboBox.getSelectedItem() == null) {
	        JOptionPane.showMessageDialog(null, "One or more required fields are empty.", "Validation error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    int classId_Field = getNextClassId();
	    String classNameNumber_Field_String = (String) classNameNumberComboBox.getSelectedItem();
	    Integer classNameNumber_Field = Integer.parseInt(classNameNumber_Field_String);
	    String classNameLetter_Field = (String) classNameLetterComboBox.getSelectedItem();
	    String classTeacherId_Field_String = (String) classTeacherIdComboBox.getSelectedItem();
	    Integer classTeacherId_Field = null;

	    try {
	        classTeacherId_Field = Integer.parseInt(classTeacherId_Field_String);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Class Teacher ID must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
	        return; 
	    }

	    String checkQuery = "select count(class_id) from Classes where class_name_number = ? and class_name_letter = ?";
	    String insertQuery = "insert into Classes (class_id, classteacher_id, class_name_number, class_name_letter) values (?, ?, ?, ?)";

	    try {
	        Connection connection = DatabaseHandler.getConnection();
	        
	        // Check
	        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	        checkStatement.setInt(1, classNameNumber_Field);
	        checkStatement.setString(2, classNameLetter_Field);
	        ResultSet resultSet = checkStatement.executeQuery();
	        resultSet.next();
	        int count = resultSet.getInt(1); 
	        resultSet.close();
	        checkStatement.close();
	        
	        if (count > 0) {
	            JOptionPane.showMessageDialog(null, "This class already exists.", "Duplicate Class Error", JOptionPane.ERROR_MESSAGE);
	            connection.close();
	            return; 
	        }
	        
	        // Insert
	        PreparedStatement statement = connection.prepareStatement(insertQuery);

	        statement.setInt(1, classId_Field);
	        statement.setInt(2, classTeacherId_Field);
	        statement.setInt(3, classNameNumber_Field);
	        statement.setString(4, classNameLetter_Field);

	        statement.executeUpdate();
	        statement.close();
	        connection.close();

	        removeReusedClassId(classId_Field);

	        JOptionPane.showMessageDialog(null, "Class Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error adding data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
	    }

	    updateClassesTable();
	    refreshNextClassId();
	    clearFields();
	}
	
	// Remove a Class
	private void removeClass() {
		if (idForEditOrRemoveClass.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Student ID to remove.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		int classId;
			
		try {
			classId = Integer.parseInt(idForEditOrRemoveClass.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid Class ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		String deleteQuery = "delete from Classes where class_id = ?";
		String insertQuery = "insert into RemovedClasses (class_id) values (?)";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
				
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, classId);
			int rowsAffected = deleteStatement.executeUpdate();
				
			if (rowsAffected > 0) {
				PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
				insertStatement.setInt(1, classId);
				insertStatement.executeUpdate();
				insertStatement.close();
					
				JOptionPane.showMessageDialog(null, "Class removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No class found with matching ID.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
			}
			
			deleteStatement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error while removing student: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		
		updateClassesTable();
		refreshNextClassId();
	}
	
	// Update existing student details
	private void updateClassDetails() {
		if (classNameNumberComboBox.getSelectedItem() == null || classNameLetterComboBox.getSelectedItem() == null || classTeacherIdComboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "One or more required fields are empty.", "Validation error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// ID Validation
		if (idForEditOrRemoveClass.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter a Class ID to save changes.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int classId_Field = Integer.parseInt(idForEditOrRemoveClass.getText());
		int classNameNumber_Field = Integer.parseInt((String) classNameNumberComboBox.getSelectedItem());
		String classNameLetter_Field = (String) classNameLetterComboBox.getSelectedItem();
		int classTeacherId_Field = (int) classTeacherIdComboBox.getSelectedItem();
		
		String checkQuery = "select count(*) from Classes where class_id = ?";		
		String duplicateCheckQuery = "select count(*) from Classes where class_name_number = ? and class_name_letter = ?";
		String updateQuery = "update Classes set class_name_number = ?, class_name_letter = ?, classteacher_id = ? where class_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
				
			// Check if class is available
			PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			checkStatement.setInt(1, classId_Field);
			ResultSet resultSet = checkStatement.executeQuery();
			resultSet.next();
				
			if (resultSet.getInt(1) == 0) {
				JOptionPane.showMessageDialog(null, "No Class found with ID:" + classId_Field, "Validation Error", JOptionPane.ERROR_MESSAGE);
				resultSet.close();
				checkStatement.close();
				connection.close();
				return;
			}
				
			resultSet.close();
			checkStatement.close();
				
			// Check for duplicate classes
			PreparedStatement duplicateCheckStatement = connection.prepareStatement(duplicateCheckQuery);
			duplicateCheckStatement.setInt(1, classNameNumber_Field);
			duplicateCheckStatement.setString(2, classNameLetter_Field);
			ResultSet duplicateResult = duplicateCheckStatement.executeQuery();
			duplicateResult.next();
			
			if (duplicateResult.getInt(1) > 0) {
	            JOptionPane.showMessageDialog(null, "This class already exists", "Duplicate Class Error", JOptionPane.ERROR_MESSAGE);
	            duplicateResult.close();
	            duplicateCheckStatement.close();
	            connection.close();
	            return;
	        }
			
			duplicateResult.close();
		    duplicateCheckStatement.close();
			
		    // Update class details
			PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setInt(1, classNameNumber_Field);
			updateStatement.setString(2, classNameLetter_Field);
			updateStatement.setInt(3, classTeacherId_Field);
			updateStatement.setInt(4, classId_Field);
			
			updateStatement.executeUpdate();
			updateStatement.close();
			connection.close();
				
			JOptionPane.showMessageDialog(null, "Successfully updated details of Class with ID: " + classId_Field, "Success", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		updateClassesTable();
		refreshNextClassId();
		clearFields();
	}
		
	// Removing of reused Class Id's
	private void removeReusedClassId(int classId) {
		String deleteQuery = "delete from RemovedClasses where class_id = ?";
			
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteQuery);
				
			statement.setInt(1, classId);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error updating RemovedClasses: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Search Class details to edit or delete
	private void searchClassDetails() {
		if (idForEditOrRemoveClass.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Class ID.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		String query = "select * from Classes where class_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, Integer.parseInt(idForEditOrRemoveClass.getText()));
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				classNameNumberComboBox.setSelectedItem(resultSet.getString("class_name_number"));
				classNameLetterComboBox.setSelectedItem(resultSet.getString("class_name_letter"));
				classTeacherIdComboBox.setSelectedItem(resultSet.getInt("classteacher_id"));
			} else {
				JOptionPane.showMessageDialog(null, "No class found with matching ID", "Not Found", JOptionPane.INFORMATION_MESSAGE);

			}
			resultSet.close();
			statement.close();
			connection.close();
			updateStudentCount(classNameNumberComboBox, classNameLetterComboBox);
		} catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid Class ID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error obtaining data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Update the Teacher Name Field
	private void updateClassTeacherNameField(int teacherIdReceiver) {
		String query = "select concat(fname, ' ', lname) as teacher_name from Teachers where teacher_id = ?";
		
		try {
			Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, teacherIdReceiver);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				String teacherName = resultSet.getString("teacher_name");
				classTeacherText.setText(teacherName);
			} else {
				classTeacherText.setText("Not Found");
			}
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error faced: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			classTeacherText.setText("Error");
		}
	}
	
	// Clear all the fields
	private void clearFields() {
		classNameNumberComboBox.setSelectedIndex(-1);
		classNameLetterComboBox.setSelectedIndex(-1);
		classTeacherIdComboBox.setSelectedIndex(-1);
		classTeacherText.setText(null);
		studentCountText.setText(null);
		idForEditOrRemoveClass.setText(null);
	}
}