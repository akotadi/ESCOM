import org.apache.commons.beanutils.PropertyUtils;

public class PropertyUtilsTest {

    public static void main(String args[]) {

        try{
        MobilePhone flexiColor = new MobilePhone();
        //here color and blue strings can come from variety or sources
        //e.g. configuration files, database, any upstream system or via HTTP Request
        PropertyUtils.setProperty(flexiColor, "color", "blue");
        String value = (String) PropertyUtils.getProperty(flexiColor, "color");
        System.out.println("PropertyUtils Example property value: " + value);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public static class MobilePhone {

        private String brand;
        private String color;

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
