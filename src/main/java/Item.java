import java.util.*;
import java.io.*;

public class Item
{
    private String name;
    private String brand;
    private String color;
    private boolean clean;
    private boolean school;
    private boolean work;
    private boolean gym;
    private boolean swim;
    private boolean nice;
    private boolean friends;
    private boolean warm;
    private boolean manly;
    private boolean golf;

    public Item()
    {

    }
    public Item(String n, String b, String c, boolean cl, boolean s, boolean w, boolean g, boolean sw, boolean ni, boolean f, boolean wa, boolean m, boolean go)
    {
        name = n;
        brand = b;
        color = c;
        clean = cl;
        school = s;
        work = w;
        gym = g;
        swim = sw;
        nice = ni;
        friends = f;
        warm = wa;
        manly = m;
        golf = go;
    }

    public String toString()
    {
        return name + ": " + brand + ", " + color;
    }

    public void setName(String n)
    {
        name = n;
    }
    public String getName()
    {
        return name;
    }
    public void setBrand(String b)
    {
        brand = b;
    }
    public String getBrand()
    {
        return brand;
    }
    public void setColor(String c)
    {
        color = c;
    }
    public String getColor()
    {
        return color;
    }
    public void setClean(boolean cl)
    {
        clean = cl;
    }
    public boolean getClean()
    {
        return clean;
    }
    public void setSchool(boolean s)
    {
        school = s;
    }
    public boolean getSchool()
    {
        return school;
    }
    public void setWork(boolean w)
    {
        work = w;
    }
    public boolean getWork()
    {
        return work;
    }
    public void setGym(boolean g)
    {
        gym = g;
    }
    public boolean getGym()
    {
        return gym;
    }
    public void setSwim(boolean sw)
    {
        swim = sw;
    }
    public boolean getSwim()
    {
        return swim;
    }
    public void setNice(boolean ni)
    {
        nice = ni;
    }
    public boolean getNice()
    {
        return nice;
    }
    public void setFriends(boolean f)
    {
        friends = f;
    }
    public boolean getFriends()
    {
        return friends;
    }
    public void setWarm(boolean wa)
    {
        warm = wa;
    }
    public boolean getWarm()
    {
        return warm;
    }
    public void setManly(boolean m)
    {
        manly = m;
    }
    public boolean getManly()
    {
        return manly;
    }
    public void setGolf(boolean go)
    {
        golf = go;
    }
    public boolean getGolf()
    {
        return golf;
    }
}