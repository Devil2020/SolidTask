package comc.example.mohammedmorse.popularmoviesapp.Model;

/**
 * Created by Mohammed Morse on 29/06/2018.
 */

public class Trailer {
    private String Key;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    private String Name;
    private String Size;
    public Trailer(){}
    public Trailer(String k, String n, String s){
        this.Name=n;
        this.Size=s;
        this.Key=k;
    }
}
