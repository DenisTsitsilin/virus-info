package ru.dlts.virusinfo.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.dlts.virusinfo.entity.Statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoChecker {
    public List<Statistics> getInfo() {
        List<Statistics> statisticsList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://xn--80aesfpebagmfblc0a.xn--p1ai/").get();
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
}
