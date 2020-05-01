public class MainClass {
  public static void main(String[] args) {
    SunriseSunset sunriseSunset = new SunriseSunset();
    sunriseSunset.iesire();
    Integer putereWatts = 140;
    String emisfera = "Nord-Vest";
    String sezon = "sezon";

    FuzzySystem fuzzySystem = new FuzzySystem(emisfera, sezon, sunriseSunset.getLatitude().doubleValue());
    double fuzzyWatts = fuzzySystem.wattsFuzzyfication(putereWatts);          // apelarea metodei pentru fuzzyficarea watts
    double fuzzyTime = sunriseSunset.timeOfDayFuzzyfication();                // apelarea metodei pentru fuzzyficarea datei
    double fuzzyValue = fuzzySystem.inferenceFuzzyLogic(fuzzyWatts, fuzzyTime); // aplicarea inferentei
    String fuzzyImplication = fuzzySystem.implicationOfFuzzySet(fuzzyValue, sunriseSunset, fuzzySystem); // Obtinere valori transante
    System.out.println(fuzzyImplication + "\n");
  }
}

