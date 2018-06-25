package school;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class JSTPasswdUpdate {
	//数据库操作
	public static final String dbdriver = "com.mysql.jdbc.Driver";
	public static final String dburl = "jdbc:mysql://localhost:3306/school?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String dbuser = "root";
	public static final String dbpass = "lanshuai2468";
	public static Connection conn;
	public static Statement stmt;
	public static ResultSet rs;
		
	public static JFrame f;
	public static JLabel l1,l2,l3;
	public static JPasswordField t1,t2,t3;
	public static JButton b1,b2;
	
	public static String passwd,realpasswd,newpasswd1,newpasswd2;
	public static String sql;
	public static String ssno;
	public JSTPasswdUpdate(String sno){
		ssno  =sno;
		System.out.println("sno:" + sno + " ssno:" + ssno);
		System.out.println("ssno:" + ssno);
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
    	
    		
		
		f = new JFrame("学生用户密码修改");
		f.setSize(400, 300);
		f.setLocation(540, 280);
		f.setLayout(null);
		
		l1 = new JLabel("输入原密码:");
		l1.setSize(100, 30);
		l1.setLocation(50, 30);
		
		t1 = new JPasswordField();
		t1.setSize(150, 30);
		t1.setLocation(150, 30);
		
		l2 = new JLabel("输入新密码：");
		l2.setSize(100, 30);
		l2.setLocation(50, 80);
		
		t2 = new JPasswordField();
		t2.setSize(150, 30);
		t2.setLocation(150, 80);
		
		l3 = new JLabel("再次输入新密码：");
		l3.setSize(110, 30);
		l3.setLocation(50, 130);
		
		t3 = new JPasswordField();
		t3.setSize(150, 30);
		t3.setLocation(150, 130);
		
		b1 = new JButton("确定");
		b1.setSize(80, 30);
		b1.setLocation(80, 200);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				checkPasswd();
			}
		});
		
		b2 = new JButton("退出");
		b2.setSize(80, 30);
		b2.setLocation(200, 200);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOpera_student st = new JOpera_student(sno);
			}
		});
		
		f.add(l1);f.add(t1);
		f.add(l2);f.add(t2);
		f.add(l3);f.add(t3);
		f.add(b1);f.add(b2);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setVisible(true);
	}
    
    public static void checkPasswd(){
    	try{
    		sql = "select password from stlogin where account = '" + ssno + "'";
    		System.out.println("sql:"+sql);
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    		
    		passwd = t1.getText();
    		while(rs.next()){
    			realpasswd = rs.getString("password");
    		}
    		System.out.println("realpasswd:" + realpasswd);
    		if(!realpasswd.equals(passwd)){
    			System.out.println("与原密码不一致！");
    			JOptionPane.showMessageDialog(null, "与原密码不一致");
    		}
    		else{
    			newpasswd1 = t2.getText();
    			newpasswd2 = t3.getText();
    			if(!newpasswd1.equals(newpasswd2)){
    				System.out.println("两次输入的密码不一致！");
    				JOptionPane.showMessageDialog(null, "两次输入密码不一致");
    			}
    			else{
    				if(newpasswd1.equals("") || newpasswd2.equals("")){
    					System.out.println("新密码不能为空");
    					JOptionPane.showMessageDialog(null, "新密码不能为空");
    				}
    				else{
    					sql = "update stlogin set password = '" + newpasswd1 + "' where account = '" + ssno + "'";
        				System.out.println("sql:"+sql);
        				stmt = conn.createStatement();
        	    		stmt.executeUpdate(sql);
        	    		System.out.println("密码更换成功");
        	    		JOptionPane.showMessageDialog(null, "密码更换成功");
    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
