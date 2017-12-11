import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class client_security 
{
	 private static Socket sock;
	 private static Socket sock1;
	 private static Socket sock2;
	 private static String fileName;
	 private BufferedReader br;
	 private BufferedReader br1;
	 private BufferedReader br2;
	 private static PrintStream os;
	 private static PrintStream os1;
	 private static PrintStream os2;
	    
	 public client_security() throws UnknownHostException, IOException
	 {
	 	sock = new Socket("localhost", 7890);
	 	sock1 = new Socket("localhost", 7891);
	 	sock2 = new Socket("localhost", 7892);
	 	br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	 	br1 = new BufferedReader(new InputStreamReader(sock1.getInputStream()));
	 	br2 = new BufferedReader(new InputStreamReader(sock2.getInputStream()));
	 	os = new PrintStream(sock.getOutputStream());
	 	os1 = new PrintStream(sock1.getOutputStream());
	 	os2 = new PrintStream(sock2.getOutputStream());
	 }
	    
	public static void main(String args[])throws IOException
	{
		
	}
	 public boolean verify_client(String uname, String pass) throws IOException
	    {
		 	
		 	os.println(uname);
		 	os.println(pass);
		 	String reply;
		 	int x = 0;
		 	while(x == 0)
		    {
		 		if((reply = br.readLine()) != null)
		 		{
		 			System.out.println("reply-" + reply);
		 			x = 1;
		 			if(reply.equals("authenticated"))
				 		return true;
				 	else
				 		return false;
		 		}
		    		
		    }
			return false;
		 	
	    }
	 
	 public void remove_file_from_user(String uname)
	 {
		 System.out.println("Removing file to user");
		 os1.println("Remove File");
		 os1.println(uname);
	 }
	 public void add_file_to_user(String fname, String uname) throws IOException
	 {
		 System.out.println("Adding file to user");
		 
		 os1.println("Add File");
		 os1.println(fname);
		 os1.println(uname);
		 
	 }
	 public void lock_file(String fname) throws IOException
	 {
		 System.out.println("Locking file");
		 
		 os2.println("Lock");
		 os2.println(fname);
	 }
	 
	 public void unlock_file(String fname) throws IOException
	 {
		 System.out.println("Unlocking file");
		 
		 os2.println("Unlock");
		 os2.println(fname);
	 }
	 public char verify_file_access(String uname, String fname, String faction) throws IOException
	    {
		 	os1.println("Verify");
		 	os1.println(uname);
		 	os1.println(fname);
		 	os1.println(faction);
		 	String reply;
		 	int x = 0;
		 	int y = 0;
		 	System.out.println("in file access function");
		 	while(x == 0)
		    {
		 		if((reply = br1.readLine()) != null)
		 		{
		 			System.out.println("reply-" + reply);
		 			x = 1;
		 			if(reply.equals("authenticated"))
		 			{
		 				while(y == 0)
		 			    {
		 			 		if((reply = br1.readLine()) != null)
		 			 		{
		 			 			client_gui.get_address(reply);
		 			 			y++;
		 			 		}
		 			    }
		 				return 'y';
		 			}	
				 	else
				 	{
				 		System.out.println("inside locked1");
				 		y = 0; 
				 		
			 			 if(reply.equals("locked"))
			 			 {
			 			 	System.out.println("inside locked");
			 			 	y++;
			 			 	return 'l';
			 			 }
				 		return 'n';
				 	}
				 		
		 		}
		    		
		    }
			return 'n';
			
		 	
	    }

}
