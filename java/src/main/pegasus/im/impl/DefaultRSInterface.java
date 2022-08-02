package main.pegasus.im.impl;

import main.pegasus.im.RSAbstractInterface;

public class DefaultRSInterface extends RSAbstractInterface {

	public DefaultRSInterface(int interfaceId) {
		super(interfaceId);
	}

	@Override
	public int openInterface() {
		return 0;
	}

}
