package de.ecconia.java.tunggapkiller.tungobjects.meta;

import de.ecconia.java.tunggapkiller.netremoting.elements.fields.NRClassField;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRClass;
import de.ecconia.java.tunggapkiller.netremoting.elements.NRField;
import de.ecconia.java.tunggapkiller.tungobjects.TungBlotter;
import de.ecconia.java.tunggapkiller.tungobjects.TungBoard;
import de.ecconia.java.tunggapkiller.tungobjects.TungButton;
import de.ecconia.java.tunggapkiller.tungobjects.TungColorDisplay;
import de.ecconia.java.tunggapkiller.tungobjects.TungDelayer;
import de.ecconia.java.tunggapkiller.tungobjects.TungDisplay;
import de.ecconia.java.tunggapkiller.tungobjects.TungInverter;
import de.ecconia.java.tunggapkiller.tungobjects.TungLabel;
import de.ecconia.java.tunggapkiller.tungobjects.TungMount;
import de.ecconia.java.tunggapkiller.tungobjects.TungNoisemaker;
import de.ecconia.java.tunggapkiller.tungobjects.TungPanelButton;
import de.ecconia.java.tunggapkiller.tungobjects.TungPanelColorDisplay;
import de.ecconia.java.tunggapkiller.tungobjects.TungPanelDisplay;
import de.ecconia.java.tunggapkiller.tungobjects.TungPanelLabel;
import de.ecconia.java.tunggapkiller.tungobjects.TungPanelSwitch;
import de.ecconia.java.tunggapkiller.tungobjects.TungPeg;
import de.ecconia.java.tunggapkiller.tungobjects.TungSnappingPeg;
import de.ecconia.java.tunggapkiller.tungobjects.TungSwitch;
import de.ecconia.java.tunggapkiller.tungobjects.TungThroughBlotter;
import de.ecconia.java.tunggapkiller.tungobjects.TungThroughPeg;
import de.ecconia.java.tunggapkiller.tungobjects.TungWire;
import de.ecconia.java.tunggapkiller.tungobjects.common.Angles;
import de.ecconia.java.tunggapkiller.tungobjects.common.Position;

public class TungObject implements Angles, Position
{
	private TungAngles angles;
	private TungPosition position;
	
	protected boolean checkField(NRField field)
	{
		String name = field.getName();
		if("LocalEulerAngles".equals(name))
		{
			angles = new TungAngles(field);
			return true;
		}
		else if("LocalPosition".equals(name))
		{
			position = new TungPosition(field);
			return true;
		}
		return false;
	}
	
	@Override
	public TungAngles getAngles()
	{
		return angles;
	}
	
	@Override
	public TungPosition getPosition()
	{
		return position;
	}
	
	protected TungObject convertComponent(NRField field)
	{
		//Assume its a class...
		NRClass childClass = (NRClass) ((NRClassField) field).getValue();
		String className = childClass.getName();
		if("SavedObjects.SavedCircuitBoard".equals(className))
		{
			return new TungBoard(childClass);
		}
		else if("SavedObjects.SavedBlotter".equals(className))
		{
			return new TungBlotter(childClass);
		}
		else if("SavedObjects.SavedButton".equals(className))
		{
			return new TungButton(childClass);
		}
		else if("SavedObjects.SavedColorDisplay".equals(className))
		{
			return new TungColorDisplay(childClass);
		}
//		else if("SavedObjects.SavedCustomObject".equals(className))
//		{
//		}
		else if("SavedObjects.SavedDelayer".equals(className))
		{
			return new TungDelayer(childClass);
		}
		else if("SavedObjects.SavedDisplay".equals(className))
		{
			return new TungDisplay(childClass);
		}
		else if("SavedObjects.SavedInverter".equals(className))
		{
			return new TungInverter(childClass);
		}
		else if("SavedObjects.SavedLabel".equals(className))
		{
			return new TungLabel(childClass);
		}
		else if("SavedObjects.SavedMount".equals(className))
		{
			return new TungMount(childClass);
		}
		else if("SavedObjects.SavedNoisemaker".equals(className))
		{
			return new TungNoisemaker(childClass);
		}
		else if("SavedObjects.SavedPanelButton".equals(className))
		{
			return new TungPanelButton(childClass);
		}
		else if("SavedObjects.SavedPanelColorDisplay".equals(className))
		{
			return new TungPanelColorDisplay(childClass);
		}
		else if("SavedObjects.SavedPanelDisplay".equals(className))
		{
			return new TungPanelDisplay(childClass);
		}
		else if("SavedObjects.SavedPanelLabel".equals(className))
		{
			return new TungPanelLabel(childClass);
		}
		else if("SavedObjects.SavedPanelSwitch".equals(className))
		{
			return new TungPanelSwitch(childClass);
		}
		else if("SavedObjects.SavedPeg".equals(className))
		{
			return new TungPeg(childClass);
		}
		else if("SavedObjects.SavedSnappingPeg".equals(className))
		{
			return new TungSnappingPeg(childClass);
		}
		else if("SavedObjects.SavedSwitch".equals(className))
		{
			return new TungSwitch(childClass);
		}
		else if("SavedObjects.SavedThroughBlotter".equals(className))
		{
			return new TungThroughBlotter(childClass);
		}
		else if("SavedObjects.SavedThroughPeg".equals(className))
		{
			return new TungThroughPeg(childClass);
		}
		else if("SavedObjects.SavedWire".equals(className))
		{
			return new TungWire(childClass);
		}
		
		System.out.println("Unknown component found: " + className);
		return null;
	}
}
