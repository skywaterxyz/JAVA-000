import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		MyClassLoader myClassLoader = new MyClassLoader();
		Class<?> helloClass = myClassLoader.findClass("Hello", "./Hello.xlass");
		if (helloClass == null) {
			System.out.print("find class fail.");
			return;
		}
		Object helloInstance  = helloClass.newInstance();
		
		Method method = helloClass.getMethod("hello");
		method.invoke(helloInstance);		
	}

	protected Class<?> findClass(String className, String fileName) throws ClassNotFoundException {
		
		byte[] decodedBytes = getDecodedBytes(fileName);
		if (decodedBytes == null) {
			System.out.print("get decoded bytes fail.");
			return null;
		}
		return defineClass(className, decodedBytes, 0, decodedBytes.length);
	}
	
	private byte[] getDecodedBytes(String fileName) {
		
		InputStream in = null;
		byte[] decodedBytes = null;
		try {
			in = new FileInputStream(fileName);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int byteTmp;
			try {
				while((byteTmp = in.read()) != -1) {
					out.write(255 - byteTmp);
				}
				decodedBytes = out.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return decodedBytes;
	}
}
