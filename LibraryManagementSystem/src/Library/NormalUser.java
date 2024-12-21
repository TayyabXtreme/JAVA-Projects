package Library;

import javax.swing.JFrame;

public class NormalUser extends User {

	public NormalUser(String name) {
		super(name);
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new Search(),
				new PlaceOrder(),
				new BorrowBook(),
				new CalculateFine(),
				new ReturnBook(),
				new Exit()
		};
	}
	
	public NormalUser(String name, String email, String phonenumber) {
		super(name, email, phonenumber);
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new Search(),
				new PlaceOrder(),
				new BorrowBook(),
				new CalculateFine(),
				new ReturnBook(),
				new Exit()
		};
	}
	
	@Override
	public void menu(Database database, User user) {
		
		String[] data = new String[7];
		data[0] = "View Books";
		data[1] = "Search";
		data[2] = "Place Order";
		data[3] = "Borrow Book";
		data[4] = "Calculate Fine";
		data[5] = "Return Book";
		data[6] = "Exit";
		
		JFrame frame = this.frame(data, database, user);
		frame.setVisible(true);
	}
	
	public String toString() {
		return name+"<N/>"+email+"<N/>"+phonenumber+"<N/>"+"Normal";
	}
	
}
