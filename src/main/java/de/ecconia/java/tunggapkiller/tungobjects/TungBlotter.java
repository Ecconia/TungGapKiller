package de.ecconia.java.tunggapkiller.tungobjects;

import de.ecconia.java.tunggapkiller.netremoting.elements.NRClass;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRField;
import de.ecconia.java.tunggapkiller.netremoting.elements.fields.NRBooleanField;
import de.ecconia.java.tunggapkiller.tungobjects.meta.TungObject;

public class TungBlotter extends TungObject
{
	private boolean outputOn;
	
	public TungBlotter(NRClass clazz)
	{
		for(NRField field : clazz.getFields())
		{
			if(checkField(field))
			{
				continue;
			}
			
			String name = field.getName();
			if("OutputOn".equals(name))
			{
				if(field instanceof NRBooleanField)
				{
					outputOn = ((NRBooleanField) field).getValue();
				}
				else
				{
					throw new RuntimeException("Expected BooleanField as inner value, but got " + field.getClass().getSimpleName());
				}
			}
		}
	}
	
	public boolean isOutputOn()
	{
		return outputOn;
	}
}
