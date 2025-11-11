import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CustomClassLoader extends ClassLoader {

    private final Path loadDirectory;

    public CustomClassLoader(Path directory) {
        // Không chỉ định parent, nó sẽ tự lấy SystemClassLoader làm cha
        this.loadDirectory = directory;
    }

    @Override


    /**
     * Đây là phương thức CỐT LÕI.
     * loadClass() sẽ tự động gọi phương thức này SAU KHI 
     * cha của nó (System CL) không tìm thấy class.
     */
    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        // 1. Tên class "com.example.MyClass" -> đường dẫn file "com/example/MyClass.class"
        String classFileName = className.replace('.', '/') + ".class";
        Path classPath = loadDirectory.resolve(classFileName);

        if (!Files.exists(classPath)) {
            throw new ClassNotFoundException("Không tìm thấy: " + className);
        }

        try {
            // 2. Đọc toàn bộ file .class thành mảng byte[]
            byte[] classBytes = Files.readAllBytes(classPath);
            
            // 3. "Định nghĩa" lớp: Đây là lúc JVM biến mảng byte thành một lớp trong Metaspace
            // tham số (name, byte_array, start, length)
            return defineClass(className, classBytes, 0, classBytes.length);

        } catch (IOException e) {
            throw new ClassNotFoundException("Lỗi khi đọc file: " + className, e);
        }
    }
}