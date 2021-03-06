package de.ecconia.java.tunggapkiller.netremoting.elements;

import de.ecconia.java.tunggapkiller.netremoting.NRParseBundle;

public class NRLibrary extends NRObject
{
	private String name;
	
	public NRLibrary(NRParseBundle b)
	{
		b.readAndStoreID(this);
		name = b.string();
		
//		System.out.println("Library: ID: " + id + " Name: " + name);
	}
	
	public NRLibrary(int id, String name)
	{
		super(id);
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
