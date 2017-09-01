public class Main {
	
	
	public static void main(String[] args) {
		int tamanhoEntrada = 1000;
		Integer[][] entrada = new Integer[tamanhoEntrada][64];
		Integer[][] entrada2 = new Integer[tamanhoEntrada][64];
		int[] avaliacao;
		Integer[][] selecionados;
		
		KnightTour kt = new KnightTour(); // SEM EXPECTATIVA DE VIDA
		KnightTourV2 kt2 = new KnightTourV2(); // COM EXPECTATIVA DE VIDA
		
		entrada = kt.inicializaPopulacao(entrada, tamanhoEntrada);
		entrada2 = kt2.inicializaPopulacao(entrada, tamanhoEntrada);
		for (int T = 0; T < 1000; T++) {
			avaliacao = kt.avaliaPopulacao(entrada);
			selecionados = kt.selecionaPais(avaliacao, entrada); 
			entrada = kt.recombinar(selecionados); 
			/*
			entrada = aplicaMutacao(entrada);
			
			 */
		} 
		
		
	}

}
