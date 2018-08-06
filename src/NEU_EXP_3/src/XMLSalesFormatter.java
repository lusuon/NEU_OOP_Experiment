import java.util.Iterator;

public class XMLSalesFormatter implements SalesFormatter{


    private static  XMLSalesFormatter singletonInstance = new XMLSalesFormatter();

    private XMLSalesFormatter(){}
    static public XMLSalesFormatter getSingletonInstance(){
        return singletonInstance;
    }


/*
•	public String formatSales(Sales sales). Produces a string that contains the specified sales information in an XML format.
•	The string should begin with the following XML:
<Sales>
•	Each order in the sales information should have the following format:
•	  <Order total="totalCost">
    <OrderItem quantity="quantity1" price="price1">code1</OrderItem>
    ...
    <OrderItem quantity="quantityN" price="priceN">codeN</OrderItem>
  </Order>
where:
o	quantityX is the quantity of the product.
o	codeX is the code of the product.
o	priceX is the price of the product.
o	totalCost is the total cost of the order.
•	The string should end with the following XML:
</Sales>
 */
    @Override
    public String formatSales(Sales sales) {
        int Ordernum;
        int Total_cost;
        String OutputString;
        Ordernum = 0;
        OutputString = "<Sales>\n";
        for (Order temp_order : sales) {
            OutputString += "<Order total=\""+temp_order.getTotalCost()+"\">\n";
            for (OrderItem temp_item : temp_order) {
                OutputString += "<OrderItem quantity=\""+temp_item.getQuantity()+"\" price=\""+temp_item.getProduct().getPrice()+"\">"+temp_item.getProduct().getCode()+"</OrderItem>\n";
            }
            OutputString+="</Order>\n";
        }
        OutputString += "</Sales>\n";
        return OutputString;
    }
}
