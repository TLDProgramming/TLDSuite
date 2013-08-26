/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.thelonedevil.TLDScape.Mob;

import java.util.Random;
import org.spout.api.entity.Player;
import org.spout.api.event.entity.EntityInteractEvent;
import org.spout.api.event.player.PlayerInteractEntityEvent;
import org.spout.vanilla.component.entity.living.Living;
import org.spout.vanilla.component.entity.living.Passive;
import org.spout.vanilla.component.entity.misc.Health;
import org.spout.vanilla.protocol.entity.creature.CreatureProtocol;
import org.spout.vanilla.protocol.entity.creature.CreatureType;

/**
 *
 * @author justin
 */
public class Guide extends Living implements Passive {

    @Override
    public void onAttached() {
        super.onAttached();
        setEntityProtocol(new CreatureProtocol(CreatureType.VILLAGER));
        Random random = getRandom();
        if (getAttachedCount() == 1) {
            getOwner().add(Health.class).setSpawnHealth(-1.0f);
        }
    }

    @Override
    public void onInteract(final EntityInteractEvent<?> event) {
        if (event instanceof PlayerInteractEntityEvent) {
            PlayerInteractEntityEvent pie = (PlayerInteractEntityEvent) event;
            Player player = (Player) pie.getEntity();
            switch (pie.getAction()) {
                case RIGHT_CLICK:
                    player.sendMessage("hello " + player.getDisplayName());
            }
        }
    }
}
