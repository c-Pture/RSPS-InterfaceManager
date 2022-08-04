# ![STAR](https://cpture.xyz/assets/github/misc/repos/img/rs_logo.png) InterfaceManager
![Java-build](https://img.shields.io/badge/build--java-1.1.0--beta.1-blue?style=for-the-badge)  ![CSharp-build](https://img.shields.io/badge/build--CSharp-1.0.0--beta.1-blue?style=for-the-badge)

## ![STAR](https://cpture.xyz/assets/github/misc/repos/img/star.png) About the InterfaceManager

The InterfaceManager is a simple implementation to help you manage interface handling in RuneScape Private Servers.

The main goal is to make it easier to manage and track the interfaces of the users but also to prevent cheating using a modified client.
If the client-side openes an interface and the user wants to use it (Request button presses etc.), the server can then simply double check the information
by checking with the interface manager if the interface is indeed open or not.

<details><summary>Click here for progress</summary>
<p>
  
#### Gameframe
  - [ ] Implement the usage of the gameframe
  - [ ] Updating the gameframe interfaces based on display mode
  
#### General Interfaces
  - [X] Implement the usage of screen interfaces + testing
  - [ ] Implement the usage of chatbox interfaces + testing
  - [ ] Implement the usage of tab interfaces + testing
  
#### Attributes
  - [X] Add attribute system for dynamic interface attributes :tada:
  - [X] Add basic attributes + handling and checking
  - [ ] Add advanced attributes + handling and checking
   

</p>
</details>


## Getting started

### Adjustments to the code
![STAR](https://cpture.xyz/assets/github/misc/repos/img/important.png) **Important:**
Before you start sending any interfaces make sure to initialize everything properly. 

- Adjust the code to your needs and fill in the required methods from your source such as open and close interface packets
  The positions are tagged within the source code and you can find them by the following tag
      >**// !-- IMPORTANT --!** 
      


## Example usage

- **Sending an Interface**
 
  ```java
  player.getInterfaceManager().openInterface(new DefaultRSInterface(id)
                              .setAttribute("WALKABLE", false)
                              .setAttribute("WINDOW_ID", 548)
                              .setAttribute("CHILD_ID", 8)
                              .setAttribute("GAMEFRAME", false)
                              .finalizeOperation());
  
   
- **Getting the current Interface Id**
 
  ```java
	player.getActionSender().sendMessage("The current onscreen interface id is: " 
                                          + player.getInterfaceManager().getCurrentOnScreenInterface().getInterfaceId());
                                          
	player.getActionSender().sendMessage("The current chatbox interface id is: " 
                                          + player.getInterfaceManager().getCurrentChatBoxInterface().getInterfaceId());
 

## Attributes

| Attribute | Type    | Essential | Description | Minimum version required |
| :---:     | :---:   | :---:     | ----------- | :---: |
| WINDOW_ID | Integer | **X**     | Set the window Id of the interface | 1.0.0-beta.1 |
| CHILD_ID  | Integer | **X**     | Set the child Id of the interface | 1.0.0-beta.1 |
| WALKABLE  | Boolean | **X**     | Set *true* or *false* to specify if this interface will be closed when walking or not | 1.0.0-beta.1 |
| GAMEFRAME | Boolean | **X**     | Set *true* or *false* to specify if the interface is part of the gameframe | 1.0.0-beta.1 |
| LOCKED    | Boolean |           | Set *true* or *false* to specify if the interface can be overwritten by the next interface / If it needs to be closed properly 					before opening a new interface | 1.2.0
