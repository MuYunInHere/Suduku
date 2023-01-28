import java.util.ArrayList;
import java.util.HashSet;

public class row extends Line {
    public int x;
    public boolean isRow = false;
    public boolean isCol = false;
    public boolean isZone = false;

    public row(ArrayList<myPoint> square) {
        super(square);
        getFixed();
    }


    //确定该特殊区域是行/列/区域，以及对应的固定行数/列数/区域标记数
    private void getFixed(){
        HashSet<Integer> xHash=new HashSet<>();
        HashSet<Integer> yHash = new HashSet<>();
        HashSet<Integer> zHash = new HashSet<>();
        for (myPoint p: this.Points
        ) {
            xHash.add(p.x);
            yHash.add(p.y);
            zHash.add(p.z);
        }
        if(xHash.size()==1) {
            this.x = (int) xHash.toArray()[0];
            this.isRow = true;
        }
        else if (yHash.size() == 1) {
            this.x = (int) yHash.toArray()[0];
            this.isCol = true;
        } else if (zHash.size() == 1) {
            this.x = (int) zHash.toArray()[0];
            this.isZone = true;
        }
    }

}
