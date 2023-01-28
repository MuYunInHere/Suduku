public class myPoint {
    public int x;//x轴
    public int y;//y轴
    public int z;//区域
    public int value;

    public myPoint(int x, int y,int z, int value){
        this.x = x;
        this.y = y;
        this.z = z;
        this.value = value;
    }

    public void setValue(int value) {
            this.value = value;
    }

}
