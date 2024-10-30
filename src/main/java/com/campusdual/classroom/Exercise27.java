package com.campusdual.classroom;


import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Exercise27 {
    public static void createDocument() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        org.w3c.dom.Element root = document.createElement("shoppinglist");
        document.appendChild(root);
        Element items = document.createElement("items");
        root.appendChild(items);


        items.appendChild(createItem(document, "item", "quantity", "2", "Manzana"));
        items.appendChild(createItem(document, "item", "quantity", "1", "Leche"));
        items.appendChild(createItem(document, "item", "quantity", "3", "Pan"));
        items.appendChild(createItem(document, "item", "quantity", "2", "Huevo"));
        items.appendChild(createItem(document, "item", "quantity", "1", "Queso"));
        items.appendChild(createItem(document, "item", "quantity", "1", "Cereal"));
        items.appendChild(createItem(document, "item", "quantity", "4", "Agua"));
        items.appendChild(createItem(document, "item", "quantity", "6", "Yogur"));
        items.appendChild(createItem(document, "item", "quantity", "2", "Arroz"));
        writeToFile(document, "src/main/resources/shoppingList.xml");
    }


    private static void writeToFile(Document document, String pathFile) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
        File file = new File(pathFile);
        file.getParentFile().mkdirs();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);

    }

    private static Element createItem(Document document, String tagName, String attribute, String attrValue, String textItem) {
        Element component = document.createElement(tagName);
        component.setAttribute(attribute, attrValue);
        component.setTextContent(textItem);
        return component;
    }

    public static void createJsonDocument() {
        String json = createJsonString();
        try (PrintWriter out = new PrintWriter(new FileWriter("src/main/resources/shoppingList.json"))) {
            out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createJsonString() { //crear el contenido del archivo JSON manualmente
        return "{\n" +
                "  \"items\": [\n" +
                "    { \"text\": \"Manzana\", \"quantity\": 2 },\n" +
                "    { \"text\": \"Leche\", \"quantity\": 1 },\n" +
                "    { \"text\": \"Pan\", \"quantity\": 3 },\n" +
                "    { \"text\": \"Huevo\", \"quantity\": 2 },\n" +
                "    { \"text\": \"Queso\", \"quantity\": 1 },\n" +
                "    { \"text\": \"Cereal\", \"quantity\": 1 },\n" +
                "    { \"text\": \"Agua\", \"quantity\": 4 },\n" +
                "    { \"text\": \"Yogur\", \"quantity\": 6 },\n" +
                "    { \"text\": \"Arroz\", \"quantity\": 2 }\n" +
                "  ]\n" +
                "}";
    }

    public static void main(String[] args) throws IOException {
        try {
            Exercise27.createDocument();
            Exercise27.createJsonDocument();
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}



