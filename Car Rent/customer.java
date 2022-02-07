package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.TextArea;

public class customer extends JFrame {

	private JPanel contentPane;
	private JTextField txtcustid;
	private JTextField txtcustname;
	private JTable table;
	DefaultTableModel model;
	
	
	Connection con;
	PreparedStatement pst;
	private JTextField txtmobile;
	private JTextField textaddress;
	


	/**
	 * Launch the application.
	 */
	public void table_update()
	{
		int c;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalSystem","root", "Pass@123");
			pst = con.prepareStatement("select * from customer");
			ResultSet Rs = pst.executeQuery();
			
			ResultSetMetaData rd = (ResultSetMetaData) Rs.getMetaData();
			c = rd.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table.getModel();
			df.setRowCount(0);
			
			while(Rs.next())
			{
				Vector v2 = new Vector();
				
				for(int i=1; i<=c; i++)
				{
					v2.add(Rs.getString("cust_id"));
					v2.add(Rs.getString("name"));
					v2.add(Rs.getString("address"));
					v2.add(Rs.getString("mobile"));
				}
				
				df.addRow(v2);
			}
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	public void autoID()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalSystem","root", "Pass@123");
			Statement s = con.createStatement();
			
			ResultSet rs = s.executeQuery("Select max(cust_id) from  customer");
			rs.next();
			rs.getString("Max(cust_id)");
			
			if(rs.getString("Max(cust_id)")==null)
			{
				txtcustid.setText("A0001");
			}
			else
			{
				long id = Long.parseLong(rs.getString("Max(cust_id)").substring(2,rs.getString("Max(cust_id)").length()));
				id++;
				txtcustid.setText("A0"+String.format("%03d", id));
				
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customer frame = new customer();
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
	public customer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 589);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setToolTipText("Car");
		panel.setBounds(10, 77, 362, 434);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer ID");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 52, 123, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Customer Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(10, 128, 150, 19);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(10, 189, 85, 19);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mobile");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_3.setBounds(10, 257, 111, 26);
		panel.add(lblNewLabel_3);
		
		txtcustid = new JTextField();
		txtcustid.setBounds(184, 55, 123, 26);
		panel.add(txtcustid);
		txtcustid.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String custID = txtcustid.getText();
			String Custname = txtcustname.getText();
			String address = textaddress.getText();
			String mobile = txtmobile.getText();
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalSystem","root", "Pass@123");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pst = con.prepareStatement("Insert into customer(cust_id,name,address,mobile) values(?,?,?,?)");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pst.setString(1, custID);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pst.setString(2,Custname);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pst.setString(3, address);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pst.setString(4, mobile);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pst.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,"Customer Added........");
			
			txtcustname.setText(" ");
			textaddress.setText(" ");
			txtmobile.setText(" ");
	
			
			
			txtcustname.requestFocus();
			autoID();
			table_update();
			
			
			
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(37, 326, 96, 32);
		panel.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_3.setBounds(184, 326, 96, 32);
		panel.add(btnNewButton_3);
		
		txtcustname = new JTextField();
		txtcustname.setColumns(10);
		txtcustname.setBounds(184, 131, 123, 26);
		panel.add(txtcustname);
		
		txtmobile = new JTextField();
		txtmobile.setBounds(184, 257, 123, 26);
		panel.add(txtmobile);
		txtmobile.setColumns(10);
		
		textaddress = new JTextField();
		textaddress.setColumns(10);
		textaddress.setBounds(184, 192, 123, 26);
		panel.add(textaddress);
		
		JLabel lblNewLabel_4 = new JLabel("Customer");
		lblNewLabel_4.setBounds(317, 27, 195, 37);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(471, 77, 368, 434);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model =new DefaultTableModel();
		Object[] column = {"CustomerID","CustomerName","Address","Mobile"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.setToolTipText("");
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		autoID();
		table_update();
	}
}


