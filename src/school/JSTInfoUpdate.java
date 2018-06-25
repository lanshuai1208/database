package school;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class JSTInfoUpdate {
	
	public static final String dbdriver = "com.mysql.jdbc.Driver";
    public static final String dburl = "jdbc:mysql://localhost:3306/school?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String dbuser = "root";
    public static final String dbpass = "lanshuai2468";
    public static Connection conn;
	public static Statement stmt;
	public static ResultSet rs;
	public static String sql;
	
	public static JFrame f;
	public static JButton b1,b2;
	public static JLabel l0,l1,l2,l3,l4,l5,l6,l7,l8;
	public static JComboBox cb1,cb2;
	public static JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	public static Font font,font1;
	
	public static String old_sno;//保存管理员修改前的学号
	
	String[] sexs = new String[] {"男","女"};
	String[] majors = new String[] {"计算机","信息安全","软件工程","物联网工程"};
	/*尚未完成：
		1.性别选择下拉框
		2.年龄数字判断，不为空判断
		3.身份证号长度判断
		4.专业选择下拉框
	*/
	public JSTInfoUpdate(String sno,boolean isST){
		f = new JFrame("修改学生基本信息");
		f.setSize(1000, 600);
		f.setLocation(160, 80);
		f.setLayout(null);
		
		l0 = new JLabel("修改学生基本信息");
		l0.setSize(300,50);
		l0.setLocation(400, 10);
		font = new Font("宋体",Font.BOLD,25);
		l0.setFont(font);
		l0.setForeground(Color.BLUE);
		
		
		l1 = new JLabel("学号:");
		l1.setSize(100,50);
		l1.setLocation(80, 50);
		font1 = new Font("宋体",Font.BOLD,20);
		l1.setFont(font1);
		l1.setForeground(Color.DARK_GRAY);
		
		t1 = new JTextField("");
		t1.setSize(100,30);
		t1.setLocation(180, 60);
		//学生用户无权修改姓名和学号
		if(isST)
			t1.setEditable(false);
		
		l2 = new JLabel("姓名:");
		l2.setSize(100, 50);
		l2.setLocation(80,100);
		l2.setFont(font1);
		l2.setForeground(Color.darkGray);

		t2 = new JTextField("");
		t2.setSize(100,30);
		t2.setLocation(180, 110);
		//学生用户无权修改姓名和学号
		if(isST)
			t2.setEditable(false);
		
		l3 = new JLabel("性别:");
		l3.setSize(100, 50);
		l3.setLocation(80,150);
		l3.setFont(font1);
		l3.setForeground(Color.darkGray);
		
		
		cb1 = new JComboBox(sexs);
		cb1.setSize(100, 30);
		cb1.setLocation(180,160);
		
		l4 = new JLabel("年龄:");
		l4.setSize(100, 50);
		l4.setLocation(80,200);
		l4.setFont(font1);
		l4.setForeground(Color.darkGray);
		
		t4 = new JTextField("");
		t4.setSize(100, 30);
		t4.setLocation(180,210);
		
		l5 = new JLabel("身份证号:");
		l5.setSize(100, 50);
		l5.setLocation(80,250);
		l5.setFont(font1);
		l5.setForeground(Color.darkGray);
		
		t5 = new JTextField("");
		t5.setSize(300, 30);
		t5.setLocation(180,260);
		
		l6 = new JLabel("专业:");
		l6.setSize(100, 50);
		l6.setLocation(80,300);
		l6.setFont(font1);
		l6.setForeground(Color.darkGray);
		
		
		cb2 = new JComboBox(majors);
		cb2.setSize(200, 30);
		cb2.setLocation(180, 310);
		
		l7 = new JLabel("联系电话:");
		l7.setSize(100, 50);
		l7.setLocation(80,350);
		l7.setFont(font1);
		l7.setForeground(Color.darkGray);
		
		t7 = new JTextField("");
		t7.setSize(200, 30);
		t7.setLocation(180,360);
		
		l8 = new JLabel("家庭住址:");
		l8.setSize(100, 50);
		l8.setLocation(80,400);
		l8.setFont(font1);
		l8.setForeground(Color.darkGray);
		
		t8 = new JTextField("");
		t8.setSize(300, 30);
		t8.setLocation(180,410);
		
		b1 = new JButton("确定");
		b1.setSize(100, 30);
		b1.setLocation(300,490);
		b1.addActionListener(new ActionListener(){
			//确认登录信息
			public void actionPerformed(ActionEvent e){
				JSTUpdate(sno,isST);
			}
		});
		
		b2= new JButton("放弃");
		b2.setSize(100, 30);
		b2.setLocation(500,490);
		b2.addActionListener(new ActionListener(){
			//确认登录信息
			public void actionPerformed(ActionEvent e){
				f.setVisible(false);
				JstudentInfo js = new JstudentInfo(sno,true,isST);
				f.dispose();
			}
		});
		
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
    	
    	//把默认的内容填入修改界面
    	try{
    		sql = "select * from student where sno = '" + sno + "'";
    		System.out.println("sql:"+sql);
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    		
    		while(rs.next())
    		{
    			t1.setText(rs.getString("sno"));
    			old_sno = t1.getText();
    			t2.setText(rs.getString("sname"));
    			int i;
    			for(i = 0;i < sexs.length;i ++)
    				if(sexs[i].equals(rs.getString("ssex")))
    					cb1.setSelectedIndex(i);
    			t4.setText(rs.getString("sage"));
    			t5.setText(rs.getString("sid"));
    			for(i = 0;i < majors.length;i++)
    				if(majors[i].equals(rs.getString("sdept")))
    					cb2.setSelectedIndex(i);
    			t7.setText(rs.getString("phonenumber"));
    			t8.setText(rs.getString("address"));
    		}
    		f.add(t1);f.add(t2);f.add(cb1);f.add(t4);f.add(t5);f.add(cb2);f.add(t7);f.add(t8);
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		
		f.add(l0);
		f.add(l1);f.add(t1);
		f.add(l2);f.add(t2);
		f.add(l3);f.add(cb1);
		f.add(l4);f.add(t4);
		f.add(l5);f.add(t5);
		f.add(l6);f.add(cb2);
		f.add(l7);f.add(t7);
		f.add(l8);f.add(t8);
		f.add(b1);f.add(b2);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setVisible(true);
	}
	
	public static void JSTUpdate(String sno,boolean isST){
		
		//更改学生信息
    	try{
    		
    		
    		sql = "update student set "  
    				+"sno = '" + t1.getText()
    				+"',sname = '" + t2.getText()
    				+"',ssex = '" + cb1.getSelectedItem()
    				+"',sage = " + t4.getText()
    				+",sid = '" + t5.getText()
    				+"',sdept = '" + cb2.getSelectedItem()
    				+"',phonenumber = '"+ t7.getText()
    				+"',address = '" + t8.getText()
    				+ "' where sno = '" + sno + "'"; 
    		System.out.println("sql:"+sql);
    		stmt = conn.createStatement();
    		stmt.execute(sql);
    		//System.out.println(rs.getString("account"));
    		
    		//如果是管理员操作的话，还要考虑登录密码的修改考虑进去
    		sql = "update stlogin set " 
    				+ "account = '" + t1.getText()
    				+ "' where account = '" + old_sno + "'";
    		System.out.println(sql);
    		stmt = conn.createStatement();
    		stmt.execute(sql);
    		
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
    	
		f.setVisible(false);
		
		//不能用以前的学号了，要使用可能更新的学号
		JstudentInfo js = new JstudentInfo(t1.getText(),true,isST);

		f.dispose();
	}
}
