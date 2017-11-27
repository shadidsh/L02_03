package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import assessment.Assessment;
import assessment.SelectedAssessment;
import dao.DbQuestions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class HHCreateTextQuestion extends JFrame {

	private JPanel contentPane;
	private JTextField questionNameField;
	private JTextField questionAnswerField;
	private JCheckBox chckbxLatex;
	private JTextArea questionContentField;
	private JPanel drawingArea;
	
	private Container content = this.getContentPane();
	private JPanel shiftPanel;
	private JButton btnPrev;
	private JFrame frame;
	private JScrollPane scrollPane;
	//private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHCreateTextQuestion frame = new HHCreateTextQuestion();
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
	public HHCreateTextQuestion() {
		SwitchForm sf = new SwitchForm();
		setTitle("HandyHomework - Create Text Question");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 650);
		
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		shiftPanel = new JPanel();
		shiftPanel.setBounds(42, 140, 579, 455);
		contentPane.add(shiftPanel);
		shiftPanel.setLayout(null);
		
		JLabel lblEnterYourQuestion = new JLabel("Enter your question here: ");
		lblEnterYourQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourQuestion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterYourQuestion.setBounds(25, 6, 171, 19);
		shiftPanel.add(lblEnterYourQuestion);
		
		JLabel lblFinalAnswer = new JLabel("Final Answer: ");
		lblFinalAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalAnswer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFinalAnswer.setBounds(25, 184, 93, 19);
		shiftPanel.add(lblFinalAnswer);
		
		questionAnswerField = new JTextField();
		questionAnswerField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		questionAnswerField.setBounds(30, 203, 510, 45);
		questionAnswerField.setColumns(10);
		shiftPanel.add(questionAnswerField);
		questionAnswerField.setName("questionAnswer");
		
		JLabel lblNumberOfMarks = new JLabel("Number of Marks Awarded: ");
		lblNumberOfMarks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumberOfMarks.setBounds(25, 293, 282, 22);
		shiftPanel.add(lblNumberOfMarks);
		
		JSpinner spinMarks = new JSpinner();
		spinMarks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinMarks.setBounds(230, 290, 155, 28);
		shiftPanel.add(spinMarks);
		spinMarks.setName("spin");
		
		JComponent field = ((JSpinner.DefaultEditor) spinMarks.getEditor());
		
		questionContentField = new JTextArea();
		questionContentField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		questionContentField.setBounds(30, 25, 515, 135);
		questionContentField.setWrapStyleWord(true);
		questionContentField.setLineWrap(true);
		shiftPanel.add(questionContentField);
		questionContentField.setName("content");
		
		chckbxLatex = new JCheckBox("Latex");
		chckbxLatex.setBounds(30, 350, 90, 27);
		shiftPanel.add(chckbxLatex);
		chckbxLatex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxLatex.isSelected()) {
					btnPrev.setVisible(true);
					shiftPanel.setBounds(shiftPanel.getX(), shiftPanel.getY() + 100, shiftPanel.getWidth(), shiftPanel.getHeight());
					drawingArea.setVisible(true);
					setBounds(100, 100, 680, 720);
				//	contentPane.setBounds(contentPane.getX(), contentPane.getY() + 185, contentPane.getWidth(), contentPane.getHeight());
				} else {
					btnPrev.setVisible(false);
					shiftPanel.setBounds(shiftPanel.getX(), shiftPanel.getY() - 100, shiftPanel.getWidth(), shiftPanel.getHeight());
					drawingArea.setVisible(false);
					setBounds(100, 100, 680, 700);
				//	contentPane.setBounds(contentPane.getX(), contentPane.getY() - 185, contentPane.getWidth(), contentPane.getHeight());
				//	this.frame .setSize(frame.getWidth(),frame.getHeight() - 185);
				}
			}
		});
		chckbxLatex.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxLatex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnPrev = new JButton("Preview Render");
		btnPrev.setBounds(260, 385, 155, 37);
		shiftPanel.add(btnPrev);
		btnPrev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String questionContent = String.valueOf(questionContentField.getText());
				try {					
				//	drawingArea.setVisible(false);
					//drawingArea.setVisible();
					// create a formula
					TeXFormula formula = new TeXFormula(questionContent);
					
					// render the formla to an icon of the same size as the formula.
					TeXIcon icon = formula
							.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		
					// insert a border 
					icon.setInsets(new Insets(5, 5, 5, 5));
					
					// now create an actual image of the rendered equation
					BufferedImage image = new BufferedImage(icon.getIconWidth(),
							icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
					
					Graphics2D g2 = image.createGraphics();
					g2.setColor(Color.white);
					g2.fillRect(5, 5, icon.getIconWidth(), icon.getIconHeight());
					JLabel jl = new JLabel();
					jl.setForeground(new Color(0, 0, 0));
					icon.paintIcon(jl, g2, 0,0);
					
					// now draw it to the screen
					Graphics g = drawingArea.getGraphics();
					g.clearRect(0, 0, 1000, 1000);
					g.drawImage(image,0,0,null);
				
				}  catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(HHCreateTextQuestion.this, 
							"Latex Parsing error, please entor a valid latex input.");		
				}
			}
		});
		btnPrev.setVisible(false);
		btnPrev.setName("submit");
		btnPrev.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(427, 386, 146, 34);
		shiftPanel.add(btnSubmit);
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.getRootPane().setDefaultButton(btnSubmit);
		btnSubmit.setName("submit");
		
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = String.valueOf(questionNameField.getText());
				String questionContent = String.valueOf(questionContentField.getText());
				String answer = String.valueOf(questionAnswerField.getText());
				int value = (int) (spinMarks.getValue());
				boolean isLat = chckbxLatex.isSelected();
				
				System.out.println("question is :" + questionContent);
				if (name.isEmpty() || questionContent.isEmpty() || answer.isEmpty()) {
					JOptionPane.showMessageDialog(HHCreateTextQuestion.this, "One or more fields are empty.");
				} else if (!SelectedAssessment.isSelected()) {
					JOptionPane.showMessageDialog(HHCreateTextQuestion.this, "No assessment selected.");
				}				
				else {	
					try {
						Assessment as = SelectedAssessment.getAssess();
						DbQuestions dbQuest = new DbQuestions();
						int qid = dbQuest.insertQuestions(as.getAid(), name, questionContent, value, false, isLat);
						dbQuest.insertAnswers(qid,  true,  answer);

						questionContentField.setText("");
						questionNameField.setText("");
						questionAnswerField.setText("");
						spinMarks.setValue(0);

					} catch (NullPointerException e1){
						System.out.println("Could not insert question into database."); 
						e1.printStackTrace();
						JOptionPane.showMessageDialog(HHCreateTextQuestion.this, 
								"Could not save question - please check your connection and try again.");
					}
				}
			}
		});
		
		JLabel lblQuestionName = new JLabel("Question Name:");
		lblQuestionName.setBounds(64, 74, 107, 19);
		contentPane.add(lblQuestionName);
		lblQuestionName.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuestionName.setVerticalAlignment(SwingConstants.TOP);
		lblQuestionName.setLayout(null);
		
		JButton btnCancel = new JButton("\u2190 Back");
		btnCancel.setBounds(32, 16, 124, 28);
		contentPane.add(btnCancel);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setName("back");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 136, 579, 114);
		contentPane.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		drawingArea = new JPanel();
		drawingArea.setBorder(null);
		scrollPane.setViewportView(drawingArea);
		
		questionNameField = new JTextField();
		questionNameField.setBounds(64, 94, 473, 34);
		contentPane.add(questionNameField);
		questionNameField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		questionNameField.setColumns(10);
		questionNameField.setName("questionName");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
				sf.switchForm(frame);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
	    Dimension prefSize = field.getPreferredSize();
	    prefSize = new Dimension(40, prefSize.height);
	    field.setPreferredSize(prefSize);
		

		
		
	}
}
