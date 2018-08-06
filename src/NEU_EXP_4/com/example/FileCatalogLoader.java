import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Lawrence on 15/10/31.
 */
public class FileCatalogLoader implements CatalogLoader {
    public static String path = "./";
    public FileCatalogLoader() {

    }
    private Product readProduct (String line) throws DataFormatException {
        StringTokenizer st = new StringTokenizer(line, "_");
        String code,description;
        double price;
        //如果用Arraylist储存token，按下标分配，会引发null错误，为什么？
        if (st.countTokens() != 4) {
            throw new DataFormatException(line);
        } else {
            st.nextToken();
            code = st.nextToken();
            description = st.nextToken();
            price = Double.parseDouble(st.nextToken());
        }//无IO等错误捕获要求，暂时不做catch处理
            return new Product(code, description, price);
    }
    private Coffee readCoffee(String line) throws DataFormatException {
        StringTokenizer st = new StringTokenizer(line, "_");
        String code, description, origin, roast, flavor, aroma, acidity, body;
        double price;
        if (st.countTokens()!=10){
            throw new DataFormatException(line);
        }else {
            st.nextToken();
            code = st.nextToken();
            description = st.nextToken();
            price = Double.parseDouble(st.nextToken());
            origin = st.nextToken();
            roast = st.nextToken();
            flavor = st.nextToken();
            aroma = st.nextToken();
            acidity = st.nextToken();
            body = st.nextToken();
        }
        return new Coffee(code, description, price, origin, roast, flavor, aroma, acidity, body);
    }

    private CoffeeBrewer readCoffeeBrewer(String line) throws DataFormatException{
        StringTokenizer st = new StringTokenizer(line, "_");
        String code, description, model, waterSupply;
        double price;
        int numberOfCups;
        if (st.countTokens()!=7){
            throw new DataFormatException(line);
        }else {
            st.nextToken();
            code = st.nextToken();
            description = st.nextToken();
            price = Double.parseDouble(st.nextToken());
            model = st.nextToken();
            waterSupply = st.nextToken();
            numberOfCups = Integer.parseInt(st.nextToken());
        }
        return new CoffeeBrewer(code, description, price, model, waterSupply, numberOfCups);
    }
    public Catalog loadCatalog(String filename) throws FileNotFoundException, IOException, DataFormatException {
        Catalog catalog = new Catalog();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        //将line=br.readLine（）置于循环前将引发死循环，可能是在不断读取下一行无法结束所造成
        //只能直接至于while判定条件赋值？
        while((line=br.readLine())!=null) {
            Object product;
            if (line.startsWith("Brewer")) {
                product = this.readCoffeeBrewer(line);
            } else if (line.startsWith("Coffee")) {
                product = this.readCoffee(line);
            } else {
                if (line.startsWith("Product")==false) {
                    throw new DataFormatException(line);
                }
                product = this.readProduct(line);
            }
            catalog.addProduct((Product) product);
        }
        br.close();
        return catalog;
    }
}