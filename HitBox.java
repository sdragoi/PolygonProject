// HitBox Class: Used for collision detection
// Aimed Completion Date: May 23, 2022

package com.example.polyproj;

public class HitBox {
    public  double width;
    public  double height;
    public  double x;
    public  double y;
    public HitBox (double x, double y, double width, double height) {
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
     }
    
     //Using Separating Axis Theorem to see if objects overlap
     public boolean overlaps (HitBox otherHitbox) {
       boolean noOverlap =
               this.x + this.width < otherHitbox.x ||
               otherHitbox.x + otherHitbox.width < this.x ||
               this.y + this.height < otherHitbox.y ||
               otherHitbox.y + otherHitbox.height < this.y;
       return !noOverlap;
     }
}
