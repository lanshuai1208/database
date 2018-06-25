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

public class JSTUnchoicedCourses{
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
	public static Font font;
	public static DefaultTableModel model;
	public static JScrollPane pane;
	public static JTable table;
	public static JPanel jp1;
	public static JButton b1,b2;
	
	
	public JSTUnchoicedCourses(String sno) {
		// TODO Auto-generated constructor stub
		System.out.println("sno:" + sno);
		f = new JFrame("未选择课程查询");
		f.setSize(400, 300);
		f.setLocation(540, 280);
		f.setLayout(null);
		
		String[] columns = {"课程号","课程名称"};
		model = new DefaultTableModel(null,columns);
		table = new JTable(model);
		pane = new JScrollPane(table);
		pane.setBounds(50, 10, 300, 150);
		
		b1 = new JButton("开始选课");
		b1.setSize(100, 30);
		b1.setLocation(50, 220);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//选课
				JSTChoicingCourse cc = new JSTChoicingCourse(sno,f);
			}
		});
		
		b2 = new JButton("退出");
		b2.setSize(100, 30);
		b2.setLocation(220, 220);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.dispose();
				JSTCourseInfo ci = new JSTCourseInfo(sno);
			}
		});
		
		//找出该学生没有选的课
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
    		sql = "select * from course ";
        	System.out.println(sql);
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    		while(rs.next()){
    			String cno = rs.getString("cno");
    			sql1 = "select * from sc where sno = '" + sno + "' ";//一个空格的BUG好难找啊
    			System.out.println(sql1);
    			stmt1 = conn.createStatement();
    			rs1 = stmt1.executeQuery(sql1);
    			boolean choiced = false;
    			System.out.println("当前正在进行确认的课程号码为" + cno);
    			while(rs1.next()){
    				//选过了
    				System.out.println("学生已选课程号码" + cno);
    				if(rs1.getString("cno").equals(cno)){
    					System.out.println(cno);
    					choiced = true;
    				}
    					
    			}
    		
    			if(!choiced)
    				model.addRow(new Object[] {rs.getString("cno"),rs.getString("cname")});
    		}

    		stmt.close();
    	System.out.println("Success to execute update");
    	}catch( Exception e){
    		e.printStackTrace();
    	}
		
		f.add(pane, BorderLayout.CENTER);
		f.add(b1);f.add(b2);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//f.add(b1);f.add(b2);f.add(b3);
		f.setVisible(true);
	}
	
}
