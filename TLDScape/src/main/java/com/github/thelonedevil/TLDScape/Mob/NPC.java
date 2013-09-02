package com.github.thelonedevil.TLDScape.Mob;

import org.spout.api.entity.Entity;
import org.spout.vanilla.component.entity.living.Human;
import org.spout.vanilla.component.entity.misc.Health;

public class NPC extends Human {

	@Override
	public void onAttached() {
		super.onAttached();
		Entity holder = getOwner();
		if (getAttachedCount() == 1) {
			holder.get(Health.class).setSpawnHealth(-1);
		}
	}

}
