package AAVL;

public class ArbolAVL<T extends Comparable<T>> {

	private NodoAVL<T> raiz;
	private int cont;

	public ArbolAVL(T dato) {
		this.raiz = new NodoAVL(dato);
		cont = 1;
	}

	public NodoAVL<T> getRaiz() {
		return raiz;
	}

	public int getCont() {
		return cont;
	}

	public int altura(NodoAVL<T> actual) {

		if (actual == null) {
			return 0;
		}

		int ramaIzq = altura(actual.getHijoIzq()) + 1;
		int ramaDer = altura(actual.getHijoDer()) + 1;

		return Math.max(ramaIzq, ramaDer);
	}

	private int calculaFe(NodoAVL<T> actual) {

		int rIzq = altura(actual.getHijoIzq());
		int rDer = altura(actual.getHijoDer());

		return (rDer - rIzq);

	}

	private NodoAVL<T> busca(T dato) {

		NodoAVL<T> actual = this.raiz;

		while (actual != null && actual.getDato().compareTo(dato) != 0)
			if (dato.compareTo(actual.getDato()) <= 0)
				actual = actual.getHijoIzq();
			else
				actual = actual.getHijoDer();

		return actual;
	}

	public boolean contains(T dato) {

		boolean res = false;
		NodoAVL<T> temp = busca(dato);

		if (temp != null) {
			res = true;
		}
		return res;
	}

	// 4 Rotaciones

	private NodoAVL<T> rIzqIzq(NodoAVL<T> alfa) {

		NodoAVL<T> beta = alfa.getHijoIzq();
		NodoAVL<T> c = beta.getHijoDer();

		if (alfa == this.raiz) {
			this.raiz = beta;
		}

		beta.setPapa(alfa.getPapa());
		alfa.setPapa(beta);
		alfa.setHijoIzq(c);
		beta.setHijoDer(alfa);

		if (c != null) {
			c.setPapa(alfa);
		}

		if (beta.getPapa() != null) {
			if (beta.getPapa().getHijoIzq() == alfa) {
				beta.getPapa().setHijoIzq(beta);
			} else {
				beta.getPapa().setHijoDer(beta);
			}
		}

		alfa.setFe(calculaFe(alfa));
		beta.setFe(calculaFe(beta));

		return beta;

	}

	private NodoAVL<T> rDerDer(NodoAVL<T> alfa) {

		NodoAVL<T> beta = alfa.getHijoDer();
		NodoAVL<T> b = beta.getHijoIzq();

		if (alfa == this.raiz) {
			this.raiz = beta;
		}

		beta.setPapa(alfa.getPapa());
		alfa.setPapa(beta);
		alfa.setHijoDer(b);
		beta.setHijoIzq(alfa);

		if (b != null) {
			b.setPapa(alfa);
		}

		if (beta.getPapa() != null) {
			if (beta.getPapa().getHijoIzq() == alfa) {
				beta.getPapa().setHijoIzq(beta);
			} else {
				beta.getPapa().setHijoDer(beta);
			}
		}

		alfa.setFe(calculaFe(alfa));
		beta.setFe(calculaFe(beta));

		return beta;

	}

	private NodoAVL<T> rIzqDer(NodoAVL<T> alfa) {

		NodoAVL<T> beta = alfa.getHijoIzq();
		NodoAVL<T> gamma = beta.getHijoDer();

		NodoAVL<T> b = gamma.getHijoIzq();
		NodoAVL<T> c = gamma.getHijoDer();

		if (alfa == this.raiz) {
			this.raiz = gamma;
		}

		gamma.setPapa(alfa.getPapa());

		alfa.setPapa(gamma);
		gamma.setHijoDer(alfa);

		beta.setPapa(gamma);
		gamma.setHijoIzq(beta);

		beta.setHijoDer(b);
		if (b != null) {
			b.setPapa(beta);
		}

		alfa.setHijoIzq(c);
		if (c != null) {
			c.setPapa(alfa);
		}

		if (gamma.getPapa() != null) {
			if (gamma.getPapa().getHijoIzq() == alfa) {
				gamma.getPapa().setHijoIzq(gamma);
			} else {
				gamma.getPapa().setHijoDer(gamma);
			}
		}

		alfa.setFe(calculaFe(alfa));
		beta.setFe(calculaFe(beta));
		gamma.setFe(calculaFe(gamma));

		return gamma;

	}

