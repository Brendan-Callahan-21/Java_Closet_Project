//Runner class for outfitPicker project
//May 2023

import java.util.Scanner;
import java.io.*;
import java.util.*;

public class ClosetRunner
{
    public static void main(String [] args) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        Scanner key = new Scanner(System.in);

        ClosetRunner cr = new ClosetRunner();        //was encountering a static reference err msg so created a new instance of the ClosetRunner class to use when calling methods in the same class

        String shirtFile, pantFile, shoeFile, hatFile, jacketFile;
        shirtFile = "shirtList.csv";
        pantFile = "pantList.csv";
        shoeFile = "shoeList.csv";
        hatFile = "hatList.csv";
        jacketFile = "jacketList.csv";

        String[] fileArray = {shirtFile, pantFile, shoeFile, hatFile, jacketFile};

        int xAxisLen = 5;       //number of clothing types
        int yAxisLen = 0;       //number of clothing items per clothing type

        //getting the highest number of any clothing type (yAxisLen)
        int numShirts = cr.getRows(shirtFile);
        if(numShirts>yAxisLen)
            yAxisLen = numShirts;
        int numPants = cr.getRows(pantFile);
        if(numPants>yAxisLen)
            yAxisLen = numPants;
        int numShoes = cr.getRows(shoeFile);
        if(numShoes>yAxisLen)
            yAxisLen = numShoes;
        int numHats = cr.getRows(hatFile);
        if(numHats>yAxisLen)
            yAxisLen = numHats;
        int numJackets = cr.getRows(jacketFile);
        if(numJackets>yAxisLen)
            yAxisLen = numJackets;        
        
        ArrayList<ArrayList<Item>> closet = new ArrayList<>(xAxisLen);      //creates an arraList holding 5 arraylists each holding yAxisLen number Item objs

        closet = cr.fillArray(fileArray, xAxisLen, yAxisLen);   //filling the closet with clothes

        for(int i = 0; i < xAxisLen; i++)   //prints the closet
        {
            for(int j = 0; j < yAxisLen; j++)
            {
                //had some index out of bounds problems, so the try statement helped my fix it
                try {
                    System.out.println(closet.get(i).get(j).toString());
                } catch (java.lang.IndexOutOfBoundsException e) {
                    System.out.println("empty");
                }
            }
            System.out.println();
        }

