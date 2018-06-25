package school;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JNewCourse {
	//定义控件
	public static JFrame f;
	public static JLabel l1,l2,l3,l4,l5,l6;
	public static JTextField t1,t2,t3;
	public static JButton b1,b2;
	public static Font font,font1;
	
	//数据库操作
	public static final String dbdriver = "com.mysql.jdbc.Driver";
    public static final String dburl = "jdbc:mysql://localhost:3306/school?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String dbuser = "root";
    public static final String dbpass = "lanshuai2468";
    public static Connection conn;
	public static Statement stmt;
	public static ResultSet rs;
	public static String sql;
	
	public JNewCourse(){
		
		//***********初始化数据库操作****************
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
    	//***************************************
		
		f = new JFrame("新建课程");
		f.setSize(400, 300);
		f.setLocation(540, 280);
		f.setLayout(null);
		
		l1 = new JLabel("课程号:");
		l1.setSize(50, 30);
		l1.setLocation(50, 20);
		
		t1 = new JTextField("");
		t1.setBounds(120, 20, 150, 30);
		
		
		font1 = new Font("宋体",Font.PLAIN,10);
		
		l2 = new JLabel("课程名:");
		l2.setSize(50, 30);
		l2.setLocation(50, 80);
		
		t2 = new JTextField("");
		t2.setBounds(120, 80, 150, 30);
		
		l3 = new JLabel("学分:");
		l3.setSize(50, 30);
		l3.setLocation(50, 140);
		
		t3 = new JTextField("");
		t3.setSize(150, 30);
		t3.setLocation(120, 140);
		//t3.setBounds(120, 140, 150, 30);
		
		
		b1 = new JButton("确认");
		b1.setSize(80, 30);
		b1.setLocation(50, 200);
		b1.addActionListener(new ActionListener(){
			//确认登录信息
			public void actionPerformed(ActionEvent e){
				if(cnoExist(t1.getText())){
					JOptionPane.showMessageDialog(null, "该课程号已存在");
				}else if(cnameExist(t2.getText())){
					JOptionPane.showMessageDialog(null, "课程名已存在");
				}
				else{
					newCourse(t1.getText(),t2.getText(),t3.getText());
					JOpera_manager om = new JOpera_manager();
				}

					
					
			}
		});
		
		
		b2 = new JButton("返回");
		b2.setSize(80, 30);
		b2.setLocation(200, 200);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		
    	
		
		f.add(l1);f.add(t1);
		f.add(l2);f.add(t2);
		f.add(l3);f.add(t3);
		f.add(b1);f.add(b2);		
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	//判断课程号是否已存在
	public static boolean cnoExist(String cno){
		try{
			sql = "select count(*) from course where cno = '" + cno + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int count = 0;
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count == 0)
				return false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
		
	}
	
	//判断课程名是否已存在
	public static boolean cnameExist(String cname){
		try{
			sql = "select count(*) from course where cname = '" + cname + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int count = 0;
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count == 0)
				return false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
			
	}
	
	public static void newCourse(String cno,String cname,String credit){
		try{
			sql = "insert into course(cno,cname,ccredit) values ('" + cno + "','" + cname + "','" + credit + "')";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
