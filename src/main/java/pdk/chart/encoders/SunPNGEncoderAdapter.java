package pdk.chart.encoders;

import pdk.chart.util.Args;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Adapter class for the Sun PNG Encoder.  The ImageEncoderFactory will only
 * return a reference to this class by default if the library has been compiled
 * under a JDK 1.4+ and is being run using a JDK 1.4+.
 */
public class SunPNGEncoderAdapter implements ImageEncoder {

    /**
     * Creates a new instance.
     */
    public SunPNGEncoderAdapter() {
        super();
    }

    /**
     * Get the quality of the image encoding (always 0.0).
     *
     * @return A float representing the quality.
     */
    @Override
    public float getQuality() {
        return 0.0f;
    }

    /**
     * Set the quality of the image encoding (not supported in this
     * ImageEncoder).
     *
     * @param quality A float representing the quality.
     */
    @Override
    public void setQuality(float quality) {
        //  No op
    }

    /**
     * Get whether the encoder should encode alpha transparency (always false).
     *
     * @return Whether the encoder is encoding alpha transparency.
     */
    @Override
    public boolean isEncodingAlpha() {
        return false;
    }

    /**
     * Set whether the encoder should encode alpha transparency (not
     * supported in this ImageEncoder).
     *
     * @param encodingAlpha Whether the encoder should encode alpha
     *                      transparency.
     */
    @Override
    public void setEncodingAlpha(boolean encodingAlpha) {
        //  No op
    }

    /**
     * Encodes an image in PNG format.
     *
     * @param bufferedImage The image to be encoded.
     * @return The byte[] that is the encoded image.
     * @throws IOException if there is an IO problem.
     */
    @Override
    public byte[] encode(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        encode(bufferedImage, outputStream);
        return outputStream.toByteArray();
    }

    /**
     * Encodes an image in PNG format and writes it to an OutputStream.
     *
     * @param bufferedImage The image to be encoded.
     * @param outputStream  The OutputStream to write the encoded image to.
     * @throws IOException if there is an IO problem.
     */
    @Override
    public void encode(BufferedImage bufferedImage, OutputStream outputStream)
            throws IOException {
        Args.nullNotPermitted(bufferedImage, "bufferedImage");
        Args.nullNotPermitted(outputStream, "outputStream");
        ImageIO.write(bufferedImage, ImageFormat.PNG, outputStream);
    }

}
