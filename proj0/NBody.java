import java.io.*;
import java.util.*;

public class NBody {


	public static double readRadius(String pathname) {
		File f = new File(pathname);
		In in = new In(f);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String pathname) {
		File f = new File(pathname);
		In in = new In(f);
		int num = in.readInt();
		in.readDouble();
		Planet[] allp = new Planet[5];
		for (int i = 0; i < num; i ++) {
			allp[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), 
				in.readDouble(), in.readString());
		}
		return allp;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);
		String background = "images/starfield.jpg";
		int num = planets.length;

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();

        StdAudio.play("audio/2001.mid");
		

		for (double time = 0.0; time <= T; time += dt) {
			double[] xForces = new double[num];
			double[] yForces = new double[num];

			StdDraw.picture(0.0, 0.0, background);

			for (int i = 0; i < num; i ++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			// for (Planet p : planets) {
			// 	double fX = p.calcNetForceExertedByX(planets);
			// 	double fY = p.calcNetForceExertedByY(planets);
			// 	p.update(dt, fX, fY);

			// 	p.draw();
			// 	StdDraw.show(10);
			// }

			for (int j = 0; j < num; j ++) {
				planets[j].update(dt, xForces[j], yForces[j]);
				planets[j].draw();
				StdDraw.show(10);
			}

		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}		
			
	}

}
