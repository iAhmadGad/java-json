package iahmadgad.json;

/*
 * Java JSON Handler
 */

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * JSONObject Class.
 * <p>
 * JSONObject is an object contains (key - value) pairs, 
 * keys are always Strings, 
 * while values can only be integers, doubles, booleans, or nested JSONObjects & JSONArrays, but it can't be chars or any other type.
 * 
 * @author iAhmadGad
 * @version 0.3
 * @since 0.3
*/

public class JSONObject 
{
	/**
	 * The main HashMap that stores JSONObject pairs.
	 */
	private HashMap<String, Object> node;
	
	/**
	 * The JSONObject id
	 * <p>
	 * equals null in default.
	 * to change its value you should put pair ("$id" - String) into the JSONObject.
	 * @see #put(String, Object)
	 */
	private String id = null;
	
	/**
	 * The main Constructor which initialises a new JSONObject.
	 */
	public JSONObject()
	{
		node = new HashMap<String, Object>();
	}
	
	/**
	 * The Constructor which creates a new JSONObject from a text.
	 * @param Source JSON string (text)
	 */
	public JSONObject(String text)
	{
		node = new JSONParser(text).getHashMap();
	}
	
	/**
	 * The Constructor which creates a new JSONObject from a file.
	 * @param Source JSON file
	 */
	public JSONObject(File file)
	{
		node = new JSONParser(file).getHashMap();
	}
	
	/**
	 * The Constructor which creates a new JSONObject from a Java object.
	 * <p>
	 * it creates pairs from the object's fields as variables, arrays & lists (with valid values only as mentioned above).
	 * @param Source Java object
	 */
	public <T> JSONObject(T object)
	{
		node = new Converter().toJSONObject(object.getClass(), object).getNode();
	}
	
	/**
	 * Converts the JSONObject to a Java Object.
	 * <p>
	 * it takes JSONObject keys & assign their values to the object fields which the same name as the key.
	 * @param Java object class
	 * @return An Object of the same class
	 */
	public <T> T toClass(Class<T> c)
	{
		return new Converter().toClass(this, c);
	}
	
	/**
	 * Returns the main HashMap that stores JSONObject pairs.
	 * @return {@link #node}
	 */
	public HashMap<String, Object> getNode()
	{
		return node;
	}
	
	/**
	 * Returns JSONObject id.
	 * @return {@link #id}
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * Iterates over the whole JSONObjects entries.
	 * @return Set of entries
	 */
	public Set<Entry<String, Object>> entrySet()
	{
		return node.entrySet();
	}
	
	/**
	 * Puts pair into the JSONObject.
	 * @param key, value
	 */
	public void put(String key, Object value)
	{
		if(key.compareTo("$id") == 0 && Validator.isString(value)) id = (String) value;
		if(Validator.isValid(value)) node.put(key, value);
	}
	
	/**
	 * Replaces existing key's value in the JSONObject.
	 * @param key, value
	 */
	public void replace(String key, Object value)
	{
		if(Validator.isValid(value)) node.replace(key, value);
	}
	
	/**
	 * Gets Object stored in this key.
	 * @param key
	 * @return Object
	 */
	public Object get(String key)
	{
		return node.get(key);
	}
	
	/**
	 * Gets String stored in this key.
	 * @param key
	 * @return String
	 */
	public String getString(String key)
	{
		return (String) node.get(key);
	}
	
	/**
	 * Gets boolean stored in this key.
	 * @param key
	 * @return boolean
	 */
	public boolean getBoolean(String key)
	{
		return (boolean) node.get(key);
	}
	
	/**
	 * Gets int stored in this key.
	 * @param key
	 * @return int
	 */
	public int getInt(String key)
	{
		return (int) node.get(key);
	}
	
	/**
	 * Gets double stored in this key.
	 * @param key
	 * @return double
	 */
	public double getDouble(String key)
	{
		return (double) node.get(key);
	}
	
	/**
	 * Gets nested JSONObject stored in this key.
	 * @param key
	 * @return JSONObject
	 */
	public JSONObject getJSONObject(String key)
	{
		return (JSONObject) node.get(key);
	}
	
	/**
	 * Gets nested JSONArray stored in this key.
	 * @param key
	 * @return JSONArray
	 */
	public JSONArray getJSONArray(String key)
	{
		return (JSONArray) node.get(key);
	}
	
	/**
	 * Gets object stored in this pointer.
	 * @param pointer
	 * @return Object
	 */
	public Object get(JSONPointer pointer)
	{
		pointer.setNode(this);
		return pointer.getPointee();
	}
	
	/**
	 * Gets String stored in this pointer.
	 * @param pointer
	 * @return String
	 */
	public String getString(JSONPointer pointer)
	{
		pointer.setNode(this);
		return (String) pointer.getPointee();
	}
	
	/**
	 * Gets boolean stored in this pointer.
	 * @param pointer
	 * @return boolean
	 */
	public boolean getBoolean(JSONPointer pointer)
	{
		pointer.setNode(this);
		return (boolean) pointer.getPointee();
	}
	
	/**
	 * Gets int stored in this pointer.
	 * @param pointer
	 * @return int
	 */
	public int getInt(JSONPointer pointer)
	{
		pointer.setNode(this);
		return (int) pointer.getPointee();
	}
	
	/**
	 * Gets double stored in this pointer.
	 * @param pointer
	 * @return double
	 */
	public double getDouble(JSONPointer pointer)
	{
		pointer.setNode(this);
		return (double) pointer.getPointee();
	}
	
	/**
	 * Gets JSONObject stored in this pointer.
	 * @param pointer
	 * @return JSONObject
	 */
	public JSONObject getJSONObject(JSONPointer pointer)
	{
		pointer.setNode(this);
		return (JSONObject) pointer.getPointee();
	}
	
	/**
	 * Gets JSONArray stored in this pointer.
	 * @param pointer
	 * @return JSONArray
	 */
	public JSONArray getJSONArray(JSONPointer pointer)
	{
		pointer.setNode(this);
		return (JSONArray) pointer.getPointee();
	}
}
