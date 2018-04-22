public class Coffee extends Product {
    private String origin;
    private String roast;
    private String flavor;
    private String aroma;
    private String acidity;
    private String body;

    //code等参数若继承自Product.class ，是否要写在子类的构造函数内？
    public Coffee(String initCode,String initDescription,double initPrice,String initOrigin,String initRoast,String initFlavor,String initAroma,String initAcidity,String initBody){
        code=initCode;
        description=initDescription;
        price=initPrice;
        origin=initOrigin;
        roast=initRoast;
        flavor=initFlavor;
        aroma=initAroma;
        acidity=initAcidity;
        body=initBody;
    }

    public String getOrigin() {
        return origin;
        };
    public String getRoast() {
        return roast;
    };
    public String getFlavor() {
        return flavor;
    };
    public String getAroma() {
        return aroma;
    };
    public String getAcidity() {
        return acidity;
    };
    public String getBody() {
        return body;
    }

}
