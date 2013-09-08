package com.tedit.engine.parser;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tedit.engine.GameView;
import com.tedit.engine.io.IOReader;

public class GameParser
{
    String contentString;
    String filePath;
    public GameParser(String filePath)
    {
        this.filePath=filePath;
    }
    public void Parse()
    {
        try
        {
            contentString = new String(IOReader.ReadFileSync(filePath), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            Log.e("Parse", e.toString());
            e.printStackTrace();
        }
        
        JsonElement jsonParsed = new JsonParser().parse(contentString);
        JsonObject gameObject = jsonParsed.getAsJsonObject();
        
        Log.d("Parse", contentString);
        if(gameObject == null)
        {
            Log.w("Parse", "JsonParsedNUILL");
        }
        JsonObject assets = gameObject.getAsJsonObject("assets");
        Log.d("Fnis", "Yo"+assets.toString());
//        AssetsParser assets = new AssetsParser(gameObject.getAsJsonObject("Assets"));
//        ScreenParser screens = new ScreenParser(gameObject.getAsJsonArray("gameScreens"));
    }

}
