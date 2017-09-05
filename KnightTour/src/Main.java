import java.util.Arrays;

public class Main {
	
	
	public static void main(String[] args) {
		int tamanhoEntrada = 1000;
		Integer[][] entrada = new Integer[tamanhoEntrada][64];
//		Integer[][] entrada2 = new Integer[tamanhoEntrada][64];
		int[] avaliacao = null;
		Integer[][] selecionados;
		
		KnightTour kt = new KnightTour(); // SEM EXPECTATIVA DE VIDA
//		KnightTourV2 kt2 = new KnightTourV2(); // COM EXPECTATIVA DE VIDA
		
		entrada = kt.inicializaPopulacao(entrada, tamanhoEntrada);
//		entrada2 = kt.inicializaPopulacao(entrada, tamanhoEntrada);
		for (int T = 0; T < 100000; T++) {
			if(T==2){
				System.out.println("2 �pocas");
				System.out.println(Arrays.toString(avaliacao.clone()));
			}
			if(T==10){
				System.out.println("10 �pocas");
				System.out.println(Arrays.toString(avaliacao.clone()));
			}
			if(T==100){
				System.out.println("100 �pocas");
				System.out.println(Arrays.toString(avaliacao.clone()));
			}
			if(T==1000){
				System.out.println("1000 �pocas");
				System.out.println(Arrays.toString(avaliacao.clone()));
			}
			if(T==5000){
				System.out.println("5000 �pocas");
				System.out.println(Arrays.toString(avaliacao.clone()));
			}
			if(T==10000){
				System.out.println("10000 �pocas");
				System.out.println(Arrays.toString(avaliacao.clone()));
			}
			avaliacao = kt.avaliaPopulacao(entrada);
			selecionados = kt.selecionaPais(avaliacao, entrada); 
			entrada = kt.recombinar(selecionados); 
			entrada = kt.aplicaMutacao(entrada);
		} 
		
		
	}

}
