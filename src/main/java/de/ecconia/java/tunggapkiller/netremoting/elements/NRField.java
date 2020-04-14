package de.ecconia.java.tunggapkiller.netremoting.elements;

import de.ecconia.java.tunggapkiller.netremoting.NRParseBundle;

public abstract class NRField
{
	private String name;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public abstract NRField copy();
	
	public abstract void parseContent(NRParseBundle b);
}
