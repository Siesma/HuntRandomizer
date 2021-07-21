public class Helper {
    public static void main(String[] args) {

        String ss = ""; //was previously -> (new ImportHelper()).get(null, "HelperFile");
        int l = 25;
        int maxl = ss.split("\t").length;
        String[] sspl = ss.split("\t");
        for (int in = 0; in < maxl / l; in++) {
            int i = in * l;
            i++;
            System.out.println("@Weapon");
            System.out.println("\tName: " + sspl[i + 0]);
            System.out.println("\tCost: " + sspl[i + 1]);
            System.out.println("\tUnlock: " + sspl[i + 2]);
            System.out.println("\tDamage: " + sspl[i + 4]);
            System.out.println("\tWeapon_Family: " + getWeaponFamily(sspl[i]));
            System.out.println("\tEffective Range: " + sspl[i + 6]);
            System.out.println("\tROF: " + sspl[i + 10]);
            System.out.println("\tHandling: " + sspl[i + 12]);
            System.out.println("\tReload_Speed: " + sspl[i + 16]);
            System.out.println("\tMuzzle_Velocity: " + sspl[i + 18]);
            System.out.println("\tMelee: " + isMelee(sspl[i]));
            System.out.println("\tMelee_Damage: " + sspl[i + 22]);
            System.out.println("\tHeavy_Melee: " + sspl[i + 24]);
            System.out.println("\tCustom_Ammo: None");
        }

    }

    static String isMelee(String in) {
        String[] meleeAble = {

                "Brawler",
                "Talon",
                "Lance",
                "Claw"

        };
        for (String s : meleeAble)
            if (in.contains(s))
                return "true";


        return "false";
    }

    static String getWeaponFamily(String in) {

        String[] families = {
                "Lebel 1886",
                "Martini-Henry IC1",
                "Mosin-Nagant M1891",
                "Nagant M1895 Officer",
                "Romero 77",
                "Sparks LRR",
                "Specter 1882",
                "Springfield 1866",
                "Vetterli 71 Karabiner",
                "Winfield M1873 ",
                "Winfield M1873C",
                "Winfield M1876 Centennial",
                "Winfield 1887 Terminus",
                "Bornheim No. 3",
                "Dolch 96",
                "Nagant M1895",
                "Caldwell Pax",
                "Caldwell Conversion",
                "Hand Crossbow",
                "LeMat Mark II"
        };

        for (String s : families) {
            if (in.startsWith(s))
                return s;
        }


        return in;
    }


}
