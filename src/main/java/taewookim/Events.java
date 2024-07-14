package taewookim;

import io.papermc.paper.event.player.PlayerPickItemEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import taewookim.playerdata.PlayerDataType;
import taewookim.playerdata.gamedata.GameData;
import taewookim.playerdata.inventorydata.InventoryData;
import taewookim.util.SkillBuilder;

public class Events implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e) {
        SlotBarPlugin.updateSlotBar(e.getPlayer());
    }

    @EventHandler
    public void hold(PlayerItemHeldEvent e) {
        ItemStack i = e.getPlayer().getInventory().getItem(e.getNewSlot());
        if(!i.hasItemMeta()) {
            return;
        }
        PersistentDataContainer container = i.getItemMeta().getPersistentDataContainer();
        if(container.has(CustomSkillPlugin.itemskill, PersistentDataType.INTEGER)) {
            new SkillBuilder(i).build(e.getPlayer());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void eatItem(PlayerAttemptPickupItemEvent e) {
        PlayerData manager = PlayerDataManager.getPlayerData(e.getPlayer());
        InventoryData data = (InventoryData) manager.getData(PlayerDataType.Inventory);
        if(!((GameData)manager.getData(PlayerDataType.Game)).isGameing()) {
            return;
        }
        e.setCancelled(true);
        if(data.getInventory().addItem(e.getItem().getItemStack())) {
            e.getItem().remove();
        }
    }

}
