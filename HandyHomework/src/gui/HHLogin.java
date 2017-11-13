package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class HHLogin extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel lblUsername;
	private JLabel lblPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HHLogin frame = new HHLogin();
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
	public HHLogin() {
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(125, 50, 250, 25);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(125, 125, 250, 25);
		contentPane.add(passwordField);
		
		lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(215, 25, 75, 20);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("PASSWORD\r\n");
		lblPassword.setBounds(215, 100, 80, 20);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(200, 175, 90, 50);
		JCheckBox chckbxStudent = new JCheckBox("Student");
		chckbxStudent.setBounds(200, 231, 97, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				boolean is_student = chckbxStudent.isSelected();
				String userName = String.valueOf(usernameField.getText());
				String password = String.valueOf(passwordField.getPassword());
				if (password.isEmpty() || userName.isEmpty()) {
					JOptionPane.showMessageDialog(HHLogin.this, "Username and password cannot be empty.");
				}
				else {
					if(is_student) {
						HandyHomeworkMainPage frame = new HandyHomeworkMainPage();
						frame.setVisible(true);
						frame.setResizable(false);
						if (frame.isShowing()){
							dispose();
						}
					}
					else {
						// need to check to make login
						HandyHomeworkMainPage frame = new HandyHomeworkMainPage();
						frame.setVisible(true);
						frame.setResizable(false);
						if (frame.isShowing()){
							dispose();
						}
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnNewButton);
		contentPane.add(chckbxStudent);
	}
}
