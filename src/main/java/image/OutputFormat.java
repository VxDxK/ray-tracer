package image;

public enum OutputFormat {
    PNG("png"),
    JPEG("jpg"),
    BMP("bmp");

    public final String type;

    OutputFormat(String type) {
        this.type = type;
    }
}
