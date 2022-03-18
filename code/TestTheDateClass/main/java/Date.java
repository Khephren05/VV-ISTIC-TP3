import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Comparable<Date> {

    private Calendar calendar = new GregorianCalendar();

    /**
     * Constructeur vide, établi la date au 1er janvier 2000
     */
    public Date() {
        this.calendar.set(2000, 0, 1);
    }

    /**
     * Constructeur paramétré, si les données entrées sont corrects, il créé une date en fonction de celles-ci.
     *
     * @param day   int jour de la date à créer
     * @param month int mois de la date à créer
     * @param year  int année de la date à créer
     * @throws WrongDateException exception levée si les données ne correspondent pas à une date valide.
     */
    public Date(int day, int month, int year) throws WrongDateException {
        if (isValidDate(day, month, year)) {
            this.calendar.set(year, month - 1, day);
        } else {
            throw new WrongDateException("The given date isn't valid");
        }
    }

    /**
     * Getter getDay
     *
     * @return int le jour de la date ciblée
     */
    public int getDay() {
        return this.calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Getter getMonth
     *
     * @return int le mois de la date ciblée
     */
    public int getMonth() {
        return this.calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * Getter getYear
     *
     * @return int l'année de la date ciblée
     */
    public int getYear() {
        return this.calendar.get(Calendar.YEAR);
    }

    /**
     * Fonction permettant de vérifier la validité d'une date
     *
     * @param day   int le jour de la date
     * @param month int le mois de la date
     * @param year  int l'année de la date
     * @return boolean, vrai si la date est correcte.
     */
    public static boolean isValidDate(int day, int month, int year) {
        if (isMonthValid(month)) { //P1
            switch (month) { //P2
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    return day > 0 && day < 32; //B1
                case 2:
                    if (isLeapYear(year)) { //P3
                        return day > 0 && day < 30; //B2
                    } else {
                        return day > 0 && day < 29; //B3
                    }
                case 4:
                case 6:
                case 9:
                case 11:
                    return day > 0 && day < 31; //B4
                default:
                    return false; //B5
            }
        }
        return false; //B6
    }

    /**
     * Fonction permettant de vérifier si une année est bissextile
     *
     * @param year int l'année en question
     * @return boolean, vrai si l'année est bissextile
     */
    public static boolean isLeapYear(int year) {
        GregorianCalendar cal = new GregorianCalendar(year, 1, 1);
        return cal.isLeapYear(year);
    }

    /**
     * Fonction renvoyant le jour suivant d'une date sous format Date
     *
     * @return Date, le jour suivant
     * @throws WrongDateException exception levée si la date du jour suivant n'est pas valide.
     */
    public Date nextDate() throws WrongDateException {
        Calendar result = new GregorianCalendar(this.getYear(), this.getMonth() - 1, this.getDay());
        result.add(Calendar.DATE, 1);
        return new Date(result.get(Calendar.DAY_OF_MONTH), result.get(Calendar.MONTH) + 1, result.get(Calendar.YEAR));
    }

    /**
     * Fonction renvoyant le jour précédent d'une date sous format Date
     *
     * @return Date, le jour précédent
     * @throws WrongDateException exception levée si la date du jour précédent n'est pas valide.
     */
    public Date previousDate() throws WrongDateException {
        Calendar result = new GregorianCalendar(this.getYear(), this.getMonth() - 1, this.getDay());
        result.add(Calendar.DATE, -1);
        return new Date(result.get(Calendar.DAY_OF_MONTH), result.get(Calendar.MONTH) + 1, result.get(Calendar.YEAR));
    }

    public int compareTo(Date other) {
        return this.calendar.compareTo(other.getCalendar());
    }

    /**
     * Fonction privée permettant de vérifier si un mois est valide
     *
     * @param month int, doit se trouver entre 1 et 12 inclus
     * @return boolean, vrai si le mois est correct selon le calendrier grégorien
     */
    private static boolean isMonthValid(int month) {
        return (month > 0 && month < 13);
    }

    /**
     * Fonction privée permettant de récupérer le calendar d'une date ciblée.
     *
     * @return Calendar de la date ciblée
     */
    private Calendar getCalendar() {
        return calendar;
    }
}