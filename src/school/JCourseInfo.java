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

public class JCourseInfo {
	public static final String dbdriver = "com.mysql.jdbc.Driver";
    public static final String dburl = "jdbc:mysql://localhost:3306/school?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String dbuser = "root";
    public static final String dbpass = "lanshuai2468";
    public static Connection conn,conn1;
	public static Statement stmt,stmt1;
	public static ResultSet rs,rs1;
	public static String sql,sql1;
	
	public static JFrame f;
	//课程编号，课程名称，修读人数，平均成绩，成绩标准差，通过率，
	public static JLabel l1,l2,l3,l4,l5,l6,l7;
	public static JLabel l22,l33,l44,l55,l66,l77;
	public static JButton b1,b2;
	public static Font font;
	
	public JCourseInfo(String cname_cno,boolean withcname){
		f = new JFrame("课程信息");
		f.setSize(660, 450);
		f.setLocation(340, 170);
		f.setLayout(null);
		
		l1 = new JLabel("课程名",JLabel.CENTER);
		l1.setSize(230, 40);
		l1.setLocation(200, 30);
		font = new Font("宋体",Font.BOLD,24);
		l1.setForeground(Color.BLUE);
		l1.setFont(font);
		//l1.setOpaque(true);
		//l1.setBackground(Color.red);
		
		//l1.setOpaque(true);
		//l1.setBackground(Color.BLUE);
		
		l2 = new JLabel("课程号");
		l2.setSize(100, 30);
		l2.setLocation(100,100 );
		//l2.setOpaque(true);
		//l2.setBackground(Color.red);
		
		l22 = new JLabel("XXXX");
		l22.setSize(100, 30);
		l22.setLocation(200, 100);
		
		l3 = new JLabel("修读人数");
		l3.setSize(100, 30);
		l3.setLocation(350, 100);
		
		l33 = new JLabel("XX");
		l33.setSize(100, 30);
		l33.setLocation(450, 100);
		
		l4 = new JLabel("平均成绩");
		l4.setSize(100, 30);
		l4.setLocation(100, 150);
		
		l44 = new JLabel("95.0");
		l44.setSize(100, 30);
		l44.setLocation(200, 150);
		
		l5 = new JLabel("标准差");
		l5.setSize(100, 30);
		l5.setLocation(350, 150);
		
		l55 = new JLabel("4.5");
		l55.setSize(100, 30);
		l55.setLocation(450, 150);
		
		l6 = new JLabel("通过率");
		l6.setSize(100, 30);
		l6.setLocation(100, 200);
		
		l66 = new JLabel("98.5%");
		l66.setSize(100, 30);
		l66.setLocation(200, 200);
		
		l7 = new JLabel("学分");
		l7.setSize(100, 30);
		l7.setLocation(350, 200);
		
		l77 = new JLabel("");
		l77.setSize(100, 30);
		l77.setLocation(450, 200);
		
		b1 = new JButton("查询全体学生成绩");
		b1.setSize(150,30);
		b1.setLocation(150, 300);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.dispose();
				JGradeList gl = new JGradeList(l22.getText());
			}
		});
		
		b2 = new JButton("返回");
		b2.setSize(150, 30);
		b2.setLocation(350, 300);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.dispose();
				JSearchCourse sc = new JSearchCourse();
			}
		});
		
		//开始使用数据库处理
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
    	
    	double sumGrade = 0,aveGrade = 0,sd = 0 ;//标准差
		int sumST = 0;//所有本科目成绩之和以及选本科目的总人数
		int sumpassST = 0;//通过科目的人数
    	
    	try{
    		if(withcname)
    			sql = "select * from course where cname = '" + cname_cno + "'";
    		else
    			sql = "select * from course where cno = '" + cname_cno + "'";
        	System.out.println(sql);
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    		while(rs.next()){
    			l1.setText(rs.getString("cname"));//课程名称
    			l22.setText(rs.getString("cno"));//课程编号
    			l77.setText(rs.getString("ccredit"));
    		}

    		//获取修读本科目的总人数
    		sql = "select count(*) from sc where cno = '" + l22.getText() + "'";
    		System.out.println(sql);
    		rs = stmt.executeQuery(sql);
    		while(rs.next()){
    			sumST = rs.getInt(1);
    			//sumGrade +=rs.getInt("grade");
    		}
    		
    		//获得修的本科目的人的总成绩
    		sql = "select * from sc where cno = '" + l22.getText() + "'";
    		System.out.println(sql);
    		rs = stmt.executeQuery(sql);
    		while(rs.next()){
    			sumGrade +=rs.getInt("grade");
    			if(rs.getInt("grade") >=60)
    				++sumpassST;
    		}
    		
    		aveGrade = sumGrade/sumST;//平均成绩
    		//计算标准差
    		sql = "select * from sc where cno = '" + l22.getText() + "'";
    		System.out.println(sql);
    		rs = stmt.executeQuery(sql);
    		while(rs.next()){
    			sd += Math.pow(aveGrade-rs.getInt("grade"), 2); 
    			
    		}
    		sd = Math.sqrt(sd/sumST);
    		System.out.println("sumST:" + sumST);
    		System.out.println("sumGrade:" + sumGrade);
    		System.out.println("sumpassST:" + sumpassST);
    		stmt.close();
    		
    		//计算平均差
    	System.out.println("Success to execute update");
    	}catch( Exception e){
    		e.printStackTrace();
    	}
		
    	l33.setText(String.valueOf(sumST));
    	//同样，刚建立的课程
    	if(sumST!=0)
    		l44.setText(String.valueOf(aveGrade));
    	else
    		l44.setText("0");
    	
    	
    	l55.setText(String.valueOf(sd));
		//刚建的课程没有学生选，直接除以0回导致进程崩溃
		if(sumST != 0)
			l66.setText(String.valueOf(100*sumpassST/sumST)+"%");
		else
			l66.setText("0");
		
		
		
		f.add(l1);
		f.add(l2);f.add(l22);f.add(l3);f.add(l33);
		f.add(l4);f.add(l44);f.add(l5);f.add(l55);
		f.add(l6);f.add(l66);f.add(l7);f.add(l77);
		f.add(b1);f.add(b2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		
	}
}
