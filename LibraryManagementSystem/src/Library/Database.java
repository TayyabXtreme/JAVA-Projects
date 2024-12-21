package Library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {
	
	private ArrayList<User> users;
	private ArrayList<String> usernames;
	private ArrayList<Book> books;
	private ArrayList<String> booknames;
	private ArrayList<Order> orders;
	private ArrayList<Borrowing> borrowings;
	
	private File usersfile = new File("C:\\Library Management System\\Data\\Users");
	private File booksfile = new File("C:\\Library Management System\\Data\\Books");
	private File ordersfile = new File("C:\\Library Management System\\Data\\Orders");
	private File borrowingsfile = new File("C:\\Library Management System\\Data\\Borrowings");
	private File folder = new File("C:\\Library Management System\\Data");
	
	public Database() {
		if (!folder.exists()) {
			folder.mkdirs();
		}
		if (!usersfile.exists()) {
			try {
				usersfile.createNewFile();
			} catch (Exception e) {}
		}
		if (!booksfile.exists()) {
			try {
				booksfile.createNewFile();
			} catch (Exception e) {}
		}
		if (!ordersfile.exists()) {
			try {
				ordersfile.createNewFile();
			} catch (Exception e) {}
		}
		if (!borrowingsfile.exists()) {
			try {
				borrowingsfile.createNewFile();
			} catch (Exception e) {}
		}
		
		users = new ArrayList<User>();
		usernames = new ArrayList<String>();
		books = new ArrayList<Book>();
		booknames = new ArrayList<String>();
		orders = new ArrayList<Order>();
		borrowings = new ArrayList<Borrowing>();
		
		getUsers();
		getBooks();
		getOrders();
		getBorrowings();
	}
	
	public void AddUser(User s) {
		users.add(s);
		usernames.add(s.getName());
		saveUsers();
	}
	
	public int login(String phonenumber, String email) {
		int n = -1;
		for (User s : users) {
			if (s.getPhoneNumber().matches(phonenumber) && s.getEmail().matches(email)) {
				n = users.indexOf(s);
				break;
			}
		}
		return n;
	}
	
	public User getUser(int n) {
		return users.get(n);
	}
	
	public void AddBook(Book book) {
		books.add(book);
		booknames.add(book.getName());
		saveBooks();
	}
	
	private void getUsers() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(usersfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		if (!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewUser/>");
			for (String s : a1) {
				String[] a2 = s.split("<N/>");
				if (a2[3].matches("Admin")) {
					User user = new Admin(a2[0], a2[1], a2[2]);
					users.add(user);
					usernames.add(user.getName());
				} else {
					User user = new NormalUser(a2[0], a2[1], a2[2]);
					users.add(user);
					usernames.add(user.getName());
				}
			}
		}
	}
	
	private void saveUsers() {
		String text1 = "";
		for (User user : users) {
			text1 = text1 + user.toString()+"<NewUser/>\n";
		}
		try {
			PrintWriter pw = new PrintWriter(usersfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	private void saveBooks() {
		String text1 = "";
		for (Book book : books) {
			text1 = text1 + book.toString2()+"<NewBook/>\n";
		}
		try {
			PrintWriter pw = new PrintWriter(booksfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	private void getBooks() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(booksfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		if (!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewBook/>");
			for (String s : a1) {
				Book book = parseBook(s);
				books.add(book);
				booknames.add(book.getName());
			}
		}
	}
	
	public Book parseBook(String s) {
		String[] a = s.split("<N/>");
		Book book = new Book();
		book.setName(a[0]);
		book.setAuthor(a[1]);
		book.setPublisher(a[2]);
		book.setAdress(a[3]);
		book.setQty(Integer.parseInt(a[4]));
		book.setPrice(Double.parseDouble(a[5]));
		book.setBrwcopies(Integer.parseInt(a[6]));
		return book;
	}
	
	public ArrayList<Book> getAllBooks() {
		return books;
	}
	
	public int getBook(String bookname) {
		int i = -1;
		for (Book book : books) {
			if (book.getName().matches(bookname)) {
				i = books.indexOf(book);
			}
		}
		return i;
	}
	
	public Book getBook(int i) {
		return books.get(i);
	}
	
	public void deleteBook(int i) {
		books.remove(i);
		booknames.remove(i);
		saveBooks();
	}
	
	public void deleteAllData() {
		if (usersfile.exists()) {
			try {
				usersfile.delete();
			} catch (Exception e) {}
		}
		if (booksfile.exists()) {
			try {
				booksfile.delete();
			} catch (Exception e) {}
		}
		if (ordersfile.exists()) {
			try {
				ordersfile.delete();
			} catch (Exception e) {}
		}
		if (borrowingsfile.exists()) {
			try {
				borrowingsfile.delete();
			} catch (Exception e) {}
		}
	}
	
	public void addOrder(Order order, Book book, int bookindex) {
		orders.add(order);
		books.set(bookindex, book);
		saveOrders();
		saveBooks();
	}
	
	private void saveOrders() {
		String text1 = "";
		for (Order order : orders) {
			text1 = text1 + order.toString2()+"<NewOrder/>\n";
		}
		try {
			PrintWriter pw = new PrintWriter(ordersfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	private void getOrders() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(ordersfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		if (!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewOrder/>");
			for (String s : a1) {
				Order order = parseOrder(s);
				orders.add(order);
			}
		}
	}
	
	public boolean userExists(String name) {
		boolean f = false;
		for (User user : users) {
			if (user.getName().toLowerCase().matches(name.toLowerCase())) {
				f = true;
				break;
			}
		}
		return f;
	}
	
	private User getUserByName(String name) {
		User u = new NormalUser("");
		for (User user : users) {
			if (user.getName().matches(name)) {
				u = user;
				break;
			}
		}
		return u;
	}
	
	private Order parseOrder(String s) {
		String[] a = s.split("<N/>");
		Order order = new Order(books.get(getBook(a[0])), getUserByName(a[1]),
				Double.parseDouble(a[2]), Integer.parseInt(a[3]));
		return order;
	}
	
	public ArrayList<Order> getAllOrders() {
		return orders;
	}
	
	private void saveBorrowings() {
		String text1 = "";
		for (Borrowing borrowing : borrowings) {
			text1 = text1 + borrowing.toString2()+"<NewBorrowing/>\n";
		}
		try {
			PrintWriter pw = new PrintWriter(borrowingsfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	private void getBorrowings() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(borrowingsfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		if (!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewBorrowing/>");
			for (String s : a1) {
				Borrowing borrowing = parseBorrowing(s);
				borrowings.add(borrowing);
			}
		}
	}
	
	private Borrowing parseBorrowing(String s) {
		String[] a = s.split("<N/>");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(a[0], formatter);
		LocalDate finish = LocalDate.parse(a[1], formatter);
		Book book = getBook(getBook(a[3]));
		User user = getUserByName(a[4]);
		Borrowing brw = new Borrowing(start, finish, book, user);
		return brw;
	}
	
	public void borrowBook(Borrowing brw, Book book, int bookindex) {
		borrowings.add(brw);
		books.set(bookindex, book);
		saveBorrowings();
		saveBooks();
	}
	
	public ArrayList<Borrowing> getBrws() {
		return borrowings;
	}
	
	public void returnBook(Borrowing b, Book book, int bookindex) {
		borrowings.remove(b);
		books.set(bookindex, book);
		saveBorrowings();
		saveBooks();
	}

}
