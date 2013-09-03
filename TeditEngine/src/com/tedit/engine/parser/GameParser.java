package com.tedit.engine.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GameParser
{
    public GameParser(String jsonContent)
    {
        JsonElement jsonParsed = new JsonParser().parse(jsonContent);
        JsonObject gameObject = jsonParsed.getAsJsonObject();
        AssetsParser assets = new AssetsParser(gameObject.getAsJsonObject("Assets"));
        ScreenParser screens = new ScreenParser(gameObject.getAsJsonArray("gameScreens"));
    }
}
