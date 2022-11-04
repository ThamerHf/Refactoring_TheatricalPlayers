import java.text.NumberFormat;
import java.util.*;
import java.lang.StringBuilder;

public class InvoiceCalcul{
    public InvoiceCalcul(){}

    public StringBuilder toText(Invoice invoice, Map<String, Play> plays){
        float totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", invoice.customer));

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance perf : invoice.performances) {
            Play play = plays.get(perf.playID);

            // calcul the amount for this play type
            float thisAmount = play.playAmount(perf);

            if(thisAmount == -1.0){
                throw new Error("unknown type: ${play.type}");
            }

            // add volume credits
            volumeCredits += play.playCredit(perf);

            // print line for this order
            result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
            totalAmount += thisAmount;
        }
        result.append(String.format("Amount owed is %s\nYou earned %s credits\n", frmt.format(totalAmount), volumeCredits));
        return result;
    }
    
}