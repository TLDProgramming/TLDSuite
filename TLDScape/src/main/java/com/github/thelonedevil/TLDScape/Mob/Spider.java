/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.thelonedevil.TLDScape.Mob;

import java.util.Random;
import org.spout.api.inventory.ItemStack;
import org.spout.vanilla.component.entity.living.Hostile;
import org.spout.vanilla.component.entity.living.Living;
import org.spout.vanilla.component.entity.misc.Damage;
import org.spout.vanilla.component.entity.misc.DeathDrops;
import org.spout.vanilla.component.entity.misc.Health;
import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.protocol.entity.creature.CreatureProtocol;
import org.spout.vanilla.protocol.entity.creature.CreatureType;

/**
 *
 * @author justin
 */
public class Spider extends Living implements Hostile {

    @Override
    public void onAttached() {
        super.onAttached();
        setEntityProtocol(new CreatureProtocol(CreatureType.CAVE_SPIDER));
        DeathDrops dropComponent = getOwner().add(DeathDrops.class);
        Random random = getRandom();
        dropComponent.addDrop(new ItemStack(VanillaMaterials.STRING, random.nextInt(2)));
        dropComponent.addDrop(new ItemStack(VanillaMaterials.SPIDER_EYE, random.nextInt(1)));
        dropComponent.addXpDrop((short) 3);
        if (getAttachedCount() == 1) {
            getOwner().add(Health.class).setSpawnHealth(12);
        }
        Damage damage = getOwner().add(Damage.class);
    }
}