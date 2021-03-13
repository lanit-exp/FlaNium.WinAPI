package FlaNium.WinAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Sample {

    public static List<WebElement> getElementList(WebDriver driver) {
        return driver.findElements(By.xpath("//*"));
    }

    public static List<WebElement> getElementList(WebElement element) {
        return element.findElements(By.xpath("//*"));
    }

    public static void elementBoundingRectangleToJSONFile(List<WebElement> elements, String elementsName, String fileName) throws IOException {

        JSONObject completeObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject writeObject = new JSONObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for (WebElement e : elements) {
            JSONObject jsonObject = new JSONObject();
            String[] s1 = e.getAttribute("BoundingRectangle").split(", ");
            jsonObject.put("x", s1[0]);
            jsonObject.put("y", s1[1]);
            jsonObject.put("h", s1[2]);
            jsonObject.put("w", s1[3]);
            completeObject.put(e.getAttribute("ClassName"), jsonObject);
        }

        jsonArray.add(completeObject);
        writeObject.put(elementsName, jsonArray);

        FileWriter file = new FileWriter(fileName);
        file.write(gson.toJson(writeObject));
        System.out.println(("Successfully Copied JSON Object to File..."));
        file.flush();
        file.close();

    }

    public static void elementBoundingRectangleToJSONFile(List<WebElement> elements, String elementsName) throws IOException {
        elementBoundingRectangleToJSONFile(elements,elementsName,"elements.json");
    }
}
