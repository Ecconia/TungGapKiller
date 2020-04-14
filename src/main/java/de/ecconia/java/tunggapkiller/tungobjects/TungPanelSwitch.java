package de.ecconia.java.tunggapkiller.tungobjects;

import de.ecconia.java.tunggapkiller.netremoting.elements.NRClass;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRField;
import de.ecconia.java.tunggapkiller.netremoting.elements.fields.NRBooleanField;
import de.ecconia.java.tunggapkiller.tungobjects.meta.TungObject;

public class TungPanelSwitch extends TungObject
{
	private boolean isOn;
	
	public TungPanelSwitch(NRClass clazz)
	{
		for(NRField field : clazz.getFields())
		{
			if(checkField(field))
			{
				continue;
			}
			
			String name = field.getName();
			if("on".equals(name))
			{
				if(field instanceof NRBooleanField)
				{
					isOn = ((NRBooleanField) field).getValue();
				}
				else
				{
					throw new RuntimeException("Expected BooleanField as inner value, but got " + field.getClass().getSimpleName());
				}
			}
		}
	}
	
	public boolean isOn()
	{
		return isOn;
	}
}
