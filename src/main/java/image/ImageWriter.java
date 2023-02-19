package image;

import math.Color;
import util.Pair;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Function;

public class ImageWriter {
    private final Function<Double, Double> gammaCorrection;

    public ImageWriter(Function<Double, Double> gammaCorrection) {
        this.gammaCorrection = gammaCorrection;
    }

    public void writeToBuffer(BufferedImage imageBuf, Image image) {
        for (Pixel px : image) {
            Color now = px.getColor();
            Color corrected = new Color(gammaCorrection.apply(now.getRed()), gammaCorrection.apply(now.getGreen()), gammaCorrection.apply(now.getBlue()));
            imageBuf.setRGB(px.getX(), px.getY(), corrected.toRGB());
        }
    }

    public BufferedImage write(Image image) {
        Pair<Integer, Integer> size = image.getSize();
        BufferedImage bufferedImage = new BufferedImage(size.getFirst(), size.getSecond(), BufferedImage.TYPE_INT_RGB);
        writeToBuffer(bufferedImage, image);
        return bufferedImage;
    }

    public void writeToFile(Path file, BufferedImage bufferedImage, OutputFormat format) throws IOException {
        ImageIO.write(bufferedImage, format.type, file.toFile());
    }

    public void writeToFile(Path file, BufferedImage bufferedImage) throws IOException {
        writeToFile(file, bufferedImage, OutputFormat.PNG);
    }

    public void writeToFile(Path file, Image image) throws IOException {
        writeToFile(file, write(image));
    }

}
