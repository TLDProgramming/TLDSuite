package com.github.thelonedevil.TLDScape.skills;

import java.sql.SQLException;
import java.util.HashMap;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.Action;
import org.spout.api.event.player.PlayerInteractBlockEvent;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.inventory.ItemStack;
import org.spout.api.inventory.Slot;
import org.spout.api.material.Material;
import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.material.item.tool.Tool;
import org.spout.vanilla.util.PlayerUtil;

import com.github.thelonedevil.TLDCommonlib.DataBase;
import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDScape.TLDScapePlugin;

public class WoodCuttingListener implements Listener {

	private DataBase database = new DataBase();
	private TLDScapePlugin plugin;

	public WoodCuttingListener(TLDScapePlugin instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onChop(PlayerInteractBlockEvent event) {
		Action action = event.getAction();
		Player p = event.getEntity();
		String name = p.getName();
		HashMap<String, Object> skills = null;
		try {
			skills = database.getRow(Lib.statement, "ScapeSkills", name, "Player");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (skills != null) {
			Block block = event.getInteracted();
			Material material = block.getMaterial();
			if (material.isMaterial(VanillaMaterials.LOG)) {
				Slot slot = PlayerUtil.getHeldSlot(p);
				ItemStack held = slot.get();
				//TODO sort per tool
				int xp = (Integer) skills.get("WoodCutting");
				if (xp >= Levels.L1) {
					// wooden
				} else if (xp >= Levels.L2) {
				} else if (xp >= Levels.L3) {
				} else if (xp >= Levels.L4) {
				} else if (xp >= Levels.L5) {
				} else if (xp >= Levels.L6) {
					// stone
				} else if (xp >= Levels.L7) {
				} else if (xp >= Levels.L8) {
				} else if (xp >= Levels.L9) {
				} else if (xp >= Levels.L10) {
				} else if (xp >= Levels.L11) {
				} else if (xp >= Levels.L12) {
					// gold
				} else if (xp >= Levels.L13) {
				} else if (xp >= Levels.L14) {
				} else if (xp >= Levels.L15) {
				} else if (xp >= Levels.L16) {
				} else if (xp >= Levels.L17) {
				} else if (xp >= Levels.L18) {
					// iron
				} else if (xp >= Levels.L19) {
				} else if (xp >= Levels.L20) {
				} else if (xp >= Levels.L21) {
				} else if (xp >= Levels.L22) {
				} else if (xp >= Levels.L23) {
				} else if (xp >= Levels.L24) {
					// diamond
				} else if (xp >= Levels.L25) {
				} else if (xp >= Levels.L26) {
				} else if (xp >= Levels.L27) {
				} else if (xp >= Levels.L28) {
				} else if (xp >= Levels.L29) {
				} else if (xp >= Levels.L30) {
					// faster cutting
				}
			}/*
			 * else if (xp >= Levels.L31) {
			 * p.get(Health.class).setMaxHealth(31.0f); } else if (xp >=
			 * Levels.L32) { p.get(Health.class).setMaxHealth(32.0f); } else if
			 * (xp >= Levels.L33) { p.get(Health.class).setMaxHealth(33.0f); }
			 * else if (xp >= Levels.L34) {
			 * p.get(Health.class).setMaxHealth(34.0f); } else if (xp >=
			 * Levels.L35) { p.get(Health.class).setMaxHealth(35.0f); } else if
			 * (xp >= Levels.L36) { p.get(Health.class).setMaxHealth(36.0f); }
			 * else if (xp >= Levels.L37) {
			 * p.get(Health.class).setMaxHealth(37.0f); } else if (xp >=
			 * Levels.L38) { p.get(Health.class).setMaxHealth(38.0f); } else if
			 * (xp >= Levels.L39) { p.get(Health.class).setMaxHealth(39.0f); }
			 * else if (xp >= Levels.L40) {
			 * p.get(Health.class).setMaxHealth(40.0f); } else if (xp >=
			 * Levels.L41) { p.get(Health.class).setMaxHealth(41.0f); } else if
			 * (xp >= Levels.L42) { p.get(Health.class).setMaxHealth(42.0f); }
			 * else if (xp >= Levels.L43) {
			 * p.get(Health.class).setMaxHealth(43.0f); } else if (xp >=
			 * Levels.L44) { p.get(Health.class).setMaxHealth(44.0f); } else if
			 * (xp >= Levels.L45) { p.get(Health.class).setMaxHealth(45.0f); }
			 * else if (xp >= Levels.L46) {
			 * p.get(Health.class).setMaxHealth(46.0f); } else if (xp >=
			 * Levels.L47) { p.get(Health.class).setMaxHealth(47.0f); } else if
			 * (xp >= Levels.L48) { p.get(Health.class).setMaxHealth(48.0f); }
			 * else if (xp >= Levels.L49) {
			 * p.get(Health.class).setMaxHealth(49.0f); } else if (xp >=
			 * Levels.L50) { p.get(Health.class).setMaxHealth(50.0f); } else if
			 * (xp >= Levels.L51) {
			 * 
			 * } else if (xp >= Levels.L52) {
			 * 
			 * } else if (xp >= Levels.L53) {
			 * 
			 * } else if (xp >= Levels.L54) {
			 * p.get(Health.class).setMaxHealth(54.0f); } else if (xp >=
			 * Levels.L55) { p.get(Health.class).setMaxHealth(55.0f); } else if
			 * (xp >= Levels.L56) { p.get(Health.class).setMaxHealth(56.0f); }
			 * else if (xp >= Levels.L57) {
			 * p.get(Health.class).setMaxHealth(57.0f); } else if (xp >=
			 * Levels.L58) { p.get(Health.class).setMaxHealth(58.0f); } else if
			 * (xp >= Levels.L59) { p.get(Health.class).setMaxHealth(59.0f); }
			 * else if (xp >= Levels.L60) {
			 * p.get(Health.class).setMaxHealth(60.0f); } else if (xp >=
			 * Levels.L61) {
			 * 
			 * } else if (xp >= Levels.L62) {
			 * 
			 * } else if (xp >= Levels.L63) {
			 * 
			 * } else if (xp >= Levels.L64) {
			 * 
			 * } else if (xp >= Levels.L65) {
			 * 
			 * } else if (xp >= Levels.L66) {
			 * 
			 * } else if (xp >= Levels.L67) {
			 * 
			 * } else if (xp >= Levels.L68) {
			 * 
			 * } else if (xp >= Levels.L69) {
			 * 
			 * } else if (xp >= Levels.L70) {
			 * 
			 * } else if (xp >= Levels.L71) {
			 * 
			 * } else if (xp >= Levels.L72) {
			 * 
			 * } else if (xp >= Levels.L73) {
			 * 
			 * } else if (xp >= Levels.L74) {
			 * 
			 * } else if (xp >= Levels.L75) {
			 * 
			 * } else if (xp >= Levels.L76) {
			 * 
			 * } else if (xp >= Levels.L77) {
			 * 
			 * } else if (xp >= Levels.L78) {
			 * 
			 * } else if (xp >= Levels.L79) {
			 * 
			 * } else if (xp >= Levels.L80) {
			 * 
			 * } else if (xp >= Levels.L81) {
			 * 
			 * } else if (xp >= Levels.L82) {
			 * 
			 * } else if (xp >= Levels.L83) {
			 * 
			 * } else if (xp >= Levels.L84) {
			 * 
			 * } else if (xp >= Levels.L85) {
			 * 
			 * } else if (xp >= Levels.L86) {
			 * 
			 * } else if (xp >= Levels.L87) {
			 * 
			 * } else if (xp >= Levels.L88) {
			 * 
			 * } else if (xp >= Levels.L89) {
			 * 
			 * } else if (xp >= Levels.L90) {
			 * 
			 * } else if (xp >= Levels.L91) {
			 * 
			 * } else if (xp >= Levels.L92) {
			 * 
			 * } else if (xp >= Levels.L93) {
			 * 
			 * } else if (xp >= Levels.L94) {
			 * 
			 * } else if (xp >= Levels.L95) {
			 * 
			 * } else if (xp >= Levels.L96) { } else if (xp >= Levels.L97) {
			 * 
			 * } else if (xp >= Levels.L98) {
			 * 
			 * } else if (xp >= Levels.L99) {
			 * 
			 * }
			 */
		} else
			throw new IllegalStateException("Player has no skill levels");

	}
}
