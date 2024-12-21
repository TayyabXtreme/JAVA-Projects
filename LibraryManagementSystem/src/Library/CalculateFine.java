package Library;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculateFine implements IOOperation {

	@Override
	public void oper(Database database, User user) {
		
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());
		
		JLabel title = Main.title("Calculate Fine");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Book Name:");
		JTextField name = Main.textfield();
		JButton calc = Main.button("Calculate Fine");
		JButton cancel = Main.button("Cancel");
		panel.add(label);
		panel.add(name);
		panel.add(calc);
		panel.add(cancel);
		
		calc.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
					return;
				}		
				boolean g = true;	
				for (Borrowing b : database.getBrws()) {
					if (b.getBook().getName().matches(name.getText().toString()) &&
							b.getUser().getName().matches(user.getName())) {
						if (b.getDaysLeft()>0) {
							JOptionPane.showMessageDialog(new JFrame(),
									"You are late!\n"+"You have to pay "+b.getDaysLeft()*50+" as fine");
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"You don't have to pay fine");
							frame.dispose();
						}
						g = false; 
						break;
					}
				}
				
				if (g) {
					JOptionPane.showMessageDialog(new JFrame(),"You didn't borrow this book!");
				}
				
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
