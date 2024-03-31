package com.fnb.utils.helpers;

import dataObject.Inventory.Material;
import dataObject.Inventory.MaterialCategory;
import dataObject.Inventory.Supplier;
import dataObject.Product.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dataObject.Store.Branch;
import dataObject.Store.Tax;
import org.openqa.selenium.json.Json;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataReader {
    public static JsonObject readConfigFile(String configFile) {
        try {
            // Read the configuration file
            FileReader reader = new FileReader(configFile);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T read(Class<T> dataType) {
        // Read the configuration file
        String configFile = null;

        if (dataType.equals(Product.class)) {
            configFile = "resources/data/productData.json";

        } else if (dataType.equals(ProductCategory.class)) {
            configFile = "resources/data/productCategory.json";

        } else if (dataType.equals(Options.class)) {
            configFile = "resources/data/option.json";

        } else if (dataType.equals(Material.class)) {
            configFile = "resources/data/material.json";

        } else if (dataType.equals(Topping.class)) {
            configFile = "resources/data/topping.json";

        } else if (dataType.equals(SpecificCombo.class)) {
            configFile = "resources/data/specificCombo.json";

        } else if (dataType.equals(Tax.class)) {
            configFile = "resources/data/tax.json";

        } else if (dataType.equals(Branch.class)) {
            configFile = "resources/data/branch.json";

        } else if (dataType.equals(MaterialCategory.class)) {
            configFile = "resources/data/materialCategories.json";

        } else if (dataType.equals(Supplier.class)) {
            configFile = "resources/data/supplier.json";
        }

        JsonObject jsonObject = readConfigFile(configFile);
        // Create a Gson instance
        Gson gson = new Gson();

        String jsonData = null;
        try {
            jsonData = new String(Files.readAllBytes(Paths.get(configFile)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Parse JSON data into ProductData object
        T data = gson.fromJson(jsonData, dataType);

        return data;
    }
}
