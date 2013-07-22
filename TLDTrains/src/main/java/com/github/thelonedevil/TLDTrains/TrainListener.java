package com.github.thelonedevil.TLDTrains;

import java.util.List;
import java.util.UUID;

import org.spout.api.entity.Entity;
import org.spout.api.event.EventHandler;
import org.spout.api.event.entity.EntityTeleportEvent;
import org.spout.api.geo.discrete.Point;
import org.spout.vanilla.component.entity.substance.vehicle.minecart.MinecartBase;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDCommonlib.Train;
import com.github.thelonedevil.TLDCommonlib.TrainsMap;

public class TrainListener {
	private TLDTrainsPlugin plugin;

	public TrainListener(TLDTrainsPlugin instance) {
		this.plugin = instance;
	}
	private Lib lib = new Lib();

	@EventHandler
	public void onMinecartMove(EntityTeleportEvent event) {
		Entity cart = event.getEntity();
		if (cart instanceof MinecartBase) {
			UUID uuid = cart.getUID();

			for (String key : lib.trainowners.keySet()) {
				TrainsMap tmap = lib.trainowners.get(key);
				for (Integer num : tmap.keySet()) {
					Train train = tmap.get(num);
					List<UUID> carts = train.getCarts();
					if (carts.contains(uuid)) {
						Point start = event.getFrom();
						Point end = event.getTo();
						double distance = start.getDistance(end);
						int size = carts.size();
						int index = 0;
						while(index<size){
							UUID cart1 = carts.get(index);
							plugin.getEngine().getEntity(cart1); //TODO finish this off
						}
					}
				}
			}

		}
	}
}
