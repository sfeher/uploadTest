package test;
 
import com.sun.xml.internal.ws.developer.JAXWSProperties;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import javax.xml.ws.soap.MTOMFeature;
import javax.xml.ws.soap.SOAPBinding;
 
public class Main {
 
        public static void main(final String[] args) throws IOException {
               MTOMFeature feature = new MTOMFeature();    
               final Endpoint endpoint = Endpoint.publish("http://localhost:9090/service", new TestService(),feature);
                //((SOAPBinding)endpoint.getBinding()).setMTOMEnabled(true);
 
                final QName qname = new QName("http://test/", "TestServiceService");
                final Service service = Service.create(new URL("http://localhost:9090/service?wsdl"), qname);
                final ITestService client = service.getPort(ITestService.class);
                //final SOAPBinding clientBinding = (SOAPBinding)((BindingProvider)client).getBinding();
                //clientBinding.setMTOMEnabled(true);                
                Map ctxt = ((BindingProvider)client).getRequestContext();
                ctxt.put(JAXWSProperties.HTTP_CLIENT_STREAMING_CHUNK_SIZE, 8192);               
                final BigData data = new BigData();
                data.setStr("string");
                final FileDataSource ds = new FileDataSource(args[0]);
                data.setData(new DataHandler(ds)); 
                client.upload(data);
                System.exit(0);
        }
}