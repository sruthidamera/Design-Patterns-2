package edu.umb.cs681.hw18;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockQuoteObservable extends Observable{
	private ConcurrentHashMap<String, Double> stock = new ConcurrentHashMap<>();
	
	public void changeQuote(String ticker, double quote) {
        stock.put(ticker, quote);
        notifyObservers(new StockEvent(ticker, quote));
    }
	
	
}
