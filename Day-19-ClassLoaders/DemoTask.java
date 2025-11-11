public class DemoTask {
    public void execute() {
        System.out.println(">>> DemoTask RUNNING... <<<");
        System.out.println(">>> Address My ClassLoader is: " + this.getClass().getClassLoader());
        System.out.println(">>> Name My ClassLoader is " + this.getClass().getClassLoader().getName());
    }
}