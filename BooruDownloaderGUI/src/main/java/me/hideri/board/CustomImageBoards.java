package me.hideri.board;

import me.hideri.board.images.TBIBImage;
import net.kodehawa.lib.imageboards.ImageBoard;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class CustomImageBoards
{
    private static final OkHttpClient client;
    public static final ImageBoard<TBIBImage> TBIB;

    static
    {
        client = (new OkHttpClient.Builder()).connectTimeout(3L, TimeUnit.SECONDS).readTimeout(3L, TimeUnit.SECONDS).build();
        TBIB = new ImageBoard<>(client, CustomBoards.TBIB, TBIBImage.class);
    }
}
