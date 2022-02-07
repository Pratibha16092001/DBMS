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

public class carreg extends JFrame {

	private JPanel contentPane;
	private JTextField txtregno;
	private JTextField txtmake;
	private JTextField txtmodel;
	private JTable table;
	DefaultTableModel model;
	
	
	Connection con;
	PreparedStatement pst;
	private JTextField txtavl;
	


	/**
	 * Launch the application.
	 */
	public void table_update()
	{
		int c;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalSystem","root", "Pass@123");
			pst = con.prepareStatement("select * from carregistration");
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
					v2.add(Rs.getString("car_no"));
					v2.add(Rs.getString("make"));
					v2.add(Rs.getString("model"));
					v2.add(Rs.getString("available"));
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
			
			ResultSet rs = s.executeQuery("Select max(car_no) from  carregistration");
			rs.next();
			rs.getString("Max(car_no)");
			
			if(rs.getString("Max(car_no)")==null)
			{
				txtregno.setText("C0001");
			}
			else
			{
				long id = Long.parseLong(rs.getString("Max(car_no)").substring(2,rs.getString("Max(car_no)").length()));
				id++;
				txtregno.setText("C0"+String.format("%03d", id));
				
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
					carreg frame = new carreg();
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
	public carreg() {
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
		
		JLabel lblNewLabel = new JLabel("Car Reg No");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 52, 123, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Make");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(10, 128, 113, 19);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Model");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(10, 189, 85, 19);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Available");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_3.setBounds(10, 257, 111, 26);
		panel.add(lblNewLabel_3);
		
		txtregno = new JTextField();
		txtregno.setBounds(184, 55, 123, 26);
		panel.add(txtregno);
		txtregno.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String rgno = txtregno.getText();
			String make = txtmake.getText();
			String mod = txtmodel.getText();
			String aval = txtavl.getText();
			
			
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
				pst = con.prepareStatement("Insert into carregistration(car_no,make,model,available) values(?,?,?,?)");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pst.setString(1, rgno);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pst.setString(2, make);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pst.setString(3, mod);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pst.setString(4, aval);
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
			JOptionPane.showMessageDialog(null,"Car Added........");
			
			txtmake.setText(" ");
			txtmodel.setText(" ");
			txtavl.setText(" ");
			txtmake.requestFocus();
			autoID();
			table_update();
			
			
			
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(37, 316, 96, 32);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				
				int selectIndex = table.getSelectedRow();
				
				try {
					
					String car_no =  txtregno.getText();                                   //d1.getValueAt(selectIndex, 0).toString();
					String make = txtmake.getText();
					String mod = txtmodel.getText();
					String avl = txtavl.getText();
					
					
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalSystem","root", "Pass@123");
					
					pst = con.prepareStatement("update carregistration set make=?,model=?,available=?,where car_no=?");
					pst.setString(1, make);
					pst.setString(2, mod);
					pst.setString(3, avl);
					pst.setString(4, car_no);
					
					JOptionPane.showMessageDialog(null, "Record Updated");
					
					
					table_update();
					
					
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_1.setBounds(184, 316, 96, 32);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				
				int selectIndex = table.getSelectedRow();
				
				String id = d1.getValueAt(selectIndex, 0).toString();
				
				int dialogResult = JOptionPane.showConfirmDialog(null,"Do you want to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION)
				{
					try {
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalSystem","root", "Pass@123");
						
						pst = con.prepareStatement("delete from carregistration where car_no = ?");
						
						pst.setString(1, id);
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Record Deleted");
						
						table_update();
						
						
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				
				
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_2.setBounds(37, 379, 96, 32);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_3.setBounds(184, 379, 96, 32);
		panel.add(btnNewButton_3);
		
		txtmake = new JTextField();
		txtmake.setColumns(10);
		txtmake.setBounds(184, 131, 123, 26);
		panel.add(txtmake);
		
		txtmodel = new JTextField();
		txtmodel.setColumns(10);
		txtmodel.setBounds(184, 192, 123, 26);
		panel.add(txtmodel);
		
		txtavl = new JTextField();
		txtavl.setBounds(184, 257, 123, 26);
		panel.add(txtavl);
		txtavl.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Car Registration");
		lblNewLabel_4.setBounds(317, 27, 195, 37);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(471, 77, 368, 434);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model =new DefaultTableModel();
		Object[] column = {"CarRegNo","Make","Model","Available"};
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
