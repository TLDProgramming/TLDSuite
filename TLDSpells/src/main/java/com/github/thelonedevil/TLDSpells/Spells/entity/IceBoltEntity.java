package com.github.thelonedevil.TLDSpells.Spells.entity;

import org.spout.api.entity.Entity;
import org.spout.vanilla.VanillaPlugin;
import org.spout.vanilla.component.entity.substance.Substance;
import org.spout.vanilla.component.entity.substance.projectile.Projectile;
import org.spout.vanilla.protocol.entity.object.ObjectEntityProtocol;
import org.spout.vanilla.protocol.entity.object.ObjectType;

public class IceBoltEntity extends Substance implements Projectile {
	private Entity shooter;

	@Override
	public void onAttached() {
		getOwner().getNetwork().setEntityProtocol(VanillaPlugin.VANILLA_PROTOCOL_ID, new ObjectEntityProtocol(ObjectType.SNOWBALL));
		super.onAttached();
	}

	public Entity getShooter() {
		return shooter;
	}

	public void setShooter(Entity shooter) {
		this.shooter = shooter;
	}

}