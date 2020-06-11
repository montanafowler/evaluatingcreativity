package creative.framework.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

    /**
     * Return a BufferedReader
     *
     * @param fileDescription
     * @return
     */
    public BufferedReader getReader(String fileDescription) {
        InputStream is = this.getClass().getResourceAsStream(fileDescription);
        return new BufferedReader(new InputStreamReader(is));
    }

}
