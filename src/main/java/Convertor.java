import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Convertor {
    public static void main(String[] args) {

        final String sourceFilename = "image.ppm";
        final String destFilename = "image.png";

        // try to get hold of the file.
        try(BufferedReader reader = new BufferedReader(new FileReader(sourceFilename))) {
            // validate file header
            final String FileHeader = "P3";
            String line = reader.readLine();
            if (!FileHeader.equals(line)) {
                System.err.printf("Failed to read %s, bad header\n", sourceFilename);
                System.exit(1);
            }

            int width = 0;
            int height = 0;

            line = reader.readLine();
            if (line != null) {
                String[] values = line.split(" ");
                width = Integer.parseInt(values[0]);
                height = Integer.parseInt(values[1]);
            } else {
                System.err.printf("Failed to read %s, bad dimensions\n", sourceFilename);
                System.exit(1);
            }


            // just validate the bpp value, even though it isn't being used
            line = reader.readLine();
            if (line != null) {
                int bpp = Integer.parseInt(line);
                if (bpp < 0 || bpp > 255) {
                    System.err.printf("Failed to read %s, bad colour size\n", sourceFilename);
                    System.exit(1);
                }
            } else {
                System.err.printf("Failed to read %s, bad colour size\n", sourceFilename);
                System.exit(1);
            }

            // prepare the data buffer
            final int BPP = 3;
            int imageSize = width * height;
            System.out.println(width + "x" + height);
            var imageData = new int[imageSize];

            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");
                if (values.length != BPP) {
                    System.err.printf("Failed to read %s at line %d, bad colour size", sourceFilename, row);
                    System.exit(1);
                }

                int r = Integer.parseInt(values[0]);
                int g = Integer.parseInt(values[1]);
                int b = Integer.parseInt(values[2]);

                int pixelValue = (r << 16) | (g << 8) | b;
                imageData[row++] = pixelValue;
            }

            var bufferImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferImg.setRGB(0, 0, width, height, imageData, 0, width);

            var outputfile = new File(destFilename);
            ImageIO.write(bufferImg, "png", outputfile);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }

        System.out.printf("Processed %s into %s\n", sourceFilename, destFilename);
    }
}
