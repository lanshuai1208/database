package school;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class JSTCancelCourses {
	public static final String dbdriver = "com.mysql.jdbc.Driver";
    public static final String dburl = "jdbc:mysql://localhost:3306/school?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String dbuser = "root";
    public static final String dbpass = "lanshuai2468";
    public static Connection conn,conn1;
	public static Statement stmt,stmt1;
	public static ResultSet rs,rs1;
	public static String sql,sql1;
	
	public static JFrame f;
	public static JLabel l1;
	public static JTextField t1;
	public static JButton b1,b2;
	
	public JSTCancelCourses(String sno,JFrame pf){
		f = new JFrame("退课");
		f.setSize(300, 200);
		f.setLocation(590, 350);
		f.setLayout(null);
		
		l1 = new JLabel("请输入课程号");
		l1.setSize(80, 20);
		l1.setLocation(20, 30);
		
		t1 = new JTextField();
		t1.setSize(100, 20);
		t1.setLocation(150, 30);
		
		b1 = new JButton("确定");
		b1.setSize(80, 20);
		b1.setLocation(50, 130);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pf.dispose();
				f.dispose();
				tuike(sno,t1.getText());
				JSTCourseInfo ci = new JSTCourseInfo(sno);
			}
		});
		
		b2 = new JButton("返回");
		b2.setSize(80, 20);
		b2.setLocation(150, 130);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.dispose();
			}
		});
		
		f.add(l1);f.add(t1);
		f.add(b1);f.add(b2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
	public static void tuike(String sno,String cno){
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
    	
    	try{
    		sql = "delete from sc where sno = '" + sno + "' and cno = '" + cno + "'";
    		System.out.println(sql);
    		stmt = conn.createStatement();
    		stmt.executeUpdate(sql);
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
