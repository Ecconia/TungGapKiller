package de.ecconia.java.tunggapkiller.netremoting.elements;

public abstract class NRObject
{
	protected int id;
	
	public NRObject()
	{
	}
	
	public NRObject(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
}
