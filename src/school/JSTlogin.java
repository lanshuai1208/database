package school;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextField;

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


//学生用户登录界面
public class JSTlogin {
	//定义控件
	public static JFrame f;
	public static JLabel l1,l2,l3,l4,l5,l6;
	public static JTextField t1;
	public static JPasswordField t2;
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
	
	
	public JSTlogin(){
		f = new JFrame("学生用户登录");
		f.setSize(400, 300);
		f.setLocation(540, 280);
		f.setLayout(null);
		
		l1 = new JLabel("用户名:");
		l1.setSize(50, 30);
		l1.setLocation(50, 30);
		
		t1 = new JTextField("");
		//t1.setText("默认为学号");
		t1.setBounds(120, 30, 150, 30);
		
		l5 = new JLabel("该用户不存在");
		l5.setSize(80, 30);
		l5.setLocation(280, 30);
		font1 = new Font("宋体",Font.PLAIN,10);
		l5.setFont(font1);
		l5.setForeground(Color.red);
		l5.setVisible(false);
		
		l3 = new JLabel("(默认为学号)");
		l3.setSize(80,30);
		l3.setLocation(120, 60);
		font = new Font("Monospaced",Font.BOLD,10);
		l3.setFont(font);
		
		l2 = new JLabel("密码:");
		l2.setSize(50, 30);
		l2.setLocation(50, 100);
		
		t2 = new JPasswordField("");
		//t2.setText("默认为St+身份证后六位");
		t2.setBounds(120, 100, 150, 30);
		
		l6 = new JLabel("密码不正确");
		l6.setSize(80, 30);
		l6.setLocation(280, 100);
		font1 = new Font("宋体",Font.PLAIN,10);
		l6.setFont(font1);
		l6.setForeground(Color.red);
		l6.setVisible(false);
		
		
		l4 = new JLabel("(默认为St+身份证号后六位)");
		l4.setSize(160,30);
		l4.setLocation(120, 130);
		font = new Font("Monospaced",Font.BOLD,10);
		l4.setFont(font);
		
		
		
		b1 = new JButton("确认");
		b1.setSize(80, 30);
		b1.setLocation(50, 200);
		b1.addActionListener(new ActionListener(){
			//确认登录信息
			public void actionPerformed(ActionEvent e){
				l5.setVisible(false);
				l6.setVisible(false);
				STlanding();
			}
		});
		
		b2 = new JButton("返回");
		b2.setSize(80, 30);
		b2.setLocation(200, 200);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				backlanding();
			}
		});
		
		f.add(l1);
		f.add(l2);
		f.add(t1);
		f.add(l3);
		f.add(t2);
		f.add(l4);
		f.add(b1);
		f.add(b2);
		
		f.add(l5);
		f.add(l6);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setVisible(true);
		
	}
	
	//返回登录主界面
	public static void backlanding(){
		f.dispose();
		Jlogin l = new Jlogin();
	}
	
	//使用输入的用户名和密码登录
	public static void STlanding(){
		
		String account = t1.getText().toString();
		String passwd = t2.getText().toString();
		String sql = null;
		rs = null;
		
		//account = "161520327";
		//passwd = "St084850";
		//System.out.println(t1.getText().toString());
		//System.out.println(t2.getText().toString());
		
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
    		sql = "select password from stlogin where account = " + account;
    		System.out.println("sql:"+sql);
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    		if(rs!=null&&rs.next()){
    			//有此用户
    			//System.out.println("fought");
    			String realpasswd = rs.getString("password");
    			
    			if(realpasswd.equals(passwd))
    			{
    				//登录成功
    				System.out.println("登录成功");
    				//打开学生用户操作界面
    				f.setVisible(false);
    				JOpera_student js = new JOpera_student(account);
    				f.dispose();
    			}
    			else
    			{
    				l6.setVisible(true);
    				System.out.println("用户名，密码不匹配");
    			}
    		}
    		else{
    			//System.out.println("not fought");
    			//无此用户
    			l5.setVisible(true);
    			System.out.println("无此用户");
    		}
    		
    		
    		//System.out.println(rs.getString("account"));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	

    	try{
    		stmt.close();
    		//System.out.println("Success to execute update");
    	}catch( Exception e){
    		e.printStackTrace();
    	}

    	
    	try{
    		conn.close();
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
	}
}
