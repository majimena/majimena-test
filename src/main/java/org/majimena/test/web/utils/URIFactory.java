package org.majimena.test.web.utils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by todoken on 2015/06/06.
 */
public class URIFactory {

    public static URI create(String url, Object... params) {
        try {
            String value = String.format(url, params);
            return new URI(value);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
