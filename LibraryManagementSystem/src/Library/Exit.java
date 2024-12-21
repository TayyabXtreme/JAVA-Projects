package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Exit implements IOOperation {
	
	Database database;

	@Override
	public void oper(Database database, User user) {
		JFrame frame = Main.frame(500, 300);
		
		this.database = new Database();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);
		
		JLabel title = Main.label("Welcome to Library Management System");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JLabel label1 = Main.label("Phone Number:");
		JLabel label2 = Main.label("Email:");
		JTextField phonenumber = Main.textfield();
		JTextField email = Main.textfield();
		JButton login = Main.button("Login");
		JButton newUser = Main.button("New User");
		
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
	
	private void login(String phonenumber, String email, JFrame frame) {
		int n = database.login(phonenumber, email);
		if (n != -1) {
			User user = database.getUser(n);
			user.menu(database, user);
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "User doesn't exist");
		}
	}
	
	private void newuser() {
		
		JFrame frame = Main.frame(500, 400);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);
		
		JLabel title = Main.label("Create new account");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JLabel label0 = Main.label("Name:");
		JLabel label1 = Main.label("Phone Number:");
		JLabel label2 = Main.label("Email:");
		JTextField name = Main.textfield();
		JTextField phonenumber = Main.textfield();
		JTextField email = Main.textfield();
		JRadioButton admin = Main.radioButton("Admin");
		JRadioButton normaluser = Main.radioButton("Normal User");
		JButton createacc = Main.button("Create Account");
		JButton cancel = Main.button("Cancel");
		
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

}
