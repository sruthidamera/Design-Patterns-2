package edu.umb.cs681.hw18;

public class LineChartObserver implements Observer<StockEvent>{

	@Override
	public void update(Observable<StockEvent> sender, StockEvent event) {
		System.out.println("Quote  :" +event.quote()+" Ticker : "+event.ticker());
		
	}

}
