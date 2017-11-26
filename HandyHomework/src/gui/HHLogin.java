package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DbUser;
import login.SelectedUser;
import login.UserLogin;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		SwitchForm sf = new SwitchForm();
		setTitle("HandyHomework");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(80, 70, 250, 25);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		usernameField.setName("username");
		
		passwordField = new JPasswordField();
		passwordField.setBounds(80, 145, 250, 25);
		contentPane.add(passwordField);
		passwordField.setName("pass");
		
		lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(166, 39, 75, 20);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("ENTER PASSWORD\r\n");
		lblPassword.setBounds(136, 114, 136, 19);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setName("Login");
		btnNewButton.setBounds(90, 183, 102, 43);
		contentPane.getRootPane().setDefaultButton(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = String.valueOf(usernameField.getText());
				String password = String.valueOf(passwordField.getPassword());
				if (passwordField.getPassword().length == 0 || userName.isEmpty()) {
					JOptionPane.showMessageDialog(HHLogin.this, "Username or password cannot be empty.");
				} else {
					DbUser db = new DbUser();
					UserLogin uf =  db.getUser(userName, password); //db.DbConnection.checkUser(userName, password);
					
					if (uf == null) {
						JOptionPane.showMessageDialog(HHLogin.this, 
								"Incorrect credentials entered.");
					} else {					
						SelectedUser.setUser(uf);
						
						HandyHomeworkMainPage frame = new HandyHomeworkMainPage();
						sf.switchForm(frame);
						dispose();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setBounds(175, 6, 125, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegister.setName("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				HHRegister frame = new HHRegister();
				sf.switchForm(frame);
				dispose();
			}
		});
		btnRegister.setBounds(217, 183, 102, 43);
		contentPane.add(btnRegister);
	}
}
