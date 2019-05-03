import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketHTTPClient_FileWriter {
    public static void main(String[] args) {

        String hostName = "www.martinbroadhurst.com";
        int portNumber = 80;

        String http_StatusLine;

        try {

            FileWriter fileWriter = new FileWriter("sample.html");

            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));


            out.println("GET / HTTP/1.1\nHost: " + hostName + "\n\n");

            String inputLine;

            System.out.println("http Status :");

            http_StatusLine = in.readLine();
            System.out.println(http_StatusLine);

            System.out.println("http Header :");

//            while ((inputLine = in.readLine()) != "\n") {
//                System.out.println(inputLine);
//            }

            while ((inputLine = in.readLine()).length() != 0) {
                System.out.println(inputLine);
            }

            System.out.println("http body :");

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                fileWriter.write(inputLine + "\n");

            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}