import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Bắt đầu chạy Main...");
        System.out.println("Address của ClassLoader Main là: " + Main.class.getClassLoader());
        System.out.println("Name của ClassLoader Main là: " + Main.class.getClassLoader().getName());
        System.out.println("--------------------");

        // 1. Chỉ định thư mục sẽ nạp class
        // THAY ĐỔI ĐƯỜNG DẪN NÀY cho đúng máy bạn
        Path customLibPath = Paths.get("T:\\Github\\Day-19---ClassLoaders\\Day-19-ClassLoaders");

        // 2. Tạo Custom ClassLoader
        CustomClassLoader myLoader = new CustomClassLoader(customLibPath);

        // 3. Yêu cầu nó nạp lớp
        String classNameToLoad = "DemoTask";
        System.out.println("Yêu cầu CustomLoader nạp: " + classNameToLoad);
        Class<?> demoTaskClass = myLoader.loadClass(classNameToLoad);

        // 4. Khởi tạo và chạy
        Object instance = demoTaskClass.getDeclaredConstructor().newInstance();
        Method method = demoTaskClass.getMethod("execute");
        method.invoke(instance);

        System.out.println("--------------------");
        System.out.println("Hoàn thành.");
    }
}