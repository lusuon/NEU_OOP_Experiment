import java.util.Iterator;

public final class PlainTextSalesFormatter implements SalesFormatter {

    private static  PlainTextSalesFormatter singletonInstance = new PlainTextSalesFormatter();
    // Constructor
    private PlainTextSalesFormatter(){}

    //Static method that obtains the single instance of class PlainTextsalesFormatter.
    static public PlainTextSalesFormatter getSingletonInstance(){
        return singletonInstance;
    }

    /*
    public String formatSales(Sales sales). Produces a string that contains the specified sales information in a plain-text format. Each order in the sales information has the following format:
    ------------------------
	Order number

	quantity1 code1 price1//使用toString方法
	quantity2 code2 price2
    ...
	quantityN codeN priceN

    Total = totalCost

    o	number is the order number.
    o	quantityX is the quantity of the product.
    o	codeX is the code of the product.
    o	priceX is the price of the product.
    o	totalCost is the total cost of the order.
    Each order should begin with a dashed line. The first order in the sales information should be given an order number of 1, the second should be given an order number of 2, and so on.
    如何获取其他类中的数组？
    */

    public String formatSales(Sales sales){
        int Ordernum;
        String OutputString;
        Ordernum=0;
        OutputString = "";
        for (Order temp_order:sales){
            OutputString+="------------------------"+"\n";
            Ordernum+=1;
            OutputString += "Order"+Ordernum+"\n";
            for (OrderItem temp_item:temp_order){
                OutputString += temp_item.toString()+"\n";
            }
            OutputString += "Total="+temp_order.getTotalCost()+"\n";
        }
        return OutputString;
        }
}

