package de.ecconia.java.tunggapkiller.tungobjects;

import de.ecconia.java.tunggapkiller.netremoting.elements.NRClass;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRField;
import de.ecconia.java.tunggapkiller.netremoting.elements.fields.NRFloatField;
import de.ecconia.java.tunggapkiller.tungobjects.meta.TungObject;

public class TungNoisemaker extends TungObject
{
	private float frequency;
	
	public TungNoisemaker(NRClass clazz)
	{
		for(NRField field : clazz.getFields())
		{
			if(checkField(field))
			{
				continue;
			}
			
			String name = field.getName();
			if("ToneFrequency".equals(name))
			{
				if(field instanceof NRFloatField)
				{
					frequency = ((NRFloatField) field).getValue();
				}
				else
				{
					throw new RuntimeException("Expected FloatField as inner value, but got " + field.getClass().getSimpleName());
				}
			}
		}
	}
	
	public float getFrequency()
	{
		return frequency;
	}
}
