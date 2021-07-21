package utils;

public class Hunter {
    Weapon[] weaponArray; Tool[] toolArray; Consumable[] consumableArray; Perk[] perkArray;
    public Hunter (String tier, Weapon[] weaponArray, Tool[] toolArray, Consumable[] consumableArray, Perk[] perkArray) {
        this.weaponArray = weaponArray;
        this.toolArray = toolArray;
        this.consumableArray = consumableArray;
        this.perkArray = perkArray;
    }



    public Weapon getWeaponSlot1 () {
        return weaponArray[0];
    }

    public Weapon getWeaponSlot2 () {
        return weaponArray[1];
    }

    public Weapon[] getWeaponArray() {
        return weaponArray;
    }

    public Hunter setWeaponArray(Weapon[] weaponArray) {
        this.weaponArray = weaponArray;
        return this;
    }

    public Tool[] getToolArray() {
        return toolArray;
    }

    public Hunter setToolArray(Tool[] toolArray) {
        this.toolArray = toolArray;
        return this;
    }

    public Consumable[] getConsumableArray() {
        return consumableArray;
    }

    public Hunter setConsumableArray(Consumable[] consumableArray) {
        this.consumableArray = consumableArray;
        return this;
    }

    public Perk[] getPerkArray() {
        return perkArray;
    }

    public Hunter setPerkArray(Perk[] perkArray) {
        this.perkArray = perkArray;
        return this;
    }
}
