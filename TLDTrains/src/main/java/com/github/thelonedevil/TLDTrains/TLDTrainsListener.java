/*
 * This file is part of the Spout plugin TLDTrains. It also has a hard
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDTrains;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.entity.EntityInteractEntityEvent;
import org.spout.api.event.player.Action;
import org.spout.api.event.player.PlayerInteractBlockEvent;
import org.spout.api.event.player.PlayerInteractEntityEvent;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;
import org.spout.api.inventory.ItemStack;
import org.spout.api.inventory.Slot;
import org.spout.api.material.Material;
import org.spout.vanilla.component.entity.substance.vehicle.minecart.MinecartBase;
import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.util.PlayerUtil;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDCommonlib.Train;
import com.github.thelonedevil.TLDCommonlib.TrainsMap;
import com.github.thelonedevil.TLDTrains.entity.Minecart;
import com.github.thelonedevil.TLDTrains.entity.PoweredMinecart;

/**
 * Provides an example of an event listener class.
 */
public class TLDTrainsListener implements Listener {
	private TLDTrainsPlugin plugin;

	public TLDTrainsListener(TLDTrainsPlugin instance) {
		this.plugin = instance;
	}

	private Lib lib = new Lib();
	private HashMap<UUID, String> map = new HashMap<UUID, String>();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent event) {
		if (event.getInteracted() instanceof MinecartBase) {
			Player p = event.getEntity();
			String name = p.getName();
			UUID uuid = event.getInteracted().getUID();
			map.put(uuid, name);
		}
	}

	@EventHandler
	public void onEntityInteract(EntityInteractEntityEvent event) {
		Entity entity = event.getEntity();
		Entity target = event.getInteracted();
		if ((entity instanceof MinecartBase) && (target instanceof MinecartBase)) {
			UUID key = entity.getUID();
			UUID key1 = target.getUID();
			String name = null;
			if (map.containsKey(key)) {
				name = map.get(key);
			} else if (map.containsKey(key1)) {
				name = map.get(key1);
			}
			if (name != null) {
				TrainsMap tmap = lib.trainowners.get(name);
				for (Integer num : tmap.keySet()) {
					Train train = tmap.get(num);
					List<UUID> carts = train.getCarts();
					if (carts.contains(key) && carts.contains(key1)) {

					} else if (carts.contains(key)) {
						train.addCart(key1);
						tmap.replace(num, train);
					} else if (carts.contains(key1)) {
						train.addCart(key);
						tmap.replace(num, train);
					} else if (!carts.contains(key) && !carts.contains(key1)) {
						train.createTrain(key, key1);
					}

				}
				lib.trainowners.put(name, tmap);
			}
		}
	}

	@EventHandler
	public void onCartPlace(PlayerInteractBlockEvent event) {
		Player p = event.getEntity();
		Action click = event.getAction();
		World w = p.getWorld();
		switch (click) {
		case RIGHT_CLICK:
			Point point = event.getPoint();
			Block block = event.getInteracted();
			if (block.isMaterial(VanillaMaterials.RAIL) || block.isMaterial(VanillaMaterials.RAIL_ACTIVATOR) || block.isMaterial(VanillaMaterials.RAIL_DETECTOR)
					|| block.isMaterial(VanillaMaterials.RAIL_POWERED)) {
				int y = point.getBlockY() + 1;
				int x = point.getBlockX();
				int z = point.getBlockZ();
				point.add(x, y, z);
				Material material = PlayerUtil.getHeldSlot(p).get().getMaterial();
				if (material.isMaterial(VanillaMaterials.MINECART)) {
					w.createEntity(point, Minecart.class);

				} else if (material.isMaterial(VanillaMaterials.MINECART_CHEST)) {

				} else if (material.isMaterial(VanillaMaterials.MINECART_FURNACE)) {
					w.createEntity(point, PoweredMinecart.class);

				} else if (material.isMaterial(VanillaMaterials.MINECART_HOPPER)) {
					
				} else if (material.isMaterial(VanillaMaterials.MINECART_TNT)) {
					
				}

			}

		}
	}
}
