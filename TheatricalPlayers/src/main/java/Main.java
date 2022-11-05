import java.util.Map;
import java.util.List;


public class Main{
    public static String resultat;
    public static void main(String[] args){
        
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

        InvoiceCalcul invoicecalcul = new InvoiceCalcul();

        resultat = invoicecalcul.toHTML(invoice, plays).toString();

        System.out.println(resultat);

    }
}