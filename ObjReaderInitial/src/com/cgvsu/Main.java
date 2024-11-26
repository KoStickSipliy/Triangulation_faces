package com.cgvsu;

import com.cgvsu.model.Model;
import com.cgvsu.Triangulation;
import com.cgvsu.objreader.ObjReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {

        Path fileName = Path.of("C:\\Users\\linar\\Desktop\\_Костяцкая папка\\__YЧЁБА\\CG\\CGVSU-main\\CGVSU-main\\3DModels\\Faceform\\WrapHand.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());

        Model newModel = new Model();
        newModel.vertices = model.vertices;
        newModel.normals = model.normals;
        newModel.textureVertices = model.textureVertices;
        newModel.polygons = Triangulation.triangulateModel(model.polygons);

        System.out.println("\nПосле триангуляции:\nVertices: " + newModel.vertices.size());
        System.out.println("Texture vertices: " + newModel.textureVertices.size());
        System.out.println("Normals: " + newModel.normals.size());
        System.out.println("Polygons: " + newModel.polygons.size());
    }
}
