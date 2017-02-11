package com.cantekinandroidlib.webApi;

import org.springframework.core.io.ByteArrayResource;

/**
 * Created by Cantekin on 10.2.2017.
 */

public class FileMessageResource extends ByteArrayResource {

    /**
     * The filename to be associated with the {@link MimeMessage} in the form data.
     */
    private final String filename;

    /**
     * Constructs a new {@link FileMessageResource}.
     * @param byteArray A byte array containing data from a {@link MimeMessage}.
     * @param filename The filename to be associated with the {@link MimeMessage} in
     * 	the form data.
     */
    public FileMessageResource(final byte[] byteArray, final String filename) {
        super(byteArray);
        this.filename = filename;
    }

    @Override
    public String getFilename() {
        return filename;
    }
}
