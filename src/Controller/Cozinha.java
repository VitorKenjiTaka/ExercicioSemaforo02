package Controller;

import java.util.concurrent.Semaphore;

public class Cozinha extends Thread{
	private int threadId;
	String prato;
	private Semaphore semaforo;
	
	public Cozinha(int threadId, Semaphore semaforo) {
		this.threadId = threadId;
		this.semaforo = semaforo;
	}
	
	public void run() {
		Cozimento();
		Entrega();
	}

	private void Entrega() {
		
		if (threadId % 2 == 0) {
			prato = "Lasanha a bolonhesa";	
		}
		else {
			prato = "Sopa de cebola";
		}
		
		try {
			semaforo.acquire();
			System.out.println(prato +" está sendo entregue.");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
		System.out.println(prato +" foi entregue.");
	}
	
	private void Cozimento() {
		int t = 0;
		int contador = 0;
		
		if (threadId % 2 == 0) {
			prato = "Lasanha a bolonhesa";
			System.out.println("Começa o cozimento de uma Lasanha a bolonhesa.");
			t = (int) ((int) ((Math.random() * 601) + 600));
		}
		else {
			prato = "Sopa de cebola"; 
			System.out.println("Começa o cozimento de uma Sopa de cebola.");
			t = (int) ((int) ((Math.random() * 301) + 500));
		}
		
		
		
		while (contador < t) {
			try {
				if (contador + 100 <= t)
					contador += 100;
				else {
					contador += t - contador;
				}
				System.out.println("Cozinheiro " +threadId + " - " + prato + " " + (contador * 100) / t + "%");
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(prato+" está pronto para entrega.");
		}
	}
}
