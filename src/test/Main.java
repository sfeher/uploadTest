package test;
 
import java.io.IOException;
import java.net.URL;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
 
public class Main {
 
        public static void main(final String[] args) throws IOException {
                final Endpoint endpoint = Endpoint.publish("http://localhost:9090/service", new TestService());
                ((SOAPBinding)endpoint.getBinding()).setMTOMEnabled(true);
 
                final QName qname = new QName("http://test/", "TestServiceService");
                final Service service = Service.create(new URL("http://localhost:9090/service?wsdl"), qname);
                final ITestService client = service.getPort(ITestService.class);
                final SOAPBinding clientBinding = (SOAPBinding)((BindingProvider)client).getBinding();
                clientBinding.setMTOMEnabled(true);
 
                final BigData data = new BigData();
                data.setStr("string");
                final FileDataSource ds = new FileDataSource(args[0]);
                data.setData(new DataHandler(ds)); 
                client.upload(data);
                System.exit(0);
        }
}