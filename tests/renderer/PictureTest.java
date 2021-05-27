package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

public class PictureTest {
    @Test
    public void transparancyRefractionReflection(){
         Scene scene = new Scene("Test scene");
         Camera camera = new Camera(new Point3D(-2, 2, 0), new Vector(1, -0.5, 0), new Vector(0, 0, 1)) //
                .setViewPlaneSize(10, 10).setDistance(5);
         scene.geometries=new Geometries(
                 new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(0.78047,0.70034,0), new Point3D(1.62883,0.6836,0))
                         .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                         .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//JEF
                 new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(0.78047,0.70034,0), new Point3D(0.36,0,0))
                         .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                         .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//JED
                 new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(0.77765,-0.58962,0), new Point3D(0.36,0,0))
                         .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                         .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//JAD
                 new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(0.77765,-0.58962,0), new Point3D (1.56646,-0.61367,0))
                         .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                         .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//JAB
                 new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(2,0,0), new Point3D (1.56646,-0.61367,0))
                         .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                         .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//JCB
        new Triangle(new Point3D(1.17,0.04,-1.5), new Point3D(2,0,0), new Point3D (1.62883,0.6836,0))
                 .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                        .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF
        new Triangle(new Point3D(1.2, 0.69, 0), new Point3D(1.41,0.38,0.5), new Point3D(1.62883,0.6836,0))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//QNF
        new Triangle(new Point3D(1.2, 0.69, 0), new Point3D(1.41,0.38,0.5), new Point3D(0.97,0.39,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//QNM
        new Triangle(new Point3D(1.2, 0.69, 0), new Point3D(0.78047,0.70034,0), new Point3D (0.97,0.39,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//QEM
        new Triangle(new Point3D(0.57, 0.35, 0), new Point3D(0.78047,0.70034,0), new Point3D (0.97,0.39,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//REM
        new Triangle(new Point3D(0.57, 0.35, 0), new Point3D(0.81,0,0.5), new Point3D (0.97,0.39,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//RLM
        new Triangle(new Point3D(0.57, 0.35, 0), new Point3D(0.81,0,0.5), new Point3D (0.36,0,0))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//RLD
        new Triangle(new Point3D(0.57, -0.29, 0), new Point3D(0.81,0,0.5), new Point3D (0.36,0,0))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//SLD
        new Triangle(new Point3D(0.57, -0.29, 0), new Point3D(0.81,0,0.5), new Point3D (0.97,-0.28,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//SLK
        new Triangle(new Point3D(0.57, -0.29, 0), new Point3D(0.77765,-0.58962,0), new Point3D (0.97,-0.28,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//SAK
        new Triangle(new Point3D(1.17, -0.6, 0), new Point3D(0.77765,-0.58962,0), new Point3D (0.97,-0.28,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//TAK
        new Triangle(new Point3D(1.17, -0.6, 0), new Point3D(1.37,-0.29,0.5), new Point3D (0.97,-0.28,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//TPK
        new Triangle(new Point3D(1.17, -0.6, 0), new Point3D(1.37,-0.29,0.5), new Point3D (1.56646,-0.61367,0))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//TPB
        new Triangle(new Point3D(1.78, -0.31, 0), new Point3D(1.37,-0.29,0.5), new Point3D (1.56646,-0.61367,0))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//UPB
        new Triangle(new Point3D(1.78, -0.31, 0), new Point3D(1.37,-0.29,0.5), new Point3D (1.59,0,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//UPO
        new Triangle(new Point3D(1.78, -0.31, 0), new Point3D(2,0,0), new Point3D (1.59,0,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//UCO
        new Triangle(new Point3D(1.81,0.34, 0), new Point3D(2,0,0), new Point3D (1.59,0,0.5))
                .setEmission(new Color(195,252,253)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//ICO
        new Triangle(new Point3D(1.81,0.34, 0), new Point3D(1.41,0.38,0.5), new Point3D (1.59,0,0.5))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),//JCF,//INO
        new Triangle(new Point3D(1.81,0.34, 0), new Point3D(1.41,0.38,0.5), new Point3D (1.62883,0.6836,0))
                .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                .setkS(0.5).setnShininess(30).setkR(0.5).setkT(0.25)),
                 new Plane(new Point3D(15,0,0),new Vector(1,0,0))
                         .setMaterial(new Material()//
                         .setkS(0.5).setnShininess(30).setkR(1)),
                 new Sphere(2.5,new Point3D(1.17,0.04,-5))
                         .setEmission(new Color(0,0,255)).setMaterial(new Material().setkD(0.5)//
                         .setkS(0.5).setnShininess(30).setkT(0.95)));
         scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
         scene.lights.add(new PointLight(new Color(java.awt.Color.YELLOW),new Point3D (-50,0,0)));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(1.17,0.04,-5), new Vector(1, -0.5, 0)) //
                .setKl(4E-4).setKq(2E-5));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("PictureTest", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
}
