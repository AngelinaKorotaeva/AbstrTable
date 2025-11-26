package tabZaznamu;

public class Zaznam {
    private int ID;
    private String jmeno;

    public Zaznam(int ID, String jmeno) {
        this.ID = ID;
        this.jmeno = jmeno;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    @Override
    public String toString() {
        return "Zaznam " + ID + ", jmeno: " + jmeno;
    }
    
}
