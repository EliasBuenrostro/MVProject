public class DocumentInfo {
    String name;
    double kincaid, flesch;
    public DocumentInfo(String name, double kincaid, double flesch){
        this.name = name;
        this.kincaid = kincaid;
        this.flesch = flesch;
    }
    public String getName(){
        return name;
    }
    public double getKincaid(){
        return kincaid;
    }
    public double getFlesch(){
        return flesch;
    }
}
