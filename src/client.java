

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class client {
	
    private static Socket sock;
    private static String fileName;
    private static BufferedReader br;
    private static PrintStream os;

    public static void main(String[] args) throws IOException {
        
    	
    		
        sock = new Socket("localhost", 6789);
        br = new BufferedReader(new InputStreamReader(System.in));
        sendFile();
        /*System.out.println("Enter File Name");
        String fileName = br.readLine();
        os = new PrintStream(sock.getOutputStream());
        os.println("READ: " + fileName);
        receiveFile(fileName);
             */
        


        sock.close();
    }

    

    public static void sendFile() throws IOException
    {
        
            System.err.print("Enter file name: ");
            fileName = br.readLine();
            os = new PrintStream(sock.getOutputStream());
            os.println("RECEIVE: " + fileName);
            File file = new File(fileName);
			byte[] mybytearray = new byte[(int) file.length()];

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            //handle file send over socket
            OutputStream os = sock.getOutputStream();

            //Sending file name and file size to the server
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(file.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();
    }

    public static void receiveFile(String fileName)throws IOException
    {
        
            int bytesRead;
            InputStream in = sock.getInputStream();

            DataInputStream clientData = new DataInputStream(in);

            fileName = clientData.readUTF();
            OutputStream output = new FileOutputStream(("received_from_server_" + fileName));
            long size = clientData.readLong();
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }

            output.close();
            in.close();

            System.out.println("File "+fileName+" received from Server.");
        
    }
}
