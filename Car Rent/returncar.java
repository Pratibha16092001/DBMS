package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class returncar extends JFrame {

	private JPanel contentPane;
	private JTextField txtcarid;
	private JTextField txtcustid;
	private JTextField txtdate;
	private JTextField txtelp;
	private JTextField txtfine;
	//private JTable table;
	private JTable table;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	
	public void table_update()
	{
		int c;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalSystem","root", "Pass@123");
			pst = con.prepareStatement("select * from returncar");
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
					v2.add(Rs.getString("carid"));
					v2.add(Rs.getString("custid"));
					v2.add(Rs.getString("return_date"));
					v2.add(Rs.getString("elap"));
					v2.add(Rs.getString("fine"));
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
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					returncar frame = new returncar();
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
	
	Connection con;
	PreparedStatement pst;
	PreparedStatement pst1;
	PreparedStatement pst2;
	ResultSet rs;
	
	
	
	
	
	
	
	
	
	
	
	
	public returncar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Return Car");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(278, 10, 225, 83);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Car Rent Information");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(26, 95, 286, 50);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Car ID");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(26, 179, 138, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Customer ID");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_2_1.setBounds(26, 259, 127, 26);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Date");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_2_2.setBounds(26, 334, 138, 26);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Days Elapsed");
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_2_3.setBounds(26, 404, 138, 26);
		contentPane.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Fine");
		lblNewLabel_2_4.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_2_4.setBounds(26, 478, 127, 26);
		contentPane.add(lblNewLabel_2_4);
		
		txtcarid = new JTextField();
		txtcarid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					try {
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalSystem","root", "Pass@123");
						
						String car_id = txtcarid.getText();
						
						
						pst = con.prepareStatement("select car_id,cust_id,due,DATEIFF(NOW(),due) as elap from rental where car_id=?");
						pst.setString(1, car_id);
						rs = pst.executeQuery();
						
						if(rs.next() == false)
						{
							JOptionPane.showConfirmDialog(null, "Car ID Not Found...");
						}
						else
						{
							String custid = rs.getString("cust_id");
							txtcustid.setText(custid.trim());
							
							
							String date = rs.getString("due");
							txtdate.setText(date.trim());
							
							String elp = rs.getString("elap");
							
							int elaped = Integer.parseInt(elp);
							
							
							if(elaped > 0)
							{
								txtelp.setText(elp);
								
								int fine = elaped * 100;
								
								txtfine.setText(String.valueOf(fine));
							}
							else
							{
								txtelp.setText("0");
								txtfine.setText("0");
							}
							
						}
						
						
						
						
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				
			}
		});
		txtcarid.setBounds(212, 183, 119, 26);
		contentPane.add(txtcarid);
		txtcarid.setColumns(10);
		
		txtcustid = new JTextField();
		txtcustid.setColumns(10);
		txtcustid.setBounds(212, 259, 119, 26);
		contentPane.add(txtcustid);
		
		txtdate = new JTextField();
		txtdate.setColumns(10);
		txtdate.setBounds(212, 338, 119, 26);
		contentPane.add(txtdate);
		
		txtelp = new JTextField();
		txtelp.setColumns(10);
		txtelp.setBounds(212, 404, 119, 26);
		contentPane.add(txtelp);
		
		txtfine = new JTextField();
		txtfine.setColumns(10);
		txtfine.setBounds(212, 478, 119, 26);
		contentPane.add(txtfine);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String car_id = txtcarid.getText();
				String Cust_id = txtcustid.getText();
				String date = txtdate.getText();
				String elp = txtelp.getText();
				String fine = txtfine.getText();
				
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalSystem","root", "Pass@123");
					
					pst = con.prepareStatement("insert into returncar(carid,custid,return_date,elap,fine) values(?,?,?,?,?)");
					pst.setString(1, car_id);
					pst.setString(2, Cust_id);
					pst.setString(3, date);
					pst.setString(4, elp);
					pst.setString(5, fine);
					pst.executeUpdate();
					
					pst1 = con.prepareStatement("update carregistration set available = 'Yes' where car_no = ?");
					pst1.setString(1, car_id);
					pst1.executeUpdate();
					
					pst2 = con.prepareStatement("Delete from rental where car_id = ?");
					pst2.setString(1, car_id);
					pst2.executeUpdate();
					
					
					JOptionPane.showMessageDialog(null, "Car Updated.....!!");
					
					
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(104, 548, 109, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnNewButton_1.setBounds(278, 548, 109, 35);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(436, 109, 410, 483);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel();
		Object[] column = {"CustID", "CarID", "Return Date", "Elapsed", "Fine"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		/*
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"CustID", "CarID", "Return Date", "Elapsed", "Fine"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		
		
		table.getColumnModel().getColumn(2).setPreferredWidth(86);
		table.getColumnModel().getColumn(3).setPreferredWidth(61);
		table.getColumnModel().getColumn(3).setMinWidth(52);
		table.getColumnModel().getColumn(4).setPreferredWidth(63);
		table.getColumnModel().getColumn(4).setMinWidth(20);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.setBounds(410, 139, 445, 444);
		contentPane.add(table);
		*/
		
		
		table_update();
		
	}
}
