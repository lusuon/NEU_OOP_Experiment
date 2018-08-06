import java.util.Iterator;
    //Class HTMLSalesFormatter implements the interface SalesFormatter.
    //This class is implemented as a singleton so a new object will not be created every time the HTML format is used.
    public class HTMLSalesFormatter implements SalesFormatter {
     //Static variable——singletonInstance. The single instance of class HTMLSalesFormatter.
     private static  HTMLSalesFormatter singletonInstance = new HTMLSalesFormatter();
    //Constructor and methods:
    //private HTMLSalesFormatter(). Constructor that is declared private so it is inaccessible to other classes. A private constructor makes it impossible for any other class to create an instance of class HTMLSalesFormatter.
    private HTMLSalesFormatter(){}
    //static public HTMLSalesFormatter getSingletonInstance(). Static method that obtains the single instance of class HTMLSalesFormatter.
    static public HTMLSalesFormatter getSingletonInstance(){
        return singletonInstance;
    }

    /*
•	public String formatSales(Sales sales). Produces a string that contains the specified sales information in an HTML format.
•	The string should begin with the following HTML:
•	<html>
•	  <body>
    <center><h2>Orders</h2></center>
•	Each order in the sales information should begin with horizontal line, that is, an <hr> tag.
•	Each order in the sales information should have the following format:
    <hr>
    <h4>Total = totalCost</h4>
      <p>
        <b>code:</b> code1<br>
        <b>quantity:</b> quantity1<br>
        <b>price:</b> price1
     </p>
	  ...
     <p>
        <b>code:</b> codeN<br>
        <b>quantity:</b> quantityN<br>
        <b>price:</b> priceN
     </p>
where:
o	quantityX is the quantity of the product.
o	codeX is the code of the product.
o	priceX is the price of the product.
o	totalCost is the total cost of the order.
•	The string should end with the following HTML:
  </body>
</html>
     */

    @Override
    public String formatSales(Sales sales) {
        int Ordernum;
        int Total_cost;
        String OutputString;
        Ordernum = 0;
        OutputString =
                "<html>\n" +
                        "\t<body>\n" +
                        "\t\t<center><h2>Orders</h2></center>\n";
        for (Order temp_order : sales) {
            Ordernum += 1;
            OutputString +=
                    "\t\t<hr>\n" +
                    "\t\t<h4>Total = " + temp_order.getTotalCost() + "</h4>\n";
            for (OrderItem temp_item : temp_order) {
                OutputString +=
                        "\t\t\t<p>\n" +
                                "\t\t\t\t<b>code:</b>" + temp_item.getProduct().getCode() + "<br>\n" +
                                "\t\t\t\t<b>quantity:</b>" + temp_item.getQuantity() + "<br>\n" +
                                "\t\t\t\t<b>price:</b>" + temp_item.getProduct().getPrice() + "<br>\n" +
                                "\t\t\t</p>\n";
            }
        }
        OutputString +=
                "\t</body>\n" +
                        "</html>";
        return OutputString;
    }
}