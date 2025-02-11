import java.util.*;
import java.io.*;

public class Outfit
{
    private Item shirt;
    private Item pant;
    private Item shoe;
    private Item hat;
    private Item jacket;

    public Outfit()
    {

    }
    public Outfit(Item si, Item p, Item so, Item h, Item j)
    {
        shirt = si;
        pant = p;
        shoe = so;
        hat = h;
        jacket = j;
    }
    
    public String toString()
    {
        return shirt.getName() + "\n" + pant.getName() + "\n" + shoe.getName() + "\n" + hat.getName() + "\n" + jacket.getName();
    }

    public void setShirt(Item si)
    {
        shirt = si;
    }
    public Item getShirt()
    {
        return shirt;
    }
    public void setPant(Item p)
    {
        pant = p;
    }
    public Item getPant()
    {
        return pant;
    }
    public void setShoe(Item so)
    {
        shoe = so;
    }
    public Item getShoe()
    {
        return shoe;
    }
    public void setHat(Item h)
    {
        hat = h;
    }
    public Item getHat()
    {
        return hat;
    }
    public void setJacket(Item j)
    {
        jacket = j;
    }
    public Item getJacket()
    {
        return jacket;
    }
}