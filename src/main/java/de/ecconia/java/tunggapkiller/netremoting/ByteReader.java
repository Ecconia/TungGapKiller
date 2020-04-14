package de.ecconia.java.tunggapkiller.netremoting;

public class ByteReader
{
	private final byte[] data;
	private int pointer;
	
	public ByteReader(byte[] data)
	{
		this.data = data;
	}
	
	public int readIntLE()
	{
		return readUnsignedByte() | readUnsignedByte() << 8 | readUnsignedByte() << 16 | readUnsignedByte() << 24;
	}
	
	public int readUnsignedByte()
	{
		return ((int) data[pointer++]) & 255;
	}
	
	public byte[] readBytes(int size)
	{
		byte[] bytes = new byte[size];
		System.arraycopy(data, pointer, bytes, 0, size);
		pointer += size;
		return bytes;
	}
	
	public boolean hasMore()
	{
		return pointer < data.length;
	}
	
	public int getRemaining()
	{
		return data.length - pointer;
	}
	
	private int variableInteger()
	{
		int shiftAmount = 0;
		int value = 0;
		int validation = 0;
		
		int read;
		do
		{
			read = readUnsignedByte();
			value |= ((read & 0b01111111) << shiftAmount);
			shiftAmount += 7;
			if(++validation == 5)
			{
				if(read >> 3 != 0)
				{
					throw new RuntimeException("Read fifth byte of a variable integer, but the upper 5 bits had not been zero: " + Integer.toBinaryString(read));
				}
			}
		}
		while((read & 0x80) != 0);
		
		return value;
	}
	
	public String readBytePrefixedString()
	{
		int byteAmount = variableInteger();
		return new String(readBytes(byteAmount));
	}
	
	public float readFloatLE()
	{
		int value = readIntLE();
		return Float.intBitsToFloat(value);
	}
}
