package com.github.thelonedevil.TLDNPC.mobs;

import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;
import org.spout.vanilla.component.entity.living.Living;

public class Mob {

	public boolean spawnNpc(Player player, String skin, String name, String message){
		Point point = player.getPhysics().getPosition();
		Entity npc = point.getWorld().createEntity(point, NPC.class);
		Living living = npc.get(Living.class);
		((NPC) living).setName(name);
		((NPC) npc).setSkin(skin);
		((NPC)npc).setMessage(message);
		player.getWorld().spawnEntity(npc);
		return npc.isSpawned();

	}
}
