package com.tedit.engine.entity;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;

import com.tedit.engine.GameRunner;
import com.tedit.engine.graphics.Sprite;
import com.tedit.engine.resource.ResourceManager;

//Should be developed to handler multiple scenes
//persistance in entities
//And handling of an entity' lifetime
public class EntityManager
{
    //Active entities in each screen
    private HashMap<String, ArrayList<Entity>> entities;
    //Contains the objects created for the game, each entity is mapped to this and gets a clone of it if its present in a scene
    private SparseArray<Entity> entityTemplates;
    //as of now entities are only supported on a screenbasis, all entities are set/reset for each screen
    private GameRunner game;
    public EntityManager(GameRunner game)
    {
        this.game=game;
        entities = new HashMap<String, ArrayList<Entity>>();
        entityTemplates = new SparseArray<Entity>();
    }
    public void createTemplateEntity(int entityId, String spritePath)
    {
        int resourceId = -1;
        //Loads the sprite
        //Method returns resourceId at once, and starts a load thread if sprite is not loaded.
        resourceId = ResourceManager.getInstance().LoadSprite(spritePath);
    }
    public void createTestEntity()
    {
        
        String path = game.externalPath;
        Log.d("IO", "PATH TO SPRITE"+path+"/skate1.png");
        Bitmap bm = BitmapFactory.decodeFile(path+"/skate1.png");
        
        Sprite testSprite = new Sprite(bm,1);
        Entity testEntity = new Entity(138,ResourceManager.getInstance().LoadSprite(path+"/skate1.png"));
        game.getEventHandler().subscribe(14096, testEntity);
        addEntity("testScreen", testEntity);
    }
    public void addEntity(String name, Entity entity)
    {
        //if scene does not exist as key in entities list, create it
        if(entities.get(name)==null)
        {
            entities.put(name, new ArrayList<Entity>());
        }
        //Add the entity
        entities.get(name).add(entity);
    }
    public void updateWorld(float deltaTime, String ActiveScreen)
    {
        if(entities.get(ActiveScreen)!=null)
        {
            for(Entity e : entities.get(ActiveScreen))
            {
                e.update(deltaTime);
            }
        }
    }
    public void renderWorld(String ActiveScreen)
    {
        if(entities.get(ActiveScreen)!=null)
        {
            for(Entity e : entities.get(ActiveScreen))
            {
                e.draw(game.getRenderer());
            }
        }
    }
}
