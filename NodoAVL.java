package AAVL;

public class NodoAVL <T extends Comparable<T>> implements PrintableNode<T>{
	
	private T dato;
	private NodoAVL <T> hijoIzq;
	private NodoAVL <T> hijoDer;
	private NodoAVL <T> papa;
	private int fe;
	
	
	
	public NodoAVL(T dato) {
		this.dato = dato;
		this.fe = 0;
		this.hijoIzq = null;
		this.hijoDer = null;
		this.papa = null;
	}



	public T getDato() {
		return dato;
	}



	public void setDato(T dato) {
		this.dato = dato;
	}



	public NodoAVL<T> getHijoIzq() {
		return hijoIzq;
	}



	public void setHijoIzq(NodoAVL<T> hijoIzq) {
		this.hijoIzq = hijoIzq;
	}



	public NodoAVL<T> getHijoDer() {
		return hijoDer;
	}



	public void setHijoDer(NodoAVL<T> hijoDer) {
		this.hijoDer = hijoDer;
	}



	public NodoAVL<T> getPapa() {
		return papa;
	}



	public void setPapa(NodoAVL<T> papa) {
		this.papa = papa;
	}



	public int getFe() {
		return fe;
	}



	public void setFe(int fe) {
		this.fe = fe;
	}
	
	
	public String toString() {
		return this.dato.toString();
	}
	
	
	public void cuelga (NodoAVL <T> hijo) {
		
		if (hijo != null) {
			if (hijo.getDato().compareTo(this.dato)<=0) {
				this.hijoIzq = hijo;
			} 
			else {
				this.hijoDer = hijo;
			}
			
			hijo.setPapa(this);
		}
		
	}



	
	
	
	
	
	
	
	
	
	

}
