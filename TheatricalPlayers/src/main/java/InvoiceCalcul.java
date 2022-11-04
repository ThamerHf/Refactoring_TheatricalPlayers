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
        float thisAmount = 0;

        switch (play.type) {
            case "tragedy":
            thisAmount = play.playAmount(perf);
            break;
            case "comedy":
            thisAmount = play.playAmount(perf);
            break;
            default:
            throw new Error("unknown type: ${play.type}");
        }

        // add volume credits
        volumeCredits += Math.max(perf.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

        // print line for this order
        result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
        totalAmount += thisAmount;
        }
        result.append(String.format("Amount owed is %s\nYou earned %s credits\n", frmt.format(totalAmount), volumeCredits));
        return result;
    }

    public StringBuilder toHTML(Invoice invoice, Map<String, Play> plays){
        float totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("<html> \n<head> Invoice </head> \n<body>\n <h1> Invoice </h1>\n"));

        result.append(String.format("<ul> <li>", invoice.customer ,"</li><ul>"));
        
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance perf : invoice.performances) {
        Play play = plays.get(perf.playID);
        float thisAmount = 0;

        switch (play.type) {
            case "tragedy":
            thisAmount = play.playAmount(perf);
            break;
            case "comedy":
            thisAmount = play.playAmount(perf);
            break;
            default:
            throw new Error("unknown type: ${play.type}");
        }

        // add volume credits
        volumeCredits += Math.max(perf.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

        // print line for this order
        result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
        totalAmount += thisAmount;
        }
        result.append(String.format("Amount owed is %s\nYou earned %s credits\n", frmt.format(totalAmount), volumeCredits));
        return result;
    }
    
}