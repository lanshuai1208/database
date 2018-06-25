package school;

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

public class JDeleteStudent {
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
	public static JButton b1,b2;
	
	public JDeleteStudent(String sno,JFrame pf) {
		// TODO Auto-generated constructor stub
		f = new JFrame("删除学生");
		f.setSize(300, 200);
		f.setLocation(450, 300);
		f.setLayout(null);
		
		l1 = new JLabel("<html>确认删除吗?<br>如果删除的话登录表<br>和选课表中该学生相关<br>数据也会删除</html>");//JLabel换行的艺术
		l1.setSize(200, 100);
		l1.setLocation(50, 0);
		
		b1 = new JButton("删除");
		b1.setSize(80, 30);
		b1.setLocation(50, 120);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//删除之后返回到管理员主界面
				f.dispose();
				pf.dispose();
				deleteST(sno);
				JOpera_manager jm = new JOpera_manager();
			}
		});
		
		b2 = new JButton("返回");
		b2.setSize(80, 30);
		b2.setLocation(150, 120);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.dispose();
			}
		});
		
		
		f.add(l1);
		f.add(b1);f.add(b2);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void deleteST(String sno)
	{
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
    		sql = "delete from student where sno = '" + sno + "'";
    		System.out.println(sql);
    		stmt = conn.createStatement();
    		stmt.executeUpdate(sql);
    		sql = "delete from stlogin where account = '" + sno + "'";
    		stmt.executeUpdate(sql);
    		stmt.close();
        	conn.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
	}
}
