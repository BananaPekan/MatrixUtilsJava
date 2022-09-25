package banana.pekan.projection;

public class PVector {

    public float x = 0;
    public float y = 0;
    public float z = 0;

    public int dim = 2;

    public PVector() {

    }

    public PVector(float x) {
        this.x = x;
    }

    public PVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PVector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dim = 3;
    }

    public boolean hasZ() {
        return dim >= 3;
    }



}
