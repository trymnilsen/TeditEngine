package com.tedit.engine.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tedit.engine.entity.EntityManager;

public class AssetsParser
{
    JsonObject assetsObject;
    EntityManager entityManager;
    public AssetsParser(JsonObject assetsObject, EntityManager entityManager)
    {
        this.assetsObject = assetsObject;
        this.entityManager = entityManager;
    }
    public void Parse() throws GameParseException
    {
        //Load sprites
        //array of filepaths, hashing(might implement exlipcit mapping) of the filepaths will connect as objects use spritepaths not id
        JsonArray spritesParsed = assetsObject.getAsJsonArray("sprites");
        //Load objects
        JsonArray GameObjectTemplatesParsed = assetsObject.getAsJsonArray("objects");
        
        for(int i=0, l=GameObjectTemplatesParsed.size(); i<l; i++)
        {
            JsonObject gameObject = GameObjectTemplatesParsed.get(i).getAsJsonObject();
            //Parse the Object and create it
            
        }
        
    }
    
}
