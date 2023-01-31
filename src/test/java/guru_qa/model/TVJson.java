package guru_qa.model;

public class TVJson {

    public int warranty;
    public int price;
    public String voiceSupport;
    public String[] audioFormat;
    public Model model;

    public static class Model {
        public String name;
        public String series;
    }
}