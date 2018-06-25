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

public class JSearchCourse {
	
	public static JFrame f;
	public static JLabel l1;
	public static JTextField t1;
	public static JButton b1,b2;

	public static boolean withcname;//是否使用课程名称查询
	public static String cname_cno;
	
	public JSearchCourse(){ 
		f = new JFrame("查询课程");
		f.setSize(400, 300);
		f.setLocation(540, 280);
		f.setLayout(null);
		
		String[] Jinquiry_mode = new String[] {"按课程名查找","按课程号查找"};
		JComboBox cb = new JComboBox(Jinquiry_mode);
		cb.setBounds(120, 30, 150, 30);
		cb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("cb.selectedIndex:" + cb.getSelectedIndex());
				if(cb.getSelectedIndex() == 1){
					//使用课程号查询
					withcname = false;
					l1.setText("请输入课程号");
				}
				else{
					//使用课程名查询
					withcname = true;
					l1.setText("请输入课程名");
				}
			}
		});
		
		l1 = new JLabel("请输入课程名");
		l1.setSize(100, 30);
		l1.setLocation(50, 100);
		
		t1 = new JTextField("");
		t1.setSize(150, 30);
		t1.setLocation(140, 100);
		
		b1 = new JButton("查询");
		b1.setSize(80, 30);
		b1.setLocation(80, 200);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(cb.getSelectedIndex() == 0){
					withcname = true;
				}
				else{
					withcname = false;
				}
				f.dispose();
				JCourseInfo ci = new JCourseInfo(t1.getText(),withcname);
			}
		});
		
		b2 = new JButton("返回");
		b2.setSize(80, 30);
		b2.setLocation(230, 200);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.dispose();
				JOpera_manager jm = new JOpera_manager();
			}
		});
		
		f.add(cb);
		f.add(l1);
		f.add(t1);
		f.add(b1);
		f.add(b2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
}
