package com.github.thelonedevil.TLDSpells.Spells;

import org.spout.api.component.entity.PhysicsComponent;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.vanilla.component.entity.substance.Substance;
import org.spout.vanilla.component.entity.substance.projectile.Projectile;

public class Bolt {

	@SuppressWarnings("unchecked")
	public static Entity cast(Player p, Class<? extends Substance> projectile) {
		World world = p.getWorld();
		Substance item = world.createEntity(p.getPhysics().getPosition().add(0, 1.6f, 0), projectile).add(projectile);
		PhysicsComponent scene = item.getOwner().getPhysics();
		scene.setMass(1.0f); // TODO: Correct this
		// scene.impulse(VectorMath.getDirection(p.getPhysics().getRotation()).multiply(250)).setMovementVelocity(Vector3.FORWARD).force(Vector3.FORWARD);//
		// TODO: Need real parameters
		if (item instanceof Projectile) {
			((Projectile) item).setShooter(p);
		}
		world.spawnEntity(item.getOwner());
		return item.getOwner();

	}

}
