import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author Sasindu Malshan
 * @project Chat-app
 * @date 1/19/2024
 */

public class LocalSocket {

    private DataInputStream dataInputStream;
    private String message = "";
    private Socket clientSocket;

    public Socket getSocket() {
        return clientSocket;
    }

    public void newServer(Socket socket) {
        new Thread(() -> {

            clientSocket = socket;
            System.out.println("Client add ... !");
            System.out.println("clientSocket               " + clientSocket);

            try {
                dataInputStream = new DataInputStream(clientSocket.getInputStream());

                while (!message.equals("Finish")) {

                    /**
                     * Check Type
                     * */
                    String clientType = dataInputStream.readUTF();

                    /**
                     * Read Text
                     * */
                    if (clientType.equals("STRING")) {
                        message = dataInputStream.readUTF();
                        Server.send(message);
                    }
                    /**
                     * Read Image
                     * */
                    else if (clientType.equals("IMAGE")) {
                       /* System.out.println("IMAGE");
                        byte[] bytes = dataInputStream.readAllBytes();
                        Server.sendImage(bytes);*/

                        System.out.println("Reading: " + System.currentTimeMillis());

                        byte[] sizeAr = new byte[4];
                        dataInputStream.read(sizeAr);
                        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                        byte[] imageAr = new byte[size];
                        dataInputStream.read(imageAr);

                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

                        Server.sendImage(image,clientSocket.getPort());
//                        System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
//                        ImageIO.write(image, "jpg", new File("C:\\Users\\Jakub\\Pictures\\test2.jpg"));
                    }
                    /**
                     * Read Emoji
                     * */
                    else if (clientType.equals("EMOJI")) {

                    }

                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
