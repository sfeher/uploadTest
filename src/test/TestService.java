package test;
 
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataHandler;
import javax.jws.WebService;
 
@WebService(endpointInterface="test.ITestService")
public class TestService {
 
        public void upload(final BigData data) {
                System.out.println("data, str: "+data.getStr());
                final DataHandler handler = data.getData();
                try {
                        final InputStream in = handler.getInputStream();
                        final byte[] buff = new byte[8192];
                        final OutputStream out = new FileOutputStream("g:\\"+data.getStr());
                        int readed = -1;
                        long sum = 0;
                        long last = System.currentTimeMillis();
                        while ((readed = in.read(buff)) != -1 ) {
                                out.write(buff, 0, readed);
                                sum += readed;
                                if (System.currentTimeMillis() > last + 1000) {
                                        last =System.currentTimeMillis();
                                        System.out.println(sum);
                                }
                        }
                        out.flush();
                        out.close();
                } catch (final Exception ex) {
                        ex.printStackTrace();
                }
        }
 
}