package school;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JChoiceDelOrNewCourse {
	public static JFrame f;
	public static JButton b1,b2,b3;
	
	public JChoiceDelOrNewCourse(JFrame pf){
		f = new JFrame("删/建课程");
		f.setSize(250, 200);
		f.setLocation(640, 355);
		f.setLayout(null);
		
		b1 = new JButton("新建");
		b1.setSize(150, 30);
		b1.setLocation(45, 20);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JNewCourse nc = new JNewCourse();
				pf.dispose();
				f.dispose();
				
			}
		});
		
		b2 = new JButton("删除");
		b2.setSize(150, 30);
		b2.setLocation(45, 60);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JDeleteCourse dc = new JDeleteCourse();
				pf.dispose();
				f.dispose();
			}
		});
		
		b3 = new JButton("返回");
		b3.setSize(150, 30);
		b3.setLocation(45, 100);
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.dispose();
			}
		});
		
		f.add(b1);
		f.add(b2);
		f.add(b3);
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
