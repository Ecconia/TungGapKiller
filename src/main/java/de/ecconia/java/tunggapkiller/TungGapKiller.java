package de.ecconia.java.tunggapkiller;

import de.ecconia.java.tunggapkiller.netremoting.NRParser;
import de.ecconia.java.tunggapkiller.netremoting.NRFile;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRClass;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRObject;
import de.ecconia.java.tunggapkiller.tungobjects.TungBoard;
import de.ecconia.java.tunggapkiller.tungobjects.TungWire;
import de.ecconia.java.tunggapkiller.tungobjects.common.TungChildable;
import de.ecconia.java.tunggapkiller.tungobjects.meta.TungObject;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;

public class TungGapKiller
{
	public static void main(String[] args)
	{
		if(args.length < 1)
		{
			printHelp();
			System.exit(1);
		}
		
		boolean overwrite = false;
		boolean verbose = false;
		String filename = null;
		
		for(String arg : args)
		{
			if("-o".equals(arg))
			{
				//Accept more than one.
				overwrite = true;
			}
			else if("-v".equals(arg))
			{
				verbose = true;
			}
			else
			{
				if(filename != null)
				{
					System.out.println("> Only supply at max one filename/path.\n");
					printHelp();
					System.exit(1);
				}
				filename = arg;
			}
		}
		
		if(filename == null)
		{
			printHelp();
			System.exit(1);
		}
		
		File in = new File(filename);
		if(!in.exists())
		{
			System.out.println("File cannot be found: '" + in.getAbsolutePath() + "'");
			System.exit(1);
		}
		
		File out;
		if(overwrite)
		{
			out = in;
		}
		else
		{
			File parent = in.getParentFile();
			String name = in.getName();
			
			int dotIndex = name.lastIndexOf('.');
			if(dotIndex < 0)
			{
				out = new File(parent, name + "-rounded");
			}
			else
			{
				out = new File(parent, name.substring(0, dotIndex) + "-rounded" + name.substring(dotIndex));
			}
		}
		
		new TungGapKiller(in, out, verbose);
	}
	
	private static void printHelp()
	{
		System.out.println("Usage: java -jar TungGapKiller.jar [-o] <filename>");
		System.out.println(" <filename> is the path to the board you want to fix/round.");
		System.out.println(" -o Tells the TungGapKiller to overwrite the provided file and not make a copy with '-rounded' suffix.");
		System.out.println(" -v Print information which stage is currently processed, useful if the program takes too long.");
	}
	
	public TungGapKiller(File in, File out, boolean verbose)
	{
		if(verbose)
		{
			System.out.println("Parsing file: " + in.getName());
		}
		try
		{
			NRFile pf = NRParser.parse(in);
			
			if(pf.getRootElements().isEmpty())
			{
				throw new RuntimeException("The .NET Remoting file appears to be 'empty'.");
			}
			
			NRObject object = pf.getRootElements().get(0);
			NRClass firstClass;
			if(object instanceof NRClass)
			{
				firstClass = (NRClass) object;
			}
			else
			{
				throw new RuntimeException("First object in .NET Remoting file is not a class: " + object.getClass().getSimpleName());
			}
			
			if(TungBoard.NAME.equals(firstClass.getName()))
			{
				if(verbose)
				{
					System.out.println("Converting to TUNG-Components...");
				}
				
				TungBoard board = new TungBoard(firstClass);
				
				if(verbose)
				{
					System.out.println("Rounding angles and positions...");
				}
				
				//Fixer:
				fix(board);
				
				if(verbose)
				{
					System.out.println("Exporting to: " + out.getName());
				}
				
				new Exporter(out, board);
				
				if(verbose)
				{
					System.out.println("Done.");
				}
			}
			else
			{
				throw new RuntimeException("First class in .NET Remoting file is not a SavedCircuitBoard: " + firstClass.getName());
			}
		}
		catch(Exception e)
		{
			PrintStream o = System.err;
			if(verbose)
			{
				o = System.out;
			}
			
			o.println("You encountered a bug in this program. In the best case it is because you supplied a wrong file. However if the file is proper this is a real bug. Please report this bug (if not intentionally caused by you) to the developer on https://github.com/Ecconia/TungGapKiller/issues. Thanks for helping :)");
			o.println("Please provide the file you supplied and the following stacktrace in your bug-report:");
			e.printStackTrace(o);
			
			System.exit(1);
		}
	}
	
	private void fix(TungChildable holder)
	{
		for(TungObject to : holder.getChildren())
		{
			if(to instanceof TungWire)
			{
				continue; //Lets rather not round wires angles and position. The very likely breaks them.
			}
			
			to.getPosition().fix();
			to.getAngles().fix();
			
			if(to instanceof TungChildable)
			{
				fix((TungChildable) to);
			}
		}
	}
}
