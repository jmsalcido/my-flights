package com.nearsoft.myflights.util;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonUtils {

    /**
     * helper method to create a string from any text file
     * @param clazz
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String createString(Class<?> clazz, String fileName)
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

    /**
     * helper method to convert any Object to a JSON String with Gson
     * @param object
     * @return
     */
    public static String convertObjectToJSON(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

}
