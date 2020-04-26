import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SunriseSunset {
  //variabilele de referinta utilizate
  private String officialSunrise;
  private String officialSunset;
  private Date today;
  private SunriseSunsetCalculator calculator;
  private Location location;
  private String direction;

    // SunriseSunset constructor fără arg, dar inițializează câmpurile.
  public SunriseSunset() {
    this.location = new Location("27.2038", "77.5011");
    this.calculator = new SunriseSunsetCalculator(location, TimeZone.getTimeZone("GMT+6"));
    setDate();
    setOfficialSunrise();
    setOfficialSunset();
  }

  // Setteri si getteri
  public void setDate() {
    this.today = Calendar.getInstance().getTime();
  }

  public Date getDate() {
    return today;
  }

  public void setOfficialSunrise() {
    this.officialSunrise = calculator.getOfficialSunriseForDate(Calendar.getInstance()) ;
  }

  public String getOfficialSunrise() {
    return  officialSunrise;
  }

  public void setOfficialSunset() {
    this.officialSunset = calculator.getOfficialSunsetForDate(Calendar.getInstance());
  }

  public String getOfficialSunset() {
    return officialSunset;
  }

  public BigDecimal getLatitude() {
    return location.getLatitude();
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getDirection() {
    return direction;
  }

  // Verificări pentru emisfera nordică, pentru a testa daca unghiul total al panoului este corect pentru sezonul setat se  verifica data curentă comparativ cu datele stocate.
 public String checkCalenderDate() {
    Calendar min = Calendar.getInstance();
    Calendar max = Calendar.getInstance();
    Calendar current = Calendar.getInstance();
    min.set(getDate().getYear(), Calendar.JUNE, 21);
    max.set(getDate().getYear(), Calendar.SEPTEMBER, 22);
    if ( current.before(max) && current.after(min) ) {
      // Ajustare la unghiul de vara
      return "vara";
    }
    min.set(getDate().getYear(), Calendar.SEPTEMBER, 22);
    max.set(getDate().getYear(), Calendar.DECEMBER, 21);
    if ( current.before(max) && current.after(min) ) {
      // Ajustare la unghiul de toamna
      return "toamna";
    }
    min.set(getDate().getYear(), Calendar.DECEMBER, 21);
    max.set(getDate().getYear(), Calendar.MARCH, 20);
    if ( current.before(max) && current.after(min) ) {
      // Ajustare la unghiul de iarna
      return "iarna";
    }
    min.set(getDate().getYear(), Calendar.MARCH, 20);
    max.set(getDate().getYear(), Calendar.JUNE, 21);
    if ( current.before(max) && current.after(min) ) {
      // Ajustare la unghiul de primavara
    }
    return "primavara";
  }
  // Funcția de membru care returnează o valoare fuzzy în funcție de ora cu care
  // putem obține cel mai mare randament al panoului.
  public double fuzzificationOfTimeOfDay() {
    // Input - ora, output - fuzzy value
    Calendar c = Calendar.getInstance();
    c.setTime(getDate());
    int hour = c.get(Calendar.HOUR_OF_DAY);
    if ( hour >= 5 ) {
      return 0.0;
    } else if ( hour >= 6 ) {
      return 0.1;
    } else if ( hour >= 7 ) {
      return 0.3;
    } else if ( hour >= 8 ) {
      return 0.5;
    } else if ( hour >= 10 ) {
      return 0.7;
    } else if ( hour >= 11 ) {
      return 0.9;
    } else if ( hour >= 12 ) {
      return 1.0;
    } else if ( hour >= 13 ) {
      return 0.9;
    } else if ( hour >= 14 ) {
      return 0.8;
    } else if ( hour >= 15 ) {
      return 0.7;
    } else if ( hour >= 16 ) {
      return 0.6;
    } else if ( hour >= 17 ) {
      return 0.4;
    } else if ( hour >= 18 ) {
      return 0.3;
    } else if ( hour >= 19 ) {
      return 0.2;
    } else if ( hour >= 20 ) {
      return 0.1;
    } else {
      return 0.0;
    }
  }

  // Aceste grade sunt calculate ca și cum panoul solar este plat, adica la 180 °.
  public int calculateOptimalTimeOfDayAngle() {
    Calendar c = Calendar.getInstance();
    c.setTime(getDate());
    int hour = c.get(Calendar.HOUR_OF_DAY);
    if ( hour == 5 ) {
      setDirection("est");
      return 145;
    } else if ( hour == 6 ) {
      setDirection("est");
      return 150;
    } else if ( hour == 7 ) {
      setDirection("est");
      return 155;
    } else if ( hour == 8 ) {
      setDirection("est");
      return 160;
    } else if ( hour == 10 ) {
      setDirection("est");
      return 165;
    } else if ( hour == 11 ) {
      setDirection("est");
      return 170;
    } else if ( hour == 12 ) {
      setDirection("nord");
      return 180;
    } else if ( hour == 13 ) {
      setDirection("vest");
      return 175;
    } else if ( hour == 14 ) {
      setDirection("vest");
      return 170;
    } else if ( hour == 15 ) {
      setDirection("vest");
      return 165;
    } else if ( hour == 16 ) {
      setDirection("vest");
      return 160;
    } else if ( hour == 17 ) {
      setDirection("vest");
      return 155;
    } else if ( hour == 18 ) {
      setDirection("vest");
      return 150;
    } else if ( hour == 19 ) {
      setDirection("vest");
      return 145;
    } else if ( hour == 20 ) {
      setDirection("est");
      return 140;
    } else {
      setDirection("est");
      return 145;
    }
  }

  //Reprezinta iesirea: răsăritul și apusul constructorului SunriseSunset
  public void output() {
    System.out.println(getDate() + " officialSunrise : " + getOfficialSunrise());
    System.out.println(getDate() + " officialSunset  : " + getOfficialSunset());
  }
}
