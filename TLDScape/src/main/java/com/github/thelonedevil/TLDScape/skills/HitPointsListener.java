package com.github.thelonedevil.TLDScape.skills;

import java.sql.SQLException;
import java.util.HashMap;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.vanilla.component.entity.misc.Health;
import org.spout.vanilla.event.entity.EntityDamageEvent;
import org.spout.vanilla.event.player.PlayerRespawnEvent;

import com.github.thelonedevil.TLDCommonlib.DataBase;
import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDScape.TLDScapePlugin;

public class HitPointsListener implements Listener {

    private TLDScapePlugin plugin;

    public HitPointsListener(TLDScapePlugin instance) {
        this.plugin = instance;
    }
    private DataBase database = new DataBase();

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Object en = event.getDamager();
        Float damage = event.getDamage();
        Player p;
        if (en instanceof Player) {
            p = (Player) en;
        } else {
            return;
        }
        // add exp to hitpoints
        String name = p.getName();
        HashMap<String, Object> skills = null;
        try {
            skills = database.getRow(Lib.statement, "ScapeSkills", name, "Player");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (skills != null) {
            int xp = (Integer) skills.get("HitPoints");
            Float newxp = damage * 1.3f;
            int total = xp + newxp.intValue();
            skills.put("HitPoints", total);
            try {
                database.updateRow(Lib.statement, "ScapeSkills", name, "Player", skills);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("Player has no skill levels!");
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        setMaxHealth(p);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        setMaxHealth(p);
    }

    public void setMaxHealth(Player p) {
        String name = p.getName();
        HashMap<String, Object> skills = null;
        try {
            skills = database.getRow(Lib.statement, "ScapeSkills", name, "Player");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (skills != null && !skills.isEmpty()) {
            int xp = (Integer) skills.get("HitPoints");
            if (xp >= Levels.L1) {
                p.get(Health.class).setMaxHealth(1.0f);
            } else if (xp >= Levels.L2) {
                p.get(Health.class).setMaxHealth(2.0f);
            } else if (xp >= Levels.L3) {
                p.get(Health.class).setMaxHealth(3.0f);
            } else if (xp >= Levels.L4) {
                p.get(Health.class).setMaxHealth(4.0f);
            } else if (xp >= Levels.L5) {
                p.get(Health.class).setMaxHealth(5.0f);
            } else if (xp >= Levels.L6) {
                p.get(Health.class).setMaxHealth(6.0f);
            } else if (xp >= Levels.L7) {
                p.get(Health.class).setMaxHealth(7.0f);
            } else if (xp >= Levels.L8) {
                p.get(Health.class).setMaxHealth(8.0f);
            } else if (xp >= Levels.L9) {
                p.get(Health.class).setMaxHealth(9.0f);
            } else if (xp >= Levels.L10) {
                p.get(Health.class).setMaxHealth(10.0f);
            } else if (xp >= Levels.L11) {
                p.get(Health.class).setMaxHealth(11.0f);
            } else if (xp >= Levels.L12) {
                p.get(Health.class).setMaxHealth(12.0f);
            } else if (xp >= Levels.L13) {
                p.get(Health.class).setMaxHealth(13.0f);
            } else if (xp >= Levels.L14) {
                p.get(Health.class).setMaxHealth(14.0f);
            } else if (xp >= Levels.L15) {
                p.get(Health.class).setMaxHealth(15.0f);
            } else if (xp >= Levels.L16) {
                p.get(Health.class).setMaxHealth(16.0f);
            } else if (xp >= Levels.L17) {
                p.get(Health.class).setMaxHealth(17.0f);
            } else if (xp >= Levels.L18) {
                p.get(Health.class).setMaxHealth(18.0f);
            } else if (xp >= Levels.L19) {
                p.get(Health.class).setMaxHealth(19.0f);
            } else if (xp >= Levels.L20) {
                p.get(Health.class).setMaxHealth(20.0f);
            } else if (xp >= Levels.L21) {
                p.get(Health.class).setMaxHealth(21.0f);
            } else if (xp >= Levels.L22) {
                p.get(Health.class).setMaxHealth(22.0f);
            } else if (xp >= Levels.L23) {
                p.get(Health.class).setMaxHealth(23.0f);
            } else if (xp >= Levels.L24) {
                p.get(Health.class).setMaxHealth(24.0f);
            } else if (xp >= Levels.L25) {
                p.get(Health.class).setMaxHealth(25.0f);
            } else if (xp >= Levels.L26) {
                p.get(Health.class).setMaxHealth(26.0f);
            } else if (xp >= Levels.L27) {
                p.get(Health.class).setMaxHealth(27.0f);
            } else if (xp >= Levels.L28) {
                p.get(Health.class).setMaxHealth(28.0f);
            } else if (xp >= Levels.L29) {
                p.get(Health.class).setMaxHealth(29.0f);
            } else if (xp >= Levels.L30) {
                p.get(Health.class).setMaxHealth(30.0f);
            } /*else if (xp >= Levels.L31) {
             p.get(Health.class).setMaxHealth(31.0f);
             } else if (xp >= Levels.L32) {
             p.get(Health.class).setMaxHealth(32.0f);
             } else if (xp >= Levels.L33) {
             p.get(Health.class).setMaxHealth(33.0f);
             } else if (xp >= Levels.L34) {
             p.get(Health.class).setMaxHealth(34.0f);
             } else if (xp >= Levels.L35) {
             p.get(Health.class).setMaxHealth(35.0f);
             } else if (xp >= Levels.L36) {
             p.get(Health.class).setMaxHealth(36.0f);
             } else if (xp >= Levels.L37) {
             p.get(Health.class).setMaxHealth(37.0f);
             } else if (xp >= Levels.L38) {
             p.get(Health.class).setMaxHealth(38.0f);
             } else if (xp >= Levels.L39) {
             p.get(Health.class).setMaxHealth(39.0f);
             } else if (xp >= Levels.L40) {
             p.get(Health.class).setMaxHealth(40.0f);
             } else if (xp >= Levels.L41) {
             p.get(Health.class).setMaxHealth(41.0f);
             } else if (xp >= Levels.L42) {
             p.get(Health.class).setMaxHealth(42.0f);
             } else if (xp >= Levels.L43) {
             p.get(Health.class).setMaxHealth(43.0f);
             } else if (xp >= Levels.L44) {
             p.get(Health.class).setMaxHealth(44.0f);
             } else if (xp >= Levels.L45) {
             p.get(Health.class).setMaxHealth(45.0f);
             } else if (xp >= Levels.L46) {
             p.get(Health.class).setMaxHealth(46.0f);
             } else if (xp >= Levels.L47) {
             p.get(Health.class).setMaxHealth(47.0f);
             } else if (xp >= Levels.L48) {
             p.get(Health.class).setMaxHealth(48.0f);
             } else if (xp >= Levels.L49) {
             p.get(Health.class).setMaxHealth(49.0f);
             } else if (xp >= Levels.L50) {
             p.get(Health.class).setMaxHealth(50.0f);
             } else if (xp >= Levels.L51) {
             p.get(Health.class).setMaxHealth(51.0f);
             } else if (xp >= Levels.L52) {
             p.get(Health.class).setMaxHealth(52.0f);
             } else if (xp >= Levels.L53) {
             p.get(Health.class).setMaxHealth(53.0f);
             } else if (xp >= Levels.L54) {
             p.get(Health.class).setMaxHealth(54.0f);
             } else if (xp >= Levels.L55) {
             p.get(Health.class).setMaxHealth(55.0f);
             } else if (xp >= Levels.L56) {
             p.get(Health.class).setMaxHealth(56.0f);
             } else if (xp >= Levels.L57) {
             p.get(Health.class).setMaxHealth(57.0f);
             } else if (xp >= Levels.L58) {
             p.get(Health.class).setMaxHealth(58.0f);
             } else if (xp >= Levels.L59) {
             p.get(Health.class).setMaxHealth(59.0f);
             } else if (xp >= Levels.L60) {
             p.get(Health.class).setMaxHealth(60.0f);
             } else if (xp >= Levels.L61) {
             p.get(Health.class).setMaxHealth(61.0f);
             } else if (xp >= Levels.L62) {
             p.get(Health.class).setMaxHealth(62.0f);
             } else if (xp >= Levels.L63) {
             p.get(Health.class).setMaxHealth(63.0f);
             } else if (xp >= Levels.L64) {
             p.get(Health.class).setMaxHealth(64.0f);
             } else if (xp >= Levels.L65) {
             p.get(Health.class).setMaxHealth(65.0f);
             } else if (xp >= Levels.L66) {
             p.get(Health.class).setMaxHealth(66.0f);
             } else if (xp >= Levels.L67) {
             p.get(Health.class).setMaxHealth(67.0f);
             } else if (xp >= Levels.L68) {
             p.get(Health.class).setMaxHealth(68.0f);
             } else if (xp >= Levels.L69) {
             p.get(Health.class).setMaxHealth(69.0f);
             } else if (xp >= Levels.L70) {
             p.get(Health.class).setMaxHealth(70.0f);
             } else if (xp >= Levels.L71) {
             p.get(Health.class).setMaxHealth(71.0f);
             } else if (xp >= Levels.L72) {
             p.get(Health.class).setMaxHealth(72.0f);
             } else if (xp >= Levels.L73) {
             p.get(Health.class).setMaxHealth(73.0f);
             } else if (xp >= Levels.L74) {
             p.get(Health.class).setMaxHealth(74.0f);
             } else if (xp >= Levels.L75) {
             p.get(Health.class).setMaxHealth(75.0f);
             } else if (xp >= Levels.L76) {
             p.get(Health.class).setMaxHealth(76.0f);
             } else if (xp >= Levels.L77) {
             p.get(Health.class).setMaxHealth(77.0f);
             } else if (xp >= Levels.L78) {
             p.get(Health.class).setMaxHealth(78.0f);
             } else if (xp >= Levels.L79) {
             p.get(Health.class).setMaxHealth(79.0f);
             } else if (xp >= Levels.L80) {
             p.get(Health.class).setMaxHealth(80.0f);
             } else if (xp >= Levels.L81) {
             p.get(Health.class).setMaxHealth(81.0f);
             } else if (xp >= Levels.L82) {
             p.get(Health.class).setMaxHealth(82.0f);
             } else if (xp >= Levels.L83) {
             p.get(Health.class).setMaxHealth(83.0f);
             } else if (xp >= Levels.L84) {
             p.get(Health.class).setMaxHealth(84.0f);
             } else if (xp >= Levels.L85) {
             p.get(Health.class).setMaxHealth(85.0f);
             } else if (xp >= Levels.L86) {
             p.get(Health.class).setMaxHealth(86.0f);
             } else if (xp >= Levels.L87) {
             p.get(Health.class).setMaxHealth(87.0f);
             } else if (xp >= Levels.L88) {
             p.get(Health.class).setMaxHealth(88.0f);
             } else if (xp >= Levels.L89) {
             p.get(Health.class).setMaxHealth(89.0f);
             } else if (xp >= Levels.L90) {
             p.get(Health.class).setMaxHealth(90.0f);
             } else if (xp >= Levels.L91) {
             p.get(Health.class).setMaxHealth(91.0f);
             } else if (xp >= Levels.L92) {
             p.get(Health.class).setMaxHealth(92.0f);
             } else if (xp >= Levels.L93) {
             p.get(Health.class).setMaxHealth(93.0f);
             } else if (xp >= Levels.L94) {
             p.get(Health.class).setMaxHealth(94.0f);
             } else if (xp >= Levels.L95) {
             p.get(Health.class).setMaxHealth(95.0f);
             } else if (xp >= Levels.L96) {
             p.get(Health.class).setMaxHealth(96.0f);
             } else if (xp >= Levels.L97) {
             p.get(Health.class).setMaxHealth(97.0f);
             } else if (xp >= Levels.L98) {
             p.get(Health.class).setMaxHealth(98.0f);
             } else if (xp >= Levels.L99) {
             p.get(Health.class).setMaxHealth(99.0f);
             }*/
        } else {
            throw new IllegalStateException("Player has no skill levels");
        }
    }
}
