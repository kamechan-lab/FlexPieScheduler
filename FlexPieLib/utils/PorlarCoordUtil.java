/**画面上の座標がDial中心から見て、どの角度・距離にあるのか計算するユーティリティクラスです。
**/
public class PolarCoordUtil {
    private PolarCoordUtil(){}

    static float calcR(float x, float y, float ox, float oy){
        return (float) Math.sqrt(Math.pow(x-ox, 2) + Math.pow(y-oy, 2));
    }

    static float calcDegrees(float x, float y, float ox, float oy) {
        //内積(0, -1) * (x-ox, y-oy)
        double inner_product = -(y - oy) / PolarCoordUtil.calcR(x, y, ox, oy);
        double deg = Math.toDegrees(Math.acos(inner_product));
        if ((x - ox) > 0) {
            return (float) deg;
        }
        else {
            return (float) (360 - deg);
        }
    }
}
