package com.github.thelonedevil.TLDSpells.Spells;

import org.spout.api.component.entity.SceneComponent;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.World;
import org.spout.api.math.Vector3;
import org.spout.api.math.VectorMath;
import org.spout.vanilla.component.entity.substance.Substance;
import org.spout.vanilla.component.entity.substance.projectile.Projectile;

public class Bolt {

	@SuppressWarnings("unchecked")
	public static Entity cast(Player p, Class<? extends Substance> projectile) {
		World world = p.getWorld();
		Substance item = world.createEntity(p.getScene().getPosition().add(0, 1.6f, 0), projectile).add(projectile);
		SceneComponent scene = item.getOwner().getScene();
		//scene.setShape(mass, new SphereShape(0.1f)); // TODO: Correct this
		scene.impulse(VectorMath.getDirection(p.getScene().getRotation()).multiply(250)).setMovementVelocity(Vector3.FORWARD).force(Vector3.FORWARD);// TODO: Need real parameters
		if (item instanceof Projectile) {
			((Projectile) item).setShooter(p);
		}
		world.spawnEntity(item.getOwner());
		return item.getOwner();

	}

}
