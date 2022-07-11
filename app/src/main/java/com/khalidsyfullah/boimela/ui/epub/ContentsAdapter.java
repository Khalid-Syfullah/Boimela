package com.khalidsyfullah.boimela.ui.epub;


import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.backgroundColorBody;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.baseUrl;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.book;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.border;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.getHtmlFromWeb;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.href;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.isLegacyFormattingSupported;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.updateData;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorBody;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorH1;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorH2;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorH3;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.colorP;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.data;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.destination;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.fontFamily;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.fontSize;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.fontWeight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.isFormattingSupported;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.letterSpacing;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.lineHeight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.paddingLeft;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.paddingRight;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.rootDir;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.subrootDir;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.textAlignment;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.textIndent;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.webView;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.wordSpacing;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.ContentDataModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.SpineReference;
import nl.siegmann.epublib.domain.TOCReference;

class ContentsViewHolder extends RecyclerView.ViewHolder{

    TextView contentTitle, contentPage;
    ConstraintLayout constraintLayout;


    public ContentsViewHolder(@NonNull View itemView) {
        super(itemView);

        contentTitle = itemView.findViewById(R.id.recyclerview_contents_title);
        contentPage = itemView.findViewById(R.id.recyclerview_contents_page);
        constraintLayout = itemView.findViewById(R.id.recyclerview_contents_constraint_layout);

    }

}

public class ContentsAdapter extends RecyclerView.Adapter<ContentsViewHolder>{
    
    private ArrayList<ContentDataModel> contentDataModels;
    private Activity activity;

    public ContentsAdapter(Activity activity, ArrayList<ContentDataModel> contentDataModels) {
        
        this.activity = activity;
        this.contentDataModels = contentDataModels;
    }

