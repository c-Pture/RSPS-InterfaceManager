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
using System.Collections.Concurrent;

namespace InterfaceManager.main.pegasus.im
{

	/// <summary>
	/// Represents a game interface
	/// </summary>
	public abstract class RSInterface
    {

        #region Fields
        /// <summary>
        /// The interface Id of the current RSInterface instance
        /// </summary>
        private int interfaceId;

		/// <summary>
		/// Attributes for the current interface
		/// </summary>
#if NET20
        private Dictionary<string, object> attributes = new Dictionary<String, object>();
#else
		private ConcurrentDictionary<string, object> attributes = new ConcurrentDictionary<String, object>();
#endif
		#endregion Fields

		#region Constructor
		/// <summary>
		/// Constructs a new interface.
		/// </summary>
		/// <param name="action">The Id of the interface.</param>
		public RSInterface(int interfaceId)
        {
            this.interfaceId = interfaceId;
        }
		#endregion Constructor

		#region Methods
		/// <summary>
		/// Set an attribute to the desired key and value
		/// </summary>
		/// <param name="key">The Attribute key</param>
		/// <param name="value">The Attribute value</param>
		/// <returns>RSInterface</returns>
		public RSInterface setAttribute(String key, Object value)
		{
			if (attributes.ContainsKey(key))
			{
				attributes[key] = value;
				return this;
			}
			attributes.TryAdd(key, value);
			return this;
		}

		/// <summary>
		/// Set the child Id of the current interface instance
		/// Actually sets the attribute "CHILD_ID" to the given value
		/// </summary>
		/// <param name="childId">Child Id</param>
		/// <returns>RSInterface</returns>
		public RSInterface setChildId(int childId)
		{
			this.setAttribute("CHILD_ID", childId);
			return this;
		}

		/// <summary>
		/// Set the window Id of the current interface instance
		/// Actually sets the attribute "WINDOW_ID" to the given value
		/// </summary>
		/// <param name="windowId">Window Id</param>
		/// <returns>RSInterface</returns>
		public RSInterface setWindowId(int windowId)
		{
			this.setAttribute("WINDOW_ID", windowId);
			return this;
		}

		/// <summary>
		/// Get the attribute for a specific key
		/// </summary>
		/// <param name="key">Attribute key value for the value</param>
		/// <returns>RSInterface</returns>
		public Object getAttribute(String key)
		{
			if (attributes[key] == null)
				return null;
			return attributes[key];
		}

		/// <summary>
		/// Get the child Id for the interface
		/// Actually stored in Attributes "CHILD_ID"
		/// </summary>
		/// <returns>Child Id</returns>
		public int getChildId()
		{
			return (int)getAttribute("CHILD_ID");
		}

		/// <summary>
		/// Get the window Id for the interface
		/// Actually stored in Attributes "WINDOW_ID"
		/// </summary>
		/// <returns>Window Id</returns>
		public int getWindowId()
		{
			return (int)getAttribute("WINDOW_ID");
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
        #endregion Methods

    }
}
