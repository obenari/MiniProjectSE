import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.*;
import scene.Scene;

public class ImprovementRunTimeTest {
    @Test
    void manualBVHTree() {

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
        Point3D I = new Point3D(0.34, 0, 1.81);//
        Point3D K = new Point3D(-0.28, 0.5, 0.96);
        Point3D L = new Point3D(0, 0.5, 0.81);
        Point3D M = new Point3D(0.39, 0.5, 0.97);
        Point3D N = new Point3D(0.38, 0.5, 1.41);
        Point3D O = new Point3D(0, 0.5, 1.59);
        Point3D P = new Point3D(-0.29, 0.5, 1.37);
        Point3D Q = new Point3D(0.69, 0, 1.2);
        Point3D R = new Point3D(0.35, 0, 0.57);//0.35
        Point3D S = new Point3D(-0.29, 0, 0.57);
        Point3D T = new Point3D(-0.6, 0, 1.17);
        Point3D U = new Point3D(-0.31, 0, 1.78);
        Material material = new Material().setkD(0.4).setkS(0).setnShininess(80).setkR(0.4).setkT(0);
        Geometries geometries = new Geometries();
        for (int i = 0; i < 1; i++) {

            Geometries geo1 = new Geometries(
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
                            .setEmission(new Color(100, 100, 100)).setMaterial(material));
            Geometries geo2 = new Geometries(
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
            );
            Vector vector = new Vector(1.5, 0, 3);
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
            Geometries geo3 = new Geometries(geo1, geo2);
            geometries.add(geo3);
        }
        scene.geometries.add(geometries);
        scene.geometries.add(new Geometries(new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
                        .setMaterial(material)),
                new Geometries(new Plane(new Point3D(-2.3, -2.3, 2.65), new Vector(1, 0.25, -1))
                        .setMaterial(material)),
                new Geometries(new Plane(new Point3D(2.3, 2.41, 1.8), new Vector(-1.0625, 0.25, -1))
                        .setMaterial(material)));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0, 0, -50)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new SpotLight(new Color(200, 100, 50), new Point3D(0.04, -5, 1.17), new Vector(-0.5, 0, 1) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0.04, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(-2, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        scene.lights.add(
                new PointLight(new Color(300, 300, 300), new Point3D(-2, 20, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("diamondSoftShadowRunTime", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveSoftShadowRayTracer(scene).setRADIUS(4)).setMultithreading(0).setDebugPrint();
        Intersectable.improvementBVHIsOff = true;
        render.renderImage();
        render.writeToImage();
    }

    @Test
    void picture2() {

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
        Point3D I = new Point3D(0.34, 0, 1.81);//
        Point3D K = new Point3D(-0.28, 0.5, 0.96);
        Point3D L = new Point3D(0, 0.5, 0.81);
        Point3D M = new Point3D(0.39, 0.5, 0.97);
        Point3D N = new Point3D(0.38, 0.5, 1.41);
        Point3D O = new Point3D(0, 0.5, 1.59);
        Point3D P = new Point3D(-0.29, 0.5, 1.37);
        Point3D Q = new Point3D(0.69, 0, 1.2);
        Point3D R = new Point3D(0.35, 0, 0.57);//0.35
        Point3D S = new Point3D(-0.29, 0, 0.57);
        Point3D T = new Point3D(-0.6, 0, 1.17);
        Point3D U = new Point3D(-0.31, 0, 1.78);
        Material material = new Material().setkD(0.4).setkS(0).setnShininess(80).setkR(0.4).setkT(0);
        Geometries geometries = new Geometries();
        for (int i = 0; i < 1; i++) {

            Geometries geo1 = new Geometries(
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
                            .setEmission(new Color(100, 100, 100)).setMaterial(material));
            Geometries geo2 = new Geometries(
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
            );
            Vector vector = new Vector(1.5, 0, 3);
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
            Geometries geo3 = new Geometries(geo1, geo2);
            geometries.add(geo3);
        }
        scene.geometries.add(geometries);
        scene.geometries.add(new Geometries(new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
                .setMaterial(material),
                new Plane(new Point3D(-2.3, -2.3, 2.65), new Vector(1, 0.25, -1))
                        .setMaterial(material),
                new Plane(new Point3D(2.3, 2.41, 1.8), new Vector(-1.0625, 0.25, -1))
                        .setMaterial(material)));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0, 0, -50)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new SpotLight(new Color(200, 100, 50), new Point3D(0.04, -5, 1.17), new Vector(-0.5, 0, 1) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0.04, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(-2, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        scene.lights.add(
                new PointLight(new Color(300, 300, 300), new Point3D(-2, 20, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        Intersectable.improvementBVHIsOff = true;
        Render render = new Render(). //
                setImageWriter(new ImageWriter("diamondSoftShadowRunTime1", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveSoftShadowRayTracer(scene).setRADIUS(4)).setMultithreading(3);
        render.renderImage();
        render.writeToImage();
    }

    @Test
    void improveRunTimePicture() {
//            Scene scene = new Scene("Test scene");
//            Camera camera = new Camera(new Point3D(0, -30, 3000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
//                    .setViewPlaneSize(200, 200).setDistance(1000);
//            Triangle triangle1=new Triangle(new Point3D(-100,0,-200),new Point3D(-20,0,-200),new Point3D(-60,0,-260));
//                    triangle1.setEmission(new Color(255, 255, 255)).setMaterial(new Material());
//                    //.setEmission(new Color(100,100,100));
//            Plane plane = new Plane(new Point3D(30, -80, -150), new Vector(0, 1, 0));
//                    plane.setEmission(new Color(20, 0, 0));
//            scene.geometries.add(triangle1,plane);
//            scene.lights.add( //
//                    new PointLight(new Color(400, 250, 10), new Point3D(-30, 200, 0)) //
//                            .setKl(0.0001).setKq(0.00001));
//            scene.lights.add( //
//                    new SpotLight(new Color(400, 250, 0), new Point3D(0, -35, 50), new Vector(30, 0, -150)//new Vector(30, 0, -150) //
//                    ).setKl(0.0005).setKq(0.000005));
//            scene.lights.add( //
//                    new DirectionalLight(new Color(200, 100, 10), new Vector(30, -5, -150) //
//                    ));
//            Render render = new Render(). //
//                    setImageWriter(new ImageWriter("picture 1", 400, 400)) //
//                    .setCamera(camera) //
//                    .setRayTracer(new ImproveSoftShadowRayTracer(scene).setRADIUS(4)).setMultithreading(3);
//            render.renderImage();
//            render.writeToImage();
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(0, -30, 3000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);
        scene.geometries.add( //

                new Triangle(new Point3D(-210, -32, -140), new Point3D(-208, -28, -139), new Point3D(-220, -26, -140)),
                new Triangle(new Point3D(-190, -32, -140), new Point3D(-192, -28, -139), new Point3D(-180, -26, -140)),
                new Plane(new Point3D(30, -80, -150), new Vector(0, 1, 0))
                        .setEmission(new Color(20, 0, 0))
                        .setMaterial(new Material().setkD(0.5).setkS(0.1).setkR(0.25).setnShininess(80))

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
                setImageWriter(new ImageWriter("pic", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));// ImproveGlossyAndBlurryRayTracer(scene).setRADIUS(0.6).setAMOUNT_OF_RAYS(100));
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
                setImageWriter(new ImageWriter("pic", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveGlossyAndBlurryRayTracer(scene).setRADIUS(0.3).setAMOUNT_OF_RAYS(500)).setMultithreading(0);
        // scene.geometries.createBVHTree1();
        Intersectable.improvementBVHIsOff = true;
        render.renderImage();
        render.writeToImage();
    }


    @Test
    void createBVHAutomaticly() {
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
        Point3D I = new Point3D(0.34, 0, 1.81);//
        Point3D K = new Point3D(-0.28, 0.5, 0.96);
        Point3D L = new Point3D(0, 0.5, 0.81);
        Point3D M = new Point3D(0.39, 0.5, 0.97);
        Point3D N = new Point3D(0.38, 0.5, 1.41);
        Point3D O = new Point3D(0, 0.5, 1.59);
        Point3D P = new Point3D(-0.29, 0.5, 1.37);
        Point3D Q = new Point3D(0.69, 0, 1.2);
        Point3D R = new Point3D(0.35, 0, 0.57);//0.35
        Point3D S = new Point3D(-0.29, 0, 0.57);
        Point3D T = new Point3D(-0.6, 0, 1.17);
        Point3D U = new Point3D(-0.31, 0, 1.78);
        Material material = new Material().setkD(0.4).setkS(0.3).setnShininess(80).setkR(0.2).setkT(0);
        Geometries geometries = new Geometries();
        for (int i = 0; i < 6; i++) {

           geometries.add(
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
                            .setEmission(new Color(100, 100, 100)).setMaterial(material));
            geometries.add(
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
            );
            Vector vector = new Vector(1.5, 0, 3);
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
        for (int i = 0; i < 5; i++) {

           geometries.add(
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
                            .setEmission(new Color(100, 100, 100)).setMaterial(material));
            geometries.add(
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
            );
            Vector vector = new Vector(-1.5, 0, -3);
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
        scene.geometries.add(geometries);
        scene.geometries.add(new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
                        .setMaterial(material),
                new Plane(new Point3D(-2.3, -2.3, 50), new Vector(1, 0.25, -1))
                        .setMaterial(material),
              new Plane(new Point3D(2.3, 2.41, 50), new Vector(-1.0625, 0.25, -1))
                        .setMaterial(material));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0, 0, -50)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new SpotLight(new Color(200, 100, 50), new Point3D(0.04, -5, 1.17), new Vector(-0.5, 0, 1) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0.04, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(-2, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        scene.lights.add(
                new PointLight(new Color(300, 300, 300), new Point3D(-2, 20, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("BVHAutomaticly", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveGlossyAndBlurryRayTracer(scene).setRADIUS(4)).setMultithreading(3).setDebugPrint();
        scene.geometries.createBVHTree();
        render.renderImage();
        render.writeToImage();

    }
    @Test
    void createBVHAutomaticly1() {
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
        Point3D I = new Point3D(0.34, 0, 1.81);//
        Point3D K = new Point3D(-0.28, 0.5, 0.96);
        Point3D L = new Point3D(0, 0.5, 0.81);
        Point3D M = new Point3D(0.39, 0.5, 0.97);
        Point3D N = new Point3D(0.38, 0.5, 1.41);
        Point3D O = new Point3D(0, 0.5, 1.59);
        Point3D P = new Point3D(-0.29, 0.5, 1.37);
        Point3D Q = new Point3D(0.69, 0, 1.2);
        Point3D R = new Point3D(0.35, 0, 0.57);//0.35
        Point3D S = new Point3D(-0.29, 0, 0.57);
        Point3D T = new Point3D(-0.6, 0, 1.17);
        Point3D U = new Point3D(-0.31, 0, 1.78);
        Material material = new Material().setkD(0.4).setkS(0.3).setnShininess(80).setkR(0).setkT(0);
        Geometries geometries = new Geometries();
        for (int i = 0; i < 6; i++) {

           geometries.add(
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
                            .setEmission(new Color(100, 100, 100)).setMaterial(material));
            geometries.add(
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
            );
            Vector vector = new Vector(1.5, 0, 3);
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
        for (int i = 0; i < 5; i++) {

           geometries.add(
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
                            .setEmission(new Color(100, 100, 100)).setMaterial(material));
            geometries.add(
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
            );
            Vector vector = new Vector(-0.5, 0, -1.5);
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
        scene.geometries.add(geometries);
        scene.geometries.add(new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
                        .setMaterial(material),
                new Plane(new Point3D(-2.3, -2.3, 50), new Vector(1, 0.25, -1))
                        .setMaterial(material),
              new Plane(new Point3D(2.3, 2.41, 50), new Vector(-1.0625, 0.25, -1))
                        .setMaterial(material));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0, 0, -50)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new SpotLight(new Color(200, 100, 50), new Point3D(0.04, -5, 1.17), new Vector(-0.5, 0, 1) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0.04, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(-2, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        scene.lights.add(
                new PointLight(new Color(300, 300, 300), new Point3D(-2, 20, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("BVHAutomaticly1", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveSoftShadowRayTracer(scene).setRADIUS(4)).setMultithreading(3).setDebugPrint();
        scene.geometries.createBVHTree();
        render.renderImage();
        render.writeToImage();

    }
    @Test
    void createBVHAutomaticly2() {
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
        Point3D I = new Point3D(0.34, 0, 1.81);//
        Point3D K = new Point3D(-0.28, 0.5, 0.96);
        Point3D L = new Point3D(0, 0.5, 0.81);
        Point3D M = new Point3D(0.39, 0.5, 0.97);
        Point3D N = new Point3D(0.38, 0.5, 1.41);
        Point3D O = new Point3D(0, 0.5, 1.59);
        Point3D P = new Point3D(-0.29, 0.5, 1.37);
        Point3D Q = new Point3D(0.69, 0, 1.2);
        Point3D R = new Point3D(0.35, 0, 0.57);//0.35
        Point3D S = new Point3D(-0.29, 0, 0.57);
        Point3D T = new Point3D(-0.6, 0, 1.17);
        Point3D U = new Point3D(-0.31, 0, 1.78);
        Material material = new Material().setkD(0.4).setkS(0.3).setnShininess(80).setkR(0).setkT(0);
        Geometries geometries = new Geometries();
        for (int i = 0; i < 6; i++) {

            geometries.add(
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
                            .setEmission(new Color(100, 100, 100)).setMaterial(material));
            geometries.add(
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
            );
            Vector vector = new Vector(1.5, 0, 3);
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

        for (int i = 0; i < 5; i++) {

            geometries.add(
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
                            .setEmission(new Color(100, 100, 100)).setMaterial(material));
            geometries.add(
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
            );
            Vector vector = new Vector(-0.5, 1, 1);
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
        scene.geometries.add(geometries);
        scene.geometries.add(new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
                        .setMaterial(material),
                new Plane(new Point3D(-2.3, -2.3, 50), new Vector(1, 0.25, -1))
                        .setMaterial(material),
                new Plane(new Point3D(2.3, 2.41, 50), new Vector(-1.0625, 0.25, -1))
                        .setMaterial(material));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0, 0, -50)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new SpotLight(new Color(200, 100, 50), new Point3D(0.04, -5, 1.17), new Vector(-0.5, 0, 1) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0.04, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(-2, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        scene.lights.add(
                new PointLight(new Color(300, 300, 300), new Point3D(-2, 20, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("BVHAutomaticly2", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveSoftShadowRayTracer(scene).setRADIUS(4)).setMultithreading(3).setDebugPrint();
       scene.geometries.createBVHTree();
        render.renderImage();
        render.writeToImage();

    }
    @Test
    void createBVHAutomaticly3() {
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
        Point3D I = new Point3D(0.34, 0, 1.81);//
        Point3D K = new Point3D(-0.28, 0.5, 0.96);
        Point3D L = new Point3D(0, 0.5, 0.81);
        Point3D M = new Point3D(0.39, 0.5, 0.97);
        Point3D N = new Point3D(0.38, 0.5, 1.41);
        Point3D O = new Point3D(0, 0.5, 1.59);
        Point3D P = new Point3D(-0.29, 0.5, 1.37);
        Point3D Q = new Point3D(0.69, 0, 1.2);
        Point3D R = new Point3D(0.35, 0, 0.57);//0.35
        Point3D S = new Point3D(-0.29, 0, 0.57);
        Point3D T = new Point3D(-0.6, 0, 1.17);
        Point3D U = new Point3D(-0.31, 0, 1.78);
        Material material = new Material().setkD(0.4).setkS(0.3).setnShininess(80).setkR(0).setkT(0);
        Geometries geometries = new Geometries();
        for (int i = 0; i < 6; i++) {

            geometries.add(
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
                            .setEmission(new Color(100, 100, 100)).setMaterial(material));
            geometries.add(
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
            );
            Vector vector = new Vector(1.5, 0, 3);
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

        for (int i = 0; i < 5; i++) {

            geometries.add(
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
                            .setEmission(new Color(100, 100, 100)).setMaterial(material));
            geometries.add(
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
            );
            Vector vector = new Vector(-0.5, 1, 1);
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
        scene.geometries.add(geometries);
        scene.geometries.add(new Plane(new Point3D(0, -2, 0), new Vector(0, 1, 0.25))
                        .setMaterial(material),
                new Plane(new Point3D(-2.3, -2.3, 50), new Vector(1, 0.25, -1))
                        .setMaterial(material),
                new Plane(new Point3D(2.3, 2.41, 50), new Vector(-1.0625, 0.25, -1))
                        .setMaterial(material));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0, 0, -50)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new SpotLight(new Color(200, 100, 50), new Point3D(0.04, -5, 1.17), new Vector(-0.5, 0, 1) //
                ).setKl(0.0005).setKq(0.000005));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(0.04, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));
        scene.lights.add(
                new PointLight(new Color(200, 100, 50), new Point3D(-2, -1, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));

        scene.lights.add(
                new PointLight(new Color(300, 300, 300), new Point3D(-2, 20, 1.17)) //
                        .setKl(0.0001).setKq(0.00001));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("BVHAutomaticly3", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new ImproveSoftShadowRayTracer(scene).setRADIUS(4)).setMultithreading(0).setDebugPrint();
        Intersectable.improvementBVHIsOff=true;
   //    scene.geometries.createBVHTree();
        render.renderImage();
        render.writeToImage();

    }
}
