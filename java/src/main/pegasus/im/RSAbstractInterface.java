/**
	c-pture/Pegasus (Manu on Rune-Server)
	Copyright (c) 2022 - Pegasus/C-Pture Team
	Written by Manuel K. - Germany
	Version: 1.1.0-beta.1
	
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package main.pegasus.im;

import java.util.HashMap;

public abstract class RSAbstractInterface {

	private int interfaceId = -1;
	
	private HashMap<String, Object> attributes = new HashMap<String, Object>();
	
	public RSAbstractInterface(int interfaceId) {
		this.interfaceId = interfaceId;
	}
	
	public int getInterfaceId() {
		return this.interfaceId;
	}
	
	public RSAbstractInterface setAttribute(String key, Object value) {
		if(attributes.containsKey(key)) {
			attributes.replace(key, value);
			return this;
		}
		attributes.put(key, value);
		return this;
	}
	
	public RSAbstractInterface setChildId(int childId) {
		this.setAttribute("CHILD_ID", childId);
		return this;
	}
	
	public RSAbstractInterface setWindowId(int windowId) {
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
	
	public RSAbstractInterface finalizeOperation() {
		if(!checkBasicSyntax()) 
			return null;
		return this;
	}
	
	/**
	 * Just a basic list of the essential attributes which should be contained within the
	 * interface. Makes it a bit easier to check them all
	 */
	private String[] essential_attributes = {
			"WALKABLE",
			"WINDOW_ID",
			"CHILD_ID",
			"GAMEFRAME"
	};
	
	/**
	 * Check if the basic syntax of the interface is correct
	 * Checking for essential attributes in order to properly open the interface
	 * If some essential information is missing it returns false
	 * If all essential information is contained it returns true
	 * @param interface_
	 * @return all essential attributes are contained in the interface
	 */
	public boolean checkBasicSyntax() {
		for(int i = 0; i < essential_attributes.length; i++) {
			if(getAttribute(essential_attributes[i]) == null) {
				System.out.println("Error! Syntax not correct! Missing: " + essential_attributes[i]);
				return false;
			}
		}
		return true;
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
