package View;
import java.util.concurrent.Semaphore;

import Controller.Cozinha;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo =  new Semaphore(1);
		
		for(int threadId = 1; threadId < 6; threadId++) {
			Thread threadC = new Cozinha(threadId, semaforo);
			threadC.start();
			
			
		}
		
	}

}
