package org.sguzman.github.tax;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Main {
  public final static String[] barNumbers = {"24041980", "00792098"};
  private final static String postURLLogin = "https://www.hcdistrictclerk.com/eDocs/Secure/WideLogin.aspx?ReturnUrl=%2fCommon%2fDefault.aspx%3fShowFF%3d1";


  private static String soupedUp() throws IOException {
    final String url = "https://www.hcdistrictclerk.com/Common/Default.aspx?ShowFF=1";
    Document doc = Jsoup.connect(url).get();
    Element viewState = doc.getElementById("__VIEWSTATE");

    return viewState.attr("value");
  }

  public static void main(String[] args) throws IOException {
    System.out.println(soupedUp());
  }
}
