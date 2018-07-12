package phongson.com.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import phongson.com.R;
import phongson.com.activity.TinActivity;
import phongson.com.adapter.TinTucAdapter;
import phongson.com.model.TinTuc;
import phongson.com.util.XMLDOMParser;

public class fm_TrangChu extends Fragment {
    ArrayList<TinTuc> list;
    ListView listView;
    TinTucAdapter adapter;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fm_trangchu, container, false);
        return myView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setdata(view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), TinActivity.class);
                //intent.putExtra("link",listtin.get(position).getLink());
                intent.putExtra("link",list.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    private void setdata(View view) {

        list = new ArrayList<>();
        listView = view.findViewById(R.id.listview);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadDataThanhNien().execute("https://thanhnien.vn/rss/home.rss");
                new ReadDataVietNamNet().execute("http://vietnamnet.vn/rss/home.rss");
                new ReadDataVNEpress().execute("https://vnexpress.net/rss/tin-moi-nhat.rss");
            }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },3000);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    class ReadDataThanhNien extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        // vn express
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1Desciption = document.getElementsByTagName("description");
            NodeList nodeListTitle = document.getElementsByTagName("title");
            // NodeList nodeListLink = document.getElementsByTagName("link");
            String title = "";
            String link = "";
            String hinhanh = "";
            String NguonBao = nodeListTitle.item(1).getTextContent();
            String Ngay = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
                int flag = 0;
//                Element line = (Element) nodeList1Desciption.item(i+1);
//                String cdata = getCharacterDataFromElement(line);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                Matcher matcher1 = p1.matcher(cdata);
                //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));

                if (matcher1.find()) {
                    hinhanh = matcher1.group(1);
                    flag = 1;

                }
                if (matcher.find() && flag == 0) {

                    hinhanh = matcher.group(1);
                    //Log.d("hinhanh",hinhanh + ".........." +i);
                }

                Element element = (Element) nodeList.item(i);
                title = nodeListTitle.item(i + 2).getTextContent();
                link = parser.getValue(element, "link");
                Ngay = parser.getValue(element, "pubDate");
                TinTuc tinTuc = new TinTuc(title,link,hinhanh,"",hinhanh,NguonBao,"","");
               list.add(tinTuc);

            }
            listView.setAdapter(adapter);
            adapter = new TinTucAdapter(getActivity(),R.layout.item_list_tintucc,list);
            Toast.makeText(getActivity(), String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);

        }
    }

    private String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    class ReadDataVNEpress extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        // vn express
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1Desciption = document.getElementsByTagName("description");
            String title = "";
            String link = "";
            String hinhanh = "";
            String nguonBao = "VnExpress";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
                int flag = 0;
                Element line = (Element) nodeList1Desciption.item(i + 1);
                //String cdata = getCharacterDataFromElement(line);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                Matcher matcher1 = p1.matcher(cdata);
                //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));

                if (matcher1.find()) {
                    hinhanh = matcher1.group(1);
                    flag = 1;

                }
                if (matcher.find() && flag == 0) {

                    hinhanh = matcher.group(1);
                    //Log.d("hinhanh",hinhanh + ".........." +i);
                }

                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                TinTuc tinTuc = new TinTuc(title,link,hinhanh,"",hinhanh,nguonBao,"","");
                list.add(tinTuc);
                listView.setAdapter(adapter);
                adapter = new TinTucAdapter(getActivity(),R.layout.item_list_tintucc,list);
                Toast.makeText(getActivity(), String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
//                listtin.add(docBao);

            }
//            listView.setAdapter(adapter);
//            adapter = new TinTucAdapter(getActivity(),R.layout.item_list_tintucc,list);
            super.onPostExecute(s);

        }
    }

    class ReadDataVietNamNet extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        // vn express
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1Desciption = document.getElementsByTagName("description");
            String title = "";
            String link = "";
            String hinhanh = "";
            String nguonbao = "Vietnamnet";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
                int flag = 0;
                Element line = (Element) nodeList1Desciption.item(i + 1);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                Matcher matcher1 = p1.matcher(cdata);
                //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));

                if (matcher1.find()) {
                    hinhanh = matcher1.group(1);
                    flag = 1;

                }
                if (matcher.find() && flag == 0) {

                    hinhanh = matcher.group(1);
                    //Log.d("hinhanh",hinhanh + ".........." +i);
                }

                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                TinTuc tinTuc = new TinTuc(title,link,hinhanh,"",hinhanh,nguonbao,"","");
                list.add(tinTuc);

            }
            listView.setAdapter(adapter);
            adapter = new TinTucAdapter(getActivity(),R.layout.item_list_tintucc,list);
            Toast.makeText(getActivity(), String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);

        }
    }
}
