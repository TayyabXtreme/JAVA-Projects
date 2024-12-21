package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewBooks implements IOOperation {

	@Override
	public void oper(Database database, User user) {
		
		int rows = database.getAllBooks().size()+1;
		int height = rows*60 + 100;
		JFrame frame = Main.frame(1000, height);
		
		JLabel title = Main.title("View Books");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(rows, 7, 0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		panel.setBackground(null);
		
		String[] row1 = new String[] {"Name", "Author", "Publisher", "CLA", "Qty", "Price", "Brw cps"};
		for (String s : row1) {
			JLabel label = label(s);
			panel.add(label);
		}
		
		for (Book b : database.getAllBooks()) {
			JLabel label1 = label(b.getName());
			JLabel label2 = label(b.getAuthor());
			JLabel label3 = label(b.getPublisher());
			JLabel label4 = label(b.getAdress());
			JLabel label5 = label(String.valueOf(b.getQty()));
			JLabel label6 = label(String.valueOf(b.getPrice()));
			JLabel label7 = label(String.valueOf(b.getBrwcopies()));
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(label4);
			panel.add(label5);
			panel.add(label6);
			panel.add(label7);
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
