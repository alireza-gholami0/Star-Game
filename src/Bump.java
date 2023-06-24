public class Bump extends Element {
    private static int bumpNum = 0;
    private int limit;

    public Bump(int limit) {
        setLimit(limit);
    }

    public static void setBumpNum(int bumpNum) {
        Bump.bumpNum = bumpNum;
    }

    public static int getBumpNum() {
        return bumpNum;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

}
