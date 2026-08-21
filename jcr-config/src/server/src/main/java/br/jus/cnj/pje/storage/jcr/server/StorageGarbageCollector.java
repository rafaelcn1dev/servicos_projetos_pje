package br.jus.cnj.pje.storage.jcr.server;

import javax.jcr.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.jus.cnj.pje.storage.jcr.server.support.JCRSessionHolder;

/**
 * Classe responsável por executar o coletor de lixo JCR em um período fixo.
 * 
 * @author leoneparise
 */
public class StorageGarbageCollector {
	private boolean running = false;
	private int interval;
	private static Logger log = LoggerFactory.getLogger(StorageGarbageCollector.class);
	
	public StorageGarbageCollector(int interval) {
		this.interval = interval;
	}
	
	/**
	 * Inicializa ocoletor de lixo
	 */
	public synchronized void start() {
		running = true;
		new Thread() {
			public void run() {
				while(isRunning()) {
					int time = 0;
					if(time < interval)
						sleepSilent(interval - time);
					
					try {
						Session session = JCRSessionHolder.getSession();
						time = executeAndTakeTime(new GarbageCollectorTask(session));
					} catch(Exception e) {
						log.error("Falha ao executar garbage collector", e);
					}
				}
			}
		}.start();
		
		log.info("Coletor de lixo iniciado...");
	}
	
	/**
	 * Finaliza o coletor de lixo
	 */
	public synchronized void stop() {
		running = false;
	}
	
	/**
	 * Verifica se o coletor de lixo está em execução
	 * 
	 * @return {@code true} caso esteja em execução {@code false} caso contrário
	 */
	private synchronized boolean isRunning() {
		return running;
	}
	
	/**
	 * Coloca a thread coletora em espera
	 */
	private synchronized void sleepSilent(long interval) {
		try {
			this.wait(interval);
		} catch (InterruptedException e) {
			// Silent
		}
	}
	
	/**
	 * Notifica a thread coletora para iniciar a coleção de lixo 
	 */
	public synchronized void executeNow() {
		this.notifyAll();
	}
	
	/**
	 * Executa uma tarefa e obtém o tempo de execução
	 * 
	 * @param runnable tarefa a ser executada
	 * @return tempo de execução
	 */
	private int executeAndTakeTime(Runnable runnable) {
		long startTime = System.currentTimeMillis();;
		runnable.run();
		return (int)(System.currentTimeMillis() - startTime);
	}
}
