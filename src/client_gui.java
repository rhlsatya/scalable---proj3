import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import com.mysql.jdbc.PerConnectionLRUFactory;

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
 private String uname;
 private static String address;
 private JTextField box;
 private JRadioButton r1;
 private JRadioButton r2;
 private ButtonGroup bg;
 private JLabel label;
 private String action;
 private JScrollPane pane;
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
        	 pane = new JScrollPane(toRead);
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
        	 frame.getContentPane().add(BorderLayout.CENTER,pane);
        	 
        	 frame.setSize(800,600);
        	 frame.setResizable(true);
        	 frame.setLocationRelativeTo(null);
        	 frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        	 frame.setVisible(true);    	 
         }
     });
 }
 
 public client_gui(String adr)throws IOException
 {
	 
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
	    	 	uname = new String(username.getText());
	    	 	String pass = new String(password.getText());
	    	 	client_security cs;
			try {
				cs = new client_security();
				
	    	 		boolean b = cs.verify_client(uname, pass);
	    	 		System.out.println("back from verification");
	    	 		if(b == true)
	    	 		{
	    	 			createFrame();
	    	 			frame_login.dispose();
	    	 		}
	    	 		else
	    	 		{
	    	 			username.setText(null);
	    	 			password.setText(null);
	    	 			label.setText("<html> <font color='red'>Incorrect Username or Password</font></html>");
	    	 		}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     }
	 });
	 
	 frame_login = new JFrame("Login");
	 //frame.setLayout(new GridLayout(1,3));
	 p = new JPanel(new GridLayout(3,1));
	 //p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	 //p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	 
	 label = new JLabel();
	 p.add(l1);
	 p.add(username);
	 p.add(l2);
	 p.add(password);
	 //p.add(login);
	 //p2.add(toRead);
	 
	 //frame.getContentPane().add(BorderLayout.NORTH,returnFile);
	 frame_login.add(BorderLayout.NORTH,label);
	 frame_login.add(BorderLayout.CENTER,p);
	 frame_login.add(BorderLayout.SOUTH,login);
	 //frame.getContentPane().add(BorderLayout.CENTER,toRead);
	 
	 frame_login.setSize(420,130);
	 frame_login.setResizable(false);
	 frame_login.setLocationRelativeTo(null);
	 frame_login.setDefaultCloseOperation(EXIT_ON_CLOSE);
	 frame_login.setVisible(true);
	 
 }
 
 
 public void verify_user_access()
 {
	 
 }

public void getFile() 
{
	// TODO Auto-generated method stub
	try 
	{
		//String s = new String(box.getText());
		client cl = new client();
		buttonModel = bg.getSelection();
		action = buttonModel.getActionCommand();
		for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                action = button.getText();
            }
        }
		client_security cs = new client_security();
		boolean b = cs.verify_file_access(uname, fileName, action);
		System.out.println("back from file access");
		if(b == true)
		{
		if(action.equals("WRITE"))
			toRead.setEditable(true);
		cl.receiveFile(action, address, fileName);
		FileReader reader = new FileReader(("1_" + fileName + ".txt"));
	    BufferedReader br = new BufferedReader(reader);
	    toRead.read( br, null );
	    br.close();
	    toRead.requestFocus();
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "You do not have " + action.toLowerCase() + " access to that file!");
			box.setText(null);
			
		}
	} 
	catch (IOException e1) 
	{
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}

public static void get_address(String adr)
{
	address = adr;
}

public void returnFile() 
{
	if(action.equals("WRITE"))
	{
	// TODO Auto-generated method stub
	try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(("1_" + fileName + ".txt")))) 
	{
	    toRead.write(fileOut);
	    client cl = new client();
	    System.out.println("filename - " + fileName + "address  --" + address);
	    cl.sendFile(address, (fileName + ".txt"));
	   
	}
	catch (IOException e1) 
	{
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	toRead.setText(null);
}



@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
 
}