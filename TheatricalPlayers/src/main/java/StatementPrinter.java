//import java.text.NumberFormat;
import java.util.*;
import java.lang.StringBuilder;

public class StatementPrinter {

/********************************************************************/
/*print: generate an invoice in text form for a given customer		*/
/*                                                                	*/
/*Input: invoice and a Map contains plays	                      	*/
/*                                                                	*/
/*Output: a String representing the invoice                 		*/
/********************************************************************/
  public StringBuilder print(Invoice invoice, Map<String, Play> plays) {
    StringBuilder result = new InvoiceCalcul().toText(invoice, plays);
    return result;
  }

}