	private NodoAVL<T> rDerIzq(NodoAVL<T> alfa) {

		NodoAVL<T> beta = alfa.getHijoDer();
		NodoAVL<T> gamma = beta.getHijoIzq();

		NodoAVL<T> b = gamma.getHijoIzq();
		NodoAVL<T> c = gamma.getHijoDer();

		if (alfa == this.raiz) {
			this.raiz = gamma;
		}

		gamma.setPapa(alfa.getPapa());

		alfa.setPapa(gamma);
		gamma.setHijoIzq(alfa);

		beta.setPapa(gamma);
		gamma.setHijoDer(beta);

		beta.setHijoIzq(c);
		if (c != null) {
			c.setPapa(beta);
		}

		alfa.setHijoDer(b);
		if (b != null) {
			b.setPapa(alfa);
		}

		if (gamma.getPapa() != null) {
			if (gamma.getPapa().getHijoIzq() == alfa) {
				gamma.getPapa().setHijoIzq(gamma);
			} else {
				gamma.getPapa().setHijoDer(gamma);
			}
		}

		alfa.setFe(calculaFe(alfa));
		beta.setFe(calculaFe(beta));
		gamma.setFe(calculaFe(gamma));

		return gamma;

	}

	// insertar

	public void inserta(T dato) {

		if (raiz == null) {
			raiz = new NodoAVL(dato);
			return;
		}

		NodoAVL<T> actual = raiz;
		NodoAVL<T> papa = raiz;
		NodoAVL<T> nuevo = new NodoAVL(dato);

		while (actual != null) {
			papa = actual;

			if (dato.compareTo(actual.getDato()) <= 0) {
				actual = actual.getHijoIzq();
			} else {
				actual = actual.getHijoDer();
			}
		}

		papa.cuelga(nuevo);

		actual = nuevo;
		boolean termine = false;

		while (!termine && papa != null) {
			papa.setFe(calculaFe(papa));

			if (papa.getFe() == 0) {
				termine = true;
			}

			if (papa.getFe() == 2) {
				if (actual.getFe() >= 0) {
					papa = rDerDer(papa);
				} else {
					papa = rDerIzq(papa);
				}
			}

			if (papa.getFe() == -2) {
				if (actual.getFe() <= 0) {
					papa = rIzqIzq(papa);
				} else {
					papa = rIzqDer(papa);
				}
			}

			actual = papa;
			papa = papa.getPapa();

		}

	}

	// elimina

	public T elimina(T elem) {

		NodoAVL<T> actual = busca(elem);

		if (actual == null) {
			throw new RuntimeException();
		}

		NodoAVL<T> papa = actual.getPapa();
		NodoAVL<T> temp;
		T res = actual.getDato();

		// C1) Es hoja

		if (actual.getHijoIzq() == null && actual.getHijoDer() == null) {

			if (actual == raiz) {
				raiz = null;
			}

			if ((res.compareTo(actual.getPapa().getDato())) > 0) {
				papa.setHijoDer(null);
			}

			else {
				papa.setHijoIzq(null);
			}

			actual.setPapa(null);
			temp = papa;
		}

		// C2) Tiene un hijo

		if (actual.getHijoIzq() == null || actual.getHijoDer() == null) {
			NodoAVL<T> hijo;

			if (actual.getHijoIzq() == null) {
				hijo = actual.getHijoDer();
			} else {
				hijo = actual.getHijoIzq();
			}

			if (actual.equals(raiz)) {
				raiz = hijo;
			} else {
				papa.cuelga(hijo);
			}

			actual.setPapa(null);
			temp = papa;
		}

		// C3) Tiene 2 hijos

		else {
			NodoAVL<T> aux = actual.getHijoDer();

			while (aux.getHijoIzq() != null) {
				aux = aux.getHijoIzq();
			}
			actual.setDato(aux.getDato());

			if (aux != actual.getHijoDer()) {
				temp = aux.getPapa();
				if (aux.getHijoDer() == null) {
					aux.getPapa().setHijoIzq(null);
					aux.setPapa(null);
				}

				else {
					aux.getPapa().cuelga(aux.getHijoDer());
					aux.setPapa(null);
				}
			}

			else {
				temp = actual;
				if (aux.getHijoDer() == null) {
					actual.setHijoDer(null);
					aux.setPapa(null);
				} else {
					aux.getHijoDer().setPapa(actual.getPapa());
					aux.getPapa().setHijoDer(aux.getHijoDer());
					aux.setHijoDer(null);
					aux.setPapa(null);
				}
			}
		}

		boolean termine = false;
		NodoAVL<T> papaTemp = temp.getPapa();

		while (!termine && papaTemp != null) {

			papaTemp.setFe(calculaFe(papaTemp));

			if (papaTemp.getFe() == 1 || papaTemp.getFe() == -1) {
				termine = true;
			}

			if (papaTemp.getFe() == 2) {
				if (actual.getFe() >= 0) {
					papaTemp = rDerDer(papaTemp);
				} else {
					papaTemp = rDerIzq(papaTemp);
				}
			}

			if (papaTemp.getFe() == -2) {
				if (actual.getFe() <= 0) {
					papaTemp = rIzqIzq(papaTemp);
				} else {
					papaTemp = rIzqDer(papaTemp);
				}
			}

			temp = papaTemp;
			papaTemp = papaTemp.getPapa();
		}

		return res;
	}

}
