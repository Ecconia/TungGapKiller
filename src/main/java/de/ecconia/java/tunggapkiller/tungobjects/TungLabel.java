package de.ecconia.java.tunggapkiller.tungobjects;

import de.ecconia.java.tunggapkiller.netremoting.elements.NRClass;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRField;
import de.ecconia.java.tunggapkiller.netremoting.elements.fields.NRFloatField;
import de.ecconia.java.tunggapkiller.netremoting.elements.fields.NRStringField;
import de.ecconia.java.tunggapkiller.tungobjects.meta.TungObject;

public class TungLabel extends TungObject
{
	private float fontSize;
	private String text;
	
	public TungLabel(NRClass clazz)
	{
		for(NRField field : clazz.getFields())
		{
			if(checkField(field))
			{
				continue;
			}
			
			String name = field.getName();
			if("FontSize".equals(name))
			{
				if(field instanceof NRFloatField)
				{
					fontSize = ((NRFloatField) field).getValue();
				}
				else
				{
					throw new RuntimeException("Expected FloatField as inner value, but got " + field.getClass().getSimpleName());
				}
			}
			else if("text".equals(name))
			{
				if(field instanceof NRStringField)
				{
					text = ((NRStringField) field).getValue();
				}
				else
				{
					throw new RuntimeException("Expected BooleanField as inner value, but got " + field.getClass().getSimpleName());
				}
			}
		}
	}
	
	public float getFontSize()
	{
		return fontSize;
	}
	
	public String getText()
	{
		return text;
	}
}
