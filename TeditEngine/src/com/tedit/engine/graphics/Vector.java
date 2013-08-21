package com.tedit.engine.graphics;

public class Vector {
	
	public float xValue;
	public float yValue;
	
	public Vector(float x, float y)
	{
		this.xValue = x;
		this.yValue = y;
	}
	public Vector(Vector copy)
	{
		this.xValue = copy.xValue;
		this.yValue = copy.yValue;
	}
	public static Vector Zero()
	{
		return new Vector(0,0);
	}
	/**
	 * Adds a vector to this vector
	 * @param toAdd the Vector object to add
	 */
	public void add(Vector toAdd)
	{
		this.xValue+=toAdd.xValue;
		this.yValue+=toAdd.yValue;
	}
	public void sub(Vector toSub)
	{
		this.xValue-=toSub.xValue;
		this.yValue-=toSub.yValue;
	}
	public float length()
	{
		return (float) Math.sqrt((xValue*xValue)+(yValue*yValue));
	}
}
