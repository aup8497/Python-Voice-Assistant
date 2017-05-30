import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class youtubeSongsPullBugsRemovedfinal{

	public static void main(String[] args) throws Exception{		
		youtubeSongsPullBugsRemovedfinal obj = new youtubeSongsPullBugsRemovedfinal();
		System.out.println(args[0]);
		obj.downloadSongs(args[0]);
		// System.out.print("enter the name of the song which you want to download : ");			
	}

	public void downloadSongs(String songName) throws Exception {

		// String songName=in.nextLine();
		songName.replaceAll("\\s","+");
		System.out.println("the formatted string is :"+songName);
		
		Document d=Jsoup.connect("https://www.youtube.com/results?search_query="+songName).timeout(6000).get();
		Elements ele=d.select("ol.item-section");
		int count=0,flag=0;
		String first_url="https://www.youtube.com/results?search_query="+songName;
		//String first_songname;
		for (Element element : ele.select("li")) {
			String download_url=element.select("a.yt-uix-sessionlink.spf-link").attr("href");
				//System.out.println(download_url);
			
			count++;
			if(flag==0){
				if(download_url.indexOf("/watch?") != -1){
				first_url=download_url;
				System.out.println(first_url);
				flag=1;
				System.out.println("Result found at position :"+ count);
				}
			}
			
//			String title=element.select("b").text();
//			//System.out.println(title);
//			if(count==1){
//				first_songname = title;
//			}
		}
		String url=("http://www.youtubeinmp3.com/fetch/?video=https://www.youtube.com"+first_url);
		System.out.println("this is for debugging purposes only :url: "+url);

		int flagToDifferenciateUrl=0;
		String finalurl="";

		try{
			Document sd=Jsoup.connect(url).timeout(6000).get();
			Elements ele2=sd.select("div.infoBox");
			//int count=0,flag=0;
			//Element element2;
			//=ele2.select("p.download-buttons.fullWidth");
			for (Element element2 : ele2.select("p")) {
				//String download_url=element.select("p.download-buttons.fullWidth").attr("href");
					//System.out.println(download_url);
				finalurl=element2.select("a.button.fullWidth").attr("href");
	//			String title=element.select("b").text();
	//			//System.out.println(title);
	//			if(count==1){
	//				first_songname = title;
	//			}
		System.out.println("this is for debugging :inside for loop:finalurl: "+finalurl);

			}
		}catch(Exception e){
			flagToDifferenciateUrl=1;
		}finally{

			if(flagToDifferenciateUrl==1){
				//System.out.println(finalurl);
				mp3 obj=new mp3();
				obj.saveUrl(songName+".mp3",url);			
			}else{
				System.out.println("final url is "+finalurl);
				mp3 obj=new mp3();
				obj.saveUrl(songName+".mp3","http://www.youtubeinmp3.com"+finalurl);	
			}		
		
		}


	}

}
