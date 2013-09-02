package com.github.thelonedevil.TLDSpells.event;

import org.spout.api.entity.Player;
import org.spout.api.event.Cancellable;
import org.spout.api.event.HandlerList;
import org.spout.api.event.player.AbstractPlayerEvent;
import org.spout.vanilla.component.entity.misc.Level;

import com.github.thelonedevil.TLDSpells.Spells.Bolt;

public class PlayerCastSpellEvent extends AbstractPlayerEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();

	Bolt spell;
	Player p;

	public PlayerCastSpellEvent(Player p, Bolt spell) {
		super(p);
		this.spell = spell;
		this.p = p;

	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}

	public Bolt getSpell() {
		return spell;
	}

	public void setSpell(Bolt spell) {
		this.spell = spell;
	}

	public Level getLevel() {
		return this.p.get(Level.class);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
