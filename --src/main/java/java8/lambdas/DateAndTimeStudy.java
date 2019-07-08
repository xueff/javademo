package java8.lambdas;

import java.time.*;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1917:20
 */
public class DateAndTimeStudy {

    public static void main(String[] args) {
        //-------当前时间处理
        LocalDateTime coffeeBreak = LocalDateTime.now()
                .plusHours(2)
                .plusMinutes(30);


        //区域日期
        ZoneId london = ZoneId.of("Europe/London");
        LocalDate july4 = LocalDate.of(2014, Month.JULY, 4);
        LocalTime early = LocalTime.parse("08:45");
        ZonedDateTime flightDeparture = ZonedDateTime.of(july4, early,london);
        System.out.println(flightDeparture);


        LocalTime from = LocalTime.from(flightDeparture);
        System.out.println(from);

        ZonedDateTime touchDown
                = ZonedDateTime.of(july4,
                LocalTime.of (11, 35),
                ZoneId.of("Europe/Stockholm"));
        Duration flightLength = Duration.between(flightDeparture, touchDown);
        System.out.println(flightLength);
// How long have I been in continental Europe?
        ZonedDateTime now = ZonedDateTime.now();
        Duration timeHere = Duration.between(touchDown, now);
        System.out.println(timeHere);
//        This code will produce an output similar to this:
//        2015-07-04T08:45+01:00[Europe/London]
//        08:45
//        PT1H50M
//        PT269H46M55.736S
    }
}
