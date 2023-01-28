import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*
        4*4的大小为2
        9*9的大小为3
        16*16的大小为4
        以此类推...
         */
        ArrayList<myPoint> points = new ArrayList<myPoint>();
        System.out.println("请输入大小");
        Scanner scanner = new Scanner(System.in);
        int size = (int) scanner.next().charAt(0)-48;

        //创建初始点列
        for(int i = 0; i < size*size; i ++){
            for(int j = 0; j < size*size; j ++){
                myPoint p = new myPoint(i,j,-1,0);
                points.add(p);
            }
        }

        Line line = new Line(points);

        /*
        划分区域，对于一般数独而言，区域序号为
        000111222
        000111222
        000111222
        333444555
        333444555
        333444555
        666777888
        666777888
        666777888
        但对于拼图数独而言则不然
        例如：
        000000111
        220111111
        220033334
        222335444
        622335544
        677355448
        667755548
        666758888
        667777888
         */
        System.out.println("请输入区域");
        for(int i = 0; i <  size*size; i ++) {
            String s = scanner.next();
            for(int j = 0; j <  size*size; j ++){
                myPoint mp = line.getPoint(i,j);
                mp.z = (int) s.charAt(j)-48;
            }
        }

        /*输入初始值，空的为0，输入9*9的String
        例如:
        000000000
        002070009
        000000000
        000600078
        000008060
        400000000
        030900000
        006050900
        000002000
         */
        System.out.println("请输入初始值");
        for(int i = 0; i < size*size; i ++) {
            String s = scanner.next();
            for(int j = 0; j < size*size; j ++){
                if((int)s.charAt(j)-48 != 0) {
                    myPoint mp = line.getPoint(i, j);
                    mp.value = (int) s.charAt(j) - 48;
                }
            }
        }

        long millis1 = System.currentTimeMillis();
        Square z= new Square(points);
        backTrace bt=new backTrace(z);
        myPoint mp = bt. getMostInfoPoint();
        bt.infoTrace(mp);
        long millis2 = System.currentTimeMillis();
        long time=millis2-millis1;//经过的毫秒数
        System.out.println("时间:"+time/1000);
    }
}