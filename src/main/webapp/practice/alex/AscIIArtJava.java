package com.psg.objectboard.consoleview;

/*import com.psg.objectboard.controller.MasterUserProfileController;*/
import com.psg.objectboard.model.datatransferobject.MasterUserDto;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class AscIIArtJava {

    public static void main(String[] args) throws IOException {

        int width = 100;
        int height = 30;


        //BufferedImage image = ImageIO.read(new File("/Users/mkyong/Desktop/logo.jpg"));
        //BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        /*MasterUserProfileController controller = new MasterUserProfileController();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Identifier?");
        long identifier=scanner.nextLong();
        System.out.println("correo?");
        String email=scanner.nextLine();
        scanner.nextLine();*/

        /*MasterUserDto masterUserDto = controller.getShowPhoto(identifier, email);
        System.out.println("teste "+ masterUserDto.getMuPhoto());*/

        BufferedImage image = ImageIO.read(new URL("/imagen.html"));

        //Graphics g = masterUserDto.getMuPhoto();
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 24));

        /*Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("JAVA", 10, 20);*/

        //save this image
        //ImageIO.write(image, "png", new File("/users/mkyong/ascii-art.png"));

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }

    }

}
