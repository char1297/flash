package javacrud;
import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class javacrud { 

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javacrud window = new javacrud();   // creating object of crud class
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**   
	 * Create the application.
	 */
	public javacrud() {
		initialize();
		connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	public void connect()
	{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con =DriverManager.getConnection("jdbc:mysql://localhost/javacrud","root","");	
				}
			catch(ClassNotFoundException ex)
			{
				
			}
			catch(SQLException ex)
			{
				
			}
	}
	public void table_load()
	{
		try {
			pst=con.prepareStatement("select * from book");
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Dialog", Font.BOLD, 14));
		frame.setBounds(100, 100, 915, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book shop");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 27));
		lblNewLabel.setBounds(349, 45, 157, 40);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(35, 127, 326, 148);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(39, 34, 89, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Editiion");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(39, 69, 89, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(39, 104, 89, 13);
		panel.add(lblNewLabel_1_2);
		
		txtname = new JTextField();
		txtname.setBounds(138, 33, 96, 19);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setBounds(138, 68, 96, 19);
		panel.add(txtedition);
		txtedition.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setBounds(138, 103, 96, 19);
		panel.add(txtprice);
		txtprice.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price;
				bname=txtname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				
				try {
					pst=con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated!!!!");
					//table_load();
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtname.requestFocus();
					
				}
				
				catch(SQLException e1 ) {
					
					e1.printStackTrace();
					
				}
				
			}
		});
		btnNewButton.setBounds(47, 298, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_1.setBounds(165, 298, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				
			}
		});
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_2.setBounds(276, 298, 85, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(47, 366, 728, 117);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Book ID");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(36, 44, 89, 13);
		panel_1.add(lblNewLabel_1_3);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id=txtbid.getText();
					pst=con.prepareStatement("select name,edition,price from book where id=?");
					pst.setString(1,id);
					ResultSet rs=pst.executeQuery();
					if(rs.next()==true) {
						String bname=rs.getString(1);
						String edition=rs.getString(2);
						String price=rs.getString(3);
						txtname.setText(bname);
						txtedition.setText(edition);
						txtprice.setText(price);
					}
					else {
						txtname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
				}
				catch(SQLException ex) {
					
				}
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(173, 43, 96, 19);
		panel_1.add(txtbid);
		
		JButton btnNewButton_2_1 = new JButton("Delete");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bid;
				
				bid=txtbid.getText();
				
				
				try {
					pst=con.prepareStatement("delete from book where id=?");
					
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record deleted!!!!");
					//table_load();
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtname.requestFocus();
					
				}
				
				catch(SQLException e1 ) {
					
					e1.printStackTrace();
					
				}
				
			}
		});
		btnNewButton_2_1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton_2_1.setBounds(574, 40, 103, 21);
		panel_1.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Update");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price,bid;
				bname=txtname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				bid=txtbid.getText();
				
				
				try {
					pst=con.prepareStatement("update book set name=?,edition=?,price=?where id=?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated!!!!");
					//table_load();
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtname.requestFocus();
					
				}
				
				catch(SQLException e1 ) {
					
					e1.printStackTrace();
					
				}
				
				
			}
		});
		btnNewButton_2_2.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton_2_2.setBounds(414, 40, 103, 21);
		panel_1.add(btnNewButton_2_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(383, 106, 392, 250);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
	}
}
