public class FuzzySystem {
  // 2. Interfereta
  // 3. Defuzzificare

  private String emisfera;
  private String sezon;
  private double latitudine;

  // fuzzy constructor  seteaza  emisfera, sezonul si  latitudinea
  public FuzzySystem(String emisfera, String sezon, double latitudine) {
    this.emisfera = emisfera;
    this.sezon = sezon;
    this.latitudine = latitudine;
  }

  // Setters and getters
  public String getEmisfera() {
    return emisfera;
  }

  public void setEmisfera(String emisfera) {
    this.emisfera = emisfera;
  }

  public String getSezon() {
    return sezon;
  }

  public void setSezon(String sezon) {
    this.sezon = sezon;
  }

  public double getLatitudine() {
    return latitudine;
  }

  public void setLatitudine(double latitudine) {
    this.latitudine = latitudine;
  }

  /*
  // Adjustare la unghiul de vara în 21 iunie - 22 sept
  // Ajustați la unghiul de toamnă pe 21 sept - 21 dec
  // Ajustați la unghiul de iarnă pe 21 dec - 20 martie
  // Ajustați la unghiul de primăvară pe 20 martie - 21 iunie
   */

  public double calculateOptimalSeasonalAngle(FuzzySystem input) {
    double unghi = 0;
// Verifică sezonul și calculează unghiul in care panoul solar va rămâne în timp ce se rotește prin ore de zi pentru a maximiza acoperirea solară.
    if ( input.getSezon().equals("vara") ) {
      unghi = (input.getLatitudine() * 0.92) - 24.3;
      System.out.println("Unghiul de inclinare a fost setat la " + unghi + "° pentru optimizari in seznoul de vara");
      return unghi;
    } else if ( input.getSezon().equals("primavara") || (input.getSezon().equals("toamna")) ) {
      unghi = (input.getLatitudine() * 0.98) - 2.3;
      System.out.println("Unghiul de inclinare a fost setat la "
                         + unghi
                         + "° pentru optimizari in seznoul de primavra/toamna");
      return unghi;
    } else if ( input.getSezon().equals("vara") ) {


    } else { unghi = (input.getLatitudine() * 0.89) + 24; }
    System.out.println("Unghiul de inclinare a fost setat la " + unghi + "° pentru optimizari in seznoul de iarna");
    return unghi;
  }

  /*    există îmbunătățiri care pot fi făcute pentru a optimiza radiatia solara.
        1. Verific unghiul optim și il reglez corespunzător
        2. Verifica ora zilei și determin  rotația
   */
  public String implicationOfFuzzySet(double fuzzyValue, SunriseSunset s, FuzzySystem f) {
    if ( fuzzyValue != 1.0 ) {
    // Verificați dacă unghiul sezonului curent este corect
      String currentSeasonAngle = s.checkCalenderDate();
      if ( f.getSezon().equals(currentSeasonAngle) ) {
        // unghi corect pentru sezonul curent
      } else  {
        f.setSezon(currentSeasonAngle);
        // Calculez noul unghi in dependenta de sezon
        calculateOptimalSeasonalAngle(f);
        return "Panoul a fost ajustat la sezonul curent";
      }
      // Re-ajustarea rotatiei panoului in functie de ora
      int rotationDegree = s.calculateOptimalTimeOfDayAngle();
      return "Timp curent: "
             + s.getDate()
             + ", panoul a fost ajustat la "
             + rotationDegree
             + "°"
             + s.getDirection()
             + " astfel încât panoul să fie rotit pentru optimizarea maximă.";
    } else {
      return "În prezent nu există ajustări ale panoului, optimizarea maximizată este în curs de desfășurare.";
    }
  }

  // Puterea arbitrară a panoului solar de 200 watts pe oră, cea mai mare putere a panoului solar. Se va folosi pentru a roti în unghiul soarelui pentru a asigura optimizarea.

  public double fuzzificationOfWatts(int watts) {
    if ( watts == 200 ) {
      return 1.0;
    } else if ( watts >= 180 ) {
      return 0.9;
    } else if ( watts >= 160 ) {
      return 0.8;
    } else if ( watts >= 140 ) {
      return 0.7;
    } else if ( watts >= 120 ) {
      return 0.6;
    } else if ( watts >= 100 ) {
      return 0.5;
    } else if ( watts >= 80 ) {
      return 0.4;
    } else if ( watts >= 60 ) {
      return 0.3;
    } else if ( watts >= 40 ) {
      return 0.2;
    } else if ( watts >= 20 ) {
      return 0.1;
    } else {
      return 0.0;
    }
  }

  //Utilizez logica Fuzzy OR asupra 2 celor 2 functii
  public double inferenceFuzzyLogic(double fuzzyWatts, double fuzzyTime) {
    return (fuzzyWatts > fuzzyTime ? fuzzyWatts : fuzzyTime);
  }

}
