package FileHelper;

import FileHelper.data.InformationData;
import FileHelper.data.RuleType;

public enum ValueType {

  Name(RuleType.String, new InformationData("Name", "")),
  Cost(RuleType.Integer, new InformationData("Cost", "")),
  Slot_Size(RuleType.Integer, new InformationData("Slot_Size", "")),
  Unlock(RuleType.Integer, new InformationData("Unlock", "")),
  Damage(RuleType.Integer, new InformationData("Damage", "")),
  Weapon_Family(RuleType.String, new InformationData("Category", ""), new InformationData("Weapon_Family", "")),
  Dualable(RuleType.Boolean, new InformationData("Dualable", "")),
  Effective_Range(RuleType.Integer, new InformationData("Effective_Range", "")),
  ROF(RuleType.Integer, new InformationData("ROF", ""), new InformationData("Rate_Of_Fire", "")),
  Handling(RuleType.Integer, new InformationData("Handling", "")),
  Reload_Speed(RuleType.Integer, new InformationData("Reload_Speed", "")),
  Muzzle_Velocity(RuleType.Integer, new InformationData("Muzzle_Velocity", "")),
  Meleeable(RuleType.Boolean, new InformationData("Meleeable", "")),
  Light_Melee(RuleType.Integer, new InformationData("Light_Melee", "")),
  Heavy_Melee(RuleType.Integer, new InformationData("Heavy_Melee", "")),
  Custom_Ammo(RuleType.Set, new InformationData("CustomAmmos", ""), new InformationData("Custom_Ammo", "")),
  Tier(RuleType.Integer, new InformationData("Tier", "")),
  Use_Type(RuleType.Set, new InformationData("Use_Type", ""));

  private InformationData[] identifier;
  private RuleType ruleType;

  ValueType(RuleType ruleType, InformationData... identifier) {
    this.ruleType = ruleType;
    this.identifier = identifier;
  }

  public InformationData[] getIdentifier() {
    return identifier;
  }

  public RuleType getRuleType() {
    return ruleType;
  }

}

