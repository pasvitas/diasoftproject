package ru.pasvitas.diasoftproject.Data;

import android.os.Handler;

public class UserInfo {

    public static String token;
    private static Integer expires;
    private static Integer userId;

    public static void getInfos(String response)
    {
        String[] props = response.split("&");
        token = props[0].split("=")[1];
        expires = Integer.parseInt(props[1].split("=")[1]);
        userId = Integer.parseInt(props[2].split("=")[1]);
    }
}