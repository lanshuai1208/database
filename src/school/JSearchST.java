package school;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Vector;

public class JSearchST {
	public static JFrame f;
	public static JLabel l1;
	public static JTextField t1;
	public static JButton b1,b2;
	public static boolean withsno = true;
	
	public static final String dbdriver = "com.mysql.jdbc.Driver";
    public static final String dburl = "jdbc:mysql://localhost:3306/school?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String dbuser = "root";
    public static final String dbpass = "lanshuai2468";
    public static Connection conn;
	public static Statement stmt;
	public static ResultSet rs;
	public static String sql;
	
	
	public JSearchST(){
		f = new JFrame("管理员用户");
		f.setSize(400, 300);
		f.setLocation(540, 280);
		f.setLayout(null);
		
		l1 = new JLabel("请输入学号");
		l1.setSize(90, 30);
		l1.setLocation(50, 100);
		
		t1 = new JTextField("");
		t1.setSize(150, 30);
		t1.setLocation(140, 100);
		
		String[] Jinquiry_mode = new String[] {"按学号查找","按姓名查找"};
		JComboBox cb = new JComboBox(Jinquiry_mode);
		cb.setBounds(150, 30, 100, 30);
		cb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("cb.selectedIndex:" + cb.getSelectedIndex());
				if(cb.getSelectedIndex() == 1){
					//使用学号查询
					withsno = false;
					l1.setText("请输入姓名");
				}
				else{
					//使用姓名查询
					withsno = true;
					l1.setText("请输入学号");
				}
			}
		});
		
		b1 = new JButton("查询");
		b1.setSize(80, 30);
		b1.setLocation(80, 200);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(exist(withsno,t1.getText())){
				f.dispose();
				JstudentInfo st = new JstudentInfo(t1.getText(),withsno, false);
				}
				else{
					JOptionPane.showMessageDialog(null, "无此用户");
				}
			}
		});
		
		b2 = new JButton("返回");
		b2.setSize(80, 30);
		b2.setLocation(230, 200);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.setVisible(false);
				JOpera_manager jm = new JOpera_manager();
				f.dispose();
			}
		});
		
		f.add(cb);
		f.add(l1);
		f.add(t1);
		f.add(b1);
		f.add(b2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		f.setVisible(true);
 		
	}
	
	//判断用户是否存在
	public static boolean exist(boolean withsno,String sno_sname){
		
		try{
    		Class.forName(dbdriver);
    		System.out.println("Success loading MySQL driver!");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("Error to loading MySQL driver");
    	}
    	
    	try{
    		System.out.println("Success to connect mysql");
    		conn = DriverManager.getConnection(dburl, dbuser, dbpass);
    	}
    	catch(SQLException e){
    		System.out.println("Error to connect mysql");
    		e.printStackTrace();
    	}
    	
		if(withsno)
			sql = "select * from student where sno = '" + sno_sname + "'";
		else
			sql = "select * from student where sname = '" + sno_sname + "'";
		System.out.println("sql:"+sql);
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int count = 0;
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count==0)
				return false;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return true;
	}
	
}
