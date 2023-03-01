package cn.jingyouyun.openai.wxkf.utils.xml;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author: zhujingchun
 * @data: 2021/12/08
 */
public class JaxbUtils {

    /**
     * 对象 转 xml字符串
     * @param obj
     * @return
     * @throws JAXBException
     */
    public static String objToXml(Object obj) throws JAXBException {
        StringWriter sw = new StringWriter();
        JAXBContext jc = JAXBContext.newInstance(obj.getClass());
        Marshaller ma = jc.createMarshaller();
        ma.marshal(obj, sw);
        return sw.toString();
    }

    /**
     * xml 字符串 转对象
     * @param obj
     * @param xmlStr
     * @return
     * @throws JAXBException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static Object xmlToObj(Object obj, String xmlStr) throws JAXBException, ParserConfigurationException, SAXException {
        if (StringUtils.isBlank(xmlStr)) {
            return null;
        }
        String xml = xmlStr.replaceAll("&lt;", "<");
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        SAXParserFactory sax = SAXParserFactory.newInstance();
        sax.setNamespaceAware(false);
        XMLReader xmlReader = sax.newSAXParser().getXMLReader();
        Source source = new SAXSource(xmlReader, new InputSource(reader));
        return unmarshaller.unmarshal(source);
    }
}
