package FileHelper.commands;

import FileHelper.ValueType;
import application.Runnable;
import utils.*;

public class CommandGenerate extends AbstractCommand {
    public CommandGenerate(String... args) {
        super(args);
    }

    @Override
    public void fire(Runnable run, String arguments) {
        String[] type = arguments.split(" ");
        boolean quarter = Math.random() > 0.5;
        int maxWeaponSlotSize = quarter ? 5 : 4;
        int maxAmountOfTools = 4;
        int maxAmountOfConsumables = 4;
        Weapon[] weapons = new Weapon[2];
        Tool[] tools = new Tool[4];
        Consumable[] consumables = new Consumable[4];
        Perk[] perks = new Perk[3];


//        Tier tier = Tier.values()[(int) Math.round(Math.random() * (Tier.values().length - 1))];
//        boolean hasQuarter = false;
//        if(tier != Tier.ONE) {
//            Scanner sc = new Scanner(System.in);
//            System.out.println("You have to use a \"" + tier.getName() + "\" Hunter. Does the given Hunter has the trait \"Quartermaster\"? Type \"yes\" or \"no\"");
//            hasQuarter = sc.nextLine().equalsIgnoreCase("yes");
//        }
//        Weapon primaryWeapon = shouldApply(1 - 0.5 / (float) run.getUnfiltered_attributeObjects().size()) ? new Weapon("Bare Hands", 0, 0, "", false) : Weapon.getRandom(maxWeaponSlotSize);
//        Weapon secondaryWeapon = shouldApply(1 - 0.5 / (float) run.getUnfiltered_attributeObjects().size()) ? new Weapon("Bare Hands", 0, 0, "", false) : Weapon.getRandom(maxWeaponSlotSize - primaryWeapon.hasGivenAttribute(ValueType.SlotSize)));

//        if (maxWeaponSlotSize > (primaryWeapon.getSize() + secondaryWeapon.getSize())) {
//            if(primaryWeapon.isDualAble() && !secondaryWeapon.isDualAble()) {
//                primaryWeapon.setDual(shouldApply(0.5));
//            } else if (secondaryWeapon.isDualAble() && !primaryWeapon.isDualAble()) {
//                secondaryWeapon.setDual(shouldApply(0.5));
//            } else if (secondaryWeapon.isDualAble() && primaryWeapon.isDualAble()) {
//                if (shouldApply(0.5)) {
//                    primaryWeapon.setDual(shouldApply(0.5));
//                } else {
//                    secondaryWeapon.setDual(shouldApply(0.5));
//                }
//            }
//        }
//        weapons[0] = primaryWeapon;
//        weapons[1] = secondaryWeapon;
//        tools[0] = Tool.getToolByName("First Aid Kit");
//        for (int i = 1; i < maxAmountOfTools; i++) {
//            double percentile = 1d / (Math.pow(2, i));
//            percentile *= 1.25;
//            if (Math.random() > percentile)
//                break;
//            Tool tool = Tool.getRandom();
//            tools[i] = tool;
//        }
//
//        for (int i = 0; i < maxAmountOfConsumables; i++) {
//            double percentile = 1d / (Math.pow(2, i));
//            percentile *= 1.25;
//            if (Math.random() > percentile)
//                break;
//            consumables[i] = Consumable.getRandom();
//        }
//
//        if (Math.random() > 1 - Math.pow(0.5, primaryWeapon.getPossibleAmmoCount())) {
//            primaryWeapon.applyRandomCustomAmmo();
//        }
//
//        if (Math.random() > 1 - Math.pow(0.5, secondaryWeapon.getPossibleAmmoCount())) {
//            secondaryWeapon.applyRandomCustomAmmo();
//        }
//
//        Hunter hunter = new Hunter("tier", weapons, tools, consumables, perks);
//
//        printHunter(hunter);


    }


    void printHunter(Hunter hunter) {
//        System.out.printf(weaponInfo, (hunter.getWeaponSlot1().hasGivenAttribute(ValueType.Name).getAttribute().getData() + (hunter.getWeaponSlot1().isDual() ? ", dual-Wield" : "")), hunter.getWeaponSlot1().getAppliedAmmo(), (hunter.getWeaponSlot2().hasGivenAttribute(ValueType.Name).getAttribute().getData() + (hunter.getWeaponSlot2().isDual() ? ", dual-Wield" : "")), hunter.getWeaponSlot2().getAppliedAmmo());
        for (Tool tool : hunter.getToolArray()) {
            if (!tool.hasGivenAttribute(ValueType.Name).doesExist())
                break;
            System.out.println("Tool: " + tool.hasGivenAttribute(ValueType.Name).getAttribute().getData());
        }
        System.out.println();
        for (Consumable consumable : hunter.getConsumableArray()) {
            if (!consumable.hasGivenAttribute(ValueType.Name).doesExist())
                break;
            System.out.println("Consumable: " + consumable.hasGivenAttribute(ValueType.Name).getAttribute().getData());
        }
        System.out.println("\nThis hunter will cost you " + calculateCost(hunter) + " plus the cost of the custom ammo");
    }

    double calculateCost(Hunter hunter) {

//        double cost;
//        Weapon[] weapons = hunter.getWeaponArray();
//        Tool[] tools = hunter.getToolArray();
//        Consumable[] consumables = hunter.getConsumableArray();
//        double weaponCost = Arrays.stream(weapons).mapToDouble(Weapon::getCost).sum();
//        double toolCost = Arrays.stream(tools).mapToDouble(SimpleType::getCost).sum();
//        double consumableCost = Arrays.stream(consumables).mapToDouble(SimpleType::getCost).sum();
//        cost = weaponCost + toolCost + consumableCost;
        return 0;

    }

    private boolean shouldApply(double in) {
        return Math.random() > in;
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
