package me.hideri.board;

import net.kodehawa.lib.imageboards.boards.Board;

public enum CustomBoards implements Board
{
    TBIB("https", "tbib.org", "index.php", "page=dapi&s=post&q=index&json=1", "pid");

    private final String scheme;
    private final String pathSegment;
    private final String host;
    private final String query;
    private final String pageMarker;
    private final String outerObject;

    private CustomBoards(String scheme, String host, String pathSegment, String query, String pageMarker)
    {
        this.scheme = scheme;
        this.host = host;
        this.query = query;
        this.pathSegment = pathSegment;
        this.pageMarker = pageMarker;
        this.outerObject = null;
    }

    private CustomBoards(String scheme, String host, String pathSegment, String query, String pageMarker, String outerObject)
    {
        this.scheme = scheme;
        this.host = host;
        this.query = query;
        this.pathSegment = pathSegment;
        this.pageMarker = pageMarker;
        this.outerObject = outerObject;
    }

    public String getScheme()
    {
        return this.scheme;
    }

    public String getPath()
    {
        return this.pathSegment;
    }

    public String getQuery()
    {
        return this.query;
    }

    public String getHost()
    {
        return this.host;
    }

    public String getPageMarker()
    {
        return this.pageMarker;
    }

    public String getOuterObject()
    {
        return this.outerObject;
    }
}
