public class GUIUtil {
		private final Inventory inventory;

        public GUIUtil(String title, int size) {
            this.inventory = Bukkit.createInventory(null, size, title);
        }

        public GUIUtil addItem(int slot, Material material, String displayName, String[] lore, 
        		Enchantment enchantment, int enchantmentNumber, 
        		List<String> itemFlagsToAdd, List<ItemFlag> itemFlagsToRemove,
        		boolean setUnbreakable) {
            ItemStack itemStack = new ItemStack(material);
            GUIUtil.setNameAndLore(itemStack, displayName, lore);
            if(enchantment != null) GUIUtil.enchantItem(itemStack, enchantment, enchantmentNumber);
            if(itemFlagsToAdd != null) for (String flag : itemFlagsToAdd) GUIUtil.addItemFlags(itemStack, ItemFlag.valueOf(flag));
            if(itemFlagsToRemove != null) for (ItemFlag flag : itemFlagsToRemove) GUIUtil.removeItemFlag(itemStack, flag);
            GUIUtil.setUnbreakable(itemStack, setUnbreakable);
            inventory.setItem(slot, itemStack);
            return this;
        }
        
        public GUIUtil returnHand(int slot, ItemStack itemStack, String displayName) {
            GUIUtil.setNameAndLore(itemStack, displayName, null);
            inventory.setItem(slot, itemStack);
            return this;
        }

        public void open(Player player) {
            player.openInventory(inventory);
        }

    public static void setNameAndLore(ItemStack itemStack, String displayName, String[] lore) {
    	ItemMeta itemMeta = itemStack.getItemMeta();
        if (displayName != null) {
            itemMeta.setDisplayName(displayName);
            itemStack.setItemMeta(itemMeta);
        }

        if (lore != null) {
            itemMeta.setLore(java.util.Arrays.asList(lore));
            itemStack.setItemMeta(itemMeta);
        }
    }
    
    public static void enchantItem(ItemStack itemStack, Enchantment enchantment, int level) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getEnchants().forEach((ench, lvl) -> itemMeta.removeEnchant(ench));
        itemMeta.addEnchant(enchantment, level, true);
        itemStack.setItemMeta(itemMeta);
    }
    
    public static void addItemFlags(ItemStack itemStack, ItemFlag... itemFlags) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (ItemFlag itemFlag : itemFlags) {
            if (!itemMeta.hasItemFlag(itemFlag)) {
                itemMeta.addItemFlags(itemFlag);
            }
        }
        itemStack.setItemMeta(itemMeta);
    }
    
    public static void addItemFlagAll(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemStack.setItemMeta(itemMeta);
    }

    public static void removeItemFlag(ItemStack itemStack, ItemFlag... itemFlags) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (ItemFlag itemFlag : itemFlags) {
            if (!itemMeta.hasItemFlag(itemFlag)) {
                itemMeta.addItemFlags(itemFlag);
            }
        }
        itemStack.setItemMeta(itemMeta);
    }
    
    public static void removeItemFlagAll(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.removeItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.removeItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.removeItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.removeItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.removeItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemStack.setItemMeta(itemMeta);
    }
    
    public static void setUnbreakable(ItemStack itemStack, boolean setUnbreakable) {
    	ItemMeta itemMeta = itemStack.getItemMeta();
    	itemMeta.setUnbreakable(setUnbreakable);
    }

}
