package com.tedit.engine.entity;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.tedit.engine.GameRunner;
import com.tedit.engine.graphics.Sprite;
import com.tedit.engine.resource.ResourceManager;

//Should be developed to handler multiple scenes
//persistance in entities
//And handling of an entity' lifetime
public class EntityManager
{
    private HashMap<String, ArrayList<Entity>> entities = new HashMap<String, ArrayList<Entity>>();
    //as of now entities are only supported on a screenbasis, all entities are set/reset for each screen
    private GameRunner game;
    public EntityManager(GameRunner game)
    {
        this.game=game;
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
        for(Entity e : entities.get(ActiveScreen))
        {
            e.update(deltaTime);
        }
    }
    public void renderWorld(String ActiveScreen)
    {
        for(Entity e : entities.get(ActiveScreen))
        {
            e.draw(game.getRenderer());
        }
    }
}
