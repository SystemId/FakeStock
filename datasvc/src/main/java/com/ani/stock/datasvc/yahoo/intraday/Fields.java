package com.ani.stock.datasvc.yahoo.intraday;

public class Fields
{
    private String issuer_name_lang;

    private String symbol;

    private String ts;

    private String issuer_name;

    private String change;

    private String day_low;

    private String type;

    private String day_high;

    private String price;

    private String utctime;

    private String name;

    private String year_low;

    private String volume;

    private String year_high;

    private String chg_percent;

    public String getIssuer_name_lang ()
    {
        return issuer_name_lang;
    }

    public void setIssuer_name_lang (String issuer_name_lang)
    {
        this.issuer_name_lang = issuer_name_lang;
    }

    public String getSymbol ()
    {
        return symbol;
    }

    public void setSymbol (String symbol)
    {
        this.symbol = symbol;
    }

    public String getTs ()
    {
        return ts;
    }

    public void setTs (String ts)
    {
        this.ts = ts;
    }

    public String getIssuer_name ()
    {
        return issuer_name;
    }

    public void setIssuer_name (String issuer_name)
    {
        this.issuer_name = issuer_name;
    }

    public String getChange ()
    {
        return change;
    }

    public void setChange (String change)
    {
        this.change = change;
    }

    public String getDay_low ()
    {
        return day_low;
    }

    public void setDay_low (String day_low)
    {
        this.day_low = day_low;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getDay_high ()
    {
        return day_high;
    }

    public void setDay_high (String day_high)
    {
        this.day_high = day_high;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getUtctime ()
    {
        return utctime;
    }

    public void setUtctime (String utctime)
    {
        this.utctime = utctime;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getYear_low ()
    {
        return year_low;
    }

    public void setYear_low (String year_low)
    {
        this.year_low = year_low;
    }

    public String getVolume ()
    {
        return volume;
    }

    public void setVolume (String volume)
    {
        this.volume = volume;
    }

    public String getYear_high ()
    {
        return year_high;
    }

    public void setYear_high (String year_high)
    {
        this.year_high = year_high;
    }

    public String getChg_percent ()
    {
        return chg_percent;
    }

    public void setChg_percent (String chg_percent)
    {
        this.chg_percent = chg_percent;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [issuer_name_lang = "+issuer_name_lang+", symbol = "+symbol+", ts = "+ts+", issuer_name = "+issuer_name+", change = "+change+", day_low = "+day_low+", type = "+type+", day_high = "+day_high+", price = "+price+", utctime = "+utctime+", name = "+name+", year_low = "+year_low+", volume = "+volume+", year_high = "+year_high+", chg_percent = "+chg_percent+"]";
    }
}