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
		Integer[] teste ={13,35,24,18,14,33,27,25,17,8,39,31,46,36,43,06,10,62,16,59,55,12,26,1,4,21,19,64,61,48,47,7,11,57,37,52,9,3,56,34,15,5,51,32,28,60,54,42,44,30,20,23,40,58,38,45,41,63,29,50,2,22,53,49}; 
//		Integer[] teste = { 1, 24, 39, 36, 11, 22, 49, 34, 40, 37, 12, 23, 50, 35, 10, 21, 13, 2, 25, 38, 57, 64, 33,
//				48, 26, 41, 60, 63, 54, 51, 20, 9, 3, 14, 53, 56, 61, 58, 47, 32, 42, 27, 62, 59, 52, 55, 8, 19, 15, 4,
//				29, 44, 17, 6, 31, 46, 28, 43, 16, 5, 30, 45, 18, 7 }; //{SOLUÇÃO TESTE}
		entrada[0]=teste;
		return entrada;
	}
	
	public int[] avaliaPopulacao2(Integer[][] entrada) {
		int[] avaliacao = new int[entrada.length];
		int ponteiro = 0;
		int ponteiro2 = 0;
		for (int i = 0; i < entrada.length; i++) {
			ponteiro = procuraIndividuo(entrada, 1, i);
			for (int individuo = 1; individuo < entrada[0].length; individuo++) {
				ponteiro2 = procuraIndividuo(entrada, individuo + 1, i);
				if (avaliaIndividuo2(ponteiro, ponteiro2)) {
					avaliacao[i]++;
				}
				ponteiro = ponteiro2;
			}
		}
		
		return avaliacao;
	}

	private boolean avaliaIndividuo2(int ponteiro, int ponteiro2) {
		Integer[] valoresAceitos = { 6, -6, 10, -10, 15, -15, 17, -17 }; 
		List<Integer> listaAceitos = Arrays.asList(valoresAceitos);
		if(listaAceitos.contains(ponteiro-ponteiro2)){
			return true;
		}
		return false;
	}

	private int procuraIndividuo(Integer[][] entrada, int individuo, int indice){
		for (int i = 0; i <= entrada.length; i++) {
			if(individuo == 64){
				
			}
			if(entrada[indice][i] == individuo){
				return i;
			}
		}
		return 0;
	}

}
