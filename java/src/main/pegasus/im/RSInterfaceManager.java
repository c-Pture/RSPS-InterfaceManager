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

public class RSInterfaceManager {

	private transient RSInterface current_interface;
	
	/**
	 * Used to open any interface with the help of the usage of interface attributes
	 * @param interface_
	 */
	public void openInterface(RSInterface interface_) {
		if(interface_.openInterface() == 9) //Actually anything but 9 can be ignored.
			return;
		if(!checkBasicSyntax(interface_)) //Attributes missing
			return;
		
		String interfaceType = (String) interface_.getAttribute("INTERFACE_TYPE");
		int windowId = (Integer) interface_.getAttribute("WINDOW_ID");
		int childId = (Integer) interface_.getAttribute("CHILD_ID");
		
		if(interfaceType.equals("CHATBOX")) {
			
		}
		if(interfaceType.equals("TAB")) {
			
		}
		if(interfaceType.equals("FRAME")) {
			
		}
		current_interface = interface_;
	}
	
	/**
	 * Just a basic list of the essential attributes which should be contained within the
	 * interface. Makes it a bit easier to check them all
	 */
	private String[] essential_attributes = {
			"INTERFACE_TYPE", //THE BASIC INTERFACE TYPE (Chatbox, tab, screen)
			"WINDOW_ID",
			"CHILD_ID"
	};
	
	/**
	 * Check if the basic syntax of the interface is correct
	 * Checking for essential attributes in order to properly open the interface
	 * If some essential information is missing it returns false
	 * If all essential information is contained it returns true
	 * @param interface_
	 * @return all essential attributes are contained in the interface
	 */
	private boolean checkBasicSyntax(RSInterface interface_) {
		for(int i = 0; i < essential_attributes.length; i++) {
			if(interface_.getAttribute(essential_attributes[i]) == null) {
				return false;
			}
		}
		return false;
	}
	
}
