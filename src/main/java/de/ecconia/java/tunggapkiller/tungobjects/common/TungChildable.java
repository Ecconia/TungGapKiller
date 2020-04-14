package de.ecconia.java.tunggapkiller.tungobjects.common;

import de.ecconia.java.tunggapkiller.netremoting.elements.fields.NRClassField;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRArray;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRField;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRObject;
import de.ecconia.java.tunggapkiller.tungobjects.meta.TungObject;

import java.util.ArrayList;
import java.util.List;

public class TungChildable extends TungObject
{
	private final List<TungObject> children = new ArrayList<>();
	
	@Override
	protected boolean checkField(NRField field)
	{
		if(super.checkField(field))
		{
			return true;
		}
		
		String name = field.getName();
		if("Children".equals(name))
		{
			if(field instanceof NRClassField)
			{
				//Drop class check, get value
				NRObject value = ((NRClassField) field).getValue();
				if(value instanceof NRArray)
				{
					//Skip type check
					for(NRField entry : ((NRArray) value).getEntries())
					{
						TungObject component = convertComponent(entry);
						if(component != null)
						{
							children.add(component);
						}
					}
				}
				else
				{
					throw new RuntimeException("Expected Array as value, but got " + field.getClass().getSimpleName());
				}
			}
			else
			{
				throw new RuntimeException("Expected ClassField as field, but got " + field.getClass().getSimpleName());
			}
			
			return true;
		}
		
		return false;
	}
	
	public List<TungObject> getChildren()
	{
		return children;
	}
}
