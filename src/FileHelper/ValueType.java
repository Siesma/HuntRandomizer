package FileHelper;

import FileHelper.data.InformationData;

public enum ValueType {

    Name(new InformationData("Name", "")),
    Cost(new InformationData("Cost", "")),
    SlotSize(new InformationData("Slot_Size", "")),
    Unlock(new InformationData("Unlock", "")),
    Damage(new InformationData("Damage", "")),
    Weapon_Family(new InformationData("Category", ""), new InformationData("Weapon_Family", "")),
    Dualable(new InformationData("Dualable", "")),
    Effective_Range(new InformationData("Effective_Range", "")),
    ROF(new InformationData("ROF", ""), new InformationData("Rate_Of_Fire", "")),
    Handling(new InformationData("Handling", "")),
    Reload_Speed(new InformationData("Reload_Speed", "")),
    Muzzle_Velocity(new InformationData("Muzzle_Velocity", "")),
    Meleeable(new InformationData("Meleeable", "")),
    Light_Melee(new InformationData("Light_Melee", "")),
    Heavy_Melee(new InformationData("Heavy_Melee", "")),
    Custom_Ammo(new InformationData("CustomAmmos", ""), new InformationData("Custom_Ammo", "")),
    Tier(new InformationData("Tier", "")),
    Use_Type(new InformationData("Use_Type", ""));

    private InformationData[] identifier;

    ValueType(InformationData... identifier) {
        this.identifier = identifier;
    }

    public InformationData[] getIdentifier() {
        return identifier;
    }

}

