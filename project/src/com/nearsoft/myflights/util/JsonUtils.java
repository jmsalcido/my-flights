package com.nearsoft.myflights.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonUtils {

    public static String createJsonString(Class<?> clazz, String fileName)
            throws IOException {
        InputStream stream = clazz.getClassLoader().getResourceAsStream(
                fileName);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
