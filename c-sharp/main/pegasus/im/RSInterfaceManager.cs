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
using System;

namespace InterfaceManager.main.pegasus.im
{
	/// <summary>
	/// Interface Manager class, commenting is not perfect but should be understandable
	/// </summary>
    public class RSInterfaceManager
    {

        #region Fields
		/// <summary>
		/// Current Interface present in game
		/// </summary>
        private RSInterface current_interface;

		/**
		 * Just a basic list of the essential attributes which should be contained within the
		 * interface. Makes it a bit easier to check them all
		 */
		private String[] essential_attributes = {
			"INTERFACE_TYPE", //THE BASIC INTERFACE TYPE (Chatbox, tab, screen)
			"WINDOW_ID",
			"CHILD_ID"
		};
		#endregion Fields

		/**
		 * Used to open any interface with the help of the usage of interface attributes
		 * @param interface_
		 */
		public void openInterface(RSInterface interface_)
		{
			if (interface_.openInterface() == 9) //Actually anything but 9 can be ignored.
				return;
			if (!checkBasicSyntax(interface_)) //Attributes missing
				return;

			String interfaceType	= (String)interface_.getAttribute("INTERFACE_TYPE");
			int windowId			= (int)interface_.getAttribute("WINDOW_ID");
			int childId				= (int)interface_.getAttribute("CHILD_ID");

			if (interfaceType.Equals("CHATBOX"))
			{

			}
			if (interfaceType.Equals("TAB"))
			{

			}
			if (interfaceType.Equals("FRAME"))
			{

			}
			current_interface = interface_;
		}

		/**
		 * Check if the basic syntax of the interface is correct
		 * Checking for essential attributes in order to properly open the interface
		 * If some essential information is missing it returns false
		 * If all essential information is contained it returns true
		 * @param interface_
		 * @return all essential attributes are contained in the interface
		 */
		private bool checkBasicSyntax(RSInterface interface_)
		{
			for (int i = 0; i < essential_attributes.Length; i++)
			{
				if (interface_.getAttribute(essential_attributes[i]) == null)
				{
					return false;
				}
			}
			return false;
		}

	}
}
