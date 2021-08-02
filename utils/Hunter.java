package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Hunter {
    Weapon[] weaponArray;
    Tool[] toolArray;
    Consumable[] consumableArray;
    Perk[] perkArray;
    private ArrayList<AttributeObject> fullList;
    private String tier;
    private boolean isPrimaryDual = false, isSecondaryDual = false;
    private String primaryCustomAmmo = "", secondaryCustomAmmo = "";
    private ArrayList<Perk> perksLeft;

    public Hunter(String tier, Weapon[] weaponArray, Tool[] toolArray, Consumable[] consumableArray, Perk[] perkArray) {
        this.tier = tier;
        this.weaponArray = weaponArray;
        this.toolArray = toolArray;
        this.consumableArray = consumableArray;
        this.perkArray = perkArray;
        this.fullList = new ArrayList<>();
        fullList.addAll(Arrays.asList((weaponArray)));
        fullList.addAll(Arrays.asList((toolArray)));
        fullList.addAll(Arrays.asList((consumableArray)));
        if(perkArray == null)
            return;
        fullList.addAll(Arrays.asList((perkArray)));
    }


    public ArrayList<AttributeObject> getFullList() {
        return fullList;
    }

    public void updateWeaponDual(boolean primary, boolean secondary) {
        this.isPrimaryDual = primary;
        this.isSecondaryDual = secondary;
    }

    public void applyCustomAmmo (String primary, String secondary) {
        this.primaryCustomAmmo = primary;
        this.secondaryCustomAmmo = secondary;
    }

    public String getTier() {
        return this.tier;
    }

    public boolean isPrimaryDual() {
        return isPrimaryDual;
    }

    public Hunter setPrimaryDual(boolean primaryDual) {
        isPrimaryDual = primaryDual;
        return this;
    }

    public boolean isSecondaryDual() {
        return isSecondaryDual;
    }

    public Hunter setSecondaryDual(boolean secondaryDual) {
        isSecondaryDual = secondaryDual;
        return this;
    }

    public String getPrimaryCustomAmmo() {
        return primaryCustomAmmo;
    }

    public Hunter setPrimaryCustomAmmo(String primaryCustomAmmo) {
        this.primaryCustomAmmo = primaryCustomAmmo;
        return this;
    }

    public String getSecondaryCustomAmmo() {
        return secondaryCustomAmmo;
    }

    public Hunter setSecondaryCustomAmmo(String secondaryCustomAmmo) {
        this.secondaryCustomAmmo = secondaryCustomAmmo;
        return this;
    }

    public Weapon getWeaponSlot1() {
        return weaponArray[0];
    }

    public Weapon getWeaponSlot2() {
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

    public ArrayList<Perk> getPerksLeft () {
        return perksLeft;
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

    public void addPerks(ArrayList<Perk> currentPerks) {
        this.perkArray = new Perk[currentPerks.size()];
        for(int i = 0; i < perkArray.length; i++) {
            perkArray[i] = currentPerks.get(i);
        }
        this.fullList.addAll(Arrays.asList(perkArray));
    }
}
