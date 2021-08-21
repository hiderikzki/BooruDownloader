package me.hideri;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Runtime rt = Runtime.getRuntime();
        rt.exec("cmd.exe /c start cmd.exe /c java -jar BooruDownloaderGUI.jar");
    }
}
