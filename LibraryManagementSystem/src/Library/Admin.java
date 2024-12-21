package Library;

import javax.swing.JFrame;

public class Admin extends User {
	
	public Admin(String name) {
		super(name);
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new AddBook(),
				new DeleteBook(),
				new Search(),
				new DeleteAllData(),
				new ViewOrders(),
				new Exit()
		};
	}
	
	public Admin(String name, String email, String phonenumber) {
		super(name, email, phonenumber);
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new AddBook(),
				new DeleteBook(),
				new Search(),
				new DeleteAllData(),
				new ViewOrders(),
				new Exit()
		};
	}
	
	@Override
	public void menu(Database database, User user) {
		String[] data = new String[7];
		data[0] = "View Books";
		data[1] = "Add Book";
		data[2] = "Delete Book";
		data[3] = "Search";
		data[4] = "Delete all data";
		data[5] = "View Orders";
		data[6] = "Exit";
		
		JFrame frame = this.frame(data, database, user);
		frame.setVisible(true);
	}
	
	public String toString() {
		return name+"<N/>"+email+"<N/>"+phonenumber+"<N/>"+"Admin";
	}

}
