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

  // SunriseSunset constructor fără arg, doar inițializează câmpurile.
  public SunriseSunset() {
    this.location = new Location("28.52", "47");
    this.calculator = new SunriseSunsetCalculator(location, TimeZone.getTimeZone("GMT+3"));
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
    this.officialSunrise = calculator.getOfficialSunriseForDate(Calendar.getInstance());
  }

  public String getOfficialSunrise() {
    return officialSunrise;
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

  // Verificări pentru emisfera setata, pentru a testa daca unghiul total al panoului este corect pentru sezonul setat se  verifica data curentă comparativ cu datele stocate. Setez intervalul corespunzator anotimpurilor
  public String preiaDataCalendar() {
    Calendar min = Calendar.getInstance();
    Calendar max = Calendar.getInstance();
    Calendar timpCurent = Calendar.getInstance();
    min.set(getDate().getYear(), Calendar.JUNE, 21);
    max.set(getDate().getYear(), Calendar.SEPTEMBER, 22);
    if ( timpCurent.before(max) && timpCurent.after(min) ) {
      return "vara";       // Ajustare la unghiul de vara
    }
    min.set(getDate().getYear(), Calendar.SEPTEMBER, 22);
    max.set(getDate().getYear(), Calendar.DECEMBER, 21);
    if ( timpCurent.before(max) && timpCurent.after(min) ) {
      return "toamna";       // Ajustare la unghiul de toamna
    }
    min.set(getDate().getYear(), Calendar.DECEMBER, 21);
    max.set(getDate().getYear(), Calendar.MARCH, 20);
    if ( timpCurent.before(max) && timpCurent.after(min) ) {
      return "iarna";       // Ajustare la unghiul de iarna
    }
    min.set(getDate().getYear(), Calendar.MARCH, 20);
    max.set(getDate().getYear(), Calendar.JUNE, 21);
    if ( timpCurent.before(max) && timpCurent.after(min) ) {
    }
    return "primavara";       // Ajustare la unghiul de primavara
  }

  // Funcția  care returnează o valoare fuzzy în funcție de ora cu care putem obține cel mai mare randament al panoului.
  public double timeOfDayFuzzyfication() {
    // Input - ora, output - fuzzy value
    Calendar calendarData = Calendar.getInstance();
    calendarData.setTime(getDate());
    int ora = calendarData.get(Calendar.HOUR_OF_DAY);
    if ( ora >= 5 ) {
      return 0.0;
    } else if ( ora >= 6 ) {
      return 0.1;
    } else if ( ora >= 7 ) {
      return 0.3;
    } else if ( ora >= 8 ) {
      return 0.5;
    } else if ( ora >= 10 ) {
      return 0.7;
    } else if ( ora >= 11 ) {
      return 0.9;
    } else if ( ora >= 12 ) {
      return 1.0;
    } else if ( ora >= 13 ) {
      return 0.9;
    } else if ( ora >= 14 ) {
      return 0.8;
    } else if ( ora >= 15 ) {
      return 0.7;
    } else if ( ora >= 16 ) {
      return 0.6;
    } else if ( ora >= 17 ) {
      return 0.4;
    } else if ( ora >= 18 ) {
      return 0.3;
    } else if ( ora >= 19 ) {
      return 0.2;
    } else if ( ora >= 20 ) {
      return 0.1;
    } else {
      return 0.0;
    }
  }

  // Aceste grade sunt calculate ca și cum panoul solar este plat, adica la 180, de asemenea panoul se roteste intr 140 si 180°.
  public int optimalTimeOfDayAngle() {
    Calendar calendarData = Calendar.getInstance();
    calendarData.setTime(getDate());
    int ora = calendarData.get(Calendar.HOUR_OF_DAY);
    if ( ora == 5 ) {
      setDirection("est");
      return 145;
    } else if ( ora == 6 ) {
      setDirection("est");
      return 150;
    } else if ( ora == 7 ) {
      setDirection("est");
      return 155;
    } else if ( ora == 8 ) {
      setDirection("est");
      return 160;
    } else if ( ora == 10 ) {
      setDirection("est");
      return 165;
    } else if ( ora == 11 ) {
      setDirection("est");
      return 170;
    } else if ( ora == 12 ) {
      setDirection("nord");
      return 180;
    } else if ( ora == 13 ) {
      setDirection("vest");
      return 175;
    } else if ( ora == 14 ) {
      setDirection("vest");
      return 170;
    } else if ( ora == 15 ) {
      setDirection("vest");
      return 165;
    } else if ( ora == 16 ) {
      setDirection("vest");
      return 160;
    } else if ( ora == 17 ) {
      setDirection("vest");
      return 155;
    } else if ( ora == 18 ) {
      setDirection("vest");
      return 150;
    } else if ( ora == 19 ) {
      setDirection("vest");
      return 145;
    } else if ( ora == 20 ) {
      setDirection("est");
      return 140;
    } else {
      setDirection("est");
      return 145;
    }
  }

  //Reprezinta iesirea: răsăritul și apusul constructorului SunriseSunset
  public void iesire() {
    System.out.println("Data: " + getDate() + " Rasarit : " + getOfficialSunrise());
    System.out.println("Data: " + getDate() + " Apus  : " + getOfficialSunset());
  }
}
