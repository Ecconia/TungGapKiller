package de.ecconia.java.tunggapkiller.netremoting.elements;

import de.ecconia.java.tunggapkiller.netremoting.NRParseBundle;
import de.ecconia.java.tunggapkiller.netremoting.NRFile;

public class NRFieldVarReference extends NRObject
{
	private final int refId;
	private final ResolveCall callable;
	
	public NRFieldVarReference(NRParseBundle b, ResolveCall callable)
	{
		this.callable = callable;
		
		refId = b.sInt();
		b.addResolver(this);
		
//		System.out.println("Resolver: To: " + refId);
	}
	
	public void resolve(NRFile file)
	{
		callable.onCall(file.getObject(refId));
	}
	
	public interface ResolveCall
	{
		void onCall(NRObject object);
	}
}
