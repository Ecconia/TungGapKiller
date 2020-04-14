package de.ecconia.java.tunggapkiller.netremoting.elements;

import de.ecconia.java.tunggapkiller.netremoting.NRParseBundle;
import de.ecconia.java.tunggapkiller.netremoting.NRFieldResolver;

public class NRProperDefinedClass extends NRClass
{
	public NRProperDefinedClass(NRParseBundle b)
	{
		b.readAndStoreID(this);
		
		this.name = b.string();
		this.fields = NRFieldResolver.parseFileds(b);
		this.library = b.readLibraryAndResolve();
		
//		System.out.println("ClassFull: ID: " + id + " Name: " + name + " Lib: " + library.getId() + " Fields... " + fields.length + "x");
		
		for(NRField field : fields)
		{
			field.parseContent(b);
		}
	}
}
