package uk.co.bbc.countmeup;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Chris on 30-Jul-17.
 */
public class ResponseResults {
    private final ClientHttpResponse theResponse;
    private final String body;

    protected ResponseResults(final ClientHttpResponse response) throws IOException {
        this.theResponse = response;
        final InputStream bodyInputStream = response.getBody();
        if (null == bodyInputStream) {
            this.body = "{}";
        } else {
            //final StringWriter stringWriter = new StringWriter();
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            IOUtils.copy(bodyInputStream, os);
            this.body = new String(os.toByteArray(),"UTF-8");
        }
    }

    protected ClientHttpResponse getTheResponse() {
        return theResponse;
    }

    protected String getBody() {
        return body;
    }
}
