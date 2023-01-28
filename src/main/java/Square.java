import java.util.*;

public class Square extends Line{
    public ArrayList<Line> lines = new ArrayList<Line>();

    public ArrayList<row> rows = new ArrayList<row>();
    public ArrayList<row> cols = new ArrayList<row>();
    public ArrayList<row> zones = new ArrayList<row>();
    public int size;
    public Square(ArrayList<myPoint> square){
        super(square);
        this.size =(int) Math.sqrt(this.Points.size());
        this.lines = SquareDistribute(new Line(square));
    }

    //把整个81点列分为行点列，列点列和区域点列
    public ArrayList<Line> SquareDistribute(Line line){
        ArrayList<Line> s = new ArrayList<Line>();
        for(int i = 0; i < size; i ++){
            ArrayList<myPoint> row = new ArrayList<myPoint>();
            ArrayList<myPoint> col = new ArrayList<myPoint>();
            ArrayList<myPoint> zr = this.getSameZ(i);
            for(int j = 0; j < size; j++){
                 myPoint rmp = line.getPoint(i,j);
                 myPoint cmp = line.getPoint(j,i);
                 row.add(rmp);
                 col.add(cmp);
            }
            s.add(new Line(zr));
            this.zones.add(new row(zr,3));
            s.add(new Line(row));
            this.rows.add(new row(row,1));
            s.add(new Line(col));
            this.cols.add(new row(col,2));
        }

        return s;
    }

    //获取同区域的点
    public ArrayList<myPoint> getSameZ(int z){
        ArrayList<myPoint> points = new ArrayList<>();
        for(int i = 0; i < size; i ++){
            for(int j = 0;j < size; j++){
                myPoint mp = this.getPoint(i,j);
                if(mp.z == z)
                    points.add(mp);
            }
        }
        return points;
    }

    public row getRow(int x){
        for (row r:this.rows
             ) {
            if(r.x == x)
                return r;
        }
        return null;
    }

    public row getCol(int x){
        for (row r:this.cols
        ) {
            if(r.x == x)
                return r;
        }
        return null;
    }

    public row getZone(int x){
        for (row r:this.zones
        ) {
            if(r.x == x)
                return r;
        }
        return null;
    }


    public String output(){
        String result="";
        for(int i =0 ;i<size;i++){
            for(int j=0;j<size;j++) {
                result+=this.getPoint(i,j).value;
                if(j==size-1)
                    result+="\n";
            }
        }
        return  result;
    }

}
