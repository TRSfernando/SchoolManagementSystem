package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.toedter.calendar.JCalendar;

import db.DatabaseHandler;

public class MainDashboard {

	private JFrame frame;
	private TeachersDashboard teachersDashboard;
	private Login loginWindow;
	
	private int totalNoOfTeachers;
	private int totalNoOfStudents;
	private int totalNoOfClasses;
	private int totalNoOfUpcomingExams;

	public void show() {
		frame.setVisible(true);
	}
	
	/**
	 * Create the application.
	 */
	public MainDashboard(String name) {
		TotalNoOfTeachers();
		TotalNoOfStudents();
		TotalNoOfClasses();
		TotalNoOfUpcomingExams();
		initialize(name);
	}
	
	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize(String name) {		
		
		frame = new JFrame("School Management System: Main Dashboard");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 220, 561);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MainDashboard.class.getResource("/resources/profile2.png")));
		lblNewLabel.setBounds(10, 32, 200, 70);
		panel.add(lblNewLabel);
		
		JLabel greeting_home = new JLabel("Hello, " + name);
		greeting_home.setForeground(Color.WHITE);
		greeting_home.setHorizontalAlignment(SwingConstants.CENTER);
		greeting_home.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		greeting_home.setBounds(10, 104, 200, 39);
		panel.add(greeting_home);
		
		JButton teachersBtn_home = new JButton("Teachers");
		teachersBtn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teachersDashboard = new TeachersDashboard(name);
				teachersDashboard.show();
				System.out.println("MainDashboard created TeachersDashboard.");
				frame.setVisible(false);
			}
		});
		teachersBtn_home.setForeground(Color.WHITE);
		teachersBtn_home.setIcon(null);
		teachersBtn_home.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		teachersBtn_home.setBackground(Color.DARK_GRAY);
		teachersBtn_home.setBounds(10, 210, 200, 30);
		panel.add(teachersBtn_home);
		
		JButton studentsBtn_home = new JButton("Students");
		studentsBtn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentsDashboard studentsDashboard = new StudentsDashboard(name);
				System.out.println("MainDashboard created StudentsDashboard.");
				studentsDashboard.show();
				frame.setVisible(false);
			}
		});
		studentsBtn_home.setForeground(Color.WHITE);
		studentsBtn_home.setIcon(null);
		studentsBtn_home.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentsBtn_home.setBackground(Color.DARK_GRAY);
		studentsBtn_home.setBounds(10, 251, 200, 30);
		panel.add(studentsBtn_home);
		
		JButton subjectsBtn_home = new JButton("Subjects");
		subjectsBtn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectsDashboard subjectsDashboard = new SubjectsDashboard(name);
				System.out.println("MainDashboard created SubjectsDashboard.");
				subjectsDashboard.show();
				frame.setVisible(false);
			}
		});
		subjectsBtn_home.setForeground(Color.WHITE);
		subjectsBtn_home.setIcon(null);
		subjectsBtn_home.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subjectsBtn_home.setBackground(Color.DARK_GRAY);
		subjectsBtn_home.setBounds(10, 292, 200, 30);
		panel.add(subjectsBtn_home);
		
		JButton resultsBtn_home = new JButton("Results");
		resultsBtn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultsDashboard resultsDashboard = new ResultsDashboard(name);
				System.out.println("MainDashboard created ResultsDashboard.");
				resultsDashboard.show();
				frame.setVisible(false);
			}
		});
		resultsBtn_home.setForeground(Color.WHITE);
		resultsBtn_home.setIcon(null);
		resultsBtn_home.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		resultsBtn_home.setBackground(Color.DARK_GRAY);
		resultsBtn_home.setBounds(10, 415, 200, 30);
		panel.add(resultsBtn_home);
		
		JButton examsBtn_home = new JButton("Exams");
		examsBtn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExamsDashboard examsDashboard = new ExamsDashboard(name);
				System.out.println("ExamsDashboard created ExamsDashboard.");
				examsDashboard.show();
				frame.setVisible(false);
			}
		});
		examsBtn_home.setForeground(Color.WHITE);
		examsBtn_home.setIcon(null);
		examsBtn_home.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		examsBtn_home.setBackground(Color.DARK_GRAY);
		examsBtn_home.setBounds(10, 374, 200, 30);
		panel.add(examsBtn_home);
		
		JButton homeBtn_home = new JButton("Home");
		homeBtn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainDashboard mainDashboard = new MainDashboard(name);
				mainDashboard.show();
				System.out.println("MainDashboard created MainDashboard.");
				frame.setVisible(false);
			}
		});
		homeBtn_home.setIcon(new ImageIcon(MainDashboard.class.getResource("/resources/home2.png")));
		homeBtn_home.setForeground(Color.WHITE);
		homeBtn_home.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		homeBtn_home.setBackground(Color.DARK_GRAY);
		homeBtn_home.setBounds(44, 154, 132, 39);
		panel.add(homeBtn_home);
		
		JButton logOutBtn_home = new JButton("");
		logOutBtn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginWindow = new Login();
				loginWindow.show();
				System.out.println("MainDashboard created Login.");
				frame.setVisible(false);
			}
		});
		logOutBtn_home.setBackground(Color.DARK_GRAY);
		logOutBtn_home.setIcon(new ImageIcon(MainDashboard.class.getResource("/resources/logout.png")));
		logOutBtn_home.setBounds(170, 510, 40, 40);
		panel.add(logOutBtn_home);
		
		JButton classesBtn_home = new JButton("Classes");
		classesBtn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassesDashboard classesDashboard = new ClassesDashboard(name);
				System.out.println("MainDashboard created ClassesDashboard.");
				classesDashboard.show();
				frame.setVisible(false);
			}
		});
		classesBtn_home.setIcon(null);
		classesBtn_home.setForeground(Color.WHITE);
		classesBtn_home.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classesBtn_home.setBackground(Color.DARK_GRAY);
		classesBtn_home.setBounds(10, 333, 200, 30);
		panel.add(classesBtn_home);
		
		JLabel lblNewLabel_2 = new JLabel("Dashboard");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(230, 0, 694, 70);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel totalTeachersPanel = new JPanel();
		totalTeachersPanel.setBounds(253, 81, 186, 124);
		frame.getContentPane().add(totalTeachersPanel);
		totalTeachersPanel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Teachers");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(10, 73, 166, 20);
		totalTeachersPanel.add(lblNewLabel_3);
		
		JLabel totalNoOfTeachersDisplayLabel = new JLabel("" + totalNoOfTeachers);
		totalNoOfTeachersDisplayLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		totalNoOfTeachersDisplayLabel.setBounds(10, 93, 166, 20);
		totalTeachersPanel.add(totalNoOfTeachersDisplayLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setIcon(new ImageIcon(MainDashboard.class.getResource("/resources/teacher.png")));
		lblNewLabel_1.setBounds(10, 11, 166, 70);
		totalTeachersPanel.add(lblNewLabel_1);
		
		JPanel totalClassesPanel = new JPanel();
		totalClassesPanel.setBounds(253, 216, 186, 124);
		frame.getContentPane().add(totalClassesPanel);
		totalClassesPanel.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("Classes");
		lblNewLabel_3_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblNewLabel_3_1.setBounds(10, 73, 166, 20);
		totalClassesPanel.add(lblNewLabel_3_1);
		
		JLabel totalNoOfClassesDisplayLabel = new JLabel("" + totalNoOfClasses);
		totalNoOfClassesDisplayLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		totalNoOfClassesDisplayLabel.setBounds(10, 93, 166, 20);
		totalClassesPanel.add(totalNoOfClassesDisplayLabel);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(MainDashboard.class.getResource("/resources/class.png")));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(10, 0, 166, 81);
		totalClassesPanel.add(lblNewLabel_5);
		
		JPanel totalStudentsPanel = new JPanel();
		totalStudentsPanel.setBounds(449, 81, 186, 124);
		frame.getContentPane().add(totalStudentsPanel);
		totalStudentsPanel.setLayout(null);
		
		JLabel lblNewLabel_3_2 = new JLabel("Students");
		lblNewLabel_3_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblNewLabel_3_2.setBounds(10, 73, 166, 20);
		totalStudentsPanel.add(lblNewLabel_3_2);
		
		JLabel totalNoOfStudentsDisplayLabel = new JLabel("" + totalNoOfStudents);
		totalNoOfStudentsDisplayLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		totalNoOfStudentsDisplayLabel.setBounds(10, 93, 166, 20);
		totalStudentsPanel.add(totalNoOfStudentsDisplayLabel);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(MainDashboard.class.getResource("/resources/student.png")));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(10, 0, 166, 82);
		totalStudentsPanel.add(lblNewLabel_4);
		
		JPanel upcomingExamsPanel = new JPanel();
		upcomingExamsPanel.setBounds(449, 216, 186, 124);
		frame.getContentPane().add(upcomingExamsPanel);
		upcomingExamsPanel.setLayout(null);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Upcoming Exams");
		lblNewLabel_3_1_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblNewLabel_3_1_1.setBounds(10, 73, 166, 20);
		upcomingExamsPanel.add(lblNewLabel_3_1_1);
		
		JLabel noOfUpcomingExamsDisplayLabel = new JLabel("" + totalNoOfUpcomingExams);
		noOfUpcomingExamsDisplayLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		noOfUpcomingExamsDisplayLabel.setBounds(10, 93, 166, 20);
		upcomingExamsPanel.add(noOfUpcomingExamsDisplayLabel);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setIcon(new ImageIcon(MainDashboard.class.getResource("/resources/exam.png")));
		lblNewLabel_6.setBounds(10, 0, 166, 70);
		upcomingExamsPanel.add(lblNewLabel_6);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(657, 81, 246, 190);
		frame.getContentPane().add(calendar);
		
		JButton generateReportsBtn_home = new JButton("Generate Reports");
		generateReportsBtn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportWindow reportWindow = new ReportWindow();
				System.out.println("MainDashboard created reportWindow.");
				reportWindow.show();
			}
		});
		generateReportsBtn_home.setIcon(null);
		generateReportsBtn_home.setForeground(Color.WHITE);
		generateReportsBtn_home.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		generateReportsBtn_home.setBackground(Color.DARK_GRAY);
		generateReportsBtn_home.setBounds(678, 292, 200, 37);
		frame.getContentPane().add(generateReportsBtn_home);
		
		JPanel visionPanel = new JPanel();
		visionPanel.setBounds(253, 365, 320, 164);
		frame.getContentPane().add(visionPanel);
		visionPanel.setLayout(null);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("Vision");
		lblNewLabel_3_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_3_1_2.setBounds(10, 11, 300, 20);
		visionPanel.add(lblNewLabel_3_1_2);
		
		JTextPane txtpntoInspireAnd = new JTextPane();
		txtpntoInspireAnd.setEditable(false);
		txtpntoInspireAnd.setBackground(new Color(240, 240, 240));
		txtpntoInspireAnd.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		txtpntoInspireAnd.setText("\"To inspire and empower every student to achieve their full potential, fostering a lifelong passion for learning, critical thinking, and ethical leadership in a rapidly evolving world.\"");
		txtpntoInspireAnd.setBounds(10, 42, 300, 111);
		visionPanel.add(txtpntoInspireAnd);
		
		JPanel missionPanel = new JPanel();
		missionPanel.setBounds(583, 365, 320, 164);
		frame.getContentPane().add(missionPanel);
		missionPanel.setLayout(null);
		
		JLabel lblNewLabel_3_1_2_1 = new JLabel("Mission");
		lblNewLabel_3_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_2_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_3_1_2_1.setBounds(10, 11, 300, 20);
		missionPanel.add(lblNewLabel_3_1_2_1);
		
		JTextPane txtpntoNurtureAnd = new JTextPane();
		txtpntoNurtureAnd.setEditable(false);
		txtpntoNurtureAnd.setText("\"To nurture and empower students to excel academically, grow socially, and lead ethically in a diverse and dynamic world.\"");
		txtpntoNurtureAnd.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		txtpntoNurtureAnd.setBackground(UIManager.getColor("Button.background"));
		txtpntoNurtureAnd.setBounds(10, 42, 300, 111);
		missionPanel.add(txtpntoNurtureAnd);
	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------- Other Methods ----------------------------------------------------------

	// Find the total number of Teachers
	private int TotalNoOfTeachers() {
		Connection connection = DatabaseHandler.getConnection();
		
		if (connection == null) {
			System.out.println("Database connection failed!");
			return totalNoOfTeachers = 0;
		}
		
		try {
			String query = "select count(*) as teacher_count from Teachers";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				totalNoOfTeachers = resultSet.getInt("teacher_count");
				//System.out.println("Teacher Count = " + totalNoOfTeachers);
				return totalNoOfTeachers;
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalNoOfTeachers =  0;
	}
	
	// Find the total number of Students
	private int TotalNoOfStudents() {
		Connection connection = DatabaseHandler.getConnection();
			
		if (connection == null) {
			System.out.println("Database connection failed!");
			return totalNoOfStudents = 0;
		}
			
		try {
			String query = "select count(*) as student_count from Students";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
				
			ResultSet resultSet = preparedStatement.executeQuery();
				
			if (resultSet.next()) {
				totalNoOfStudents = resultSet.getInt("student_count");
				//System.out.println("Student Count = " + totalNoOfStudents);
				return totalNoOfStudents;
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalNoOfStudents =  0;
	}
		
	// Find the total number of Classes
	private int TotalNoOfClasses() {
		Connection connection = DatabaseHandler.getConnection();
			
		if (connection == null) {
			System.out.println("Database connection failed!");
			return totalNoOfClasses = 0;
		}
			
		try {
			String query = "select count(*) as class_count from Classes";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
				
			ResultSet resultSet = preparedStatement.executeQuery();
				
			if (resultSet.next()) {
				totalNoOfClasses = resultSet.getInt("class_count");
				//System.out.println("Class Count = " + totalNoOfClasses);
				return totalNoOfClasses;
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalNoOfClasses =  0;
	}

	// Find the total number of Upcoming Exams
	private int TotalNoOfUpcomingExams() {
		Connection connection = DatabaseHandler.getConnection();
			
		if (connection == null) {
			System.out.println("Database connection failed!");
			return totalNoOfUpcomingExams = 0;
		}
			
		try {
			String query = "select count(*) as upcomingExam_count from Exams where exam_date > (cast(now() as date))";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
				
			ResultSet resultSet = preparedStatement.executeQuery();
				
			if (resultSet.next()) {
				totalNoOfUpcomingExams = resultSet.getInt("upcomingExam_count");
				//System.out.println("Upcoming Exams Count = " + totalNoOfUpcomingExams);
				return totalNoOfUpcomingExams;
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalNoOfUpcomingExams =  0;
	}	
}