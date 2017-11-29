
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class file_server 
{

	private static final ServerThread[] threads = new ServerThread[100];
	public static final int port = 7899;
	private static Socket socket;
	private static ServerSocket chatr;
	public volatile static int join_id[][] = new int[100][100];
	public volatile static int chat_id[][] = new int[100][100];
	int counter = 100;
	
	
	
	
	
	
	
	public void runServer() throws IOException
	{
		chatr = new ServerSocket(port);
		System.out.println("Server ready for connections..");
		
		while(true)
		{
			
			socket = chatr.accept();
			for(int i = 0; i<100; i++)
			{
				if(threads[i] == null)
				{
					//join_id[i][2] = counter;
					counter++;
					(threads[i] = new ServerThread(socket, threads, join_id, chat_id, port, counter)).start();
					break;
				}
			}
		}
		
	}
	
	public static void main(String args[])throws IOException
	{
		file_server fs = new file_server();
		fs.runServer();
		
		//String msg;
		
		
	}
}

class ServerThread extends Thread {
	 Socket socket;
		private final ServerThread[] threads;
		private DataInputStream is = null;
		private PrintStream ps = null;
		private int port;
		int kill = 0;
		private BufferedReader br;
		public String client_name;
		public volatile static int join_id[][] = new int[100][100];
		public volatile static int chat_id[][] = new int[100][100];
		public int room_ref;
		int counter; //to keep track of join id
		ServerThread(Socket socket, ServerThread[] threads, int join_id[][], int chat_id[][], int port, int counter)
		{
			this.socket = socket;
			this.threads = threads;
			this.port = port;
			ServerThread.join_id = join_id;
			ServerThread.chat_id = chat_id;
			this. counter = counter;
		}
		
		public void run()
		{
			try
			{
				
				is = new DataInputStream(socket.getInputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			    ps = new PrintStream(socket.getOutputStream());
				
			    
				
			}
			catch(IOException s)
			{
				//s.printStackTrace();
			}
			
		}
		
}
