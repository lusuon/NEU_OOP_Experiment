public class Coffee extends Product{
    private String origin;
    private String roast;
    private String flavor;
    private String aroma;
    private String acidity;
    private String body;

    Coffee(String initCode,String initDescription,double initPrice,String initOrigin,String initRoast,String initFlavor,String initAroma,String initAcidity,String initBody){
        super(initCode,initDescription,initPrice);
        origin = initOrigin;
        roast = initRoast;
        flavor = initFlavor;
        aroma = initAroma;
        acidity = initAcidity;
        body = initBody;
    }

    public String getOrigin() {
        return this.origin;
    }
    public String getRoast() {
        return this.roast;
    }
    public String getFlavor() {
        return this.flavor;
    }
    public String getAroma() {
        return this.aroma;
    }
    public String getAcidity() {
        return this.acidity;
    }
    public String getBody() {
        return this.body;
    }

    public String toString() {
        return this.getCode()+"_"+this.getDescription()+"_"+this.getPrice()+"_"+this.getOrigin()+"_"+this.getRoast()+"_"+this.getFlavor()+"_"+this.getAroma()+"_"+this.getAcidity()+"_"+this.getBody();
    }
}
