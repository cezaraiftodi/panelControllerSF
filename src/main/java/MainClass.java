public class MainClass {
  public static void main(String[] args) {
    SunriseSunset s = new SunriseSunset();
    s.output();
    FuzzySystem f = new FuzzySystem("Nord-Vest", "Primavara", s.getLatitude().doubleValue());
    double fuzzyWatts = f.fuzzificationOfWatts(160);          // membership function calls
    double fuzzyTime = s.fuzzificationOfTimeOfDay();                // membership function calls
    double fuzzyValue = f.inferenceFuzzyLogic(fuzzyWatts, fuzzyTime); // Fuzzy logic call
    String fuzzyImplication = f.implicationOfFuzzySet(fuzzyValue, s, f); // Gets hard numbers
    System.out.println(fuzzyImplication + "\n");

    // Re-adjusts the season angle since the input is for winter.
    FuzzySystem f2 = new FuzzySystem("Nord-Vest", "Iarna", s.getLatitude().doubleValue());
    fuzzyWatts = f2.fuzzificationOfWatts(40);
    fuzzyTime = s.fuzzificationOfTimeOfDay();
    fuzzyValue = f2.inferenceFuzzyLogic(fuzzyWatts, fuzzyTime);
    fuzzyImplication = f2.implicationOfFuzzySet(fuzzyValue, s, f2);
    System.out.println(fuzzyImplication + "\n");

    // Third example ignores any fuzzy logic calculations because the input watts are currently at 200 (maximum).
    FuzzySystem f3 = new FuzzySystem("Nord-Vest", "Vara", s.getLatitude().doubleValue());
    fuzzyWatts = f3.fuzzificationOfWatts(200);
    fuzzyTime = s.fuzzificationOfTimeOfDay();
    fuzzyValue = f3.inferenceFuzzyLogic(fuzzyWatts, fuzzyTime);
    fuzzyImplication = f3.implicationOfFuzzySet(fuzzyValue, s, f3);
    System.out.println(fuzzyImplication + "\n");

    // Rotated to 145° to be at the prime angle for when the sun rises.
    FuzzySystem f4 = new FuzzySystem("Nord-Vest", "Toamna", s.getLatitude().doubleValue());
    fuzzyTime = s.fuzzificationOfTimeOfDay();
    fuzzyValue = f4.inferenceFuzzyLogic(fuzzyWatts, fuzzyTime);
    fuzzyImplication = f4.implicationOfFuzzySet(fuzzyValue, s, f4);
    System.out.println(fuzzyImplication + "\n");
  }
}

