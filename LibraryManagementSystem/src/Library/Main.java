package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {
	
	static Scanner s;
	static Database database;

	public static void main(String[] args) {
		
		database = new Database();
		
		JFrame frame = frame(500, 300);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);
		
		JLabel title = label("Welcome to Library Management System");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JLabel label1 = label("Phone Number:");
		JLabel label2 = label("Email:");
		JTextField phonenumber = textfield();
		JTextField email = textfield();
		JButton login = button("Login");
		JButton newUser = button("New User");
		
		login.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (phonenumber.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Phone number cannot be empty!");
					return;
				}
				if (email.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Email cannot be empty!");
					return;
				}
				login(phonenumber.getText().toString(), email.getText().toString(), frame);
			}	
		});
		newUser.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				newuser();
				frame.dispose();
			}	
		});
		
		panel.add(label1);
		panel.add(phonenumber);
		panel.add(label2);
		panel.add(email);	
		panel.add(login);
		panel.add(newUser);
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}

	private static void login(String phonenumber, String email, JFrame frame) {
		int n = database.login(phonenumber, email);
		if (n != -1) {
			User user = database.getUser(n);
			user.menu(database, user);
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "User doesn't exist");
		}
	}
	
	private static void newuser() {
		
		JFrame frame = frame(500, 400);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);
		
		JLabel title = label("Create new account");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JLabel label0 = label("Name:");
		JLabel label1 = label("Phone Number:");
		JLabel label2 = label("Email:");
		JTextField name = textfield();
		JTextField phonenumber = textfield();
		JTextField email = textfield();
		JRadioButton admin = radioButton("Admin");
		JRadioButton normaluser = radioButton("Normal User");
		JButton createacc = button("Create Account");
		JButton cancel = button("Cancel");
		
		admin.addActionListener(e -> {
			if (normaluser.isSelected()) {
				normaluser.setSelected(false);
			}
		});
		normaluser.addActionListener(e -> {
			if (admin.isSelected()) {
				admin.setSelected(false);
			}
		});
		
		panel.add(label0);
		panel.add(name);
		panel.add(label1);
		panel.add(phonenumber);
		panel.add(label2);
		panel.add(email);
		panel.add(admin);
		panel.add(normaluser);
		panel.add(createacc);
		panel.add(cancel);
		
		createacc.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (database.userExists(name.getText().toString())) {
					JOptionPane.showMessageDialog(new JFrame(), "Username exists!\nTry another one");
					return;
				}
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Name cannot be empty!");
					return;
				}
				if (phonenumber.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Phone number cannot be empty!");
					return;
				}
				if (email.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Email cannot be empty!");
					return;
				}
				if (!admin.isSelected() && !normaluser.isSelected()) {
					JOptionPane.showMessageDialog(new JFrame(), "You must choose account type!");
					return;
				}
				User user;
				if (admin.isSelected()) {
					user = new Admin(name.getText().toString(),
							email.getText().toString(), phonenumber.getText().toString());
				} else {
					user = new NormalUser(name.getText().toString(),
							email.getText().toString(), phonenumber.getText().toString());
				}
				frame.dispose();
				database.AddUser(user);
				user.menu(database, user);		
			}
		});
		cancel.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public static JFrame frame(int width, int height) {
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Library Management System");
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.white);
		frame.getContentPane().setBackground(Color.white);
		return frame;
	}
	
	public static JLabel label(String text) {
		JLabel label1 = new JLabel(text);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Tahoma", Font.BOLD, 17));
		label1.setForeground(Color.black);
		return label1;
	}
	
	public static JTextField textfield() {
		JTextField textfield1 = new JTextField();
		textfield1.setFont(new Font("Tahoma", Font.BOLD, 17));
		textfield1.setForeground(Color.black);
		textfield1.setHorizontalAlignment(SwingConstants.CENTER);
		return textfield1;
	}
	
	public static JButton button(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setForeground(Color.white);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setBackground(Color.decode("#1da1f2"));
		button.setBorder(null);
		return button;
	}
	
	public static JRadioButton radioButton(String text) {
		JRadioButton btn = new JRadioButton();
		btn.setForeground(Color.black);
		btn.setText(text);
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn.setBackground(null);
		return btn;
	}
	
	public static JLabel title(String text) {
		JLabel title = Main.label(text);
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		return title;
	}

}
