package com.github.thelonedevil.TLDTrains.entity;

import java.util.ArrayList;
import java.util.List;

import org.spout.api.entity.Player;
import org.spout.api.event.entity.EntityInteractEvent;
import org.spout.api.event.player.PlayerInteractEntityEvent;
import org.spout.api.inventory.ItemStack;
import org.spout.api.inventory.Slot;
import org.spout.api.util.Parameter;

import org.spout.vanilla.VanillaPlugin;
import org.spout.vanilla.component.entity.misc.DeathDrops;
import org.spout.vanilla.event.entity.EntityMetaChangeEvent;
import org.spout.vanilla.material.Fuel;
import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.protocol.entity.object.ObjectType;
import org.spout.vanilla.protocol.entity.object.vehicle.MinecartObjectEntityProtocol;
import org.spout.vanilla.util.PlayerUtil;

public class PoweredMinecart extends MinecartBase {
	private float fuel = 0f;
	private boolean fueled = false;

	@Override
	public void onAttached() {
		super.onAttached();
		getOwner().getNetwork().setEntityProtocol(VanillaPlugin.VANILLA_PROTOCOL_ID, new MinecartObjectEntityProtocol(ObjectType.MINECART));
		if (getAttachedCount() == 1) {
			getOwner().add(DeathDrops.class).addDrop(new ItemStack(VanillaMaterials.FURNACE, 1));
		}
	}

	public void setFueled(boolean isFueled) {
		if (isFueled) {
			fuel = 180f;
		} else {
			fuel = 0f;
		}
		updateMetadata();
	}

	public boolean isFueled() {
		return fueled;
	}

	@Override
	public boolean canTick() {
		return fuel > 0f;
	}

	@Override
	public void onTick(float dt) {
		fuel -= dt;
		if (fuel <= 0f) {
			updateMetadata();
		}
	}

	@Override
	public void onInteract(final EntityInteractEvent event) {
		if (event instanceof PlayerInteractEntityEvent) {
			final PlayerInteractEntityEvent pie = (PlayerInteractEntityEvent) event;
			final Player player = (Player) pie.getEntity();
			switch (pie.getAction()) {
			case LEFT_CLICK:
				Slot slot = PlayerUtil.getHeldSlot(player);
				if (slot.get() != null) {
					ItemStack stack = slot.get();
					if (stack.getMaterial() instanceof Fuel) {
						setFueled(true);
						slot.addAmount(-1);
					}
				}
			}
		}
		super.onInteract(event);
	}

	private void updateMetadata() {
		List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();
		parameters.add(new Parameter<Byte>(Parameter.TYPE_BYTE, 16, (byte) (this.fuel > 0f ? 1 : 0))); // Powered
																										// flag
		getOwner().getNetwork().callProtocolEvent(new EntityMetaChangeEvent(getOwner(), parameters));
	}

	@Override
	public int getMinecraftBlockID() {
		return VanillaMaterials.FURNACE.getMinecraftId();
	}
}
