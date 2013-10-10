package cn.dreamslink.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLConnection {

	private static String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "<SOAP-ENV:Body>" + "<AvailabilityRQ EchoToken=\"BusterMcT\" Version=\"1.000\" xmlns=\"http://www.amtrak.com/schema/2009/01/01\">" + "<POS>" + "<ns1:Source xmlns:ns1=\"http://www.opentravel.org/OTA/2003/05\">"
			+ "<ns1:RequestorID ID=\"57534b50\" Type=\"WAS\">" + "<ns1:CompanyName CompanyShortName=\"TravelFusionTest\"/>" + "</ns1:RequestorID>" + "</ns1:Source>" + "</POS>" + "<Option IncudeAllDayTrains=\"true\">" + "<Fares><Passengers><Passenger Code=\"F\" Quantity=\"1\"/></Passengers></Fares></Option><Journeys><Journey>" + "<OriginLocation LocationCode=\"BOS\" MultiLocationInd=\"true\"/>" + "<DestinationLocation LocationCode=\"HAR\" MultiLocationInd=\"true\"/>"
			+ "<TravelDateTime><ns2:DepartureDateTime xmlns:ns2=\"http://www.opentravel.org/OTA/2003/05\">2013-11-01T01:00:00</ns2:DepartureDateTime>" + "</TravelDateTime></Journey></Journeys></AvailabilityRQ></SOAP-ENV:Body></SOAP-ENV:Envelope>";


//    private static String request = "<SOAP-ENV:Envelope xmlns:SOAPSDK1=\"http://www.w3.org/2001/XMLSchema\" xmlns:SOAPSDK2=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:SOAPSDK3=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"> "
//  +"<SOAP-ENV:Body xmlns:tns=\"http://localhost/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\" xmlns:ns=\"http://localhost/AmtrakWebservices/fareQuote/services/processSoapMessage\" xmlns:mime=\"http://schemas.xmlsoap.org/wsdl/mime/\" xmlns:http=\"http://schemas.xmlsoap.org/wsdl/http/\" xmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"> "
//  +"<OTA_RailAvailRQ xmlns:ota=\"http://www.opentravel.org/OTA/2003/05\" xmlns=\"http://www.amtrak.com/schema/2006/01/01\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Version=\"1.004\" ResponseType=\"FareQuote\" EchoToken=\"BusterMcT\">  "
//  +"<POS>       <ota:Source>                                               "
//  +"<ota:RequestorID Type=\"WAS\" ID=\"57534b50\">              "
//  +"<ota:CompanyName CompanyShortName=\"TravelFusionTest\"/>           "
//  +"</ota:RequestorID>    </ota:Source>       </POS>    <RailAvailInfo>    "
//  +"<OriginDestinationInformation>                                         "
//  +"<ota:DepartureDateTime>2005-08-30T10:00:00</ota:DepartureDateTime>     "
//  +"<ota:OriginLocation LocationCode=\"WAS\"/>                             "
//  +"<ota:DestinationLocation LocationCode=\"PHL\"/>"
//  +"</OriginDestinationInformation>      </RailAvailInfo>    </OTA_RailAvailRQ>      </SOAP-ENV:Body>   </SOAP-ENV:Envelope> ";









	private static final String CLIENT_KEY_STORE_PASSWORD = "amtrak";

	public static void main(String[] args) throws Exception {
		KeyStore keystore = KeyStore.getInstance("JKS");
		keystore.load(new FileInputStream("data/keystore.ImportKey"), CLIENT_KEY_STORE_PASSWORD.toCharArray());
		process(keystore);
	}

	public static void process(KeyStore keystore) throws Exception {
		URL url = new URL("https://wsqa.amtrak.com/soap/availabilityv3");
		HttpURLConnection  connection= (HttpURLConnection)url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.addRequestProperty("Accept-Language", "en-US,en;q=0.5");
		connection.addRequestProperty("Connection", "keep-alive");
		connection.addRequestProperty("User-Agent", "User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:24.0) Gecko/20100101 Firefox/24.0");
		((HttpsURLConnection) connection).setSSLSocketFactory(createSSLSocketFactory(keystore, CLIENT_KEY_STORE_PASSWORD));
		
		OutputStream os = connection.getOutputStream();
		os.write(request.getBytes());
		os.flush();
		
		try{
			InputStream in = connection.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);
			byte[] buffer = new byte[2048];
			bis.read(buffer);
			System.out.println("response==" + new String(buffer));
		}catch(Exception e){
			BufferedInputStream bis = new BufferedInputStream(connection.getErrorStream());
			byte[] buffer = new byte[1024];
			bis.read(buffer);
			System.out.println("error===" + new String(buffer));
		}
	}

	

	public static SSLSocketFactory createSSLSocketFactory(KeyStore store, String password) throws Exception {
		if (store != null && password == null) {
			throw new NullPointerException("Keystore recovery password not present");
		}
		try {
			KeyManager[] keyManagerList = null;
			if (store != null) {
				KeyManagerFactory factory = KeyManagerFactory.getInstance("SunX509");
				factory.init(store, password.toCharArray());
				keyManagerList = factory.getKeyManagers();
			}
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(keyManagerList, new TrustManager[]{new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

			}}, null);
			return context.getSocketFactory();
		} catch (GeneralSecurityException gse) {
			throw new Exception("Unable to initialize the SSL socket factory", gse);
		}
	}
}
