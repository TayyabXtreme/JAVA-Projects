package Library;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class ViewOrders implements IOOperation {

	@Override
	public void oper(Database database, User user) {
		
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());
		
		JLabel title = Main.title("View Orders");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Book Name:");
		JTextField name = Main.textfield();
		JButton view = Main.button("View Orders");
		JButton cancel = Main.button("Cancel");
		panel.add(label);
		panel.add(name);
		panel.add(view);
		panel.add(cancel);
		
		view.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
					return;
				}
				int i = database.getBook(name.getText().toString());
				if (i>-1) {
					viewOrders(name.getText().toString(), database);
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Book doesn't exist!");
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
	
	private void viewOrders(String bookname, Database database) {
		
		int rows = 1;
		for (Order order : database.getAllOrders()) {
			if (order.getBook().getName().matches(bookname)) {
				rows++;
			}
		}
		int height = rows*55 + 100;
		JFrame frame = Main.frame(500, height);
		
		JLabel title = Main.title("View Orders");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(rows, 4, 0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		panel.setBackground(null);
		
		String[] row1 = new String[] {"Book", "User", "Qty", "Price"};
		for (String s : row1) {
			JLabel label = label(s);
			panel.add(label);
		}
		
		for (Order order : database.getAllOrders()) {
			if (order.getBook().getName().matches(bookname)) {
				JLabel label1 = label(order.getBook().getName());
				JLabel label2 = label(order.getUser().getName());
				JLabel label3 = label(String.valueOf(order.getQty()));
				JLabel label4 = label(String.valueOf(order.getPrice()));
				panel.add(label1);
				panel.add(label2);
				panel.add(label3);
				panel.add(label4);
			}
		}
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);	
	}
	
	private JLabel label(String text) {
		JLabel label = Main.label(text);
		label.setBackground(Color.white);
		label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		label.setOpaque(true);
		return label;
	}

}
