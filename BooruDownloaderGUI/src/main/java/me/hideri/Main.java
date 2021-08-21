package me.hideri;

import me.hideri.window.DownloaderWindow;
import net.kodehawa.lib.imageboards.ImageBoard;

public class Main
{
    public static final DownloaderWindow downloaderWindow = new DownloaderWindow();

    public static void main(String[] args)
    {
        ImageBoard.setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        downloaderWindow.initDownloaderWindow();
    }
}
