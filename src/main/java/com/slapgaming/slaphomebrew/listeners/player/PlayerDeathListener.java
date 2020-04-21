package com.slapgaming.slaphomebrew.listeners.player;

import com.slapgaming.slaphomebrew.controller.PlayerTracker;
import com.slapgaming.slaphomebrew.listeners.AbstractListener;
import com.slapgaming.slaphomebrew.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener extends AbstractListener {

    private PlayerTracker playerTracker;

    public PlayerDeathListener(PlayerTracker playerTracker) {
        this.playerTracker = playerTracker;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeathMonitor(PlayerDeathEvent event) {
        Player player = event.getEntity();
        World world = player.getWorld();
        String playername = player.getName();

        //Check for suicides
        if (playerTracker.hasCommittedSuicide(playername)) {
            event.setDeathMessage(null);
            plugin.getServer().broadcastMessage(playername + " has committed suicide..");
        }

        String message = event.getDeathMessage();
        if (message != null) {

            //Only send death messages from pvp world to players in the pvp world.
            if (world.getName().equalsIgnoreCase("world_pvp")) {
                event.setDeathMessage(null);
                for (Player messagePlayer : Bukkit.getServer().getOnlinePlayers()) {
                    if (messagePlayer.getWorld().getName().equalsIgnoreCase("world_pvp"))
                        messagePlayer.sendMessage(message);
                }
                Util.getLogger().info(message);
            }
        }

        //Add backdeath location if not in pvp/end world or nobackdeath region.
//        if (!world.getName().equalsIgnoreCase("world_pvp") && !world.getName().equalsIgnoreCase("world_the_end") && !world.getName().equalsIgnoreCase("world_sonic") && !Util.hasFlag(plugin, player.getLocation(), Flag.NOBACKDEATH)) {
//            PlayerControl.getPlayer(player).setDeathLocation(player.getLocation()); //Set death location
//            if (Util.testPermission(player, "backdeath")) { //If has backdeath permission
//                player.sendMessage(ChatColor.GRAY + "Use the /backdeath command to return to your death point."); //Send message
//            }
//        }

        //Print death location to console.
        Location location = player.getLocation();
        Util.getLogger().info(playername + " died at (" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + " in world " + world.getName() + ").");
    }

//    @EventHandler(priority = EventPriority.HIGHEST)
//    public void onPlayerDeath(PlayerDeathEvent event) {
//        //Drop player heads in pvp world.
//        if (event.getEntity().getWorld().getName().equals("world_pvp")) {
//            Entity killer = null;
//            if (event.getEntity().getKiller() instanceof Projectile) {
//                Projectile projectile = (Projectile) event.getEntity().getKiller();
//                if (projectile.getShooter() instanceof Entity) {
//                    killer = (Entity) projectile.getShooter();
//                }
//            } else {
//                killer = event.getEntity().getKiller();
//            }
//
//            if (killer instanceof Player) {
//                Random random = new Random();
//                int randomNumber = random.nextInt(100) + 1;
//                Configuration config = plugin.getConfig();
//                if (!config.contains("headdropchance")) {
//                    config.set("headdropchance", 5);
//                    plugin.saveConfig();
//                }
//
//                if (randomNumber <= config.getInt("headdropchance")) {
//                    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
//                    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
//                    skullMeta.setOwner(event.getEntity().getName());
//                    skull.setItemMeta(skullMeta);
//                    event.getDrops().add(skull);
//                }
//            }
//        }
//    }
}
