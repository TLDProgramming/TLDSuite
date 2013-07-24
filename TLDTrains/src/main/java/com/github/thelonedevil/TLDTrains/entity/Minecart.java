package com.github.thelonedevil.TLDTrains.entity;

import org.spout.vanilla.VanillaPlugin;
import org.spout.vanilla.protocol.entity.object.ObjectType;
import org.spout.vanilla.protocol.entity.object.vehicle.MinecartObjectEntityProtocol;

public class Minecart extends MinecartBase {
	@Override
	public void onAttached() {
		super.onAttached();
		getOwner().getNetwork().setEntityProtocol(VanillaPlugin.VANILLA_PROTOCOL_ID, new MinecartObjectEntityProtocol(ObjectType.MINECART));
	}
}