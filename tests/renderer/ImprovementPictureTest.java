package renderer;

import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

public class ImprovementPictureTest {
    @Test
    public void diamond() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(-2, 2, 0.5), new Vector(1, -0.5, -0.25), new Vector(0.25, 0, 1)) //
                .setViewPlaneSize(10, 10).setDistance(5);
        scene.geometries = new Geometries(
                new Triangle(new Point3D(1.17, 0.04, -1.5), new Point3D(0.78047, 0.70034, 0), new Point3D(1.62883, 0.6836, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//JEF
                new Triangle(new Point3D(1.17, 0.04, -1.5), new Point3D(0.78047, 0.70034, 0), new Point3D(0.36, 0, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//JED
                new Triangle(new Point3D(1.17, 0.04, -1.5), new Point3D(0.77765, -0.58962, 0), new Point3D(0.36, 0, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//JAD
                new Triangle(new Point3D(1.17, 0.04, -1.5), new Point3D(0.77765, -0.58962, 0), new Point3D(1.56646, -0.61367, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//JAB
                new Triangle(new Point3D(1.17, 0.04, -1.5), new Point3D(2, 0, 0), new Point3D(1.56646, -0.61367, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//JCB
                new Triangle(new Point3D(1.17, 0.04, -1.5), new Point3D(2, 0, 0), new Point3D(1.62883, 0.6836, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF
                new Triangle(new Point3D(1.2, 0.69, 0), new Point3D(1.41, 0.38, 0.5), new Point3D(1.62883, 0.6836, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//QNF
                new Triangle(new Point3D(1.2, 0.69, 0), new Point3D(1.41, 0.38, 0.5), new Point3D(0.97, 0.39, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//QNM
                new Triangle(new Point3D(1.2, 0.69, 0), new Point3D(0.78047, 0.70034, 0), new Point3D(0.97, 0.39, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//QEM
                new Triangle(new Point3D(0.57, 0.35, 0), new Point3D(0.78047, 0.70034, 0), new Point3D(0.97, 0.39, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//REM
                new Triangle(new Point3D(0.57, 0.35, 0), new Point3D(0.81, 0, 0.5), new Point3D(0.97, 0.39, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//RLM
                new Triangle(new Point3D(0.57, 0.35, 0), new Point3D(0.81, 0, 0.5), new Point3D(0.36, 0, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//RLD
                new Triangle(new Point3D(0.57, -0.29, 0), new Point3D(0.81, 0, 0.5), new Point3D(0.36, 0, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//SLD
                new Triangle(new Point3D(0.57, -0.29, 0), new Point3D(0.81, 0, 0.5), new Point3D(0.97, -0.28, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//SLK
                new Triangle(new Point3D(0.57, -0.29, 0), new Point3D(0.77765, -0.58962, 0), new Point3D(0.97, -0.28, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//SAK
                new Triangle(new Point3D(1.17, -0.6, 0), new Point3D(0.77765, -0.58962, 0), new Point3D(0.97, -0.28, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//TAK
                new Triangle(new Point3D(1.17, -0.6, 0), new Point3D(1.37, -0.29, 0.5), new Point3D(0.97, -0.28, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//TPK
                new Triangle(new Point3D(1.17, -0.6, 0), new Point3D(1.37, -0.29, 0.5), new Point3D(1.56646, -0.61367, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//TPB
                new Triangle(new Point3D(1.78, -0.31, 0), new Point3D(1.37, -0.29, 0.5), new Point3D(1.56646, -0.61367, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//UPB
                new Triangle(new Point3D(1.78, -0.31, 0), new Point3D(1.37, -0.29, 0.5), new Point3D(1.59, 0, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//UPO
                new Triangle(new Point3D(1.78, -0.31, 0), new Point3D(2, 0, 0), new Point3D(1.59, 0, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//UCO
                new Triangle(new Point3D(1.81, 0.34, 0), new Point3D(2, 0, 0), new Point3D(1.59, 0, 0.5))
                        .setEmission(new Color(195, 252, 253)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//ICO
                new Triangle(new Point3D(1.81, 0.34, 0), new Point3D(1.41, 0.38, 0.5), new Point3D(1.59, 0, 0.5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//INO
                new Triangle(new Point3D(1.81, 0.34, 0), new Point3D(1.41, 0.38, 0.5), new Point3D(1.62883, 0.6836, 0))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),
                new Plane(new Point3D(15, 0, 0), new Vector(1, 0, 0))
                        .setMaterial(new Material()//
                                .setkS(0.5).setnShininess(30).setkR(1)),
                new Sphere(2.5, new Point3D(1.17, 0.04, -5))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkT(0.95))
        );
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(new PointLight(new Color(java.awt.Color.YELLOW), new Point3D(-50, 0, 0)));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(1.17, 0.04, -5), new Vector(1, -0.5, 0)) //
                .setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new Color(java.awt.Color.YELLOW), new Point3D(1.17, 0.04, -1)));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("diamondB", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void glossyAndBlurry8() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(50, new Point3D(30, 0, -150)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkR(0.1).setkS(0.4).setnShininess(80)) //
                , new Plane(new Point3D(30, -60, -150), new Vector(0, 1, 0)) //
                        .setEmission(new Color(20, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkR(0.4).setnShininess(80))
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 0), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0), new Point3D(0, 0, 50), new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("check5", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void glossyAndBlurry9() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(0, -30, 3000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(60, new Point3D(-200, -20, -200)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(-115, -30, -220)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(-45, -30, -230)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(25, -30, -210)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(95, -30, -200)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(165, -30, -190)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(235, -30, -180)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(305, -30, -200)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.4).setkR(0.3).setkS(0.09).setnShininess(80)),
                new Sphere(10, new Point3D(-220, 0, -147)) //
                        .setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(10, new Point3D(-180, 0, -147)) //
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(15, new Point3D(-200, -30, -147)) //
                        .setEmission(new Color(0, 0, 0)),
                new Plane(new Point3D(30, -80, -150), new Vector(0, 1, 0)) //
                        .setEmission(new Color(20, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.1).setkR(0.2).setnShininess(80))
//                ,new Plane( new Point3D(30, 80, -150),new Vector(0,1,-0.15)) //
//                        .setEmission(new Color(20,0,0)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0).setkR(0).setnShininess(80))
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 10), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0), new Point3D(0, -35, 50), new Vector(30, 35, -200)//new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add( //
                new DirectionalLight(new Color(400, 250, 10), new Vector(30, -5, -150) //
                ));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("GlossyWorm", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveGlossyAndBlurryRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void glossyWorm() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(0, -30, 3000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(60, new Point3D(-200, -20, -200)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(-115, -30, -220)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(-45, -15, -230)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(25, -15, -220)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(95, -30, -200))
                        .setEmission(new Color(100, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(165, -30, -180))
                        .setEmission(new Color(100, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(235, -30, -190))
                        .setEmission(new Color(100, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(305, -30, -200))
                        .setEmission(new Color(100, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(10, new Point3D(-220, 0, -147))//eye
                        .setEmission(new Color(50, 80, 150))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(10, new Point3D(-180, 0, -147))//eye
                        .setEmission(new Color(50, 80, 150))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(5, new Point3D(-220, 0, -140))//pupil
                        .setEmission(new Color(0, 0, 0)),
                new Sphere(5, new Point3D(-180, 0, -140))//pupil
                        .setEmission(new Color(0, 0, 0)),
                new Sphere(10, new Point3D(-200, -30, -147))//mouth
                        .setEmission(new Color(0, 0, 0)),
                new Triangle(new Point3D(-210, -32, -140), new Point3D(-208, -28, -139), new Point3D(-220, -26, -140)),
                new Triangle(new Point3D(-190, -32, -140), new Point3D(-192, -28, -139), new Point3D(-180, -26, -140)),
                new Plane(new Point3D(30, -80, -150), new Vector(0, 1, 0))
                        .setEmission(new Color(20, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.1).setkR(0.3).setnShininess(80))
                , new Triangle(new Point3D(-230, 30, -200), new Point3D(-217, 30, -200), new Point3D(-235, 55, -200))
                        .setEmission(new Color(20, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80))
                , new Triangle(new Point3D(-170, 30, -200), new Point3D(-183, 30, -200), new Point3D(-165, 55, -200))
                        .setEmission(new Color(20, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(7, new Point3D(-235, 55, -200))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(7, new Point3D(-165, 55, -200))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80))

        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 10), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0), new Point3D(0, -35, 50), new Vector(30, 0, -150)//new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add( //
                new DirectionalLight(new Color(200, 100, 10), new Vector(30, -5, -150) //
                ));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("glossyWorm2", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveGlossyAndBlurryRayTracer(scene).setRADIUS(0.3).setAMOUNT_OF_RAYS(500));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void glossyAndBlurry6() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(0, 0, 3000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(50, new Point3D(30, 0, -200)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.25).setkR(0.4).setkS(0.09).setnShininess(80)) //
                , new Plane(new Point3D(30, -80, -150), new Vector(0, 1, 0)) //
                        .setEmission(new Color(20, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.1).setkR(0.3).setnShininess(80))
//                ,new Plane( new Point3D(30, 80, -150),new Vector(0,1,-0.15)) //
//                        .setEmission(new Color(20,0,0)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0).setkR(0).setnShininess(80))
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 10), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0), new Point3D(0, -35, 50), new Vector(30, 0, -150)//new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));
//scene.lights.add( //
//                new DirectionalLight(new Color(400, 250, 10) ,new Vector(30, -5, -150) //
//                ));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("check6Glossy", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveGlossyAndBlurryRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void Blurry() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(50, new Point3D(30, 0, -200)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.4).setkS(0.4).setnShininess(80)) //
                , new Plane(new Point3D(30, -60, -150), new Vector(0, 1, 0)) //
                        .setEmission(new Color(20, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setnShininess(80)),
                new Triangle(new Point3D(30, 100, 200), new Point3D(-30, -40, 200), new Point3D(90, -40, 200))
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setkD(0.3).setkS(0.3).setkT(0.4).setnShininess(80))
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 0), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0), new Point3D(0, 0, 50), new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("triangleSphereBl", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveGlossyAndBlurryRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    void blurryDiamond() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(1.5, 0.5, -2), new Vector(-0.5, -0.25, 1), new Vector(0, 1, 0.25)) //
                .setViewPlaneSize(10, 10).setDistance(5);
        Point3D J = new Point3D(0.04, -1.5, 1.17);
        Point3D A = new Point3D(-0.58962, 0, 0.77765);
        Point3D B = new Point3D(-0.61367, 0, 1.56646);///
        Point3D C = new Point3D(0, 0, 2);
        Point3D D = new Point3D(0, 0, 0.36);
        Point3D E = new Point3D(0.70034, 0, 0.78047);
        Point3D F = new Point3D(0.6836, 0, 1.62883);
        Point3D I = new Point3D(0.4, 0, 1.94);//
        Point3D K = new Point3D(-0.28, 0.5, 0.96);
        Point3D L = new Point3D(0, 0.5, 0.81);
        Point3D M = new Point3D(0.39, 0.5, 0.97);
        Point3D N = new Point3D(0.38, 0.5, 1.41);
        Point3D O = new Point3D(0, 0.5, 1.59);
        Point3D P = new Point3D(-0.29, 0.5, 1.37);
        Point3D Q = new Point3D(0.81, 0, 1.19);
        Point3D R = new Point3D(0.44, 0, 0.46);//0.35
        Point3D S = new Point3D(-0.36, 0, 0.45);
        Point3D T = new Point3D(-0.74, 0, 1.17);
        Point3D U = new Point3D(-0.38, 0, 1.9);
        Material material = new Material().setkD(0.4).setkS(0.2).setnShininess(80).setkR(0.2).setkT(0);
        for (int i = 0; i < 2; i++) {

            scene.geometries.add(new Geometries(
                    new Triangle(J, U, B)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, B, T)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, A, T)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, A, S)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, S, D)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, D, R)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, E, R)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, E, Q)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, Q, F)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, F, I)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(J, I, C)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(J, C, U)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(Q, N, F)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(Q, N, M)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(Q, E, M)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(R, E, M)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(R, L, M)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(R, L, D)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(S, L, D)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(S, L, K)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(S, A, K)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(T, A, K)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(T, P, K)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(T, P, B)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(U, B, P)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(U, P, O)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(U, C, O)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(I, C, O)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material),
                    new Triangle(I, N, O)
                            .setEmission(new Color(100, 100, 130)).setMaterial(material),
                    new Triangle(I, N, F)
                            .setEmission(new Color(100, 100, 100)).setMaterial(material)
//                    new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
//                            .setMaterial(material)

            ));
            Vector vector = new Vector(1.5, 0, 1);
            J = J.add(vector);
            A = A.add(vector);
            B = B.add(vector);
            C = C.add(vector);
            D = D.add(vector);
            E = E.add(vector);
            F = F.add(vector);
            I = I.add(vector);
            K = K.add(vector);
            L = L.add(vector);
            M = M.add(vector);
            N = N.add(vector);
            O = O.add(vector);
            P = P.add(vector);
            Q = Q.add(vector);
            R = R.add(vector);
            S = S.add(vector);
            T = T.add(vector);
            U = U.add(vector);
        }
        scene.geometries.add(new Geometries(new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
                        .setMaterial(new Material().setkD(0.4).setkS(0.4))),
                new Geometries(new Plane(new Point3D(-2.3, -2.3, 2.65), new Vector(1, 0.25, -1))
                        .setMaterial(new Material().setkD(0.4).setkS(0.4))),
                new Geometries(new Plane(new Point3D(2.3, 2.41, 5), new Vector(-2.0625, 0.25, -1))
                        .setMaterial(new Material().setkD(0.4).setkS(0.4))));
        // new Triangle(new Point3D(0.8,2,1),new Point3D(0.5,-2,1),new Point3D(3,0,1))
        //   .setEmission(new Color(100,100,100)).setMaterial(new Material().setkD(0.2).setkS(0.2).setkT(0.2))),
        //  new Triangle(new Point3D(-0.7,2,1),new Point3D(-1,-2,1),new Point3D(1.5,0,1))
        //.setEmission(new Color(100,100,100)).setMaterial(new Material().setkD(0.2).setkS(0.2).setkT(0.5))
        //  new Sphere(1,new Point3D(1.5,-0.1,1.8)).setEmission(new Color(50,50,50)).setMaterial(new Material().setkD(0.1).setkS(0.1).setkT(0.5))));

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
//        scene.lights.add(
//                new PointLight(new Color(200, 100, 50), new Point3D(0, 0, -50)) //
//                        .setKl(0.0001).setKq(0.00001));
//        scene.lights.add(
//                new SpotLight(new Color(200, 100, 50), new Point3D(0.04, -5, 1.17), new Vector(-0.5, 0, 1) //
//                ).setKl(0.0005).setKq(0.000005));
//        scene.lights.add(
//                new PointLight(new Color(200, 100, 50), new Point3D(0.04, -1, 1.17)) //
//                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(-2, -1, 0)) //
                        .setKl(0.0001).setKq(0.00001));

//    scene.lights.add(
//            new PointLight(new Color(300, 300, 300), new Point3D(-2, 20, 1.17)) //
//                    .setKl(0.0001).setKq(0.00001));
//
        scene.lights.add(
                new PointLight(new Color(150, 150, 150), new Point3D(1.5, 5, -20)) //
                        .setKl(0.0001).setKq(0.00001));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("diamondsoft1", 400, 400)) //
                .setCamera(camera)
                .setRayTracer(new BasicRayTracer(scene)).setDebugPrint().setMultithreading(3);
        render.renderImage();
        render.writeToImage();

    }

    @Test
    void blurryWorm() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(0, -30, 3000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(60, new Point3D(-200, -20, -200)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(-115, -30, -220)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(-45, -15, -230)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(25, -15, -220)) //
                        .setEmission(new Color(100, 0, 0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(95, -30, -200))
                        .setEmission(new Color(100, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(165, -30, -180))
                        .setEmission(new Color(100, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(235, -30, -190))
                        .setEmission(new Color(100, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(50, new Point3D(305, -30, -200))
                        .setEmission(new Color(100, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(10, new Point3D(-220, 0, -147))//eye
                        .setEmission(new Color(50, 80, 150))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(10, new Point3D(-180, 0, -147))//eye
                        .setEmission(new Color(50, 80, 150))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(5, new Point3D(-220, 0, -140))//pupil
                        .setEmission(new Color(0, 0, 0)),
                new Sphere(5, new Point3D(-180, 0, -140))//pupil
                        .setEmission(new Color(0, 0, 0)),
                new Sphere(10, new Point3D(-200, -30, -147))//mouth
                        .setEmission(new Color(0, 0, 0)),
                new Triangle(new Point3D(-210, -32, -140), new Point3D(-208, -28, -139), new Point3D(-220, -26, -140)),
                new Triangle(new Point3D(-190, -32, -140), new Point3D(-192, -28, -139), new Point3D(-180, -26, -140)),
                new Plane(new Point3D(30, -80, -150), new Vector(0, 1, 0))
                        .setEmission(new Color(20, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.1).setkR(0.25).setnShininess(80))
                , new Triangle(new Point3D(-230, 30, -200), new Point3D(-217, 30, -200), new Point3D(-235, 55, -200))
                        .setEmission(new Color(20, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80))
                , new Triangle(new Point3D(-170, 30, -200), new Point3D(-183, 30, -200), new Point3D(-165, 55, -200))
                        .setEmission(new Color(20, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(7, new Point3D(-235, 55, -200))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
                new Sphere(7, new Point3D(-165, 55, -200))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.4).setnShininess(80)),
               // new Triangle(new Point3D(20, -120, -20), new Point3D(20, 120, -20), new Point3D(-120, -20, -20))
                new Triangle(new Point3D(-190, -120, 30), new Point3D(-190, 120, 30), new Point3D(-330, -20, 30))
                        .setMaterial(new Material().setkD(0.2).setkS(0.1).setkT(0.5)),
               // new Triangle(new Point3D(40, -120, 30), new Point3D(40, 120, 30), new Point3D(160, -20, 30))
                new Triangle(new Point3D(-185, -120, 30), new Point3D(-185, 120, 30), new Point3D(-65, -20, 30))
                        .setMaterial(new Material().setkD(0.2).setkS(0.1).setkT(0.2))
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 10), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0), new Point3D(0, -35, 50), new Vector(30, 0, -150)//new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add( //
                new DirectionalLight(new Color(200, 100, 10), new Vector(30, -5, -150) //
                ));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("notBlurryWorm", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));// ImproveGlossyAndBlurryRayTracer(scene).setRADIUS(0.6).setAMOUNT_OF_RAYS(100));
        render.renderImage();
        render.writeToImage();
    }
}