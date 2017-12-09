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
	 private static String fileName;
	 private BufferedReader br;
	 private BufferedReader br1;
	 private static PrintStream os;
	    
	 public client_security() throws UnknownHostException, IOException
	 {
	 	sock = new Socket("localhost", 7890);
	 	sock1 = new Socket("localhost", 7891);
	 	br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	 	br1 = new BufferedReader(new InputStreamReader(sock1.getInputStream()));
	 }
	    
	public static void main(String args[])throws IOException
	{
		
	}
	 public boolean verify_client(String uname, String pass) throws IOException
	    {
		 	os = new PrintStream(sock.getOutputStream());
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
	 
	 public boolean verify_file_access(String uname, String fname, String faction) throws IOException
	    {
		 	os = new PrintStream(sock1.getOutputStream());
		 	os.println(uname);
		 	os.println(fname);
		 	os.println(faction);
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
		 				return true;
		 			}	
				 	else
				 		return false;
		 		}
		    		
		    }
			return false;
		 	
	    }

}
