package com.ani.stock.datasvc.yahoo.intraday;

public class Resources
{
    private Resource resource;

    public Resource getResource ()
    {
        return resource;
    }

    public void setResource (Resource resource)
    {
        this.resource = resource;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [resource = "+resource+"]";
    }
}