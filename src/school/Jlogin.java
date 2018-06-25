package school;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Jlogin {
	public static JFrame f;
	public static JLabel l;
	public static Font font;
	public static JButton b1,b2;
	public Jlogin(){
		f = new JFrame("学生信息管理系统");
		f.setSize(400, 300);
		f.setLocation(540, 280);
		f.setLayout(null);
		
		//设置登录界面标题
		l = new JLabel("用户登录");
		l.setSize(300, 50);
		l.setLocation(120, 40);
		l.setForeground(Color.red);
		font = new Font("Monospaced",Font.BOLD,32);
		l.setFont(font);
		
		b1 = new JButton("学生用户");
		b1.setBounds(140, 120, 100, 40);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ){
				studentlanding();
			}
		});
		
		b2 = new JButton("管理员");
		b2.setBounds(140, 190, 100, 40);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				managerlanding();
			}
		});
		
		f.add(l);
		f.add(b1);
		f.add(b2);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setVisible(true);
	}
	
	//打开学生登录界面
	public static void studentlanding(){
		f.dispose();
		JSTlogin st = new JSTlogin();
		
	}
	
	//打开管理员登录界面
	public static void managerlanding(){
		f.dispose();
		JMNlogin mn = new JMNlogin();
	}
	
	public static void main (String[] args){
		Jlogin l = new Jlogin();
		
	}
	
	
}
