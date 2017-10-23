package gui;
import db.DbConnection;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class HHSavedQuestionsPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHSavedQuestionsPage frame = new HHSavedQuestionsPage();
					frame.setVisible(true);
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSavedQuestions = new JLabel("Saved Questions");
		lblSavedQuestions.setMaximumSize(new Dimension(100, 30));
		lblSavedQuestions.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblSavedQuestions.setBounds(232, 11, 175, 31);
		contentPane.add(lblSavedQuestions);
		
		JLabel lblNewLabel = new JLabel("There are no saved questions");
		
		Connection conn = DbConnection.getConnection();
		String res = "";
		res = "<html>name question Answer<br>";
			try {
				PreparedStatement stat = conn.prepareStatement("SELECT * FROM sware.textquestions;");
				ResultSet Rs = stat.executeQuery();
				
				while (Rs.next()) {
					res +=  Rs.getString(2) + "," +  Rs.getString(3) + "," + Rs.getString(4) + "<br>";
				}
				
				Rs.close();
				conn.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res += "</html>";
		
			
		// when we can access the data base, check if there is anything in the db, if there is, use jLabel.setText("new Value"); to modify display
		lblNewLabel.setText(res);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 53, 397, 152);
		contentPane.add(lblNewLabel);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(298, 216, 120, 30);
		contentPane.add(btnMainMenu);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HandyHomeworkMainPage().setVisible(true);
			}
		});
	}
}
