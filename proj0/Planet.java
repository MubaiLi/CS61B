import java.math.*;

public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet() {

	}

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double distance = Math.sqrt(Math.pow((this.xxPos - p.xxPos), 2) + Math.pow((this.yyPos - p.yyPos), 2));
		return distance;
	}

	public double calcForceExertedBy(Planet p) {
		double force = (6.67 * Math.pow(10, -11) * this.mass * p.mass) / Math.pow(this.calcDistance(p), 2);
		return force;
	}
	
	public double calcForceExertedByX(Planet p) {
		double forceX = this.calcForceExertedBy(p) * ((p.xxPos - this.xxPos) / this.calcDistance(p));
		return forceX;
	}
	public double calcForceExertedByY(Planet p) {
		double forceY = this.calcForceExertedBy(p) * ((p.yyPos - this.yyPos) / this.calcDistance(p));
		return forceY;
	}

	public double calcNetForceExertedByX(Planet[] allp) {
		double netforceX = 0.0;
		for (Planet p : allp) {
			if (this.equals(p)) {
				continue;
			} else {
				netforceX += this.calcForceExertedByX(p);
			}
		}
		return netforceX;
	}
	public double calcNetForceExertedByY(Planet[] allp) {
		double netforceY = 0.0;
		for (Planet p : allp) {
			if (this.equals(p)) {
				continue;
			} else {
				netforceY += this.calcForceExertedByY(p);
			}
		}
		return netforceY;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw() {
		String imgPath = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imgPath);
	}
}