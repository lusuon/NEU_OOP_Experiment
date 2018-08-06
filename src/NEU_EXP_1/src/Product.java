

public class Product {
    //声明实例变量
    private String code;
    private String description;
    private double price;

    //Constructor Method 构造函数
    public Product(String initCode, String initDescription, double initPrice) {
        code = initCode;
        description = initDescription;
        price = initPrice;
    }

    //Accessor Method  访问方法
    public String getCode() {
        return this.code;
    }
    public String getDescription() {
        return this.description;
    }
    public double getPrice() {
        return this.price;
    }

    //If two objects have the same variable code,but each of them is not the instance of Product class,or worse,their class are not the same,how to avoid the "True" output?
    public boolean equals(Object obj) {
        if (obj.getClass().getName()== this.getClass().getName()){
            //Object类没有getCode(),除了强制转换还有什么可以获取code属性
            Product TransObj =(Product) obj;
            return TransObj.getCode() == this.getCode();
        }else{
            return false;

        /* //不适用于有子类后的情况
        if(obj instanceof Product) {
            Product TransObj =(Product) obj;
            return TransObj.getCode() == this.getCode();
        }else{
            return false；
            */
        }
    }

    public String toString() {
        return this.getCode()+"_"+this.getDescription()+"_"+this.getPrice();
    }
}

