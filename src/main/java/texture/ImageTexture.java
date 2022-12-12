package texture;

import math.Color;
import math.Point;
import util.Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class ImageTexture implements Texture{
    private BufferedImage bufferedImage;
    private int width;
    private int height;

    public ImageTexture(Path image){
        try{
            bufferedImage = ImageIO.read(image.toFile());
            width = bufferedImage.getWidth();
            height = bufferedImage.getHeight();
        } catch (IOException e) {
            bufferedImage = null;
            //TODO
            throw new RuntimeException("Lolz no image found for this texture");
        }

    }
    @Override
    public Color value(double u, double v, Point p) {
        if(bufferedImage == null){
            return new Color(0, 1, 1);
        }

        double tu = Util.clamp(u, 0d, 1d);
        double tv = 1d - Util.clamp(v, 0d, 1d);

        int i = (int)(tu * width);
        int j = (int)(tv * height);

        if (i >= width)  i = width-1;
        if (j >= height) j = height-1;

        return Color.getByRGB(bufferedImage.getRGB(i, j));
    }
}
