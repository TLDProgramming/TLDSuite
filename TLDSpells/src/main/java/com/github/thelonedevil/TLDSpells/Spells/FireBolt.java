package com.github.thelonedevil.TLDSpells.Spells;

import java.util.HashMap;
import java.util.UUID;

import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.entity.EntityInteractEntityEvent;

import org.spout.vanilla.component.entity.misc.Health;
import org.spout.vanilla.component.entity.misc.Level;

import com.github.thelonedevil.TLDSpells.TLDSpellsPlugin;
import com.github.thelonedevil.TLDSpells.event.PlayerCastSpellEvent;

public class FireBolt extends Bolt implements Listener {
	private final TLDSpellsPlugin plugin;

	public FireBolt(TLDSpellsPlugin instance) {
		this.plugin = instance;
	}

	HashMap<UUID, Player> list = new HashMap<UUID, Player>();

	public void cast(Player p) {
		//Entity s = Bolt.cast(p, Fireball.class); //TODO replace FireBall.class with real fireball class
		//list.put(s.getUID(), p);
		PlayerCastSpellEvent event = new PlayerCastSpellEvent(p, this);
		plugin.getEngine().getEventManager().callEvent(event);
	}

	@EventHandler
	public void icebolt(EntityInteractEntityEvent event) {
		UUID e = event.getEntity().getUID();
		int level = list.get(e).get(Level.class).getLevel();
		int basedamage = 4;
		int damage = level * basedamage;
		damage(event, damage);

	}

	void damage(EntityInteractEntityEvent event, int amount) {
		UUID e = event.getEntity().getUID();
		if (list.containsKey(e)) {
			Entity hit = event.getInteracted();
				hit.get(Health.class).damage(amount);
				//TODO ignite entity
			
		}
	}
}
