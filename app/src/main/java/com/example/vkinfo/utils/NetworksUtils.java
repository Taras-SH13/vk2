package com.example.vkinfo.utils;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class NetworksUtils {

    private static final String VK_API_BASE_URL = "https://api.vk.com";
    private static final String VK_USERS_GET = "/method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";
    private static final String PARAM_ACCESS_TOKEN="access_token";


    public static URL generateURL(String userId) {
        Uri builUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_ID, userId)
                .appendQueryParameter(PARAM_ACCESS_TOKEN,"d8420fa36da4f5821dc99faa9f0b5f7054fe7fef04938bfe8ae9fdaf91742386a7a4894e45cbbb7d78cce")
                .appendQueryParameter(PARAM_VERSION, "5.8")
                .build();
        URL url = null;
        try {
            url = new URL(builUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


}
