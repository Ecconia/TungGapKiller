package de.ecconia.java.tunggapkiller.tungobjects;

import de.ecconia.java.tunggapkiller.netremoting.elements.NRClass;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRField;
import de.ecconia.java.tunggapkiller.tungobjects.meta.TungObject;

public class TungButton extends TungObject
{
	public TungButton(NRClass clazz)
	{
		for(NRField field : clazz.getFields())
		{
			checkField(field);
		}
	}
}
