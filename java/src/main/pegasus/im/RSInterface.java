package main.pegasus.im;

import java.util.HashMap;

/**
 * 
 * @author manu
 * This class is used to create an instance of a interface
 *
 */

public abstract class RSInterface {

	private int interfaceId;
	
	private HashMap<String, Object> attributes = new HashMap<String, Object>();
	
	public RSInterface(int interfaceId) {
		this.interfaceId = interfaceId;
	}
	
	public int getInterfaceId() {
		return this.interfaceId;
	}
	
	public RSInterface setAttribute(String key, Object value) {
		if(attributes.containsKey(key)) {
			attributes.replace(key, value);
			return this;
		}
		attributes.put(key, value);
		return this;
	}
	
	public RSInterface setChildId(int childId) {
		this.setAttribute("CHILD_ID", childId);
		return this;
	}
	
	public RSInterface setWindowId(int windowId) {
		this.setAttribute("WINDOW_ID", windowId);
		return this;
	}
 	
	public Object getAttribute(String key) {
		if(attributes.get(key) == null)
			return null;
		return attributes.get(key);
	}
	
	public int getChildId() {
		return (Integer) getAttribute("CHILD_ID");
	}
	
	public int getWindowId() {
		return (Integer) getAttribute("WINDOW_ID");
	}
	
	/**
	 * Define a additional check for specific interfaces
	 * Can be used to seperate certain interfaces from others and to add
	 * requirements like quest completion etc.
	 * @return status
	 * 
	 * If status == -1 or anything else => Not defined or not used, check is ignored
	 * If status == 7 => Interface can be opened and check is complete
	 * If status == 9 => Interface can not be opened, check is complete and interface wont
	 * 					 be opened
	 */
	public abstract int openInterface();
	
}
