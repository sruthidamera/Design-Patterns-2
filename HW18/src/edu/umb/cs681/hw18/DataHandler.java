package edu.umb.cs681.hw18;

public class DataHandler implements Runnable{
	private String ticker;
	private double quote;
	private StockQuoteObservable Observable ;
	
	
	private volatile boolean done=false;
	DataHandler(String ticker, double quote,StockQuoteObservable Observable) {
		this.ticker=ticker;
		this.quote=quote;
		this.Observable=Observable;
	}
	
	public void setDone() {
		done=true;
	}

	@Override
	public void run() {
		while(true){
			if(done) break;
			Observable.changeQuote(ticker,quote);
			
			try {
	            while(!done) {
	                Thread.sleep(300);
	            }
	        } catch(InterruptedException ex) {
	            
	        }
		}
		
	}
	
	
	public static void main(String args[]) {
		StockQuoteObservable observable=new StockQuoteObservable();
		LineChartObserver ob1=new LineChartObserver();
		LineChartObserver ob2=new LineChartObserver();
		TableObserver ob3=new TableObserver();
		ThreeDObserver ob4=new ThreeDObserver();
		observable.addObserver(ob1);
		observable.addObserver(ob2);
		observable.addObserver(ob3);
		observable.addObserver(ob4);
		
		DataHandler d1=new DataHandler("abc",2,observable);
		DataHandler d2=new DataHandler("cde",3,observable);
		DataHandler d3=new DataHandler("hig",4,observable);
		
		Thread t1=new Thread(d1);
		Thread t2=new Thread(d2);
		Thread t3=new Thread(d3);
		t1.start();
		t2.start();
		t3.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		d1.setDone();
		d2.setDone();
		d3.setDone();
		
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
	}
	
	

}
