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

public class PlaceOrder implements IOOperation {

	@Override
	public void oper(Database database, User user) {
		
		JFrame frame = Main.frame(400, 270);
		frame.setLayout(new BorderLayout());
		
		JLabel title = Main.title("Place Order");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Book Name:");
		JTextField name = Main.textfield();
		JLabel label2 = Main.label("Qty:");
		JTextField qty = Main.textfield();
		JButton placeorder = Main.button("Place Order");
		JButton cancel = Main.button("Cancel");
		panel.add(label);
		panel.add(name);
		panel.add(label2);
		panel.add(qty);
		panel.add(placeorder);
		panel.add(cancel);
		
		placeorder.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
					return;
				}
				if (qty.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Qty cannot be empty!");
					return;
				}
				try {
					Integer.parseInt(qty.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Qty must be integer!");
					return;
				}
				Order order = new Order();
				int i = database.getBook(name.getText().toString());
				if (i<=-1) {
					JOptionPane.showMessageDialog(new JFrame(), "Book doesn't exist!");
				} else {
					Book book = database.getBook(i);
					order.setBook(book);
					order.setUser(user);
					int qt = Integer.parseInt(qty.getText().toString());
					order.setQty(qt);
					order.setPrice(book.getPrice()*qt);
					int bookindex = database.getBook(book.getName());
					book.setQty(book.getQty()-qt);
					database.addOrder(order, book, bookindex);
					JOptionPane.showMessageDialog(new JFrame(), "Order placed successfully!");
					frame.dispose();
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
