package org.example;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Task3 {
    public static void main(String[] args) {
        try {
            Map<Integer, String> valuesJson = readValues(args[0]);
            JSONParser parser = new JSONParser();
            JSONObject testsJson = (JSONObject) parser.parse(new FileReader(args[1]));
            JSONObject reportJson = new JSONObject();
            JSONArray tests = (JSONArray) testsJson.get("tests");
            JSONArray report;

            report = fill(tests, valuesJson);
            reportJson.put("tests", report);
            try (FileWriter fileWriter = new FileWriter(args[2])) {
                fileWriter.write(reportJson.toJSONString());
                fileWriter.flush();
            }
            System.out.println("report.json сформирован");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<Integer, String> readValues(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject valuesJson = (JSONObject) parser.parse(new FileReader(path));
        Map<Integer, String> valueMap = new HashMap<>();
        JSONArray valuesArray = (JSONArray) valuesJson.get("values");
        for (Object obj : valuesArray) {
            JSONObject valueObject = (JSONObject) obj;
            int id = ((Long) valueObject.get("id")).intValue();
            String value = (String) valueObject.get("value");
            valueMap.put(id, value);
        }

        return valueMap;
    }

    private static JSONArray fill(JSONArray tests, Map<Integer, String> valueMap) {
        JSONArray reportArray = new JSONArray();
        for (Object o : tests) {
            JSONObject test = (JSONObject) o;
            JSONObject report = new JSONObject();
            report.put("id", test.get("id"));
            report.put("title", test.get("title"));
            report.put("value", valueMap.getOrDefault(((Long) test.get("id")).intValue(), ""));

            if (test.containsKey("values")) {
                JSONArray values = (JSONArray) test.get("values");
                JSONArray subReportArray;
                subReportArray = fill(values, valueMap);
                report.put("values", subReportArray);
            }
            reportArray.add(report);
//            System.out.println(reportArray);
        }
        return reportArray;
    }
}