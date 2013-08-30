package com.tedit.engine.resource;

import com.tedit.engine.graphics.Sprite;
import com.tedit.engine.io.IOReader;
import com.tedit.engine.io.IoLoadable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.SparseArray;

public class ResourceManager implements IoLoadable
{
	//Contains the mapping of Resource GUID for all resourceObjects
	private SparseArray<Resource> resources = new SparseArray<Resource>();
	//contains mapping of loadrequests that has been started but not recived
	private SparseArray<ResourceType> pendingFetches = new SparseArray<ResourceType>();
	//Resource id is implemented as simple as increasing the variable by 1 for each load method call
	private int currentResourceId = 0;
	
	public ResourceManager()
	{
		
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
		int hashCode = pathToSprite.hashCode();
		if(resources.get(hashCode)==null)
		{		
			//put the resource id in pending fetches list
			pendingFetches.put(hashCode, ResourceType.Sprite);
			readFileAsync(pathToSprite, hashCode);
		}
		return hashCode;
		
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
