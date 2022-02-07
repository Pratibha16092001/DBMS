package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class rental extends JFrame {

	private JPanel contentPane;
	private JTextField txtcust_id;
	private JTextField txtrental;
	private JTextField carid;

	/**
	 * Launch the application.
	 */
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	PreparedStatement pst1;
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rental frame = new rental();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public rental() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rental");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(189, 0, 175, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Car ID");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(41, 75, 88, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Customer ID");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(41, 144, 147, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Rental Fee");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_4.setBounds(41, 225, 140, 31);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Date");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_5.setBounds(41, 307, 102, 31);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Due Date");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_6.setBounds(41, 391, 112, 31);
		contentPane.add(lblNewLabel_6);
		
		txtcust_id = new JTextField();
		txtcust_id.setBounds(252, 147, 147, 31);
		contentPane.add(txtcust_id);
		txtcust_id.setColumns(10);
		
		txtrental = new JTextField();
		txtrental.setBounds(252, 225, 147, 31);
		contentPane.add(txtrental);
		txtrental.setColumns(10);
		
		JDateChooser txtdate = new JDateChooser();
		txtdate.setBounds(252, 306, 147, 32);
		contentPane.add(txtdate);
		
		JDateChooser txtdue = new JDateChooser();
		txtdue.setBounds(252, 391, 147, 31);
		contentPane.add(txtdue);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String car_id = carid.getText();
				String cust_id = txtcust_id.getText();
				//String cust_name = txtcustname.getText();
				String fee = txtrental.getText();
				
				SimpleDateFormat Date_Format = new SimpleDateFormat("dd-MM-yyyy");
				String date = Date_Format.format(txtdate.getDate());
				
				SimpleDateFormat Date_Format1 = new SimpleDateFormat("dd-MM-yyyy");
				String due = Date_Format1.format(txtdue.getDate());
				
				
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalSystem","root", "Pass@123");
					
					pst = con.prepareStatement("insert into rental(car_id,cust_id,fee,date,due) values(?,?,?,?,?)");
					pst.setString(1, car_id);
					pst.setString(2, cust_id);
					//pst.setString(3, cust_name);
					pst.setString(3, fee);
					pst.setString(4, date);
					pst.setString(5, due);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Success");
					
					//pst.executeUpdate();
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnNewButton.setBounds(72, 487, 116, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnNewButton_1.setBounds(276, 487, 112, 31);
		contentPane.add(btnNewButton_1);
		
		carid = new JTextField();
		carid.setColumns(10);
		carid.setBounds(252, 75, 147, 31);
		contentPane.add(carid);
		
	
		/*
		carid = new JTextField();
		carid.setColumns(10);
		carid.setBounds(252, 75, 147, 31);
		contentPane.add(carid);
		
		carid = new JTextField();
		carid.setColumns(10);
		carid.setBounds(252, 75, 147, 31);
		contentPane.add(carid);
		*/
		
		
	}
}
