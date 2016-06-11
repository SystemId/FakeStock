package com.ani.stock.datasvc.yahoo.intraday;

public class Resource {
	
	
	private String classname;

    private Fields fields;

    public String getClassname ()
    {
        return classname;
    }

    public void setClassname (String classname)
    {
        this.classname = classname;
    }

    public Fields getFields ()
    {
        return fields;
    }

    public void setFields (Fields fields)
    {
        this.fields = fields;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [classname = "+classname+", fields = "+fields+"]";
    }
	

}
