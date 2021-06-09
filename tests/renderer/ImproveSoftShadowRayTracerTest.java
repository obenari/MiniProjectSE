package renderer;

import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.*;

class ImproveSoftShadowRayTracerTest {
    @Test
    public void SoftShadow(){
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(50, new Point3D(30, 0, -150)) //
                        .setEmission(new Color(100,0,0)) //
                        .setMaterial(new Material().setkD(0.5).setkR(0.1).setkS(0.4).setnShininess(80)) //
                ,new Plane( new Point3D(30, -60, -150),new Vector(0,1,0)) //
                        .setEmission(new Color(20,0,0)) //
                        .setMaterial(new Material().setkD(0.5).setkR(0.4).setnShininess(80))
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 0), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0),new Point3D(0,0,50) ,new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("softShadow", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveSoftShadowRayTracer(scene).setRADIUS(100));
        render.renderImage();
        render.writeToImage();
    }

}