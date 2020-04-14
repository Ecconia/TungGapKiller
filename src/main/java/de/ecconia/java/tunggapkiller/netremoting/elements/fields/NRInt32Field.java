package de.ecconia.java.tunggapkiller.netremoting.elements.fields;

import de.ecconia.java.tunggapkiller.netremoting.NRParseBundle;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRField;

public class NRInt32Field extends NRField
{
	private int value;
	
	@Override
	public NRField copy()
	{
		NRField field = new NRInt32Field();
		field.setName(getName());
		return field;
	}
	
	@Override
	public void parseContent(NRParseBundle b)
	{
		value = b.sInt();
		
//		System.out.println("Read int32: " + value);
	}
	
	public int getValue()
	{
		return value;
	}
}
