package com.example.user.xmlpursingdemo1;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by user on 02/27/18.
 */

public class DownloadXmlAsysnTask extends AsyncTask<String, Void, ArrayList<Data>> {

    String nameSpace = "https://www.newsfolo.com/";
    String url = "https://www.newsfolo.com/feed/";
    public GetTitleData getTitleData;

    @Override
    protected ArrayList<Data> doInBackground(String... params) {

        try {
            return loadXmlFromNetwork(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Data> s) {
        super.onPostExecute(s);

        getTitleData.getTitleData(s);
    }

    private ArrayList<Data> loadXmlFromNetwork(String url) throws IOException {
        ArrayList<Data> arrayList = new ArrayList<>();
        InputStream stream = null;
        try {
            stream = downloadUrl(url);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("item");

            for (int i = 0; i < nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;

                    Data data = new Data();
                    data.setTitle(getValue("title", element2));

                    data.setDecription(getValue("description", element2));

                    arrayList.add(data);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return arrayList;
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }
}
