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

import java.util.ArrayList;
import java.util.List;

import main.pegasus.im.impl.DefaultRSInterface;

public class RSInterfaceManager {
	
	/**
	 * Currently only support for chatbox interface and screen interfaces
	 */

	private transient RSAbstractInterface current_onscreen_interface 	= null;
	private transient RSAbstractInterface current_chat_interface 		= null;
	
	private transient List<RSAbstractInterface> gameframe_interfaces 	= new ArrayList<RSAbstractInterface>();
	
	private Player player;
	
	public RSInterfaceManager() {
		
	}
	
	public RSInterfaceManager(Player player) {
		this.player = player;
	}
	
	/**
	 * Used to open any interface with the help of the usage of interface attributes
	 * @param interface_
	 */
	public void openInterface(RSAbstractInterface interface_) {
		if(interface_.openInterface() == 9) //Actually anything but 9 can be ignored.
			return;
		if(!interface_.checkBasicSyntax()) //Attributes missing
			return;
		int windowId 		= (Integer) interface_.getAttribute("WINDOW_ID");
		int childId 		= (Integer) interface_.getAttribute("CHILD_ID");
		boolean walkable 	= (Boolean) interface_.getAttribute("WALKABLE");
		boolean gameframe 	= (Boolean) interface_.getAttribute("GAMEFRAME");
		boolean chatbox 	= (Integer) windowId == 752 ? true : false;
		
		//Gameframe interfaces
		if(gameframe) {
			if(!gameframe_interfaces.contains(interface_)) {
				gameframe_interfaces.add(interface_);
				// !-- IMPORTANT --!
				// Add your packet to open the interface right here!
			}
			return;
		}
		//send interface
		// !-- IMPORTANT --!
		// Add your packet to open the interface right here!
		
		//add the interface
		if(chatbox) {
			current_chat_interface = interface_;
			return;
		}
		current_onscreen_interface = interface_;
		return;
	}
	
	public void closeAllInterfaces(boolean triggerIsWalking) {
		closeOnScreenInterface(triggerIsWalking);
		closeChatInterface(triggerIsWalking);
	}
	
	public void closeOnScreenInterface(boolean triggerIsWalking) {
		if(current_onscreen_interface == null) {
			return;
		}
		if(current_onscreen_interface.getInterfaceId() == -1) {
			return;
		}
		int windowId 	= (Integer) current_onscreen_interface.getAttribute("WINDOW_ID");
		int childId 	= (Integer) current_onscreen_interface.getAttribute("CHILD_ID");
		if(triggerIsWalking && (Boolean) current_onscreen_interface.getAttribute("WALKABLE") == true) {
			return;
		}
		current_onscreen_interface = null;
		// !-- IMPORTANT --!
		// Add your packet to close the interface right here!
	}
	
	public void closeChatInterface(boolean triggerIsWalking) {
		if(current_chat_interface == null) {
			return;
		}
		if(current_chat_interface.getInterfaceId() == -1) {
			return;
		}
		int windowId 	= (Integer) current_chat_interface.getAttribute("WINDOW_ID");
		int childId 	= (Integer) current_chat_interface.getAttribute("CHILD_ID");
		if(triggerIsWalking && (Boolean) current_chat_interface.getAttribute("WALKABLE") == true) {
			return;
		}
		current_chat_interface = null;
		// !-- IMPORTANT --!
		// Add your packet to close the interface right here!
	}
	
	public RSAbstractInterface getCurrentOnScreenInterface() {
		if(current_onscreen_interface == null)
			return new DefaultRSInterface(-1);
		return current_onscreen_interface;
	}
	
	public RSAbstractInterface getCurrentChatBoxInterface() {
		if(current_chat_interface == null)
			return new DefaultRSInterface(-1);
		return current_chat_interface;
	}
	
}
