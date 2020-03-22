public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    /** 寫一個巨大Body List */
    /** 如果變數一樣的話要設定this*/
    /** this.xxPos = xxPos; */
    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**應該是複製的功能 但整個城市看起來好像沒有實際用處？*/
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /**計算距離 this就是在要執行函數時逗點前面那個*/
    public double calcDistance(Body target) {
        return Math.sqrt((target.xxPos-this.xxPos)*(target.xxPos-this.xxPos)+(target.yyPos-this.yyPos)*(target.yyPos-this.yyPos));
    }

    /** Calculating Force */
    public double calcForceExertedBy(Body target){
        double r = this.calcDistance(target);
        double F = G*this.mass*target.mass/Math.pow(r, 2);
        return F;
    }

    /** Calculating Force X */
    public double calcForceExertedByX(Body target){
        double r = this.calcDistance(target);
        double F = this.calcForceExertedBy(target);
        double x = F*(target.xxPos-this.xxPos)/r;
        return x;
    }

    /** Calculating ForceY  */
    public double calcForceExertedByY(Body target){
        double r = this.calcDistance(target);
        double F = this.calcForceExertedBy(target);
        double y = F*(target.yyPos-this.yyPos)/r;
        return y;
    }

    /** Calculating Net Force X */
    /** 注意 變數如果不是在Method 裡面定義 而是在Global定義的話 計算後的數值不會歸零 這邊要小心 */
    public double calcNetForceExertedByX(Body[] allBodies){
        double xNetForce = 0;
        for(int i = 0; i < allBodies.length; i++) {
            if (this.equals(allBodies[i])) {
                continue;
            }
            xNetForce = xNetForce + this.calcForceExertedByX(allBodies[i]);
        }
        return xNetForce;
    }

    /** Calculating Net Force Y */
    /** 注意 變數如果不是在Method 裡面定義 而是在Global定義的話 計算後的數值不會歸零 這邊要小心 */
    public double calcNetForceExertedByY(Body[] allBodies){
        double yNetForce = 0;
        for(int i = 0; i < allBodies.length; i++){
            if (this.equals(allBodies[i])) {
                continue;
            }
            yNetForce = yNetForce + this.calcForceExertedByY(allBodies[i]);
        }
        return yNetForce;
    }

    /** 用物理定義計算位置 */
    public double update(double dt, double fx, double fy){
        this.xxVel = this.xxVel + fx*dt/this.mass;
        this.yyVel = this.yyVel + fy*dt/this.mass;
        this.xxPos = this.xxPos + this.xxVel*dt;
        this.yyPos = this.yyPos + this.yyVel*dt;
        return 0;
    }

    /** 畫出行星的圖 */
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}