import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class java_sql_gui_1_simple extends JFrame implements ActionListener {
	JTextField tf1, tf2, tf3;
	JLabel l1, l2, l3;
	JButton b1, b2, b3, b4;
	Connection con;
	ResultSet rs;
	Statement st;
	PreparedStatement preparedStatement;

	public java_sql_gui_1_simple() {
		super("Personal Details");
		setLayout(null);
		Label d = new Label("Person Details");
		d.setBounds(200, 10, 150, 50);
		d.setBackground(Color.BLUE);
		add(d);
		l1 = new JLabel("Name");
		l1.setBounds(100, 100, 100, 50);
		add(l1);
		tf1 = new JTextField(" ");

		tf1.setBounds(200, 100, 250, 40);
		add(tf1);
		l2 = new JLabel("Email");
		l2.setBounds(100, 150, 100, 50);
		add(l2);
		tf2 = new JTextField();
		tf2.setBounds(200, 150, 250, 40);
		add(tf2);
		l3 = new JLabel("Phone");
		l3.setBounds(100, 200, 100, 50);

		// l3.setFont(new Font("",Font.BOLD));
		add(l3);
		tf3 = new JTextField();
		tf3.setBounds(200, 200, 250, 40);
		add(tf3);
		b1 = new JButton("Show");
		b1.setBounds(100, 250, 90, 50);
		add(b1);
		b2 = new JButton("Insert");
		b2.setBounds(200, 250, 90, 50);
		add(b2);
		b3 = new JButton("Update");
		b3.setBounds(300, 250, 90, 50);
		add(b3);
		b4 = new JButton("Delete");
		b4.setBounds(400, 250, 90, 50);
		add(b4);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		setSize(600, 600);
		setVisible(true);
		connection();
	}

	public void connection() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");// Loading Driver
			con = DriverManager.getConnection(
					"jdbc:ucanaccess://lab.accdb");// Establishing Connection
			System.out.println("Connected Successfully");
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT * FROM Practice");
			System.out.println("connect");

			// JOptionPane.showMessageDialog(null, "please click next Button");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b1) {
			System.out.println("I am show");
			try {

				System.out.println("Name     " + "Email  " + "Phone");
				while (rs.next()) {
					String name = rs.getString("Name");
					String email = rs.getString("Email");
					String phone = rs.getString("Phone");
					// Printing Results
					System.out.println(name + " " + email + " " + phone);
				}

			} catch (Exception ex) {

			}
		}
		if (e.getSource() == b2) {
			System.out.println("I am Insert");
			String up1 = tf1.getText();
			String up2 = tf2.getText();
			String up3 = tf3.getText();
			try {
				preparedStatement = con
						.prepareStatement("" + "INSERT INTO Practice(Name,Email,Phone) VALUES (?, ?, ?)");
				// Setting values for Each Parameter

				preparedStatement.setString(1, up1);
				preparedStatement.setString(2, up2);
				preparedStatement.setString(3, up3);
				// Executing Query
				preparedStatement.executeUpdate();
				System.out.println("data inserted successfully");
			} catch (Exception er) {
			}
		}
		if (e.getSource() == b3) {
			System.out.println("I am Update");
			String up1 = tf1.getText();
			// String up2=tf2.getText();
			String up3 = tf3.getText();
			try {
				// Crating PreparedStatement object
				preparedStatement = con.prepareStatement("update Practice set Name=? where Phone=?");
				// Setting values for Each Parameter
				preparedStatement.setString(1, up1);
				preparedStatement.setString(2, up3);

				preparedStatement.executeUpdate();
				System.out.println("data updated successfully");

			} catch (Exception er) {
			}
		}

		if (e.getSource() == b4) {
			String up3 = tf3.getText();
			System.out.println("I am Delete");
			try {
				// Crating PreparedStatement object
				preparedStatement = con.prepareStatement("delete from Practice  where Phone=?");
				// Setting values for Each Parameter
				preparedStatement.setString(1, up3);
				preparedStatement.executeUpdate();
				System.out.println("data Delete successfully");
			} catch (Exception er) {

			}
		}
	}

	public static void main(String a[]) {
		new java_sql_gui_1_simple();
	}
}
