package com.tedit.engine.parser;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
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
            Log.e("StringFromFile", e.toString());
            e.printStackTrace();
        }
        
        JsonObject gameObject;
        try{
        
        JsonElement jsonParsed = new JsonParser().parse(contentString);
        gameObject = jsonParsed.getAsJsonObject();
        //Parses The assets
        AssetsParser assets = new AssetsParser(gameObject.getAsJsonObject("Assets"),null);
        assets.Parse();
        //Parsers the Screens
        ScreenParser screens = new ScreenParser(gameObject.getAsJsonArray("gameScreens"));
        
        }
        catch(JsonSyntaxException jse)
        {

        }
        catch(GameParseException gpe)
        {
        
        }
    }

}
