import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NBody {

    /** 讀入檔案 檔案室照順序讀的 以空格隔開 變數型態要注意 */
    public static double readRadius(String filename){
        In in = new In(filename);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    /** List也可以放入 array裡面 先初始化一個新的 array of List 在array中依序放入第一個List後 開始把每個讀入到的值帶入 */
    public static Planet[] readBodies(String filename){
        In in = new In(filename);
        int size = in.readInt();
        Planet[] bodies = new Planet[size];
        for(int i = 0; i < size; i ++){
            bodies[i] = new Planet(0, 0, 0, 0, 0, "img");
        }
        double trash = in.readDouble();
        for(int j = 0; j < size; j ++){
            bodies[j].xxPos = in.readDouble();
            bodies[j].yyPos = in.readDouble();
            bodies[j].xxVel = in.readDouble();
            bodies[j].yyVel = in.readDouble();
            bodies[j].mass = in.readDouble();
            bodies[j].imgFileName = in.readString();
        }
        return bodies;
    }

    /** 執行程式的主要Method */
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double R = readRadius(filename);
        Planet[] bodies = readBodies(filename);

        StdDraw.setXscale(-R, R);
        StdDraw.setYscale(-R, R);
        StdDraw.clear();
        for(int time = 0; time < T; T = T + dt){
            StdDraw.enableDoubleBuffering();
            StdDraw.picture(0, 0, "images/sun.jpg");
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for(int i = 0; i < 5; i++){
                double x = bodies[i].calcNetForceExertedByX(bodies);
            xForces[i] = x;
                double y = bodies[i].calcNetForceExertedByY(bodies);
            yForces[i] = y;
            }
            for(int i = 0; i < 5; i++){
                bodies[i].draw();
            }
            for(int i = 0; i < 5; i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        /** copy from reference */
        /** showing the result */
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }

}
