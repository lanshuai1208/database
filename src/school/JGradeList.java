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
import javax.swing.*;

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

import java.util.*;

public class JGradeList {
	public static final String dbdriver = "com.mysql.jdbc.Driver";
    public static final String dburl = "jdbc:mysql://localhost:3306/school?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String dbuser = "root";
    public static final String dbpass = "lanshuai2468";
    public static Connection conn;
	public static Statement stmt,stmt1;
	public static ResultSet rs,rs1;
	public static String sql,sql1;
	
	public static JFrame f;
	public static JLabel l1;
	public static Font font;
	public static DefaultTableModel model;
	public static JScrollPane pane;
	public static JTable table;
	public static JPanel jp1;
	public static JButton b1,b2;
	public JGradeList(String cno){
		String sno = null,cname = null,sname = null;
		double grade = 0;
		
		JFrame f = new JFrame("修读课程查询");
		f.setSize(400, 300);
		f.setLocation(540, 280);
		f.setLayout(null);
		
		String[] columns = {"名次","学号","姓名","成绩"};
			  
		model = new DefaultTableModel(null,columns);
		
		table = new JTable(model);
		
		pane = new JScrollPane(table);
		pane.setBounds(50, 10, 300, 200);
		
		b1 = new JButton("更改学生成绩");
		b1.setSize(120, 30);
		b1.setLocation(60, 220);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JUpdateSTGrade cg = new JUpdateSTGrade(cno,f);
			}
		});
		
		b2 = new JButton("返回");
		b2.setSize(120, 30);
		b2.setLocation(230, 220);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JCourseInfo ci = new JCourseInfo(cno,false);
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
    	int i = 1;
    	
    	try{
    		sql = "select cname from course where cno = '" + cno + "'";
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    		while(rs.next()){
    			cname = rs.getString("cname");
    			f.setTitle("成绩表 - " + cname);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	try{

    		sql = "select * from sc where cno = '" + cno + "' order by grade desc" ;//降序
    		System.out.println("sql:" + sql);
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    		while(rs.next()){
    			sno = rs.getString("sno");
    			grade = rs.getInt("grade");
    			sql1 = "select * from student where sno = '" + sno + "'";
    			stmt1 = conn.createStatement();
    			rs1 = stmt1.executeQuery(sql1);
    			while(rs1.next()){
    				sname = rs1.getString("sname");
    			}
    			model.addRow(new Object[] {i++,sno,sname,grade});
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
		f.add(pane);
		f.add(b1);
		f.add(b2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
