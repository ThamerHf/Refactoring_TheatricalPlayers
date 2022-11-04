import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {
        Play play1 = new PlayTragedy("Hamlet");
        Play play2 = new PlayComedy("As You Like It");
        Play play3 = new PlayTragedy("Othello");

        Map<String, Play> plays = Map.of(
                play1.name, play1,
                play2.name, play2,
                play3.name, play3);

        Invoice invoice = new Invoice("BigCo", List.of(
            new Performance(play1.name, 55),
            new Performance(play2.name, 35),
            new Performance(play3.name, 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);
    }

    @Test
    void statementWithNewPlayTypes() {
        Play play1 = new Play("Henry V", "history");
        Play play2 = new Play("As You Like It", "pastoral");

        Map<String, Play> plays = Map.of(
            play1.name, play1,
            play2.name, play2);

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance(play1.name, 53),
                new Performance(play2.name, 55)));

        StatementPrinter statementPrinter = new StatementPrinter();
        Assertions.assertThrows(Error.class, () -> {
            statementPrinter.print(invoice, plays);
        });
    }
}
