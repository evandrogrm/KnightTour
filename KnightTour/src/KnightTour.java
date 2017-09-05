import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
//		Integer[] teste = { 1, 24, 39, 36, 11, 22, 49, 34, 40, 37, 12, 23, 50, 35, 10, 21, 13, 2, 25, 38, 57, 64, 33,
//				48, 26, 41, 60, 63, 54, 51, 20, 9, 3, 14, 53, 56, 61, 58, 47, 32, 42, 27, 62, 59, 52, 55, 8, 19, 15, 4,
//				29, 44, 17, 6, 31, 46, 28, 43, 16, 5, 30, 45, 18, 7 }; //{SOLUÇÃO TESTE}
//		entrada[0]=teste;
		return entrada;
	}
	
	public int[] avaliaPopulacao(Integer[][] entrada) {
		int[] avaliacao = new int[entrada.length];
		int ponteiro = 0;
		int ponteiro2 = 0;
		for (int i = 0; i < entrada.length; i++) {
			ponteiro = procuraIndividuo(entrada, 1, i);
			for (int individuo = 1; individuo < entrada[0].length; individuo++) {
				ponteiro2 = procuraIndividuo(entrada, individuo + 1, i);
				if (avaliaIndividuo2(ponteiro, ponteiro2)) {
					avaliacao[i]+=5;// 315 = ótimo
				}else{
					avaliacao[i]+=1;
				}
				ponteiro = ponteiro2;
			}
			if(avaliacao[i]>=315){
				System.out.println("Encontrado solução!");
				System.out.println("Entrada["+i+"] = "+Arrays.toString(entrada[i]));
				ponteiro = procuraIndividuo(entrada, 1, i);
				ponteiro2 = procuraIndividuo(entrada, 64, i);
				if(avaliaIndividuo2(ponteiro, ponteiro2)){
					System.out.println("Ciclo Hamiltoniano");
				}
			}
			
		}
		
		return avaliacao;
	}

	private boolean avaliaIndividuo2(int ponteiro, int ponteiro2) {
		Integer[] valoresAceitos = {6, -6, 10, -10, 15, -15, 17, -17}; 
		List<Integer> listaAceitos = Arrays.asList(valoresAceitos);
		if(listaAceitos.contains(ponteiro-ponteiro2)){
			return true;
		}
		return false;
	}

	private int procuraIndividuo(Integer[][] entrada, int individuo, int indice){
		for (int i = 0; i <= entrada.length; i++) {
			if(entrada[indice][i] == individuo){
				return i;
			}
		}
		return 0;
	}
	/** ORDENA OS VETORES AVALIACAO E ENTRADA**/
	public Integer[][] selecionaPais(int[] avaliacao, Integer[][] entrada) {
		Integer[][] selecionados = new Integer[entrada.length / 2][entrada[0].length];
		boolean changed = true;
		int tempAva = 0;
		Integer[] tempEnt = null;
		while (changed) {
			changed = false;
			for (int i = 0; i < avaliacao.length - 1; i++) {
				if (avaliacao[i + 1] > avaliacao[i]) {
					// swap
					// swap(avaliacao2[i+1],avaliacao2[i]);
					tempAva = avaliacao[i];
					avaliacao[i] = avaliacao[i + 1];
					avaliacao[i + 1] = tempAva;
					// swap(entrada2[i+1],entrada2[i]);
					tempEnt = entrada[i];
					entrada[i] = entrada[i + 1];
					entrada[i + 1] = tempEnt;
					changed = true;
				}
			}
		}
		for (int i = 0; i < selecionados.length; i++) {
			selecionados[i] = entrada[i];
		}
		return selecionados;
	}

	public Integer[][] recombinar(Integer[][] selecionados) {
		Integer[][] populacao = new Integer[selecionados.length * 2][selecionados[0].length];
		Integer[][] filhos = new Integer[selecionados.length][selecionados[0].length];
		ArrayList<Integer> listaJaCombinados = new ArrayList<>();
		ArrayList<Integer[]> listaSelecionados = new ArrayList<>();
		Random rnd = ThreadLocalRandom.current();
		int[] pontoDeCorte = new int[2];
		pontoDeCorte[0] = 1 + rnd.nextInt(selecionados[0].length - 1);
		while(pontoDeCorte[1] < pontoDeCorte[0]){
			pontoDeCorte[1] = 1 + rnd.nextInt(selecionados[0].length - 1);
		}
//		System.out.println("Pontos de corte: "+Arrays.toString(pontoDeCorte));
		int indiceFilhos = 0;
		for (int i = 0; i < filhos.length / 2; i++) {
			int controle = 0;
			int[] filhoEscolhido = new int[2];
			while (controle < 2) {
				int escolhido = 0 + rnd.nextInt(selecionados.length);
				if (listaJaCombinados.isEmpty() || (!listaJaCombinados.contains(escolhido))) {
					filhoEscolhido[controle] = escolhido;
					listaJaCombinados.add(escolhido);
					controle++;
				}
			}
			Integer[][] filhosGerados = cutAndCrossFill(selecionados[filhoEscolhido[0]], selecionados[filhoEscolhido[1]], pontoDeCorte);
			filhos[indiceFilhos] = filhosGerados[0].clone();
			filhos[indiceFilhos + 1] = filhosGerados[1].clone();
			indiceFilhos += 2;
		}
		for (int i = 0; i < selecionados.length; i++) {
			if(!listaSelecionados.contains(selecionados[i])){
				populacao[i] = selecionados[i].clone(); // pais
				listaSelecionados.add(selecionados[i].clone());
			}
		}
		indiceFilhos = 0;
		for (int i = selecionados.length; i < populacao.length; i++) {
			if(!listaSelecionados.contains(filhos[indiceFilhos])){
				populacao[i] = filhos[indiceFilhos].clone(); // filhos
				listaSelecionados.add(filhos[indiceFilhos]);
			}
			indiceFilhos++;
		}

		return populacao;
	}

	private Integer[][] cutAndCrossFill(Integer[] pai, Integer[] mae, int[] pontoDeCorte) {
		Integer[][] filhos = new Integer[2][pai.length];
		Integer[] array = null;
		Integer[] arrayOutro = null;
		for (int i = 0; i < filhos.length; i++) {
			if (i == 0) {
				array = pai.clone();
				arrayOutro = mae.clone();
			} else {
				array = mae.clone();
				arrayOutro = pai.clone();
			}
			int c = 0;
			for (int j = 0; j < pontoDeCorte[0]; j++) {
				filhos[i][c] = array[j];
				c++;
			}
			for (int j = pontoDeCorte[0]; j < pontoDeCorte[1]; j++) {
				if (!contemNumeroNoArray(arrayOutro[j], filhos[i])) {
					filhos[i][c] = arrayOutro[j];
					c++;
				}
			}
			for (int j = pontoDeCorte[1]; j < array.length; j++) {
				if (!contemNumeroNoArray(array[j], filhos[i])) {
					filhos[i][c] = array[j];
					c++;
				}
			}
			while (c <= filhos[0].length - 1) {
				for (int j = 0; j < arrayOutro.length; j++) {
					if (!contemNumeroNoArray(arrayOutro[j], filhos[i])) {
						filhos[i][c] = arrayOutro[j];
						c++;
					}
				}
			}

		}
		return filhos;
	}
	
	private boolean contemNumeroNoArray(Integer num, Integer[] array) {
		for (int j = 0; j < array.length; j++) {
			if (array[j] == num) {
				return true;
			}
		}
		return false;
	}

	public Integer[][] aplicaMutacao(Integer[][] entrada) {
		double perc = entrada.length*0.01;//mutação de 1%
		double controle = 0;
		while(perc >= controle){
			Random rnd = ThreadLocalRandom.current();
			int indice=0,coluna1=0,coluna2=0;
			while (coluna1==coluna2) {
				indice = 0 + rnd.nextInt(entrada.length);
				coluna1 = 0 + rnd.nextInt(entrada[0].length);
				coluna2 = 0 + rnd.nextInt(entrada[0].length);
			}
			//swap
			int temp = entrada[indice][coluna1];
			entrada[indice][coluna1] = entrada[indice][coluna2];
			entrada[indice][coluna2] = temp;
			controle++;
		}
		
		return entrada;
	}

}
