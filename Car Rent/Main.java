package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 548);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer frame = new customer();
				frame.setVisible(true);
			}
			
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(185, 153, 191, 38);
		getContentPane().add(btnNewButton);
		
		JButton btnCarRegistration = new JButton("Car Registration");
		btnCarRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carreg frame = new carreg();
				frame.setVisible(true);
			}
		});
		btnCarRegistration.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCarRegistration.setBounds(185, 62, 191, 38);
		getContentPane().add(btnCarRegistration);
		
		JButton btnRental = new JButton("Rental");
		btnRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rental frame = new rental();
				frame.setVisible(true);
			}
		});
		btnRental.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnRental.setBounds(185, 252, 191, 38);
		getContentPane().add(btnRental);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returncar frame = new returncar();
				frame.setVisible(true);
			}
		});
		btnReturn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReturn.setBounds(185, 355, 191, 38);
		getContentPane().add(btnReturn);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Confirm if you want to Logout","Logout",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION);
				{
					System.exit(0);
				}
				JOptionPane.showMessageDialog(null, "Logout Successfully...!!!");
			}
		});
		btnLogout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLogout.setBounds(185, 452, 191, 38);
		getContentPane().add(btnLogout);
	}
}
