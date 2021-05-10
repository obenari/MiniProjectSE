package renderer;

import elements.Camera;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void testWriteToImage() {
        ImageWriter imageWriter=new ImageWriter("module check 1", 800,500);
        Scene scene=new Scene("module check 2");
        scene.setBackground(new Color(0,0,255));
        Camera camera=new Camera(Point3D.ZERO,new Vector(0,0,1), new Vector(1,0,0))
                .setDistance(2)
                .setViewPlaneSize(800, 500);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));

        render.renderImage();
        render.printGrid(50, new Color(java.awt.Color.PINK));
        render.writeToImage();
    }
}