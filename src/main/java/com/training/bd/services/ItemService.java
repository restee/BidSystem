package com.training.bd.services;

import java.util.List;

import com.training.bd.models.Item;

import com.training.bd.webModels.ItemDetails;
import com.training.bd.webModels.ItemFromWeb;

public interface ItemService {
	public ItemDetails getItemDetails(int itemID);
	public List<Item> getItemList();
	public void saveItem(ItemFromWeb item);
	public void deleteItem(int itemID);
	public void updateItem(ItemFromWeb item);
}