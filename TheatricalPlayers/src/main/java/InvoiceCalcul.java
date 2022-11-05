import java.text.NumberFormat;
import java.util.*;
import java.lang.StringBuilder;

public class InvoiceCalcul{
    public InvoiceCalcul(){}
    /********************************************************************/
    /* La methode toText() permet de génerer le résultat en format text */
    /********************************************************************/

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

    public StringBuilder toHTML(Invoice invoice, Map<String, Play> plays){
        float totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("<html> \n<head> <title> Invoice </title></head> \n<body>\n <h1> Invoice </h1>\n"));

        result.append(String.format("<ul> <li> <b> Client : </b> %s </li> </ul> \n", invoice.customer));
        result.append("<table border='1'> \n <tr> \n <td> Piece </td> \n <td> Seats sold </td> \n <td> Price </td> \n </tr> \n");
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

        // print line for this order
        result.append(String.format("<tr> \n <td> %s </td> \n <td> %d </td> \n <td> $%s </td> \n </tr> \n", play.name, perf.audience, frmt.format(thisAmount)));
        
        // add volume credits
        volumeCredits += Math.max(perf.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

        totalAmount += thisAmount;
        }
        result.append(String.format("<tr> \n <td colspan = '2'> <b>Total Owned :</b> </td> \n <td> $%s </td> \n </tr> \n", frmt.format(totalAmount)));
        result.append(String.format("<tr> \n <td colspan = '2'> <b>Fidelity Points Earned :</b> </td> \n <td> %d </td> \n </tr> \n" ,volumeCredits));
        result.append("</table> \n </body> \n </html>");
        return result;
    }
    
}