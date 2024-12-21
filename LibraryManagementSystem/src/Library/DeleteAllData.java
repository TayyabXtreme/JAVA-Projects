package Library;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeleteAllData implements IOOperation {

	@Override
	public void oper(Database database, User user) {
		
		JFrame frame = Main.frame(600, 150);
		frame.setLayout(new BorderLayout());
		
		JLabel title = Main.title("Are you sure that you want to delete all data?");
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
		panel.setBackground(null);
		
		JButton del = Main.button("Continue");
		JButton cancel = Main.button("Cancel");
		
		panel.add(del);
		panel.add(cancel);
		
		del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				database.deleteAllData();
				frame.dispose();
				new Exit().oper(database, user);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				user.menu(database, user);
			}
		});
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}
	
}
