package me.hideri;

import me.hideri.window.DownloaderWindow;
import net.kodehawa.lib.imageboards.ImageBoard;

public class Main
{
    public static final DownloaderWindow downloaderWindow = new DownloaderWindow();

    public static void main(String[] args)
    {
        ImageBoard.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36");
        downloaderWindow.initDownloaderWindow();
    }
}
