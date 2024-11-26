package com.cgvsu;

import com.cgvsu.model.Polygon;

import java.util.ArrayList;

public class Triangulation {
    public static ArrayList<Polygon> triangulatePolygon (Polygon poly) {
        int vertexNum = poly.getVertexIndices().size();
        ArrayList<Polygon> polygons = new ArrayList<Polygon>();

        for (int i = 2; i < vertexNum - 1; i++) {
            ArrayList<Integer> vertex = new ArrayList<>();
            vertex.add(0); vertex.add(i); vertex.add(i - 1);

            Polygon currPoly = new Polygon();
            currPoly.setNormalIndices(vertex);
            polygons.add(currPoly);
        }
        if (vertexNum > 3) { // последний треугольник
            ArrayList<Integer> vertex = new ArrayList<>();
            vertex.add(0); vertex.add(vertexNum - 1); vertex.add(vertexNum - 2);

            Polygon currPoly = new Polygon();
            currPoly.setNormalIndices(vertex);
            polygons.add(currPoly);
        }

        return polygons;
    }
    public static ArrayList<Polygon> triangulateModel (ArrayList<Polygon> polygons){
        ArrayList<Polygon> newModelPoly = new ArrayList<Polygon>();

        for (int i = 0; i < polygons.size(); i++) {
            if (polygons.get(i).getVertexIndices().size() < 4) {
                newModelPoly.add(polygons.get(i));
                continue;
            }
            newModelPoly.addAll(
                    triangulatePolygon(polygons.get(i))
            );
        }
        return newModelPoly;
    }
}
