package ru.pasvitas.diasoftproject.Utils;

public class UserInfo {

    public static String token;
    public static Integer expires;
    public static Integer userId;

    public static void getInfos(String response)
    {
        String[] props = response.split("&");
        token = props[0].split("=")[1];
        expires = Integer.parseInt(props[1].split("=")[1]);
        userId = Integer.parseInt(props[2].split("=")[1]);
    }
}