# Missing content
- the won command isnt fully supported yet.

# HuntRandomizer

This project is meant to be a functional randomizer for the game "Hunt:Showdown".
The file "DataFile" is used for all the given objects. They are seperated by an "@". The following string indicates what type of object it is.

# Command options #

Command | Action | Special usage
| :--- | :--- | :---
generate | generates a new loadout given a rule of conditions. |
won | tells the randomizer that the previous round was succesfull and shall generate a new set of perks. Is also affected by rules. "rule Use_Type = "Support"" only generates perks that are of the support category. If the pool of useable perks is exhausted it will generate random perks regardless of the given rules. | "--won {aquired_perk_points}"
help | previews some help regarding commands and or usages | "--help" will print general help; "--help rules" will print general help regarding rules; "--help .get rules" will print all currently active rules; "--help challenges" 
rule | will create a new rule | "--rule {attribute, operation, operand, ObjectType}" creates a new rule with the given constraints. The condition affective value. All objects in the given file insist of attributes. The relative names there are the attribute. e.g. "rule tier > 2" will create a rule that states, that only items that have a tier of 3 or higher will be chosen. Possible operands are ">"; ">="; "<"; "<=", "=".The objecttype refers to all the possible Files (Weapons, Tools, etc)
exit | exits the program
loss | is identical to telling the generator to generate a new loadout. 
info | prints out some information. Can either print the data itself or counts the amount. Also affected by the ruleset. | "--info {attribute} {ObjectType}". The example command "info Support Tool" will print each Tool that has the respective attribute. For counting exists an attribute called "-count". e.g. "info -count Meleeable Weapon".
challenge | choose a loadout out of a set of pre determined challenges. Those include bomberman {a loadout consisting of only explosive weaponry}

# Additional info #

There exists a strict rule of your loadout to always include an item that is meant for melee (e.g. knifes, etc). Same goes for vitality related items. Having a loadout generate multiple vitality shots can disable the forced med kit.
Those rules can be deactivated individually by the commands "rule always_meds" and "rule always_melee".

# Attribute types #

The internal file for storing all attributes consists of multiple value sets. The following attributes are possible:

Attribute | value | usage
| :--- | :--- | :---
Name | Name of the weapon | String
Cost | Cost of the weapon | Integer (+ "$") 
Slot_Size | Slotsize of the weapon | Integer
Unlock | Needed rank to unlock the weapon | ("Rank" +) Integer
Damage | Damage of the weapon | Integer
Weapon_Family | weapon family of the weapon | String
Dualable | Whether the weapon is able to be held double | Boolean (true or false)
Effective_Range | The effective range of the weapon | Integer (+ "m")
ROF or Rate_Of_Fire | The rate of fire of the weapon | Integer (+ "rpm)
Handling | The handling stat of the weapon | Integer (+ "%")
Reload_Speed | The duration it takes to reload the weapon | Integer (+ "s")
Muzzle_Velocity | The muzzle velocity of the weapon | Integer (+ "m/s")
Meleeable | Whether the weapon can be considered a melee weapon or not | Boolean (true of false)
Light_Melee | The damage of a non charged melee attack | Integer
Heavy_Melee | The damage of a charged melee attack | Integer
Custom_Ammo or CustomAmmos | A set of all the possible Ammotypes | String(, String, String) (Strings seperated by ",")
Tier | How the weapon is percieved, the lower the Tier the worse the weapon is | Integer
Use_Type | Used for tools, consumables and perks and states what use they each serve | String
