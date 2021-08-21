package me.hideri.board.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.kodehawa.lib.imageboards.entities.BoardImage;
import net.kodehawa.lib.imageboards.entities.Rating;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TBIBImage implements BoardImage
{
    private String source;
    private String hash;
    private int height;
    private int width;
    private int id;
    private String image;
    private String owner;
    private Rating rating;
    private String tags;
    private String file_url;
    private int score;
    @JsonProperty("has_children")
    private boolean has_children;
    @JsonProperty("change")
    private long change;

    public TBIBImage()
    {

    }

    public String getSource()
    {
        return this.source;
    }

    public String getHash()
    {
        return this.hash;
    }

    public int getId()
    {
        return this.id;
    }

    public String getImage()
    {
        return this.image;
    }

    public String getOwner()
    {
        return this.owner;
    }

    public String getFile_url()
    {
        return this.file_url;
    }

    public boolean isHas_children()
    {
        return this.has_children;
    }

    public Rating getRating()
    {
        return this.rating;
    }

    public List<String> getTags()
    {
        return Collections.unmodifiableList(Arrays.asList(this.tags.split(" ")));
    }

    public int getScore()
    {
        return this.score;
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getWidth()
    {
        return this.width;
    }

    public String getURL()
    {
        return this.getFile_url();
    }

    public boolean hasChildren()
    {
        return this.isHas_children();
    }

    public boolean isPending()
    {
        return false;
    }

    public long getCreationMillis()
    {
        return this.change * 1000L;
    }
}
