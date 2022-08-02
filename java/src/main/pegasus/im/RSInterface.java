/**

	c-pture/Pegasus (Manu on Rune-Server)
	Copyright (c) 2022 - Pegasus/C-Pture Team
	Written by Manuel K. - Germany

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
