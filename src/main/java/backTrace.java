import java.util.ArrayList;
import java.util.HashSet;

public class backTrace {
    public Square square;
    private int maxX;
    private int maxY;
    private int maxValue;
    private int maxZ;

    private boolean isSuccess;
    public backTrace(Square square){
        this.square=square;
        this.maxX=square.rows.size();
        this.maxY=square.cols.size();
        this.maxZ=square.zones.size();
        this.maxValue = this.maxX;
    }
    //按序递归
    public boolean Trace(int x,int y){
        int nextX;
        int nextY;
        boolean result=false;
        for(int i=1;i<maxValue + 1;i++){
            getvalueSet(x,y);
            nextX=x;
            nextY=y;
            if(canSet(x,y,i)){
                this.square.setValue(x,y,i);
                System.out.println(this.square.output());
                do{
                    nextY++;
                    if(nextY==maxY){
                        nextY=0;
                        nextX++;
                        if(nextX == maxX)
                            return true;
                    }
                }while(this.square.getPoint(nextX,nextY).value!=0);
                result = Trace(nextX,nextY);
                if(result)
                    return true;
            }

        }
        this.square.setValue(x,y,0);
        return result;
    }

    //按信息度递归
    public boolean infoTrace(myPoint mp){
        boolean result=false;
        for(int i=1;i<maxValue+1;i++){
            getvalueSet(mp);
            if(canSet(mp,i)){
                this.square.setValue(mp,i);
                System.out.println(this.square.output());
                //搜索信息量最大的点
                myPoint newPoint = this.getMostInfoPoint();
                if(newPoint == null)
                    return true;
                result = infoTrace(newPoint);
                if(result)
                    return true;
            }
        }
        this.square.setValue(mp,0);
        return result;
    }

    private HashSet<Integer> valueSet=new HashSet<>();
    public void getvalueSet(int x,int y){
        valueSet.clear();
        row targetRow=this.square.getRow(x);
        row targetCol=this.square.getCol(y);
        row targetZone = this.square.getZone(targetCol.getPoint(x,y).z);

        for (myPoint p:targetRow.Points
        ) {
            valueSet.add(p.value);
        }
        for (myPoint p:targetCol.Points
        ) {
            valueSet.add(p.value);
        }
        for (myPoint p:targetZone.Points
        ) {
            valueSet.add(p.value);
        }
    }
    public void getvalueSet(myPoint mp){
        valueSet.clear();
        row targetRow=this.square.getRow(mp.x);
        row targetCol=this.square.getCol(mp.y);
        row targetZone = this.square.getZone(mp.z);

        for (myPoint p:targetRow.Points
        ) {
            valueSet.add(p.value);
        }
        for (myPoint p:targetCol.Points
        ) {
            valueSet.add(p.value);
        }
        for (myPoint p:targetZone.Points
        ) {
            valueSet.add(p.value);
        }
    }
    public boolean canSet(int x,int y,int value){
        if(this.valueSet.contains(value))
            return false;
        return true;
    }
    public boolean canSet(myPoint mp,int value){
        if(this.valueSet.contains(value))
            return false;
        return true;
    }

    public myPoint getMostInfoPoint(){
        myPoint result = null;
        int temp=-1;
        for (myPoint p:this.square.Points
             ) {
            if(p.value!=0)
                continue;;
            int c = calculateInfo(p);
            if(c>temp){
                temp =c;
                result=p;
            }
        }
        return result;
    }

    //计算点的信息量，即所在行已填值的数量+所在列已填值的数量+所在区域已填值的数量
    private int calculateInfo(int x, int y){
        int result = 0;
        row targetRow = this.square.getRow(x);
        row targetCol = this.square.getCol(y);
        row targetZone = this.square.getZone(targetCol.getPoint(x,y).z);
        for (myPoint p:targetRow.Points
             ) {
            if(p.value!=0)
                result++;
        }
        for (myPoint p:targetCol.Points
        ) {
            if(p.value!=0)
                result++;
        }
        for (myPoint p:targetZone.Points
        ) {
            if(p.value!=0)
                result++;
        }
        return result;
    }

    //计算点的信息量，即所在行已填值的数量+所在列已填值的数量+所在区域已填值的数量
    private int calculateInfo(myPoint mp){
        int result = 0;
        row targetRow = this.square.getRow(mp.x);
        row targetCol = this.square.getCol(mp.y);
        row targetZone = this.square.getZone(mp.z);
        for (myPoint p:targetRow.Points
        ) {
            if(p.value!=0)
                result++;
        }
        for (myPoint p:targetCol.Points
        ) {
            if(p.value!=0)
                result++;
        }
        for (myPoint p:targetZone.Points
        ) {
            if(p.value!=0 && !targetRow.Points.contains(p) && !targetCol.Points.contains(p))
                result++;
        }
        return result;
    }

}
