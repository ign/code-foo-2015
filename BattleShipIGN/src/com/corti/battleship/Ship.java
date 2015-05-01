package com.corti.battleship;

import javafx.scene.Parent;

public class Ship extends Parent{
    public int type;
    public boolean vertical = true;
    public String name;

    private int health;

    public Ship(int newType, String newName, boolean newVertical) 
    {
    	type = newType;
    	vertical = newVertical;
    	name = newName;
        health = type;
    }

    public void hit() 
    {
        health--;
    }

    public boolean isAlive() 
    {
        return health > 0;
    }
    
    public void setName(String newName)
    {
    	name = newName;
    }
    
    public String getName()
    {
    	return name;
    }
}