package coding_problems_set.websites.educative;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.function.*;
import java.util.regex.Pattern;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

import java.time.*;

public class Dec_20_2024_1_Features_FunctionP {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[, ]+");
        pattern.splitAsStream("a, b, c").forEach(System.out::println);

        Function<Integer,String> f = Function.<Integer>identity()
                .andThen(i -> 2*i).andThen(i -> "Haixia's -> " + i);

        System.out.println(
                Stream.iterate(1, i -> i + 1)
                        .limit(10)
                        .map(f)
                        .collect(joining(","))
        );


        Function<LocalDate,LocalDateTime> plusTwoM = Function.<LocalDate>identity()
                .andThen(dateTimeFunction(d -> d.plusMonths(2)));

        System.out.println(
                Stream.iterate(LocalDate.now(), d -> d.plusDays(1))
                        .limit(10)
                        .map(plusTwoM)
                        .map(Object::toString)
                        .collect(joining(", "))
        );
    }

    public static Function<LocalDate,LocalDateTime> dateTimeFunction(final Function<LocalDate,LocalDate> f) {
        return f.andThen(d -> d.atTime(2, 2));
    }
}
