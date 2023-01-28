import java.util.ArrayList;
import java.util.HashSet;

public class row extends Line {
    public int x;

    public int rowType;//1:行，2：列，3：区域

    public row(ArrayList<myPoint> square,int rowType) {
        super(square);
        this.rowType = rowType;
        getFixed();
    }


    //确定该特殊区域是行/列/区域，以及对应的固定行数/列数/区域标记数
    private void getFixed(){
        HashSet<Integer> Hash = new HashSet<>();
        switch (this.rowType) {
            case 1:
                for (myPoint p : this.Points
                ) {
                    Hash.add(p.x);
                }
                if (Hash.size() == 1) {
                    this.x = (int) Hash.toArray()[0];
                }
                break;
            case 2:
                for (myPoint p : this.Points
                ) {
                    Hash.add(p.y);
                }
                if (Hash.size() == 1) {
                    this.x = (int) Hash.toArray()[0];
                }
                break;
            case 3:
                for (myPoint p : this.Points
                ) {
                    Hash.add(p.z);
                }
                if (Hash.size() == 1) {
                    this.x = (int) Hash.toArray()[0];
                }
                break;
        }
    }

}
