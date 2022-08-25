package math;

import math.Vector;

public class Vectors {
    //Скалярное произведение
    public static double dot(Vector vector1, Vector vector2){
        return vector1.getX()*vector2.getX() + vector1.getY()*vector2.getY() + vector1.getZ()*vector2.getZ();
    }
    //Векторное произведение
    public static Vector cross(Vector vector1, Vector vector2){
        return new Vector(vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY(),
                vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ(),
                vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX());
    }

}
