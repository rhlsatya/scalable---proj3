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
	 private static String fileName;
	 private BufferedReader br;
	 private static PrintStream os;
	    
	 public client_security() throws UnknownHostException, IOException
	 {
	 	sock = new Socket("localhost", 7890);
	 	br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
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
		 	
		 	//return true;
		 	/*while(reply == null)
		 	{
		 		
		 		reply = br.readLine();
		 	}
		 	System.out.println(reply);
		 	if(reply.equals("authenticated"))
		 		return true;
		 	else
		 		return false;*/
	    }

}
