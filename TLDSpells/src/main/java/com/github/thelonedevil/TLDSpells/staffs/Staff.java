package com.github.thelonedevil.TLDSpells.staffs;

import org.spout.vanilla.material.item.VanillaItemMaterial;

public class Staff extends VanillaItemMaterial {

	public static final Staff STAFF = new Staff((short) 0x7, "Staff", 280);
	public static final Staff ICEBOLTSTAFF = new Staff("IceBolt Staff", 1, STAFF);
	public static final Staff FIREBOLTSTAFF = new Staff("FireBolt Staff", 2, STAFF);
	public static final Staff HEALINGSTAFF = new Staff("Healing Staff", 3, STAFF);
	public static final Staff BRICK = new Staff("Brick Staff", 4, STAFF);
	public static final Staff STONE_BRICK = new Staff("Stone Brick Staff", 5, STAFF);
	public static final Staff NETHER_BRICK = new Staff("Nether Brick Staff", 6, STAFF);
	public static final Staff QUARTZ = new Staff("Quartz Staff", 7, STAFF);


	private Staff(short datamask, String name, int id) {
		super(datamask, name, id, null);

	}

	private Staff(String name, int data, Staff parent) {
		super(name, parent.getMinecraftId(), data, parent, null);

	}

}
