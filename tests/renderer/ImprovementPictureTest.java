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
    public void glossyAndBlurry(){
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(-2, 2, 0.5), new Vector(1, -0.5, -0.25), new Vector(0.25, 0, 1)) //
                .setViewPlaneSize(10, 10).setDistance(5);
        scene.geometries=new Geometries(
                new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(0.78047,0.70034,0), new Point3D(1.62883,0.6836,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//JEF
                new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(0.78047,0.70034,0), new Point3D(0.36,0,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//JED
                new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(0.77765,-0.58962,0), new Point3D(0.36,0,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//JAD
                new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(0.77765,-0.58962,0), new Point3D (1.56646,-0.61367,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//JAB
                new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(2,0,0), new Point3D (1.56646,-0.61367,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//JCB
                new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(2,0,0), new Point3D (1.62883,0.6836,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF
                new Triangle(new Point3D(1.2, 0.69, 0), new Point3D(1.41,0.38,0.5), new Point3D(1.62883,0.6836,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//QNF
                new Triangle(new Point3D(1.2, 0.69, 0), new Point3D(1.41,0.38,0.5), new Point3D(0.97,0.39,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//QNM
                new Triangle(new Point3D(1.2, 0.69, 0), new Point3D(0.78047,0.70034,0), new Point3D (0.97,0.39,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//QEM
                new Triangle(new Point3D(0.57, 0.35, 0), new Point3D(0.78047,0.70034,0), new Point3D (0.97,0.39,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//REM
                new Triangle(new Point3D(0.57, 0.35, 0), new Point3D(0.81,0,0.5), new Point3D (0.97,0.39,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//RLM
                new Triangle(new Point3D(0.57, 0.35, 0), new Point3D(0.81,0,0.5), new Point3D (0.36,0,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//RLD
                new Triangle(new Point3D(0.57, -0.29, 0), new Point3D(0.81,0,0.5), new Point3D (0.36,0,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//SLD
                new Triangle(new Point3D(0.57, -0.29, 0), new Point3D(0.81,0,0.5), new Point3D (0.97,-0.28,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//SLK
                new Triangle(new Point3D(0.57, -0.29, 0), new Point3D(0.77765,-0.58962,0), new Point3D (0.97,-0.28,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//SAK
                new Triangle(new Point3D(1.17, -0.6, 0), new Point3D(0.77765,-0.58962,0), new Point3D (0.97,-0.28,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//TAK
                new Triangle(new Point3D(1.17, -0.6, 0), new Point3D(1.37,-0.29,0.5), new Point3D (0.97,-0.28,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//TPK
                new Triangle(new Point3D(1.17, -0.6, 0), new Point3D(1.37,-0.29,0.5), new Point3D (1.56646,-0.61367,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//TPB
                new Triangle(new Point3D(1.78, -0.31, 0), new Point3D(1.37,-0.29,0.5), new Point3D (1.56646,-0.61367,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//UPB
                new Triangle(new Point3D(1.78, -0.31, 0), new Point3D(1.37,-0.29,0.5), new Point3D (1.59,0,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//UPO
                new Triangle(new Point3D(1.78, -0.31, 0), new Point3D(2,0,0), new Point3D (1.59,0,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//UCO
                new Triangle(new Point3D(1.81,0.34, 0), new Point3D(2,0,0), new Point3D (1.59,0,0.5))
                        .setEmission(new Color(195,252,253)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//ICO
                new Triangle(new Point3D(1.81,0.34, 0), new Point3D(1.41,0.38,0.5), new Point3D (1.59,0,0.5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),//JCF,//INO
                new Triangle(new Point3D(1.81,0.34, 0), new Point3D(1.41,0.38,0.5), new Point3D (1.62883,0.6836,0))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.0).setkT(0.3)),
                new Plane(new Point3D(15,0,0),new Vector(1,0,0))
                        .setMaterial(new Material()//
                                .setkS(0.5).setnShininess(30).setkR(1)),
                new Sphere(2.5,new Point3D(1.17,0.04,-5))
                        .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkT(0.95))
        );
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(new PointLight(new Color(java.awt.Color.YELLOW),new Point3D (-50,0,0)));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(1.17,0.04,-5), new Vector(1, -0.5, 0)) //
                .setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new Color(java.awt.Color.YELLOW),new Point3D (1.17,0.04,-1)));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("diamondB", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void glossyAndBlurry1(){
         Scene scene = new Scene("Test scene");

        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(150, 150).setDistance(1000);

        scene.geometries.add( //
                new Sphere(50, new Point3D(0, 0, -50)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.2)),
                new Sphere(25, new Point3D(0, 0, -50)) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100).setkR(0.1)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("glossyAndBlurry1", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void glossyAndBlurry2(){
         Scene scene = new Scene("Test scene");
         Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(60, new Point3D(0, 0, -200)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30).setkT(0.25)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new PointLight(new Color(400, 240, 0), new Point3D(-100, -100, 200)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("check50", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void glossyAndBlurry3(){
         Scene scene = new Scene("Test scene");
         Camera camera = new Camera(new Point3D(0, 0, -1), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
                .setViewPlaneSize(200, 200).setDistance(30);
        scene.geometries.add( //
                new Sphere(20, new Point3D(30, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30).setkT(0.25)), //
                                new Plane(new Point3D(0,0,-30),new Vector(0,0,1))
                                        .setMaterial(new Material().setkD(0).setkS(0).setnShininess(30)) ,//
                new Sphere(10, new Point3D(30, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.PINK)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30))
        );
        scene.lights.add( //
                new PointLight(new Color(50, 50, 0), new Point3D(-100, 0, 0)) //
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add( //
                new DirectionalLight(new Color(50, 50, 0), new Vector(1, 0, -1)) //
                        );

        Render render = new Render(). //
                setImageWriter(new ImageWriter("check1", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void glossyAndBlurry4(){
         Scene scene = new Scene("Test scene");
         Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(15, new Point3D(30, 0, -150)) //
                        .setEmission(new Color(255,0,0)) //
                        .setMaterial(new Material()  .setkD(0.5).setkS(0.4).setkT(0).setnShininess(80)) //
                , new Triangle( new Point3D(-100, -100, -100),new Point3D(100, -100, -100),new Point3D(30, 100, -100)) //
                        .setEmission(new Color(50,50,50)) //
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setkT(0.4).setnShininess(80)
                              ) //
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 0), new Point3D(-30, 0, -130)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0),new Point3D(0,0,50) ,new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("check1", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
@Test
    public void glossyAndBlurry5(){
         Scene scene = new Scene("Test scene");
         Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(50, new Point3D(30, 0, -150)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.4).setkT(0.1).setkS(0.1).setnShininess(80)) //
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 0), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0),new Point3D(0,0,20) ,new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("check22", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
@Test
    public void glossyAndBlurry6(){
         Scene scene = new Scene("Test scene");
         Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(50, new Point3D(30, 0, -150)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.4).setkT(0.2).setkS(0.4).setnShininess(80)) //
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 0), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0),new Point3D(0,0,50) ,new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("check3", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
@Test
    public void glossyAndBlurry7(){
         Scene scene = new Scene("Test scene");
         Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(50, new Point3D(30, 0, -150)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkR(0.1).setkS(0.4).setnShininess(80)) //
                ,new Plane( new Point3D(30, -60, -150),new Vector(0,1,0)) //
                        .setEmission(new Color(100,0,0)) //
                        .setMaterial(new Material().setkD(0.5).setkR(0.4).setnShininess(80))
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 0), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0),new Point3D(0,0,50) ,new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("check4", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }@Test
    public void glossyAndBlurry8(){
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
                setImageWriter(new ImageWriter("check5", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void glossyAndBlurry9(){
         Scene scene = new Scene("Test scene");
         Camera camera = new Camera(new Point3D(0, -30, 3000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(50, new Point3D(30, 0, -200)) //
                        .setEmission(new Color(100,0,0)) //
                        .setMaterial(new Material().setkD(0.25).setkR(0.4).setkS(0.09).setnShininess(80)) //
                ,new Plane( new Point3D(30, -80, -150),new Vector(0,1,0)) //
                        .setEmission(new Color(20,0,0)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.1).setkR(0.4).setnShininess(80))
//                ,new Plane( new Point3D(30, 80, -150),new Vector(0,1,-0.15)) //
//                        .setEmission(new Color(20,0,0)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0).setkR(0).setnShininess(80))
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 10), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0),new Point3D(0,-35,50) ,new Vector(30, 35, -200)//new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));
scene.lights.add( //
                new DirectionalLight(new Color(400, 250, 10) ,new Vector(30, -5, -150) //
                ));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("check6", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void glossyAndBlurry10(){
         Scene scene = new Scene("Test scene");
         Camera camera = new Camera(new Point3D(0, 0, 3000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //
                new Sphere(50, new Point3D(30, 0, -200)) //
                        .setEmission(new Color(100,0,0)) //
                        .setMaterial(new Material().setkD(0.).setkR(0.8).setkS(0).setnShininess(80)) //
                ,new Plane( new Point3D(30, -60, -150),new Vector(0,1,0)) //
                        .setEmission(new Color(20,0,0)) //
                        .setMaterial(new Material().setkD(0.5).setkR(0.1).setnShininess(80))
        );
        scene.lights.add( //
                new PointLight(new Color(400, 250, 0), new Point3D(-30, 200, 0)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add( //
                new SpotLight(new Color(400, 250, 0),new Point3D(0,0,50) ,new Vector(30, 0, -150) //
                ).setKl(0.0005).setKq(0.000005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("check7", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

}
