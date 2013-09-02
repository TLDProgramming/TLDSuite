package com.github.thelonedevil.TLDSpells.Spells.entity;

import org.spout.api.entity.Entity;
import org.spout.vanilla.component.entity.substance.Substance;
import org.spout.vanilla.component.entity.substance.projectile.Projectile;
import org.spout.vanilla.protocol.entity.VanillaEntityProtocol;
import org.spout.vanilla.protocol.entity.object.ObjectEntityProtocol;
import org.spout.vanilla.protocol.entity.object.ObjectType;

public class HealthBoltEntity extends Substance implements Projectile {
	private Entity shooter;

	@Override
	public void onAttached() {
		VanillaEntityProtocol pr = new ObjectEntityProtocol(ObjectType.POTION); // replace
																				// object
																				// type
																				// with
																				// something
																				// else
		setEntityProtocol(pr);
		super.onAttached();
	}

	@Override
	public Entity getShooter() {
		return shooter;
	}

	@Override
	public void setShooter(Entity shooter) {
		this.shooter = shooter;

	}

}
