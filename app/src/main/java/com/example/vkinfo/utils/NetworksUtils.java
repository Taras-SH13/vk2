package com.example.vkinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.Scanner;

public class NetworksUtils {

    private static final String VK_API_BASE_URL = "https://api.vk.com";
    private static final String VK_USERS_GET = "/method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";
    private static final String PARAM_ACCESS_TOKEN = "access_token";


    public static URL generateURL(String userIds) {
        Uri buildUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_ID, userIds)
                .appendQueryParameter(PARAM_ACCESS_TOKEN, "d8420fa36da4f5821dc99faa9f0b5f7054fe7fef04938bfe8ae9fdaf91742386a7a4894e45cbbb7d78cce")
                .appendQueryParameter(PARAM_VERSION, "5.21")
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A"); // трюк для удобного считывания данных целой строкой
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (UnknownHostException e) {
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }


}
