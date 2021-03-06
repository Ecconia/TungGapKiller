package de.ecconia.java.tunggapkiller.netremoting.elements.fields;

import de.ecconia.java.tunggapkiller.netremoting.NRParseBundle;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRField;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRText;

public class NRStringField extends NRField
{
	private NRText text;
	
	@Override
	public NRField copy()
	{
		NRField field = new NRStringField();
		field.setName(getName());
		return field;
	}
	
	@Override
	public void parseContent(NRParseBundle b)
	{
		text = new NRText(b, true);
	}
	
	public String getValue()
	{
		if(text == null)
		{
			return null;
		}
		else
		{
			return text.getContent();
		}
	}
}