        boolean test = true;
        while(test)
        {
            for(int day = 1; day<7; day++) //week loop that loops once for each day
            {
                System.out.println("\nDAY " + day + "\n");
                ArrayList<Outfit> dailyWardrobe = new ArrayList<Outfit>();          //dailyWardrobe is an arrayList of Outfit objects --> (outfit is an object holding 5 Item objs representing arrayLists)

                System.out.println("what is the temperature?");
                int temp = key.nextInt();                      //collecting the temperature for the day
                boolean test2 = true;
                int actNum = 1;
                String ans;
                while(test2)    //day loop that loops once for ea activity
                {
                    System.out.println("would you like to enter an activity? (y or n)");
                    ans = scan.nextLine();
                    if(ans.equals("y"))     //if you want to enter an activity
                    {
                        System.out.println("Acivity " + actNum + ": Enter school, work, gym, swim, church, friends, manly (outdoor stuff), or golf");
                        ans = scan.nextLine();
                        if(ans.equals("school"))        //if the activity is school
                        {
                            //creating a new item for each clothing type to be implemented in this activities outfit
                            Item shirt = new Item();
                            Item pant = new Item();
                            Item shoe = new Item();
                            Item hat = new Item();
                            Item jacket = new Item();

                            boolean test3 = true;
                            while(test3)    //picking a shirt
                            {
                                int shirtNum = (int) (Math.random()*(47));      //number bw 0 and 46 bc closet has 47 pants
                                shirt = closet.get(0).get(shirtNum);      //making shirt the item in closet at that index

                                if(shirt.getSchool() && shirt.getClean())   //if selectied shirt is acceptable for school, and is clean it moves to pants - if not it selects a new random shirt;
                                {
                                    test3 = false;

                                    boolean test4 = true;
                                    while(test4)        //picking a pair of pants
                                    {
                                        int pantNum = (int) (Math.random()*(36));//random number bw 0 and 35 bc closet has 36 pants
                                        pant = closet.get(1).get(pantNum);

                                        if(pant.getSchool() && pant.getClean()) //if pants are school appropriate and clean it makes the pant item this pants - if not it picks new pants
                                        {
                                            test4 = false;

                                            boolean test5 = true;
                                            while(test5)        //picking shoes 
                                            {
                                                int shoeNum = (int) (Math.random()*(11)); //random number bw 0 and 10 bc closet has 11 shoes
                                                shoe = closet.get(2).get(shoeNum);
                                                if(shoe.getSchool())        //if shoe is school appropriate, but if not it picks new shoe
                                                {
                                                    test5 = false;

                                                    boolean test6 = true;
                                                    while(test6)        //picking hat
                                                    {
                                                        int hatNum = (int) (Math.random()*(9)); //random number bw 0 and 8 bc closet has 9 hats
                                                        hat = closet.get(3).get(hatNum);
                                                        if(hat.getColor().equals("none")) //hats can't be warn to school, so it picks a hat with color = "none" witch is just not a hat
                                                        {
                                                            test6 = false;
                                                            
                                                            boolean test7 = true;
                                                            while(test7)        //picking jacket
                                                            {
                                                                int jacketNum = (int)(Math.random()*11);            //num bw 0 and 10 bc closet has 11 jackets
                                                                jacket = closet.get(4).get(jacketNum);
                                                                if(temp < 60 && !(jacket.getColor().equals("none"))){ //if temp is less than 60, it makes sure jacket color != "none"
                                                                    test7 = false;
                                                                }
                                                                else if(temp >= 60 && jacket.getColor().equals("none")){ //if temp is greater than 60 it makes sure jacket color = "none"
                                                                    test7 = false;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }  
                            }
                            Outfit school = new Outfit(shirt, pant, shoe, hat, jacket);     //making a new outfit which is an object of Item objects, representing an outfit
                            dailyWardrobe.add(school);  //adding selected outfit to dailyWardrobe arrayList
                            actNum++;   //increase the activity number
                        } 
                        else if(ans.equals("work"))
                        {

                            Item shirt = new Item();
                            Item pant = new Item();
                            Item shoe = new Item();
                            Item hat = new Item();
                            Item jacket = new Item();

                            boolean test3 = true;
                            while(test3)
                            {
                                //give clean work pants, school shirt, and school shoes

                                int shirtNum = (int) (Math.random()*(47));      //number b/w 0 & 4
                                shirt = closet.get(0).get(shirtNum);

                                if(shirt.getWork() && shirt.getClean())
                                {
                                    test3 = false;

                                    boolean test4 = true;
                                    while(test4)
                                    {
                                        int pantNum = (int) (Math.random()*(36));//random number bw 0 and 4
                                        pant = closet.get(1).get(pantNum);

                                        if(pant.getWork() && pant.getClean())//if pants are school pants and clean it makes the pant item this pants
                                        {
                                            test4 = false;

                                            boolean test5 = true;
                                            while(test5)
                                            {
                                                int shoeNum = (int) (Math.random()*(11));//random number bw 0 and 4
                                                shoe = closet.get(2).get(shoeNum);
                                                if(shoe.getWork())
                                                {
                                                    test5 = false;

                                                    boolean test6 = true;
                                                    while(test6)
                                                    {
                                                        int hatNum = (int) (Math.random()*(9));
                                                        hat = closet.get(3).get(hatNum);
                                                        if(!(hat.getColor().equals("none")))
                                                        {
                                                            test6 = false;
                                                            
                                                            boolean test7 = true;
                                                            while(test7)
                                                            {
                                                                int jacketNum = (int)(Math.random()*11);
                                                                jacket = closet.get(4).get(jacketNum);
                                                                if(temp < 60 && !(jacket.getColor().equals("none")))
                                                                {
                                                                    test7 = false;
                                                                }
                                                                else if(temp >= 60 && jacket.getColor().equals("none"))
                                                                {
                                                                    test7 = false;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }

                                        }
                                    }
                                }  
                            }
                            Outfit work = new Outfit(shirt, pant, shoe, hat, jacket);
                            dailyWardrobe.add(work);         
                            actNum++;                   
                        }
                        else if(ans.equals("gym"))
                        {
                            Item shirt = new Item();
                            Item pant = new Item();
                            Item shoe = new Item();
                            Item hat = new Item();
                            Item jacket = new Item();

                            boolean test3 = true;
                            while(test3)
                            {
                                //give clean gym pants, school shirt, and school shoes

                                int shirtNum = (int) (Math.random()*(47));      //number b/w 0 & 4
                                shirt = closet.get(0).get(shirtNum);

                                if(shirt.getGym() && shirt.getClean())
                                {
                                    test3 = false;

                                    String shirtColor = shirt.getColor();
                                    boolean shirtNut;
                                    if(shirtColor.equals("black") || shirtColor.equals("white") || shirtColor.equals("gray"))
                                    {
                                        shirtNut = true;
                                    }
                                    else
                                    {
                                        shirtNut = false;
                                    }

                                    boolean test4 = true;
                                    while(test4)
                                    {
                                        int pantNum = (int) (Math.random()*(36));//random number bw 0 and 4
                                        pant = closet.get(1).get(pantNum);
                                        String pantColor = pant.getColor();
                                        boolean pantNut;
                                        if(pantColor.equals("black") || pantColor.equals("white") || pantColor.equals("gray"))
                                        {
                                            pantNut = true;
                                        }
                                        else
                                        {
                                            pantNut = false;
                                        }
                                        boolean match;
                                        if(!(shirtColor.equals(pantColor)) && !(!shirtNut && !pantNut)) 
                                        {
                                            match = true;
                                        }
                                        else
                                        {
                                            match = false;
                                        }

                                        if(pant.getGym() && pant.getClean() && match)
                                        {
                                            test4 = false;

                                            boolean test5 = true;
                                            while(test5)
                                            {
                                                int shoeNum = (int) (Math.random()*(11));//random number bw 0 and 4
                                                shoe = closet.get(2).get(shoeNum);
                                                if(shoe.getGym())
                                                {
                                                    test5 = false;

                                                    boolean test6 = true;
                                                    while(test6)
                                                    {
                                                        int hatNum = (int) (Math.random()*(9));
                                                        hat = closet.get(3).get(hatNum);
                                                        String hatColor = hat.getColor();
                                                        boolean hatNut;
                                                        if(hatColor.equals("black") || hatColor.equals("white") || hatColor.equals("gray"))
                                                        {
                                                            hatNut = true;
                                                        }
                                                        else
                                                        {
                                                            hatNut = false;
                                                        }
                                                        if(hat.getColor().equals(shirtColor) || hat.getColor().equals(pantColor) || hatNut)
                                                        {
                                                            test6 = false;
                                                            
                                                            boolean test7 = true;
                                                            while(test7)
                                                            {
                                                                int jacketNum = (int)(Math.random()*11);
                                                                jacket = closet.get(4).get(jacketNum);

                                                                String jacketColor = jacket.getColor();
                                                                boolean jacketNut;
                                                                if(jacketColor.equals("black") || jacketColor.equals("white") || jacketColor.equals("gray"))
                                                                {
                                                                    jacketNut = true;
                                                                }
                                                                else
                                                                {
                                                                    jacketNut = false;
                                                                }
                                                                if(!(jacketColor.equals(pantColor)) || !(!jacketNut && !pantNut))
                                                                {
                                                                    match = true;
                                                                }
                                                                else
                                                                {
                                                                    match = false;
                                                                }
                                                                
                                                                if(temp < 60 && !(jacket.getColor().equals("none")) && match)
                                                                {
                                                                    test7 = false;
                                                                }
                                                                else if(temp >= 60 && jacket.getColor().equals("none"))
                                                                {
                                                                    test7 = false;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }

                                        }
                                    }
                                }  
                            }
                            Outfit gym = new Outfit(shirt, pant, shoe, hat, jacket);
                            dailyWardrobe.add(gym);
                            actNum++;
                        }
                        else if(ans.equals("friends"))
                        {
                            Item shirt = new Item();
                            Item pant = new Item();
                            Item shoe = new Item();
                            Item hat = new Item();
                            Item jacket = new Item();

                            boolean test3 = true;
                            while(test3)
                            {
                                int shirtNum = (int) (Math.random()*(47));      //number b/w 0 & 4
                                shirt = closet.get(0).get(shirtNum);

                                if(shirt.getFriends() && shirt.getClean())
                                {
                                    test3 = false;

                                    String shirtColor = shirt.getColor();
                                    boolean shirtNut;
                                    if(shirtColor.equals("black") || shirtColor.equals("white") || shirtColor.equals("gray"))
                                    {
                                        shirtNut = true;
                                    }
                                    else
                                    {
                                        shirtNut = false;
                                    }

                                    boolean test4 = true;
                                    while(test4)
                                    {
                                        int pantNum = (int) (Math.random()*(36));//random number bw 0 and 4
                                        pant = closet.get(1).get(pantNum);
                                        String pantColor = pant.getColor();
                                        boolean pantNut;
                                        if(pantColor.equals("black") || pantColor.equals("white") || pantColor.equals("gray"))
                                        {
                                            pantNut = true;
                                        }
                                        else
                                        {
                                            pantNut = false;
                                        }
                                        boolean match;
                                        if(!(shirtColor.equals(pantColor)) && !(!shirtNut && !pantNut)) 
                                        {
                                            match = true;
                                        }
                                        else
                                        {
                                            match = false;
                                        }

                                        if(pant.getFriends() && pant.getClean() && match)
                                        {
                                            test4 = false;

                                            boolean test5 = true;
                                            while(test5)
                                            {
                                                int shoeNum = (int) (Math.random()*(11));//random number bw 0 and 4
                                                shoe = closet.get(2).get(shoeNum);
                                                String shoeColor = shoe.getColor();
                                                boolean shoeNut;
                                                if(shoeColor.equals("black") || shoeColor.equals("white") || shoeColor.equals("gray") || shoeColor.equals("tan"))
                                                {
                                                    shoeNut = true;
                                                }
                                                else
                                                {
                                                    shoeNut = false;
                                                }
                                                if((!shoeNut && shoeColor.equals(pantColor)) || (shoeNut))
                                                {
                                                    match = true;
                                                }
                                                else
                                                {
                                                    match = false;
                                                }
                                                if(shoe.getFriends() && match)
                                                {
                                                    test5 = false;

                                                    boolean test6 = true;
                                                    while(test6)
                                                    {
                                                        int hatNum = (int) (Math.random()*(9));
                                                        hat = closet.get(3).get(hatNum);
                                                        String hatColor = hat.getColor();
                                                        boolean hatNut;
                                                        if(hatColor.equals("black") || hatColor.equals("white") || hatColor.equals("gray"))
                                                        {
                                                            hatNut = true;
                                                        }
                                                        else
                                                        {
                                                            hatNut = false;
                                                        }
                                                        if(hat.getColor().equals(shirtColor) || hat.getColor().equals(pantColor) || hatNut || (pantNut && shirtNut))
                                                        {
                                                            test6 = false;
                                                            
                                                            boolean test7 = true;
                                                            while(test7)
                                                            {
                                                                int jacketNum = (int)(Math.random()*11);
                                                                jacket = closet.get(4).get(jacketNum);

                                                                String jacketColor = jacket.getColor();
                                                                boolean jacketNut;
                                                                if(jacketColor.equals("black") || jacketColor.equals("white") || jacketColor.equals("gray"))
                                                                {
                                                                    jacketNut = true;
                                                                }
                                                                else
                                                                {
                                                                    jacketNut = false;
                                                                }
                                                                if(!(jacketColor.equals(pantColor)) || !(!jacketNut && !pantNut))
                                                                {
                                                                    match = true;
                                                                }
                                                                else
                                                                {
                                                                    match = false;
                                                                }
                                                                
                                                                if(temp < 60 && !(jacket.getColor().equals("none")) && match)
                                                                {
                                                                    test7 = false;
                                                                }
                                                                else if(temp >= 60 && jacket.getColor().equals("none"))
                                                                {
                                                                    test7 = false;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }

                                        }
                                    }
                                }  
                            }
                            Outfit friends = new Outfit(shirt, pant, shoe, hat, jacket);
                            dailyWardrobe.add(friends);
                            actNum++;
                        }
                        else if(ans.equals("church"))
                        {
                            Item shirt = new Item();
                            Item pant = new Item();
                            Item shoe = new Item();
                            Item hat = new Item();
                            Item jacket = new Item();

                            boolean test3 = true;
                            while(test3)
                            {
                                int shirtNum = (int) (Math.random()*(47));      //number b/w 0 & 4
                                shirt = closet.get(0).get(shirtNum);

                                if(shirt.getNice() && shirt.getClean())
                                {
                                    test3 = false;

                                    String shirtColor = shirt.getColor();
                                    boolean shirtNut;
                                    if(shirtColor.equals("black") || shirtColor.equals("white") || shirtColor.equals("gray"))
                                    {
                                        shirtNut = true;
                                    }
                                    else
                                    {
                                        shirtNut = false;
                                    }

                                    boolean test4 = true;
                                    while(test4)
                                    {
                                        int pantNum = (int) (Math.random()*(36));//random number bw 0 and 4
                                        pant = closet.get(1).get(pantNum);
                                        String pantColor = pant.getColor();
                                        boolean pantNut;
                                        if(pantColor.equals("black") || pantColor.equals("white") || pantColor.equals("gray"))
                                        {
                                            pantNut = true;
                                        }
                                        else
                                        {
                                            pantNut = false;
                                        }
                                        boolean match;
                                        if(!(shirtColor.equals(pantColor)) && !(!shirtNut && !pantNut)) 
                                        {
                                            match = true;
                                        }
                                        else
                                        {
                                            match = false;
                                        }

                                        if(pant.getNice() && pant.getClean() && match)
                                        {
                                            test4 = false;

                                            boolean test5 = true;
                                            while(test5)
                                            {
                                                int shoeNum = (int) (Math.random()*(11));//random number bw 0 and 4
                                                shoe = closet.get(2).get(shoeNum);
                                                String shoeColor = shoe.getColor();
                                                boolean shoeNut;
                                                if(shoeColor.equals("black") || shoeColor.equals("white") || shoeColor.equals("gray") || shoeColor.equals("tan"))
                                                {
                                                    shoeNut = true;
                                                }
                                                else
                                                {
                                                    shoeNut = false;
                                                }
                                                if((!shoeNut && shoeColor.equals(pantColor)) || (shoeNut) || (!shoeNut && shoeColor.equals(pantColor)))
                                                {
                                                    match = true;
                                                }
                                                else
                                                {
                                                    match = false;
                                                }
                                                if(shoe.getNice() && match)
                                                {
                                                    test5 = false;

                                                    boolean test6 = true;
                                                    while(test6)
                                                    {
                                                        int hatNum = (int) (Math.random()*(9));
                                                        hat = closet.get(3).get(hatNum);
                                                        if(hat.getColor().equals("none"))
                                                        {
                                                            test6 = false;
                                                            
                                                            boolean test7 = true;
                                                            while(test7)
                                                            {
                                                                int jacketNum = (int)(Math.random()*11);
                                                                jacket = closet.get(4).get(jacketNum);

                                                                String jacketColor = jacket.getColor();
                                                                boolean jacketNut;
                                                                if(jacketColor.equals("black") || jacketColor.equals("white") || jacketColor.equals("gray"))
                                                                {
                                                                    jacketNut = true;
                                                                }
                                                                else
                                                                {
                                                                    jacketNut = false;
                                                                }
                                                                if(!(jacketColor.equals(pantColor)) || !(!jacketNut && !pantNut))
                                                                {
                                                                    match = true;
                                                                }
                                                                else
                                                                {
                                                                    match = false;
                                                                }
                                                                
                                                                if(temp < 60 && !(jacket.getColor().equals("none")) && match)
                                                                {
                                                                    test7 = false;
                                                                }
                                                                else if(temp >= 60 && jacket.getColor().equals("none"))
                                                                {
                                                                    test7 = false;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }

                                        }
                                    }
                                }  
                            }
                            Outfit church = new Outfit(shirt, pant, shoe, hat, jacket);
                            dailyWardrobe.add(church);
                            actNum++;
                        }
                        else if(ans.equals("golf"))
                        {
                            Item shirt = new Item();
                            Item pant = new Item();
                            Item shoe = new Item();
                            Item hat = new Item();
                            Item jacket = new Item();

                            boolean test3 = true;
                            while(test3)
                            {
                                //give clean gym pants, school shirt, and school shoes

                                int shirtNum = (int) (Math.random()*(47));      //number b/w 0 & 4
                                shirt = closet.get(0).get(shirtNum);

                                if(shirt.getGolf() && shirt.getClean())
                                {
                                    test3 = false;

                                    String shirtColor = shirt.getColor();
                                    boolean shirtNut;
                                    if(shirtColor.equals("black") || shirtColor.equals("white") || shirtColor.equals("gray"))
                                    {
                                        shirtNut = true;
                                    }
                                    else
                                    {
                                        shirtNut = false;
                                    }

                                    boolean test4 = true;
                                    while(test4)
                                    {
                                        int pantNum = (int) (Math.random()*(36));//random number bw 0 and 4
                                        pant = closet.get(1).get(pantNum);
                                        String pantColor = pant.getColor();
                                        boolean pantNut;
                                        if(pantColor.equals("black") || pantColor.equals("white") || pantColor.equals("gray"))
                                        {
                                            pantNut = true;
                                        }
                                        else
                                        {
                                            pantNut = false;
                                        }
                                        boolean match;
                                        if(!(shirtColor.equals(pantColor)) && !(!shirtNut && !pantNut)) 
                                        {
                                            match = true;
                                        }
                                        else
                                        {
                                            match = false;
                                        }

                                        if(pant.getGolf() && pant.getClean() && match)
                                        {
                                            test4 = false;

                                            boolean test5 = true;
                                            while(test5)
                                            {
                                                int shoeNum = (int) (Math.random()*(11));//random number bw 0 and 4
                                                shoe = closet.get(2).get(shoeNum);
                                                String shoeColor = shoe.getColor();
                                                boolean shoeNut;
                                                if(shoeColor.equals("black") || shoeColor.equals("white") || shoeColor.equals("gray") || shoeColor.equals("tan"))
                                                {
                                                    shoeNut = true;
                                                }
                                                else
                                                {
                                                    shoeNut = false;
                                                }
                                                if((!shoeNut && shoeColor.equals(pantColor)) || (shoeNut) || (!shoeNut && shoeColor.equals(shirtColor)))
                                                {
                                                    match = true;
                                                }
                                                else
                                                {
                                                    match = false;
                                                }
                                                if(shoe.getGolf() && match)
                                                {
                                                    test5 = false;

                                                    boolean test6 = true;
                                                    while(test6)
                                                    {
                                                        int hatNum = (int) (Math.random()*(9));
                                                        hat = closet.get(3).get(hatNum);
                                                        String hatColor = hat.getColor();
                                                        boolean hatNut;
                                                        if(hatColor.equals("black") || hatColor.equals("white") || hatColor.equals("gray"))
                                                        {
                                                            hatNut = true;
                                                        }
                                                        else
                                                        {
                                                            hatNut = false;
                                                        }
                                                        if(hat.getColor().equals(shirtColor) || hat.getColor().equals(pantColor) || hatNut || hat.getColor().equals("none"))
                                                        {
                                                            test6 = false;
                                                            
                                                            boolean test7 = true;
                                                            while(test7)
                                                            {
                                                                int jacketNum = (int)(Math.random()*11);
                                                                jacket = closet.get(4).get(jacketNum);

                                                                String jacketColor = jacket.getColor();
                                                                boolean jacketNut;
                                                                if(jacketColor.equals("black") || jacketColor.equals("white") || jacketColor.equals("gray"))
                                                                {
                                                                    jacketNut = true;
                                                                }
                                                                else
                                                                {
                                                                    jacketNut = false;
                                                                }
                                                                if(!(jacketColor.equals(pantColor)) || !(!jacketNut && !pantNut))
                                                                {
                                                                    match = true;
                                                                }
                                                                else
                                                                {
                                                                    match = false;
                                                                }
                                                                
                                                                if(temp < 60 && !(jacket.getColor().equals("none")) && match)
                                                                {
                                                                    test7 = false;
                                                                }
                                                                else if(temp >= 60 && jacket.getColor().equals("none"))
                                                                {
                                                                    test7 = false;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }

                                        }
                                    }
                                }  
                            }
                            Outfit golf = new Outfit(shirt, pant, shoe, hat, jacket);
                            dailyWardrobe.add(golf);
                            actNum++;
                        }
                        else if(ans.equals("swim"))
                        {
                            Item shirt = new Item();
                            Item pant = new Item();
                            Item shoe = new Item();
                            Item hat = new Item();
                            Item jacket = new Item();

                            boolean test3 = true;
                            while(test3)
                            {
                                int pantNum = (int)(Math.random()*36);
                                pant = closet.get(1).get(pantNum);
                                String pantColor = pant.getColor();
                                if(pant.getSwim() && pant.getClean())
                                {
                                    test3 = false;

                                    boolean test4 = true;
                                    while(test4)
                                    {
                                        int shirtNum = (int) (Math.random()*47);
                                        shirt = closet.get(0).get(shirtNum);
                                        String shirtColor = shirt.getColor();
                                        if(shirtColor.equals("white") && shirt.getClean())
                                        {
                                            test4 = false;

                                            boolean test5 = true;
                                            while(test5)
                                            {
                                                int shoeNum = (int) (Math.random()*11);
                                                shoe = closet.get(2).get(shoeNum);
                                                if(shoe.getSwim())
                                                {
                                                    test5 = false;

                                                    boolean test6 = true;
                                                    while(test6)
                                                    {
                                                        int hatNum = (int) (Math.random()*9);
                                                        hat = closet.get(3).get(hatNum);
                                                        if(hat.getColor().equals("none"))
                                                        {
                                                            test6 = false;

                                                            boolean test7 = true;
                                                            while(test7)
                                                            {
                                                                int jacketNum = (int)(Math.random()*11);

                                                                jacket = closet.get(4).get(jacketNum);
                                                                boolean jacketNut;
                                                                if(jacket.getColor().equals("white") || jacket.getColor().equals("gray") || jacket.getColor().equals("black"))
                                                                {
                                                                    jacketNut = true;
                                                                }
                                                                else
                                                                {
                                                                    jacketNut = false;
                                                                }
                                                                boolean match;
                                                                if(jacketNut && !(jacket.getColor().equals(pantColor)))
                                                                {
                                                                    match = true;
                                                                }
                                                                else if(!jacketNut && pantColor.equals("black"))
                                                                {
                                                                    match = true;
                                                                }
                                                                else
                                                                {
                                                                    match = false;
                                                                }
                                                                if(temp < 60 && !(jacket.getColor().equals("none")) && match)
                                                                {
                                                                    test7 = false;
                                                                }
                                                                else if(temp >= 60 && jacket.getColor().equals("none"))
                                                                {
                                                                    test7 = false;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            Outfit swim = new Outfit(shirt, pant, shoe, hat, jacket);
                            dailyWardrobe.add(swim);
                            actNum++;
                        }
                        else if(ans.equals("manly"))
                        {
                            Item shirt = new Item();
                            Item pant = new Item();
                            Item shoe = new Item();
                            Item hat = new Item();
                            Item jacket = new Item();

                            boolean test3 = true;
                            while(test3)
                            {
                                //give clean gym pants, school shirt, and school shoes

                                int pantNum = (int) (Math.random()*(36));      //number b/w 0 & 4
                                pant = closet.get(1).get(pantNum);

                                if(pant.getManly() && pant.getClean())
                                {
                                    test3 = false;

                                    String pantColor = pant.getColor();
                                    boolean test4 = true;
                                    while(test4)
                                    {
                                        int shirtNum = (int) (Math.random()*(47));//random number bw 0 and 4
                                        shirt = closet.get(0).get(shirtNum);
                                        String shirtColor = shirt.getColor();
                                        boolean shirtNut;
                                        if(shirtColor.equals("black") || shirtColor.equals("white") || shirtColor.equals("gray"))
                                        {
                                            shirtNut = true;
                                        }
                                        else
                                        {
                                            shirtNut = false;
                                        }
                                        boolean match;
                                        if(!(shirtColor.equals(pantColor))) 
                                        {
                                            match = true;
                                        }
                                        if(shirtNut)
                                        {
                                            match = true;
                                        }
                                        else
                                        {
                                            match = false;
                                        }

                                        if(shirt.getManly() && shirt.getClean() && match)
                                        {
                                            test4 = false;

                                            boolean test5 = true;
                                            while(test5)
                                            {
                                                int shoeNum = (int) (Math.random()*(11));//random number bw 0 and 4
                                                shoe = closet.get(2).get(shoeNum);
                                                if(shoe.getManly())
                                                {
                                                    test5 = false;

                                                    boolean test6 = true;
                                                    while(test6)
                                                    {
                                                        int hatNum = (int) (Math.random()*(9));
                                                        hat = closet.get(3).get(hatNum);                                                        
                                                        if(hat.getColor().equals("brown") || hat.getColor().equals("black") || hat.getColor().equals("white") || hat.getColor().equals("gray") || hat.getColor().equals("none"))
                                                        {
                                                            test6 = false;
                                                            
                                                            boolean test7 = true;
                                                            while(test7)
                                                            {
                                                                int jacketNum = (int)(Math.random()*11);
                                                                jacket = closet.get(4).get(jacketNum);
                                                                
                                                                if(temp < 60 && !(jacket.getColor().equals("none")) && jacket.getManly())
                                                                {
                                                                    test7 = false;
                                                                }
                                                                else if(temp >= 60 && jacket.getColor().equals("none"))
                                                                {
                                                                    test7 = false;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }  
                            }
                            Outfit manly = new Outfit(shirt, pant, shoe, hat, jacket);
                            dailyWardrobe.add(manly);
                            actNum++;
                        }
                        else
                        {
                            System.out.println("not a valid answer!");
                        }
                    }
                    else if(ans.equals("n"))
                    {
                        test2 = false;
                    }
                    else
                    {
                        System.out.print("Not valid answer");
                    }
                }
                for(Outfit i: dailyWardrobe)
                {
                    i.getShirt().setClean(false);
                    i.getPant().setClean(false);
                }
                int num = 1;
                for(Outfit i: dailyWardrobe)
                {
                    System.out.println("\nOutfit " + num + ": ");
                    System.out.println(i.toString());
                    num++;
                }
            }

        }
        
    }

    //gets the amount of clothing items: ex: if there are 5 shirts and "shirtList.csv" is put in the paramenter then method returns 5;
    public int getRows(String str) throws Exception
    {
        Scanner testRows = new Scanner(new File(str));
        int numRows = 0;
        while(testRows.hasNextLine())     //gets number of rows
        {
            testRows.nextLine();
            numRows++;
        }
        return numRows;
    }

    //FillArray method fills closet with clothing items and atributes from the csv files
    public ArrayList<ArrayList<Item>> fillArray(String[] fileA, int x, int y) throws Exception
    {
        int xAxisLen = x;   //number of clothing types (5)
        int yAxisLen = y;   //number of clothing spaces for a cltohing item to stay in each type of cloths
        ArrayList<ArrayList<Item>> newList = new ArrayList<>();         //makes new 2D arrayList
        String[] fileArray = new String[5];                             //array of strings holding the names of the clothing csv files
        System.arraycopy(fileA, 0, fileArray, 0, fileA.length);     //coppies fileA to fileArray
        Scanner fileIn;
        String str = "";

        boolean clean, school, work, gym, swim, nice, friends, warm, manly, golf;       //attributes that are on each clothing item

        for(int type = 0; type < xAxisLen; type++)      //loops for each type of clothing item
        {
            fileIn = new Scanner(new File(fileArray[type])); // fileIn is a scanner scanning one csv file at a time
            newList.add(new ArrayList<>(yAxisLen)); //adds a new arraylist  to newList for holding items
            while(fileIn.hasNextLine())           //loops for each
            {
                str = fileIn.nextLine();                        //goes through each line/item in the selected csv file
                String[] rowString = str.split(",");        //takes str and makes it a string array seperated by commas
                
                clean = Boolean.parseBoolean(rowString[3]);         //makes all the true/false attributes to each item and convertes from string --> "true" to boolean --> true
                school = Boolean.parseBoolean(rowString[4]);
                work = Boolean.parseBoolean(rowString[5]);
                gym = Boolean.parseBoolean(rowString[6]);
                swim = Boolean.parseBoolean(rowString[7]);
                nice = Boolean.parseBoolean(rowString[8]);
                friends = Boolean.parseBoolean(rowString[9]);
                warm = Boolean.parseBoolean(rowString[10]);
                manly = Boolean.parseBoolean(rowString[11]);
                golf = Boolean.parseBoolean(rowString[12]);

                //try catch statement was used to help me solve a outOfBounds error
                try {
                    newList.get(type).add(new Item(rowString[0], rowString[1], rowString[2], clean, school, work, gym, swim, nice, friends, warm, manly, golf)); //adds item to arrayList
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Index is out of bounds.");
                }
            }
        }
        return newList;
    }
}