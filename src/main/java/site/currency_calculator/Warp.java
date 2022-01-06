package site.currency_calculator;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.actions;

public class Warp {

    private static WebDriver driver;

    public static void moveAndClick(SelenideElement el) {
        actions().moveToElement(el.waitUntil(enabled, 10000, 1000)).click().perform();
    }

    public static void scrollToElement(SelenideElement el) {
        actions().moveToElement(el.waitUntil(enabled, 10000, 1000)).perform();
    }

    public static void sendKeys(String el) {
        actions().sendKeys(el).perform();
    }

    public static String extractDOMProperties(SelenideElement el, String script) {
        driver = WebDriverRunner.getWebDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript(script,
                el.shouldBe(visible));
    }

    public static int stringToIntWrapper(String s) {
        int value;
        try {
            value = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            value = 0;
        }
        return value;
    }

    public static double difference(Double a, Double b) {
        double result = a - b;
        String.valueOf(result);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String roundResult = decimalFormat.format(result);
        return Double.parseDouble(roundResult.replaceAll(",", "."));
    }

    public static List<Double> parsePropOuterText(ElementsCollection collection, String script, List<Double> list, int x) {
        String outerText = extractDOMProperties(collection.get(x),
                script);
        String[] valuesOfString = outerText
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll(",", "")
                .split("[\t\n]+");
        for (String word : valuesOfString) {
            try {
                list.add(Double.parseDouble(word));
            } catch (NumberFormatException e) {
                list.add(0.00);
            }
        }
        return list;
    }

    public static Map<Integer, List<Double>> dataSaver(Map<Integer, List<Double>> map) {
        return map;
    }

    public static List<Double> dataReturner (Map<Integer, List<Double>> map, Integer index){
        return map.get(index);
    }
}

