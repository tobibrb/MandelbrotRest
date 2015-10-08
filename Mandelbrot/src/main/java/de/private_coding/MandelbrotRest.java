package de.private_coding;
/**
 * Created by Bartz, Tobias @Tobi-PC on 08.10.2015 at 10:47.
 */

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import javax.swing.*;
import javax.ws.rs.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Path("/Mandelbrot")
public class MandelbrotRest {

    @GET
    @Path("/getMandelbrot")
    @Produces("image/png")
    public BufferedImage getMandelbrot(@QueryParam("w") int width, @QueryParam("h") int height, @QueryParam("it") int iterations) {
        int max = iterations;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        int black = 0;
        int[] colors = new int[max];
        for (int i = 0; i<max; i++) {
            colors[i] = Color.HSBtoRGB(i/256f, 1, i/(i+8f));
        }
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double c_re = (col - width/2)*4.0/width;
                double c_im = (row - height/2)*4.0/width;
                double x = 0, y = 0;
                int iteration = 0;
                while (x*x+y*y < 4 && iteration < max) {
                    double x_new = x*x-y*y+c_re;
                    y = 2*x*y+c_im;
                    x = x_new;
                    iteration++;
                }
                if (iteration < max) image.setRGB(col, row, colors[iteration]);
                else image.setRGB(col, row, black);
            }
        }
        return image;
    }


    public static void main(String[] args) {
        try {
            HttpServer server = HttpServerFactory.create("http://0.0.0.0:8080/");
            server.start();
            // For windows standalone implementation without cli
            //JOptionPane.showMessageDialog(null, "Stop server!");
            //server.stop(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
