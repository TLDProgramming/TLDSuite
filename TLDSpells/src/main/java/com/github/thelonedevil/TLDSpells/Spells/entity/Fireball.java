package com.github.thelonedevil.TLDSpells.Spells.entity;

import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;

import org.spout.vanilla.VanillaPlugin;
import org.spout.vanilla.component.entity.misc.Health;
import org.spout.vanilla.component.entity.substance.Substance;
import org.spout.vanilla.component.entity.substance.projectile.Projectile;
import org.spout.vanilla.protocol.entity.object.ObjectEntityProtocol;
import org.spout.vanilla.protocol.entity.object.ObjectType;

public class Fireball extends Substance implements Projectile {
	private Entity shooter;

	@Override
	public void onAttached() {
		getOwner().getNetwork().setEntityProtocol(VanillaPlugin.VANILLA_PROTOCOL_ID, new ObjectEntityProtocol(ObjectType.FIREWORKS_ROCKET));
		super.onAttached();
	}

	public Entity getShooter() {
		return shooter;
	}

	public void setShooter(Entity shooter) {
		this.shooter = shooter;
	}

	@Override
	public void onCollided(Point point, Entity entity) {
		Health health = entity.get(Health.class);
		if (health != null) {
			health.damage(0);
		}
		getOwner().remove();
	}

	@Override
	public void onCollided(Point point, Block block) {
		getOwner().remove();
	}
}