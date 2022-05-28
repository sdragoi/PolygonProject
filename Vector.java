//JM MS
//APCS Final project
//Use Vectors for positioning
//5/23/2022
package application;

//vector class is used to determine position and velocity of a shape
public class Vector {
    double x;
    double y;
    public Vector(double x, double y) {
        set(x,y);
    }

    public void set (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set (double y) {
        this.y = y;
    }


    public void add (double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    //Vector addition
    public void add (Vector Vec) {
        this.add(Vec.x, Vec.y);
    }
    //scalar multiplication
    public void scalarMult (double n) {
        this.x *= n;
        this.y *=n;
    }
}
