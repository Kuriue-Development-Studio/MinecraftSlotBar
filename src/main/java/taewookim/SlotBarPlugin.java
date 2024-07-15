package taewookim;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import taewookim.playerdata.PlayerDataType;
import taewookim.playerdata.slotbardata.SlotBarData;
import taewookim.playerdata.slotbardata.SlotBarInventory;
import util.ItemList;

public class SlotBarPlugin extends JavaPlugin {

    public static void updateSlotBar(Player p) {
        DataManagerPlugin.updateSlotBar(p);
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
    }

    public void onDisable() {

    }

}
