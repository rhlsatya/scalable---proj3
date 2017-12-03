import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
import java.io.*;

public class client_gui extends JFrame implements ActionListener {
 
 private JFrame frame;
 private JPanel p, p1, p2;
 private JButton getFile;
 private JTextField box;
 //private JTextField toRead;
 private JTextArea toRead;
 public static void main(String[] args) throws IOException {
 
 new client_gui();
 }

 public client_gui()throws IOException
 {
	 frame = new JFrame("First frame");
	 //frame.setLayout(new GridLayout(1,3));
	 
	 p = new JPanel();
	 //p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 //p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	 
	 getFile = new JButton("Get File");
	 getFile.addActionListener(this);
	 
	 box = new JTextField(10);
	 toRead = new JTextArea();
	 toRead.setEditable(false);
	 
	 p.add(box);
	 p.add(getFile);
	 //p2.add(toRead);
	 
	 
	 
	 frame.getContentPane().add(BorderLayout.SOUTH,p);
	 //frame.getContentPane().add(BorderLayout.NORTH,mb);
	 frame.getContentPane().add(BorderLayout.CENTER,toRead);
	 
	 frame.setSize(800,600);
	 frame.setResizable(true);
	 frame.setLocationRelativeTo(null);
	 frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	 frame.setVisible(true);
	 
	 
 }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	try {
		String s = new String(box.getText());
		client cl = new client();
	
		cl.receiveFile(s);
		FileReader reader = new FileReader( s + "_1" );
	    BufferedReader br = new BufferedReader(reader);
	    toRead.read( br, null );
	    br.close();
	    toRead.requestFocus();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}
 
}