package FileHelper.data;

public class ChallengeData {

  private String identifier, explanation;
  private String[] rules;

  public ChallengeData(String identifier, String explanation, String... rules) {
    this.identifier = identifier;
    this.explanation = explanation;
    this.rules = rules;
  }

  public String getIdentifier() {
    return identifier;
  }

  public ChallengeData setIdentifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  public String getExplanation() {
    return explanation;
  }

  public ChallengeData setExplanation(String explanation) {
    this.explanation = explanation;
    return this;
  }

  public String[] getRules() {
    return rules;
  }

  public ChallengeData setRules(String[] rules) {
    this.rules = rules;
    return this;
  }

  @Override
  public String toString() {
    return this.identifier + " -> " + this.explanation;
  }
}
