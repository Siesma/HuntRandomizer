package FileHelper.commands;

import FileHelper.ValueType;
import FileHelper.attribute.Attribute;
import FileHelper.attribute.AttributeIdentifier;
import application.Runnable;
import utils.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandGenerate extends AbstractCommand {
    private final
    // the logarithmic expression that delivers a probability of 25% to obtain four tools.
    double twentyp = 0.707107;


    @Override
    public void updateInformationData() {
        ArrayList list = new ArrayList();
        setInformationLists(list);
    }

    @Override
    public void setInformationLists(ArrayList list) {
        super.setInformationLists(list);
    }

    public CommandGenerate(String... args) {
        super(args);
    }

    @Override
    public void fire(Runnable run, String arguments, boolean suppress) {
        String[] type = arguments.split(" ");
        int maxWeaponSlotSize = 4;
        int maxAmountOfTools = 4;
        int maxAmountOfConsumables = 4;
        Weapon[] weapons;
        Tool[] tools = new Tool[maxAmountOfTools];
        Consumable[] consumables;

        Hunter hunter;
        String tier = "" + ((int) (Math.random() * 3));
        System.out.println("You have to use your \"Tier " + tier + "\" hunter. Does the hunter has Quartermaster? Type \"yes\" or \"no\"");
        boolean quarter = new Scanner(System.in).nextLine().equalsIgnoreCase("yes");
        maxWeaponSlotSize += quarter ? 1 : 0;

        ArrayList<Weapon> weaponArrayList = new ArrayList<>();
        ArrayList<Tool> toolArrayList = new ArrayList<>();
        ArrayList<Consumable> consumableArrayList = new ArrayList<>();
        ArrayList<Perk> perkArrayList = new ArrayList<>();

        for (AttributeObject attributeObject : run.getFiltered_attributeObjects()) {
            if (attributeObject.getClass().toString().endsWith("Weapon")) {
                weaponArrayList.add((Weapon) attributeObject);
            } else if (attributeObject.getClass().toString().endsWith("Tool")) {
                toolArrayList.add((Tool) attributeObject);
            } else if (attributeObject.getClass().toString().endsWith("Consumable")) {
                consumableArrayList.add((Consumable) attributeObject);
            } else if (attributeObject.getClass().toString().endsWith("Perk")) {
                perkArrayList.add((Perk) attributeObject);
            }
        }


        /*
         Start section for Weapons
         */
        boolean updatePrimaryToDual = false, updateSecondaryToDual = false;
        Weapon primary, secondary;
        if (weaponArrayList.isEmpty()) {
            System.out.println("\tThere are no Weapons that fill the requirements. Therefor the generator will ignore the ruleset. ");


        }

        Weapon bare_hands = new Weapon(new Attribute("Name", "Bare Hands"), new Attribute("Slot_Size", "0"));

        if (weaponArrayList.isEmpty()) {
            primary = bare_hands;
        } else {
            primary = weaponArrayList.get((int) (Math.random() * weaponArrayList.size()));
        }
        if (primary.hasGivenAttribute(ValueType.Slot_Size).doesExist()) {
            maxWeaponSlotSize -= Integer.parseInt(primary.hasGivenAttribute(ValueType.Slot_Size).getAttribute().getData().replaceAll("[^0-9.]", ""));
        }
        ArrayList<Weapon> helperArrayList = new ArrayList<>(weaponArrayList);
        for (Weapon w : weaponArrayList) {
            AttributeIdentifier slotSize = w.hasGivenAttribute(ValueType.Slot_Size);
            if (!slotSize.doesExist()) {
                continue;
            }
            if (Integer.parseInt(w.hasGivenAttribute(ValueType.Slot_Size).getAttribute().getData().replaceAll("[^0-9.]", "")) > maxWeaponSlotSize) {
                helperArrayList.remove(w);
            }
        }
        weaponArrayList = helperArrayList;

        if (weaponArrayList.isEmpty()) {
            secondary = bare_hands;
        } else {
            secondary = weaponArrayList.get((int) (Math.random() * weaponArrayList.size()));
        }

        if (secondary.hasGivenAttribute(ValueType.Slot_Size).doesExist()) {
            maxWeaponSlotSize -= Integer.parseInt(secondary.hasGivenAttribute(ValueType.Slot_Size).getAttribute().getData().replaceAll("[^0-9.]", ""));
        }

        if (maxWeaponSlotSize > 0) {
            AttributeIdentifier primaryIsDualable = primary.hasGivenAttribute(ValueType.Dualable);
            AttributeIdentifier secondaryIsDualable = secondary.hasGivenAttribute(ValueType.Dualable);
            if (primaryIsDualable.doesExist()) {
                if (shouldApply(0.5)) {
                    updatePrimaryToDual = true;
                    maxWeaponSlotSize--;
                }
            }
            if (maxWeaponSlotSize > 0) {
                if (secondaryIsDualable.doesExist()) {
                    if (shouldApply(0.5)) {
                        updateSecondaryToDual = true;
                        maxWeaponSlotSize--;
                    }
                }
            }
        }

        AttributeIdentifier primaryMeleeable = primary.hasGivenAttribute(ValueType.Meleeable);
        AttributeIdentifier secondaryMeleeable = secondary.hasGivenAttribute(ValueType.Meleeable);

        boolean hasMeleeWeapon = false;
        if (primaryMeleeable.doesExist()) {
            if (primaryMeleeable.getAttribute().getData().equalsIgnoreCase("True")) {
                hasMeleeWeapon = true;
            }
        }
        if (secondaryMeleeable.doesExist()) {
            if (secondaryMeleeable.getAttribute().getData().equalsIgnoreCase("True")) {
                hasMeleeWeapon = true;
            }
        }
        AttributeIdentifier primaryCustomAmmo = primary.hasGivenAttribute(ValueType.Custom_Ammo);
        AttributeIdentifier secondaryCustomAmmo = secondary.hasGivenAttribute(ValueType.Custom_Ammo);
        String primaryAmmo = "", secondaryAmmo = "";

        String ruleStatingAmmo = "";
        for (Rule r : run.getRules()) {
            if (r.getAttribute().equalsIgnoreCase("Custom_Ammo")) {
                ruleStatingAmmo = r.getOperand();
            }
        }
        if (primaryCustomAmmo.doesExist()) {
            if (shouldApply(0.5)) {
                String[] ammos = (primaryCustomAmmo.getAttribute().getData() + "").split(",");
                primaryAmmo = ruleStatingAmmo.equalsIgnoreCase("") ? ammos[(int) (Math.random() * ammos.length)] : ruleStatingAmmo;
            }
        }

        if (secondaryCustomAmmo.doesExist()) {
            if (shouldApply(0.5)) {
                String[] ammos = (secondaryCustomAmmo.getAttribute().getData() + "").split(",");
                secondaryAmmo = ruleStatingAmmo.equalsIgnoreCase("") ? ammos[(int) (Math.random() * ammos.length)] : ruleStatingAmmo;
            }
        }

        weapons = new Weapon[]{
            primary, secondary
        };

        /*
         Start section for consumables
         */
        Consumable[] consumableHelperArray = new Consumable[maxAmountOfConsumables];
        for (int i = 0; i < consumableHelperArray.length; i++) {
            if (consumableArrayList.isEmpty()) {
                for (int j = i; j < consumableHelperArray.length; j++) {
                    consumableHelperArray[j] = new Consumable(new Attribute("Name", "None"));
                }
                break;
            }
            if (!shouldApply(Math.pow(twentyp, i))) {
                for (int j = i; j < consumableHelperArray.length; j++) {
                    consumableHelperArray[j] = new Consumable(new Attribute("Name", "None"));
                }
                break;
            }
            consumableHelperArray[i] = consumableArrayList.get((int) (Math.random() * consumableArrayList.size()));
        }
        consumables = consumableHelperArray;


        /*
         Start section for tools
         */
        int healthItems = 0;
        for (Consumable consumable : consumables) {
            AttributeIdentifier isHealthItem = consumable.hasGivenAttribute(ValueType.Use_Type);
            if (isHealthItem.doesExist()) {
                if (isHealthItem.getAttribute().getData().contains("Support")) {
                    healthItems++;
                }
            }
        }


        ArrayList<Tool> tooMeleelList = new ArrayList<>();
        for (AttributeObject tool : run.getUnfiltered_attributeObjects()) {
            AttributeIdentifier meleeAble = tool.hasGivenAttribute(ValueType.Meleeable);
            if (meleeAble.doesExist()) {
                if (meleeAble.getAttribute().getData().replaceAll("\\s+", "").equalsIgnoreCase("True") && tool.hasGivenAttribute(ValueType.Use_Type).getAttribute().getData().contains("Tool")) {
                    tooMeleelList.add((Tool) tool);
                }
            }
        }
        boolean hasToolMelee = false;
        if (!hasMeleeWeapon) {
            tools[0] = tooMeleelList.get((int) (Math.random() * tooMeleelList.size()));
            hasToolMelee = true;
        }

        Tool med_kit = null;
        for (Tool tool : toolArrayList) {
            AttributeIdentifier hasName = tool.hasGivenAttribute(ValueType.Name);
            if (!hasName.doesExist()) {
                continue;
            }
            if (hasName.getAttribute().getData().equalsIgnoreCase("First Aid Kit")) {
                med_kit = tool;
                toolArrayList.remove(med_kit);
                break;
            }
        }

        if (med_kit == null) {
            med_kit = new Tool(new Attribute("Name", "First Aid Kit"), new Attribute("Cost", "30"), new Attribute("Use_Type", "Tool, Support"));
        }

        if (healthItems <= 3) {
            tools[3] = med_kit;
            maxAmountOfTools--;
        }

        int beginIndex = hasToolMelee ? 1 : 0;

        for (int i = beginIndex; i < maxAmountOfTools; i++) {
            // failsafe; no possible tools can be generated -> fill with nothing
            if (toolArrayList.isEmpty()) {
                for (int j = i; j < maxAmountOfTools - 1; j++) {
                    tools[j] = new Tool(new Attribute("Name", "None"));
                }
                break;
            }

            if (!shouldApply(Math.pow(twentyp, i))) {
                for (int j = i; j < tools.length; j++)
                    tools[j] = new Tool(new Attribute("Name", "None"));

                break;
            }
            Tool nT = toolArrayList.get((int) (Math.random() * toolArrayList.size()));
            toolArrayList.remove(nT);
            tools[i] = nT;

        }

        if (!tools[3].hasGivenAttribute(ValueType.Name).getAttribute().getData().equalsIgnoreCase("First Aid Kit") && healthItems <= 3) {
            tools[3] = med_kit;
        }

        hunter = new Hunter(tier, weapons, tools, consumables, null);
        hunter.updateWeaponDual(updatePrimaryToDual, updateSecondaryToDual);
        hunter.applyCustomAmmo(primaryAmmo, secondaryAmmo);
        run.updateHunter(hunter);

        run.printHunter();

    }


    private boolean shouldApply(double in) {
//        return Math.random() < in;
        return in > 0;
    }

    @Override
    public String getHelp(String... type) {
        StringBuilder out = new StringBuilder();
        out.append("---\n");
        out.append("\tUsage: \n");
        out.append("\t\"generate\" generates a new loadout based off of the current set of rules.\n");
        out.append("---\n");
        return out.toString();
    }
}
