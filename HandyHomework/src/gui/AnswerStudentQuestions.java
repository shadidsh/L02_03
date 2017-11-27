package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import answer.TextAnswer;
import assessment.Assessment;
import assessment.SelectedAssessment;
import dao.DbQuestions;
import login.SelectedUser;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import question.MultQuestion;
import question.Question;
import question.TextQuestion;

import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JScrollPane;

public class AnswerStudentQuestions extends JFrame {

	private JPanel contentPane;
	private JFrame frame;	
	private TextQuestion tq;
	private int totalPts = 0;
	private boolean nextQ = true;	
	private JPanel drawingArea;
	private Container content = this.getContentPane();
	private JButton btnParse;	
	private JLabel lblQuestions;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnswerStudentQuestions frame = new AnswerStudentQuestions();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Initialize the contents of the frame.
	 */
	public AnswerStudentQuestions() {
		SwitchForm sf = new SwitchForm();
		setTitle("HandyHomework - Text Question");
		//frame = new JFrame();
	    setBounds(300, 300, 853, 564);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		//lblQuestions.setText(null);
		
		JTextArea txtAns = new JTextArea();
		txtAns.setName("Ans");
		txtAns.setLineWrap(true);
		txtAns.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAns.setBounds(110, 271, 623, 170);
		getContentPane().add(txtAns);
		
		JButton btnback = new JButton(" Back");
		btnback.setName("back");
		btnback.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnback.setBounds(12, 454, 141, 49);
		getContentPane().add(btnback);
		
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				HHSavedAssessments frame = new HHSavedAssessments();
				sf.switchForm(frame);
				if (frame.isShowing()){
					dispose();
				}
			}
		});
		
		JLabel lblPointsWorth = new JLabel("");
		lblPointsWorth.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointsWorth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPointsWorth.setBounds(547, 0, 238, 72);
		getContentPane().add(lblPointsWorth);
		
		JLabel lblQuestName = new JLabel("");
		lblQuestName.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuestName.setBounds(0, 0, 238, 72);
		getContentPane().add(lblQuestName);
		
		lblQuestions = new JLabel("");
		lblQuestions.setBounds(47, 91, 755, 40);
		contentPane.add(lblQuestions);
		lblQuestions.setName("");
		lblQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestions.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		drawingArea = new JPanel();
		drawingArea.setBounds(151, 168, 493, 81);
		contentPane.add(drawingArea);
		drawingArea.setVisible(true);
		
		if (!SelectedAssessment.isSelected()) {
			JOptionPane.showMessageDialog(AnswerStudentQuestions.this, "Asssessment not selected");
			HHLogin frame = new HHLogin();
			sf.switchForm(frame);
			if (frame.isShowing()){
				dispose();
			}
		}
		int aid = SelectedAssessment.getAssess().getAid();
		DbQuestions dbQuest = new DbQuestions();
		List<TextQuestion> textQuests = dbQuest.TextQuestions(aid);
		ListIterator<TextQuestion>  textQ = textQuests.listIterator();
		
		if (!textQ.hasNext()) {
			JOptionPane.showMessageDialog(AnswerStudentQuestions.this, 
					"There are no questions for this assessment");
			HHSavedAssessments frame = new HHSavedAssessments();
			sf.switchForm(frame);
			if (frame.isShowing()){
				dispose();
			}
		}


		
		JButton btnSubmit = new JButton("Submit Answer");
		btnSubmit.setName("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String answer = String.valueOf(txtAns.getText());				
				if (answer.isEmpty()) {
					JOptionPane.showMessageDialog(AnswerStudentQuestions.this, "Answer field is empty.");
				} else if (tq == null) {
					JOptionPane.showMessageDialog(AnswerStudentQuestions.this, ".");
				} else {
					DbQuestions dbQ = new DbQuestions();
					if (!tq.hasAnswer()) {
						JOptionPane.showMessageDialog(AnswerStudentQuestions.this, 
								"ERROR in the Database - There are no answers for this question");
						HHSavedAssessments frame = new HHSavedAssessments();
						sf.switchForm(frame);
						if (frame.isShowing()){
							dispose();
						}
					}					
					TextAnswer ta = tq.getCorrectAnswer();
					if (ta.isCorrect(answer.toString())) {
						totalPts += tq.getPoints();
					} 
					
					int aid = SelectedAssessment.getAssess().getAid();					
					if (!textQ.hasNext()) {
						System.out.println(totalPts);
						if (dbQ.hasMultChoice(aid)) {
							AnswerMultipleChoice frame = new AnswerMultipleChoice();
							sf.switchForm(frame);
							if (frame.isShowing()){
								dispose();
							}
						} else {
							//DUPLICATE COOOOODE!!!!!!!!
							JOptionPane.showMessageDialog(
									AnswerStudentQuestions.this, "Finished assessment, points earned: " + totalPts);
							HHSavedAssessments frame = new HHSavedAssessments();
							sf.switchForm(frame);
							if (frame.isShowing()){
								dispose();
							}
						}						
					} else {
						tq = textQ.next();
						lblQuestName.setText("Name: " + tq.getName());
						lblPointsWorth.setText("Points: " + new Integer(tq.getPoints()).toString() );
						lblQuestions.setText("Questions: " + tq.getQuestion());
						if (!tq.isLat()) {
							btnParse.setVisible(false);
						} else {
							btnParse.setVisible(true);
							//render(tq.getQuestion(), frame);
						}
					}
				}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(652, 454, 171, 52);
		contentPane.add(btnSubmit);
		
		btnParse = new JButton(" Parse As Latex");
		btnParse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			//	if (tq.isLat())  {
					try {					
						// create a formula
						TeXFormula formula = new TeXFormula(tq.getQuestion());
						
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
						//g.clearRect(0, 0, 20, 20);
						//g.drawImage(image,0,0,null);
						g.drawImage(image,0,0,null);
						
				}  catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame, 
							"Latex Parsing error, please entor a valid latex input.");		
				}
	//		}
			}
		});
		btnParse.setName("ParseAsLatex");
		btnParse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnParse.setBounds(252, 454, 284, 49);
		contentPane.add(btnParse);
		
		tq = textQ.next();
		if (!tq.hasAnswer()) {
			JOptionPane.showMessageDialog(AnswerStudentQuestions.this, 
					"There are no answers for some questions in this assessment, please select another");
			HHSavedAssessments frame = new HHSavedAssessments();
			sf.switchForm(frame);
			if (frame.isShowing()){
				dispose();
			}
		}
		
		lblQuestName.setText("Name: " + tq.getName());
		lblPointsWorth.setText("Points: " + new Integer(tq.getPoints()).toString() );
		lblQuestions.setText("Questions: " + tq.getQuestion());
		
		if (!tq.isLat()) {
			btnParse.setVisible(false);
		} else {
			btnParse.setVisible(true);
		}
	}
	
}
