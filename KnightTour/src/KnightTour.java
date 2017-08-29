import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KnightTour {

	public Integer[][] inicializaPopulacao(Integer[][] entrada, int tamanho) {
		Integer[] modelo = new Integer[64];
		for (int i = 0; i < modelo.length; i++) {
			modelo[i] = i+1;
		}
		List<Integer> entradaModelo = Arrays.asList(modelo);
		for (int i = 0; i < tamanho; i++) {
			Collections.shuffle(entradaModelo);
			entrada[i] = entradaModelo.toArray(modelo.clone());
//			System.out.println("Entrada["+i+"] = "+Arrays.toString(entrada[i]));
		}
//		Integer[] teste ={13,35,24,18,14,33,27,25,17,8,39,31,46,36,43,06,10,62,16,59,55,12,26,1,4,21,19,64,61,48,47,7,11,57,37,52,9,3,56,34,15,5,51,32,28,60,54,42,44,30,20,23,40,58,38,45,41,63,29,50,2,22,53,49}; 
		Integer[] teste = { 1, 24, 39, 36, 11, 22, 49, 34, 40, 37, 12, 23, 50, 35, 10, 21, 13, 2, 25, 38, 57, 64, 33,
				48, 26, 41, 60, 63, 54, 51, 20, 9, 3, 14, 53, 56, 61, 58, 47, 32, 42, 27, 62, 59, 52, 55, 8, 19, 15, 4,
				29, 44, 17, 6, 31, 46, 28, 43, 16, 5, 30, 45, 18, 7 }; 
		entrada[0]=teste;
		return entrada;
	}

	public int[] avaliaPopulacao(Integer[][] entrada) {
		int[] avaliacao = new int[entrada.length];
		Integer[] v6p = {1,2,9,10,17,18,25,26,33,34,41,42,49,50,57,58,59,60,61,62,63,64}; 
		List<Integer> lista6p = Arrays.asList(v6p);//6p = 6positivo / 6n = 6negativo
		Integer[] v10p = {7,8,15,16,23,24,31,32,39,40,47,48,55,56,57,58,59,60,61,62,63,64};
		List<Integer> lista10p = Arrays.asList(v10p);
		Integer[] v15p = {1,9,17,25,33,41,49}; //1,9,17,25,33,41,>=49
		List<Integer> lista15p = Arrays.asList(v15p);
		Integer[] v17p = {8,16,24,32,40}; //08,16,24,32,40,>=48
		List<Integer> lista17p = Arrays.asList(v17p);
		Integer[] v6n = {15,16,23,24,31,32,39,40,47,48,55,56,63,64}; //<=8,15,16,23,24,31,32,39,40,47,48,55,56,63,64
		List<Integer> lista6n = Arrays.asList(v6n);
		Integer[] v10n = {17,18,25,26,33,34,41,42,49,50,57,58}; //<=10,17,18,25,26,33,34,41,42,49,50,57,58
		List<Integer> lista10n = Arrays.asList(v10n);
		Integer[] v15n = {24,32,40,48,56,64}; //<=16,24,32,40,48,56,64
		List<Integer> lista15n = Arrays.asList(v15n);	
		Integer[] v17n = {25,33,41,49,57}; //<=18,25,33,41,49,57
		List<Integer> lista17n = Arrays.asList(v17n);	
		
		for (int v = 0; v < entrada.length; v++) {
			for (int i = 0; i < entrada[0].length; i++) {
				if (!lista6p.contains(i + 1) && avaliaIndividuo(entrada[v], 6, i)) {
					avaliacao[v]++;
				}
				if (!lista10p.contains(i + 1) && avaliaIndividuo(entrada[v], 10, i)) {
					avaliacao[v]++;
				}
				if (!lista15p.contains(i + 1) && avaliaIndividuo(entrada[v], 15, i)) {
					avaliacao[v]++;
				}
				if ((i + 1 < 49 && !lista17p.contains(i + 1)) && avaliaIndividuo(entrada[v], 17, i)) {
					avaliacao[v]++;
				}
				if ((i + 1 > 8 && !lista6n.contains(i + 1)) && avaliaIndividuo(entrada[v], -6, i)) {
					avaliacao[v]++;
				}
				if ((i + 1 > 10 && !lista10n.contains(i + 1)) && avaliaIndividuo(entrada[v], -10, i)) {
					avaliacao[v]++;
				}
				if ((i + 1 > 16 && !lista15n.contains(i + 1)) && avaliaIndividuo(entrada[v], -15, i)) {
					avaliacao[v]++;
				}
				if ((i + 1 > 18 && !lista17n.contains(i + 1)) && avaliaIndividuo(entrada[v], -17, i)) {
					avaliacao[v]++;
				}
			}
			avaliacao[v] /= 2;
		}
		
		return avaliacao;
	}

	private boolean avaliaIndividuo(Integer[] vetor, int tamanho, int i) {
		if (i + 1 + tamanho <= vetor.length) {
			if (vetor[i] + vetor[i + tamanho] == tamanho) {
				System.out.println("vetor[" + i + "]=" + vetor[i] + "-" + vetor[i + tamanho] + "=" + tamanho);
				return true;
			}
		}
		return false;
	}

}
