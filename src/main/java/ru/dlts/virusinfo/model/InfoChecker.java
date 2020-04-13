package ru.dlts.virusinfo.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.dlts.virusinfo.entity.MainInfo;
import ru.dlts.virusinfo.entity.Statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoChecker {
    String url = "https://xn--80aesfpebagmfblc0a.xn--p1ai/";
    public List<Statistics> getInfo() {
        List<Statistics> statisticsList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
//            Document doc = Jsoup.connect(URLEncoder.encode("https://стопкоронавирус.рф", "UTF-8")).get();
            Elements elementsByClass = doc.getElementsByClass("d-map__list");
            for (Element e : elementsByClass) {
                Elements tds = e.getElementsByTag("tr");
                for (Element td : tds) {
                    Statistics statistics = new Statistics();
                    statistics.setLocation(td.getElementsByTag("th").text());
                    Iterator iterator = td.getElementsByTag("td").iterator();
                    while (iterator.hasNext()) {
                        Element stickElement = (Element) iterator.next();
                        statistics.setStick(stickElement.text());
                        Element healedElement = (Element) iterator.next();
                        statistics.setHealed(healedElement.text());
                        Element dieElement = (Element) iterator.next();
                        statistics.setDie(dieElement.text());
                    }
                    statisticsList.add(statistics);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statisticsList;
    }

    public MainInfo getMainInfo(){
        MainInfo mainInfo = new MainInfo();
        try {
            Document doc = Jsoup.connect(url).get();
            mainInfo.setDate(doc.getElementsByClass("d-map__title").get(0).getElementsByTag("span").text().substring(16));
            Elements tds = doc.getElementsByClass("d-map__counter");
            for (Element td : tds) {
                Elements h3 = td.getElementsByTag("h3");
                mainInfo.setStickPlus(h3.get(0).getElementsByTag("sup").text().replaceAll(" ", "").replaceAll("[+]", ""));
                mainInfo.setStick(h3.get(0).text().replaceAll(" ", "").replaceAll("[+]", "").replaceAll(mainInfo.getStickPlus(),""));
                mainInfo.setHealedPlus(h3.get(1).getElementsByTag("sup").text().replaceAll(" ", "").replaceAll("[+]", ""));
                mainInfo.setHealed(h3.get(1).text().replaceAll(" ", "").replaceAll("[+]", "").replaceAll(mainInfo.getHealedPlus(),""));
                mainInfo.setDiePlus(h3.get(2).getElementsByTag("sup").text().replaceAll(" ", "").replaceAll("[+]", ""));
                mainInfo.setDie(h3.get(2).text().replaceAll(" ", "").replaceAll("[+]", "").replaceAll(mainInfo.getDiePlus(),""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mainInfo;
    }
}