    @NonNull
    @Override
    public ContentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(activity).inflate(R.layout.recyclerview_contents, parent, false);
        ContentsViewHolder contentsViewHolder = new ContentsViewHolder(view);
        return contentsViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ContentsViewHolder holder, int position) {

        ContentDataModel contentDataModel = contentDataModels.get(position);

        holder.contentTitle.setText(contentDataModel.getTitle());
        holder.contentPage.setText(contentDataModel.getPage());

        if(contentDataModel.getType() == 1){
            holder.contentTitle.setTextAppearance(R.style.TextViewB);
            holder.contentTitle.setTextColor(activity.getResources().getColor(R.color.black));

        }
        else{
            holder.contentTitle.setTextAppearance(R.style.TextViewB2);
            holder.contentTitle.setTextColor(activity.getResources().getColor(R.color.grey));

        }

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //                    File f1 = new File(destination+ File.separator + rootDir + File.separator + hrefHashSeparated[0]);
//                    FileInputStream fileInputStream2 = new FileInputStream(f1);
//                    byte [] bytes = new byte[(int) f1.length()];
//                    fileInputStream2.read(bytes);
//                    data = new String(bytes);
//                    data = data.replaceFirst("(?s)(<head>)(.*?)(</head>)","$1$3");
//                    Log.d("EPUB","Separated[1] "+hrefHashSeparated[1]);

                //data = xmlParser(fileInputStream2, "div","id",hrefHashSeparated[1]);

//                    Document doc = Jsoup.parse(f1);
//                    Element content = doc.getElementById(hrefHashSeparated[1]);
//
//                    if(content != null) {
//                        data = content.text();
//                    }

                String [] hrefHashSeparated = contentDataModel.getHref().split("#");
                String [] hrefSlashSeparated = contentDataModel.getHref().split("/");


                if(hrefHashSeparated.length == 1) {
                    getChapterFromBook(book.getTableOfContents().getTocReferences(), contentDataModel.getHref());
                }


//               GET SUBROOT FROM TOC HREF
                if(hrefSlashSeparated.length > 1){
                    subrootDir = hrefSlashSeparated[0];
                    baseUrl = "file://"+destination + File.separator + rootDir + File.separator + subrootDir + File.separator;

                }
                else{
                    subrootDir = "";
                    baseUrl = "file://"+destination + File.separator + rootDir + File.separator;

                }
                href = contentDataModel.getHref();


                Log.d("EPUB","Data: "+data);
                Log.d("EPUB","href: "+contentDataModel.getHref());
                Log.d("EPUB","hrefHashSeparated[0]: "+hrefHashSeparated[0]);
                Log.d("EPUB","hrefHashSeparated[1]: "+(hrefHashSeparated.length > 1 ? hrefHashSeparated[1] : "Does not exist"));
                Log.d("EPUB","hrefSlashSeparated[0]: "+hrefSlashSeparated[0]);
                Log.d("EPUB","hrefSlashSeparated[1]: "+(hrefSlashSeparated.length > 1 ? hrefSlashSeparated[1] : "Does not exist"));
                Log.d("EPUB","hrefHashSeparated Length: "+hrefHashSeparated.length);
                Log.d("EPUB","hrefSlashSeparated Length: "+hrefSlashSeparated.length);
                Log.d("EPUB","baseUrl: "+baseUrl);

                File f1 = new File(destination+ File.separator + rootDir, hrefHashSeparated[0]);
                data = getHtmlFromWeb(f1);


//                UPDATE WEBVIEW

                String customizedData = updateData(data, backgroundColorBody, colorBody, colorH1, colorH2, colorH3, colorP, letterSpacing, wordSpacing, lineHeight, textIndent, fontFamily, fontSize, fontWeight, textAlignment, paddingLeft, paddingRight, border);

                if(hrefHashSeparated.length > 1){

                    new AsyncTask<Void, Void, Void>(){

                        @Override
                        protected Void doInBackground(Void... voids) {

                            FileOutputStream fileOutputStream = null;
                            try {
                                fileOutputStream = new FileOutputStream(f1);
                                fileOutputStream.write(customizedData.getBytes(StandardCharsets.UTF_8));
                                isFormattingSupported = false;
                                isLegacyFormattingSupported = true;

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);

                            //Toast.makeText(activity, "This EPUB doesn't support text formatting", Toast.LENGTH_SHORT).show();
                            if(hrefSlashSeparated.length > 1) {
                                webView.loadUrl(baseUrl + hrefSlashSeparated[1]);

                            }
                            else{
                                webView.loadUrl(baseUrl + hrefSlashSeparated[0]);
                            }
                        }
                    }.execute();




                }


                else{

//                    isFormattingSupported = true;
//                    webView.loadDataWithBaseURL(baseUrl, customizedData, "text/html", "utf-8", null);

                    new AsyncTask<Void, Void, Void>(){

                        @Override
                        protected Void doInBackground(Void... voids) {

                            FileOutputStream fileOutputStream = null;
                            try {
                                fileOutputStream = new FileOutputStream(f1);
                                fileOutputStream.write(customizedData.getBytes(StandardCharsets.UTF_8));

                                isFormattingSupported = false;
                                isLegacyFormattingSupported = true;

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }



                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);

                            if(hrefSlashSeparated.length > 1) {
                                webView.loadUrl(baseUrl + hrefSlashSeparated[1]);

                            }
                            else{
                                webView.loadUrl(baseUrl + hrefSlashSeparated[0]);
                            }


                        }
                    }.execute();




                }
                

            }
        });


    }

    @Override
    public int getItemCount() {
        return contentDataModels.size();

    }

    public void setContentDataModels(ArrayList<ContentDataModel> contentDataModels) {
        Log.d("EPUB","Current Size: "+ReaderActivity.contentDataModels.size());
        this.contentDataModels = contentDataModels;
        notifyDataSetChanged();

    }

    public String getChapterFromBook(List<TOCReference> tocReferenceList, String href)
    {
        String line, linez = null;
        Resource res;

        for (TOCReference tocReference : tocReferenceList) {

            Log.d("TOC","TITLE: "+tocReference.getTitle());
            Log.d("TOC","HREF: "+tocReference.getCompleteHref());

            if(tocReference.getCompleteHref().equals(href)) {

                if(activity instanceof ReaderActivity) {
                    ((ReaderActivity) activity).SetProgressSliderLeftText(tocReference.getTitle());
                }

                Log.d("TOC","SELECTED HREF: "+tocReference.getCompleteHref());
                StringBuilder string = new StringBuilder();
                res = tocReference.getResource();

                try {
                    InputStream is = res.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    try {
                        while ((line = reader.readLine()) != null) {
                            linez = string.append(line + "\n").toString();
                        }

                        Log.d("TOC","LINEZ: "+linez);
                        data = linez;
                        return linez;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            getChapterFromBook(tocReference.getChildren(), href);

        }
        return linez;
    }

    public String getEntireBook()
    {
        String line, linez = null;
        Spine spine = book.getSpine();
        Resource res;
        List<SpineReference> spineList = spine.getSpineReferences() ;

        int count = spineList.size();
        int start = 0;

        StringBuilder string = new StringBuilder();
        for (int i = start; count > i; i = i +1) {
            res = spine.getResource(i);

            try {
                InputStream is = res.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                try {
                    while ((line = reader.readLine()) != null) {
                        linez =   string.append(line + "\n").toString();
                    }

                } catch (IOException e) {e.printStackTrace();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return linez;
    }

    private String xmlParser(InputStream inputStream, String tag, String attribute, String attributeValue) throws XmlPullParserException, IOException {

        String attr="", value = "";
        XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
        XmlPullParser myParser = xmlFactoryObject.newPullParser();

        myParser.setInput(inputStream, null);

        int event = myParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT)  {
            String name=myParser.getName();
            switch (event){
                case XmlPullParser.START_TAG:
                    break;

                case XmlPullParser.END_TAG:
                    if(name.equals(tag)){

                        Log.d("Parser",name);
                        Log.d("Parser",myParser.getAttributeValue(null,attribute));
                        Log.d("Parser",myParser.getText());

                        if(myParser.getAttributeValue(null,attribute).equals(attributeValue)){
                            value = myParser.getText();
                        }

                    }
                    else{
                        Log.d("Parser",name);
                        Log.d("Parser",myParser.getAttributeValue(null,attribute));
                        Log.d("Parser",myParser.getText());

                    }
                    break;
            }
            event = myParser.next();
        }

        return value;
    }




}