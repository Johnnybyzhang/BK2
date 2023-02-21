public class backend {
    boolean state = false;

    public static void main(String[] args) {
        backend bk = new backend();
        bk.postState(false);
        System.out.println(bk.getState());
    }

    public void start(){
        System.out.println("started backend");
    }

    public boolean getState() {
        return state;
    }
    
    public void postState(boolean state) {
        this.state = state;
        System.out.println("state changed to "+state);
    }
}
