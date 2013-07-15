package test;
 
import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BigData {
        @XmlElement
        private String str;
 
        @XmlMimeType("application/octet-stream")
        private DataHandler data;
 
        public String getStr() {
                return this.str;
        }
 
        public void setStr(final String str) {
                this.str = str;
        }
 
        public DataHandler getData() {
                return this.data;
        }
 
        public void setData(final DataHandler data) {
                this.data = data;
        }
}