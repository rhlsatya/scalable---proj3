import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class client_gui extends JFrame implements ActionListener {
 
 private JFrame frame;
 private JPanel p, p1;
 private JButton getFile;
 private JTextField box;
 public static void main(String[] args) {
 
 new client_gui();
 }

 public client_gui()
 {
	 frame = new JFrame("First frame");
	 frame.setLayout(new GridLayout(1,2));
	 
	 p = new JPanel(new FlowLayout());
	 p1 = new JPanel(new FlowLayout());
	 
	 getFile = new JButton("Get File");
	 getFile.addActionListener(this);
	 
	 box = new JTextField(10);
	 
	 p.add(getFile);
	 p1.add(box);
	 frame.add(p1);
	 frame.add(p);
	 
	 frame.setSize(800,600);
	 frame.setResizable(true);
	 frame.setLocationRelativeTo(null);
	 frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	 frame.setVisible(true);
	 
	 
 }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	String s = new String(box.getText());
	client cl = new client();
	try {
		cl.receiveFile(s);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}
 
}