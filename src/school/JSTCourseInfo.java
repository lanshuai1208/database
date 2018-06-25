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

public class JSTCourseInfo {
	
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
	public static JButton b1,b2,b3;
	
	public static double gpa = 0;//绩点
	public static double all = 0;//所有学分
	public static double gotgrade = 0;//已得到的所有学分
	public static int credit = 0;//学分
	
	
	public JSTCourseInfo(String sno){

		JFrame f = new JFrame("修读课程查询");
		f.setSize(400, 300);
		f.setLocation(540, 280);
		f.setLayout(null);

		l1 = new JLabel("");
		l1.setSize(200, 30);
		l1.setLocation(50, 175);
		

		String[] columns = {"课程号","课程名称","学分","成绩"};
		model = new DefaultTableModel(null,columns);
		
		
		table = new JTable(model);
		
		//model.addRow(new Object[]{"0001","数据结构",95});
		
		pane = new JScrollPane(table);
		pane.setBounds(50, 10, 300, 150);
		
		b1 = new JButton("添加");
		b1.setSize(80, 30);
		b1.setLocation(40, 220);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//选课
				f.dispose();
				JSTUnchoicedCourses uc = new JSTUnchoicedCourses(sno);
			}
		});
		
		b2 = new JButton("退选");
		b2.setSize(80, 30);
		b2.setLocation(160, 220);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//退课
				JSTCancelCourses uc = new JSTCancelCourses(sno,f);
			}
		});
		
		b3 = new JButton("返回");
		b3.setSize(80, 30);
		b3.setLocation(280, 220);
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//退课
				f.dispose();
				JOpera_student os = new JOpera_student(sno);
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
    	
    	try{
    		sql = "select * from sc where sno = '" + sno + "'";
        	System.out.println(sql);
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    		int i = 0;
    		while(rs.next()){
    			String cno = rs.getString("cno");
    			int grade = rs.getInt("grade");
    			
    			sql1 = "select * from course where cno = '" + cno + "'";
    			System.out.println(sql1);
    			stmt1 = conn.createStatement();
    			rs1 = stmt1.executeQuery(sql1);
    			
    			String cname = null;
    			while(rs1.next()){
    				cname = rs1.getString("cname");
    	   			credit = rs1.getInt("ccredit");
        			
        			gotgrade += grade*credit/100;
        			all += credit;
    			}
    		
    			/*System.out.println("*******************");
    			System.out.println("课程号:" + cno);
    			//System.out.println("课程名称" + cname);
    			System.out.println("课程分数" + grade);
    			System.out.println("*******************");
    			tableM.addRow(new Object[] {cno,null,grade} );*/
    			for(int j = 0;j<3;j++){
    				model.isCellEditable(i++, j);
    			}
    			model.addRow(new Object[] {cno,cname,credit,grade});
    		}
    		
    		stmt.close();
    	System.out.println("Success to execute update");
    	}catch( Exception e){
    		e.printStackTrace();
    	}
		
    	gpa = (gotgrade/all - 0.5)*10;//绩点
    	
    	System.out.println("gpa: " + String.format("%.2f", gpa));//保留两位小数
    	
    	l1.setText("你的绩点为:" + String.format("%.2f", gpa));
		f.add(pane, BorderLayout.CENTER);
		f.add(l1);
		f.add(b1);f.add(b2);f.add(b3);
		f.setVisible(true);
	}
}
