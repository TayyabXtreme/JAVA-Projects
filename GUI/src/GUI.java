//import java.awt.TextField;
//
//import javax.swing.DefaultListModel;
//import javax.swing.JFrame;
//import javax.swing.JList;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//
//public class GUI {
//    private JFrame frame;
//
//    public GUI() {
//        frame = new JFrame("Abrar Pahar");
//        frame.setSize(500, 500);
//        
//        DefaultListModel<String> listModel = new DefaultListModel<>();
//        listModel.addElement("Abrar");
//        listModel.addElement("Ayuib");
//        listModel.addElement("Anees");
//        listModel.addElement("Munib");
//        
//        JList<String> list = new JList<>(listModel);
//        list.setBounds(100, 100, 75, 75);
//
//        TextField textField = new TextField();
//        textField.setBounds(200, 100, 100, 20);
//
//        list.addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent event) {
//               
//                    String selectedData = list.getSelectedValue();
//                    textField.setText(selectedData);
//             
//            }
//        });
//
//        frame.add(textField);
//        frame.add(list);
//
//        frame.setLayout(null);
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        new GUI();
//    }
//}

//abstract class Vechicle{
//	abstract void start();
//	abstract void fly();
//	void flying() {
//		
//	}
//}
//
//
//class Car extends Vechicle {
//	void start() {
//		System.out.println("Car is starting");
//	}
//
//	void fly() {
//		System.out.println("Car is flying");
//	}
//}
//
//class Bike extends Vechicle {
//	void start() {
//		System.out.println("Bike is starting");
//	}
//
//	void fly() {
//		System.out.println("Bike is flying");
//	}
//}
//
//public class GUI{
//	
//	public static void main(String[] args) {
//		//Vechicle v=new Vehicle();
//		Car car=new Car();
//		Bike bike=new Bike();
//		car.start();
//		bike.start();
//		
//	
//	}
//	
//	
//}
//
//
//class hello implements Runnable {
//	public void run() {
//		System.out.println("Hello");
//	}
//}
//
//interface Vechicle {
// void start();
// int speed=100;
// static void flystart(){
//		System.out.println("Fly");
//	}
// 
// 
//}
//
//class Car implements Vechicle {
//	public void start() {
//		System.out.println("Car is starting");
//	}
//}
//
//class Bike implements Vechicle {
//	public void start() {
//		System.out.println("Bike is starting");
//	}
//}
//
//public class GUI{
//	
//	
//	public static void main(String[] args) {
//		System.out.println("Hello World");
//	}
//	
//}
//


//public class GUI{
//	
////	public void run() {
////		System.out.println("Hello"+Thread.currentThread().getName());
////	}
////	
//	public static void main(String[] args) throws InterruptedException {
//		Thread Health = new Thread(new Runnable() {
//			public void run() {
//				System.out.println("Health" + Thread.currentThread().getName());
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println("Health cheked" + Thread.currentThread().getName());
//			}
//		});
//		
//		Thread DrivingTest = new Thread(new Runnable() {
//			public void run() {
//				System.out.println("Driving Test" + Thread.currentThread().getName());
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println("Driving Test Passed" + Thread.currentThread().getName());
//			}
//		});
//		
//		Thread License = new Thread(new Runnable() {
//			public void run() {
//				System.out.println("License" + Thread.currentThread().getName());
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println("License Issued" + Thread.currentThread().getName());
//			}
//		});
//		
//		
//		Health.start();
//		Health.join();
//		
//		DrivingTest.start();
//		DrivingTest.join();
//		
//		License.start();
//	}
//}


class TicketCounter {
	
	static TicketCounter ticketCounter;
	static int totalseat=10;
	public synchronized void bookTicket(String name, int seats) {
		if (totalseat >= seats) {
            System.out.println(name+"Seats are book succesfuly");
            totalseat = totalseat - seats;
        } else {
            System.out.println("Ticket are not available");
        }
	}
}

class GUI extends Thread {
	TicketCounter ticketCounter;
	String name;
	int seats;

	public GUI(TicketCounter ticketCounter, String name, int seats) {
		this.ticketCounter = ticketCounter;
		this.name = name;
		this.seats = seats;
	}

	public void run() {
		ticketCounter.bookTicket(name, seats);
	}

	public static void main(String[] args) {
		TicketCounter ticketCounter = new TicketCounter();
		GUI t1 = new GUI(ticketCounter, "Abrar", 7);
		GUI t2 = new GUI(ticketCounter, "Anees", 4);
		GUI t3 = new GUI(ticketCounter, "Munib", 2);
		t2.setPriority(10);
		//t1.setPriority(1);
		t1.start();
		t2.start();
		t3.start();
	}
}


