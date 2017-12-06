import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class client_gui extends JFrame implements ActionListener {
 private String fileName;
 private JFrame frame;
 private JFrame frame_login;
 private JPanel p, p1, p2;
 private JButton getFile;
 private JButton returnFile;
 
 private JTextField box;
 private JRadioButton r1;
 private JRadioButton r2;
 private ButtonGroup bg;
 //private JTextField toRead;
 private JTextArea toRead;
 private ButtonModel buttonModel;
 public static void main(String[] args) throws IOException {
 
 new client_gui();
 }

 
 public void createFrame()
 {

     EventQueue.invokeLater(new Runnable()
     {
         @Override
         public void run()
         {
        	 frame = new JFrame("First frame");
        	 //frame.setLayout(new GridLayout(1,3));
        	 
        	 p = new JPanel();
        	 //p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        	 //p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        	 
        	 getFile = new JButton("Get File");
        	 returnFile = new JButton("Return File");
        	 getFile.addActionListener(
        			 new ActionListener() 
        	 {

        		@Override
        		public void actionPerformed(ActionEvent e) 
        		{
        			fileName = new String(box.getText());
        			getFile();
        		}
        		 
        	 }
        			 );
        	 
        	 returnFile.addActionListener(
        			 new ActionListener() 
        	 {

        		@Override
        		public void actionPerformed(ActionEvent e) {
        			returnFile();
        		}
        		 
        	 }
        			 );
        	 
        	 box = new JTextField(10);
        	 toRead = new JTextArea();
        	 toRead.setEditable(false);
        	 
        	 r1 = new JRadioButton("READ");
        	 r2 = new JRadioButton("WRITE"); 
        	 
        	 bg = new ButtonGroup();    
        	 bg.add(r1);
        	 bg.add(r2); 
        	 r1.setSelected(true);
        	 
        	 p.add(r1);
        	 p.add(r2);
        	 p.add(box);
        	 p.add(getFile);
        	 //p2.add(toRead);
        	 
        	 frame.getContentPane().add(BorderLayout.NORTH,returnFile);
        	 frame.getContentPane().add(BorderLayout.SOUTH,p);
        	 frame.getContentPane().add(BorderLayout.CENTER,toRead);
        	 
        	 frame.setSize(800,600);
        	 frame.setResizable(true);
        	 frame.setLocationRelativeTo(null);
        	 frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        	 frame.setVisible(true);
        	 
        	 
        	 
        	 
        	 
        	 
             /*JFrame frame = new JFrame("Test");
             frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             try 
             {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             } catch (Exception e) {
                e.printStackTrace();
             }
             JPanel panel = new JPanel();
             panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
             panel.setOpaque(true);
             JTextArea textArea = new JTextArea(15, 50);
             textArea.setWrapStyleWord(true);
             textArea.setEditable(false);
             textArea.setFont(Font.getFont(Font.SANS_SERIF));
             JScrollPane scroller = new JScrollPane(textArea);
             scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
             scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
             JPanel inputpanel = new JPanel();
             inputpanel.setLayout(new FlowLayout());
             JTextField input = new JTextField(20);
             JButton button = new JButton("Enter");
             DefaultCaret caret = (DefaultCaret) textArea.getCaret();
             caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
             panel.add(scroller);
             inputpanel.add(input);
             inputpanel.add(button);
             panel.add(inputpanel);
             frame.getContentPane().add(BorderLayout.CENTER, panel);
             frame.pack();
             frame.setLocationByPlatform(true);
             frame.setVisible(true);
             frame.setResizable(false);
             input.requestFocus();*/
         }
     });
 }
 
 
 public client_gui()throws IOException
 {
	 JTextField username = new JTextField(15);
	 JTextField password = new JTextField(15);
	 JButton login = new JButton("Login");
	 JLabel l1 = new JLabel("Enter Username");
	 JLabel l2 = new JLabel("Enter Password");
	 
	 login.addActionListener( new ActionListener()
	 {
	     public void actionPerformed(ActionEvent e)
	     {
	    	 	String uname = new String(username.getText());
	    	 	String pass = new String(password.getText());
	         createFrame();
	         frame_login.dispose();
	     }
	 });
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 frame_login = new JFrame("Login");
	 //frame.setLayout(new GridLayout(1,3));
	 
	 p = new JPanel(new GridLayout(3,1));
	 //p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 //p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	 
	 
	 p.add(l1);
	 p.add(username);
	 p.add(l2);
	 p.add(password);
	 //p.add(login);
	 //p2.add(toRead);
	 
	 //frame.getContentPane().add(BorderLayout.NORTH,returnFile);
	 frame_login.add(BorderLayout.CENTER,p);
	 frame_login.add(BorderLayout.SOUTH,login);
	 //frame.getContentPane().add(BorderLayout.CENTER,toRead);
	 
	 frame_login.setSize(420,130);
	 frame_login.setResizable(false);
	 frame_login.setLocationRelativeTo(null);
	 frame_login.setDefaultCloseOperation(EXIT_ON_CLOSE);
	 frame_login.setVisible(true);
	 
	 
 }

public void getFile() 
{
	// TODO Auto-generated method stub
	try 
	{
		//String s = new String(box.getText());
		client cl = new client();
		buttonModel = bg.getSelection();
		String action = buttonModel.getActionCommand();
		for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                action = button.getText();
            }
        }
		if(action.equals("WRITE"))
			toRead.setEditable(true);
		cl.receiveFile(action, fileName);
		FileReader reader = new FileReader("1_" + fileName);
	    BufferedReader br = new BufferedReader(reader);
	    toRead.read( br, null );
	    br.close();
	    toRead.requestFocus();
	} 
	catch (IOException e1) 
	{
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}


public void returnFile() 
{
	// TODO Auto-generated method stub
	try (BufferedWriter fileOut = new BufferedWriter(new FileWriter("1_" + fileName))) 
	{
	    toRead.write(fileOut);
	    client cl = new client();
	    System.out.println("filename - " + fileName);
	    cl.sendFile(fileName);
	    toRead.setText(null);
	}
	catch (IOException e1) 
	{
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}



@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
 
}