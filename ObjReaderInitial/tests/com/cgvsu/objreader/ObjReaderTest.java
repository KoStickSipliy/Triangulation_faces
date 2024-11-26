package com.cgvsu.objreader;

import com.cgvsu.Triangulation;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Polygon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.util.ArrayList;
import java.util.Arrays;

class ObjReaderTest {

    @Test
    public void testParseVertex01() {
        final ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02", "1.03"));
        final Vector3f result = ObjReader.parseVertex(wordsInLineWithoutToken, 5);
        final Vector3f expectedResult = new Vector3f(1.01f, 1.02f, 1.03f);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex02() {
        final ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("ab", "o", "ba"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
            Assertions.fail();

        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Failed to parse float value.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex03() {
        final ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0", "2.0"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
            Assertions.fail();

        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too few vertex arguments.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex04() {
        // АГААА! Вот тест, который говорит, что у метода нет проверки на более, чем 3 числа
        // А такой случай лучше не игнорировать, а сообщать пользователю, что у него что-то не так
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0", "2.0", "3.0", "4.0"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
            Assertions.fail();

        } catch (ObjReaderException exception) {
            String expectedError = "";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testTriangulatePolygon1 () {
        Polygon polygon = new Polygon();
        ArrayList<Integer> vertex = new ArrayList<>();
        for (int i = 3; i < 9; i++) {
            vertex.add(i);
        }
        polygon.setVertexIndices(vertex);
        ArrayList<Polygon> faces = Triangulation.triangulatePolygon(polygon);
        ArrayList<Integer> real = new ArrayList<>();
        for (int i = 0; i < faces.size(); i++) {
            real.add(faces.get(i).getVertexIndices().get(0));
            real.add(faces.get(i).getVertexIndices().get(1));
            real.add(faces.get(i).getVertexIndices().get(2));
        }

        ArrayList<Integer> expected = new ArrayList<>();
        for (int i = 4; i < 8; i++) {
            expected.add(3);
            expected.add(i);
            expected.add(i + 1);
        }
        Assertions.assertEquals(expected, real);

//        ArrayList<Polygon> expectedFaces = new ArrayList<>();
//        for (int i = 4; i < 8; i++) {
//            ArrayList<Integer> expectedVertex = new ArrayList<>();
//            Polygon p = new Polygon();
//            expectedVertex.add(3); expectedVertex.add(i); expectedVertex.add(i + 1);
//            p.setVertexIndices(expectedVertex);
//            expectedFaces.add(p);
//        }
     }

    @Test
    public void testTriangulatePolygon () {
        //создание полигона и его триангулция
        Polygon polygon = new Polygon();
        ArrayList<Integer> vertex = new ArrayList<>();
        for (int i = 3; i < 8; i++) {
            vertex.add(i);
        } polygon.setVertexIndices(vertex);
        ArrayList<Polygon> faces = Triangulation.triangulatePolygon(polygon);

        // создание ожидаемого триангулированного полигона
        ArrayList<Polygon> expectedFaces = new ArrayList<>();
        for (int i = 4; i < 8; i++) {
            ArrayList<Integer> expectedVertex = new ArrayList<>();
            Polygon p = new Polygon();

            expectedVertex.add(3); expectedVertex.add(i); expectedVertex.add(i + 1);
            p.setVertexIndices(expectedVertex);
            expectedFaces.add(p);
        }
    }

    @Test
    public void testTriangulateModel () {

    }
}