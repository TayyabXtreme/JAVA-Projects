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

public class AddBook implements IOOperation {

	@Override
	public void oper(Database database, User user) {
		
		JFrame frame = Main.frame(500, 600);
		
		JLabel title = Main.title("Add new book");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		panel.setBackground(null);
		
		JLabel label1 = Main.label("Book Name:");
		JLabel label2 = Main.label("Book Author:");
		JLabel label3 = Main.label("Book Publisher:");
		JLabel label4 = Main.label("Book Collection Adress:");
		JLabel label5 = Main.label("Qty:");
		JLabel label6 = Main.label("Price:");
		JLabel label7 = Main.label("Borrowing Copies:");
		
		JTextField name = Main.textfield();
		JTextField author = Main.textfield();
		JTextField pub = Main.textfield();
		JTextField bca = Main.textfield();
		JTextField qty = Main.textfield();
		JTextField price = Main.textfield();
		JTextField brwcpis = Main.textfield();
		
		JButton addbook = Main.button("Add Book");
		JButton cancel = Main.button("Cancel");
		
		panel.add(label1);
		panel.add(name);
		panel.add(label2);
		panel.add(author);
		panel.add(label3);
		panel.add(pub);
		panel.add(label4);
		panel.add(bca);
		panel.add(label5);
		panel.add(qty);
		panel.add(label6);
		panel.add(price);
		panel.add(label7);
		panel.add(brwcpis);
		panel.add(addbook);
		panel.add(cancel);
		
		addbook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (name.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
					return;
				}
				if (author.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book author cannot be empty!");
					return;
				}
				if (pub.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book publisher cannot be empty!");
					return;
				}
				if (bca.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Book collection adress cannot be empty!");
					return;
				}
				if (qty.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Qty cannot be empty!");
					return;
				}
				if (price.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Price cannot be empty!");
					return;
				}
				if (brwcpis.getText().toString().matches("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Borrowing copies cannot be empty!");
					return;
				}
				try {
					Integer.parseInt(qty.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Qty must be integer!");
					return;
				}
				try {
					Double.parseDouble(price.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Price must be number!");
					return;
				}
				try {
					Integer.parseInt(brwcpis.getText().toString());
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Borrowing copies must be integer!");
					return;
				}
				Book book = new Book();
				if (database.getBook(name.getText().toString())>-1) {
					JOptionPane.showMessageDialog(new JFrame(), "There is a book with this name!");
					return;
				} else {
					book.setName(name.getText().toString());
					book.setAuthor(author.getText().toString());
					book.setPublisher(pub.getText().toString());
					book.setAdress(bca.getText().toString());
					book.setQty(Integer.parseInt(qty.getText().toString()));
					book.setPrice(Double.parseDouble(price.getText().toString()));
					book.setBrwcopies(Integer.parseInt(brwcpis.getText().toString()));
					database.AddBook(book);
					JOptionPane.showMessageDialog(new JFrame(), "Book added successfully");
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
