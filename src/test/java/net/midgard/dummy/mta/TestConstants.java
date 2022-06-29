package net.midgard.dummy.mta;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestConstants {

    public static final String ME_AT_MIDGARD = "me@midgard.net";
    public static final String YOU_AT_MIDGARD = "you@midgard.net";
    public static final String SUBJECT = "Test Subject";
    public static final String BODY = "Test Body";
    public static final String RAW_BYTES = SUBJECT + "\n\n" + BODY;

    public static InputStream getEmailStream() {
        return new ByteArrayInputStream(RAW_BYTES.getBytes());
    }

    private TestConstants() {}

}
