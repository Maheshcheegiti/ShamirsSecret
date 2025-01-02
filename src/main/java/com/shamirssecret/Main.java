package com.shamirssecret;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        try {
            // Step 1: Read JSON files
            ObjectMapper mapper = new ObjectMapper();
            JsonNode input1 = mapper.readTree(new File("./testcase1.json"));
            JsonNode input2 = mapper.readTree(new File("./testcase2.json"));

            // Step 2: Decode Roots for both test cases
            int[][] roots1 = decodeRoots(input1);
            int[][] roots2 = decodeRoots(input2);

            // Step 3: Calculate constant term using Lagrange Interpolation
            double c1 = Utils.lagrangeInterpolation(roots1);
            double c2 = Utils.lagrangeInterpolation(roots2);

            // Step 4: Print Results
            System.out.println("Constant term for Test Case 1: " + c1);
            System.out.println("Constant term for Test Case 2: " + c2);

            // System.out.println("Current Working Directory: " + new File(".").getAbsolutePath());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Decode roots from JSON input
    private static int[][] decodeRoots(JsonNode input) {
    List<int[]> roots = new ArrayList<>();

    // Use input.fieldNames() to iterate over the fields
    input.fieldNames().forEachRemaining(key -> {
        if (key.equals("keys")) return; // Skip metadata

        int x = Integer.parseInt(key); // x is the key
        JsonNode root = input.get(key);
        int base = root.get("base").asInt();
        String value = root.get("value").asText();
        int y = Utils.decodeValue(value, base);
        roots.add(new int[]{x, y});
    });

    return roots.toArray(new int[0][]); // Convert List to 2D Array
}

}
