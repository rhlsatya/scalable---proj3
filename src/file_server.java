
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class file_server 
{

	private static final ServerThread[] threads = new ServerThread[100];
	public static final int port = 6789;
	private static Socket socket;
	private static ServerSocket chatr;
	public volatile static int join_id[][] = new int[100][100];
	public volatile static int chat_id[][] = new int[100][100];
	int counter = 100;
	
	public void runServer() throws IOException
	{
		chatr = new ServerSocket(port);
		System.out.println("File Server ready for connections..");
		
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
		public void checkmsg(String input) throws IOException
		{
			if(input.startsWith("READ: "))
			{
				readfile(input.substring(6));
			}
			else if(input.startsWith("WRITE: "))
			{
				writefile(input.substring(7));
			}
			else if(input.startsWith("RECEIVE: "))
			{
				receivefile(input.substring(9));
			}
		}
		
		public void readfile(String file_name)throws IOException
		{
			System.out.println("Inside read");
			File file = new File(file_name);
			byte[] mybytearray = new byte[(int) file.length()];

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            //handle file send over socket
            OutputStream os = socket.getOutputStream();

            //Sending file name and file size to the server
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(file.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();
		}
		
		public void writefile(String file_name)
		{
			
		}
		public void receivefile(String file_name)
		{
			
		}
		
		public void run()
		{
			try
			{
				
				is = new DataInputStream(socket.getInputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			    ps = new PrintStream(socket.getOutputStream());
				
			    while(true)
			    {
			    		String input = br.readLine();
			    		checkmsg(input);
			    }
			    
				
			}
			catch(IOException s)
			{
				//s.printStackTrace();
			}
			
		}
		
}
