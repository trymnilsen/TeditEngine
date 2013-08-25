package com.tedit.engine.entity;

import com.tedit.engine.graphics.Vector;

public class EntityTransformation
{
    private Vector localPosition;
    private Vector localScale;
    private float localRotation;
    private Entity entityToTransform;
    public EntityTransformation(Entity parent)
    {
        entityToTransform = parent;
        localPosition = Vector.Zero();
        localScale = Vector.One();
        localRotation = 0;
    }
    public EntityTransformation(Entity parent, Vector position, Vector scale, float rotation)
    {
        entityToTransform=parent;
        localPosition=position;
        localScale = scale;
        localRotation = rotation;
    }
    public void translate(Vector translation)
    {
        this.localPosition.add(translation);
    }
    public Vector getPostion()
    {
        return localPosition;
    }
    //recursive function getting the absolute position based on the parent(s) relative positions to each other
    public Vector getAbsPostion()
    {
        return getAbsPosition(Vector.Zero());
    }
    public Vector getAbsPosition(Vector thisVector)
    {
        if(entityToTransform.getParent()!=null)
        {
            thisVector.add(entityToTransform.transformation.localPosition);
            thisVector=entityToTransform.getParent().transformation.getAbsPosition(thisVector);
        }
        return thisVector;
    }
}
