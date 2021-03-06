package de.ecconia.java.tunggapkiller.netremoting;

import de.ecconia.java.tunggapkiller.netremoting.elements.NRArray;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRHeader;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRLibrary;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRProperDefinedClass;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRReferencedClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class NRParser
{
	public static NRFile parse(File file)
	{
		try
		{
			byte[] data = Files.readAllBytes(file.toPath());
			ByteReader reader = new ByteReader(data);
			return parse(reader);
		}
		catch(IOException e)
		{
			throw new RuntimeException("Could not read file.", e);
		}
	}
	
	private static NRFile parse(ByteReader reader)
	{
		NRFile pf = new NRFile();
		NRParseBundle b = new NRParseBundle(reader, pf);
		
		loop:
		while(true)
		{
			int tag = reader.readUnsignedByte();
//			System.out.print("\n-> ");
			switch(tag)
			{
				case 0:
				{
					pf.setHeader(new NRHeader(b));
					break;
				}
				case 1:
				{
					pf.addRoot(new NRReferencedClass(b));
					break;
				}
				case 5:
				{
					pf.addRoot(new NRProperDefinedClass(b));
					break;
				}
				case 7:
				{
					pf.addRoot(new NRArray(b));
					break;
				}
				case 11:
//					System.out.println("End.");
					break loop;
				case 12:
				{
					pf.registerLibrary(new NRLibrary(b));
					break;
				}
				default:
				{
					throw new RuntimeException("Unknown .NET Remoting root record: " + tag);
				}
			}
		}
		
		if(reader.hasMore())
		{
			throw new RuntimeException(".NET Remoting file was not fully read " + reader.getRemaining() + " bytes left.");
		}
		
		b.resolve();
		
		return pf;
	}
}
