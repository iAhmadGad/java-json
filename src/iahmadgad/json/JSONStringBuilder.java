package iahmadgad.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;

public class JSONStringBuilder 
{
	private static JSONObject object;
	private static String JSONString = "";
	private static byte counter = 0;
	
	public JSONStringBuilder(JSONObject object)
	{
		JSONStringBuilder.object = object;
	}
	
	public String getJSONString()
	{
		if(JSONString == "") build(object, 0);
		return JSONString;
	}
	
	private void build(JSONObject object, int i)
	{
		JSONString += getIndentation(i) + "{\n";
		counter = 0;
		for(Entry entry: object.entrySet())
		{
			JSONString += getIndentation(i + 1) + (counter != 0 ? ',' : "") + "\"" + entry.getKey() + "\"" + getSpaceAroundColon() + ':' + getSpaceAroundColon();
			if(Validator.isJSONArray(entry.getValue()))
			{
				build((JSONArray) entry.getValue(), i + 1);
				JSONString += "\n";
				counter++;
			}
			else if(Validator.isJSONObject(entry.getValue()))
			{
				build((JSONObject) entry.getValue(), i + 1);
				JSONString += "\n";
				counter++;
			}
			else
			{
				JSONString += (Validator.isString(entry.getValue()) ? "\"" + entry.getValue() + "\"" : entry.getValue()) + "\n";
				counter++;
			}
		}
		JSONString += getIndentation(i) + "}\n";
	}
	
	private void build(JSONArray array, int i)
	{
		JSONString += getIndentation(i);
		JSONString += "[\n";
		for(int j = 0; j < array.size(); j++)
		{
			if(Validator.isJSONArray(array.get(j)))
			{
				build((JSONArray) array.get(j), i + 1);
				JSONString += "\n";
			}
			else if(Validator.isJSONObject(array.get(j)))
			{
				build((JSONObject) array.get(j), i + 1);
				JSONString += "\n";
			}
			else 
			{
				JSONString += getIndentation(i + 1) + (array.size() - j != array.size() ? "," : "") + (Validator.isString(array.get(j)) ? "\"" + array.get(j) + "\"" : array.get(j)) + "\n";
			}
		}
		JSONString += getIndentation(i) + "]\n";
	}
	
	public void write(File file)
	{
		try 
		{
			FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);
			writer.write(getJSONString());
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	private int indentation = 5;
	private int spaceAroundColon = 0;
	
	public void setIndentation(int i)
	{
		indentation = i;
	}
	
	public void setIndentation(SBEnum e)
	{
		if (e == SBEnum.DEFAULT) indentation = 5;
		else if(e == SBEnum.NONE) indentation = 0;
	}
	
	public String getIndentation(int i)
	{
		String string = "";
		for(int j = 0; j < i * indentation; j++) string += " ";
		return string;
	}
	
	public void setSpaceAroundColon(int i)
	{
		spaceAroundColon = i;
	}
	
	public void setSpaceAroundColon(SBEnum e)
	{
		if (e == SBEnum.DEFAULT) spaceAroundColon = 0;
		else if(e == SBEnum.NONE) spaceAroundColon = 0;
	}
	
	public String getSpaceAroundColon()
	{
		String string = "";
		for(int j = 0; j < spaceAroundColon; j++) string += " ";
		return string;
	}
	
	public void setDefault()
	{
		indentation = 3;
		spaceAroundColon = 0;
	}
	
	public enum SBEnum 
	{
		DEFAULT,
		NONE
	}
}
