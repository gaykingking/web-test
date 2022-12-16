package com.ls.service;

import com.ls.exception.DataResult;
import com.ls.entity.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;

@FeignClient("inventory")
@Service
public interface InventoryService {
    @PostMapping("/inventory/getInventory")
     DataResult getInventory(@RequestBody Inventory inventory);
    @PostMapping("/inventory/saveInventory")
    DataResult saveInventory(@RequestBody Inventory inventory);
    @DeleteMapping("/deleteInventory")
    DataResult deleteInventory(BigInteger id);
}
