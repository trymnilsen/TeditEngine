package com.tedit.engine.resource;

import java.util.HashMap;

import com.tedit.engine.graphics.Sprite;
import com.tedit.engine.io.IOReader;
import com.tedit.engine.io.IoLoadable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.SparseArray;

public class ResourceManager implements IoLoadable
{
	private static final ResourceManager INSTANCE = new ResourceManager();
	//Contains the mapping of Resource GUID for all resourceObjects
	private SparseArray<Resource> resources = new SparseArray<Resource>();
	//contains mapping of loadrequests that has been started but not recived
	private SparseArray<ResourceType> pendingFetches = new SparseArray<ResourceType>();
	//Conatins mapping of resourcepath to resourceGUID
	private HashMap<String, Integer> resourcesLoaded = new HashMap<String, Integer>();
	//Resource id is implemented as simple as increasing the variable by 1 for each load method call
	//Then mapped by path name enabling easy lookup if assets is loaded. Completely eliminates the chance of collisions
	private int currentUnusedResourceId = 0;
	
	private ResourceManager() //avoid creating of another
	{
		
	}
	/**
	 * Singleton pattern implementation
	 * @return The resourcemanager instance
	 */
	public static ResourceManager getInstance()
	{
		return INSTANCE;
	}
	/**
	 * Checks if a sprite with the given id is loaded
	 * @param spritePath the path of the sprite
	 * @return true if loaded false if not
	 */
	public boolean isSpriteLoaded(String spritePath)
	{
	    return resourcesLoaded.containsKey(spritePath);
	}
	/**
	 * Loads a sprite from path
	 * returns the id of the newly created resource, however the resource itself is loaded async and will not be availabe at once.
	 * Classes using resources should handle this
	 * @param pathToSprite path to the sprite to load
	 * @return id representing the GUID of the resource
	 */
	public int LoadSprite(String pathToSprite)
	{
		int resourceId = -1;
		if(resourcesLoaded.containsKey(pathToSprite))
		{
		    resourceId=resourcesLoaded.get(pathToSprite);
		}
		else
		{
		    currentUnusedResourceId++;
		    resourceId = currentUnusedResourceId;
		}
		if(resources.get(resourceId)==null)
		{		
			//put the resource id in pending fetches list
			pendingFetches.put(resourceId, ResourceType.Sprite);
			resourcesLoaded.put(pathToSprite,resourceId);
			readFileAsync(pathToSprite, resourceId);
		}
		return resourceId;
		
	}
	@Override
	//Callback method from the IO thread
	//responsible for decoding the bytes and assigning them to the Fetchid(which is the same as the resourceId)
	public void ioFinishedLoading(byte[] dataLoaded, int fetchId) {
		if(dataLoaded == null)
		{
			Log.e("Resource Load", "Resource "+fetchId+" loaded with no data");
		}
		else
		{
			
		ResourceType loadedResourceType = pendingFetches.get(fetchId);
		
		switch(loadedResourceType)
		{
		case Sprite: 
			//remove from pending map
			pendingFetches.remove(fetchId);
			//Decode asset
			Bitmap loadedSprite = BitmapFactory.decodeByteArray(dataLoaded, 0, dataLoaded.length);
			Sprite newSprite = new Sprite(loadedSprite,fetchId);
			//add to resource map
			resources.put(fetchId, newSprite);
			break;
			
		default: 
			Log.e("Resource Load", "Got callback with data on fetchid with no mapping(not a pending fetc)");
		}
		}
	}
	public <T extends Resource> T getAsset(int resourceId)
	{
		try
		{
			@SuppressWarnings("unchecked")
			T ReturnAsset = (T)resources.get(resourceId);
			return ReturnAsset;
		}
		catch(ClassCastException cce)
		{
			Log.w("Resource", "Invalid cast of asset "+cce.toString());
			return null;
		}
	}
	private void readFileAsync(String path, int fetchId)
	{
		IOReader.FetchResource(path, this, fetchId);
	}
	
}
