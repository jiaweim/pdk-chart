package pdk.chart.encoders;

import pdk.chart.util.Args;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class for returning {@link ImageEncoder}s for different
 * {@link ImageFormat}s.
 */
public class ImageEncoderFactory {

    /**
     * Storage for the encoders.
     */
    private static Map<String, String> encoders = null;

    static {
        init();
    }

    private ImageEncoderFactory() {
        // no requirement to instantiate
    }

    /**
     * Sets up default encoders (uses Sun PNG Encoder if JDK 1.4+ and the
     * SunPNGEncoderAdapter class is available).
     */
    private static void init() {
        encoders = new HashMap<>();
        encoders.put("jpeg", "pdk.chart.encoders.SunJPEGEncoderAdapter");
        encoders.put("jpg", "pdk.chart.encoders.SunJPEGEncoderAdapter");
        encoders.put("png", "pdk.chart.encoders.SunPNGEncoderAdapter");
    }

    /**
     * Used to set additional encoders or replace default ones.
     *
     * @param format                The image format name.
     * @param imageEncoderClassName The name of the ImageEncoder class.
     */
    public static void setImageEncoder(String format,
            String imageEncoderClassName) {
        Args.nullNotPermitted(format, "format");
        Args.nullNotPermitted(imageEncoderClassName, "imageEncoderClassName");
        encoders.put(format, imageEncoderClassName);
    }

    /**
     * Used to retrieve an ImageEncoder for a specific image format.
     *
     * @param format The image format required.
     * @return The ImageEncoder or {@code null} if none available.
     */
    public static ImageEncoder newInstance(String format) {
        ImageEncoder imageEncoder;
        String className = encoders.get(format);
        try {
            Class<?> imageEncoderClass = Class.forName(className);
            imageEncoder = (ImageEncoder) imageEncoderClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return imageEncoder;
    }

    /**
     * Used to retrieve an ImageEncoder for a specific image format.
     *
     * @param format  The image format required.
     * @param quality The quality to be set before returning.
     * @return The ImageEncoder or {@code null} if none available.
     */
    public static ImageEncoder newInstance(String format, float quality) {
        ImageEncoder imageEncoder = newInstance(format);
        imageEncoder.setQuality(quality);
        return imageEncoder;
    }

    /**
     * Used to retrieve an ImageEncoder for a specific image format.
     *
     * @param format        The image format required.
     * @param encodingAlpha Sets whether alpha transparency should be encoded.
     * @return The ImageEncoder or {@code null} if none available.
     */
    public static ImageEncoder newInstance(String format,
            boolean encodingAlpha) {
        ImageEncoder imageEncoder = newInstance(format);
        imageEncoder.setEncodingAlpha(encodingAlpha);
        return imageEncoder;
    }

    /**
     * Used to retrieve an ImageEncoder for a specific image format.
     *
     * @param format        The image format required.
     * @param quality       The quality to be set before returning.
     * @param encodingAlpha Sets whether alpha transparency should be encoded.
     * @return The ImageEncoder or {@code null} if none available.
     */
    public static ImageEncoder newInstance(String format, float quality,
            boolean encodingAlpha) {
        ImageEncoder imageEncoder = newInstance(format);
        imageEncoder.setQuality(quality);
        imageEncoder.setEncodingAlpha(encodingAlpha);
        return imageEncoder;
    }

}
