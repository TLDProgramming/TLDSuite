package com.github.thelonedevil.TLDNPC.mobs;

import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.event.entity.EntityInteractEvent;
import org.spout.api.event.player.PlayerInteractEntityEvent;
import org.spout.vanilla.component.entity.living.Human;
import org.spout.vanilla.component.entity.misc.Health;

import com.github.thelonedevil.TLDNPC.TLDNPCPlugin;
import com.github.thelonedevil.TLDNPC.gui.NPCWindow;

public class NPC extends Human {
	private TLDNPCPlugin plugin;

	public NPC(TLDNPCPlugin instance) {
		this.plugin = instance;
	}

	private String message = "";
	private String skin;

	String getMessage() {
		return message;
	}

	void setMessage(String message) {
		this.message = message;
	}

	String getSkin() {
		return skin;
	}

	void setSkin(String skin) {
		this.skin = skin;
	}

	@Override
	public void onAttached() {
		super.onAttached();
		Entity holder = getOwner();
		if (getAttachedCount() == 1) {
			holder.get(Health.class).setSpawnHealth(-1);
		}
	}

	@Override
	public void onInteract(final EntityInteractEvent<?> event) {
		if (event instanceof PlayerInteractEntityEvent) {
			final PlayerInteractEntityEvent pie = (PlayerInteractEntityEvent) event;
			final Player player = (Player) pie.getEntity();
			switch (pie.getAction()) {
			case RIGHT_CLICK:
				NPCWindow window = new NPCWindow(this, message, player);
			case LEFT_CLICK:
				break;
			default:
				break;

			}
		}
	}

}
