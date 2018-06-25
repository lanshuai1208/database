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

public class JstudentInfo {
	public static JButton b3,b1,b2;
	public static JLabel l0,l1,l11,l2,l22,l3,l33,l4,l44,l5,l55,l6,l66,l7,l77,l8,l88;
	public static Font font,font1;
	
	public static final String dbdriver = "com.mysql.jdbc.Driver";
    public static final String dburl = "jdbc:mysql://localhost:3306/school?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String dbuser = "root";
    public static final String dbpass = "lanshuai2468";
    public static Connection conn;
	public static Statement stmt;
	public static ResultSet rs;
	public static String sql;
	
	
	public JstudentInfo(String sno_sname ,boolean withsno,boolean isST){
		System.out.println("my sno:" + sno_sname);
		JFrame f = new JFrame("学生基本信息");
		f.setSize(1000, 600);
		f.setLocation(160, 80);
		f.setLayout(null);
		
		l0 = new JLabel("学生基本信息");
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
		
		l11 = new JLabel("学号:");
		l11.setSize(100,50);
		l11.setLocation(180, 50);
		l11.setFont(font1);
		l11.setForeground(Color.DARK_GRAY);
		
		l2 = new JLabel("姓名:");
		l2.setSize(100, 50);
		l2.setLocation(80,100);
		l2.setFont(font1);
		l2.setForeground(Color.darkGray);

		l22 = new JLabel("学号:");
		l22.setSize(100,50);
		l22.setLocation(180, 100);
		l22.setFont(font1);
		l22.setForeground(Color.DARK_GRAY);
		
		l3 = new JLabel("性别:");
		l3.setSize(100, 50);
		l3.setLocation(80,150);
		l3.setFont(font1);
		l3.setForeground(Color.darkGray);
		
		l33 = new JLabel("性别:");
		l33.setSize(100, 50);
		l33.setLocation(180,150);
		l33.setFont(font1);
		l33.setForeground(Color.darkGray);
		
		l4 = new JLabel("年龄:");
		l4.setSize(100, 50);
		l4.setLocation(80,200);
		l4.setFont(font1);
		l4.setForeground(Color.darkGray);
		
		l44 = new JLabel("年龄:");
		l44.setSize(100, 50);
		l44.setLocation(180,200);
		l44.setFont(font1);
		l44.setForeground(Color.darkGray);
		
		l5 = new JLabel("身份证号:");
		l5.setSize(100, 50);
		l5.setLocation(80,250);
		l5.setFont(font1);
		l5.setForeground(Color.darkGray);
		
		l55 = new JLabel("身份证号:");
		l55.setSize(300, 50);
		l55.setLocation(180,250);
		l55.setFont(font1);
		l55.setForeground(Color.darkGray);
		
		l6 = new JLabel("专业:");
		l6.setSize(100, 50);
		l6.setLocation(80,300);
		l6.setFont(font1);
		l6.setForeground(Color.darkGray);
		
		l66 = new JLabel("专业:");
		l66.setSize(200, 50);
		l66.setLocation(180,300);
		l66.setFont(font1);
		l66.setForeground(Color.darkGray);
		
		l7 = new JLabel("联系电话:");
		l7.setSize(100, 50);
		l7.setLocation(80,350);
		l7.setFont(font1);
		l7.setForeground(Color.darkGray);
		
		l77 = new JLabel("联系电话:");
		l77.setSize(200, 50);
		l77.setLocation(180,350);
		l77.setFont(font1);
		l77.setForeground(Color.darkGray);
		
		l8 = new JLabel("家庭住址:");
		l8.setSize(100, 50);
		l8.setLocation(80,400);
		l8.setFont(font1);
		l8.setForeground(Color.darkGray);
		
		l88 = new JLabel("家庭住址:");
		l88.setSize(300, 50);
		l88.setLocation(180,400);
		l88.setFont(font1);
		l88.setForeground(Color.darkGray);
		
		b3 = new JButton("删除");
		b3.setSize(100, 30);
		b3.setLocation(100, 490);
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JDeleteStudent ds = new JDeleteStudent(l11.getText(),f);
			}
		});
		
		b1 = new JButton("修改");
		b1.setSize(100, 30);
		b1.setLocation(300,490);
		b1.addActionListener(new ActionListener(){
			//修改学生基本信息,学生用户无权修改学号和姓名
			public void actionPerformed(ActionEvent e){
				f.setVisible(false);
				JSTInfoUpdate stu = new JSTInfoUpdate(l11.getText(),isST);
				f.dispose();
			}
		});
		
		b2= new JButton("返回");
		b2.setSize(100, 30);
		b2.setLocation(500,490);
		b2.addActionListener(new ActionListener(){
			//返回学生用户操作主界面
			public void actionPerformed(ActionEvent e){
				f.setVisible(false);
				if(isST){
					JOpera_student js = new JOpera_student(sno_sname);
				}
				else{
					
					JSearchST jst = new JSearchST();
				}
				f.dispose();
			}
		});
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setVisible(true);
		
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
    		if(withsno)
    			sql = "select * from student where sno = '" + sno_sname + "'";
    		else
    			sql = "select * from student where sname = '" + sno_sname + "'";
    		System.out.println("sql:"+sql);
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);

    		while(rs.next())
    		{
    			l11.setText(rs.getString("sno"));
    			l22.setText(rs.getString("sname"));
    			l33.setText(rs.getString("ssex"));
    			l44.setText(rs.getString("sage"));
    			l55.setText(rs.getString("sid"));
    			l66.setText(rs.getString("sdept"));
    			l77.setText(rs.getString("phonenumber"));
    			l88.setText(rs.getString("address"));
    		}
    		
    		
    		//System.out.println(rs.getString("account"));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	f.add(l0);
		f.add(l1);f.add(l11);
		f.add(l2);f.add(l22);
		f.add(l3);f.add(l33);
		f.add(l4);f.add(l44);
		f.add(l5);f.add(l55);
		f.add(l6);f.add(l66);
		f.add(l7);f.add(l77);
		f.add(l8);f.add(l88);
		f.add(b3);f.add(b1);f.add(b2);
		
		//学生不能删除自己的信息
		if(isST)
			b3.setVisible(false);
		
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
