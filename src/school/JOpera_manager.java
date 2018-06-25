package school;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class JOpera_manager {
	public static JFrame f;
	public static JLabel l1,l2,l3;
	public static JButton b1,b2,b3,b4,b5,b6;
	
	public JOpera_manager(){
		f = new JFrame("管理员用户");
		f.setSize(400, 300);
		f.setLocation(540, 280);
		f.setLayout(null);
		
		b1 = new JButton("增加学生信息");
		b1.setSize(200, 30);
		b1.setLocation(50, 20);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.dispose();
				JAddStudent js = new JAddStudent();
			}
		});
		
		b2 = new JButton("查询学生信息");
		b2.setSize(200,30);
		b2.setLocation(50, 70);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//f.setVisible(false);
				f.dispose();
				JSearchST ss = new JSearchST();
				
			}
		});
		
		b3 = new JButton("查询选课情况");
		b3.setSize(200, 30);
		b3.setLocation(50, 120);
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.dispose();
				JSearchCourse jc = new JSearchCourse();
				
			}
		});
		
		b5 = new JButton("新建/删除课程");
		b5.setSize(200,30);
		b5.setLocation(50, 170);
		b5.addActionListener(new ActionListener(){
			//删除/新建课程
			public void actionPerformed(ActionEvent e){
				//f.dispose();
				JChoiceDelOrNewCourse cdn = new JChoiceDelOrNewCourse(f);
			}
		});
		
		
		
		b4 = new JButton("注销登录");
		b4.setSize(200,30);
		b4.setLocation(50, 220);
		b4.addActionListener(new ActionListener(){
			//确认登录信息
			public void actionPerformed(ActionEvent e){
				f.dispose();
				JMNlogin mn = new JMNlogin();
			}
		});
		
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b5);
		f.add(b4);
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setVisible(true);
	}
}
