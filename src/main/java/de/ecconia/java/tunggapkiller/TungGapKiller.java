package de.ecconia.java.tunggapkiller;

import de.ecconia.java.tunggapkiller.netremoting.NRParser;
import de.ecconia.java.tunggapkiller.netremoting.NRFile;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRClass;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRObject;
import de.ecconia.java.tunggapkiller.tungobjects.TungBoard;
import de.ecconia.java.tunggapkiller.tungobjects.common.TungChildable;
import de.ecconia.java.tunggapkiller.tungobjects.meta.TungObject;

import java.io.File;

public class TungGapKiller
{
	public static void main(String[] args)
	{
		new TungGapKiller();
	}
	
	public TungGapKiller()
	{
		NRFile pf = NRParser.parse(new File("boards/16Bit-Paralell-CLA-ALU.tungboard"));
		
		NRObject object = pf.getRootElements().get(0);
		NRClass firstClass;
		if(object instanceof NRClass)
		{
			firstClass = (NRClass) object;
		}
		else
		{
			throw new RuntimeException("Unknown first object: " + object.getClass().getSimpleName());
		}
		
		if(TungBoard.NAME.equals(firstClass.getName()))
		{
			TungBoard board = new TungBoard(firstClass);
			
			//Fixer:
			fix(board);
			
			new Exporter(new File("boards/output.tungboard"), board);
		}
		else
		{
			throw new RuntimeException("First Class has wrong type: " + firstClass.getName());
		}
	}
	
	private void fix(TungChildable holder)
	{
		for(TungObject to : holder.getChildren())
		{
			to.getPosition().fix();
			to.getAngles().fix();
			
			if(to instanceof TungChildable)
			{
				fix((TungChildable) to);
			}
		}
	}
}
