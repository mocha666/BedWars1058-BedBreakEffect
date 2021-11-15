package com.josemarcellio.bedbreakeffect.effect;

import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent;

import com.josemarcellio.bedbreakeffect.BedBreakEffect;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class SquidMissile extends PlayEffect {

	public SquidMissile() {
		super ( "SquidMissile");
	}

	@Override
	public void bedbreakeffect(PlayerBedBreakEvent e) {
		final Player player = e.getPlayer ();
		Location location = e.getVictimTeam ().getBed ();
		ArmorStand armor = (ArmorStand) location.getWorld ().spawnEntity ( location.add ( 0, 0, 0 ), EntityType.ARMOR_STAND );
		armor.setVisible ( false );
		armor.setGravity ( false );
		Entity squid = location.getWorld ().spawnEntity ( location, EntityType.SQUID );
		armor.setPassenger ( squid );
		new BukkitRunnable () {
			int i = 0;

			@Override
			public void run() {
				i++;
				Entity passenger = armor.getPassenger ();
				armor.eject ();
				armor.teleport ( armor.getLocation ().add ( 0, 0.5, 0 ) );
				armor.setPassenger ( passenger );
				player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1f, 1f);
				armor.getWorld().spigot().playEffect(armor.getLocation(), Effect.FLAME, 0, 0, 0.0f, 0.0f, 0.0f, 0.0f, 1, 128);
				if (i == 13) {
					Firework fw = armor.getWorld ().spawn ( armor.getLocation (), Firework.class );
					FireworkMeta fm = fw.getFireworkMeta ();
					fm.addEffect ( FireworkEffect.builder ().flicker ( true ).trail ( false ).with ( FireworkEffect.Type.BALL ).withColor ( Color.BLACK )
							.withFade ( Color.BLACK ).build () );
					fw.setFireworkMeta ( fm );
				}
				if (i == 25) {
					armor.remove ();
					squid.remove ();
					i = 0;
					cancel ();
				}
			}
		}.runTaskTimer ( BedBreakEffect.getPlugins (), 4, 1 );
	}
}
