
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	final static String[] itemList = {"totalCount", "contentid",
			"contenttypeid", "accomcount", "chkbabycarriage", "chkcreditcard", "chkpet", "expagerange", "expguide",
			"heritage1", "heritage2", "heritage3", "infocenter", "opendate", "parking", "restdate", "useseason",
			"usetime", "accomcountculture", "chkbabycarriage culture", "chkcreditcardculture", "chkpetculture",
			"discountinfo", "infocenterculture", "parkingculture", "parkingfee", "restdateculture", "usefee",
			"usetimeculture", "scale", "spendtime", "agelimit", "bookingplace", "discountinfofestival", "eventenddate",
			"eventhomepage", "eventplace", "eventstartdate", "festivalgrade", "placeinfo", "playtime", "program",
			"spendtimefestival", "sponsor1", "sponsor1tel", "sponsor2", "sponsor2tel", "subevent", "usetimefestival",
			"distance", "infocentertour course", "schedule", "taketime", "theme", "accomcountleports",
			"chkbabycarriageleports", "chkcreditcardleports", "chkpetleports", "expagerangeleports",
			"infocenterleports", "openperiod", "parkingfeeleports", "parkingleports", "reservation", "restdateleports",
			"scaleleports", "usefeeleports", "usetimeleports", "accomcountlodging", "benikia", "chekintime",
			"checkouttime", "chkcooking", "foodplace", "goodstay", "hanok", "infocenterlodging", "parkingloding",
			"pickup", "roomcount", "reservationlodging", "reservationlodging", "roomtype", "scalelodging",
			"subfacility", "barbecue", "beauty", "beverage", "bicycle", "campfire", "fitness", "karaoke", "publicbath",
			"publicpc", "sauna", "seminar", "sports", "chkbabycarriageshopping", "chkcreditcardshopping",
			"chkpetshopping", "culturecenter", "fairday", "infocentershopping", "opendateshopping", "opentime",
			"parkingshopping", "restdateshopping", "restroom", "saleitem", "sleitemcost", "scaleshopping", "shopguide",
			"chkcreditcrdfood", "discountinfofood", "firstmenu", "infocenterfood", "kidsfacility", "opendatefood",
			"opentimefood", "packing", "parkingfood", "reservationfood", "restdatefood", "scalefood", "seat", "smoking",
			"treatmenu" };
	//final static String SERVICE_KEY = "YGTxnWglVVxb5Wek5lRHq4DOipfJ8J0OhpAuhy4%2B8otF2BaQkNWgoahOF0NkVcL%2BCPwB2kbJ3KB9Y%2F61oop5Ow%3D%3D";
	final static String SERVICE_KEY = "e%2Bbp1dW67EA2ZVNd%2F4PXtNQ06gzh4oQLqrNtROM%2Ffk%2BFUTFSdqAunHGB9LtSPjktrnt451dlrvv00W48SWQ3lQ%3D%3D";
	static BufferedWriter writer;


	 public static void main(String[] args) throws IOException {
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data.csv"), "UTF-8"));
		ArrayList<String[]> list=CsvConverter.getcsv();
		int listsize=list.size();
		for(int i=0;i<listsize;i++)
		{
			try {
				//System.out.println(list.get(i)[0]);
				getData(list.get(i)[0],list.get(i)[1]);
				Thread.sleep(1000);
				System.out.println(i+"/"+listsize);
			} catch (ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Done!");
		writer.close();
	}

	private static String getTagValue(String tag, Element eElement) {
		try {
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
			Node nValue = (Node) nlList.item(0);
			if (nValue == null)
				return "-";
			return nValue.getNodeValue();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			return "-";
		}

	}

	private static void csvMake(String s) {
		try {
			writer.write(s);
			writer.write("=");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void getData(String id, String type) throws ParserConfigurationException, SAXException, IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + SERVICE_KEY); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
				+ URLEncoder.encode("����Ű (URL- Encode)", "UTF-8")); /* �������������п��� �߱޹��� ����Ű */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* �� ������ ����� */
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
				+ URLEncoder.encode("1", "UTF-8")); /* ���� ������ ��ȣ */
		urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "="
				+ URLEncoder.encode("ETC", "UTF-8")); /* IOS (������), AND (�ȵ���̵�), WIN (��������), ETC */
		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "="
				+ URLEncoder.encode("AppTest", "UTF-8")); /* ���񽺸�=���ø� */
		urlBuilder.append(
				"&" + URLEncoder.encode("contentId", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")); /* ������ ID */
		urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "="
				+ URLEncoder.encode(type, "UTF-8")); /* ����Ÿ��(������, ���� ��) ID */
		urlBuilder.append(
				"&" + URLEncoder.encode("introYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /* �Ұ����� ��ȸ */

		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(urlBuilder.toString());
		doc.getDocumentElement().normalize();
	//	System.out.println("Root element :" +doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("item");
		Node nNode = nList.item(0);
		Element eElement = (Element) nNode;
		String con;
		for (String s : itemList) {
			con = getTagValue(s, eElement).replaceAll("<br />", "");
			con = con.replaceAll("&nbsp;", "");
			con = con.replaceAll("\n", "");

			csvMake(con);
		}
		writer.newLine();
		// One Row Complete!
	}
}