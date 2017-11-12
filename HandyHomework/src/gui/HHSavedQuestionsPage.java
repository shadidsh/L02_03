package gui;
import db.DbConnection;
import question.TextQuestion;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;

import answer.TextAnswer;
import assessment.Assessment;
import assessment.SharedAssessment;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.DropMode;

public class HHSavedQuestionsPage extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JList listQuestion_1;
	private JTextField questionAnswerField;
	private TextAnswer questAnswer;
	
	private TextQuestion selQuestion;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HHSavedQuestionsPage() {
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea lblAssessmentName = new JTextArea("Saved Questions");
		lblAssessmentName.setEditable(false);
		lblAssessmentName.setWrapStyleWord(true);
		lblAssessmentName.setLineWrap(true);
		lblAssessmentName.setBackground(SystemColor.window);
		lblAssessmentName.setMaximumSize(new Dimension(100, 30));
		lblAssessmentName.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
		lblAssessmentName.setBounds(138, 30, 376, 40);
		contentPane.add(lblAssessmentName);
		
		JPanel panel = new JPanel();
		panel.setBounds(229, 84, 307, 152);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{307, 0};
		gbl_panel.rowHeights = new int[]{30, 49, 57, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel questionTitle = new JLabel("");
		GridBagConstraints gbc_questionTitle = new GridBagConstraints();
		gbc_questionTitle.fill = GridBagConstraints.BOTH;
		gbc_questionTitle.insets = new Insets(0, 0, 5, 0);
		gbc_questionTitle.gridx = 0;
		gbc_questionTitle.gridy = 0;
		panel.add(questionTitle, gbc_questionTitle);
		questionTitle.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JTextArea questionText = new JTextArea("Select a Question");
		questionText.setWrapStyleWord(true);
		questionText.setEditable(false);
		questionText.setBackground(SystemColor.window);
		questionText.setLineWrap(true);
		
		//lblQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_questionText = new GridBagConstraints();
		gbc_questionText.anchor = GridBagConstraints.WEST;
		gbc_questionText.insets = new Insets(0, 0, 5, 0);
		gbc_questionText.gridx = 0;
		gbc_questionText.gridy = 1;
		questionText.setSize(gbl_panel.columnWidths[0], gbl_panel.rowHeights[1]);
		panel.add(questionText, gbc_questionText);
		JLabel lblPts = new JLabel("");
		GridBagConstraints gbc_lblPts = new GridBagConstraints();
		gbc_lblPts.fill = GridBagConstraints.BOTH;
		gbc_lblPts.gridx = 0;
		gbc_lblPts.gridy = 2;
		panel.add(lblPts, gbc_lblPts);
		lblPts.setAutoscrolls(true);
		lblPts.setVerticalAlignment(SwingConstants.TOP);
		
		Connection conn = DbConnection.getConnection();
		String res = "";
		
		DefaultListModel<String> lstQuestion = new DefaultListModel<>();		
		ArrayList<TextQuestion> questions = new ArrayList<TextQuestion>();
		
		try {
			PreparedStatement stat;
			int aid;
			
			if (SharedAssessment.isSelected()) {				
				//JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Assessment is selected");
				stat = conn.prepareStatement("SELECT * FROM "	
						+ constants.Constants.DataConstants.QUESTIONS + " where aid = ?;");
				Assessment as = SharedAssessment.getAssess();
				stat.setInt(1, as.getAid());
				aid = as.getAid();
				lblAssessmentName.setText(as.getName());
			} else {
				//JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "No Assessments have been selected, displaying question for assessment 3");
				stat = conn.prepareStatement("SELECT * FROM "	
						+ constants.Constants.DataConstants.QUESTIONS + ";");
				aid = 3;
			}
			System.out.println(stat);
			ResultSet Rs = stat.executeQuery();				
			
			while (Rs.next()) {
				
				int qid = Rs.getInt(1);
				String name = Rs.getString(4);
				String questionContent = Rs.getString(5);
				Integer points = new Integer(Rs.getInt(6));
				
				TextQuestion question = new TextQuestion(aid, name, questionContent, points);
				
				ArrayList<TextAnswer> ans = db.DbConnection.answers_for_question(qid);
				question.addList(ans);				
				
				lstQuestion.addElement(question.getName());
				questions.add(question);
					
			}	
			Rs.close();
			conn.close();
				
		} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Could not access database."); 
				JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Could not access database - " + "\nplease check your connection and try again.");
		} catch (NullPointerException e2) {
			e2.printStackTrace();
			System.out.println("Could not access database."); 
			JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Could not access database - " + "\nplease check your connection and try again.");
		}
		JButton btnback = new JButton("Back to Assessments");
		btnback.setBounds(163, 248, 177, 29);
		contentPane.add(btnback);
		
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHSavedAssessments frame = new HHSavedAssessments();
				frame.setVisible(true);		
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		JButton btnView = new JButton("Submit Answer");

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer = String.valueOf(questionAnswerField.getText());
				
				if (selQuestion == null) {
					JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "No questions selected.");
				} else if (answer.isEmpty()) {
					JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Answer field is empty.");
				} else {
					questAnswer = selQuestion.getCorrectAnswer();
					if (questAnswer == null) {
						JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Question doesn't have a corresponding answer.");
					} else {
						if (questAnswer.isCorrect(answer.toString())) {
							JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Correct!");
						} else {
							JOptionPane.showMessageDialog(HHSavedQuestionsPage.this, "Incorrect!");
						}
						
					}
				}				
			}
		});
		btnView.setBounds(277, 307, 120, 31);
		contentPane.add(btnView);		
		questionAnswerField = new JTextField();
		questionAnswerField.setAlignmentY(Component.TOP_ALIGNMENT);
		questionAnswerField.setColumns(10);
		questionAnswerField.setBounds(29, 293, 247, 57);
		contentPane.add(questionAnswerField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 84, 188, 155);
		contentPane.add(scrollPane);
		JList listQuestion = new JList<>(lstQuestion);
		scrollPane.setViewportView(listQuestion);
		
		listQuestion.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String res = "<html>This question is worth</html>";
				//System.out.println(res);
					
				JList list = (JList) e.getSource();
				TextQuestion question = questions.get(list.getSelectedIndex());
					
				questionText.setText(question.getQuestion());	
				questionText.setSize(questionText.getPreferredSize());
				res = "<html>This question is worth <html>" + new Integer(question.getPoints()).toString() + "<html> marks</html>" ;
				lblPts.setText(res);
				questionTitle.setText(question.getName());
				questionTitle.setSize(questionTitle.getPreferredSize());
				
				selQuestion = question;
				//System.out.println(selQuestion.getAssessID());
				//questAnswer = question.getCorrectAnswer();
				/*questAnswer = question.getAnswer(); */
					// need to pass this question into submit button, then check for answer
					
			}
		});
		
		
		listQuestion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnAdd = new JButton("Add Question");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//SharedQuestion.setQuestion(selQuestion);
				HHFormFrame frame = new HHFormFrame();
				frame.setVisible(true);	
				frame.setResizable(false);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		btnAdd.setMaximumSize(new Dimension(139, 23));
		btnAdd.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnAdd.setBounds(29, 247, 136, 31);
		contentPane.add(btnAdd);
		
		
		
		
		
		
	}
}
