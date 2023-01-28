import java.util.*;
import java.util.function.Predicate;

public class Line {
    public ArrayList<myPoint> Points;
    public Line(ArrayList<myPoint> square){
        this.Points = square;
    }

    public void setValue(int x, int y ,int value){
        myPoint mp = this.getPoint(x,y);
        mp.setValue(value);
    }
    public void setValue(myPoint mp ,int value){
        if(this.Points.contains(mp))
            mp.setValue(value);
    }


    public myPoint getPoint(int x, int y){
        for (myPoint p:this.Points
             ) {
            if(p.x == x && p.y == y)
                return p;
        }
        return null;
    }

    //根据值获取点组
    public ArrayList<myPoint> getPoints(int value){
        ArrayList<myPoint> result = new ArrayList<>();
        for (myPoint p :this.Points
             ) {
            if(p.value == value){
                result.add(p);
            }
        }
        return result;
    }

    //设置区域
    public void setZone(int x,int y,int z){
        myPoint p = this.getPoint(x,y);
        if(p!=null)
            p.z = z;
    }


}
